package com.web.blog.model.dto.post;

public class Likes{
    private int likeNo;
    private int postNo;
    private int userNo;

    public Likes() {
    }

    public Likes(int likeNo, int postNo, int userNo) {
        this.likeNo = likeNo;
        this.postNo = postNo;
        this.userNo = userNo;
    }

    public int getLikeNo() {
        return likeNo;
    }

    public void setLikeNo(int likeNo) {
        this.likeNo = likeNo;
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

    @Override
    public String toString() {
        return "Likes [likeNo=" + likeNo + ", postNo=" + postNo + ", userNo=" + userNo + "]";
    }
    
}