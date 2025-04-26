-- Create Geo table
CREATE TABLE geo (
    id SERIAL PRIMARY KEY,
    lat VARCHAR(50) NOT NULL,
    lng VARCHAR(50) NOT NULL
);

-- Create Company table
CREATE TABLE company (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    catch_phrase VARCHAR(255) NOT NULL,
    bs VARCHAR(255) NOT NULL
);

-- Create Address table
CREATE TABLE address (
    id SERIAL PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    suite VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    zipcode VARCHAR(50) NOT NULL,
    geo_id INT NOT NULL REFERENCES geo(id)
);

-- Create Users table
CREATE TABLE users (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(100) NOT NULL,
    website VARCHAR(255) NOT NULL,
    address_id INT NOT NULL REFERENCES address(id),
    company_id INT NOT NULL REFERENCES company(id)
);

-- Insert Geo data
INSERT INTO geo (lat, lng) VALUES
('-37.3159', '81.1496'), -- 1
('-43.9509', '-34.4618'), -- 2
('-68.6102', '-47.0653'), -- 3
('29.4572', '-164.2990'), -- 4
('-31.8129', '62.5342'), -- 5
('-71.4197', '71.7478'), -- 6
('24.8918', '21.8984'), -- 7
('-14.3990', '-120.7677'), -- 8
('24.6463', '-168.8889'), -- 9
('-38.2386', '57.2232'); -- 10

-- Insert Company data
INSERT INTO company (name, catch_phrase, bs) VALUES
('Romaguera-Crona', 'Multi-layered client-server neural-net', 'harness real-time e-markets'), -- 1
('Deckow-Crist', 'Proactive didactic contingency', 'synergize scalable supply-chains'), -- 2
('Romaguera-Jacobson', 'Face to face bifurcated interface', 'e-enable strategic applications'), -- 3
('Robel-Corkery', 'Multi-tiered zero tolerance productivity', 'transition cutting-edge web services'), -- 4
('Keebler LLC', 'User-centric fault-tolerant solution', 'revolutionize end-to-end systems'), -- 5
('Considine-Lockman', 'Synchronised bottom-line interface', 'e-enable innovative applications'), -- 6
('Johns Group', 'Configurable multimedia task-force', 'generate enterprise e-tailers'), -- 7
('Abernathy Group', 'Implemented secondary concept', 'e-enable extensible e-tailers'), -- 8
('Yost and Sons', 'Switchable contextually-based project', 'aggregate real-time technologies'), -- 9
('Hoeger LLC', 'Centralized empowering task-force', 'target end-to-end models'); -- 10

-- Insert Address data
INSERT INTO address (street, suite, city, zipcode, geo_id) VALUES
('Kulas Light', 'Apt. 556', 'Gwenborough', '92998-3874', 1),
('Victor Plains', 'Suite 879', 'Wisokyburgh', '90566-7771', 2),
('Douglas Extension', 'Suite 847', 'McKenziehaven', '59590-4157', 3),
('Hoeger Mall', 'Apt. 692', 'South Elvis', '53919-4257', 4),
('Skiles Walks', 'Suite 351', 'Roscoeview', '33263', 5),
('Norberto Crossing', 'Apt. 950', 'South Christy', '23505-1337', 6),
('Rex Trail', 'Suite 280', 'Howemouth', '58804-1099', 7),
('Ellsworth Summit', 'Suite 729', 'Aliyaview', '45169', 8),
('Dayna Park', 'Suite 449', 'Bartholomebury', '76495-3109', 9),
('Kattie Turnpike', 'Suite 198', 'Lebsackbury', '31428-2261', 10);

-- Insert Users data
INSERT INTO users (id, name, username, email, phone, website, address_id, company_id) VALUES
(1, 'Leanne Graham', 'Bret', 'Sincere@april.biz', '1-770-736-8031 x56442', 'hildegard.org', 1, 1),
(2, 'Ervin Howell', 'Antonette', 'Shanna@melissa.tv', '010-692-6593 x09125', 'anastasia.net', 2, 2),
(3, 'Clementine Bauch', 'Samantha', 'Nathan@yesenia.net', '1-463-123-4447', 'ramiro.info', 3, 3),
(4, 'Patricia Lebsack', 'Karianne', 'Julianne.OConner@kory.org', '493-170-9623 x156', 'kale.biz', 4, 4),
(5, 'Chelsey Dietrich', 'Kamren', 'Lucio_Hettinger@annie.ca', '(254)954-1289', 'demarco.info', 5, 5),
(6, 'Mrs. Dennis Schulist', 'Leopoldo_Corkery', 'Karley_Dach@jasper.info', '1-477-935-8478 x6430', 'ola.org', 6, 6),
(7, 'Kurtis Weissnat', 'Elwyn.Skiles', 'Telly.Hoeger@billy.biz', '210.067.6132', 'elvis.io', 7, 7),
(8, 'Nicholas Runolfsdottir V', 'Maxime_Nienow', 'Sherwood@rosamond.me', '586.493.6943 x140', 'jacynthe.com', 8, 8),
(9, 'Glenna Reichert', 'Delphine', 'Chaim_McDermott@dana.io', '(775)976-6794 x41206', 'conrad.com', 9, 9),
(10, 'Clementina DuBuque', 'Moriah.Stanton', 'Rey.Padberg@karina.biz', '024-648-3804', 'ambrose.net', 10, 10);
