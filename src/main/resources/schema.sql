CREATE SCHEMA IF NOT EXISTS My_Db;

DROP TABLE IF EXISTS Personal_Information;
DROP TABLE IF EXISTS Candidate_References;
DROP TABLE IF EXISTS Vetting_Officers;
DROP TABLE IF EXISTS Applications;
DROP TABLE IF EXISTS Candidates;
DROP TABLE IF EXISTS Master_Admin;

    -- -----------------------------------------------------
-- TABLE `Candidates`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Candidates
(
    ID            int not null auto_increment primary key,
    First_Name    varchar(200) not null,
    Surname       varchar(200) not null,
    Email         varchar(200) not null unique,
    Password      varchar(200) not null unique,
    Company_Name  varchar(200) not null
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `Personal_Information`
-- -----------------------------------------------------

create table Personal_Information
(
    ID                 int auto_increment not null primary key,
    CID               int not null,
    National_Insurance varchar(200),
    Ethnicity          varchar(200),
    Gender             varchar(200),
    Age                int,
    Sexuality          varchar(200),
    constraint PI_candidate_fk
        foreign key (CID) references candidates (ID)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `Vetting_Officers`
-- -----------------------------------------------------

create table Vetting_Officers
(
    ID         int auto_increment not null primary key,
    First_Name varchar(200) not null,
    Surname    varchar(200) not null,
    Email      varchar(200) not null unique,
    Password   varchar(200) not null unique
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `Candidate_References`
-- -----------------------------------------------------

create table Candidate_References
(
    ID             int  auto_increment not null primary key,
    CID           int not null,
    Referee        varchar(200) not null,
    Referee_Number int not null,
    constraint candidate_references__fk
        foreign key (CID) references candidates (ID)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `Applications`
-- -----------------------------------------------------

create table Applications
(
    ID         int auto_increment not null primary key,
    App_Status varchar(200) not null,
    CID       int not null,
    constraint applications_candidates_fk
        foreign key (CID) references candidates (ID)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `MasterAdmin`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Master_Admin
(
    ID            int auto_increment not null primary key,
    Username      varchar(200) not null unique,
    Password      varchar(200) not null unique
)
    ENGINE = INNODB;