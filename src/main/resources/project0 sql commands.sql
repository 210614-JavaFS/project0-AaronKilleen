
--DDL

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
	account_id NUMERIC(9,0) not null UNIQUE,
	balance DECIMAL(15,2),
	PRIMARY KEY (account_id)
);

CREATE TABLE users (
	user_name VARCHAR(30) not null UNIQUE,
	passwords VARCHAR(30),
	first_name VARCHAR(30),
	last_name VARCHAR(30),
	email VARCHAR(30),
	phone NUMERIC(10, 0),
	ssn NUMERIC(9, 0),
	street_address VARCHAR(30),
	city VARCHAR(30),
	state VARCHAR (2),
	zip NUMERIC(5,0),
	account_id NUMERIC(9,0),
	reg_approved BOOL,
	joint_approved BOOL,
	is_employee BOOL,
	is_admin BOOL,
	PRIMARY KEY (user_name),
	FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);





--DML

INSERT INTO accounts (account_id, balance)
	VALUES (123456789, 32543.21);

INSERT INTO users (user_name, passwords, first_name, last_name, email, phone, ssn, street_address, city, state, zip, account_id, reg_approved, joint_approved, is_employee, is_admin) 
	VALUES ('JohnQPublic', 'password', 'John', 'Public', 'john@customer.com', 1112223333, 111223333, '100 John Street', 'Public City', 'IN', 11111, 123456789, true, true, false, false);

INSERT INTO accounts (account_id, balance)
	VALUES (987654321, 11234.56);

INSERT INTO users (user_name, passwords, first_name, last_name, email, phone, ssn, street_address, city, state, zip, account_id, reg_approved, joint_approved, is_employee, is_admin) 
	VALUES ('JimCustomer', 'password', 'Jim', 'Customer', 'jim@customer.com', 9998887777, 999887777, '100 Jim Street', 'Customer City', 'IN', 22222, 987654321, true, true, false, false);

INSERT INTO accounts (account_id, balance)
	VALUES (0, 0.00);

INSERT INTO users (user_name, passwords, first_name, last_name, email, phone, ssn, street_address, city, state, zip, account_id, reg_approved, joint_approved, is_employee, is_admin) 
	VALUES ('TomTeller', 'password', 'Tom', 'Teller', 'tom@teller.com', 1111111111, 111111111, '100 Tom Street', 'Teller City', 'TN', 33333, 0, true, true, true, false);

INSERT INTO users (user_name, passwords, first_name, last_name, email, phone, ssn, street_address, city, state, zip, account_id, reg_approved, joint_approved, is_employee, is_admin) 
	VALUES ('AdamAdmin', 'password', 'Adam', 'Admin', 'adam@admin.com', 2222222222, 222222222, '100 Adam Street', 'Admin City', 'AZ', 44444, 0, true, true, true, true);



create or replace procedure transfer(
   sender int,
   receiver int, 
   amount DECIMAL(15,2)
)
language plpgsql    
as $$
begin
    -- subtracting the amount from the sender's account 
    update accounts 
    set balance = balance - amount 
    where account_id = sender;

    -- adding the amount to the receiver's account
    update accounts 
    set balance = balance + amount 
    where account_id = receiver;

    commit;
end;$$;


call transfer(123456789, 987654321, 1000.00);




--DQL


--TCL
