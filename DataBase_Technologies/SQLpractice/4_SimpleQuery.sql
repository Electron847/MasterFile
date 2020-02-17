
DROP TABLE Point2D;
CREATE TABLE Point2D
(
        X INTEGER,
        Y INTEGER,
        
        PRIMARY KEY (X,Y)
);

INSERT INTO Point2D VALUES (5, 6);
INSERT INTO Point2D VALUES (6, 7);
INSERT INTO Point2D VALUES (11, 13);


-- Select one row into variables
DECLARE
	a NUMBER;
	b NUMBER;
BEGIN
	SELECT X,Y INTO A,B FROM Point2D WHERE Y>12;

  IF (B > A) THEN
   	INSERT INTO Point2D VALUES(B,A);
  END IF;
    
END;


-- Find all points between 1 and 10 (x-axis)
DECLARE
	a NUMBER;
	b NUMBER;
  vCount NUMBER;
BEGIN

  FOR counter IN 1 .. 10 LOOP
     SELECT COUNT(*) INTO vCount FROM Point2D WHERE X BETWEEN counter AND counter+1;
     
     IF vCount = 1 THEN
         	SELECT X,Y INTO A,B FROM Point2D WHERE X BETWEEN counter AND counter+1;
          DBMS_OUTPUT.PUT_LINE('Found ' || A || ' and ' || B);
     ELSE
          DBMS_OUTPUT.PUT_LINE('Too many (or too few) values -> ' || vCount);
     END IF;
  END LOOP;
END;




-- Doesn't work when more than one row returns (or less than one)
DECLARE
	A NUMBER;
	B NUMBER;
BEGIN
	SELECT X,Y INTO A,B FROM Point2D WHERE Y<12;

  IF (B > A) THEN
   	INSERT INTO Point2D VALUES(B,A);
  END IF;
    
END;

