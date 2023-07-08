CREATE DATABASE ProjectPRJ301;
USE ProjectPRJ301;

CREATE TABLE Manufacturer (
	ID INT NOT NULL,
	Name NVARCHAR(50),
	Phone VARCHAR(10),
	Email VARCHAR(20),
	Address NVARCHAR(100),
);
ALTER TABLE Manufacturer ADD CONSTRAINT pk_ID PRIMARY KEY(ID);

CREATE TABLE Discount(
	id INT NOT NULL,
	name VARCHAR(20),
	description VARCHAR(50),
	discount_percent DECIMAL(3,2),
	active BIT, -- whether this discount is available or has been expire
);
ALTER TABLE Discount ADD CONSTRAINT pk_discountID PRIMARY KEY(id);

CREATE TABLE product_category(
	id INT NOT NULL,
	name VARCHAR(30),
	description VARCHAR(50)
);
ALTER TABLE product_category ADD CONSTRAINT pk_categoryID PRIMARY KEY(id);

CREATE TABLE Product (
	product_id INT NOT NULL,
	product_name VARCHAR(50),
	manufacturer_id INT NOT NULL,
	model_year INT,
	price DECIMAL(10, 2),
	quantity INt,
	category INT NOT NULL,
	status BIT, -- specify whether this product is available or has been deleted
	discount_id INT,
	description VARCHAR(200),
);
ALTER TABLE Product ADD CONSTRAINT pk_productID PRIMARY KEY(product_id);
ALTER TABLE Product ADD CONSTRAINT fk_manufacturer_id FOREIGN KEY(manufacturer_id) REFERENCES Manufacturer(ID);
ALTER TABLE Product ADD CONSTRAINT fk_discount_id FOREIGN KEY(discount_id) REFERENCES Discount(id);
ALTER TABLE Product ADD CONSTRAINT fk_categoryID FOREIGN KEY(category) REFERENCES product_category(id);

CREATE TABLE [User] (
	id INT NOT NULL,
	username VARCHAR(50) NOT NULL,
	fullName VARCHAR(50),
	email VARCHAR(50),
	phone VARCHAR(10),
	pwd_hashed BINARY(64) NOT NULL,
	salt BINARY(16) NOT NULL,
	city VARCHAR(50)
);
ALTER TABLE [User] ADD CONSTRAINT pk_userID PRIMARY KEY(id);

DROP TABLE [User];
DROP TABLE [UserRole];

DELETE FROM [User];
DELETE FROM UserRole;
DELETE FROM order_details;
DELETE FROM order_items;

CREATE TABLE Role (
	role_id INT NOT NULL,
	role_name VARCHAR(10),
	role_description VARCHAR(50)
);
ALTER TABLE Role ADD CONSTRAINT pk_roleID PRIMARY KEY(role_id);

CREATE TABLE UserRole (
	role_id INT NOT NULL,
	[user_id] INT NOT NULL
);
ALTER TABLE UserRole ADD CONSTRAINT pk_roleID_userID PRIMARY KEY(role_id, [user_id]);
ALTER TABLE UserRole ADD CONSTRAINT fk_roleID FOREIGN KEY(role_id) REFERENCES Role(role_id);
ALTER TABLE UserRole ADD CONSTRAINT fk_userID FOREIGN KEY([user_id]) REFERENCES [User](id);

--CREATE TABLE Stocks (
--	product_id INT NOT NULL,
--	quantity INT,
--);
--ALTER TABLE Stocks ADD CONSTRAINT pk_stock_productID PRIMARY KEY(product_id);
--ALTER TABLE Stocks ADD CONSTRAINT fk_productID FOREIGN KEY(product_id) REFERENCES Product(product_id);

CREATE TABLE order_details (
	order_id INT NOT NULL,
	[user_id] INT NOT NULL,
	order_status TINYINT NOT NULL,
	order_date DATETIME,
	total DECIMAL(10, 2)
);
ALTER TABLE order_details ADD CONSTRAINT pk_orderID	PRIMARY KEY(order_id);
ALTER TABLE order_details ADD CONSTRAINT fk_user_ID FOREIGN KEY([user_id]) REFERENCES [User](id);

CREATE TABLE order_items (
	id INT NOT NULL,
	order_id INT NOT NULL,
	product_id INT NOT NULL,
	quantity INT,
);
ALTER TABLE order_items ADD CONSTRAINT pk_orderItemID PRIMARY KEY(id);
ALTER TABLE order_items ADD CONSTRAINT fk_orderID FOREIGN KEY(order_id) REFERENCES order_details(order_id);
ALTER TABLE order_items ADD CONSTRAINT fk_prodID FOREIGN KEY(product_id) REFERENCES Product(product_id);

--DROP TABLE Product;
--DROP TABLE Stocks;
--ALTER TABLE Product DROP CONSTRAINT fk_manufacturer_id;
--ALTER TABLE Stocks DROP CONSTRAINT fk_productID;

INSERT INTO [User](id, username, email, phone, pwd_hashed, city) VALUES
--(1, 'haquangthang', 'haquangthangvnn@gmail.com', '0382721058', '123456', 'Thai Nguyen'),
--(2, 'admin', 'admin@root.com', '0123456789', '123456', 'X'),
(3, 'gideon', 'gideon@gmail.com', '0382721058', '123456', '?');

--INSERT INTO Role(role_id, role_name, role_description) VALUES
--(1, 'user', 'Normal user'),
--(2, 'admin', 'Super user');

INSERT INTO UserRole(role_id, [user_id]) VALUES
--(1, 1),
--(2, 2),
(2, 1);

SELECT * FROM [User];
SELECT * FROM [Role];
SELECT * FROM [UserRole];
SELECT * FROM Manufacturer;
SELECT * FROM product_category;
SELECT * FROM Discount;
SELECT * FROM Product;
SELECT * FROM order_details;
SELECT * FROM order_items;

--SELECT order_id, user_id, order_status, order_date, total FROM order_details
--WHERE order_id = 1;

--DELETE FROM [order_details]
--      WHERE order_id = 1;


--SELECT id FROM [User] WHERE username = 'admin    ' AND pwd_hashed = '123456   ';
--SELECT * FROM [User] WHERE id = ?;

INSERT INTO Manufacturer(ID, Name, Phone, Email, Address) VALUES
--(1, 'Apple', '1111', 'apple-inc@apple.com', 'LA-USA'),
--(2, 'HP', '1000', 'hp-inc@hp.com', 'USA'),
--(3, 'Dell', '1001', 'dell@corp.com', 'China');
(4, 'Realme', '1002', 'realme@corp.com', 'China'),
(5, 'Samsung', '1003', 'samsung@inc.com', 'Korea');

--UPDATE [Product]
--   SET [product_id] = <product_id, int,>
--      ,[product_name] = <product_name, varchar(50),>
--      ,[manufacturer_id] = <manufacturer_id, int,>
--      ,[model_year] = <model_year, int,>
--      ,[price] = <price, decimal(10,2),>
--      ,[quantity] = <quantity, int,>
--      ,[category] = <category, int,>
--      ,[status] = <status, bit,>
--      ,[discount_id] = <discount_id, int,>
--      ,[description] = <description, varchar(200),>
-- WHERE <Search Conditions,,>

INSERT INTO product_category(id, name, description) VALUES
(1, 'Mobile', 'Dien thoai di dong'),
(2, 'Laptop', 'May tinh'),
(3, 'StorageDevice', 'HDD/SSD/USB/MicroUSB');

INSERT INTO Discount(id, name, description, discount_percent, active) VALUES
--(1, 'Gia goc', 'Khong co khuyen mai', 0, 1),
(2, 'Khuyen mai 30/4', 'Giam gia 50%', 0.5, 1);

INSERT INTO Product(product_id, product_name, manufacturer_id, model_year, price, quantity, category, status, discount_id, description) VALUES
(1, 'Iphone 15 Pro Max', 1, 2022, 1499, 100, 1, 1, 1, 'Dien thoai xin'),
(2, 'Iphone 14 Pro Max', 1, 2021, 1490, 1, 1, 1, 1, 'Dien thoai xin'),
(3, 'Iphone 13 Pro Max', 1, 2020, 1399, 1, 1, 1, 1, 'Dien thoai xin'),
(4, 'Iphone 12 Pro Max', 1, 2019, 1299, 1, 1, 1, 1, 'Dien thoai xin'),
(5, 'Samsung Galaxy Note 7', 5, 2017, 1199, 1, 1, 1, 1, 'Dien thoai xin'),
(6, 'Realme 5i', 4, 2020, 200, 1, 1, 1, 1, 'Dien thoai xin'),
(7, 'HP Envy Ultrabook', 2, 2022, 1700, 1, 2, 1, 1, 'May tinh xin'),
(8, 'Neo X10', 4, 2022, 599, 1, 1, 1, 1, 'Dien thoai xin'),
(9, 'Dell XPS', 3, 2023, 1900, 1, 2, 1, 1, 'May tinh xin'),
(10, 'Dell Lattitude 5480', 3, 2017, 600, 2, 2, 1, 1, 'May tinh da su dung');

SELECT *
FROM Product
ORDER BY product_id
OFFSET 0 ROWS
FETCH NEXT 2 ROWS ONLY;

SELECT b.role_id FROM 
[User] a JOIN UserRole b ON a.id = b.user_id 
WHERE username LIKE 'admin';

SELECT * FROM order_items
SELECT TOP(1) id FROM order_items ORDER BY id DESC;

SELECT a.product_id, b.*
FROM Product a JOIN Discount b ON a.discount_id = b.id;

SELECT * FROM Product WHERE product_name LIKE 'Neo 10';

SELECT b.role_id
FROM [User] a JOIN UserRole b ON a.id = b.user_id;

ALTER TABLE Product ADD CONSTRAINT default_img_path DEFAULT 'https://dummyimage.com/600x400/55595c/fff' FOR img_path;
ALTER TABLE Product ADD CONSTRAINT default_status DEFAULT 1 FOR status;
