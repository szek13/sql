-- Table: users

-- DROP TABLE users;

CREATE TABLE users
        (
        iduser bigint NOT NULL DEFAULT nextval('user_iduser_seq'::regclass),
        username character(25),
        parola character(25),
        varsta character(2),
        orasul character(20),
        blocat character(8),
        CONSTRAINT pk_user PRIMARY KEY (iduser)
        )
        WITH (
        OIDS=FALSE
        );
        ALTER TABLE users
        OWNER TO fasttrackit_dev;



-- Table: postare

-- DROP TABLE postare;

        CREATE TABLE postare
        (
        idpostare bigserial NOT NULL,
        mesaj character(150),
        data_postarii character(18),
        fkuser bigint,
        CONSTRAINT pkpostare PRIMARY KEY (idpostare),
        CONSTRAINT fkkey FOREIGN KEY (fkuser)
        REFERENCES users (iduser) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
        )
        WITH (
        OIDS=FALSE
        );
        ALTER TABLE postare
        OWNER TO fasttrackit_dev;




/* C2 sa se selecteze toate postarile lui ionel */

select mesaj from postare
where fkuser in
(select iduser from users where username= 'ionel')


/* C3 sa se afiseze toti userii */

select username from users



/* C4 sa se afiseze toti userii care nu sunt blocati */

select username from users where blocat= 'nu'



/* C5 sa se afiseze userul cu varsta cea mai mica */

select username from users where varsta=(select min(varsta) from users)


/* C6 sa se afiseze userii cu varsta intre 23 si 33 de ani, ordonati dupa varsta */

select username from users where (varsta> '23' and varsta< '33') order by varsta


/* C7 sa se afiseze media varstei userilor blocati */

select AVG (varsta) from users where blocat= 'nu'



/* C8 sa se afiseze userii neblocati din dej */

select username from users where orasul= 'Dej' and blocat= 'nu'



/* C9 sa se afiseze postarile userilor neblocati din turda care au varsta peste 40 de ani */

select mesaj from postare
where fkuser in
(select iduser from users where orasul= 'Turda'
and varsta> '40' and blocat= 'nu')


/* C13 sa se stearga postarile userilor sub 18ani
care contin textul 'politica'*/

delete from postare
where fkuser
in( select iduser from users where varsta< '18')
and mesaj LIKE '%politica%'


/* C10 sa se afiseze userul cu cele mmai multe postari*/

select username
from users
join postare
on users.iduser=postare.fkuser
group by username
order by count(*) desc
limit 1


/* C11 sa se afiseze postarile userilor care incep cu litera D si sunt postate intre 1 si 31 maetie */

select username
from users
join postare
on users.iduser=postare.fkuser
where username like 'd%' and (data_postarii> '01.03.2016' and data_postarii<'31.03.2016')


/* C12 sa se afiseze postarile ordonate dupa data postarii
descendent, indiferent de user*/

select mesaj, data_postarii from postare
order by data_postarii DESC