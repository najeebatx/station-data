create table station
(
   stationId varchar(255) not null,
   name varchar(50) not null,
   hdEnabled boolean not null,
   callSign varchar(4) not null,
   primary key(stationId)
);