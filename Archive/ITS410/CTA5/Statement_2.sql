/*Write a SELECT statement that returns one row for each category that has products with these columns:
-The category_name column from the Categories table
-The count of the products in the Products table
-The list price of the most expensive product in the Products table.
Sort the result set so the category with the most products appears first.*/

SELECT 
    category_name AS 'Instrument_Category',
    COUNT(product_id) AS 'Product_Number_In_Category',
    MAX(list_price) AS 'Highest_Value_Item'
FROM categories c
JOIN products p ON c.category_id = p.category_id
GROUP BY category_name
ORDER BY Product_Number_In_Category DESC