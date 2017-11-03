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





CREATE TABLE  userjson (
	
	id IDENTITY,
	status_code VARCHAR(20),
	status_message  VARCHAR(20),
    is_active BOOLEAN,
	
	CONSTRAINT pk_category_id PRIMARY KEY (id) 

);

INSERT INTO USERJSON  (status_code,status_message) VALUES ('OK ', 'successfull');

