package com.web.blog.model.service.post;

import java.util.List;

import com.web.blog.model.dao.post.ReplyDao;
import com.web.blog.model.dto.post.Reply;
import com.web.blog.model.dto.post.ReplyUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao dao;

    // 게시글의 옵션별 댓글 조회
    @Override
    public List<Reply> replyList(int postNo, int replyOpt) {
        return dao.replyList(postNo, replyOpt);
    }

    // 게시글의 옵션별 댓글 조회
    @Override
    public List<ReplyUser> replyUserList(int postNo, int replyOpt) {
        return dao.replyUserList(postNo, replyOpt);
    }

    // 게시글의 전체 댓글 조회
    @Override
    public List<Reply> replyAllList(int postNo) {
        return dao.replyAllList(postNo);
    }

    // 게시글의 전체 댓글 조회 user
    @Override
    public List<ReplyUser> replyUserAllList(int postNo) {
        return dao.replyUserAllList(postNo);
    }

    // 댓글 작성
    @Override
    public int replyInsert(Reply reply) {
        return dao.replyInsert(reply);

    }

    // 댓글 수정
    @Override
    public int replyUpdate(Reply reply) {
        return dao.replyUpdate(reply);
    }

    // 댓글 삭제
    @Override
    public int replyDelete(int replyNo) {
        return dao.replyDelete(replyNo);
    }
}