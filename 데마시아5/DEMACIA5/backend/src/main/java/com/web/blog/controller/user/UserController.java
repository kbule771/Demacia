package com.web.blog.controller.user;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import com.web.blog.model.dto.user.User;
import com.web.blog.model.dto.user.UserVote;
import com.web.blog.model.service.user.UserService;
import com.web.blog.util.BasicResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/account/user")
    @ApiOperation(value = "사용자 조회")
    public Object userInfo(@RequestParam int userNo) {
        User user = userService.userSearch(userNo);

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();
        try {
            result.status = true;
            result.data = "Success";
            result.object = user;

            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.status = false;
            result.data = "Fail";

            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return response;

    }

    @GetMapping("/account/login")
    @ApiOperation(value = "로그인")
    public Object login(String userEmail, String userPw) {

        System.out.println("userEmail : " + userEmail);
        System.out.println("userPw : " + userPw);

        ResponseEntity response = null;
        try {
            User user = new User();
            user = userService.login(userEmail, userPw);
            // 로그인에 성공한 경우 토큰 생성
            // 토큱정보를 해더로 보내고 나머지는 map에 담는다
            BasicResponse result = new BasicResponse();

            result.data = "success";
            result.status = true;
            result.object = user;
            String msg = user.getUserEmail();
            if (msg.equals("id failed")) {
                result.data = "Incorrect ID";
                result.status = false;
            } else if (msg.equals("password failed")) {
                result.data = "Incorrect PW";
                result.status = false;
            }
            result.object = user;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("No User");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @PostMapping("/account/signup")
    @ApiOperation(value = "가입하기")
    public Object signup(@RequestBody User user) throws MessagingException, SQLException {
        ResponseEntity response = null;
        BasicResponse result = new BasicResponse();

        int res = userService.userInsert(user);

        if (res == 0) {
            result.status = false;
            result.data = "fail";
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            userService.sendMail(user);
            result.status = true;
            result.data = "success";
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }

        return response;

    }

    @GetMapping("/account/checkEmail")
    @ApiOperation(value = "이메일 중복확인")
    public Object checkEmail(String userEmail) throws MessagingException, SQLException {

        ResponseEntity response = null;
        BasicResponse result = new BasicResponse();
        System.out.println("확인 email : " + userEmail);

        int res = userService.userByEmail(userEmail);

        if (res == 1) { // 이메일이 있다면 (중복)
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else { // 중복 아님
            result.status = true;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("/account/checkNickname")
    @ApiOperation(value = "닉네임 중복확인")
    public Object checkNickname(String userNickname) throws MessagingException, SQLException {

        ResponseEntity response = null;
        BasicResponse result = new BasicResponse();

        System.out.println(userNickname);
        int res = userService.userByNickname(userNickname);
        System.out.println(res);

        if (res == 1) { // 닉네임이 있다면 (중복)
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else { // 중복 아님
            result.status = true;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }

        return response;

    }

    @PutMapping("/account/")
    @ApiOperation(value = "회원 정보 수정")
    public Object update(@RequestBody User user) {
        int res = userService.userUpdate(user);
        final BasicResponse result = new BasicResponse();
        result.status = false;
        result.data = "수정실패";

        if (res != 0) {
            result.status = true;
            result.data = "success";
        }

        return new ResponseEntity<>(result, result.status ? HttpStatus.OK : HttpStatus.valueOf(500));

    }

    @DeleteMapping("/account/")
    @ApiOperation(value = "회원 탈퇴")
    public Object delete(@RequestParam int userNo) {

        int res = userService.userDelete(userNo);

        final BasicResponse result = new BasicResponse();
        if (res == 1) {
            result.status = true;
            result.data = "Success";
        } else {
            result.status = false;
            result.data = "Fail";
        }

        return new ResponseEntity<>(result, result.status ? HttpStatus.OK : HttpStatus.valueOf(500));
    }

    @GetMapping("/account/uservote")
    @ApiOperation(value = "사용자 투표 이력")
    public Object userVote(@RequestParam int userNo) {
        ArrayList<UserVote> userVote = userService.userVote(userNo);

        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();
        try {
            result.status = true;
            result.data = "Success";
            result.object = userVote;

            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.status = false;
            result.data = "Fail";

            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return response;

    }

    @GetMapping("/account/userhitrate")
    @ApiOperation(value = "사용자 적중률")
    public Object userHitRate(@RequestParam int userNo) {
        
        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();
        Double ret = userService.userHitRate(userNo);
        try {
            result.status = true;
            result.data = "Success";
            result.object = ret;

            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.status = false;
            result.data = "Fail";

            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return response;

    }

}