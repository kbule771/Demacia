package com.web.blog.util;

public class TransRank {

    public int loltransferRankToNum(String tier, String rank) {
        int transNum = 1;
        switch (tier.toLowerCase()) {
            case "provisional":
                break;

            case "bronze":
                transNum = 2;
                if (rank.equals("I")) {
                    transNum += 4;
                    break;
                } else if (rank.equals("II")) {
                    transNum += 3;
                } else if (rank.equals("III")) {
                    transNum += 2;
                } else if (rank.equals("IV")) {
                    transNum += 1;
                }
                break;

            case "silver":
                transNum = 7;
                if (rank.equals("I")) {
                    transNum += 4;
                } else if (rank.equals("II")) {
                    transNum += 3;
                } else if (rank.equals("III")) {
                    transNum += 2;
                } else if (rank.equals("IV")) {
                    transNum += 1;
                }
                break;

            case "gold":
                transNum = 12;
                if (rank.equals("I")) {
                    transNum += 4;
                } else if (rank.equals("II")) {
                    transNum += 3;
                } else if (rank.equals("III")) {
                    transNum += 2;
                } else if (rank.equals("IV")) {
                    transNum += 1;
                }
                break;

            case "platinum":
                transNum = 17;
                if (rank.equals("I")) {
                    transNum += 4;
                } else if (rank.equals("II")) {
                    transNum += 3;
                } else if (rank.equals("III")) {
                    transNum += 2;
                } else if (rank.equals("IV")) {
                    transNum += 1;
                }
                break;

            case "diamond":
                transNum = 22;
                if (rank.equals("I")) {
                    transNum += 4;
                } else if (rank.equals("II")) {
                    transNum += 3;
                } else if (rank.equals("III")) {
                    transNum += 2;
                } else if (rank.equals("IV")) {
                    transNum += 1;
                }
                break;

            case "master":
                transNum = 30;
                break;

            case "grandmaster":
                transNum = 35;
                break;

            case "challenger":
                transNum = 40;
                break;
            default:
                break;
        }
        return transNum;
    }

    public String[] loltransferRankToStr(int rank) {
        String transTier = "provisional";
        String transRank = "";

        if (rank == 1) {

        } else if (rank >= 2 && rank < 6) {
            transTier = "bronze";

            switch (rank) {
                case 2:
                    transRank = "I";
                    break;
                case 3:
                    transRank = "II";
                    break;
                case 4:
                    transRank = "III";
                    break;
                case 5:
                    transRank = "IV";
                    break;

                default:
                    break;
            }

        } else if (rank >= 6 && rank < 10) {
            transTier = "silver";
            switch (rank) {
                case 6:
                    transRank = "I";
                    break;
                case 7:
                    transRank = "II";
                    break;
                case 8:
                    transRank = "III";
                    break;
                case 9:
                    transRank = "IV";
                    break;

                default:
                    break;
            }
        } else if (rank >= 10 && rank < 14) {
            transTier = "gold";
            switch (rank) {
                case 10:
                    transRank = "I";
                    break;
                case 11:
                    transRank = "II";
                    break;
                case 12:
                    transRank = "III";
                    break;
                case 13:
                    transRank = "IV";
                    break;

                default:
                    break;
            }
        } else if (rank >= 14 && rank < 18) {
            transTier = "platinum";
            switch (rank) {
                case 14:
                    transRank = "I";
                    break;
                case 15:
                    transRank = "II";
                    break;
                case 16:
                    transRank = "III";
                    break;
                case 17:
                    transRank = "IV";
                    break;

                default:
                    break;
            }
        } else if (rank >= 18 && rank < 22) {
            transTier = "diamond";
            switch (rank) {
                case 18:
                    transRank = "I";
                    break;
                case 19:
                    transRank = "II";
                    break;
                case 20:
                    transRank = "III";
                    break;
                case 21:
                    transRank = "IV";
                    break;

                default:
                    break;
            }
        } else if (rank == 25) {
            transTier = "master";
        } else if (rank == 30) {
            transTier = "grandmaster";
        } else if (rank == 40) {
            transTier = "challenger";
        } else {
            transTier = "Not regist Rank";
        }

        String[] str = { transTier, transRank };
        return str;
    }
}