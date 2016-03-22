CREATE DATABASE appreview;
CREATE USER 'dbuser'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON appreview.* TO 'dbuser'@'%';
FLUSH PRIVILEGES;