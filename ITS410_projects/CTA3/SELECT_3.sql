/*Write a SELECT statement that joins the Customers table to the Addresses table and returns these columns: first_name, last_name, line1, city, state, zip_code.
Return one row for each customer, but only return addresses that are the shipping address for a customer. Execute the query and take a screenshot of the query and the results.*/

SELECT first_name, last_name, line1 SHIPPING_ADDRESS, city, state, zip_code FROM addresses a JOIN customers c ON a.customer_id = c.customer_id WHERE a.address_id = c.shipping_address_id