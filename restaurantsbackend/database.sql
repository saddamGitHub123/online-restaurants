
Create database groceriesproduct;

//For H2 DATABASE
CREATE TABLE usershopkeeper (
	
	id IDENTITY,
	shop_id VARCHAR(20),
	user_id VARCHAR(20),
	name VARCHAR(50),
	username VARCHAR(20),
	password VARCHAR(20),
	contact VARCHAR(20),
	email VARCHAR(20),
	is_active BOOLEAN,
	
	CONSTRAINT pk_category_id PRIMARY KEY (id) 

);
//For MySQL

CREATE TABLE usershopkeeper (
	
	id INT NOT NULL AUTO_INCREMENT,
	shop_id VARCHAR(20),
	user_id VARCHAR(20),
	name VARCHAR(50),
	username VARCHAR(20),
	password VARCHAR(20),
	contact VARCHAR(20),
	email VARCHAR(20),
	is_active BOOLEAN,
	
	CONSTRAINT pk_category_id PRIMARY KEY (id) 

);
INSERT INTO usershopkeeper (shop_id, user_id, name, username, password, contact,email,is_active)
VALUES ('shop001', 'Sadda','sad', 'shop001','sad', '720','s@',true);

INSERT INTO usershopkeeper (shop_id, user_id, name, username, password, contact,email,is_active)
VALUES ('shop002', 'user1','sad', 'TouchCor1','touchcor1', '123','s@',true);

INSERT INTO usershopkeeper (shop_id, user_id, name, username, password, contact,email,is_active)
VALUES ('shop003', 'user2', 'sad','TouchCor2','touchcor2', '1234','s@',true);

INSERT INTO usershopkeeper (shop_id, user_id, name, username, password, contact,email,is_active)
VALUES ('shop004', 'user3','sad', 'TouchCor3','123', '720','s@',true);

INSERT INTO usershopkeeper (shop_id, user_id, name, username, password, contact,email,is_active)
VALUES ('shop005', 'user4','sad', 'TouchCor4','1234', '720','s@',true);

INSERT INTO usershopkeeper (shop_id, user_id, name, username, password, contact,email,is_active)
VALUES ('shop006', 'user5','sad', 'TouchCor5','12345', '720','s@',true);



CREATE TABLE Productlist (
    ID INT NOT NULL AUTO_INCREMENT,
    Shop_ID VARCHAR(20)NOT NULL,
    Product_ID VARCHAR(20)NOT NULL,
    Product_Name VARCHAR(20),
    Product_Price int,
    Product_Image VARCHAR(20),
    Product_Category VARCHAR(20),
    Product_Type VARCHAR(20),
    Availability BOOLEAN,
    CONSTRAINT pk_product_id PRIMARY KEY (ID),
    CONSTRAINT pk_Product UNIQUE (Shop_ID,Product_ID)

);

INSERT INTO Productlist (Shop_ID, Product_ID, Product_Name, Product_Price, Product_Image, Product_Category,Product_Type,Availability)
VALUES ('shop001', 'product001', 'apple', 300,'image_1', 'grocery','L_01',true);

INSERT INTO Productlist (Shop_ID, Product_ID, Product_Name, Product_Price, Product_Image, Product_Category,Product_Type,Availability)
VALUES ('shop002', 'product002', 'Apple1', 301,'image_2', 'Grocery','L_02',true);

INSERT INTO Productlist (Shop_ID, Product_ID, Product_Name, Product_Price, Product_Image, Product_Category,Product_Type,Availability)
VALUES ('shop003', 'product003', 'apple3', 303,'image_3', 'grocery3','L_03',true);

INSERT INTO Productlist (Shop_ID, Product_ID, Product_Name, Product_Price, Product_Image, Product_Category,Product_Type,Availability)
VALUES ('shop', 'product', 'Apple1', 301,'image_2', 'Grocery','L_02',true);

INSERT INTO Productlist (Shop_ID, Product_ID, Product_Name, Product_Price, Product_Image, Product_Category,Product_Type,Availability)
VALUES ('shopp', 'productt', 'apple3', 303,'image_3', 'grocery3','L_03',true);




CREATE TABLE  userjson (
	
	id IDENTITY,
	status_code VARCHAR(20),
	status_message  VARCHAR(20),
    is_active BOOLEAN,
	
	CONSTRAINT pk_category_id PRIMARY KEY (id) 

);

INSERT INTO USERJSON  (status_code,status_message) VALUES ('OK ', 'successfull');


/**
 * for the orderlist table
 * **/

CREATE TABLE OrderList (
    ID INT NOT NULL AUTO_INCREMENT,
    Shop_ID VARCHAR(20)NOT NULL,
    User_ID VARCHAR (20) NOT NULL,
    Ordered_List VARCHAR(MAX),
    Address VARCHAR(200),
    TimeStamp TIMESTAMP()
    CONSTRAINT pk_order_id PRIMARY KEY (ID),

);

