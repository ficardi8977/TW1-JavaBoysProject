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
-- Table structure for table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentario` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Mensaje` varchar(255) NOT NULL,
  `IdUsuario` int NOT NULL,
  `IdCuidado` int DEFAULT NULL,
  `IdMascota` int DEFAULT NULL,
  `clasificacion` int DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `IdComentarioPadre` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKaxs8cesf75lcnxw757k86ida9` (`IdUsuario`),
  KEY `FK72d3oldrndbmp1t0ecr77suet` (`IdCuidado`),
  KEY `FKslaxgc705oeuxu9o7vkvg2nqd` (`IdComentarioPadre`),
  KEY `FKmlmtk28xrctxumwl7tqnq1w4w` (`IdMascota`),
  CONSTRAINT `comentario_ibfk_1` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario` (`Id`),
  CONSTRAINT `comentario_ibfk_2` FOREIGN KEY (`IdCuidado`) REFERENCES `cuidado` (`Id`),
  CONSTRAINT `comentario_ibfk_3` FOREIGN KEY (`IdMascota`) REFERENCES `mascota` (`Id`) ON DELETE CASCADE,
  CONSTRAINT `comentario_ibfk_4` FOREIGN KEY (`IdComentarioPadre`) REFERENCES `comentario` (`Id`),
  CONSTRAINT `FK72d3oldrndbmp1t0ecr77suet` FOREIGN KEY (`IdCuidado`) REFERENCES `cuidado` (`Id`),
  CONSTRAINT `FKaxs8cesf75lcnxw757k86ida9` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario` (`Id`),
  CONSTRAINT `FKmlmtk28xrctxumwl7tqnq1w4w` FOREIGN KEY (`IdMascota`) REFERENCES `mascota` (`Id`),
  CONSTRAINT `FKslaxgc705oeuxu9o7vkvg2nqd` FOREIGN KEY (`IdComentarioPadre`) REFERENCES `comentario` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentario`
--

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
INSERT INTO `comentario` VALUES (14,'Buen refugio!',10,22,NULL,5,'2023-06-29 06:13:13',NULL),(15,'muy buena predisposicion!',10,22,NULL,5,'2023-06-29 06:17:42',NULL),(16,'mi hernana me hablo muy bien del lugar!',10,18,NULL,5,'2023-06-29 06:18:51',NULL),(37,'grosos!',10,1,NULL,5,'2023-07-12 01:19:46',NULL),(39,'tremendos! ',1,1,NULL,0,'2023-07-12 01:44:55',37),(41,'otro sub mas!',1,1,NULL,0,'2023-07-12 01:54:22',37),(42,'me parecio ver una gatita muy parecida sobre la calle don bosco. se veia asustada!',1,NULL,5,0,'2023-07-12 01:59:37',NULL),(43,'que pena! no era!',1,NULL,5,0,'2023-07-12 02:30:52',NULL),(44,'que lastima! subcomentario',1,NULL,5,0,'2023-07-12 02:34:07',NULL),(45,'que lastima! subcomentario',1,NULL,5,0,'2023-07-12 02:34:07',NULL),(46,'ya va a aparecer!',1,NULL,5,0,'2023-07-12 02:40:26',42),(48,'firmo',10,12,NULL,4,'2023-07-13 02:50:19',NULL),(58,'Me parecio verla por Don bosco al 3000',5,NULL,2,0,'2023-07-13 21:01:27',NULL),(59,'gracias! era muy parecida pero no era!',4,NULL,2,0,'2023-07-13 21:04:06',58),(60,'Ojala la puedas encontrar!',11,NULL,2,0,'2023-07-13 21:04:43',NULL),(61,'que linda que es!',7,NULL,2,0,'2023-07-13 21:04:48',NULL),(62,'viste que lo que es!',4,NULL,2,0,'2023-07-13 21:05:13',61),(63,'lo vi por aca te dejo el map\r\nhttps://www.google.com/maps/place/Hospital+de+Mor%C3%B3n/@-34.6584846,-58.6119495,14.5z/data=!4m6!3m5!1s0x95bcc772198ebd57:0xf684172956985f18!8m2!3d-34.6589404!4d-58.6100777!16s%2Fg%2F11bw61qlrr?hl=es&entry=ttu',6,NULL,2,0,'2023-07-13 21:06:50',NULL),(64,'un capo total arnold!',10,8,NULL,5,'2023-07-13 21:29:07',NULL),(66,'suerte con eso!',15,NULL,2,0,'2023-07-13 22:19:20',NULL);
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
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
