
DROP TABLE IF EXISTS Candidates;
DROP TABLE IF EXISTS Personal_Information;
DROP TABLE IF EXISTS Candidate_References;
DROP TABLE IF EXISTS Vetting_Officers;
DROP TABLE IF EXISTS Applications;
DROP TABLE IF EXISTS Master_Admin;

    -- -----------------------------------------------------
-- TABLE `Candidates`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Candidates
(
    ID            INTEGER AUTO_INCREMENT NOT NULL,
    First_Name    VARCHAR(200),
    Surname       VARCHAR(200),
    Email         VARCHAR(200) NOT NULL UNIQUE,
    Password      VARCHAR(200) NOT NULL UNIQUE,
    Company_Name  VARCHAR(200),
    PRIMARY KEY (`ID`)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `Personal_Information`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Personal_Information
(
    ID INTEGER AUTO_INCREMENT NOT NULL,
    C_ID INTEGER NOT NULL,
    National_Insurance INTEGER,
    Ethnicity VARCHAR(200),
    Gender VARCHAR(200),
    Age INTEGER,
    Sexuality VARCHAR(200),
    PRIMARY KEY (`ID`)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `Vetting_Officers`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Vetting_Officers
(
    ID            INTEGER AUTO_INCREMENT NOT NULL,
    First_Name    VARCHAR(200),
    Surname       VARCHAR(200),
    Email         VARCHAR(200) NOT NULL UNIQUE,
    Password      VARCHAR(200) NOT NULL UNIQUE,
    App_ID        INTEGER NOT NULL,
    PRIMARY KEY (`ID`)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `Candidate_References`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Candidate_References (
    ID INTEGER NOT NULL,
    C_ID INTEGER NOT NULL,
    Referee VARCHAR(200),
    Referee_Number INTEGER,
    PRIMARY KEY (`ID`)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `Applications`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Applications (
    ID INTEGER NOT NULL,
    App_Status VARCHAR(200),
    C_ID INTEGER NOT NULL,
    R_ID INTEGER NOT NULL,
    PRIMARY KEY (`ID`)
)
    ENGINE = INNODB;

-- -----------------------------------------------------
-- TABLE `MasterAdmin`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS Master_Admin
(
    ID            INTEGER AUTO_INCREMENT NOT NULL,
    Username        VARCHAR(200) NOT NULL UNIQUE,
    Password      VARCHAR(200) NOT NULL UNIQUE,
    PRIMARY KEY (`ID`)
)
    ENGINE = INNODB;