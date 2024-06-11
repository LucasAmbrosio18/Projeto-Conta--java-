create schema bdfacul;

create table USERS(
    id int not null auto_increment,
    name varchar(100) NOT NULL,
    phone varchar(15) not NULL,
    email varchar(100) not NULL,
    password varchar(100) NOT NULL,
    username varchar(100) not null,
    isadm boolean not null,
    PRIMARY KEY (id)
);

create table BILLS(
    id int not null auto_increment,
    cost double not null,
    date varchar (10) not null,
    PRIMARY KEY (id)
);

create table FEEDBACKS(
    id int not null auto_increment,
    comment varchar(100) not null,
    user_id int not null,
    bill_id int not null,
    PRIMARY KEY (id),
    constraint fk_userid foreign KEY (user_id) references users(id),
    constraint fk_billid foreign KEY (bill_id) references bills(id)
);

INSERT INTO USERS  
      (USERNAME, EMAIL, NAME, PASSWORD, PHONE, ISADM)  
      VALUES ('admin', 'admin', 'admin', 'admin','admin', true);



