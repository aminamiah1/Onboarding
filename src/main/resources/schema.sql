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
    First_Name    varchar(100) not null,
    Surname       varchar(100) not null,
    Email         varchar(200) not null unique,
    Password      varchar(100) not null unique,
    Company_Name  varchar(100) not null,
    constraint candidateEmailAndPasswordLengthOver1CharacterCheck
        check(LENGTH(Email) > 1 AND LENGTH(Password) > 1)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `Personal_Information`
-- -----------------------------------------------------

create table Personal_Information
(
    ID                 int auto_increment not null primary key,
    CID               int not null,
    National_Insurance varchar(9) unique,
    Ethnicity          varchar(50),
    Gender             varchar(50),
    Age                int,
    Sexuality          varchar(50),
    constraint PI_candidate_fk
        foreign key (CID) references candidates (ID),
    constraint personalInfoAgeBetween18And120Check
        check(Age > 18 AND Age < 120)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `Vetting_Officers`
-- -----------------------------------------------------

create table Vetting_Officers
(
    ID         int auto_increment not null primary key,
    First_Name varchar(100) not null,
    Surname    varchar(100) not null,
    Email      varchar(200) not null unique,
    Password   varchar(100) not null,
    constraint vettingOfficerEmailAndPasswordLengthOver1CharacterCheck
        check(LENGTH(Email) > 1 AND LENGTH(Password) > 1)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `Candidate_References`
-- -----------------------------------------------------

create table Candidate_References
(
    ID             int  auto_increment not null primary key,
    CID           int not null,
    Referee_Name        varchar(100) not null,
    Referee_Phone_Number text not null unique,
    constraint refereePhoneNumberFormatCheck
        check(LENGTH(Referee_Phone_Number) > 9 AND LENGTH(Referee_Phone_Number) <= 13),
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
    App_Status varchar(100) not null,
    CID       int not null,
    constraint appStatusLength
        check(LENGTH(App_Status) > 1 AND LENGTH(App_Status) < 30),
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
    Email         varchar(200) not null unique,
    Password      varchar(100) not null,
    constraint masterAdminEmailAndPasswordLengthOver1CharacterCheck
        check(LENGTH(Email) > 1 AND LENGTH(Password) > 1)
)
    ENGINE = INNODB;