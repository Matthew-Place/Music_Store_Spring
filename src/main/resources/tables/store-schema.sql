DROP TABLE IF EXISTS store CASCADE;
CREATE TABLE store
(
   id integer primary key auto_increment,
   manager varchar (255),
   address varchar (255),
   contact_number varchar (255)
);