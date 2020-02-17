DROP TABLE CONTRACT CASCADE CONSTRAINTS; 
DROP TABLE TASK CASCADE CONSTRAINTS; 

CREATE TABLE TASK (
TaskID CHAR(3),
TaskName VARCHAR(20),
ContractCount NUMERIC(1,0) DEFAULT 0, 
CONSTRAINT PK_TASK PRIMARY KEY (TaskID)
);

CREATE TABLE CONTRACT 
(
TaskID CHAR(3),
WorkerID CHAR(7),
Payment NUMERIC(6,2),
CONSTRAINT PK_CONTRACT PRIMARY KEY (TaskID, WorkerID),
CONSTRAINT FK_CONTRACTTASK FOREIGN KEY (TaskID) REFERENCES TASK (TaskID) 
);

INSERT INTO TASK (TaskID, TaskName) VALUES ('333', 'Security' );
INSERT INTO TASK (TaskID, TaskName) VALUES ('322', 'Infrastructure');
INSERT INTO TASK (TaskID, TaskName) VALUES ('896', 'Compliance' );

INSERT INTO CONTRACT (TaskID, WorkerID, Payment) values ('333', 'ref', 89);
INSERT INTO CONTRACT (TaskID, WorkerID, Payment) values ('322', 'juh', 21);
INSERT INTO CONTRACT (TaskID, WorkerID, Payment) values ('333', 'sam', 67);
INSERT INTO CONTRACT (TaskID, WorkerID, Payment) values ('333', 'cam', 67);
INSERT INTO CONTRACT (TaskID, WorkerID, Payment) values ('896', 'jak', 67);

delete from contract where taskid = 333 and workerID = 'jos';

update CONTRACT set payment = 99 where TaskID = '333';


SELECT * FROM TASK;
SELECT * FROM CONTRACT;
COMMIT; 

--Q3, part 1
create or replace trigger ContractInsert before insert on CONTRACT
for each row
declare  counter int :=0;
begin
    select ContractCount into counter from Task where TaskID = :new.TaskID;
    if counter < 3 THEN
        update task set ContractCount = ContractCount + 1 where TaskID = :new.TaskID;
    else if counter >= 3 THEN
        RAISE_APPLICATION_ERROR( -20001, 'Error: Task is full.' );
    end if;
    end if;
end;


--Q3, part 2
create or replace trigger EndContract after delete on CONTRACT
for each row
declare  counter int :=0;
begin
    select ContractCount into counter from Task where TaskID = :old.TaskID;
        update task set ContractCount = ContractCount - 1 where TaskID = :old.TaskID;
end;

--Q3, part 3
create or replace trigger NoChanges before update on CONTRACT
begin
    RAISE_APPLICATION_ERROR( -20002, 'Error: Contract cannot be modified.' );

end;
/

