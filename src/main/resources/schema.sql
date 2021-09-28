CREATE TABLE BANK_ACCOUNTS(
    number int auto_increment (1000) primary key not null,
    balance decimal (10, 2),
    version int
);

CREATE TABLE CARDS(
    card_id int not null auto_increment (1000),
    account_number int not null auto_increment (1000),
    version int,
    foreign key (account_number) references BANK_ACCOUNTS(number),
    primary key (card_id, account_number)
);