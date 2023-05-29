-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (arm64)
--
-- Host: 127.0.0.1    Database: StaffManege
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('admin1','admin1'),('admin2','admin2'),('admin3','admin3'),('admin4','admin4');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `name` varchar(20) NOT NULL,
  `manager_user_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `manager_user_id` (`manager_user_id`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`manager_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('生产部','00001'),('研发部','00002'),('营销部','00002'),('行政部','00002'),('技术部','00003'),('财务部','00012');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `title` varchar(1000) DEFAULT NULL,
  `article` varchar(2000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES ('本周工作完成情况','本周营销部工作圆满完成！'),('待遇问题','工资能不能提高一些啊！'),('人力问题','行政部人手不够了，请增加行政部员工'),('123','123'),('123','123');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `sex` tinyint(1) NOT NULL,
  `user_type` int NOT NULL,
  `department_name` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  `late_number` int DEFAULT '0',
  `bunk_number` int DEFAULT '0',
  `off_number` int DEFAULT '0',
  `over_number` int DEFAULT '0',
  `evec_number` int DEFAULT '0',
  `base_salary` float DEFAULT '0',
  PRIMARY KEY (`user_id`),
  KEY `department_name` (`department_name`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`department_name`) REFERENCES `department` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('00001','00001','苏智宸',1,2,NULL,'47880287001','456279@yahoo.com','青岛市黄岛区',0,0,0,0,0,11500),('00002','00002','宋晓博',1,2,NULL,'36777750703','345734@hotmail.com','济南市市中区',0,0,0,0,0,10600),('00003','00003','顾钰轩',0,2,NULL,'18144084351','ihg64310@gmail.com','青岛市市南区',0,0,0,0,0,9500),('00004','00004','杨伟祺',1,1,'行政部','864279617227','ijkd45@gmail.com','青岛市市南区',0,0,0,0,0,6500),('00005','00005','吕子轩',0,1,'营销部','93328498040','abgd440@yahoo.com','聊城市东昌府区',12,0,0,0,0,8000),('00006','00006','沈荣轩',1,1,'行政部','148612421879','fsdr3354@yahoo.com','聊城市冠县区',0,0,0,0,0,7500),('00007','00007','于鑫磊',1,1,'营销部','17684739878','17684739878@qq.com','青岛市城阳区',0,0,0,0,0,7800),('00008','00008','龚雨泽',0,1,'财务部','18878473857','18878473857@qq.com','青岛市即墨区',0,0,0,0,0,8900),('00009','00009','姜鹤轩',1,1,'研发部','17868476398','17868476398@qq.com','济南市市中区',0,0,0,0,0,9800),('00010','00010','武天宇',1,1,'营销部','13387697687','13387697687@163.com','济南市天桥区',0,0,0,0,0,7800),('00011','00011','邹潇然',0,2,NULL,'18903859385','18903859385@163.com','济南市历下区',0,0,0,0,0,12000),('00012','00012','周懿轩',1,2,NULL,'18958483958','18958483958@qq.com','济南市天桥区',0,0,0,0,0,12050),('00013','00013','莫浩然',0,1,'营销部','15685769780','15685769780@qq.com','青岛市黄岛区',0,0,0,0,0,9800),('00014','00014','张立轩',1,1,'行政部','18758687960','18758687960@163.com','青岛市市北区',0,0,0,0,0,8800),('00015','00015','郝昊天',1,1,'研发部','18974958499','18974958499@163.com','青岛市崂山区',0,0,0,0,0,9000);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-29 13:58:29
