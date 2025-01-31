/*Write a script that creates and calls a stored function named discount_price that calculates the discount price of an item in the Order_Items table (discount amount subtracted from item price). To do that, this function should accept one parameter for the item ID, and it should return the value of the discount price for that item. Execute the query and take a screenshot of the query and the results.*/

DELIMITER //

CREATE FUNCTION discount_price_func(item_id_param INT)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN

DECLARE discounted_price DECIMAL(10,2);
SELECT item_price - discount_amount INTO discounted_price FROM order_items
WHERE item_id = item_id_param;

RETURN discounted_price;

END//

DELIMITER ;