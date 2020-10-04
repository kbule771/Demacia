package com.web.blog.controller.post;

import java.sql.SQLException;

import com.web.blog.model.dto.post.Likes;
import com.web.blog.model.service.post.LikesService;
import com.web.blog.util.BasicResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class LikesController {

        @Autowired
        LikesService likesService;

        @GetMapping("/likes/count")
        @ApiOperation(value = "좋아요 갯수 조회")
        public Object likesCount(int postNo) throws SQLException {

                int res = likesService.likesCount(postNo);

                ResponseEntity response = null;
                try {
                        BasicResponse result = new BasicResponse();

                        if (res >= 0) {
                                result.status = true;
                                result.data = "success";
                                result.object = res;
                                response = new ResponseEntity<>(result, HttpStatus.OK);
                        }
                } catch (Exception e) {
                        System.out.println("Fail to load likes count");
                        response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }

                return response;
        }

        @PostMapping("/likes/")
        @ApiOperation(value = "좋아요")
        public Object likesInsert(@RequestBody Likes likes) {
                ResponseEntity response = null;
                try {
                        final BasicResponse result = new BasicResponse();
                        if (likesService.likesInsert(likes) != 0) {
                                result.status = true;
                                result.data = "success";
                                response = new ResponseEntity<>(result, HttpStatus.OK);
                        }else{
                                result.status = false;
                                result.data = "Duplicated";
                                response = new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE); 
                        }
                } catch (Exception e) {
                        System.out.println("Fail to insert likes");
                        response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }

                return response;
        }

        @DeleteMapping("/likes/")
        @ApiOperation(value = "좋아요 취소")
        public Object likesDelete(int postNo, int userNo) {

                ResponseEntity response = null;
                try {
                        BasicResponse result = new BasicResponse();
                        int res = likesService.likesDelete(postNo, userNo);

                        if (res != 0) {
                                result.status = true;
                                result.data = "success";
                                response = new ResponseEntity<>(result, HttpStatus.OK);
                        }
                } catch (Exception e) {
                        System.out.println("Fail to delete likes");
                        response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
                return response;
        }

        @GetMapping("/likes/status")
        @ApiOperation(value = "좋아요 상태조회")
        public Object likesStatus(int postNo, int userNo) {
                int res = likesService.likesStatus(postNo, userNo);

                ResponseEntity response = null;
                try {
                        BasicResponse result = new BasicResponse();

                        if (res != 0) {
                                result.status = true;
                                result.data = "success";
                                result.object = 1;
                                response = new ResponseEntity<>(result, HttpStatus.OK);
                        }else{
                                result.status = true;
                                result.data = "success";
                                result.object = 0;
                                response = new ResponseEntity<>(result, HttpStatus.OK);
                        }
                } catch (Exception e) {
                        System.out.println("Fail to load likesStatus");
                        response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
                return response;
        }

}