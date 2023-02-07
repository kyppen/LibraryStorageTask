DROP USER user1;
CREATE DATABASE mediaLibrary;
CREATE USER 'user1' IDENTIFIED BY 'pass';
GRANT ALL on medialibrary.* TO 'user1';