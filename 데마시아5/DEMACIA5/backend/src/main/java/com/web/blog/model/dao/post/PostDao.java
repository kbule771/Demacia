package com.web.blog.model.dao.post;

import java.util.List;

import com.web.blog.model.dto.post.Post;
import com.web.blog.model.dto.post.PostDetail;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PostDao {

     // 게시물 등록
     public int postInsert(Post post);

     // 게시물 제목으로 검색
     public List<PostDetail> postSearchByTitle(String orderby, String title, int limit, int offset, String tabOption);

     // 게시물 유저로 검색
     public List<PostDetail> postSearchByUser(String orderby, String userNickname, int limit, int offset, String tabOption);

     // 게시물 옵션으로 검색
     public List<PostDetail> postSearchByOption(String orderby, String option, int limit, int offset, String tabOption);

     // 게시물 검색
     public List<PostDetail> postSearch(String orderby, int limit, int offset, String tabOption);

     // 게시물 상세 조회
     public PostDetail postDetail(int postNo);

     // 게시물 삭제
     public int postDelete(int postNo);

     // 게시물 수정
     public int postUpdate(Post post);

     // 게시물 카운트
     public int postVisitCount(int postNo,int view);

}