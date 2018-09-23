insert into customers(customer_id,  username, password, account_not_expired, account_not_locked, enabled) values (1 ,  '0123456789', '0123456789', true, true, true);
insert into customers(customer_id,  username, password, account_not_expired, account_not_locked, enabled) values (2 ,  '1234567890', '1234567890', true, true, true);
insert into accounts(account_id, balance, name_account, accounts_customer_id) values (5711234567, 300, 'test1', 1);
insert into accounts(account_id, balance, name_account, accounts_customer_id) values (5711234568, 500, 'test2', 1);
insert into accounts(account_id, balance, name_account, accounts_customer_id) values (5711234569, 300, 'test3', 2);
insert into accounts(account_id, balance, name_account, accounts_customer_id) values (5711234510, 500, 'test4', 2);
insert into user_role(user_id, roles) values (1, 'USER');