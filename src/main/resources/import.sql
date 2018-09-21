insert into customers(customer_id,  username, password, account_not_expired, account_not_locked, enabled) values (1 ,  '0123456789', '0123456789', true, true, true);
insert into accounts(account_id, balance, name_account, accounts_customer_id) values (5701234567, 300, 'test', 1);
insert into accounts(account_id, balance, name_account, accounts_customer_id) values (1001234568, 500, 'test', 1);
insert into user_role(user_id, roles) values (1, 'USER');