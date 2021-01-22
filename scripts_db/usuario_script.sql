create table usuario(
	usuario_id int auto_increment,
	username varchar(200)  not null,
	pwd varchar(200)  not null,	
    owner varchar(100) not null default "public.user",
    state TINYINT(1) not null default 1,
	constraint pk_usuario PRIMARY key (usuario_id)
)engine = innodb;

create table usuario_roles(
    usuario_id int,
    role_id int,
    constraint pk_usuario_role primary key(usuario_id,role_id)
) engine=innodb;

create table roles(
    role_id int auto_increment,
    nombre varchar(60) not null,
    descripcion varchar(200) null,
    constraint pk_role primary key(role_id)
)engine = innodb;


alter table usuario_roles
    add constraint fk_usuarioroles_usuario
        foreign key(usuario_id) references usuario(usuario_id),
    add constraint fk_usuariorole_role 
        foreign key(role_id) references roles(role_id);
