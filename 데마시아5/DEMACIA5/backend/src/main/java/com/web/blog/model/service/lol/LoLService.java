package com.web.blog.model.service.lol;

import com.web.blog.model.dto.lol.LeagueInfo;
import com.web.blog.model.dto.lol.Summoner;

public interface LoLService {
    // 소환사명으로 계정 정보 조회
    public Summoner lolSummoner(String summonerName);

    // 소환사ID로 리그정보 조회
    public LeagueInfo lolLeagueInfo(Summoner summoner);

    // 게임 랭크 업데이트
    public int lolUpdateRank(int userNo, LeagueInfo info);

    //게임 랭크 삭제
    public int deleteGameRank(int userNo);

}