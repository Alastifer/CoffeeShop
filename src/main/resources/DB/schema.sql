DROP TABLE IF EXISTS CoffeeGrades;
DROP TABLE IF EXISTS OrderElements;
DROP TABLE IF EXISTS Configurations;
DROP TABLE IF EXISTS Orders;

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

CREATE TABLE Orders (
	id INT AUTO_INCREMENT,
	customer_full_name VARCHAR(100) NOT NULL,
	customer_address VARCHAR(100) NOT NULL,
	total_cost INT NOT NULL,
	PRIMARY KEY (id)
);
