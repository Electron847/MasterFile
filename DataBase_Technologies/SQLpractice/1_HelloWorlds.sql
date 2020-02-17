

-- Hello world
declare
   v_line varchar2(40);
begin
   v_line := 'Hello World';
   dbms_output.put_line (v_line);
end;
/

-- Value concatenation
declare
   v_line1 varchar2(20);
   v_line2 varchar2(20);
begin
   v_line1 := 'Hello ...';
   v_line2 := '+... World';
   dbms_output.put_line(v_line1 || v_line2);
end;
/
  

-- Default variable settings
declare
   v_line1 varchar2(20) := 'Default A  ';
   v_line2 varchar2(20) := 'Default B';
begin
   dbms_output.put_line('Before: ' || v_line1 || v_line2);
   v_line1 := 'Hello ...';
   v_line2 := '+... World';
   dbms_output.put_line(v_line1 || v_line2);
end;
/
  

-- Declaring types
declare
   v_line1 varchar2(20);
   v_line2 v_line1%TYPE;
begin
   v_line1 := 'Hello ...';
   v_line2 := '+... World';
   dbms_output.put_line(v_line1 || v_line2);
end;
/
