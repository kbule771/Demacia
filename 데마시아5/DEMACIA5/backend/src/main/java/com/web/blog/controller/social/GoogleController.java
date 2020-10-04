package com.web.blog.controller.social;

import java.net.URI;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.web.blog.model.dto.social.GoogleOAuthRequest;
import com.web.blog.model.dto.social.GoogleOAuthResponse;
import com.web.blog.model.dto.user.User;
import com.web.blog.model.service.user.UserService;
import com.web.blog.util.BasicResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class GoogleController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login/google", method = RequestMethod.GET)
    public Object GoogleSignCallback(final String code, final String redirectUrl) throws JsonProcessingException{
        System.out.println(" google code : " + code);
 
        ResponseEntity response = null;
        final BasicResponse result = new BasicResponse();

        try {

        //HTTP Request를 위한 RestTemplate
        final RestTemplate restTemplate = new RestTemplate();

        //Google OAuth Access Token 요청을 위한 파라미터 세팅
        final GoogleOAuthRequest googleOAuthRequestParam = GoogleOAuthRequest
                .builder()
                .clientId("1023963510057-bijvog3gfp162178b7iqu978ruruqkq2.apps.googleusercontent.com")
                .clientSecret("lr8Wr76eiVRUS8Z-LNv5n7Bv")
                .code(code)
                .redirectUri(redirectUrl)
                .grantType("authorization_code").build();


        //JSON 파싱을 위한 기본값 세팅
	//요청시 파라미터는 스네이크 케이스로 세팅되므로 Object mapper에 미리 설정해준다.
	final ObjectMapper mapper = new ObjectMapper();
	mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(Include.NON_NULL);
       
        //AccessToken 발급 요청
        final String GOOGLE_TOKEN_BASE_URL = "https://oauth2.googleapis.com/token";
        final ResponseEntity<String> resultEntity = restTemplate.postForEntity(GOOGLE_TOKEN_BASE_URL, googleOAuthRequestParam, String.class);

        //Token Request
        final GoogleOAuthResponse requestresult = mapper.readValue(resultEntity.getBody(), new TypeReference<GoogleOAuthResponse>() {});
        String access_token = requestresult.getAccessToken();
        System.out.println("google accesstoken : "+access_token);
        
        //ID Token만 추출 (사용자의 정보는 jwt로 인코딩 되어있다)
	String jwtToken = requestresult.getIdToken();
	String requestUrl = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/tokeninfo")
	        .queryParam("id_token", jwtToken).encode().toUriString();
		
        String resultJson = restTemplate.getForObject(requestUrl, String.class);
        JSONObject json = new JSONObject(resultJson);
        
        String userEmail = json.getString("email");
        System.out.println("google id : "+userEmail);

        // 기존에 정보를 저장한 회원정보가 있는 경우
        if (userService.socialuserByEmail(userEmail) != null) {
                User user = userService.socialuserByEmail(userEmail);
                user.setProviderName("GOOGLE");
                user.setUserEmail(userEmail);
                user.setAccessToken(access_token);
                userService.userUpdate(user);
                
                result.status = true;
                result.data = "Success";
                result.object = user;

                response = new ResponseEntity<>(result, HttpStatus.OK);

            } else {
                // 이메일이 없는 경우 정보 저장
                User OauthUser = new User();
                OauthUser.setProviderName("GOOGLE");
                OauthUser.setUserEmail(userEmail);
                OauthUser.setUserNickname(userEmail);
                OauthUser.setAccessToken(access_token);
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
                e.printStackTrace();
                result.status = false;
                result.data = "socialLogin Fail";

                response = new ResponseEntity<>(result, HttpStatus.OK);
        }

        return response;

        }
        
        @GetMapping("/logout/google")
        public Object logout(String token) {
                ResponseEntity response = null;
                final BasicResponse result = new BasicResponse();
        
                try {
                        RestTemplate restTemplate = new RestTemplate();
                        final String requestUrl = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/revoke")
                        .queryParam("token", token).encode().toUriString();
                        String resultJson = restTemplate.postForObject(requestUrl, null, String.class);
                        System.out.println("Success logout : "+resultJson);
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
