create table CONFIGURATIONS (
    KEY varchar(100) not null,
    VALUE varchar(200) not null,
    PRIMARY KEY (KEY)
);

create table GATEWAYS (
	UUID uuid not null,
	NAME varchar(100) not null,
    DESCRIPTION varchar(200) not null,
    CREATED timestamp not null,
    UPDATED timestamp not null,
    PRIMARY KEY (UUID),
    UNIQUE (NAME)
);

create table GATEWAY_CONFIGURATIONS (
    GATEWAY_FK uuid not null,
    UUID uuid not null,
    KEY varchar(100) not null,
    VALUE varchar(200) not null,
    PRIMARY KEY (UUID),
    PRIMARY KEY (GATEWAY_FK, KEY),
    FOREIGN KEY (GATEWAY_FK) REFERENCES GATEWAYS (UUID)
);

create table PAYMENTS (
    ID uuid not null,
    GATEWAY_FK uuid not null,
    
    ORDER_NUMBER varchar(100) not null,
    ORDER_DESCRIPTION varchar(255) not null,
    AMOUNT decimal not null,
    CURRENCY varchar(4) not null,
    PAYMENT_TYPE varchar(22) not null,
    CHECKOUT_ID varchar(100) not null,
    CHECKOUT_TIME timestamp not null,
    PAYMENT_ID varchar(100),
    PAYMENT_TIME timestamp,
    PAYMENT_STATUS_CODE varchar (100),
    PAYMENT_STATUS_DESCRIPTION varchar(255),
    PAYMENT_USER_ID uuid,
    PRIMARY KEY (ID),
    FOREIGN KEY (GATEWAY_FK) REFERENCES GATEWAYS (UUID)
);

create extension "uuid-ossp";

INSERT INTO CONFIGURATIONS(KEY, VALUE) VALUES ('checkout_base_url', 'http://localhost:4200/checkout');

INSERT INTO GATEWAYS(UUID, NAME, DESCRIPTION, CREATED, UPDATED ) VALUES (uuid_generate_v4(), 'SIBS', 'Gateway de Pagamento Europeu', now(), now());

INSERT INTO GATEWAY_CONFIGURATIONS(UUID, KEY, VALUE, GATEWAY_FK ) VALUES (uuid_generate_v4(), 'base_url', 'https://test.oppwa.com', ( SELECT UUID FROM GATEWAYS WHERE NAME = 'SIBS'));
INSERT INTO GATEWAY_CONFIGURATIONS(UUID, KEY, VALUE, GATEWAY_FK ) VALUES (uuid_generate_v4(), 'entity_id', '8a8294185332bbe601533754724914d9', ( SELECT UUID FROM GATEWAYS WHERE NAME = 'SIBS'));
INSERT INTO GATEWAY_CONFIGURATIONS(UUID, KEY, VALUE, GATEWAY_FK ) VALUES (uuid_generate_v4(), 'token', 'OGE4Mjk0MTg1MzMyYmJlNjAxNTMzNzU0NzZjMzE1Mjd8RzV3UDVUekY1aw==', ( SELECT UUID FROM GATEWAYS WHERE NAME = 'SIBS'));


