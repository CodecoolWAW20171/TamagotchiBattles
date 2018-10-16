--drop table public.pets;


CREATE TABLE public.pets
(
  id serial PRIMARY KEY ,
  name varchar(100),
  exp int,
  attack int,
  defence int,
  speed int,
  health int,
  type varchar(100)
);
CREATE UNIQUE INDEX pets_id_uindex ON public.pets (id);

--example data
--insert into pets (name, exp, attack, defence, speed, health) values ('pet3', 0, 30, 30, 30, 40);