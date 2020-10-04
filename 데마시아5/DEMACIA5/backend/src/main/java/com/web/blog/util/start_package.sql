-- --------------------------------------------------------
-- 호스트:                          i3a602.p.ssafy.io
-- 서버 버전:                        10.5.4-MariaDB-1:10.5.4+maria~focal - mariadb.org binary distribution
-- 서버 OS:                        debian-linux-gnu
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- ssafysk 데이터베이스 구조 내보내기
DROP DATABASE IF EXISTS `ssafysk`;
CREATE DATABASE IF NOT EXISTS `ssafysk` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `ssafysk`;

-- 테이블 ssafysk.likes 구조 내보내기
DROP TABLE IF EXISTS `likes`;
CREATE TABLE IF NOT EXISTS `likes` (
  `likeno` int(11) NOT NULL AUTO_INCREMENT,
  `postno` int(11) NOT NULL,
  `userno` int(11) NOT NULL,
  PRIMARY KEY (`likeno`) USING BTREE,
  KEY `FK_vote_post` (`postno`) USING BTREE,
  KEY `FK_vote_user` (`userno`) USING BTREE,
  CONSTRAINT `like_ibfk_1` FOREIGN KEY (`postno`) REFERENCES `post` (`postno`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `like_ibfk_2` FOREIGN KEY (`userno`) REFERENCES `user` (`userno`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=218 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 ssafysk.OauthUser 구조 내보내기
DROP TABLE IF EXISTS `OauthUser`;
CREATE TABLE IF NOT EXISTS `OauthUser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oauthId` varchar(256) NOT NULL,
  `name` varchar(256) NOT NULL,
  `providerName` varchar(256) NOT NULL,
  `accessToken` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 ssafysk.post 구조 내보내기
DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS `post` (
  `postno` int(11) NOT NULL AUTO_INCREMENT,
  `userno` int(11) NOT NULL,
  `postdate` datetime NOT NULL DEFAULT current_timestamp(),
  `view` int(10) unsigned zerofill DEFAULT 0000000000,
  `title` varchar(256) NOT NULL,
  `content` longtext NOT NULL,
  `video` varchar(256) DEFAULT NULL,
  `thumbnail` varchar(256) DEFAULT NULL,
  `option1` varchar(50) NOT NULL,
  `option2` varchar(50) NOT NULL,
  `deadline` datetime NOT NULL,
  `mileage` int(11) unsigned NOT NULL DEFAULT 50,
  `isFinished` int(11) DEFAULT 0,
  PRIMARY KEY (`postno`),
  KEY `FK_table_user` (`userno`),
  CONSTRAINT `FK_table_user` FOREIGN KEY (`userno`) REFERENCES `user` (`userno`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 ssafysk.reply 구조 내보내기
DROP TABLE IF EXISTS `reply`;
CREATE TABLE IF NOT EXISTS `reply` (
  `replyno` int(11) NOT NULL AUTO_INCREMENT,
  `postno` int(11) NOT NULL,
  `userno` int(11) NOT NULL,
  `content` longtext NOT NULL,
  `replydate` datetime DEFAULT current_timestamp(),
  `replyOpt` int(11) NOT NULL,
  PRIMARY KEY (`replyno`) USING BTREE,
  KEY `FK_vote_post` (`postno`) USING BTREE,
  KEY `FK_vote_user` (`userno`) USING BTREE,
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`postno`) REFERENCES `post` (`postno`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `reply_ibfk_2` FOREIGN KEY (`userno`) REFERENCES `user` (`userno`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 ssafysk.user 구조 내보내기
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userno` int(11) NOT NULL AUTO_INCREMENT,
  `useremail` varchar(128) NOT NULL,
  `userpw` varchar(128) DEFAULT NULL,
  `usernickname` varchar(128) NOT NULL,
  `userimage` varchar(256) DEFAULT NULL,
  `usercreatedate` datetime DEFAULT current_timestamp(),
  `userSolRankNum` int(11) DEFAULT 0,
  `userGameRankNum` int(11) DEFAULT 1,
  `userSolTierStr` varchar(128) DEFAULT NULL,
  `userSolRankStr` varchar(128) DEFAULT NULL,
  `userGameTierStr` varchar(128) DEFAULT NULL,
  `userGameRankStr` varchar(128) DEFAULT NULL,
  `summonerName` varchar(255) DEFAULT NULL,
  `userMileage` int(11) DEFAULT 0,
  `providerName` varchar(128) DEFAULT NULL,
  `accesstoken` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`userno`) USING BTREE,
  UNIQUE KEY `user_idx_unique_email` (`useremail`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 ssafysk.vote 구조 내보내기
DROP TABLE IF EXISTS `vote`;
CREATE TABLE IF NOT EXISTS `vote` (
  `voteno` int(11) NOT NULL AUTO_INCREMENT,
  `postno` int(11) NOT NULL,
  `userno` int(11) NOT NULL,
  `usergameranknum` int(11) NOT NULL,
  `usersolranknum` int(11) NOT NULL,
  `value` int(10) unsigned zerofill DEFAULT 0000000000,
  PRIMARY KEY (`voteno`),
  KEY `FK_vote_post` (`postno`),
  KEY `userno` (`userno`),
  CONSTRAINT `FK_vote_post` FOREIGN KEY (`postno`) REFERENCES `post` (`postno`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_vote_user` FOREIGN KEY (`userno`) REFERENCES `user` (`userno`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
