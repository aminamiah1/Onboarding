USE my_db;

INSERT INTO master_admin(Email, password)
VALUES('MADMIN@gmail.com', '$2a$12$6d5hHGhjB9bnQNe.ndjyo.VN6fPm9FVBAIF4.ae4GD3dkZP2MiBgS');

INSERT INTO candidates(ID, First_Name, Surname, Email, Password, Company_Name)
VALUES(1, 'Kerry', 'Malik', 'KMalik@gmail.com', '$2a$12$1bYa79yXePS4DdQiOtW8quDj2HMlzClpcHnAJdkRDlGe/vIYxvBB2', 'Admiral');

INSERT INTO candidates(ID, First_Name, Surname, Email, Password, Company_Name)
VALUES(2, 'Terry', 'Houston', 'TH@gmail.com', '$2a$12$KONcg/S5POa162hjUWH0I.p6SRfCGIy.7LCl8mDJC803rqsDTUcUS', 'Farmland');

INSERT INTO candidates(ID, First_Name, Surname, Email, Password, Company_Name)
VALUES(3, 'Molly', 'Jacobsen', 'MJ@gmail.com', '$2a$12$NOK89HvHvh3ou.XBvE7KreC74YFy8wKl35WXE68OMzEHXBnuQ.EaO', 'JCB');

INSERT INTO personal_information(CID, National_Insurance, Ethnicity, Gender, Age, Telephone_Number)
VALUES(1, 'QQ123456A', 'White', 'Woman', '48', '+441214960839');

INSERT INTO personal_information(CID, National_insurance, Ethnicity, Gender, Age, Telephone_number)
VALUES(2, 'QQ256225A', 'Asian', 'Man', '31', '+441164960799');

INSERT INTO personal_information(CID)
VALUES(3);

INSERT INTO vetting_officers(First_name, surname, email, password)
VALUES('Sarah', 'Radcliffe', 'SR@gmail.com', '$2a$12$u8rpeK5sRXjUuGxASxtHEeLIAqXtCLrosyjAh2sNHoo93ovm.8qZS');

INSERT INTO vetting_officers(First_name, surname, email, password)
VALUES('Emily', 'Clarke', 'EC@gmail.com','EmilyClarke212@');

INSERT INTO vetting_officers(First_name, surname, email, password)
VALUES('Khalid', 'Rahman', 'KR@gmail.com','MyPasswordIs1@');

INSERT INTO vetting_officers(First_name, surname, email, password)
VALUES('Amina', 'Miah', 'AminaMiah@gmail.com','Amina112@');

INSERT INTO candidate_references(ID, CID, Referee_Name, Referee_Phone_Number)
VALUES(1, 1, 'Angela Mooring', '+447975777666');

INSERT INTO candidate_references(ID, CID, Referee_Name, Referee_Phone_Number)
VALUES(2, 1, 'Brad Lewis', '+446532535353');

INSERT INTO candidate_references(ID, CID, Referee_Name, Referee_Phone_Number)
VALUES(3, 2, 'Katherine Hudson', '+442345666234');

INSERT INTO candidate_references(ID, CID, Referee_Name, Referee_Phone_Number)
VALUES(4, 2, 'Louise Johnson', '+443423454325');

INSERT INTO applications(ID, App_Status, CID)
VALUES(1, 'Ongoing', 1);