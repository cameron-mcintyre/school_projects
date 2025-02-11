/*Write a SELECT statement that returns one row for each customer that has orders with these columns:
-The email_address column from the Customers table
-A count of the number of orders
-The total amount for each order (Hint: First, subtract the discount amount from the price. Then, multiply by the quantity.)
Return only those rows where the customer has more than one order. Sort the result set in descending sequence by the sum of the line item amounts.*/

SELECT 
    email_address AS 'Customer',
    COUNT(o.order_id) AS 'Orders',
    SUM((item_price - discount_amount) * quantity) AS 'Total_Order_Amount'
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
JOIN order_items oi ON o.order_id = oi.order_id
GROUP BY email_address
HAVING COUNT(o.order_id) > 1
ORDER BY Total_Order_Amount DESC