SELECT 
    p.product_name AS 'Product_1',
    q.product_name AS 'Product_2', 
    p.list_price AS 'Product1_price',
    q.list_price AS 'Product2_price'
FROM products p
JOIN products q
WHERE p.product_id != q.product_id AND p.list_price = q.list_price