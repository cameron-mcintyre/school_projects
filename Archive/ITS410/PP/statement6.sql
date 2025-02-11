SELECT email_address, COUNT(DISTINCT oi.product_id) AS 'Distinct_Products_Ordered'
FROM orders o
JOIN order_items oi ON o.order_id = oi.order_id
JOIN customers c ON o.customer_id = c.customer_id
GROUP BY c.email_address
HAVING COUNT(DISTINCT oi.product_id) > 1
ORDER BY email_address ASC