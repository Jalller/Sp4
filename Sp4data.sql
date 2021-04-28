
CREATE DATABASE IF NOT EXISTS bordfodbold DEFAULT CHARSET = utf8mb4;
use bordfodbold;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS Teams;
DROP TABLE IF EXISTS Players;
DROP TABLE IF EXISTS PreQuarter;
DROP TABLE IF EXISTS Quarter;
DROP TABLE IF EXISTS Semi;
DROP TABLE IF EXISTS Finale;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE Teams (
    id TINYINT PRIMARY KEY AUTO_INCREMENT,
    teamName VARCHAR(255),
    teamSize TINYINT,
    points TINYINT DEFAULT 0,
    goalScore int default 0
)  ENGINE=INNODB AUTO_INCREMENT=1;

CREATE TABLE Players (
    /*id TINYINT PRIMARY KEY AUTO_INCREMENT,*/
    team_id TINYINT,
    playerName VARCHAR(255),
    FOREIGN KEY (team_id)
        REFERENCES Teams (id)
)  ENGINE=INNODB AUTO_INCREMENT=1;

CREATE TABLE PreQuarter(
	id tinyint PRIMARY KEY AUTO_INCREMENT,
	team_Name VARCHAR(255)
)ENGINE=INNODB AUTO_INCREMENT=1;

CREATE TABLE Quarter(
	id tinyint PRIMARY KEY AUTO_INCREMENT,
	team_Name VARCHAR(255)
)ENGINE=INNODB AUTO_INCREMENT=1;

CREATE TABLE Semi(
	id tinyint PRIMARY KEY AUTO_INCREMENT,
	team_Name VARCHAR(255)
)ENGINE=INNODB AUTO_INCREMENT=1;

CREATE TABLE Finale(
	id tinyint PRIMARY KEY AUTO_INCREMENT,
	team_Name VARCHAR(255)
)ENGINE=INNODB AUTO_INCREMENT=1;

insert into Teams(teamName,teamSize,points) values("a",2,0);
insert into Teams(teamName,teamSize,points) values("B",3,0);
insert into Teams(teamName,teamSize,points) values("C",2,0);
insert into Teams(teamName,teamSize,points) values("D",2,0);
insert into Teams(teamName,teamSize,points) values("E",2,0);
insert into Teams(teamName,teamSize,points) values("F",2,0);
insert into Teams(teamName,teamSize,points) values("G",2,0);
insert into Teams(teamName,teamSize,points) values("h",2,0);
insert into Teams(teamName,teamSize,points) values("i",2,0);
INSERT into Teams(teamName,teamSize,points) values("j",2,0);
INSERT into Teams(teamName,teamSize,points) values("k",6,0);
INSERT into Teams(teamName,teamSize,points) values("l",2,0);
INSERT into Teams(teamName,teamSize,points) values("m",2,0);
INSERT into Teams(teamName,teamSize,points) values("n",4,0);
INSERT into Teams(teamName,teamSize,points) values("o",3,0);
INSERT into Teams(teamName,teamSize,points) values("p",2,0);


insert into Players(playerName,team_id) values("Lars",1);
insert into Players(playerName,team_id) values("Kim",1);
INSERT into Players(playerName, team_id) values("Jesper",2);
INSERT into Players(playerName, team_id) values("Kasper",2);
INSERT into Players(playerName, team_id) values("Jens",2);
INSERT into Players(playerName, team_id) values("Messi",3);
INSERT into Players(playerName, team_id) values("Demble",3);
INSERT into Players(playerName, team_id) values("Hazard",4);
INSERT into Players(playerName, team_id) values("Benzma",4);
INSERT into Players(playerName, team_id) values("Neymar",5);
INSERT into Players(playerName, team_id) values("mpabbe",5);
INSERT into Players(playerName, team_id) values("mana",6);
INSERT into Players(playerName, team_id) values("salah",6);
INSERT into Players(playerName, team_id) values("popba",7);
INSERT into Players(playerName, team_id) values("giggs",7);
INSERT into Players(playerName, team_id) values("Ronaldo",8);
INSERT into Players(playerName, team_id) values("Dybala",8);
INSERT into Players(playerName, team_id) values("torsk",9);
INSERT into Players(playerName, team_id) values("sild",9);
INSERT into Players(playerName, team_id) values("blackcat",10);
INSERT into Players(playerName, team_id) values("browncat",10);
INSERT into Players(playerName, team_id) values("attack",11);
INSERT into Players(playerName, team_id) values("field",11);
INSERT into Players(playerName, team_id) values("defence",11);
INSERT into Players(playerName, team_id) values("goalkeeper",11);
INSERT into Players(playerName, team_id) values("reserve",11);
INSERT into Players(playerName, team_id) values("gouch coach",11);
INSERT into Players(playerName, team_id) values("++",12);
INSERT into Players(playerName, team_id) values("- -",12);
INSERT into Players(playerName, team_id) values("torrist",13);
INSERT into Players(playerName, team_id) values("countertorrist",13);
INSERT into Players(playerName, team_id) values("CPU",14);
INSERT into Players(playerName, team_id) values("GPU",14);
INSERT into Players(playerName, team_id) values("PSU",14);
INSERT into Players(playerName, team_id) values("RAM",14);
INSERT into Players(playerName, team_id) values("playstaion",15);
INSERT into Players(playerName, team_id) values("xbox",15);
INSERT into Players(playerName, team_id) values("netendo",15);
INSERT into Players(playerName, team_id) values("git good",16);
INSERT into Players(playerName, team_id) values("zero deaths",16);

use bordfodbold;

select * FROM  Teams;

select * FROM  PreQuarter;
select * FROM  Quarter;
select * FROM  Semi;
select * FROM  Finale;



