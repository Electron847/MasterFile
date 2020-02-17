
DROP TABLE Point2D;
CREATE TABLE Point2D
(
        X INTEGER,
        Y INTEGER,

        PRIMARY KEY (X, Y)
);

INSERT INTO Point2D VALUES (5, 6);
INSERT INTO Point2D VALUES (6, 7);
INSERT INTO Point2D VALUES (11, 13);
INSERT INTO Point2D VALUES (37, 42);
INSERT INTO Point2D VALUES (15, 14);

-- A more complicated query
 DECLARE
    /* Output variables to hold the result of the query */
  Point1  Point2D%rowtype;
  Point2  Point2D%rowtype;
  XYZ NUMBER;

    /* Cursor Declaration */
 CURSOR T1Cursor IS
         SELECT *
         FROM Point2D   
         WHERE X < Y
         ORDER BY X;    
 BEGIN
 OPEN T1Cursor;

    FETCH T1Cursor INTO Point1;
    FETCH T1Cursor INTO Point2;
    DBMS_OUTPUT.PUT_LINE('Point1 - X ' || Point1.X);    
    DBMS_OUTPUT.PUT_LINE('Point1 - Y ' || Point1.Y);    
    DBMS_OUTPUT.PUT_LINE('Point2 - X ' || Point2.X);    
    DBMS_OUTPUT.PUT_LINE('Point2 - Y ' || Point2.Y);    
   
 /* Free cursor used by the query */
 CLOSE T1Cursor;
 END;
/



DECLARE
 TYPE bin_array IS TABLE OF VARCHAR2(30)
 INDEX BY BINARY_INTEGER;

 state_array bin_array;
BEGIN
  state_array(1) := 'Alaska';
  state_array(2) := 'California';
  state_array(3) := 'Oregon';
  state_array(4) := 'Washington';

  FOR i IN 1 .. state_array.COUNT LOOP
    dbms_output.put_line(state_array(i));
  END LOOP;
END;
/

DROP TABLE States;
CREATE TABLE States 
(
resultcol VARCHAR2(20)
);

DECLARE
 TYPE bin_array IS TABLE OF VARCHAR2(20)
 INDEX BY BINARY_INTEGER;

 state_array bin_array;
BEGIN
  state_array(1) := 'Alaska';
  state_array(2) := 'California';
  state_array(3) := 'Oregon';
  state_array(4) := 'Washington';

  FORALL i IN 1 .. state_array.COUNT
    INSERT INTO States VALUES (state_array(i));

  DBMS_OUTPUT.PUT_LINE('States.COUNT ' || state_array.COUNT);
  DBMS_OUTPUT.PUT_LINE('States.FIRST ' || state_array.FIRST);
  DBMS_OUTPUT.PUT_LINE('States.LAST ' || state_array.LAST);
  
  state_array.delete(4);
    
  DBMS_OUTPUT.PUT_LINE('States.LAST after delete ' || state_array.LAST);
END;
/

SELECT * FROM States;

