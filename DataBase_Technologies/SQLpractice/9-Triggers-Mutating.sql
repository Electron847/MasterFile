drop table emp;

create table emp (
    id number(4)
)

create table bin (
    id number(4)
)

INSERT INTO emp values (1);
INSERT INTO emp values (2);
--whenever tryping to modify emp and selecting data from the same table. 

create or replace trigger main_trig
before delete on emp
for each row
declare
a number;
begin
 select count(*) into a from emp;
 insert into bin values (a);
end;
 
DELETE FROM emp WHERE Id = 1;

--created a global variable
drop trigger main_trig;

CREATE OR REPLACE PACKAGE global_var is
 cnt number := 0;
END;


create or replace trigger trg1
before delete on emp
begin
 select count(*) into global_var.cnt from emp;
 dbms_output.put_line('This is the first trigger');
end;

create or replace trigger trg2
before delete on emp
begin
  insert into bin values (global_var.cnt); 
   dbms_output.put_line('This is the second trigger');
end;

DELETE FROM emp WHERE Id = 1;
