/*OPGAVER*/
SELECT * FROM product
SELECT * FROM supplier
SELECT * FROM order_T
SELECT * FROM orderItem

/* OPGAVE 1 */
SELECT prodName, unitPrice FROM product p
JOIN supplier s ON s.suppId = p.suppId
WHERE s.compName = 'FixFax'

/* OPAGVE 2 */
SELECT orderid, SUM(unitPrice * quantity) AS totalCost FROM orderItem oi
JOIN product p ON p.prodID = oi.prodID
GROUP BY orderId

/* OPGAVE 3 */
SELECT orderid, SUM(unitPrice * quantity) AS totalCost FROM orderItem oi
JOIN product p ON p.prodID = oi.prodID
GROUP BY orderId
HAVING SUM(unitPrice * quantity) > 200

/* OPGAVE 4 */
SELECT oi.orderItemId, orderid, p.prodName, p.unitPrice, oi.quantity, SUM(unitPrice * quantity) as subTotal
FROM orderItem oi
JOIN Product p ON p.prodID = oi.prodID
WHERE orderId = 2
GROUP BY orderItemID, orderid, prodname, unitPrice, quantity


/* OPGAVE 4.B */

/* OPGAVE 5 */
CREATE procedure opgave5 @orderId INT
AS 
BEGIN 
	SELECT oi.orderItemId, orderid, p.prodName, p.unitPrice, oi.quantity, SUM(unitPrice * quantity) as subTotal
	FROM orderItem oi
	JOIN Product p ON p.prodID = oi.prodID
	WHERE orderId = @orderId
	GROUP BY orderItemID, orderid, prodname, unitPrice, quantity
END

EXEC opgave5 @orderId = 2
 

 /* OPGAVE 6 */
CREATE TRIGGER mangeSave
ON orderItem
AFTER insert
AS
BEGIN
	if(
	(SELECT i.quantity
    FROM inserted i
	JOIN Product p ON i.prodID = p.prodID
	WHERE p.prodName = 'Sav')
	> 5
	)
	BEGIN
    print('Der er mere end 5 save!')
	END
END

INSERT INTO OrderItem VALUES (23, 6, 7, 1)

 /* OPGAVE 7 */
SELECT s.compName, COUNT(prodID) AS antalProdukter FROM Product p
JOIN Supplier s ON s.suppID = p.suppID
GROUP BY s.compName

/* OPGAVE 8 */
CREATE VIEW supplierProds
AS
	SELECT s.compName, p.prodId, p.unitPrice FROM Product p
	JOIN Supplier s ON s.suppID = p.suppID

SELECT * FROM supplierProds sp
WHERE sp.compName = 'Primus'

/* OPGAVE 9 */
SELECT p.prodName, s.compName, Oi.quantity FROM OrderItem oi
JOIN Product p ON p.prodID = oi.prodID
JOIN supplier s ON s.suppID = p.suppID
WHERE oi.orderID = 2

/* OPGAVE 10 */
SELECT c.customerName, COUNT(orderId) AS antalOrdre FROM order_T ot
JOIN customer c ON c.customerID = ot.customerID
WHERE ot.orderDate < '2021-03-02' AND ot.orderDate > '2020-01-04'
GROUP BY c.customerName

/* OPGAVE 11 */
DROP TRIGGER checkOrderItem

CREATE TRIGGER checkOrderItem
ON orderItem
INSTEAD OF INSERT 
AS
BEGIN 
	if EXISTS(
	SELECT * FROM OrderItem oi
	WHERE oi.prodID = (SELECT i.prodId FROM inserted i)
	AND oi.orderID = (SELECT i.orderID FROM inserted i)
	)
		BEGIN 
			UPDATE OrderItem SET quantity = (
				quantity + (SELECT i.quantity FROM inserted i)
			)
			WHERE orderID = (SELECT i.orderID FROM inserted i)
			PRINT('VALUES UPDATES')
		END
	ELSE
		BEGIN
			INSERT INTO OrderItem
			SELECT *
			FROM inserted;
		END
END

INSERT INTO OrderItem VALUES (24, 2, 1, 1);
 


