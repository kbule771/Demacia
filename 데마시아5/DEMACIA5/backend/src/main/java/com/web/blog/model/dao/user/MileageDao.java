package com.web.blog.model.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MileageDao {

    // 사용자 마일리지 조회
    public int mileageSearch(int userNo);

    // 사용자 마일리지 순위 조회
    public List<Integer> mileageRank();

    // 마일리지 올리기
    public int mileageAdd(int postNo, int value, int mileage);

    // 마일리지 내리기
    public int mileageSub(int postNo, int value, int mileage);

    // 마일리지 차감(포스트 작성시)
    public int postMileage(int userNo, int mileage);

    // post의 isFinished 변경
    public int postFinished(int postNo,int isFinished);

    // post의 mileage 조회
    public int searchMileage(int postNo);

    // user의 mileage 조회
    public int searchUserMileage(int userNo);

    // solrank 변경
    public int updateSolRank(String userSolTierStr, String userSolRankStr, int userNo);

    // solranknum 변경
    public void updateSolRankNum(int userSolRankNum, int userNo);

}