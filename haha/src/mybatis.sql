create table person(
personid varchar2(20) primary key,
name varchar2(30) not null,
age number,
carid varchar2(20)
);
create table car(
carid varchar2(40) primary key,
brand varchar2(30),
price number,
maxspeed number,
smarkdate date
);

select * from car;
select * from person;
drop table person;


update car set brand='BMW' where carid='c001';



select a.personid,a.name,a.age,a.carid,b.brand from person a join car b on a.carid=b.carid
