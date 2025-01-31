/*Write a script that creates and calls a stored procedure named insert_category.*/

DELIMITER //

CREATE PROCEDURE insert_category()
BEGIN
SELECT 'This is a test' AS message;
END//

DELIMITER ;

CALL insert_category()