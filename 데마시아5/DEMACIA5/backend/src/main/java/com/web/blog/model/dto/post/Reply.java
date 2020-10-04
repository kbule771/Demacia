package com.web.blog.model.dto.post;

import java.time.LocalDateTime;

public class Reply {
    private int replyNo;
    private int postNo;
    private int userNo;
    private String content;
    private LocalDateTime replyDate;
    private int replyOpt;

    public Reply() {
    }

    public Reply(int replyNo, int postNo, int userNo, String content, LocalDateTime replyDate, int replyOpt) {
        this.replyNo = replyNo;
        this.postNo = postNo;
        this.userNo = userNo;
        this.content = content;
        this.replyDate = replyDate;
        this.replyOpt = replyOpt;
    }

    public int getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(int replyNo) {
        this.replyNo = replyNo;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(LocalDateTime replyDate) {
        this.replyDate = replyDate;
    }

    public int getReplyOpt() {
        return replyOpt;
    }

    public void setReplyOpt(int replyOpt) {
        this.replyOpt = replyOpt;
    }

    @Override
    public String toString() {
        return "Reply [content=" + content + ", postNo=" + postNo + ", replyDate=" + replyDate + ", replyNo=" + replyNo
                + ", replyOpt=" + replyOpt + ", userNo=" + userNo + "]";
    }

    
}