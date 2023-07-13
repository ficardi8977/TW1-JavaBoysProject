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
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Telefono` varchar(50) NOT NULL,
  `Latitud` varchar(50) NOT NULL,
  `Longitud` varchar(50) NOT NULL,
  `FechaCreacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `IdTipoUsuario` int NOT NULL,
  `activo` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  KEY `FK43itlpxdx8vu7wphtopnby7td` (`IdTipoUsuario`),
  CONSTRAINT `FK43itlpxdx8vu7wphtopnby7td` FOREIGN KEY (`IdTipoUsuario`) REFERENCES `tipousuario` (`Id`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`IdTipoUsuario`) REFERENCES `tipousuario` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Moderador','Administrador','admin@gmail.com','','-34,0000000','-58,0000000','2023-05-11 04:29:22',2,_binary '','2E33A9B0B06AA0A01EDE70995674EE23','carlitos.jpg',NULL),(3,'Moderador','admin2','mail2@mail','','-34,0000000','-58,0000000','2023-05-11 23:03:47',1,_binary '','2E33A9B0B06AA0A01EDE70995674EE23','AMIPETS2.PNG',NULL),(4,'Fernando','Icardi','fernando.icardi@gmail.com','1166314479','-34,0000000','-58,0000000','2023-06-11 14:39:31',1,_binary '','2E33A9B0B06AA0A01EDE70995674EE23','AMIPETS2.PNG',NULL),(5,'Emanuel','Ibarrola','emanuel.ibarrola3001@gmail.com','1166314479','-34,0000000','-58,0000000','2023-06-11 14:39:31',1,_binary '','2E33A9B0B06AA0A01EDE70995674EE23','perfil.jpg',NULL),(6,'Tomas','Magliano','maglianotomas@gmail.com','1166314479','-34,0000000','-58,0000000','2023-06-11 14:39:31',1,_binary '','2E33A9B0B06AA0A01EDE70995674EE23','mecanico-celebracion-tableta-digital_1170-1552.jpg',NULL),(7,'William','Meneses','menesestapia@gmail.com','1166314479','-34,0000000','-58,0000000','2023-06-11 14:39:31',1,_binary '','2E33A9B0B06AA0A01EDE70995674EE23','1000_F_602284189_Hwz111B8LkCgIaxlIKgmgZr4CKE2BStk.jpg',NULL),(8,'Moderador','Icardi','ficardi89@gmail.com','66314479','-34.6613458','-58.59316370000001','2023-06-29 04:49:47',1,_binary '\0','2E33A9B0B06AA0A01EDE70995674EE23','AMIPETS2.PNG',NULL),(9,'Moderador','Icardi','pepe@gmail.com','66314479','-34.465521429697674','-59.96400607597655','2023-06-29 04:50:38',1,_binary '\0','2E33A9B0B06AA0A01EDE70995674EE23','AMIPETS2.PNG',NULL),(10,'Carlos','Icardi','fernandoicardi@gmail.com','66314479','-34.6153206216885','-58.498683104632555','2023-06-29 04:51:26',1,_binary '\0','2E33A9B0B06AA0A01EDE70995674EE23','AMIPETS2.PNG',NULL),(11,'Carlos','sancho','juancito@gmail.com','66314479','-34.3766824','-59.8227099','2023-06-29 04:59:31',1,_binary '\0','2E33A9B0B06AA0A01EDE70995674EE23','1000_F_600060484_UUSunitLVExz3Re3WCAeuLT8gwGfOKym.jpg',NULL),(12,'Carlos','perez','jp@gmail.com','66314479','-34.5633312','-59.1208805','2023-06-29 23:46:24',1,_binary '\0','2E33A9B0B06AA0A01EDE70995674EE23','AMIPETS2.PNG',NULL),(13,'Carlos','sanchez','jsanchez@gmail.com','66314479','-34.5633312','-59.1208805','2023-06-30 00:59:08',1,_binary '\0','2E33A9B0B06AA0A01EDE70995674EE23','AMIPETS2.PNG',NULL),(14,'Carlos','Sanchez','js@gmail.com','66314479','-34.6613458','-58.59316370000001','2023-07-13 20:33:32',1,_binary '\0','2E33A9B0B06AA0A01EDE70995674EE23','GUANTES.jpg_b88ccd05-c58a-4e77-95f0-5ef08510069c.jpg',NULL),(15,'Juan','Sanchez','js2@gmail.com','66314479','-34.6157959','-58.5158707','2023-07-13 22:16:08',1,_binary '\0','2E33A9B0B06AA0A01EDE70995674EE23','perfil02.jpg_eb352efe-5167-40a5-9ec8-c96525d21324.jpg',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
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
