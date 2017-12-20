How to kill particuler port id
 => netstat -a -o -n
 taskkill /F /PID 28344

 
 Modify column and size :
 
 ALTER TABLE groceriesproduct.usershopkeeper MODIFY COLUMN email VARCHAR(100);

=> Add new column to existing Table :
      ALTER TABLE groceriesproduct.orderlist ADD Order_ID varchar(200) NULL AFTER Product_ID;
      
      
 
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
	contact VARCHAR(1010),
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
    code varchar(20),
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
 * Address Table for all user 
 * ***/

CREATE TABLE Address (
    ID INT NOT NULL AUTO_INCREMENT,
    user_ID VARCHAR(20),
    House_No VARCHAR(20),
    Locality VARCHAR(20),
    Landmark VARCHAR(20),
    PinCode VARCHAR(20),
    Area VARCHAR(10),
    City VARCHAR(10),
    CONSTRAINT pk_address_id PRIMARY KEY (ID),
    CONSTRAINT fk_UserOrder FOREIGN KEY (user_id)
    REFERENCES usershopkeeper(user_id)
);


CREATE TABLE groceriesproduct.Address (
    ID INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(20) NOT NULL,
    House_No VARCHAR(20),
    Locality VARCHAR(20),
    Landmark VARCHAR(20),
    PinCode VARCHAR(20),
    Area VARCHAR(10),
    City VARCHAR(10),
    CONSTRAINT pk_address_id PRIMARY KEY (ID),
       // FOREIGN KEY (id) REFERENCES usershopkeeper(id)
);
INSERT INTO `groceriesproduct`.`address` (`ID`, `user_id`, `House_No`, `Locality`, `Landmark`, `PinCode`, `Area`, `City`) 
VALUES ('1', 'Sadda', 'ee', 'ww', 'tt', '33', 'dd', 'rr');

/**
 * for the orderlist table
 * **/

CREATE TABLE orderList (
    ID INT NOT NULL AUTO_INCREMENT,
    code varchar(20),
    Shop_ID VARCHAR(20)NOT NULL,
    User_ID VARCHAR (20) NOT NULL,
    Product_ID VARCHAR(20) NOT NULL,
    Product_Name VARCHAR(20) ,
    Units VARCHAR(20) ,
    Qty VARCHAR(10),
    TimeStamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_order_id PRIMARY KEY (ID)

);


CREATE TABLE prices (
	
	ID INT NOT NULL AUTO_INCREMENT,
	Shop_ID VARCHAR(20)NOT NULL,
    Product_ID VARCHAR(20)NOT NULL,
	Price VARCHAR(400) NOT NULL,
    Qty_Price VARCHAR(400),
	
	CONSTRAINT pk_prices_id PRIMARY KEY (ID) 

);

Drop Table prices
