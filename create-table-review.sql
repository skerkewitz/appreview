CREATE TABLE review
(
    id INT(11) PRIMARY KEY NOT NULL,
    appStoreId INT(11),
    countryCode INT(11),
    date DATETIME,
    author VARCHAR(128),
    rating TINYINT(4),
    version VARCHAR(32),
    title VARCHAR(256),
    text VARCHAR(1028)
);