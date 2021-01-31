create table productor(
    productor_id int auto_increment,
    dni char(8) not null,
    nombre varchar(60) not null,
    apellido_paterno varchar(60) not null,
    apellido_materno varchar(60) not null,
    direccion varchar(60) not null,
    telefono varchar(60) not null,
    email varchar(60) not null,
    constraint pk_productor primary key(productor_id)
) engine = innodb;
create table empleado(
    empleado_id int auto_increment,    
    nombre varchar(60) not null,
    apellido_paterno varchar(60) not null,
    apellido_materno varchar(60) not null,
    dni char(8) not null,    
    telefono varchar(60) not null,
    direccion varchar(60) not null,
    email varchar(60) not null,
    sueldo double(8,2) not null,
    fecha_contrato date not null,
    estado tinyint(1) not null default 1,
    constraint pk_empleado primary key(empleado_id)
) engine = innodb;
