CREATE TABLE orders (id BIGINT IDENTITY(1,1) PRIMARY KEY, customer_id BIGINT, car_id BIGINT, status_id BIGINT);
CREATE TABLE customers (id BIGINT IDENTITY(1,1) PRIMARY KEY, name VARCHAR(100) NOT NULL);
CREATE TABLE cars (id BIGINT IDENTITY(1,1) PRIMARY KEY, model VARCHAR(100) NOT NULL, brand_id BIGINT);
CREATE TABLE brands (id BIGINT IDENTITY(1,1) PRIMARY KEY, name VARCHAR(100) NOT NULL);
CREATE TABLE orders_options (order_id BIGINT, option_id BIGINT);
CREATE TABLE options (id BIGINT IDENTITY(1,1) PRIMARY KEY, name VARCHAR(100) NOT NULL);
CREATE TABLE order_statuses (id BIGINT IDENTITY(1,1) PRIMARY KEY, code VARCHAR(100) NOT NULL, name VARCHAR(100) NOT NULL);