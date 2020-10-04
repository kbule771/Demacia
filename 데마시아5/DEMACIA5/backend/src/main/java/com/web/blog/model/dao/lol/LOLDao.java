package com.web.blog.model.dao.lol;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LOLDao {

    // account ID 추가
    public int updateAccountId(int userNo, String accountId);

    // 웹 랭크 업데이트
    public int solUpdateRank(int userNo, int userSolRankNum, String userSolTierStr, String userSolRankStr);

    // 게임 랭크 업데이트
    public int lolUpdateRank(int userNo, String summonerName, int userGameRankNum, String userGameTierStr,
            String userGameRankStr);

    // 게임 랭크 삭제
    public int deleteGameRank(int userNo);
}