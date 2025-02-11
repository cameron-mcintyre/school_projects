CREATE USER 'cameronmcintyre'@'%' IDENTIFIED BY 'password';

GRANT SELECT, INSERT, UPDATE, DELETE ON my_guitar_shop.customers TO 'cameronmcintyre'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON my_guitar_shop.addresses TO 'cameronmcintyre'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON my_guitar_shop.orders TO 'cameronmcintyre'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON my_guitar_shop.order_items TO 'cameronmcintyre'@'%';
GRANT SELECT ON my_guitar_shop.products TO 'cameronmcintyre'@'%';
GRANT SELECT ON my_guitar_shop.categories TO 'cameronmcintyre'@'%';

REVOKE GRANT OPTION ON *.* FROM 'cameronmcintyre'@'%';

FLUSH PRIVILEGES;