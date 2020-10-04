package com.web.blog.controller.user;

import com.web.blog.model.service.user.MileageService;
import com.web.blog.util.BasicResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
public class MileageController {

    @Autowired
    MileageService mileageService;

    @GetMapping("/user/mileage")
    @ApiOperation(value = "마일리지 조회")
    public Object mileageSearch(int userNo) {
        ResponseEntity response = null;
        try {

            final BasicResponse result = new BasicResponse();
            int res = mileageService.mileageSearch(userNo);
            result.status = true;
            result.data = "success";
            result.object = res;
            response = new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Fail to search mileage");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/user/mileagerank")
    @ApiOperation(value = "마일리지 순위 조회")
    public Object mileageRank(int userNo) {
        ResponseEntity response = null;
        try {
            BasicResponse result = new BasicResponse();
            int res = mileageService.mileageRank(userNo);
            result.status = true;
            result.data = "success";
            result.object = res;
            response = new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Fail to search mileage rnak");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/user/mileagerankper")
    @ApiOperation(value = "마일리지 순위 퍼센트 조회")
    public Object mileageRankPer(int userNo) {
        ResponseEntity response = null;
        try {
            BasicResponse result = new BasicResponse();
            Double res = mileageService.mileageRankPer(userNo);
            result.status = true;
            result.data = "success";
            result.object = res;
            response = new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Fail to search mileage rankper");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/update/mileage/")
    @ApiOperation(value = "마일리지 적용")
    public Object mileageUpdate(int postNo) {
        System.out.println(postNo);
        ResponseEntity response = null;
        try {
            int res = mileageService.mileageUpdate(postNo);
            BasicResponse result = new BasicResponse();

            if (res != 0) {
                result.status = true;
                result.data = "success";

                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("Fail to update mileage");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/update/solrank")
    @ApiOperation(value = "유저의 solrank 수정")
    public Object updateSolRank(int userNo) {
        ResponseEntity response = null;
        try {
            int res = mileageService.updateSolRank(userNo);
            BasicResponse result = new BasicResponse();

            if (res != 0) {
                result.status = true;
                result.data = "success";
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("Fail to update solrank");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

}
