package com.web.blog.model.service.user;

import java.util.ArrayList;

import javax.mail.MessagingException;

import com.web.blog.model.dto.user.User;
import com.web.blog.model.dto.user.UserVote;

public interface UserService {

	// 회원가입
	public int userInsert(User user);

	// 로그인
	public User login(String userEmail, String userPw);

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

	// 이메일 송신
	public int sendMail(User user) throws MessagingException;

	// 소셜 이메일로 유저정보 조회
	public User socialuserByEmail(String userEmail);

	// 소셜 회원가입
	public int socialuserInsert(User user);

	// 투표 이력
	public ArrayList<UserVote> userVote(int userNo);

	// 투표 적중률
	public Double userHitRate(int userNo);

}