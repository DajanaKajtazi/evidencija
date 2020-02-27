CREATE USER IF NOT EXISTS jwduser IDENTIFIED BY 'pass';

DROP DATABASE IF EXISTS evidencija;
CREATE DATABASE evidencija DEFAULT CHARACTER SET utf8;

USE evidencija;

GRANT ALL ON evidencija.* TO 'jwduser'@'%';

FLUSH PRIVILEGES;