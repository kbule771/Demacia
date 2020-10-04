package com.web.blog.model.service.user;

public interface MileageService {

        // 사용자 마일리지 조회
        public int mileageSearch(int userNo);

        // 사용자 마일리지 순위 조회
        public int mileageRank(int userNo);

        // 사용자 마일리지 순위 퍼센트 조회
        public Double mileageRankPer(int userNo);

        // 마일리지 수정
        public int mileageUpdate(int postNo);

        // 포스트 작성 시 마일리지 차감
        public int postMileage(int userNo, int mileage);

        // post의 mileage 조회
        public int searchMileage(int postNo);

        // User의 mileage 조회
        public int searchUserMileage(int userNo);

        // user의 solrank 변경
        public int updateSolRank(int userNo);

}