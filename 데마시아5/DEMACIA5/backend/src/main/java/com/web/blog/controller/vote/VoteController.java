package com.web.blog.controller.vote;

import com.web.blog.model.dto.vote.Vote;
import com.web.blog.model.service.user.UserService;
import com.web.blog.model.service.vote.VoteService;
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
public class VoteController {

    @Autowired
    VoteService voteService;
    UserService UserService;

    @PostMapping("/vote")
    @ApiOperation(value = "투표")
    public Object voteInsert(@RequestBody Vote vote) {
        ResponseEntity response = null;
        try {
            if (voteService.voteStatus(vote.getPostNo(), vote.getUserNo()) == null) {
                BasicResponse result = new BasicResponse();
                int res = voteService.voteInsert(vote);
                if (res != 0) {
                    result.status = true;
                    result.data = "success";
                    response = new ResponseEntity<>(result, HttpStatus.OK);
                } else {
                    result.status = false;
                    result.data = "fail to insert vote";
                    response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                System.out.println("Fail to insert vote");
                response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("Fail to insert vote");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/vote")
    @ApiOperation(value = "투표 여부")
    public Object voteStatus(int postNo, int userNo) {
        ResponseEntity response = null;
        try {
            BasicResponse result = new BasicResponse();
            Vote res = voteService.voteStatus(postNo, userNo);

            if (res != null) {
                result.status = true;
                result.data = "success";
                result.object = res;
                response = new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.status = true;
                result.data = "no vote";
                result.object = res;
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("Fail to show vote");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/vote")
    @ApiOperation(value = "투표 수정")
    public Object voteUpdate(int postNo, int userNo, int camp) {
        ResponseEntity response = null;
        try {
            BasicResponse result = new BasicResponse();
            int res = voteService.voteUpdate(postNo, userNo, camp);

            if (res != 0) {
                result.status = true;
                result.data = "success";
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("Fail to update vote");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @DeleteMapping("/vote")
    @ApiOperation(value = "투표 취소")
    public Object voteDelete(int postNo, int userNo) {
        ResponseEntity response = null;
        try {
            BasicResponse result = new BasicResponse();
            int res = voteService.voteDelete(postNo, userNo);

            if (res != 0) {
                result.status = true;
                result.data = "success";
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("Fail to delete vote");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/vote/count")
    @ApiOperation(value = "투표 집계")
    public Object voteCount(int postNo) {
        ResponseEntity response = null;
        try {
            BasicResponse result = new BasicResponse();
            int res1 = voteService.voteCount(postNo, 1);
            System.out.println(res1);
            int res2 = voteService.voteCount(postNo, 2);
            System.out.println(res2);
            int res[] = { res1, res2 };

            System.out.println("res1 : " + res1 + " / res 2: " + res2);

            result.status = true;
            result.data = "success";
            result.object = res;
            response = new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Fail to count vote");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

}