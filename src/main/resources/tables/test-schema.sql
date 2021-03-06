DROP TABLE IF EXISTS store CASCADE;
CREATE TABLE store
(
   id integer primary key auto_increment,
   manager varchar (255),
   address varchar (255),
   contact_number varchar (255)
);

DROP TABLE IF EXISTS item CASCADE;
CREATE TABLE item
(
   id integer primary key auto_increment,
   category varchar (255),
   type varchar (255),
   instrument varchar (255),
   brand varchar (255),
   name varchar (255),
   stock integer,
   price integer,
   store_id integer,
   FOREIGN KEY (store_id) REFERENCES store(id) ON DELETE CASCADE
);