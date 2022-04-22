
CREATE TABLE IF NOT EXISTS  PRODUCT(
    ID SERIAL AUTO_INCREMENT PRIMARY KEY ,
    NAME varchar(255),
    PRICE double precision,
    REFERENCE varchar(255)
);

INSERT INTO PRODUCT (NAME, PRICE, REFERENCE) VALUES ('Iphone 11 PRO MAX', 7000, 'REF_IPHONE_11_PR_MAX');
INSERT INTO PRODUCT (NAME, PRICE, REFERENCE) VALUES ('IphoneX', 4000, 'REF_IPHONE_X');
INSERT INTO PRODUCT (NAME, PRICE, REFERENCE) VALUES ('Iphone 13', 12000, 'REF_IPHONE_13');
INSERT INTO PRODUCT (NAME, PRICE, REFERENCE) VALUES ('Iphone 12 PR', 10000, 'REF_IPHONE_12_PR');

