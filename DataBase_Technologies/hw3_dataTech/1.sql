set SERVEROUT on;

/* Delete the tables if they already exist */
drop table  Restaurant;
drop table  Reviewer;
drop table  Rating;
drop table  Restaurant_Locations;

/* Create the schema for our tables */
create table Restaurant(rID int, name varchar2(100), address varchar2(100), cuisine varchar2(100));
create table Reviewer(vID int, name varchar2(100));
create table Rating(rID int, vID int, stars int, ratingDate date);
create table Restaurant_Locations(rID int, name varchar2(100), street_address varchar2(50), 
    city varchar2(50), state varchar2(2), zipcode int, cuisine varchar2(100));

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