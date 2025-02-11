/*Write a SELECT statement that returns these columns:
-The count of the number of orders in the Orders table
-The sum of the tax_amount columns in the Orders table*/

SELECT COUNT(order_id) AS 'Number_Of_Orders', SUM(tax_amount) AS 'Total_Tax'
FROM orders