package com.web.blog.model.dto.user;

public class UserVote {
    private int userNo;
    private int postNo;
    private String postTitle;
    private String voteResult;
    private int point;

    public UserVote() {
    }

    public UserVote(int userNo, int postNo, String postTitle, String voteResult, int point) {
        this.userNo = userNo;
        this.postNo = postNo;
        this.postTitle = postTitle;
        this.voteResult = voteResult;
        this.point = point;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getVoteResult() {
        return voteResult;
    }

    public void setVoteResult(String voteResult) {
        this.voteResult = voteResult;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "UserVote [point=" + point + ", postNo=" + postNo + ", postTitle=" + postTitle + ", userNo=" + userNo
                + ", voteResult=" + voteResult + "]";
    }

}