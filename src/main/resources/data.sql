USE my_db;

INSERT INTO master_admin(username, password)
VALUES('MADMIN', 'MasterAdmin@123');

INSERT INTO candidates(ID, First_Name, Surname, Email, Password, Company_Name)
VALUES(1, 'Kerry', 'Malik', 'KMalik@gmail.com', 'IamKerry@123', 'Admiral');

INSERT INTO personal_information(c_id, national_insurance, ethnicity, gender, age, sexuality)
VALUES(1, 'QQ123456A', 'White', 'Female', '48', 'heterosexual');

INSERT INTO vetting_officers(First_name, surname, email, password)
VALUES('Sarah', 'Radcliffe', 'SR@gmail.com', 'SR@YaDude123');

INSERT INTO candidate_references(ID, C_ID, Referee, Referee_Number)
VALUES(1, 1, 'Angela Mooring', '34232345' );

INSERT INTO applications(ID, App_Status, C_ID)
VALUES(1, 'Ongoing', 1);