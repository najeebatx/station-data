
create table station
(
   stationId varchar(10) not null,
   name varchar(50) not null,
   hdEnabled boolean not null,
   callSign varchar(50) not null,
   primary key(stationId)
);