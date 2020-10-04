package com.web.blog.model.service.post;

import java.sql.SQLException;

import com.web.blog.model.dao.post.LikesDao;
import com.web.blog.model.dto.post.Likes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikesServiceImpl implements LikesService {

    @Autowired
    private LikesDao dao;

    @Override
    public int likesCount(int postNo) throws SQLException{
        return dao.likesCount(postNo);
    }

    @Override
    public int likesInsert(Likes likes) {
        if(dao.likesStatus(likes.getPostNo(), likes.getUserNo()) == 0){
            return dao.likesInsert(likes);
        }else{
            return 0;
        }
    }

    @Override
    public int likesDelete(int postNo, int userNo) {
        return dao.likesDelete(postNo, userNo);
    }

    @Override
    public int likesStatus(int postNo, int userNo) {
        if(dao.likesStatus(postNo, userNo)>=1){
            return 1;
        }else{
            return 0;
        }
    }

}