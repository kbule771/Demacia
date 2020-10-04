package com.web.blog.model.service.vote;

import java.util.ArrayList;

import com.web.blog.model.dao.user.UserDao;
import com.web.blog.model.dao.vote.VoteDao;
import com.web.blog.model.dto.vote.Vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteDao dao;
    private UserDao userDao;

    @Override
    public int voteInsert(Vote vote) {
        return dao.voteInsert(vote);

    }

    @Override
    public Vote voteStatus(int postNo, int userNo) {
        return dao.voteStatus(postNo, userNo);
    }

    @Override
    public int voteUpdate(int postNo, int userNo, int camp) {
        return dao.voteUpdate(postNo, userNo, camp);
    }

    @Override
    public int voteDelete(int postNo, int userNo) {
        return dao.voteDelete(postNo, userNo);
    }

    @Override
    public int voteCount(int postNo, int camp) {
        ArrayList<Vote> arr = dao.voteCount(postNo, camp);
        int res = 0;
        if (arr != null) {
            for (Vote v : arr) {
                res +=  v.getUserGameRankNum()+v.getUserSolRankNum();
            }
        }
        return res;
    }

}