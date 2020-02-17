
-- Count up by hundreds until we get an error.
  DECLARE
    hundreds_counter  NUMBER(3);
  BEGIN
    hundreds_counter := 100;
    LOOP
      DBMS_OUTPUT.PUT_LINE(hundreds_counter);
      hundreds_counter := hundreds_counter + 100;
    END LOOP;
  END;
  /


-- Count up by hundreds until we get an error.
  DECLARE
    hundreds_counter  NUMBER(3);
  BEGIN
    hundreds_counter := 100;
    LOOP
      DBMS_OUTPUT.PUT_LINE(hundreds_counter);
      hundreds_counter := hundreds_counter + 100;
    END LOOP;
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('That is as high as you can go.');
    END;
  /
   
   
-- Count up by hundreds until we stop at condition
  DECLARE
    hundreds_counter  NUMBER(3);
  BEGIN
    hundreds_counter := 100;
    WHILE hundreds_counter <= 800 LOOP
      DBMS_OUTPUT.PUT_LINE(hundreds_counter);
      hundreds_counter := hundreds_counter + 100;
    END LOOP;
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('That is as high as you can go.');
    END;
  /

  DECLARE
    fin  CHAR(50) := 'That''s all numbers between 66 and 50';
    counter NUMBER(3);
  BEGIN

    FOR counter IN REVERSE 50 .. 66 LOOP
      DBMS_OUTPUT.PUT_LINE('Counter = ' || counter);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE(fin);

  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('This would have been an error.');
    END;
  /


  DECLARE
    counter NUMBER(3);
  BEGIN

    FOR counter IN -5 .. 5 LOOP
      DBMS_OUTPUT.PUT_LINE(' 37 divided by ' || counter || ' = ' || (37/counter));
    END LOOP;
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('This would have been an error.');
    END;
  /


