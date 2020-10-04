package com.web.blog.model.dao.user;

import com.web.blog.model.dto.user.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    // 회원가입
    public int userInsert(User user);

    // 로그인
    public User login(String userEmail, String userPw);

    // 전체 유저수
    public int userTotal();

    // 회원 정보 조회
    public User userSearch(int userNo);

    // 회원 정보 수정
    public int userUpdate(User user);

    // 회원 정보 삭제
    public int userDelete(int userNo);

    // 이메일 찾기
    public String userFindEmail(String user);

    // 비밀번호 찾기
    public String userFindPW(String userEmail, String userNickname);

    // 닉네임으로 조회
    public int userByNickname(String userNickname);

    // 이메일로 조회
    public int userByEmail(String userEmail);

    // 소셜 이메일로 유저정보 조회
    public User socialuserByEmail(String userEmail);

    // 소셜 회원가입
    public int socialuserInsert(User user);

    // 투표 적중률
	public Double userHitRate(int userNo);

}