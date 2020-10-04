package com.web.blog.controller.social;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.web.blog.model.dto.user.User;
import com.web.blog.model.service.social.KakaoAPI;
import com.web.blog.model.service.user.UserService;
import com.web.blog.util.BasicResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class SocialController {

    @Autowired
    UserService userService;

    @Autowired
    KakaoAPI kakao;

    @RequestMapping(value = "/login/oauth")
    public Object login(String code, String redirectUrl) {
        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();
        System.out.println(" oauth code : " + code);

        try {
            String access_Token = kakao.getAccessToken(code, redirectUrl);
            System.out.println("access_Token : " + access_Token);
            HashMap<String, String> userInfo = kakao.getUserInfo(access_Token);
            System.out.println("login Controller : " + userInfo);

            String userEmail = userInfo.get("userEmail");

            // 기존에 정보를 저장한 회원정보가 있는 경우
            if (userService.socialuserByEmail(userEmail) != null) {
                User user = userService.socialuserByEmail(userEmail);
                user.setProviderName("KAKAO");
                user.setUserEmail(userEmail);
                user.setAccessToken(access_Token);

                result.status = true;
                result.data = "Success";
                result.object = user;

                response = new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                // 이메일이 없는 경우 정보 저장
                System.out.println();
                User OauthUser = new User();
                OauthUser.setProviderName("KAKAO");
                OauthUser.setUserEmail(userEmail);
                OauthUser.setUserNickname(userEmail);
                OauthUser.setAccessToken(access_Token);
                System.out.println(" 소셜 유저 정보 " + OauthUser.toString());
                int res = userService.socialuserInsert(OauthUser);
                System.out.println("res : " + res);
                if (res == 1) {
                    User user = userService.socialuserByEmail(userEmail);
                    user.setUserNickname("user-" + user.getUserNo());
                    userService.userUpdate(user);
                    result.status = true;
                    result.data = "Success";
                    result.object = user;

                    response = new ResponseEntity<>(result, HttpStatus.OK);
                } else {
                    result.status = false;
                    result.data = "socialLogin Fail";

                    response = new ResponseEntity<>(result, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            result.status = false;
            result.data = "socialLogin Fail";

            response = new ResponseEntity<>(result, HttpStatus.OK);
        }

        return response;
    }

    @GetMapping(value = "/logout/oauth")
    public Object logout(String accesstoken) {
        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        try {
            kakao.kakaoLogout(accesstoken);
            System.out.println("Success logout");
            result.status = true;
            result.data = "Success logout";

            response = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.status = false;
            result.data = "logout Fail";
            System.out.println("logout Fail");

            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

}