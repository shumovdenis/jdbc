create table Orders (
                        id int primary key auto_increment,
                        date DATETIME NOT NULL ,
                        customer_id int,
                        product_name varchar(255) not null ,
                        amount int check ( amount > 0 ),
                        FOREIGN KEY (customer_id) References Customers(id)
);