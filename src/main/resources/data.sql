USE my_db;

INSERT INTO master_admin(Email, password)
VALUES('MADMIN@gmail.com', '$2a$12$6d5hHGhjB9bnQNe.ndjyo.VN6fPm9FVBAIF4.ae4GD3dkZP2MiBgS');

INSERT INTO candidates(ID, First_Name, Surname, Email, Password, Company_Name)
VALUES(1, 'Kerry', 'Malik', 'KMalik@gmail.com', '$2a$12$1bYa79yXePS4DdQiOtW8quDj2HMlzClpcHnAJdkRDlGe/vIYxvBB2', 'Admiral');

INSERT INTO candidates(ID, First_Name, Surname, Email, Password, Company_Name)
VALUES(2, 'Terry', 'Houston', 'TH@gmail.com', '$2a$12$KONcg/S5POa162hjUWH0I.p6SRfCGIy.7LCl8mDJC803rqsDTUcUS', 'Farmland');

INSERT INTO candidates(ID, First_Name, Surname, Email, Password, Company_Name)
VALUES(3, 'Molly', 'Jacobsen', 'MJ@gmail.com', '$2a$12$NOK89HvHvh3ou.XBvE7KreC74YFy8wKl35WXE68OMzEHXBnuQ.EaO', 'JCB');

INSERT INTO candidates(ID, First_Name, Surname, Email, Password, Company_Name)
VALUES(4, 'Sammy', 'Jones', 'SJ@gmail.com', '$2a$12$7jLnTNUHwOGPuc4PTxTOcOEKAyDfP/lxIuK7Qawt4ArkpzHWeFNnG', 'Lidl');

INSERT INTO candidates(ID, First_Name, Surname, Email, Password, Company_Name)
VALUES(5, 'Perry', 'Lee', 'PL@gmail.com', '$2a$12$vMez.THyAs.9S9NcL7hNE.ag7ffeyTgITKIr/h1moxSCrAGA11ndG', 'Ford');

INSERT INTO personal_information(CID, National_Insurance, Ethnicity, Gender, Age, Telephone_Number)
VALUES(1, 'QQ123456A', 'White', 'Woman', '48', '+441214960839');

INSERT INTO personal_information(CID, National_insurance, Ethnicity, Gender, Age, Telephone_number)
VALUES(2, 'QQ256225A', 'Asian', 'Man', '31', '+441164960799');

INSERT INTO personal_information(CID)
VALUES(3);

INSERT INTO personal_information(CID, National_Insurance, Ethnicity, Gender, Age, Telephone_Number)
VALUES(4, 'QQ464242A', 'White', 'Man', '60', '+445678745454');

INSERT INTO personal_information(CID, National_Insurance, Ethnicity, Gender, Age, Telephone_Number)
VALUES(5, 'QQ434542A', 'Black', 'Man', '35', '+442572525252');

INSERT INTO vetting_officers(ID, First_name, surname, email, password)
VALUES(1, ' Sarah', 'Radcliffe', 'SR@gmail.com', '$2a$12$u8rpeK5sRXjUuGxASxtHEeLIAqXtCLrosyjAh2sNHoo93ovm.8qZS');

INSERT INTO vetting_officers(ID, First_name, surname, email, password)
VALUES(2 ,  'Emily', 'Clarke', 'EC@gmail.com','$2a$12$EgU960k.T/srn/j/KCZcoO5OmGPEEZa262BY/L71tS5XSTZ/0yzZK');

INSERT INTO vetting_officers(ID, First_name, surname, email, password)
VALUES(3, 'Khalid', 'Rahman', 'KR@gmail.com','$2a$12$w/ZuaMPVz2pwMfALbJU51.fSfxaST3ukDFQs3XQ28GFrDmt1rk2tu');

INSERT INTO vetting_officers(ID, First_name, surname, email, password)
VALUES(4, 'Amina', 'Miah', 'AminaMiah@gmail.com','$2a$12$GKT35VCKiHlXPKqJBGkrWuufrzeoffUyEkabNm0rlA0w.X.ubWGDa');

INSERT INTO candidate_references(ID, CID, Referee_Name, Referee_Phone_Number)
VALUES(1, 1, 'Angela Mooring', '+447975777666');

INSERT INTO candidate_references(ID, CID, Referee_Name, Referee_Phone_Number)
VALUES(2, 1, 'Brad Lewis', '+446532535353');

INSERT INTO candidate_references(ID, CID, Referee_Name, Referee_Phone_Number)
VALUES(3, 2, 'Katherine Hudson', '+442345666234');

INSERT INTO candidate_references(ID, CID, Referee_Name, Referee_Phone_Number)
VALUES(4, 2, 'Louise Johnson', '+443423454325');

INSERT INTO candidate_references(ID, CID, Referee_Name, Referee_Phone_Number)
VALUES(5, 4, 'Michael Douglas', '+443453453455');

INSERT INTO candidate_references(ID, CID, Referee_Name, Referee_Phone_Number)
VALUES(6, 4, 'Yolanda Knight', '+444243434343');

INSERT INTO candidate_references(ID, CID, Referee_Name, Referee_Phone_Number)
VALUES(7, 4, 'Dereck Doherty', '+444653665335');

INSERT INTO applications(ID, App_Status, CID)
VALUES(1, 'Pending', 1);

INSERT INTO applications(ID, App_Status, CID)
VALUES(2, 'Approved', 2);

INSERT INTO applications(ID, App_Status, CID)
VALUES(3, 'Denied', 3);

INSERT INTO applications(ID, App_Status, CID)
VALUES(4, 'Pending', 4);

INSERT INTO applications(ID, App_Status, CID)
VALUES(5, 'Approved', 5);