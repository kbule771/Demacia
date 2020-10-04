package com.web.blog.model.service.post;

import java.sql.SQLException;

import com.web.blog.model.dto.post.Likes;

public interface LikesService {

    // like 개수 조회
    public int likesCount(int postNo) throws SQLException;

    // like 버튼 클릭(비활성화 -> 활성화)
    public int likesInsert(Likes likes);

    // like 버튼 클릭(활성화 -> 비활성화)
    public int likesDelete(int postNo, int userNo);

    // like 상태 조회
    public int likesStatus(int postNo, int userNo);

}