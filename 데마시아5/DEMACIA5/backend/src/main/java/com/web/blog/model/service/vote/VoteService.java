package com.web.blog.model.service.vote;

import com.web.blog.model.dto.vote.Vote;

public interface VoteService {
    // 투표 
    public int voteInsert(Vote vote);

    // 투표 여부
    public Vote voteStatus(int postNo, int userNo);
    
    // 투표 수정
    public int voteUpdate(int postNo, int userNo, int camp);

    // 투표 취소
    public int voteDelete(int postNo, int userNo);

    // 각팀의 투표수
    public int voteCount(int postNo, int camp);

}