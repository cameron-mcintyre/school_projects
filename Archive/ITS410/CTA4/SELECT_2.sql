/*Write an UPDATE statement that modifies the row you just added to the Categories table. This statement should change the category_name column to “Woodwinds,” and it should use the category_id column to identify the row. Execute the query and take a screenshot of the query and the results.*/

UPDATE categories SET category_name = 'Woodwinds' WHERE category_id = 7;

SELECT * FROM categories