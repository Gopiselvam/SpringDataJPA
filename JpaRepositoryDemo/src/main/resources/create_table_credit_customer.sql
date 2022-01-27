CREATE TABLE CREDIT_CARD_CUSTOMERS_ADDRESS (
    addressId INT(11),
    city VARCHAR(20),
    pincode VARCHAR(6),
    PRIMARY KEY(addressId)
);

CREATE TABLE CREDIT_CARD_CUSTOMERS(
    customerId INT(11),
    active BOOLEAN,
    creditPoints INT(11),
    firstName VARCHAR(20),
    lastName VARCHAR(20),
    contactNumber VARCHAR(20),
    email VARCHAR(50),
    address_addressId INT(10),
    PRIMARY KEY(customerId),
    CONSTRAINT contactNumber_email_unique UNIQUE (email, contactNumber),
    CONSTRAINT fk_address FOREIGN KEY (address_addressId)
    REFERENCES CREDIT_CARD_CUSTOMERS_ADDRESS(addressId) ON DELETE CASCADE
);


