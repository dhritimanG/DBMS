CREATE TABLE `MOVIE2DIRECTOR` (
  `moviesDirected_ID` int(11) NOT NULL,
  `directors_ID` int(11) NOT NULL,
  PRIMARY KEY (`moviesDirected_ID`,`directors_ID`),
  KEY `FK_MOVIE2DIRECTOR_directors_ID` (`directors_ID`),
  CONSTRAINT `FK_MOVIE2DIRECTOR_directors_ID` FOREIGN KEY (`directors_ID`) REFERENCES `PERSON` (`ID`),
  CONSTRAINT `FK_MOVIE2DIRECTOR_moviesDirected_ID` FOREIGN KEY (`moviesDirected_ID`) REFERENCES `MOVIE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
