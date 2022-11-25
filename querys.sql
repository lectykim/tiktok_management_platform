CREATE SCHEMA `tiktok_management` ;

create table user(
	uid int not null,
    nickname varchar(50),
    password varchar(50),
    age int,
    star int,
    primary key(uid));
    
create table movie(
	movie_id int not null auto_increment,
    user int,
    len varchar(50),
    song int,
    primary key(movie_id));

create table music(
	music_id int not null auto_increment,
	title varchar(50),
    primary key(music_id));
    
alter table movie add foreign key(user) references user(uid);

alter table movie add foreign key(song) references music(music_id);

insert into user values(null,"권도형","kwon123","40",1);
insert into user values(null,"김범수","kakao123","50",4);
insert into user values(null,"이재용","samsungman","15",5);

insert into music values(null,"masterpiece");
insert into music values(null,"re:wind");
insert into music values(null,"snow halation");

insert into movie values(null,1,"1:50",1);
insert into movie values(null,1,"2:50",2);
insert into movie values(null,1,"3:50",3);
insert into movie values(null,2,"1:30",1);
insert into movie values(null,2,"2:54",3);
insert into movie values(null,3,"20:54",2);