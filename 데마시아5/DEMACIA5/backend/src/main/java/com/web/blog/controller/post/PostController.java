package com.web.blog.controller.post;

import java.sql.SQLException;
import java.util.List;

import com.web.blog.model.dto.post.Post;
import com.web.blog.model.dto.post.PostDetail;
import com.web.blog.model.service.post.LikesService;
import com.web.blog.model.service.post.PostService;
import com.web.blog.model.service.user.MileageService;
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
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    LikesService likesService;
    @Autowired
    MileageService mileageService;

    @PostMapping("/post/")
    @ApiOperation(value = "게시물 등록")
    public Object postInsert(@RequestBody Post post) {

        ResponseEntity response = null;
        try {
            System.out.println(post.toString());
            final BasicResponse result = new BasicResponse();
            if (postService.postInsert(post) != 0) {
                int mile = post.getMileage();
                int userNo = post.getUserNo();
                mileageService.postMileage(userNo, mile);
                result.status = true;
                result.data = "success";
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("Fail to Insert post");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @GetMapping("/post/search")
    @ApiOperation(value = "게시물 검색")
    public Object postSearch(String searchby, String orderby, String searchText, int limit, int offset, String tabOption)
            throws SQLException {

        List<PostDetail> postList;

        if (searchby.equals("title")) {
            postList = postService.postSearchByTitle(orderby, searchText, limit, offset, tabOption);
        } else if (searchby.equals("userNickname")) {
            postList = postService.postSearchByUser(orderby, searchText, limit, offset, tabOption);
        } else if (searchby.equals("option")) {
            postList = postService.postSearchByOption(orderby, searchText, limit, offset, tabOption);
        } else {
            postList = postService.postSearch(orderby, limit, offset, tabOption);
        }

        for (int i = 0; i < postList.size(); i++) {
            postList.get(i).setTotalLikes(likesService.likesCount(postList.get(i).getPostNo()));
        }

        ResponseEntity response = null;
        try {
            BasicResponse result = new BasicResponse();
            if (postList.size() != 0) {
                result.status = true;
                result.data = "success";
                result.object = postList;
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("Fail to print post list");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @GetMapping("/post/detail")
    @ApiOperation(value = "게시물 상세조회")
    public Object postDetail(int postNo) {

        PostDetail postdetail = new PostDetail();
        ResponseEntity response = null;
        try {
            BasicResponse result = new BasicResponse();
            postdetail = postService.postDetail(postNo);

            if (postdetail != null) {
                result.status = true;
                result.data = "success";
                result.object = postdetail;
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("Fail to postdetail");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/post/")
    @ApiOperation(value = "게시물 수정")
    public Object postUpdate(@RequestBody Post post) {
        System.out.println(post.toString());
        int res = postService.postUpdate(post);

        ResponseEntity response = null;
        try {
            BasicResponse result = new BasicResponse();

            if (res != 0) {
                result.status = true;
                result.data = "success";
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("Fail to postUpdate");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @DeleteMapping("post/")
    @ApiOperation(value = "게시물 삭제")
    public Object postDelete(int postNo) {

        ResponseEntity response = null;
        try {
            BasicResponse result = new BasicResponse();
            int res = postService.postDelete(postNo);

            if (res != 0) {
                result.status = true;
                result.data = "success";
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("Fail to postDelete");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

}