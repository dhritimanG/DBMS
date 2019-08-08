CREATE TABLE `MOVIE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL,
  `LIBRARY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_MOVIE_LIBRARY_ID` (`LIBRARY_ID`),
  CONSTRAINT `FK_MOVIE_LIBRARY_ID` FOREIGN KEY (`LIBRARY_ID`) REFERENCES `MOVIELIBRARY` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
