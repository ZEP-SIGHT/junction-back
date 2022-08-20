-- mapTBL : 스페이스 정보 삽입
insert into maptbl values ("asdfe324d75sf", "name1");
insert into maptbl values ("esdf3423r4dsf", "name2");
-- insert into maptbl values ("f123e3247dsf", "name3");
-- insert into maptbl values ("klwerd334dsf", "name4");

-- visitor :
insert into visited
values (1, "area1", "asdfe324d75sf", "MASTER", "1");

insert into visited
values (2, "area2", "esdf3423r4dsf", "MASTER", "1");

insert into visited
values (3, "area1", "f123e3247dsf", "MASTER", "2");

insert into visited
values (4, "area2", "asdfe324d75sf", "MASTER", "1");



-- auto-generated definition
-- create table visitedtbl
-- (
--     v_id                 varchar(255) not null
--         primary key,
--     designated_area_name varchar(255) null,
--     map_hash             varchar(255) null,
--     v_player_auth        int          null,
--     v_player_id          varchar(255) null
-- );
--
--