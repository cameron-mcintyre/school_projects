/*Write a SELECT statement that returns one row for each customer that has orders with these columns:
-The email_address column from the Customers table
-The sum of the item price in the Order_Items table multiplied by the quantity in the Order_Items table
-The sum of the discount amount column in the Order_Items table multiplied by the quantity in the Order_Items table
Sort the result set in descending sequence by the item price total for each customer.*/

SELECT email_address AS 'Customer_Email', SUM(item_price * quantity) AS 'Customer_Total_Sales', SUM(discount_amount * quantity) AS 'Customer_Total_Discounts'
FROM customers c
JOIN orders o ON o.customer_id = c.customer_id
JOIN order_items oi ON oi.order_id = o.order_id
GROUP BY email_address
ORDER BY Customer_Total_Sales DESC