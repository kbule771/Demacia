package com.web.blog.model.service.post;

import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.List;

import com.web.blog.model.dao.post.PostDao;

import com.web.blog.model.dto.post.Post;
import com.web.blog.model.dto.post.PostDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao dao;

    @Override
    public int postInsert(Post post) {
        LocalDateTime time = LocalDateTime.now();
        time = time.plusHours(post.getDeadLineFront());
        Timestamp ts = Timestamp.valueOf(time);
        post.setDeadLine(ts);
        System.out.println(post.toString());
        return dao.postInsert(post);
    }

    @Override
    public List<PostDetail> postSearchByTitle(String orderby, String title, int limit, int offset, String tabOption) {
        return dao.postSearchByTitle(orderby, title, limit, offset, tabOption);
    }

    @Override
    public List<PostDetail> postSearchByUser(String orderby, String userNickname, int limit, int offset, String tabOption) {
        return dao.postSearchByUser(orderby, userNickname, limit, offset, tabOption);
    }

    @Override
    public List<PostDetail> postSearchByOption(String orderby, String option, int limit, int offset, String tabOption) {
        return dao.postSearchByOption(orderby, option, limit, offset, tabOption);
    }

    @Override
    public List<PostDetail> postSearch(String orderby, int limit, int offset, String tabOption) {
        return dao.postSearch(orderby, limit, offset, tabOption);
    }

    @Override
    public PostDetail postDetail(int postNo) {
        PostDetail post = dao.postDetail(postNo);
        dao.postVisitCount(postNo, post.getView());
        return post;
    }

    @Override
    public int postDelete(int postNo) {
        return dao.postDelete(postNo);
    }

    @Override
    public int postUpdate(Post post) {
        return dao.postUpdate(post);
    }

}