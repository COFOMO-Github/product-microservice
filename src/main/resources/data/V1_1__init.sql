
CREATE TABLE IF NOT EXISTS  PRODUCT(
    ID SERIAL AUTO_INCREMENT PRIMARY KEY ,
    NAME varchar(255),
    PRICE double precision,
    REFFRS varchar(255),
    REFPDT varchar(255)
);

INSERT INTO PRODUCT (NAME, PRICE, REFPDT, REFFRS) VALUES ('Iphone 11 PRO MAX', 7000,'REF_Iphone 11 PRO MAX', 'REF_APPLE_STORE_1');
INSERT INTO PRODUCT (NAME, PRICE, REFPDT, REFFRS) VALUES ('IphoneX', 4000,'REF_IphoneX', 'REF_APPLE_STORE_2');
INSERT INTO PRODUCT (NAME, PRICE, REFPDT, REFFRS) VALUES ('Iphone 13', 12000,'REF_Iphone 13', 'REF_APPLE_STORE_3');
INSERT INTO PRODUCT (NAME, PRICE, REFPDT, REFFRS) VALUES ('Iphone 12 PR', 10000,'REF_Iphone 12 PR', 'REF_APPLE_STORE_3');
INSERT INTO PRODUCT (NAME, PRICE, REFPDT, REFFRS) VALUES ('S20 plus ', 10000,'REF_S20 plus', 'REF_Samsung_STORE_1');
INSERT INTO PRODUCT (NAME, PRICE, REFPDT, REFFRS) VALUES ('S22 ', 10000,'REF_S22', 'REF_Samsung_STORE_3');

