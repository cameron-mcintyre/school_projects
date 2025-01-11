/*Write a SELECT statement that joins the Customers, Orders, Order_Items, and Products tables. This statement should return these columns: last_name, first_name, order_date, product_name, item_price, discount_amount, and quantity.
Use aliases for the tables.

Sort the final result set by the last_name, order_date, and product_name columns. Execute the query and take a screenshot of the query and the results.*/

SELECT last_name, first_name, order_date, product_name, item_price, discount_amount, quantity FROM customers c JOIN orders o ON c.customer_id = o.customer_id JOIN order_items oi ON o.order_id = oi.order_id JOIN products p ON p.product_id = oi.product_id ORDER BY last_name, order_date, product_name ASC