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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (1,'bug1','关闭','2020-11-18','昌黎',1,'缺陷','v1.0','02teacher','2020-11-19','蓝彬峰','见机行事','无','2020-11-21','2020-11-21'),(2,'bug2','关闭','2020-11-18','昌黎',1,'缺陷','v1.0','02teacher','2020-11-19','蓝彬峰','见机行事','无','2020-11-21','2020-11-21'),(3,'bug3','待验证','2020-11-18','昌黎',2,'缺陷','v1.0','02teacher','2020-11-19','蓝彬峰','见机行事','无','2020-11-21','2020-11-21'),(4,'bug4','待修改','2020-11-18','陈泽森',2,'缺陷','v1.0','chenzeshen','2020-11-19','蓝彬峰','见机行事','无','2020-11-21','2020-11-21'),(5,'bug5','关闭','2020-11-18','陈泽森',3,'缺陷','v1.0','chenzeshen','2020-11-19','蓝彬峰','见机行事','无','2020-11-21','2020-11-21'),(6,'bug6','待修改','2020-11-18','陈泽森',3,'缺陷','v1.0','chenzeshen','2020-11-19','蓝彬峰','见机行事','无','2020-11-21','2020-11-21'),(7,'bug7','待修改','2020-11-18','陈泽森',4,'缺陷','v1.0','chenzeshen','2020-11-19','蓝彬峰','见机行事','无','2020-11-21','2020-11-21'),(8,'issue1','待验证','2020-11-18','陈泽森',1,'缺陷','v1.0','chenzeshen','2020-11-19','蓝彬峰','见机行事','无','2020-11-21','2020-11-21'),(9,'issue2','待修改','2020-11-18','陈泽森',1,'缺陷','v1.0','chenzeshen','2020-11-19','蓝彬峰','见机行事','无','2020-11-21','2020-11-21'),(10,'issue3','关闭','2020-11-18','陈泽森',2,'缺陷','v1.0','chenzeshen','2020-11-19','蓝彬峰','见机行事','无','2020-11-21','2020-11-21'),(11,'issue4','关闭','2020-11-18','陈泽森',2,'缺陷','v1.0','chenzeshen','2020-11-19','蓝彬峰','见机行事','无','2020-11-21','2020-11-21'),(12,'issue5','关闭','2020-11-18','陈泽森',3,'缺陷','v1.0','chenzeshen','2020-11-19','蓝彬峰','见机行事','无','2020-11-21','2020-11-21'),(13,'issue6','关闭','2020-11-22','陈泽森',3,'缺陷','v1.0','chenzeshen','2020-11-23','蓝彬峰','见机行事','无','2020-11-25','2020-11-25'),(14,'issue8','待验证','2020-11-22','陈泽森',1,'缺陷','v1.0','chenzeshen','2020-11-23','蓝彬峰','见机行事','无','2020-11-25','2020-11-25'),(15,'issue9','待修改','2020-11-22','陈泽森',1,'缺陷','v1.0','chenzeshen','2020-11-23','蓝彬峰','见机行事','无','2020-11-25','2020-11-25'),(16,'issue10','关闭','2020-11-22','陈泽森',1,'缺陷','v1.0','chenzeshen','2020-11-23','蓝彬峰','见机行事','无','2020-11-25','2020-11-25'),(17,'issue11','关闭','2020-11-22','陈泽森',2,'缺陷','v1.0','chenzeshen','2020-11-23','蓝彬峰','见机行事','无','2020-11-25','2020-11-25'),(18,'issue12','关闭','2020-11-22','陈泽森',2,'缺陷','v1.0','chenzeshen','2020-11-23','蓝彬峰','见机行事','无','2020-11-25','2020-11-25'),(19,'issue13','关闭','2020-11-22','陈泽森',2,'缺陷','v1.0','chenzeshen','2020-11-23','蓝彬峰','见机行事','无','2020-11-25','2020-11-25'),(20,'issue14','关闭','2020-11-22','陈泽森',2,'缺陷','v1.0','chenzeshen','2020-11-23','蓝彬峰','见机行事','无','2020-11-25','2020-11-25'),(21,'issue15','关闭','2020-11-22','陈泽森',2,'缺陷','v1.0','chenzeshen','2020-11-23','蓝彬峰','见机行事','无','2020-11-25','2020-11-25'),(22,'issue16','关闭','2004-02-22','陈泽森',2,'缺陷','v1.0','chenzeshen','2004-02-23','蓝彬峰','见机行事','无','2004-02-25','2004-02-25'),(23,'issue17','待修改','2004-02-22','陈泽森',4,'缺陷','v1.0','chenzeshen','2004-02-23','蓝彬峰','见机行事','无','2004-02-25','2004-02-25'),(24,'issue18','待验证','2004-02-22','陈泽森',1,'缺陷','v1.0','chenzeshen','2004-02-23','蓝彬峰','见机行事','无','2004-02-25','2004-02-25'),(25,'issue19','关闭','2004-02-22','陈泽森',2,'缺陷','v1.0','chenzeshen','2004-02-23','蓝彬峰','见机行事','无','2004-02-25','2004-02-25'),(26,'issue20','待验证','2004-02-22','陈泽森',2,'缺陷','v1.0','chenzeshen','2004-02-23','蓝彬峰','见机行事','无','2004-02-25','2004-02-25'),(27,'issue21','关闭','2004-02-22','陈泽森',2,'缺陷','v1.0','chenzeshen','2004-02-23','蓝晓明','见机行事','无','2004-02-25','2004-02-25'),(28,'issue22','待修改','2004-02-22','陈泽森',2,'缺陷','v1.0','chenzeshen','2004-02-23','蓝晓明','见机行事','无','2004-02-25','2004-02-25'),(29,'issue23','关闭','2004-02-22','陈泽森',2,'缺陷','v1.0','chenzeshen','2004-02-23','蓝晓明','见机行事','无','2004-02-25','2004-02-25'),(30,'issue24','关闭','2020-11-23','陈泽森',2,'缺陷','v1.0','chenzeshen','2020-11-24','蓝晓明','随机应变','无','2020-11-26','2020-11-26'),(31,'issue25','关闭','2020-11-23','陈泽森',2,'缺陷','v1.0','chenzeshen','2020-11-24','劳志斌','随机应变','无','2020-11-26','2020-11-26'),(32,'question1','待修改','2020-11-23','关凌枫',4,'缺陷','v1.0','guanlingfeng','2020-11-24','劳志斌','随机应变','无','2020-11-26','2020-11-26'),(33,'question2','待验证','2020-11-23','张一',3,'缺陷','v1.0','q123','2020-11-24','劳志斌','随机应变','无','2020-11-26','2020-11-26'),(34,'question3','关闭','2020-11-23','张一',2,'缺陷','v1.0','q123','2020-11-24','昊嘉','随机应变','无','2020-11-26','2020-11-26'),(35,'question4','待验证','2020-11-23','鸿哲',1,'缺陷','v1.0','user','2020-11-24','昊嘉','随机应变','无','2020-11-26','2020-11-26'),(36,'question5','待修改','2020-11-23','鸿哲',4,'缺陷','v1.0','user','2020-11-24','李二','随机应变','无','2020-11-26','2020-11-26'),(37,'question6','待修改','2020-11-23','鸿哲',3,'缺陷','v1.0','user','2020-11-24','李二','随机应变','无','2020-11-26','2020-11-26'),(38,'question7','待修改','2020-11-23','鸿哲',2,'缺陷','v1.0','user','2020-11-24','李二','随机应变','无','2020-11-26','2020-11-26'),(39,'question8','关闭','2020-11-23','张三',1,'缺陷','v1.0','w123','2020-11-24','运锋','随机应变','无','2020-11-26','2020-11-26'),(40,'question9','关闭','2020-11-23','张三',4,'缺陷','v1.0','w123','2020-11-24','运锋','随机应变','无','2020-11-26','2020-11-26'),(41,'question10','关闭','2020-11-23','张三',3,'缺陷','v1.0','w123','2020-11-24','运锋','随机应变','无','2020-11-26','2020-11-26'),(42,'question11','关闭','2020-11-23','张三',2,'缺陷','v1.0','w123','2020-11-24','晋鹏','随机应变','无','2020-11-26','2020-11-26'),(43,'question12','关闭','2020-11-23','张三',1,'缺陷','v1.0','w123','2020-11-24','晋鹏','随机应变','无','2020-11-26','2020-11-26'),(44,'question13','关闭','2020-11-23','晋鹏',4,'缺陷','v1.0','tony123','2020-11-24','张三','随机应变','无','2020-11-26','2020-11-26'),(45,'question14','待修改','2020-11-23','晋鹏',3,'缺陷','v1.0','tony123','2020-11-24','张三','随机应变','无','2020-11-26','2020-11-26'),(46,'question15','关闭','2020-11-23','晋鹏',2,'缺陷','v1.0','tony123','2020-11-24','张三','随机应变','无','2020-11-26','2020-11-26');
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
INSERT INTO `user` VALUES ('01teacher','振锐','@qQ123456','2459860471@qq.com','2004-02-22','普通用户','激活'),('02teacher','昌黎','@qQ123456','2459860471@qq.com','2004-02-22','普通用户','激活'),('03teacher','昊苍','@qQ123456','2459860471@qq.com','2004-02-22','普通用户','激活'),('123450q','海超','@qQ123456','735723058@qq.com','2020-11-23','普通用户','激活'),('123456@qq.com','曜灿','@qQ123456','2459860471@qq.com','2014-07-30','经理','注销'),('123456q','依秋','@qQ123456','735723058@qq.com','2020-11-23','普通用户','激活'),('1234@163.com','浩阔','@qQ123456','2459860471@qq.com','2014-07-30','经理','激活'),('admin','鸿祯','@qQ123456','2459860471@qq.com','2020-11-18','超级Admin','激活'),('chenzeshen','陈泽森','@qQ123456','735723058@qq.com','2020-11-22','普通用户','激活'),('dengningdong','邓宁栋','@qQ123456','735723058@qq.com','2010-10-10','超级Admin','注销'),('dengweihao','邓伟豪','@qQ123456','735723058@qq.com','2020-11-18','超级Admin','激活'),('guanlingfeng','关凌枫','@qQ123456','735723058@qq.com','2020-11-22','普通用户','激活'),('lanbinfeng','蓝彬峰','@qQ123456','735723058@qq.com','2020-11-23','普通用户','激活'),('lanxiaoming','蓝晓明','@qQ123456','735723058@qq.com','2010-10-10','普通用户','激活'),('laozhibin','劳志斌','@qQ123456','735723058@qq.com','2010-10-10','普通用户','激活'),('liangzhuoming','梁卓林','@qQ123456','735723058@qq.com','2010-10-10','普通用户','激活'),('lisa','昆鹏','@qQ123456','2459860471@qq.com','2014-07-30','经理','注销'),('lisa321','昊嘉','@qQ123456','2459860471@qq.com','2014-07-30','普通用户','激活'),('manager','博耘','@qQ123456','2459860471@qq.com','2020-11-18','超级Admin','激活'),('pio0909','蓝敲门','@qQ123456','735723058@qq.com','2020-11-22','普通用户','激活'),('q123','张一','@qQ123456','735723058@qq.com','2020-11-23','普通用户','激活'),('q234','张二','@qQ123456','735723058@qq.com','2010-10-10','普通用户','激活'),('root','海阳','@qQ123456','735723058@qq.com','2020-11-23','超级Admin','激活'),('ss123','李二','@qQ123456','735723058@qq.com','2020-11-22','普通用户','激活'),('ss789','李三','@qQ123456','735723058@qq.com','2010-10-10','经理','注销'),('student','晨濡','@qQ123456','2459860471@qq.com','2020-11-18','普通用户','注销'),('student1','运锋','@qQ123456','2459860471@qq.com','2020-11-18','普通用户','激活'),('student2','星爵','@qQ123456','2459860471@qq.com','2020-11-18','普通用户','激活'),('tony','景逸','@qQ123456','2459860471@qq.com','2014-07-30','普通用户','激活'),('tony123','晋鹏','@qQ123456','2459860471@qq.com','2014-07-30','普通用户','激活'),('user','鸿哲','@qQ123456','735723058@qq.com','2020-11-23','普通用户','激活'),('w123','张三','@qQ123456','735723058@qq.com','2020-11-22','普通用户','注销'),('w234','李一','@qQ123456','735723058@qq.com','2010-10-10','经理','激活'),('zhaoweiliang','赵维亮','@qQ123456','735723058@qq.com','2020-11-22','经理','注销'),('zxc123','李四','@qQ123456','735723058@qq.com','2020-11-18','经理','激活');
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

-- Dump completed on 2020-11-24  8:50:39