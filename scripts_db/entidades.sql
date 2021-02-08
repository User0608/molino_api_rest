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

create table tipo_arroz(
    tipo_arroz_id int auto_increment,
    nombre varchar(60) not null,
    descripcion varchar(200) not null,
    constraint pk_tipo_arroz primary key(tipo_arroz_id)
)engine=innodb;

create table procedencia(
    procedencia_id int auto_increment,
    lugar varchar(100) not null,
    constraint pk_procedencia primary key(procedencia_id)
) engine=innodb;

create table lote_arroz(
    lote_id int auto_increment,
    numero_sacos int not null,
    productor_id int,
    tipo_arroz_id int,
    procedencia_id int,
    constraint pk_lote_arroz primary key(lote_id)
) engine=innodb;

create table camion_transporte(
    camion_transporte_id int auto_increment,
    placa varchar(10) not null,
    chofer varchar(100) not null,
    descripcion varchar(200) default null,
    constraint pk_camion_transporte primary key(camion_transporte_id)
) engine = innodb;

create table registro_ingreso(
    lote_id int,
    numero_sacos int not null,
    kilos_saco decimal default null,
    total_kilos decimal default null,
    fecha date not null, 
    hora time not null,
    empleado_id int not null,
    camion_transporte_id int not null,    
    constraint pk_registro_ingreso primary key(lote_id)
    
) engine= innodb;

-- Constrains, foreign key definition.
alter table registro_ingreso
    add constraint fk_ingreso_camion 
        foreign key(camion_transporte_id) references camion_transporte(camion_transporte_id),
    add constraint fk_registroingreso_empleado 
        foreign key(empleado_id) references empleado(empleado_id),
    add constraint fk_registroingreso_lotearroz 
        foreign key(lote_id) references lote_arroz(lote_id);

alter table lote_arroz
    add constraint fk_lotearroz_tipo
        foreign key(tipo_arroz_id) references tipo_arroz(tipo_arroz_id),
    add constraint fk_lotearroz_productor 
        foreign key(productor_id) references productor(productor_id),
    add constraint fk_lotearroz_procedencia 
        foreign key(procedencia_id) references procedencia(procedencia_id);