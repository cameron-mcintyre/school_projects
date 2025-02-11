SELECT
	category_name AS 'Category',
    product_id AS 'Product_ID'
FROM categories c
LEFT JOIN products p ON c.category_id = p.category_id
WHERE p.category_id IS NULL