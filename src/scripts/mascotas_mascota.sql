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
-- Table structure for table `mascota`
--

DROP TABLE IF EXISTS `mascota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mascota` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `IdTipoRaza` int NOT NULL,
  `Descripcion` text,
  `Imagen` varchar(255) DEFAULT NULL,
  `Latitud` varchar(50) NOT NULL,
  `Longitud` varchar(50) NOT NULL,
  `fechaAdopcion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `IdEstado` int NOT NULL,
  `idUsuario` bigint DEFAULT NULL,
  `nombreUsuario` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKh73suar4fkhmf7lqnyaohe753` (`IdTipoRaza`),
  KEY `FK3lpj5cirahh84oe469fmkgccl` (`IdEstado`),
  CONSTRAINT `FK3lpj5cirahh84oe469fmkgccl` FOREIGN KEY (`IdEstado`) REFERENCES `estado` (`Id`),
  CONSTRAINT `FKh73suar4fkhmf7lqnyaohe753` FOREIGN KEY (`IdTipoRaza`) REFERENCES `tiporaza` (`Id`),
  CONSTRAINT `mascota_ibfk_1` FOREIGN KEY (`IdTipoRaza`) REFERENCES `tiporaza` (`Id`),
  CONSTRAINT `mascota_ibfk_2` FOREIGN KEY (`IdEstado`) REFERENCES `estado` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mascota`
--

LOCK TABLES `mascota` WRITE;
/*!40000 ALTER TABLE `mascota` DISABLE KEYS */;
INSERT INTO `mascota` VALUES (1,'Carlitos',1,'Carlitos es un perro amigable y juguetón que fue encontrado abandonado en las calles. Ha pasado los últimos meses en un refugio esperando encontrar un hogar amoroso. Es un perro de tamaño mediano \ncon una personalidad encantadora y está listo para formar parte de una familia','carlitos.jpg','-34.662006','-58.5965566',NULL,2,6,'Fernando','1166314479'),(2,'Luna',1,'Luna, una adorable perra, se ha perdido y estamos desesperados por encontrarla. Por favor, si tienes cualquier información o la has visto en algún lugar, te suplicamos que te pongas en contacto con nosotros de inmediato. Luna es parte de nuestra familia y la extrañamos muchísimo.','Lunas.jpg','-34.6617594','-58.5986801',NULL,5,6,'Fernando','1166314479'),(3,'Perla',1,'Perrito encontrado en la calle','perla.jpg','-34.661933','-58.6002472',NULL,2,6,'Fernando','1166314479'),(4,'Pocho',1,'Perrito encontrado en la calle','pocho.jpg','-34.98239482','-58.98239482',NULL,2,7,'Fernando','1166314479'),(5,'Kira',8,'Se busca una gata perdida llamada Kira. Es de tamaño mediano, con un pelaje suave y brillante. Tiene el color del pelaje atigrado, con tonos grises y marrones. Tiene ojos grandes y expresivos de color verde. Su cara es redondeada y tiene un hocico pequeño. Tiene una estatura baja y un cuerpo esbelto. Suele llevar un collar con una chapa identificativa. Responde al nombre de Luna. Si la has visto o tienes alguna información, por favor, ponte en contacto con nosotros. ¡Se agradece cualquier ayuda para reunirnos con nuestra querida gata!','Kira.jpg','-34.98239482','-58.98239482',NULL,5,4,'Fernando','1166314479'),(6,'Manchita',8,'Se busca una gata perdida llamada Manchita. Es de tamaño mediano, con un pelaje suave y brillante. Tiene el color del pelaje atigrado, con tonos grises y marrones. Tiene ojos grandes y expresivos de color verde. Su cara es redondeada y tiene un hocico pequeño. Tiene una estatura baja y un cuerpo esbelto. Suele llevar un collar con una chapa identificativa. Responde al nombre de Luna. Si la has visto o tienes alguna información, por favor, ponte en contacto con nosotros. ¡Se agradece cualquier ayuda para reunirnos con nuestra querida gata!','Manchita.jpg','-34.98239482','-58.98239482',NULL,2,5,'Fernando','1166314479'),(7,'Piñata',8,'Se busca una gata perdida llamada Piñata. Es de tamaño mediano, con un pelaje suave y brillante. Tiene el color del pelaje atigrado, con tonos grises y marrones. Tiene ojos grandes y expresivos de color verde. Su cara es redondeada y tiene un hocico pequeño. Tiene una estatura baja y un cuerpo esbelto. Suele llevar un collar con una chapa identificativa. Responde al nombre de Luna. Si la has visto o tienes alguna información, por favor, ponte en contacto con nosotros. ¡Se agradece cualquier ayuda para reunirnos con nuestra querida gata!','Piñata.jpg','-34.98239482','-58.98239482',NULL,5,5,'Fernando','1166314479'),(8,'Lito',8,'Se busca una gata perdida llamada Piñata. Es de tamaño mediano, con un pelaje suave y brillante. Tiene el color del pelaje atigrado, con tonos grises y marrones. Tiene ojos grandes y expresivos de color verde. Su cara es redondeada y tiene un hocico pequeño. Tiene una estatura baja y un cuerpo esbelto. Suele llevar un collar con una chapa identificativa. Responde al nombre de Luna. Si la has visto o tienes alguna información, por favor, ponte en contacto con nosotros. ¡Se agradece cualquier ayuda para reunirnos con nuestra querida gata!','Lito.jpg','-34.98239482','-58.98239482',NULL,5,5,'Fernando','1166314479'),(9,'Josecito',2,'se perdio josecito','perritotriste_02.jpg','-34.46398983791535','-58.90870601674661',NULL,5,12,'Fernando','1166314479'),(12,'Roco',2,'descripcion','perrito_contento.jpg_9947acf4-0d83-4677-934b-1ba3ecaa961c.jpg','-34.6613458','-58.59316370000001',NULL,3,15,'Juan','66314479');
/*!40000 ALTER TABLE `mascota` ENABLE KEYS */;
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
