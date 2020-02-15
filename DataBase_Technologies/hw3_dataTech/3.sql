set SERVEROUT on;

/* Delete the tables if they already exist */
drop table  zipcode;
drop table  Rating;
drop table Top5Restaurants;
select * from zipcode;
select * from Restaurant_Locations;
Select * from Restaurant;
Select * from Rating;
Select * from Restaurant_Locations;
Select * from Reviewer;

/* Create the schema for our tables */
create table Restaurant(rID int, name varchar2(100), address varchar2(100), cuisine varchar2(100));
create table Reviewer(vID int, name varchar2(100));
create table Rating(rID int, vID int, stars int, ratingDate date);
create table Restaurant_Locations(rID int, name varchar2(100), street_address varchar2(50), 
    city varchar2(50), state varchar2(2), zipcode int, cuisine varchar2(100));
create table zipcode(zip varchar2(30),city varchar2(50), state varchar2(2), 
    latitude varchar2(30), longitude varchar2(30), timezone varchar2(30), dst varchar2(30));
  
  
--Q3 a Procedure   
create or replace procedure insertRating (
    RestaurantName IN Restaurant.name%TYPE, 
    UserName IN Reviewer.name%TYPE, 
    Rating IN Rating.stars%TYPE, 
    RatingDate2 IN Reviewer.name%TYPE
    )
as
 counter number :=1;

BEGIN
    INSERT INTO Rating VALUES ((select rID from Restaurant where name = RestaurantName ),
        (select vID from Reviewer where name = UserName),
        Rating, to_date(RatingDate2, 'MM-DD-YYYY'));
    
    select count(*) into counter from Reviewer where name = UserName;
    if counter < 1 THEN
        insert into Reviewer values (NULL, UserName);
    end if;
END;
/


--Q3 c Test
BEGIN
    insertRating('Jade Court','Sarah M.', 4, '08/17/2017');
    insertRating('Shanghai Terrace','Cameron J.', 5, '08/17/2017');
    insertRating('Rangoli','Vivek T.',3,'09/17/2017');
    insertRating('Shanghai Inn','Audrey M.',2,'07/08/2017');
    insertRating('Cumin','Cameron J.', 2, '09/17/2017');
END;

create table Top5Restaurants(rID int);
select * from Top5Restaurants;
drop table Top5Restaurants;
drop table rating;


select * from rating;
select RID, STARS from TopRatings;
drop view TopRatings;

create or replace VIEW TopRatings(rID, stars) as select rid, stars from Rating;


--Q3 b Trigger
create or replace trigger RatingInsert before insert on Rating
for each row 
declare top5count int :=0; alreadyin int :=0; 
minRating int :=0; avgst int :=0; lowestRidinT int :=0;
begin
    select count(*) into top5count from Top5Restaurants;
    select count(*) into alreadyin from Top5Restaurants where :new.rid = rid;
    select avg(stars) into avgst from TopRatings where rid = :new.rid; --avg stars of the new RID
    select min(topavst) into minRating from 
    (select t.rid, avg(stars) as topavst from 
        TopRatings r, Top5Restaurants t where r.rid = t.rid group by t.rid);
    if (top5count < 5) and (alreadyin < 1) THEN
        insert into Top5Restaurants values (:new.rID);  
    select rid into lowestRidinT from (select t.rid, avg(stars) from 
        TopRatings r, Top5Restaurants t where r.rid = t.rid group by t.rid) where rownum =1; 
        
    else if (avgst > minRating) THEN --update Top5Restaurants set rid = :new.rid where rid = 
    update Top5Restaurants set rid = :new.rid 
    where rid = lowestRidinT; --(select rid from (select t.rid, avg(stars) as topavst from rating r, Top5Restaurants t where r.rid = t.rid group by t.rid) where rownum =1);
        end if;
    end if;
end;
/


/* Populate the tables with our data */
insert into Restaurant values(101, 'India House Restaurant', '59 W Grand Ave Chicago, IL 60654', 'Indian');
insert into Restaurant values(102, 'Bombay Wraps', '122 N Wells St Chicago, IL 60606', 'Indian');
insert into Restaurant values(103, 'Rangoli', '2421 W North Ave Chicago, IL 60647', 'Indian');
insert into Restaurant values(104, 'Cumin', '1414 N Milwaukee Ave Chicago, IL 60622', 'Indian');
insert into Restaurant values(105, 'Shanghai Inn', '4723 N Damen Ave Chicago, IL 60625', 'Chinese');
insert into Restaurant values(106, 'MingHin Cuisine', '333 E Benton Pl Chicago, IL 60601', 'Chinese');
insert into Restaurant values(107, 'Shanghai Terrace', '108 E Superior St Chicago, IL 60611', 'Chinese');
insert into Restaurant values(108, 'Jade Court', '626 S Racine Ave Chicago, IL 60607', 'Chinese');

insert into Reviewer values(2001, 'Sarah M.');
insert into Reviewer values(2002, 'Daniel L.');
insert into Reviewer values(2003, 'B. Harris');
insert into Reviewer values(2004, 'P. Suman');
insert into Reviewer values(2005, 'Suikey S.');
insert into Reviewer values(2006, 'Elizabeth T.');
insert into Reviewer values(2007, 'Cameron J.');
insert into Reviewer values(2008, 'Vivek T.');

insert into Rating values( 101, 2001,2, DATE '2011-01-22');
insert into Rating values( 101, 2001,4, DATE '2011-01-27');
insert into Rating values( 106, 2002,4, null);
insert into Rating values( 103, 2003,2, DATE '2011-01-20');
insert into Rating values( 108, 2003,4, DATE '2011-01-12');
insert into Rating values( 108, 2003,2, DATE '2011-01-30');
insert into Rating values( 101, 2004,3, DATE '2011-01-09');
insert into Rating values( 103, 2005,3, DATE '2011-01-27');
insert into Rating values( 104, 2005,2, DATE '2011-01-22');
insert into Rating values( 108, 2005,4, null);
insert into Rating values( 107, 2006,3, DATE '2011-01-15');
insert into Rating values( 106, 2006,5, DATE '2011-01-19');
insert into Rating values( 107, 2007,5, DATE '2011-01-20');
insert into Rating values( 104, 2008,3, DATE '2011-01-02'); 

--Q1 - Cursor
DECLARE
v_name Restaurant.name%TYPE;
v_rID Restaurant.rID%TYPE;
v_cuisine Restaurant.cuisine%TYPE;
v_address Restaurant.address%TYPE;

CURSOR T1Cursor IS SELECT
    name, rID, cuisine, address
    from Restaurant;
    
BEGIN
OPEN T1Cursor;
LOOP
FETCH T1Cursor INTO v_name, v_rID, v_cuisine, v_address;

INSERT INTO Restaurant_Locations
        VALUES(v_rID, --ID
        v_name, --Name
        substr(v_address, 1, INSTR(v_address, ' ', -15, 1)), --Street Address
          substr(v_address, INSTR(v_address, ' ', -15, 1), 
            (REGEXP_INSTR(v_address, ','))-INSTR(v_address, ' ', -15, 1)), --City
        substr(v_address, -8, 2), --state
        substr(v_address, -5), --zipcode
        v_cuisine); --cuisine

EXIT WHEN T1Cursor%NOTFOUND;
END LOOP;
CLOSE T1Cursor;
END;
/