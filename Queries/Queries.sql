#1. Create Person Table:
CREATE TABLE `hw2_ganguly_dhritiman_fall_2017`.`person` (
  `firstName` VARCHAR(20) NULL,
  `lastName` VARCHAR(20) NULL,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NULL,
  `email` VARCHAR(20) NULL,
  `dob` DATE NULL,
  `personId` VARCHAR(20) NULL,
  `type` VARCHAR(20) NULL,
  PRIMARY KEY (`username`));

#2. Create Developer Table:
CREATE TABLE `hw2_ganguly_dhritiman_fall_2017`.`developer` (
  `developerKey` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`developerKey`),
  CONSTRAINT `developer_person_generalization`
    FOREIGN KEY (`developerKey`)
    REFERENCES `hw2_ganguly_dhritiman_fall_2017`.`person` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

#3. Create User Table:
CREATE TABLE `hw2_ganguly_dhritiman_fall_2017`.`user` (
  `userAgreement` TINYINT NULL,
  `userKey` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`userKey`),
  CONSTRAINT `user_person_generalization`
    FOREIGN KEY (`userKey`)
    REFERENCES `hw2_ganguly_dhritiman_fall_2017`.`person` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

#4. Create Website Table:
CREATE TABLE `hw2_ganguly_dhritiman_fall_2017`.`website` (
  `name` VARCHAR(20) NULL,
  `description` VARCHAR(45) NULL,
  `created` DATE NULL,
  `updated` DATE NULL,
  `visits` INT NULL,
  `websiteId` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`websiteId`),
  CONSTRAINT `website_developer`
    FOREIGN KEY (`websiteId`)
    REFERENCES `hw2_ganguly_dhritiman_fall_2017`.`developer` (`developerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

/*Changed developerId  for foreign Key*/

ALTER TABLE `hw2_ganguly_dhritiman_fall_2017`.`website` 
CHANGE COLUMN `websiteId` `websiteId` VARCHAR(12) NOT NULL ,
ADD COLUMN `developerId` VARCHAR(12) NULL AFTER `websiteId`,
ADD INDEX `website_developer_idx` (`developerId` ASC);
ALTER TABLE `hw2_ganguly_dhritiman_fall_2017`.`website` 
ADD CONSTRAINT `website_developer`
  FOREIGN KEY (`developerId`)
  REFERENCES `hw2_ganguly_dhritiman_fall_2017`.`developer` (`developerId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

#5. Create Page Table:
CREATE TABLE `hw2_ganguly_dhritiman_fall_2017`.`page` (
  `websiteId` VARCHAR(12) NULL,
  `title` VARCHAR(12) NULL,
  `description` VARCHAR(45) NULL,
  `created` DATE NULL,
  `updated` DATE NULL,
  `views` INT NULL,
  `pageId` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`pageId`),
  CONSTRAINT `page_website`
    FOREIGN KEY (`websiteId`)
    REFERENCES `hw2_ganguly_dhritiman_fall_2017`.`website` (`websiteId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

#6. Create Widget Table:
CREATE TABLE `hw2_ganguly_dhritiman_fall_2017`.`widget` (
  `widgetId` VARCHAR(12) NOT NULL,
  `pageId` VARCHAR(12) NULL,
  `name` VARCHAR(20) NULL,
  `width` INT NULL,
  `height` INT NULL,
  `cssClass` VARCHAR(10) NULL,
  `cssStyle` VARCHAR(10) NULL,
  `text` VARCHAR(45) NULL,
  `order` INT NULL,
  PRIMARY KEY (`widgetId`),
  INDEX `widget_page_idx` (`pageId` ASC),
  CONSTRAINT `widget_page`
    FOREIGN KEY (`pageId`)
    REFERENCES `hw2_ganguly_dhritiman_fall_2017`.`page` (`pageId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

#7. Create Address Table:
CREATE TABLE `hw2_ganguly_dhritiman_fall_2017`.`address` (
  `street1` VARCHAR(45) NOT NULL,
  `street2` VARCHAR(45) NULL,
  `city` VARCHAR(20) NULL,
  `state` VARCHAR(20) NULL,
  `zip` VARCHAR(10) NULL,
  `primary` TINYINT NULL,
  `addressId` INT NOT NULL,
  `personId` VARCHAR(12) NULL,
  PRIMARY KEY (`street1`, `addressId`),
  INDEX `address_person_idx` (`personId` ASC),
  CONSTRAINT `address_person`
    FOREIGN KEY (`personId`)
    REFERENCES `hw2_ganguly_dhritiman_fall_2017`.`person` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

/*Adding cascade constraint */
ALTER TABLE `hw2_ganguly_dhritiman_fall_2017`.`address` 
DROP FOREIGN KEY `address_person`;
ALTER TABLE `hw2_ganguly_dhritiman_fall_2017`.`address` 
ADD CONSTRAINT `address_person`
  FOREIGN KEY (`personId`)
  REFERENCES `hw2_ganguly_dhritiman_fall_2017`.`person` (`username`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
#8. Create Phone Table:
CREATE TABLE `hw2_ganguly_dhritiman_fall_2017`.`phone` (
  `phone` VARCHAR(10) NULL,
  `primary` TINYINT NOT NULL,
  `personId` VARCHAR(45) NULL,
  PRIMARY KEY (`primary`),
  INDEX `phone_person_idx` (`personId` ASC),
  CONSTRAINT `phone_person`
    FOREIGN KEY (`personId`)
    REFERENCES `hw2_ganguly_dhritiman_fall_2017`.`person` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

/*update phone as primary key */

ALTER TABLE `hw2_ganguly_dhritiman_fall_2017`.`phone` 
CHANGE COLUMN `phone` `phone` VARCHAR(20) NOT NULL ,
CHANGE COLUMN `primary` `primary` TINYINT(4) NULL ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`phone`);

#9. Create WebsiteRole Table:
CREATE TABLE `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (
  `role` VARCHAR(20) NOT NULL,
  `developerId` VARCHAR(12) NULL,
  `websiteId` VARCHAR(12) NULL,
  `roleId` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`roleId`),
  INDEX `role_developer_idx` (`developerId` ASC),
  INDEX `role_website_idx` (`websiteId` ASC),
  CONSTRAINT `role_developer`
    FOREIGN KEY (`developerId`)
    REFERENCES `hw2_ganguly_dhritiman_fall_2017`.`developer` (`developerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `role_website`
    FOREIGN KEY (`websiteId`)
    REFERENCES `hw2_ganguly_dhritiman_fall_2017`.`website` (`websiteId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

/* Adding foreign key for role table */
ALTER TABLE `hw2_ganguly_dhritiman_fall_2017`.`websiterole` 
DROP FOREIGN KEY `role_developer`,
DROP FOREIGN KEY `role_website`;
ALTER TABLE `hw2_ganguly_dhritiman_fall_2017`.`websiterole` 
ADD INDEX `websiterole_role_idx` (`role` ASC);
ALTER TABLE `hw2_ganguly_dhritiman_fall_2017`.`websiterole` 
ADD CONSTRAINT `websiterole_developer`
  FOREIGN KEY (`developerId`)
  REFERENCES `hw2_ganguly_dhritiman_fall_2017`.`developer` (`developerId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `websiterole_website`
  FOREIGN KEY (`websiteId`)
  REFERENCES `hw2_ganguly_dhritiman_fall_2017`.`website` (`websiteId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `websiterole_role`
  FOREIGN KEY (`role`)
  REFERENCES `hw2_ganguly_dhritiman_fall_2017`.`role` (`role`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  
# 10. Create WebsitePriviledge Table:
CREATE TABLE `websitepriviledge` (
  `priviledge` varchar(20) NOT NULL,
  `developerId` varchar(12) DEFAULT NULL,
  `websiteId` varchar(12) DEFAULT NULL,
  `priviledgeId` varchar(12) NOT NULL,
  PRIMARY KEY (`priviledgeId`),
  KEY `priviledge_developer_idx` (`developerId`),
  KEY `priviledge_website_idx` (`websiteId`),
  CONSTRAINT `priviledge_developer` FOREIGN KEY (`developerId`) REFERENCES `developer` (`developerId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `priviledge_website` FOREIGN KEY (`websiteId`) REFERENCES `website` (`websiteId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8

# 11. Create PageRole Table:
CREATE TABLE `pagerole` (
  `roleId` varchar(12) NOT NULL,
  `role` varchar(45) NOT NULL,
  `pageId` varchar(12) DEFAULT NULL,
  `developerId` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  KEY `pagerole_page_idx` (`pageId`),
  KEY `pagerole_developer_idx` (`developerId`),
  CONSTRAINT `pagerole_developer` FOREIGN KEY (`developerId`) REFERENCES `developer` (`developerId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pagerole_page` FOREIGN KEY (`pageId`) REFERENCES `page` (`pageId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8

/*Adding foreign key for role table*/

ALTER TABLE `hw2_ganguly_dhritiman_fall_2017`.`pagerole` 
ADD INDEX `pagerole_role_idx` (`role` ASC);
ALTER TABLE `hw2_ganguly_dhritiman_fall_2017`.`pagerole` 
ADD CONSTRAINT `pagerole_role`
  FOREIGN KEY (`role`)
  REFERENCES `hw2_ganguly_dhritiman_fall_2017`.`role` (`role`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

#12. Create PagePriviledge Table: 
CREATE TABLE `pagepriviledge` (
  `priviledgeId` varchar(12) NOT NULL,
  `priviledge` varchar(45) NOT NULL,
  `developerId` varchar(12) DEFAULT NULL,
  `pageId` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`priviledgeId`),
  KEY `priviledge_page_idx` (`pageId`),
  KEY `pagepriviledge_developer_idx` (`developerId`),
  CONSTRAINT `pagepriviledge_developer` FOREIGN KEY (`developerId`) REFERENCES `developer` (`developerId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pagepriviledge_page` FOREIGN KEY (`pageId`) REFERENCES `page` (`pageId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8

#13. Create Role Table:
CREATE TABLE `role` (
  `role` varchar(12) NOT NULL,
  PRIMARY KEY (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8










#14.	Insert to Person:

INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`person` (`firstName`, `lastName`, `username`, `password`, `email`, `dob`, `personId`, `type`) VALUES ('Alice', 'Wonder', 'alice', 'alice', 'alice@wonder.com', '2017-01-01', '12', 'Developer');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`person` (`firstName`, `lastName`, `username`, `password`, `email`, `dob`, `personId`, `type`) VALUES ('Bob', 'Marley', 'bob', 'bob', 'bob@marley.com', '2017-01-02', '23', 'Developer');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`person` (`firstName`, `lastName`, `username`, `password`, `email`, `dob`, `personId`, `type`) VALUES ('Charles', 'Garcia', 'charlie', 'charlie', 'church@garcia.com', '2017-01-03', '34', 'Developer');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`person` (`firstName`, `lastName`, `username`, `password`, `email`, `dob`, `personId`, `type`) VALUES ('Dan', 'Martin', 'dan', 'dan', 'dan@martin.com', '2017-01-04', '45', 'User');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`person` (`firstName`, `lastName`, `username`, `password`, `email`, `dob`, `personId`, `type`) VALUES ('Ed', 'Karaz', 'ed', 'ed', 'ed@kar.com', '2017-01-05', '56', 'User');

#15.	Insert to User:

INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`user` (`userAgreement`, `userId`, `userKey`) VALUES ('0', 'dan', '7654fda');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`user` (`userAgreement`, `userId`, `userKey`) VALUES ('1', 'ed', '5678dfgh');

#16.	Insert to Developer:

INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`developer` (`developerKey`, `developerId`) VALUES ('4321rewq', 'alice');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`developer(`developerKey`,   `developerId`)  VALUES (' 5432trew', 'bob');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`developer(`developerKey`,   `developerId`)  VALUES (' 6543ytre', 'charlie');



#17.	Insert to Phone:

INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`phone` (`phone`, `primary`, `personId`) VALUES ('123-234-3456', '1', 'alice');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`phone` (`phone`, `primary`, `personId`) VALUES ('234-345-4566', '0', 'alice');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`phone` (`phone`, `primary`, `personId`) VALUES ('345-456-5677', '1', 'bob');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`phone` (`phone`, `primary`, `personId`) VALUES ('321-432-5435', '1', 'charlie');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`phone` (`phone`, `primary`, `personId`) VALUES ('432-432-5433', '0', 'charlie');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`phone` (`phone`, `primary`, `personId`) VALUES ('543-543-6544', '0', 'charlie');

#18.	Insert to Address:

INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`address` (`street1`, `city`, `state`, `zip`, `primary`, `addressId`, `personId`) VALUES ('123 Adam St', 'Alton', 'MA', '01234', '1', '1', 'alice');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`address` (`street1`, `city`, `state`, `zip`, `primary`, `addressId`, `personId`) VALUES ('234 Birch St.', 'Boston', 'MA', '02345', '0', '2', 'alice');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`address` (`street1`, `street2`, `city`, `state`, `zip`, `primary`, `addressId`, `personId`) VALUES ('345 Charles St.', '', 'Chelms', 'MA', '03455', '1', '3', 'bob');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`address` (`street1`, `city`, `state`, `zip`, `primary`, `addressId`, `personId`) VALUES ('456 Down St', 'Dalton', 'MA', '04566', '0', '4', 'bob');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`address` (`street1`, `city`, `state`, `zip`, `primary`, `addressId`, `personId`) VALUES ('543 East St.', 'Everett', 'MA', '01112', '0', '5', 'bob');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`address` (`street1`, `city`, `state`, `zip`, `primary`, `addressId`, `personId`) VALUES ('654 Frank St.', 'Foulton', 'MA', '04322', '1', '6', 'charlie');

#19.	Insert to Website:

INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`website` (`name`, `description`, `visits`, `websiteId`, `developerId`) VALUES ('Facebook', 'an online social media and social networking service', '1234234', '123', 'alice');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`website` (`name`, `description`, `visits`, `websiteId`, `developerId`) VALUES ('Twiter', 'an online news and social networking service', '4321543', '234', 'bob');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`website` (`name`, `description`, `visits`, `websiteId`, `developerId`) VALUES ('Wikipedia', 'a free online encyclopedia', '3456654', '345', 'charlie');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`website` (`name`, `description`, `visits`, `websiteId`, `developerId`) VALUES ('CNN', 'an American basic cable and satellite television news channel', '6543345', '456', 'alice');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`website` (`name`, `description`, `visits`, `websiteId`, `developerId`) VALUES ('CNET', 'an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics', '5433455', '567', 'bob');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`website` (`name`, `description`, `visits`, `websiteId`, `developerId`) VALUES ('Gizmodo', 'a design, technology, science and science fiction website that also writes articles on politics', '4322345', '678', 'charlie');

#20.	Insert into Page:

INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`page` (`title`, `description`, `views`, `pageId`, `websiteId`) VALUES ('Home', 'Landing page', '123434', '1', '567');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`page` (`title`, `description`, `views`, `pageId`, `websiteId`) VALUES ('About', 'Website description', '234545', '2', '678');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`page` (`title`, `description`, `views`, `pageId`, `websiteId`) VALUES ('Contact', 'Addresses, phones, and contact info', '345656', '3', '345');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`page` (`title`, `description`, `views`, `pageId`, `websiteId`) VALUES ('Preferences', 'Where users can configure their preferences', '456776', '4', '456');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`page` (`title`, `description`, `views`, `pageId`, `websiteId`) VALUES ('Profile', 'Users can configure their personal information', '567878', '5', '567');

#21.	Insert into WebsiteRole:

INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Owner', 'alice', '123', '1');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Editor', 'bob', '123', '2');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Admin', 'charlie', '123', '3');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Owner', 'bob', '234', '4');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Editor', 'charlie', '234', '5');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Admin', 'alice', '234', '6');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Owner', 'charlie', '345', '7');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Editor', 'alice', '345', '8');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Admin', 'bob', '345', '9');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Owner', 'alice', '456', '10');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Editor', 'bob', '456', '11');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Admin', 'charlie', '456', '12');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Owner', 'bob', '567', '13');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Editor', 'charlie', '567', '14');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Admin', 'alice', '567', '15');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Owner', 'charlie', '678', '16');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Editor', 'alice', '678', '17');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`websiterole` (`role`, `developerId`, `websiteId`, `roleId`) VALUES ('Admin', 'bob', '678', '18');

#22.	Insert into PageRole Table:

INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`pagerole` (`roleId`, `role`, `pageId`, `developerId`) VALUES ('1', 'Editor', '123', 'alice');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`pagerole` (`roleId`, `role`, `pageId`, `developerId`) VALUES ('2', 'Reviewer', '123', 'bob');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`pagerole` (`roleId`, `role`, `pageId`, `developerId`) VALUES ('3', 'Writer', '123', 'charlie');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`pagerole` (`roleId`, `role`, `pageId`, `developerId`) VALUES ('4', 'Editor', '234', 'bob');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`pagerole` (`roleId`, `role`, `pageId`, `developerId`) VALUES ('5', 'Reviewer', '234', 'charlie');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`pagerole` (`roleId`, `role`, `pageId`, `developerId`) VALUES ('6', 'Writer', '234', 'alice');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`pagerole` (`roleId`, `role`, `pageId`, `developerId`) VALUES ('7', 'Editor', '345', 'charlie');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`pagerole` (`roleId`, `role`, `pageId`, `developerId`) VALUES ('8', 'Reviewer', '345', 'alice');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`pagerole` (`roleId`, `role`, `pageId`, `developerId`) VALUES ('9', 'Writer', '345', 'bob');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`pagerole` (`roleId`, `role`, `pageId`, `developerId`) VALUES ('10', 'Editor', '456', 'alice');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`pagerole` (`roleId`, `role`, `pageId`, `developerId`) VALUES ('11', 'Reviewer', '456', 'bob');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`pagerole` (`roleId`, `role`, `pageId`, `developerId`) VALUES ('12', 'Writer', '456', 'charlie');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`pagerole` (`roleId`, `role`, `pageId`, `developerId`) VALUES ('13', 'Editor', '567', 'bob');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`pagerole` (`roleId`, `role`, `pageId`, `developerId`) VALUES ('14', 'Reviewer', '567', 'charlie');
INSERT INTO `hw2_ganguly_dhritiman_fall_2017`.`pagerole` (`roleId`, `role`, `pageId`, `developerId`) VALUES ('15', 'Writer', '567', 'alice');


#23.	Queries (Retrieve):
#1.	A) 
select * from developer;

#B) 
select p.username from person p, developer d where d.developerId = p.username and p.personId=34;

#C) 
select p.username from person p, developer d where p.username = d.developerId and d.developerId in (select wrole.developerId from websiterole wrole, person p1, developer d1, website w where p.username = d.developerId and w.developerId = d.developerId and w.name = ‘Twitter’ and wrole.role not in (‘Owner’));

#D) 
select p.username from person p, developer d where p.username = d.developerId and d.developerId in (select prole.developerId from pagerole prole, developer d1, website w, page p1 where w.developerId = d1.developerId and p1.websiteId = w.websiteId and prole.pageId = p1.pageId and prolr.role = ‘Reviewer’ and p1.visits < 300000);

#E) 
select p.username from person p, developer d where p.username  = d.developerId and d.developerId in (select prole.developerId from pagerole prole, websiterole wrole developer d1, website w, page p1, widget wg where w.developerId = d1.developerId and p1.websiteId = w.websiteId and wrole.websiteId = w.websiteId and wg.type = ‘Heading and w.name = ‘CNET’ and p1.title = ‘Home’ and prole.role = ‘Writer’);

#2.	A) 
select name from website where visits = (select min(visits) from website);

#B) 
select name from website where websiteId = 678;

#C) 
select w.name from website w, page p, pagerole prole, widget wg where p.websiteId = w.websiteId and wg.pageId = p.pageId and prole.pageId = p.pageId and prole.role = ‘Reviewer’ and wg.type = ‘YouTube’;

#D) 
select w.name from website w, person p, developer d, websiterole wrole where p.username = d.developerId and w.developerId = d.developerId and wrole.websiteId = w.websiteId and wrole.role = ‘Owner’ and p.firstName = ‘Alice’;

#E) 
select w.name from website w, person p, developer d, websiterole wrole where p.username = d.developerId and w.developerId = d.developerId and wrole.websiteId = w.websiteId and wrole.role = ‘Admin’ and wr.developerId = (select username from person where username = ‘charlie’ and w.visits > 6000000);

#3.	A) 
select title from page where views = (select max(views) from page);

#B) 
select title from page where pageId = 234;

#C) 
select p.title from page p1, pagerole prole, person p, developer d, role r where prole.role = r.role and prole.page.Id = p1.pageId and d.developerId = p.username and prole.developerId = d.developerId and p.username = ‘alice’ and r.role = ‘Editor’;

#D) 
select w.name, sum(p.views) from website w, page p where p.websiteId = w.websiteId and w.name = ‘CNET’ group by w.name;

#E) 
select w.name, avg(p.views) from website w, page p where p.websiteId = w.websiteId and w.name = ‘Wikipedia group by w.name;

#4.	A) 
select wg.name from widget wg, website w, page p where wg.pageId = p.pageId and p.websiteId = w.websiteId and w.name = ‘CNN’ and p.title = ‘Home’;

#B) 
select wg.name from widget wg, website w, page p where wg.pageId = p.pageId and p.websiteId = w.websiteId and w.name = ‘CNN’ and wg.type = ‘Youtube’;

#C) 
select wg.name from widget wg, person p, developer d, page p1, pagerole prole, role r where p.username = d.developerId and prole.developerId = d.developerId and prole.pageId = p.pageId and wg.pageId = p.pageId and prole.role = r.role and p.username = ‘alice’ and r.role = ‘Reviewer’ and wg.type = ‘Image’;

#D) 
select wg.name from widget wg, website w, page p where p.websiteId = w.websiteId and wg.pageId = p.pageId and w.name = ‘Wikipedia’


#24.Queries (Update):
#1.	
UPDATE phone p SET p.phone =' 333-444-5555' WHERE p.personId = 'charlie' and p.primary = 1;

#3.	
SET SQL_SAFE_UPDATES = 0;
UPDATE page p SET title = concat("CNET-",title) where p.websiteId = 567;

#25.	Queries (Delete):
#1.	
DELETE FROM address where personId = 'alice' and address.primary = 1;

#2.	
DELETE FROM widget w where page p = (select pageId from page where p.title = ‘Contact’) and w.order = (select max(w.order) from w);

#4.	
DELETE FROM website where name = 'CNET';


  