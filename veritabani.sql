CREATE DATABASE  IF NOT EXISTS `kultur_merkezi_otomasyonu` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `kultur_merkezi_otomasyonu`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: kultur_merkezi_otomasyonu
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `biletler`
--

DROP TABLE IF EXISTS `biletler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `biletler` (
  `bilet_id` int NOT NULL AUTO_INCREMENT,
  `etkinlik_id` int DEFAULT NULL,
  `musteri_id` int DEFAULT NULL,
  `koltuk_id` int DEFAULT NULL,
  `tarih` date DEFAULT NULL,
  `salon_id` int DEFAULT NULL,
  `fiyat` int DEFAULT NULL,
  `kasaNo` int DEFAULT NULL,
  `musteriTel` varchar(15) DEFAULT NULL,
  `seans` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bilet_id`),
  KEY `etkinlik_id` (`etkinlik_id`),
  KEY `musteri_id` (`musteri_id`),
  KEY `fk_salon_id` (`salon_id`),
  KEY `fk_kasaNo` (`kasaNo`),
  KEY `fk_koltuk_id` (`koltuk_id`),
  KEY `fk_musteriTel_idx` (`musteriTel`),
  CONSTRAINT `biletler_ibfk_1` FOREIGN KEY (`etkinlik_id`) REFERENCES `etkinlikler` (`etkinlik_id`),
  CONSTRAINT `biletler_ibfk_2` FOREIGN KEY (`musteri_id`) REFERENCES `musteriler` (`musteri_id`),
  CONSTRAINT `fk_kasaNo` FOREIGN KEY (`kasaNo`) REFERENCES `kasiyerler` (`kasiyer_kasaNo`),
  CONSTRAINT `fk_koltuk_id` FOREIGN KEY (`koltuk_id`) REFERENCES `koltuklar` (`koltuk_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_musteri_tel` FOREIGN KEY (`musteriTel`) REFERENCES `musteriler` (`telefon`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_salon_id` FOREIGN KEY (`salon_id`) REFERENCES `salonlar` (`salon_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biletler`
--

LOCK TABLES `biletler` WRITE;
/*!40000 ALTER TABLE `biletler` DISABLE KEYS */;
INSERT INTO `biletler` VALUES (57,1,55,109,'2025-01-14',1,200,4,'12345678800','14:00-16:00'),(58,3,56,135,'2025-01-17',3,500,2,'05326559898','14:00-16:00'),(60,18,58,111,'2025-11-17',2,200,3,'02556554487','14:00-16:00'),(61,3,59,135,'2025-01-17',3,500,4,'02556998877','16:00-18:00'),(62,4,60,130,'2025-01-18',4,700,4,'05668440000','20:00-22:00'),(63,18,61,109,'2025-11-17',2,200,2,'05556667788','12:00-14:00'),(64,4,62,116,'2025-01-18',4,700,5,'04445556677','16:00-18:00'),(65,27,63,135,'2025-11-02',2,225,3,'05662225544','14:00-16:00'),(66,27,64,109,'2025-11-02',2,225,3,'02556559988','12:00-14:00'),(67,28,65,136,'2025-04-18',5,250,3,'05968779565','16:00-18:00'),(68,28,66,115,'2025-04-18',5,250,2,'05369775567','14:00-16:00'),(69,27,67,116,'2025-11-02',2,225,4,'05369885565','12:00-14:00'),(70,22,68,116,'2025-05-25',4,450,1,'05362554477','16:00-18:00'),(71,3,69,137,'2025-01-17',3,500,4,'05369866789','12:00-14:00'),(72,18,70,120,'2025-11-17',2,200,8,'05322554565','12:00-14:00'),(73,28,71,129,'2025-04-18',5,250,7,'05368559636','18:00-20:00'),(74,4,72,131,'2025-01-18',4,700,1,'05374475767','16:00-18:00'),(75,23,73,130,'2025-07-16',5,280,5,'05362554758','14:00-16:00'),(76,14,74,135,'2025-02-02',2,350,1,'05369866768','12:00-14:00'),(77,27,75,118,'2025-11-03',2,250,2,'05369865898','20:00-22:00'),(78,3,76,130,'2025-01-17',3,500,8,'05362445898','12:00-14:00');
/*!40000 ALTER TABLE `biletler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etkinlikler`
--

DROP TABLE IF EXISTS `etkinlikler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `etkinlikler` (
  `etkinlik_id` int NOT NULL AUTO_INCREMENT,
  `etkinlik_adi` varchar(100) NOT NULL,
  `etkinlik_turu` varchar(100) DEFAULT NULL,
  `etkinlik_tarihi` date NOT NULL,
  `salon_id` int DEFAULT NULL,
  `etkinlik_fiyati` int DEFAULT NULL,
  PRIMARY KEY (`etkinlik_id`),
  KEY `salon_id` (`salon_id`),
  CONSTRAINT `etkinlikler_ibfk_1` FOREIGN KEY (`salon_id`) REFERENCES `salonlar` (`salon_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etkinlikler`
--

LOCK TABLES `etkinlikler` WRITE;
/*!40000 ALTER TABLE `etkinlikler` DISABLE KEYS */;
INSERT INTO `etkinlikler` VALUES (1,'Delibal','Sinema','2025-01-14',1,200),(2,'Deadpool','Sinema','2025-01-15',1,200),(3,'Melike Şahin','Müzikal','2025-01-17',3,500),(4,'Cem Adrian','Müzikal','2025-01-18',4,700),(14,'Titanik','Sinema','2025-02-02',2,350),(15,'Hababam Sınıfı','Sinema','2025-11-05',3,250),(18,'Romeo ve Juliet','Tiyatro','2025-11-17',2,200),(20,'Hamlet','Tiyatro','2025-05-14',4,280),(22,'Sertap Erener','Müzikal','2025-05-25',4,450),(23,'Romantik Komedi','Sinema','2025-07-16',5,280),(27,'Kral Oidipus','Tiyatro','2025-11-03',1,250),(28,'Karagöz ve Hacivat','Tiyatro','2025-04-18',4,250),(30,'Sagopa Kajmer','Müzikal','2025-01-14',2,700);
/*!40000 ALTER TABLE `etkinlikler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kasiyerler`
--

DROP TABLE IF EXISTS `kasiyerler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kasiyerler` (
  `kasiyer_id` int NOT NULL AUTO_INCREMENT,
  `kasiyer_eposta` varchar(100) DEFAULT NULL,
  `kasiyer_sifre` varchar(100) DEFAULT NULL,
  `kasiyer_maas` int DEFAULT NULL,
  `kasiyer_kasaNo` int DEFAULT NULL,
  `kasiyer_ad` varchar(100) DEFAULT NULL,
  `kasiyer_soyad` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`kasiyer_id`),
  UNIQUE KEY `kasiyer_kasaNo` (`kasiyer_kasaNo`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kasiyerler`
--

LOCK TABLES `kasiyerler` WRITE;
/*!40000 ALTER TABLE `kasiyerler` DISABLE KEYS */;
INSERT INTO `kasiyerler` VALUES (1,'ilknur@example.com','123456',25000,1,'İlknur','Duman'),(2,'melike@example.com','123456',30000,2,'Melike','Duman'),(3,'eren@example.com','123456',30000,3,'Eren','Tekin'),(4,'ırmak@example.com','123456',27000,4,'Irmak','Onay'),(5,'duygu@example.com','123456',20000,5,'Duygu','Demir'),(6,'hayrunnisa@example.com','123456',25000,7,'Hayrunnisa','Gündüz'),(7,'emre@example.com','123456',35000,8,'Emre','Tekin');
/*!40000 ALTER TABLE `kasiyerler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `koltuklar`
--

DROP TABLE IF EXISTS `koltuklar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `koltuklar` (
  `koltuk_id` int NOT NULL AUTO_INCREMENT,
  `koltuk_no` int NOT NULL,
  `salon_id` int DEFAULT NULL,
  `etkinlik_id` int DEFAULT NULL,
  `bilet_durumu` varchar(10) DEFAULT NULL,
  `bilet_id` int DEFAULT NULL,
  PRIMARY KEY (`koltuk_id`),
  KEY `salon_id` (`salon_id`),
  KEY `etkinlik_id` (`etkinlik_id`),
  KEY `fk_bilet_id` (`bilet_id`),
  CONSTRAINT `fk_bilet_id` FOREIGN KEY (`bilet_id`) REFERENCES `biletler` (`bilet_id`) ON DELETE CASCADE,
  CONSTRAINT `koltuklar_ibfk_1` FOREIGN KEY (`salon_id`) REFERENCES `salonlar` (`salon_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `koltuklar_ibfk_2` FOREIGN KEY (`etkinlik_id`) REFERENCES `etkinlikler` (`etkinlik_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=488 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `koltuklar`
--

LOCK TABLES `koltuklar` WRITE;
/*!40000 ALTER TABLE `koltuklar` DISABLE KEYS */;
INSERT INTO `koltuklar` VALUES (108,11,3,3,'BOŞ',NULL),(109,12,3,3,'BOŞ',NULL),(110,13,3,3,'BOŞ',NULL),(111,14,3,3,'BOŞ',NULL),(112,15,3,3,'BOŞ',NULL),(113,16,3,3,'BOŞ',NULL),(114,17,3,3,'DOLU',NULL),(115,18,3,3,'BOŞ',NULL),(116,19,3,3,'BOŞ',NULL),(117,20,3,3,'BOŞ',NULL),(118,21,3,3,'BOŞ',NULL),(119,22,3,3,'BOŞ',NULL),(120,23,3,3,'BOŞ',NULL),(121,24,3,3,'BOŞ',NULL),(122,25,3,3,'BOŞ',NULL),(123,26,3,3,'BOŞ',NULL),(124,27,3,3,'BOŞ',NULL),(125,28,3,3,'BOŞ',NULL),(126,29,3,3,'BOŞ',NULL),(127,30,3,3,'BOŞ',NULL),(128,1,3,15,'BOŞ',NULL),(129,2,3,15,'BOŞ',NULL),(130,3,3,15,'BOŞ',NULL),(131,4,3,15,'BOŞ',NULL),(132,5,3,15,'BOŞ',NULL),(133,6,3,15,'BOŞ',NULL),(134,7,3,15,'BOŞ',NULL),(135,8,3,15,'DOLU',NULL),(136,9,3,15,'DOLU',NULL),(137,10,3,15,'BOŞ',NULL),(138,11,3,15,'BOŞ',NULL),(139,12,3,15,'BOŞ',NULL),(140,13,3,15,'BOŞ',NULL),(141,14,3,15,'BOŞ',NULL),(142,15,3,15,'BOŞ',NULL),(143,16,3,15,'BOŞ',NULL),(144,17,3,15,'BOŞ',NULL),(145,18,3,15,'BOŞ',NULL),(146,19,3,15,'BOŞ',NULL),(147,20,3,15,'BOŞ',NULL),(148,21,3,15,'BOŞ',NULL),(149,22,3,15,'BOŞ',NULL),(150,23,3,15,'BOŞ',NULL),(151,24,3,15,'BOŞ',NULL),(152,25,3,15,'BOŞ',NULL),(153,26,3,15,'BOŞ',NULL),(154,27,3,15,'BOŞ',NULL),(155,28,3,15,'BOŞ',NULL),(156,29,3,15,'BOŞ',NULL),(157,30,3,15,'BOŞ',NULL),(158,1,1,23,'BOŞ',NULL),(159,2,1,23,'BOŞ',NULL),(160,3,1,23,'BOŞ',NULL),(161,4,1,23,'BOŞ',NULL),(162,5,1,23,'BOŞ',NULL),(163,6,1,23,'BOŞ',NULL),(164,7,1,23,'BOŞ',NULL),(165,8,1,23,'BOŞ',NULL),(166,9,1,23,'BOŞ',NULL),(167,10,1,23,'BOŞ',NULL),(168,11,1,23,'BOŞ',NULL),(169,12,1,23,'BOŞ',NULL),(170,13,1,23,'DOLU',NULL),(171,14,1,23,'BOŞ',NULL),(172,15,1,23,'BOŞ',NULL),(173,16,1,23,'DOLU',NULL),(174,17,1,23,'BOŞ',NULL),(175,18,1,23,'BOŞ',NULL),(176,19,1,23,'BOŞ',NULL),(177,20,1,23,'BOŞ',NULL),(178,21,1,23,'BOŞ',NULL),(179,22,1,23,'BOŞ',NULL),(180,23,1,23,'BOŞ',NULL),(181,24,1,23,'BOŞ',NULL),(182,25,1,23,'BOŞ',NULL),(183,26,1,23,'BOŞ',NULL),(184,27,1,23,'BOŞ',NULL),(185,28,1,23,'BOŞ',NULL),(186,29,1,23,'BOŞ',NULL),(187,30,1,23,'BOŞ',NULL),(188,1,4,22,'BOŞ',NULL),(189,2,4,22,'BOŞ',NULL),(190,3,4,22,'BOŞ',NULL),(191,4,4,22,'BOŞ',NULL),(192,5,4,22,'BOŞ',NULL),(193,6,4,22,'BOŞ',NULL),(194,7,4,22,'BOŞ',NULL),(195,8,4,22,'DOLU',NULL),(196,9,4,22,'BOŞ',NULL),(197,10,4,22,'BOŞ',NULL),(198,11,4,22,'DOLU',NULL),(199,12,4,22,'BOŞ',NULL),(200,13,4,22,'BOŞ',NULL),(201,14,4,22,'BOŞ',NULL),(202,15,4,22,'BOŞ',NULL),(203,16,4,22,'BOŞ',NULL),(204,17,4,22,'BOŞ',NULL),(205,18,4,22,'BOŞ',NULL),(206,19,4,22,'DOLU',NULL),(207,20,4,22,'BOŞ',NULL),(208,21,4,22,'BOŞ',NULL),(209,22,4,22,'BOŞ',NULL),(210,23,4,22,'BOŞ',NULL),(211,24,4,22,'BOŞ',NULL),(212,25,4,22,'BOŞ',NULL),(213,26,4,22,'BOŞ',NULL),(214,27,4,22,'BOŞ',NULL),(215,28,4,22,'BOŞ',NULL),(216,29,4,22,'BOŞ',NULL),(217,30,4,22,'BOŞ',NULL),(218,1,2,14,'BOŞ',NULL),(219,2,2,14,'BOŞ',NULL),(220,3,2,14,'BOŞ',NULL),(221,4,2,14,'BOŞ',NULL),(222,5,2,14,'BOŞ',NULL),(223,6,2,14,'BOŞ',NULL),(224,7,2,14,'BOŞ',NULL),(225,8,2,14,'DOLU',NULL),(226,9,2,14,'BOŞ',NULL),(227,10,2,14,'BOŞ',NULL),(228,11,2,14,'BOŞ',NULL),(229,12,2,14,'BOŞ',NULL),(230,13,2,14,'BOŞ',NULL),(231,14,2,14,'BOŞ',NULL),(232,15,2,14,'BOŞ',NULL),(233,16,2,14,'BOŞ',NULL),(234,17,2,14,'BOŞ',NULL),(235,18,2,14,'BOŞ',NULL),(236,19,2,14,'BOŞ',NULL),(237,20,2,14,'BOŞ',NULL),(238,21,2,14,'BOŞ',NULL),(239,22,2,14,'BOŞ',NULL),(240,23,2,14,'BOŞ',NULL),(241,24,2,14,'BOŞ',NULL),(242,25,2,14,'BOŞ',NULL),(243,26,2,14,'BOŞ',NULL),(244,27,2,14,'BOŞ',NULL),(245,28,2,14,'BOŞ',NULL),(246,29,2,14,'BOŞ',NULL),(247,30,2,14,'BOŞ',NULL),(278,1,1,1,'BOŞ',NULL),(279,2,1,1,'BOŞ',NULL),(280,3,1,1,'BOŞ',NULL),(281,4,1,1,'BOŞ',NULL),(282,5,1,1,'BOŞ',NULL),(283,6,1,1,'BOŞ',NULL),(284,7,1,1,'BOŞ',NULL),(285,8,1,1,'BOŞ',NULL),(286,9,1,1,'BOŞ',NULL),(287,10,1,1,'BOŞ',NULL),(288,11,1,1,'BOŞ',NULL),(289,12,1,1,'DOLU',NULL),(290,13,1,1,'BOŞ',NULL),(291,14,1,1,'BOŞ',NULL),(292,15,1,1,'BOŞ',NULL),(293,16,1,1,'BOŞ',NULL),(294,17,1,1,'BOŞ',NULL),(295,18,1,1,'BOŞ',NULL),(296,19,1,1,'BOŞ',NULL),(297,20,1,1,'BOŞ',NULL),(298,21,1,1,'BOŞ',NULL),(299,22,1,1,'BOŞ',NULL),(300,23,1,1,'BOŞ',NULL),(301,24,1,1,'BOŞ',NULL),(302,25,1,1,'BOŞ',NULL),(303,26,1,1,'BOŞ',NULL),(304,27,1,1,'BOŞ',NULL),(305,28,1,1,'BOŞ',NULL),(306,29,1,1,'BOŞ',NULL),(307,30,1,1,'BOŞ',NULL),(308,1,2,18,'BOŞ',NULL),(309,2,2,18,'BOŞ',NULL),(310,3,2,18,'BOŞ',NULL),(311,4,2,18,'BOŞ',NULL),(312,5,2,18,'BOŞ',NULL),(313,6,2,18,'BOŞ',NULL),(314,7,2,18,'BOŞ',NULL),(315,8,2,18,'BOŞ',NULL),(316,9,2,18,'BOŞ',NULL),(317,10,2,18,'BOŞ',NULL),(318,11,2,18,'BOŞ',NULL),(319,12,2,18,'DOLU',NULL),(320,13,2,18,'BOŞ',NULL),(321,14,2,18,'DOLU',NULL),(322,15,2,18,'BOŞ',NULL),(323,16,2,18,'BOŞ',NULL),(324,17,2,18,'BOŞ',NULL),(325,18,2,18,'BOŞ',NULL),(326,19,2,18,'BOŞ',NULL),(327,20,2,18,'BOŞ',NULL),(328,21,2,18,'BOŞ',NULL),(329,22,2,18,'BOŞ',NULL),(330,23,2,18,'DOLU',NULL),(331,24,2,18,'BOŞ',NULL),(332,25,2,18,'BOŞ',NULL),(333,26,2,18,'BOŞ',NULL),(334,27,2,18,'BOŞ',NULL),(335,28,2,18,'BOŞ',NULL),(336,29,2,18,'BOŞ',NULL),(337,30,2,18,'BOŞ',NULL),(338,1,4,4,'BOŞ',NULL),(339,2,4,4,'BOŞ',NULL),(340,3,4,4,'DOLU',NULL),(341,4,4,4,'DOLU',NULL),(342,5,4,4,'BOŞ',NULL),(343,6,4,4,'BOŞ',NULL),(344,7,4,4,'BOŞ',NULL),(345,8,4,4,'BOŞ',NULL),(346,9,4,4,'BOŞ',NULL),(347,10,4,4,'BOŞ',NULL),(348,11,4,4,'BOŞ',NULL),(349,12,4,4,'DOLU',NULL),(350,13,4,4,'BOŞ',NULL),(351,14,4,4,'BOŞ',NULL),(352,15,4,4,'BOŞ',NULL),(353,16,4,4,'BOŞ',NULL),(354,17,4,4,'BOŞ',NULL),(355,18,4,4,'BOŞ',NULL),(356,19,4,4,'DOLU',NULL),(357,20,4,4,'BOŞ',NULL),(358,21,4,4,'BOŞ',NULL),(359,22,4,4,'BOŞ',NULL),(360,23,4,4,'BOŞ',NULL),(361,24,4,4,'BOŞ',NULL),(362,25,4,4,'BOŞ',NULL),(363,26,4,4,'BOŞ',NULL),(364,27,4,4,'BOŞ',NULL),(365,28,4,4,'BOŞ',NULL),(366,29,4,4,'BOŞ',NULL),(367,30,4,4,'BOŞ',NULL),(368,1,2,27,'BOŞ',NULL),(369,2,2,27,'BOŞ',NULL),(370,3,2,27,'BOŞ',NULL),(371,4,2,27,'BOŞ',NULL),(372,5,2,27,'BOŞ',NULL),(373,6,2,27,'BOŞ',NULL),(374,7,2,27,'BOŞ',NULL),(375,8,2,27,'DOLU',NULL),(376,9,2,27,'BOŞ',NULL),(377,10,2,27,'BOŞ',NULL),(378,11,2,27,'BOŞ',NULL),(379,12,2,27,'DOLU',NULL),(380,13,2,27,'BOŞ',NULL),(381,14,2,27,'BOŞ',NULL),(382,15,2,27,'BOŞ',NULL),(383,16,2,27,'BOŞ',NULL),(384,17,2,27,'BOŞ',NULL),(385,18,2,27,'BOŞ',NULL),(386,19,2,27,'DOLU',NULL),(387,20,2,27,'BOŞ',NULL),(388,21,2,27,'DOLU',NULL),(389,22,2,27,'BOŞ',NULL),(390,23,2,27,'BOŞ',NULL),(391,24,2,27,'BOŞ',NULL),(392,25,2,27,'BOŞ',NULL),(393,26,2,27,'BOŞ',NULL),(394,27,2,27,'BOŞ',NULL),(395,28,2,27,'BOŞ',NULL),(396,29,2,27,'BOŞ',NULL),(397,30,2,27,'BOŞ',NULL),(398,1,5,28,'BOŞ',NULL),(399,2,5,28,'DOLU',NULL),(400,3,5,28,'BOŞ',NULL),(401,4,5,28,'BOŞ',NULL),(402,5,5,28,'BOŞ',NULL),(403,6,5,28,'BOŞ',NULL),(404,7,5,28,'BOŞ',NULL),(405,8,5,28,'BOŞ',NULL),(406,9,5,28,'DOLU',NULL),(407,10,5,28,'BOŞ',NULL),(408,11,5,28,'BOŞ',NULL),(409,12,5,28,'DOLU',NULL),(410,13,5,28,'BOŞ',NULL),(411,14,5,28,'BOŞ',NULL),(412,15,5,28,'BOŞ',NULL),(413,16,5,28,'BOŞ',NULL),(414,17,5,28,'BOŞ',NULL),(415,18,5,28,'DOLU',NULL),(416,19,5,28,'BOŞ',NULL),(417,20,5,28,'BOŞ',NULL),(418,21,5,28,'BOŞ',NULL),(419,22,5,28,'BOŞ',NULL),(420,23,5,28,'BOŞ',NULL),(421,24,5,28,'BOŞ',NULL),(422,25,5,28,'BOŞ',NULL),(423,26,5,28,'BOŞ',NULL),(424,27,5,28,'BOŞ',NULL),(425,28,5,28,'BOŞ',NULL),(426,29,5,28,'BOŞ',NULL),(427,30,5,28,'BOŞ',NULL),(428,1,4,20,'BOŞ',NULL),(429,2,4,20,'BOŞ',NULL),(430,3,4,20,'BOŞ',NULL),(431,4,4,20,'BOŞ',NULL),(432,5,4,20,'BOŞ',NULL),(433,6,4,20,'BOŞ',NULL),(434,7,4,20,'BOŞ',NULL),(435,8,4,20,'BOŞ',NULL),(436,9,4,20,'BOŞ',NULL),(437,10,4,20,'BOŞ',NULL),(438,11,4,20,'BOŞ',NULL),(439,12,4,20,'BOŞ',NULL),(440,13,4,20,'BOŞ',NULL),(441,14,4,20,'BOŞ',NULL),(442,15,4,20,'BOŞ',NULL),(443,16,4,20,'BOŞ',NULL),(444,17,4,20,'BOŞ',NULL),(445,18,4,20,'BOŞ',NULL),(446,19,4,20,'BOŞ',NULL),(447,20,4,20,'BOŞ',NULL),(448,21,4,20,'BOŞ',NULL),(449,22,4,20,'BOŞ',NULL),(450,23,4,20,'BOŞ',NULL),(451,24,4,20,'BOŞ',NULL),(452,25,4,20,'BOŞ',NULL),(453,26,4,20,'BOŞ',NULL),(454,27,4,20,'BOŞ',NULL),(455,28,4,20,'BOŞ',NULL),(456,29,4,20,'BOŞ',NULL),(457,30,4,20,'BOŞ',NULL),(458,1,5,23,'BOŞ',NULL),(459,2,5,23,'BOŞ',NULL),(460,3,5,23,'DOLU',NULL),(461,4,5,23,'BOŞ',NULL),(462,5,5,23,'BOŞ',NULL),(463,6,5,23,'BOŞ',NULL),(464,7,5,23,'BOŞ',NULL),(465,8,5,23,'BOŞ',NULL),(466,9,5,23,'BOŞ',NULL),(467,10,5,23,'BOŞ',NULL),(468,11,5,23,'BOŞ',NULL),(469,12,5,23,'BOŞ',NULL),(470,13,5,23,'BOŞ',NULL),(471,14,5,23,'BOŞ',NULL),(472,15,5,23,'DOLU',NULL),(473,16,5,23,'BOŞ',NULL),(474,17,5,23,'BOŞ',NULL),(475,18,5,23,'BOŞ',NULL),(476,19,5,23,'BOŞ',NULL),(477,20,5,23,'BOŞ',NULL),(478,21,5,23,'BOŞ',NULL),(479,22,5,23,'BOŞ',NULL),(480,23,5,23,'BOŞ',NULL),(481,24,5,23,'BOŞ',NULL),(482,25,5,23,'BOŞ',NULL),(483,26,5,23,'BOŞ',NULL),(484,27,5,23,'BOŞ',NULL),(485,28,5,23,'BOŞ',NULL),(486,29,5,23,'BOŞ',NULL),(487,30,5,23,'BOŞ',NULL);
/*!40000 ALTER TABLE `koltuklar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mudurler`
--

DROP TABLE IF EXISTS `mudurler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mudurler` (
  `mudur_id` int NOT NULL AUTO_INCREMENT,
  `mudur_eposta` varchar(100) DEFAULT NULL,
  `mudur_sifre` varchar(100) DEFAULT NULL,
  `mudur_maas` int DEFAULT NULL,
  `mudur_departman` enum('Sinema','Tiyatro','Müzikal','Genel Müdür') DEFAULT NULL,
  `mudur_ad` varchar(100) DEFAULT NULL,
  `mudur_soyad` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`mudur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mudurler`
--

LOCK TABLES `mudurler` WRITE;
/*!40000 ALTER TABLE `mudurler` DISABLE KEYS */;
INSERT INTO `mudurler` VALUES (1,'ilknur@gmail.com','123456',60000,'Genel Müdür','İlknur','Duman'),(2,'eren@gmail.com','123456',50000,'Müzikal','Eren','Tekin'),(3,'melike@gmail.com','123456',40000,'Tiyatro','Melike','Duman'),(5,'hayrunnisa@gmail.com','123456',40000,'Müzikal','Hayrunnisa','Gündüz'),(9,'emre@gmail.com',NULL,42000,'Sinema','Emre','Tekin'),(10,'Muhammet@gmail.com',NULL,45000,'Tiyatro','Muhammet','Koçak'),(11,'ırmak@gmail.com',NULL,42000,'Müzikal','Irmak','Onay');
/*!40000 ALTER TABLE `mudurler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musteriler`
--

DROP TABLE IF EXISTS `musteriler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `musteriler` (
  `musteri_id` int NOT NULL AUTO_INCREMENT,
  `ad` varchar(100) DEFAULT NULL,
  `soyad` varchar(100) DEFAULT NULL,
  `telefon` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`musteri_id`),
  KEY `telefon_idx` (`telefon`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musteriler`
--

LOCK TABLES `musteriler` WRITE;
/*!40000 ALTER TABLE `musteriler` DISABLE KEYS */;
INSERT INTO `musteriler` VALUES (1,'İlknur','Duman','05392500532'),(3,'Eren','Tekin','05392500535'),(4,'Irmak','Onay','05392500050'),(5,'Hayrunnisa','Gündüz','05392500500'),(6,'Duygu','Demir','05352500053'),(7,'Adnan','Kavlak','05303200320'),(8,'Irmak','Onay','05303003300'),(9,'Melike','Duman','05302002200'),(10,'Yiğit','Duman','05324445566'),(11,'Muhammet','Koçak','05322002211'),(14,'Eren','Tekin','05392500532'),(15,'Ben','Başkabirisiyim','05392500532'),(16,'Ben','Başkabirisiyim','05392500532'),(17,'Ben','Başkabirisiyim','05392500532'),(18,'Ben','Başkabirisiyim','05392500532'),(19,'Ben','Başkabirisiyim','05392500532'),(20,'Eren','Tekin','05392500532'),(21,'eren','teh','05392500532'),(22,'eren','teh','05392500532'),(23,'Eren','Tekin','05392500532'),(24,'Eren','Tekin','05392500532'),(25,'Eren','Tekin','05392500532'),(26,'Eren','Tekin','05392500532'),(27,'İlknu','Duman','05392500532'),(28,'İlknu','Duman','05392500532'),(29,'İlknur','Eren','05392500532'),(30,'İlknur','Duman','05392500533'),(31,'İlknur','Duman','05392500534'),(32,'İlknur','Dmn','0220200'),(33,'İlknur','Dmn','02202002211'),(34,'ilknur','duman','05392500220'),(35,'ilknur','duman','05392500222'),(36,'Melike','duman','05322212233'),(37,'İlknur','Dmn','11112223344'),(38,'Eren','Tekin','05362500232'),(39,'Eren','Tekin','05362500233'),(40,'Emre','Tekin','02566559988'),(41,'Emre','Tekin','02566559989'),(42,'Melike','dmn','02221113335'),(43,'Melike','dmn','02221113337'),(44,'Melike','dmn','02221113338'),(45,'Irmak','onay','02223338899'),(46,'Irmak','onay','02223338897'),(47,'İlknur','Dmn','02223338896'),(48,'İlknur','Dmn','02223338811'),(49,'Duygu','Demirr','02556665577'),(50,'İlknur','Eren','05352500114'),(51,'Eren','Tkn','05362544115'),(52,'Eren','Tekinn','05326665544'),(53,'Melike','Dumans','05326559988'),(54,'İlknur','Makbule','02336669988'),(55,'İlknur','Duman','12345678800'),(56,'Eren','Tekin','05326559898'),(57,'Melike','Duma','02556554489'),(58,'Melike','Duman','02556554487'),(59,'Melike','Duman','02556998877'),(60,'Eren','Tekin','05668440000'),(61,'Fatma','Özlem','05556667788'),(62,'Mükerrem','Zeynep','04445556677'),(63,'Tutkum','Adıgüzel','05662225544'),(64,'Ayşe','Aydın','02556559988'),(65,'Ayşe','Aydın','05968779565'),(66,'Celile','Vişne','05369775567'),(67,'Hayrunnisa','Gündüz','05369885565'),(68,'Özlem','Ekin','05362554477'),(69,'Mehmet','Kılıçarslan','05369866789'),(70,'Mehmet','Kılıçarslan','05322554565'),(71,'İsmail','Kurtuluş','05368559636'),(72,'Melike','Özlem','05374475767'),(73,'Doğukan','Mert','05362554758'),(74,'İlknur','Duman','05369866768'),(75,'Ayşe','Nur','05369865898'),(76,'Elif','Öner','05362445898');
/*!40000 ALTER TABLE `musteriler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salonlar`
--

DROP TABLE IF EXISTS `salonlar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salonlar` (
  `salon_id` int NOT NULL AUTO_INCREMENT,
  `salon_adi` varchar(50) NOT NULL,
  `kapasite` int DEFAULT '30',
  PRIMARY KEY (`salon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salonlar`
--

LOCK TABLES `salonlar` WRITE;
/*!40000 ALTER TABLE `salonlar` DISABLE KEYS */;
INSERT INTO `salonlar` VALUES (1,'Salon 1',30),(2,'Salon 2',30),(3,'Salon 3',30),(4,'Salon 4',30),(5,'Salon 5',30);
/*!40000 ALTER TABLE `salonlar` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-22  0:57:41
