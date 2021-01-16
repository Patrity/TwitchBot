-- --------------------------------------------------------
-- Host:                         192.168.1.45
-- Server version:               8.0.22 - MySQL Community Server - GPL
-- Server OS:                    Linux
-- HeidiSQL Version:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for twitch
DROP DATABASE IF EXISTS `twitch`;
CREATE DATABASE IF NOT EXISTS `twitch` /*!40100 DEFAULT CHARACTER SET latin1 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `twitch`;

-- Dumping structure for table twitch.guilds
DROP TABLE IF EXISTS `guilds`;
CREATE TABLE IF NOT EXISTS `guilds` (
  `id` int NOT NULL AUTO_INCREMENT,
  `guild_id` varchar(50) NOT NULL,
  `channel_id` varchar(50) NOT NULL,
  `streamer_role_id` varchar(50) NOT NULL,
  `role_promotions` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table twitch.logs
DROP TABLE IF EXISTS `logs`;
CREATE TABLE IF NOT EXISTS `logs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `response_code` int NOT NULL DEFAULT '0',
  `endpoint` varchar(50) NOT NULL,
  `timestamp` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table twitch.streamers
DROP TABLE IF EXISTS `streamers`;
CREATE TABLE IF NOT EXISTS `streamers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `discord_id` varchar(50) NOT NULL,
  `twitch_id` varchar(50) NOT NULL,
  `guild_id` varchar(50) NOT NULL,
  `twitch_name` varchar(50) NOT NULL,
  `enabled` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
