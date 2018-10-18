--drop table public.pets;


CREATE TABLE public.pets
(
  id int PRIMARY KEY ,
  name varchar(100),
  exp int,
  attack int,
  defence int,
  speed int,
  health int,
  type varchar(100)
);

--example data
--insert into pets (name, exp, attack, defence, speed, health) values ('pet3', 0, 30, 30, 30, 40);