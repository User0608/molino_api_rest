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
------------------------------------------------------------
create table lote_secado(
    lote_secado_id int auto_increment,
    fecha date not null,
    total_sacos  int not null,
    lote_id int not null,
    ubicacion_id int not null,
    constraint pk_lote_secado primary key(lote_secado_id)
) engine= innodb;;

create table ubicacion(
    ubicacion_id int auto_increment,
    codigo varchar(60) not null,
    descripcion varchar(200),
    area_secado_id int not null,
    constraint pk_ubicacion primary key(ubicacion_id)
)engine= innodb;;

create table area_secado(
    area_secado_id int auto_increment,
    ubicacion varchar(60) not null ,
    capacidad int not null,
    constraint pk_area_secado primary key(area_secado_id)
) engine = innodb;

create table detalle_recojo(
    lote_secado_id int,
    fecha date not null,
    hora  time not null,
    humedad decimal(8,2),
    numero_sacos int,
    constraint pk_detalle_recojo  primary key(lote_secado_id)
) engine = innodb;

create table detalle_tendido(
    lote_secado_id int,
    ubicacion varchar(60) not null ,
    fecha date not null,
    hora time not null,
    constraint pk_detalle_tendido primary key(lote_secado_id)
)

create table costo(
    costo_id int auto_increment,
    monto decimal(8,2) not null,
    descripcion varchar(200) default null,
    constraint pk_costo primary key(costo_id)
) engine = innodb;

create table detalle_gasto(
    lote_secado_id int,
    costo_id int
    constraint pk_detalle_gasto primary key(lote_secado_id,costo_id)
) engine = innodb;

create table ingreso_secado(
    lote_secado_id int,
    fecha date not null,
    hora time not null,
    numero_sacos int not null,
    nivel_humedad decimal(8,2),
    constraint pk_ingreso_secado primary key(lote_secado_id)
) engine = innodb;


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

alter table ubicacion
    add constraint fk_ubicacion_areasecado 
        foreign key(area_secado_id) references area_secado(area_secado_id);

alter table lote_secado
    add constraint fk_lotesecado_ubicacion
        foreign key(ubicacion_id) references ubicacion(ubicacion_id);

alter table detalle_tendido
    add constraint fk_detalle_tendido
        foreign key(lote_secado_id) references lote_secado(lote_secado_id);
alter table detalle_recojo
    add constraint fk_detalle_recojo
        foreign key(lote_secado_id) references lote_secado(lote_secado_id);
    
alter table detalle_gasto
    add constraint fk_detallegasto_lotesecado
        foreign key(lote_secado_id) references lote_secado(lote_secado_id),
    add constraint fk_detallegasto_gasto 
        foreign key(costo_id) references costo(costo_id);

alter table ingreso_secado
    add constraint fk_ingresosecado_lotesecado
        foreign key(lote_secado_id) references lote_secado(lote_secado_id),
    add constraint fk_imgresosecado_empleado
        foreign key(empleado_id) references empleado(empleado_id);