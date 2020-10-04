package com.web.blog.model.dto.post;

import java.time.LocalDateTime;

public class ReplyUser {
    private int replyNo;
    private int postNo;
    private String content;
    private LocalDateTime replyDate;
    private int replyOpt;
    private int userNo;
    private String userNickname;
    private String userImage;
    private String userGameRankStr;
    private String userGameTierStr;
    private String userSolRankStr;
    private String userSolTierStr;

    public ReplyUser() {
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

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserGameRankStr() {
        return userGameRankStr;
    }

    public void setUserGameRankStr(String userGameRankStr) {
        this.userGameRankStr = userGameRankStr;
    }

    public String getUserGameTierStr() {
        return userGameTierStr;
    }

    public void setUserGameTierStr(String userGameTierStr) {
        this.userGameTierStr = userGameTierStr;
    }

    public String getUserSolRankStr() {
        return userSolRankStr;
    }

    public void setUserSolRankStr(String userSolRankStr) {
        this.userSolRankStr = userSolRankStr;
    }

    public String getUserSolTierStr() {
        return userSolTierStr;
    }

    public void setUserSolTierStr(String userSolTierStr) {
        this.userSolTierStr = userSolTierStr;
    }

    @Override
    public String toString() {
        return "ReplyUser [content=" + content + ", postNo=" + postNo + ", replyDate=" + replyDate + ", replyNo="
                + replyNo + ", replyOpt=" + replyOpt + ", userGameRankStr=" + userGameRankStr + ", userGameTierStr="
                + userGameTierStr + ", userImage=" + userImage + ", userNickname=" + userNickname + ", userNo=" + userNo
                + ", userSolRankStr=" + userSolRankStr + ", userSolTierStr=" + userSolTierStr + "]";
    }

    public ReplyUser(int replyNo, int postNo, String content, LocalDateTime replyDate, int replyOpt, int userNo,
            String userNickname, String userImage, String userGameRankStr, String userGameTierStr,
            String userSolRankStr, String userSolTierStr) {
        this.replyNo = replyNo;
        this.postNo = postNo;
        this.content = content;
        this.replyDate = replyDate;
        this.replyOpt = replyOpt;
        this.userNo = userNo;
        this.userNickname = userNickname;
        this.userImage = userImage;
        this.userGameRankStr = userGameRankStr;
        this.userGameTierStr = userGameTierStr;
        this.userSolRankStr = userSolRankStr;
        this.userSolTierStr = userSolTierStr;
    }

}