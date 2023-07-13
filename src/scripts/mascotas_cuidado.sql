-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: mascotas
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `cuidado`
--

DROP TABLE IF EXISTS `cuidado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuidado` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Direccion` varchar(255) NOT NULL,
  `Telefono` varchar(50) NOT NULL,
  `Imagen` varchar(255) DEFAULT NULL,
  `CBU` varchar(100) DEFAULT NULL,
  `IdTipoCuidado` int NOT NULL,
  `Latitud` double DEFAULT NULL,
  `Longitud` double DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKgjgrhpowdm1qm0m3afgt3j5o9` (`IdTipoCuidado`),
  CONSTRAINT `cuidado_ibfk_1` FOREIGN KEY (`IdTipoCuidado`) REFERENCES `tipocuidado` (`Id`),
  CONSTRAINT `FKgjgrhpowdm1qm0m3afgt3j5o9` FOREIGN KEY (`IdTipoCuidado`) REFERENCES `tipocuidado` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuidado`
--

LOCK TABLES `cuidado` WRITE;
/*!40000 ALTER TABLE `cuidado` DISABLE KEYS */;
INSERT INTO `cuidado` VALUES (1,'Patita de San Vicente','misPerritos@gmail.com','Cordoba 123','11837428374','refugio01.jpg','039249293402823',1,-34.6666328,-58.5824041),(2,'Patita del sur','misGatitos@gmail.com','Cordoba 123','11837428374','refugio02.jpg','039249293402823',1,-34.6666328,-58.5824041),(3,'Patita Dorrego','misGatitos@gmail.com','Peron 9385','11837428374','refugio03.jpg','039249293402823',1,-34.6666328,-58.5824041),(5,'Refugio Feliz','refugioFeliz@gmail.com','Machado 9385','11837428374','refugio04.jpg','039249293402823',1,-34.6666328,-58.5824041),(6,'Hernan Gomez','hernancito@gmail.com','Machado 9385','11837428374','cuidador01.jpg','039249293402823',2,-34.6666328,-58.5824041),(7,'roberto Andrade','rober_perrito@gmail.com','Machado 9385','11837428374','cuidador02.jpg','039249293402823',2,-34.6666328,-58.5824041),(8,'Arnold Termi','T2@gmail.com','Machado 9385','11837428374','cuidador03.jpg','039249293402823',2,-34.6666328,-58.5824041),(9,'Anita Sanchez','soyanita77@gmail.com','Machado 9385','11837428374','cuidador04.jpg','039249293402823',2,-34.6666328,-58.5824041),(10,'Elian Perrone','Perrone_perro@gmail.com','Machado 9385','11837428374','cuidador05.jpg','039249293402823',2,-34.6666328,-58.5824041),(11,'Sergio Berni','Perrone_perro@gmail.com','Machado 9385','11837428374','cuidador07.jpg','039249293402823',2,-34.6666328,-58.5824041),(12,'Vanesa pepe','Perrone_perro@gmail.com','Machado 9385','11837428374','cuidador08.jpg','039249293402823',2,-34.6666328,-58.5824041),(13,'Jorge Martin','Perrone_perro@gmail.com','Machado 9385','11837428374','cuidador09.jpg','039249293402823',2,-34.6666328,-58.5824041),(14,'Lucia Rodriguez','Perrone_perro@gmail.com','Machado 9385','11837428374','cuidador10.jpg','039249293402823',2,-34.6666328,-58.5824041),(15,'Sonia juarez','Perrone_perro@gmail.com','Machado 9385','11837428374','cuidador11.jpg','039249293402823',2,-34.6666328,-58.5824041),(16,'Yesica juarez','Perrone_perro@gmail.com','Machado 9385','11837428374','cuidador12.jpg','039249293402823',2,-34.6666328,-58.5824041),(17,'Yamila Roo','Perrone_perro@gmail.com','Machado 9385','11837428374','cuidador13.jpg','039249293402823',2,-34.6666328,-58.5824041),(18,'El campito','Perrone_perro@gmail.com','Machado 9385','11837428374','refugio05.jpg','039249293402823',1,-34.6666328,-58.5824041),(19,'Las colitas chochas','Perrone_perro@gmail.com','Machado 9385','11837428374','refugio06.jpg','039249293402823',1,-34.6666328,-58.5824041),(20,'Huellitas seguras','Perrone_perro@gmail.com','Machado 9385','11837428374','refugio07.jpg','039249293402823',1,-34.6666328,-58.5824041),(21,'Dejando huellas','Perrone_perro@gmail.com','Machado 9385','11837428374','refugio08.jpg','039249293402823',1,-34.6666328,-58.5824041),(22,'Cuenta conmigo','Perrone_perro@gmail.com','Machado 9385','11837428374','refugio09.jpg','039249293402823',1,-34.6666328,-58.5824041),(23,'Huellitas al corazon','Perrone_perro@gmail.com','Machado 9385','11837428374','refugio10.jpg','039249293402823',1,-34.6666328,-58.5824041),(24,'Huellitas en peligro','Perrone_perro@gmail.com','Machado 9385','11837428374','refugio11.jpg','039249293402823',1,-34.6666328,-58.5824041),(25,'Huellas en apures','Perrone_perro@gmail.com','Machado 9385','11837428374','refugio12.jpg','039249293402823',1,-34.6666328,-58.5824041);
/*!40000 ALTER TABLE `cuidado` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-13 20:51:51
