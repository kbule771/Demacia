package com.web.blog.model.dto.lol;

public class LeagueInfo {
    private String leagueId;
    private String summonerId;
    private String summonerName;
    private String queueType;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;

    private String imgURL;
    private String profileImgURL;
    private String tierImgURL;

    public LeagueInfo() {
    }

    public LeagueInfo(String leagueId, String summonerId, String summonerName, String queueType, String tier,
            String rank, int leaguePoints, int wins, int losses, String imgURL, String profileImgURL, String tierImgURL) {
        this.leagueId = leagueId;
        this.summonerId = summonerId;
        this.summonerName = summonerName;
        this.queueType = queueType;
        this.tier = tier;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
        this.imgURL = imgURL;
        this.profileImgURL = profileImgURL;
        this.tierImgURL = tierImgURL;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getProfileImgURL() {
        return profileImgURL;
    }

    public void setProfileImgURL(String profileImgURL) {
        this.profileImgURL = profileImgURL;
    }

    public String getTierImgURL() {
        return tierImgURL;
    }

    public void setTierImgURL(String tierImgURL) {
        this.tierImgURL = tierImgURL;
    }

    @Override
    public String toString() {
        return "LeagueInfo [imgURL=" + imgURL + ", leagueId=" + leagueId + ", leaguePoints=" + leaguePoints
                + ", losses=" + losses + ", profileImgURL=" + profileImgURL + ", queueType=" + queueType + ", rank="
                + rank + ", summonerId=" + summonerId + ", summonerName=" + summonerName + ", tier=" + tier
                + ", tierImgURL=" + tierImgURL + ", wins=" + wins + "]";
    }

}