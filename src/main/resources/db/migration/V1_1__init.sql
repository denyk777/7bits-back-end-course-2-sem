 create table task (
     id text PRIMARY KEY ,
     text text not null ,
     status text not null ,
     createat text not null
 );

 insert into task (id, text, status, createat)
 values ('1', 'first task', 'inbox', '000-00-00');