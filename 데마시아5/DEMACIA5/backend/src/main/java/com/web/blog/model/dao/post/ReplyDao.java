package com.web.blog.model.dao.post;

import java.util.List;

import com.web.blog.model.dto.post.Reply;
import com.web.blog.model.dto.post.ReplyUser;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReplyDao {

        // 게시글의 댓글 조회
        public List<Reply> replyList(int postNo, int replyOpt);

        // 게시글의 댓글 조회
        public List<ReplyUser> replyUserList(int postNo, int replyOpt);

        // 게시글의 전체 댓글 조회
        public List<Reply> replyAllList(int postNo);

        // 게시글의 전체 댓글 조회(user)
        public List<ReplyUser> replyUserAllList(int postNo);

        // 댓글 작성
        public int replyInsert(Reply reply);

        // 댓글 수정
        public int replyUpdate(Reply reply);

        // 댓글 삭제
        public int replyDelete(int replyNo);

}