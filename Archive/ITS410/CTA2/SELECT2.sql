/*Write a SELECT statement that returns one column from the Customers table named full_name that joins the last_name and first_name columns.
Format this column with the last name, a comma, a space, and the first name like this:

     Doe, John

Sort the result set by the last_name column in ascending sequence.
Return only the customers whose last names begin with letters from M to Z. Execute the query and take a screenshot of the query and the results.
NOTE: When comparing strings of characters, ‘M’ comes before any string of characters that begins with ‘M’. For example, ‘M’ comes before ‘Murach’.*/

SELECT CONCAT(last_name, ', ', first_name) AS full_name FROM customers /*WHERE last_name > 'M'*/ ORDER BY full_name asc