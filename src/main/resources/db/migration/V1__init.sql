-- MySQL dump 10.13  Distrib 5.7.38, for Win64 (x86_64)
--
-- Host: localhost    Database: expresskassa
-- ------------------------------------------------------
-- Server version	5.7.38-log

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
-- Table structure for table `fiscal_check`
--

DROP TABLE IF EXISTS `fiscal_check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fiscal_check` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `address` varchar(255) DEFAULT NULL,
                                `cash` float DEFAULT NULL,
                                `change_amount` float DEFAULT NULL,
                                `completed` bit(1) NOT NULL,
                                `date` datetime DEFAULT NULL,
                                `discount` float DEFAULT NULL,
                                `employee_name` varchar(255) DEFAULT NULL,
                                `inn` varchar(255) DEFAULT NULL,
                                `name` varchar(255) DEFAULT NULL,
                                `payment_method` varchar(255) DEFAULT NULL,
                                `tax_percent` decimal(19,2) DEFAULT NULL,
                                `tax_type` varchar(255) DEFAULT NULL,
                                `total` decimal(19,2) DEFAULT NULL,
                                `merchant_id` bigint(20) DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `FKbv4w1jko9bgnf9e5s3onusrnf` (`merchant_id`),
                                CONSTRAINT `FKbv4w1jko9bgnf9e5s3onusrnf` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fiscal_check`
--

LOCK TABLES `fiscal_check` WRITE;
/*!40000 ALTER TABLE `fiscal_check` DISABLE KEYS */;
/*!40000 ALTER TABLE `fiscal_check` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchant`
--

DROP TABLE IF EXISTS `merchant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchant` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `address` varchar(255) DEFAULT NULL,
                            `inn` varchar(255) DEFAULT NULL,
                            `name` varchar(255) DEFAULT NULL,
                            `tax_percent` decimal(19,2) DEFAULT NULL,
                            `tax_type` varchar(255) DEFAULT NULL,
                            `email` varchar(255) DEFAULT NULL,
                            `password` varchar(255) DEFAULT NULL,
                            `shift_id` bigint(20) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKsdlfy1bsv0kb9we8fl82whbyy` (`shift_id`),
                            CONSTRAINT `FKsdlfy1bsv0kb9we8fl82whbyy` FOREIGN KEY (`shift_id`) REFERENCES `shift` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant`
--

LOCK TABLES `merchant` WRITE;
/*!40000 ALTER TABLE `merchant` DISABLE KEYS */;
/*!40000 ALTER TABLE `merchant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `bar_code` varchar(255) DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `photo_url` varchar(255) DEFAULT NULL,
                           `price` decimal(19,2) DEFAULT NULL,
                           `merchant_id` bigint(20) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKk47qmalv2pg906heielteubu7` (`merchant_id`),
                           CONSTRAINT `FKk47qmalv2pg906heielteubu7` FOREIGN KEY (`merchant_id`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt_product`
--

DROP TABLE IF EXISTS `receipt_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt_product` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                   `count` int(11) DEFAULT NULL,
                                   `name` varchar(255) DEFAULT NULL,
                                   `price` decimal(19,2) DEFAULT NULL,
                                   `check_id` bigint(20) DEFAULT NULL,
                                   `product_id` bigint(20) DEFAULT NULL,
                                   PRIMARY KEY (`id`),
                                   KEY `FK3anffpline8l8pvnobxy4d23s` (`product_id`),
                                   KEY `FKlnv0yo0b4n17p3pnuvoccsffd` (`check_id`),
                                   CONSTRAINT `FK3anffpline8l8pvnobxy4d23s` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
                                   CONSTRAINT `FKlnv0yo0b4n17p3pnuvoccsffd` FOREIGN KEY (`check_id`) REFERENCES `fiscal_check` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt_product`
--

LOCK TABLES `receipt_product` WRITE;
/*!40000 ALTER TABLE `receipt_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipt_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shift`
--

DROP TABLE IF EXISTS `shift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shift` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `employee_name` varchar(255) DEFAULT NULL,
                         `end_date` datetime DEFAULT NULL,
                         `start_date` datetime DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shift`
--

LOCK TABLES `shift` WRITE;
/*!40000 ALTER TABLE `shift` DISABLE KEYS */;
/*!40000 ALTER TABLE `shift` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-18 22:34:18
