/*Write a SELECT statement that joins the Customers table to the Addresses table and returns these columns: first_name, last_name, line1, city, state, zip_code.
Return one row for each address for the customer with an email address of allan.sherwood@yahoo.com. Execute the query and take a screenshot of the query and the results.*/

SELECT first_name, last_name, line1, city, state, zip_code FROM customers c JOIN addresses a ON c.customer_id = a.customer_id WHERE c.email_address = 'allan.sherwood@yahoo.com'