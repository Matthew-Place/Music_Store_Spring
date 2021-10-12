DROP TABLE IF EXISTS item CASCADE;
CREATE TABLE item
(
   id integer primary key auto_increment,
   category varchar (255),
   type varchar (255),
   instument varchar (255),
   brand varchar (255),
   name varchar (255),
   stock integer,
   store_id integer FOREIGN KEY REFERENCES store (id)
);