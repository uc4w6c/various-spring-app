# メモ
$ docker run -p 5432:5432 -e POSTGRES_PASSWORD=password -d postgres
$ psql -h localhost -p 5432 -U postgres -d postgres
$ create table books (id integer, name varchar(100));
$ insert into books values (1, 'プロになるJava');
