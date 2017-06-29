DROP TABLE CoffeeGrades IF EXISTS;
DROP TABLE OrderElements IF EXISTS;
DROP TABLE Configurations IF EXISTS;
DROP TABLE Orders IF EXISTS;

CREATE TABLE CoffeeGrades (
	id INT AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	cost INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE OrderElements (
	id INT AUTO_INCREMENT,
	order_id INT NOT NULL,
	coffee_id INT NOT NULL,
	amount_of_coffee INT NOT NULL,
	total_cost INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE Configurations (
	order_id INT
);

CREATE TABLE Orders (
	id INT NOT NULL,
	customer_full_name VARCHAR(100) NOT NULL,
	customer_address VARCHAR(100) NOT NULL,
	total_cost INT NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO Configurations VALUE (1);
