USE my_db;

INSERT INTO master_admin(username, password)
VALUES('MADMIN', 'MasterAdmin@123');

INSERT INTO candidates(ID, First_Name, Surname, Email, Password, Company_Name)
VALUES(1, 'Kerry', 'Malik', 'KMalik@gmail.com', '$2a$12$1bYa79yXePS4DdQiOtW8quDj2HMlzClpcHnAJdkRDlGe/vIYxvBB2', 'Admiral');

INSERT INTO candidates(ID, First_Name, Surname, Email, Password, Company_Name)
VALUES(2, 'Terry', 'Houston', 'TH@gmail.com', '$2a$12$KONcg/S5POa162hjUWH0I.p6SRfCGIy.7LCl8mDJC803rqsDTUcUS', 'Farmland');

INSERT INTO candidates(ID, First_Name, Surname, Email, Password, Company_Name)
VALUES(3, 'Molly', 'Jacobsen', 'MJ@gmail.com', '$2a$12$NOK89HvHvh3ou.XBvE7KreC74YFy8wKl35WXE68OMzEHXBnuQ.EaO', 'JCB');

INSERT INTO personal_information(CID, national_insurance, ethnicity, gender, age, sexuality)
VALUES(1, 'QQ123456A', 'White', 'Female', '48', 'heterosexual');

INSERT INTO personal_information(CID, national_insurance, ethnicity, gender, age, sexuality)
VALUES(2, 'QQ256225A', 'Asian', 'Male', '31', 'homosexual');

INSERT INTO personal_information(CID)
VALUES(3);

INSERT INTO vetting_officers(First_name, surname, email, password)
VALUES('Sarah', 'Radcliffe', 'SR@gmail.com', 'SR@YaDude123');

INSERT INTO candidate_references(ID, CID, Referee, Referee_Number)
VALUES(1, 1, 'Angela Mooring', '34232345');

INSERT INTO candidate_references(ID, CID, Referee, Referee_Number)
VALUES(2, 2, 'Katherine Hudson', '35235354');

INSERT INTO applications(ID, App_Status, CID)
VALUES(1, 'Ongoing', 1);