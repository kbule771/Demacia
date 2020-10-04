package com.web.blog.model.service.user;

import java.util.List;

import com.web.blog.model.dao.user.MileageDao;
import com.web.blog.model.dao.user.UserDao;
import com.web.blog.model.service.vote.VoteService;
import com.web.blog.util.TransRank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MileageServiceImpl implements MileageService {

   @Autowired
   private MileageDao dao;

   @Autowired
   private UserDao userDao;

   @Autowired
   private VoteService voteService;

   // 사용자 마일리지 조회
   @Override
   public int mileageSearch(int userNo) {
      return dao.mileageSearch(userNo);
   }

   // 사용자 마일리지 순위 조회
   @Override
   public int mileageRank(int userNo) {
      return dao.mileageRank().indexOf(Integer.valueOf(userNo));
   }

   // 사용자 마일리지 순위 퍼센트 조회
   @Override
   public Double mileageRankPer(int userNo) {
      List<Integer> list = dao.mileageRank();
      int rank = list.indexOf(Integer.valueOf(userNo));
      int total = list.size();
      Double result = (double) rank / (double) total;
      return result;
   }

   // 마일리지 수정
   @Override
   public int mileageUpdate(int postNo) {
      int mileage = dao.searchMileage(postNo);

      // 게시물의 투표 결과 가져오기
      int res1 = voteService.voteCount(postNo, 1);
      int res2 = voteService.voteCount(postNo, 2);
      int isFinished = 0;

      if (res1 > res2) {
         isFinished = 1;
      } else if (res1 < res2) {
         isFinished = 2;
      } else {
         isFinished = 3;
         System.out.println("동표");
      }

      // 투표 결과에 따른 마일리지 적용
      if (isFinished == 1) {
         dao.mileageAdd(postNo, 1, mileage);
         dao.postFinished(postNo, isFinished); // 투표 정산 종료
         return 1;
      } else if (isFinished == 2) {
         dao.mileageAdd(postNo, 2, mileage);
         dao.postFinished(postNo, isFinished); // 투표 정산 종료
         return 1;
      } else if (isFinished == 3) {
         dao.mileageAdd(postNo, 1, mileage);
         dao.mileageAdd(postNo, 2, mileage);
         dao.postFinished(postNo, isFinished); // 투표 정산 종료
         return 1;
      }
      return 0;
   }

   // 글 작성 시 마일리지 차감
   @Override
   public int postMileage(int userNo, int mileage) {
      return dao.postMileage(userNo, mileage);
   }

   // 포스트에 정의된 마일리지 조회
   @Override
   public int searchMileage(int postNo) {
      return dao.searchMileage(postNo);
   }

   // 유저의 마일리지 조회
   @Override
   public int searchUserMileage(int userNo) {
      return dao.searchUserMileage(userNo);
   }

   // 마일리지 값으로 sol rank 변경 (유저당_userNo)
   @Override
   public int updateSolRank(int userNo) {
      int userMile = dao.searchUserMileage(userNo);
      String userSolTierStr = null;
      String userSolRankStr = null;
      if (userMile < 1000) { // bronze
         userSolTierStr = "BRONZE";
         if (userMile < 250) {
            userSolRankStr = "IV";
         } else if (userMile < 500) {
            userSolRankStr = "III";
         } else if (userMile < 750) {
            userSolRankStr = "II";
         } else {
            userSolRankStr = "I";
         }
      } else if (userMile < 3000) { // silver
         userSolTierStr = "SILVER";
         if (userMile < 1500) {
            userSolRankStr = "IV";
         } else if (userMile < 2000) {
            userSolRankStr = "III";
         } else if (userMile < 2500) {
            userSolRankStr = "II";
         } else {
            userSolRankStr = "I";
         }
      } else if (userMile < 10000) { // gold
         userSolTierStr = "GOLD";
         if (userMile < 4750) {
            userSolRankStr = "IV";
         } else if (userMile < 6500) {
            userSolRankStr = "III";
         } else if (userMile < 8250) {
            userSolRankStr = "II";
         } else {
            userSolRankStr = "I";
         }
      } else if (userMile < 20000) { // platinum
         userSolTierStr = "PLATINUM";
         if (userMile < 12500) {
            userSolRankStr = "IV";
         } else if (userMile < 15000) {
            userSolRankStr = "III";
         } else if (userMile < 17500) {
            userSolRankStr = "II";
         } else {
            userSolRankStr = "I";
         }
      } else if (userMile < 40000) { // diamond
         userSolTierStr = "DIAMOND";
         if (userMile < 25000) {
            userSolRankStr = "IV";
         } else if (userMile < 30000) {
            userSolRankStr = "III";
         } else if (userMile < 35000) {
            userSolRankStr = "II";
         } else {
            userSolRankStr = "I";
         }
      } else { // master, grandmaster, challenger
         userSolRankStr = "I";
         if (userMile < 60000) {
            userSolTierStr = "MASTER";
         } else if (userMile < 100000) {
            userSolTierStr = "GRANDMASTER";
         } else {
            userSolTierStr = "CHALLENGER";
         }
      }
      TransRank transRank = new TransRank();

      int userSolRankNum = transRank.loltransferRankToNum(userSolTierStr, userSolRankStr);
      dao.updateSolRankNum(userSolRankNum, userNo);

      return dao.updateSolRank(userSolTierStr, userSolRankStr, userNo);

   }

}