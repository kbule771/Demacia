package com.web.blog.model.dto.user;

import java.time.LocalDateTime;

public class User {

    private int userNo;
    private String userEmail;
    private String userPw;
    private String userNickname;
    private String userImage;
    private LocalDateTime userCreateDate;
    private int userSolRankNum;
    private int userGameRankNum;
    private String summonerName;
    private String userSolTierStr;
    private String userSolRankStr;
    private String userGameTierStr;
    private String userGameRankStr;
    private int userMileage;
    private String accessToken;
    private String providerName;
    private String mbti;
    private String accountid;

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
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

    public LocalDateTime getUserCreateDate() {
        return userCreateDate;
    }

    public void setUserCreateDate(LocalDateTime userCreateDate) {
        this.userCreateDate = userCreateDate;
    }

    public int getUserSolRankNum() {
        return userSolRankNum;
    }

    public void setUserSolRankNum(int userSolRankNum) {
        this.userSolRankNum = userSolRankNum;
    }

    public int getUserGameRankNum() {
        return userGameRankNum;
    }

    public void setUserGameRankNum(int userGameRankNum) {
        this.userGameRankNum = userGameRankNum;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getUserSolTierStr() {
        return userSolTierStr;
    }

    public void setUserSolTierStr(String userSolTierStr) {
        this.userSolTierStr = userSolTierStr;
    }

    public String getUserSolRankStr() {
        return userSolRankStr;
    }

    public void setUserSolRankStr(String userSolRankStr) {
        this.userSolRankStr = userSolRankStr;
    }

    public String getUserGameTierStr() {
        return userGameTierStr;
    }

    public void setUserGameTierStr(String userGameTierStr) {
        this.userGameTierStr = userGameTierStr;
    }

    public String getUserGameRankStr() {
        return userGameRankStr;
    }

    public void setUserGameRankStr(String userGameRankStr) {
        this.userGameRankStr = userGameRankStr;
    }

    public int getUserMileage() {
        return userMileage;
    }

    public void setUserMileage(int userMileage) {
        this.userMileage = userMileage;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public User(int userNo, String userEmail, String userPw, String userNickname, String userImage,
            LocalDateTime userCreateDate, int userSolRankNum, int userGameRankNum, String summonerName,
            String userSolTierStr, String userSolRankStr, String userGameTierStr, String userGameRankStr,
            int userMileage, String accessToken, String providerName, String mbti, String accountid) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userNickname = userNickname;
        this.userImage = userImage;
        this.userCreateDate = userCreateDate;
        this.userSolRankNum = userSolRankNum;
        this.userGameRankNum = userGameRankNum;
        this.summonerName = summonerName;
        this.userSolTierStr = userSolTierStr;
        this.userSolRankStr = userSolRankStr;
        this.userGameTierStr = userGameTierStr;
        this.userGameRankStr = userGameRankStr;
        this.userMileage = userMileage;
        this.accessToken = accessToken;
        this.providerName = providerName;
        this.mbti = mbti;
        this.accountid = accountid;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User [accessToken=" + accessToken + ", accountid=" + accountid + ", mbti=" + mbti + ", providerName="
                + providerName + ", summonerName=" + summonerName + ", userCreateDate=" + userCreateDate
                + ", userEmail=" + userEmail + ", userGameRankNum=" + userGameRankNum + ", userGameRankStr="
                + userGameRankStr + ", userGameTierStr=" + userGameTierStr + ", userImage=" + userImage
                + ", userMileage=" + userMileage + ", userNickname=" + userNickname + ", userNo=" + userNo + ", userPw="
                + userPw + ", userSolRankNum=" + userSolRankNum + ", userSolRankStr=" + userSolRankStr
                + ", userSolTierStr=" + userSolTierStr + "]";
    }

}