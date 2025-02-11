SELECT 
	IF(GROUPING(c.category_name), 'TOTAL SUMMARY', category_name) AS 'Category', 
	IF(GROUPING(p.product_name), 'PRODUCT SUMMARY', product_name) AS 'Product', 
	COUNT(quantity) AS 'num'
FROM categories c
JOIN products p ON c.category_id = p.category_id
JOIN order_items oi ON p.product_id = oi.product_id
GROUP BY category_name, product_name WITH ROLLUP