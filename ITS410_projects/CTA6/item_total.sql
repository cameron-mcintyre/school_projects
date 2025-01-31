/*Write a script that creates and calls a stored function named item_total that calculates the total amount of an item in the Order_Items table (discount price multiplied by quantity). To do that, this function should accept one parameter for the item ID, it should use the discount_price function that you created in exercise 2, and it should return the value of the total for that item. Execute the query and take a screenshot of the query and the results.*/

DELIMITER //

CREATE FUNCTION item_total (item_id_param INT)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN

DECLARE total_profit DECIMAL(10,2);
SELECT (item_price - discount_amount) * quantity INTO total_profit FROM order_items
WHERE item_id = item_id_param;

RETURN total_profit;

END //

DELIMITER ;
