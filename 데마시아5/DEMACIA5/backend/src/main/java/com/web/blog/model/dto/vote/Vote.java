package com.web.blog.model.dto.vote;

public class Vote {
    private int voteNo;
    private int postNo;
    private int userNo;
    private int value;
    
    private int userGameRankNum;
    private int userSolRankNum;

    public Vote() {
    }

    public Vote(int voteNo, int postNo, int userNo, int value, int userGameRankNum, int userSolRankNum) {
        this.voteNo = voteNo;
        this.postNo = postNo;
        this.userNo = userNo;
        this.value = value;
        this.userGameRankNum = userGameRankNum;
        this.userSolRankNum = userSolRankNum;
    }

    public int getVoteNo() {
        return voteNo;
    }

    public void setVoteNo(int voteNo) {
        this.voteNo = voteNo;
    }

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getUserGameRankNum() {
        return userGameRankNum;
    }

    public void setUserGameRankNum(int userGameRankNum) {
        this.userGameRankNum = userGameRankNum;
    }

    public int getUserSolRankNum() {
        return userSolRankNum;
    }

    public void setUserSolRankNum(int userSolRankNum) {
        this.userSolRankNum = userSolRankNum;
    }

    @Override
    public String toString() {
        return "Vote [postNo=" + postNo + ", userGameRankNum=" + userGameRankNum + ", userNo=" + userNo
                + ", userSolRankNum=" + userSolRankNum + ", value=" + value + ", voteNo=" + voteNo
                + "]";
    }
    
}