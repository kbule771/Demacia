package com.web.blog.model.service.lol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.web.blog.model.dao.lol.LOLDao;
import com.web.blog.model.dto.lol.LeagueInfo;
import com.web.blog.model.dto.lol.Summoner;
import com.web.blog.util.TransRank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class LOLServiceImpl implements LoLService {

    final static String API_KEY = "RGAPI-4dcd2099-2605-4440-9864-f53a305141e7";



    @Autowired
    LOLDao loldao;

    @Override
    public Summoner lolSummoner(String summonerName) {
        BufferedReader br = null;
        Summoner summoner = null;

        try {
            summonerName = URLEncoder.encode(summonerName, "UTF-8");
            String urlstr = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName
                    + "?api_key=" + API_KEY;

            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("GET");

            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result = result + line;
            }
            JsonParser jsonParser = new JsonParser();
            JsonObject k = (JsonObject) jsonParser.parse(result);
            String accountId = k.get("accountId").getAsString();
            int profileIconId = k.get("profileIconId").getAsInt();
            long revisionDate = k.get("revisionDate").getAsLong();
            String name = k.get("name").getAsString();
            String id = k.get("id").getAsString();
            String puuid = k.get("puuid").getAsString();
            long summonerLevel = k.get("summonerLevel").getAsLong();
            String imgURL = "http://ddragon.leagueoflegends.com/cdn/9.16.1/img/profileicon/" + profileIconId + ".png";
            summoner = new Summoner(accountId, profileIconId, revisionDate, name, id, puuid, summonerLevel, imgURL);
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return summoner;
    }

    @Override
    public LeagueInfo lolLeagueInfo(Summoner summoner) {
        BufferedReader br = null;
        LeagueInfo leagueInfo = null;
        String summonerId = summoner.getId();

        try {
            summonerId = URLEncoder.encode(summonerId, "UTF-8");
            String urlstr = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerId + "?api_key="
                    + API_KEY;
            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8")); // 여기에 문자열을 받아와라.
            String result = "";
            String line;
            while ((line = br.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
                result = result + line;
            }
            JsonParser jsonParser = new JsonParser();
            JsonArray arr = (JsonArray) jsonParser.parse(result);
            JsonObject k = (JsonObject) arr.get(0);
            String leagueId = k.get("leagueId").getAsString();
            String summonerName = k.get("summonerName").getAsString();
            String queueType = k.get("queueType").getAsString();
            String tier = k.get("tier").getAsString();
            String rank = k.get("rank").getAsString();
            int leaguePoints = k.get("leaguePoints").getAsInt();
            int wins = k.get("wins").getAsInt();
            int losses = k.get("losses").getAsInt();

            String imgURL = "http://ddragon.leagueoflegends.com/cdn/9.16.1/img/profileicon/"
                    + summoner.getProfileIconId() + ".png";
            String profileImgURL = "http://ddragon.leagueoflegends.com/cdn/9.16.1/img/profileicon/"
                    + summoner.getProfileIconId() + ".png";
            String tierImgURL = "images/emblems/" + tier.toLowerCase() + "_" + rank + ".png";

            leagueInfo = new LeagueInfo(leagueId, summonerId, summonerName, queueType, tier, rank, leaguePoints, wins,
                    losses, imgURL, profileImgURL, tierImgURL);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return leagueInfo;
    }

    // 웹 랭크 업데이트 미완성
    // @Override
    // public int solUpdateRank(int userNo, LeagueInfo info) {
    // TransRank transRank = new TransRank();

    // String tier = info.getTier();
    // String rank = info.getRank();
    // int userGameRank = transRank.loltransferRankToNum(tier, rank);

    // return loldao.solUpdateRank(userNo, userGameRank);
    // };

    // 게임 랭크 업데이트
    @Override
    public int lolUpdateRank(int userNo, LeagueInfo info) {
        TransRank transRank = new TransRank();

        String userGameTierStr = info.getTier();
        String userGameRankStr = info.getRank();
        String summonerName = info.getSummonerName();
        int userGameRankNum = transRank.loltransferRankToNum(userGameTierStr, userGameRankStr);

        return loldao.lolUpdateRank(userNo, summonerName, userGameRankNum, userGameTierStr, userGameRankStr);
    }

    @Override
    public int deleteGameRank(int userNo) {
        return loldao.deleteGameRank(userNo);
    }
}