package com.web.blog.model.dao.vote;

import java.util.ArrayList;

import com.web.blog.model.dto.vote.Vote;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VoteDao {
 
    // 투표 
    public int voteInsert(Vote vote);

    // 투표 여부
    public Vote voteStatus(int postNo, int userNo);
    
    // 투표 수정
    public int voteUpdate(int postNo, int userNo, int camp);

    // 투표 취소
    public int voteDelete(int postNo, int userNo);

    // 투표 집계
    public ArrayList<Vote> voteCount(int postNo, int camp);

    // 특정 회원의 투표 이력
    public ArrayList<Vote> userVote(int userNo);

}