-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: issue
-- ------------------------------------------------------
-- Server version	5.7.31-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue` (
  `issue_id` int(20) NOT NULL AUTO_INCREMENT,
  `issue_name` varchar(80) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `create_man` varchar(20) DEFAULT NULL,
  `level` int(20) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `beta` varchar(20) DEFAULT NULL,
  `user_id` varchar(30) NOT NULL,
  `update_date` date DEFAULT NULL,
  `update_man` varchar(20) DEFAULT NULL,
  `step` varchar(2000) DEFAULT NULL,
  `solution` varchar(2000) DEFAULT NULL,
  `plan_date` date DEFAULT NULL,
  `final_date` date DEFAULT NULL,
  PRIMARY KEY (`issue_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (1,'bug','关闭',NULL,'张三',1,NULL,NULL,'1',NULL,'五六七',NULL,NULL,NULL,'2020-11-19'),(2,'bug','关闭',NULL,'张三',1,NULL,NULL,'1',NULL,'五六七',NULL,NULL,NULL,'2020-11-19'),(3,'11111',NULL,NULL,'1',0,'1111','11111','1',NULL,'11111','1111','',NULL,NULL),(4,'312',NULL,NULL,'1',0,'1321','1321','1',NULL,'123213','213213','',NULL,NULL),(5,'1123',NULL,NULL,'1',0,'12313213','231312321','1',NULL,'23132131','23132131','',NULL,NULL),(6,'123',NULL,NULL,'1',0,'12313','2313213','1',NULL,'2143214','2131231','',NULL,NULL),(7,'123',NULL,NULL,'1',0,'313213','231321','1',NULL,'1232131','aaa','',NULL,NULL),(8,'123',NULL,NULL,'1',0,'weqeq','qweqe','1',NULL,'231','wqeqeq','',NULL,NULL),(9,'313',NULL,NULL,'1',0,'sdadad','sdad','1',NULL,'2313213','sdada','',NULL,NULL),(10,'675',NULL,NULL,'1',0,'123213','231313','1',NULL,'13123','1232131','',NULL,NULL),(11,'897','待修改','2020-11-18','1',0,'2131','2313','1','2020-11-18','21321','2321231','','2020-11-18','2020-11-18'),(12,'222',NULL,NULL,'1',0,'双方都','111','1',NULL,'车祸','4444444444444444444','','2020-11-09',NULL),(13,'222',NULL,'2020-11-22','1',0,'双方都','111','1',NULL,'车祸','4444444444444444444','','2020-11-09',NULL),(14,'22',NULL,'2020-11-22','1',0,'双方都','111','1',NULL,'车祸','4444444444444444444','','2020-11-09',NULL),(15,'333',NULL,'2020-11-22','1',0,'33333','3333','1',NULL,'333','323232','','2020-11-11',NULL),(16,'33',NULL,'2020-11-22','1',0,'33333','3333','1',NULL,'333','323232','','2020-11-11',NULL),(17,'而温热',NULL,'2020-11-22','1',2,'ASDAD','asd','1',NULL,'asds','SADADADSAD','','2020-11-17',NULL),(18,'而温','待解决','2020-11-22','1',2,'ASDAD','asd','1',NULL,'asds','SADADADSAD','','2020-11-17',NULL),(19,'而温21','待解决','2020-11-22','1',2,'ASDAD','asd','1',NULL,'asds','SADADADSAD','','2020-11-17',NULL),(20,'2313','待解决','2020-11-22','1',2,'1232','21313','1',NULL,'21323','12312313','','2020-11-17',NULL),(21,'231','待解决','2020-11-22','1',2,'23131','231312','1',NULL,'2313','23132132','','2020-11-12',NULL),(22,'231','待解决','2020-11-22','1',2,'23131','231312','1',NULL,'2313','23132132','','2020-11-12',NULL);
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` varchar(30) NOT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `identity` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1','1','1','1','2010-10-10','1','注销'),('12321','231313','231313','3232133','2020-11-22','普通用户','激活'),('2','2','2','2','2010-10-10','普通用户','激活'),('213213','32132131','sSW','额外额外企鹅@com','2020-11-22','普通用户','激活'),('231231','31231321','231233','231233','2020-11-22','普通用户','激活'),('3','4','3','3','2010-10-10','经理','激活'),('3424','weqeq','wqee','weqeq','2020-11-22','普通用户','激活'),('4','5','4','4','2010-10-10','经理','激活'),('6','张三','12345','123456@163.com','2020-11-18','经理','激活'),('666','蓝敲门','@12345678','735723058@qq.com','2020-11-22','普通用户','激活'),('7','3','3','3','2020-11-18','超级Admin','3'),('t4567','8888','8889','1234567@qq.com','2020-11-22','经理','注销');
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

-- Dump completed on 2020-11-23  0:01:15
