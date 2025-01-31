/*First, code a statement that creates a procedure that adds a new row to the Categories table. To do that, this procedure should have one parameter for the category name.*/

DELIMITER //

CREATE PROCEDURE insert_category(new_category_param VARCHAR(10))
BEGIN
INSERT INTO categories (category_id, category_name) VALUES (NULL, new_category_param);
END//

DELIMITER ;