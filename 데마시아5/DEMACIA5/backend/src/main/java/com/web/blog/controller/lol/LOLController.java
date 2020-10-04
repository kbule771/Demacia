
package com.web.blog.controller.lol;

import com.web.blog.model.dao.lol.LOLDao;
import com.web.blog.model.dto.lol.LeagueInfo;
import com.web.blog.model.dto.lol.Summoner;
import com.web.blog.model.service.lol.LoLService;
import com.web.blog.util.BasicResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

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
public class LOLController {

    @Autowired
    LoLService lolService;
    @Autowired
    LOLDao lolDao;

    @GetMapping("/rank")
    @ApiOperation(value = "소환사 검색")
    public Object rankAuth(String summonerName, int userNo) {
        ResponseEntity response = null;
        Summoner summoner = lolService.lolSummoner(summonerName);
        LeagueInfo leagueInfo = lolService.lolLeagueInfo(summoner);
        System.out.println(summoner.toString());
        System.out.println(leagueInfo.toString());

        BasicResponse result = new BasicResponse();
        try {
            if (leagueInfo != null) {
                System.out.println("acc update : " + lolDao.updateAccountId(userNo, summoner.getAccountId())
                        + "\nrank update :" + lolService.lolUpdateRank(userNo, leagueInfo));
                result.status = true;
                result.data = "success";
                result.object = leagueInfo;
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            result.status = false;
            result.data = "소환사명이 존재하지 않습니다.";
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }

        return response;

    }

    @DeleteMapping("/rank")
    @ApiOperation(value = "gamerank 삭제")
    public Object rankAuth(int userNo) {
        ResponseEntity response = null;
        try {
            BasicResponse result = new BasicResponse();
            int res = lolService.deleteGameRank(userNo);
            if (res != 0) {
                result.status = true;
                result.data = "success";
                response = new ResponseEntity<>(result, HttpStatus.OK);

            }
        } catch (Exception e) {
            System.out.println("Fail to delete game rank");
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
