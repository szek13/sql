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