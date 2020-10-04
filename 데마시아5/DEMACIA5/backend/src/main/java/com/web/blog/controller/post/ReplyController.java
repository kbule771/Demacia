package com.web.blog.controller.post;

import java.util.List;

import com.web.blog.model.dto.post.Reply;
import com.web.blog.model.dto.post.ReplyUser;
import com.web.blog.model.service.post.ReplyService;
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
public class ReplyController {

        @Autowired
        ReplyService replyService;

        @GetMapping("/reply/list")
        @ApiOperation(value = "옵션별 댓글 조회")
        public Object replyList(int postNo, int replyOpt) {

                // List<Reply> replyList = replyService.replyList(postNo, replyOpt);
                List<ReplyUser> replyList = replyService.replyUserList(postNo, replyOpt);
                ResponseEntity response = null;

                try {
                        BasicResponse result = new BasicResponse();
                        if (replyList.size() != 0) {
                                result.status = true;
                                result.data = "success";
                                result.object = replyList;
                                response = new ResponseEntity<>(result, HttpStatus.OK);
                        }
                } catch (Exception e) {
                        System.out.println("Fail to get optionn replyList");
                        response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }

                return response;
        }

        @GetMapping("/reply/ListAll")
        @ApiOperation(value = "전체 댓글 조회")
        public Object replyAllList(int postNo) {

                // List<Reply> replyList = replyService.replyAllList(postNo);
                List<ReplyUser> replyList = replyService.replyUserAllList(postNo);
                ResponseEntity response = null;

                try {
                        BasicResponse result = new BasicResponse();
                        if (replyList.size() != 0) {
                                result.status = true;
                                result.data = "success";
                                result.object = replyList;
                                response = new ResponseEntity<>(result, HttpStatus.OK);
                        }
                } catch (Exception e) {
                        System.out.println("Fail to get all replyList");
                        response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }

                return response;
        }

        // 댓글 작성
        @PostMapping("/reply/")
        @ApiOperation(value = "댓글 작성")
        public Object replyInsert(@RequestBody Reply reply) {

                ResponseEntity response = null;
                // System.out.println("reply : "+reply.toString());
                try {
                        final BasicResponse result = new BasicResponse();
                        if (replyService.replyInsert(reply) != 0) {
                                result.status = true;
                                result.data = "success";
                                response = new ResponseEntity<>(result, HttpStatus.OK);
                        }
                } catch (Exception e) {
                        System.out.println("Fail to insert reply");
                        response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }

                return response;
        }

        @PutMapping("/reply/")
        @ApiOperation(value = "댓글 수정")
        public Object replyUpdate(@RequestBody Reply reply) {
                // System.out.println(reply.toString());
                int res = replyService.replyUpdate(reply);

                ResponseEntity response = null;
                try {
                        BasicResponse result = new BasicResponse();

                        if (res != 0) {
                                result.status = true;
                                result.data = "success";
                                response = new ResponseEntity<>(result, HttpStatus.OK);
                        }
                } catch (Exception e) {
                        System.out.println("Fail to update rely");
                        response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
                return response;
        }

        @DeleteMapping("/reply/")
        @ApiOperation(value = "댓글 삭제")
        public Object replyDelete(int replyNo) {

                ResponseEntity response = null;
                try {
                        BasicResponse result = new BasicResponse();
                        int res = replyService.replyDelete(replyNo);

                        if (res != 0) {
                                result.status = true;
                                result.data = "success";
                                response = new ResponseEntity<>(result, HttpStatus.OK);
                        }
                } catch (Exception e) {
                        System.out.println("Fail to delete reply");
                        response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
                return response;
        }

}