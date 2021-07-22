
create procedure sp_productores(in year varchar(10))
    select p.dni,
        p.nombre,
        p.apellido_paterno,
        p.apellido_materno,
        p.telefono,
        sum(ri.numero_sacos) as sacos
    from productor p
    inner join lote_arroz la
    on p.productor_id = la.productor_id
    inner join registro_ingreso ri
    on ri.lote_id=la.lote_id
    where year(ri.fecha) = year or year ='all'
    group by  p.dni,p.nombre,p.apellido_paterno,p.apellido_materno,p.telefono
    order by sacos desc

create procedure sp_arroz_mas_ingresado(in year varchar(10))
    select 
        pc.lugar as procedencia,
        ta.nombre as tipo_error,
        sum(ri.numero_sacos) as sacos
    from
    tipo_arroz ta
    inner join lote_arroz la
    on la.tipo_arroz_id = ta.tipo_arroz_id
    inner join registro_ingreso ri    
    on ri.lote_id = la.lote_id
    inner join procedencia pc
    on pc.procedencia_id=la.procedencia_id
    where year(ri.fecha) = '2020' or year='all'
    group by ta.nombre,pc.lugar
    order by sacos desc

create procedure sp_arroz_ingreso_mes(in year varchar(10))
    select  
        case
            when month(ri.fecha) = 1 then "Enero"
            when month(ri.fecha) = 2 then "Febrero"
            when month(ri.fecha) = 3 then "Marzo"
            when month(ri.fecha) = 4 then "Abril"
            when month(ri.fecha) = 5 then "Mayo"
            when month(ri.fecha) = 6 then "Junio"
            when month(ri.fecha) = 7 then "Julio"
            when month(ri.fecha) = 8 then "Agosto"
            when month(ri.fecha) = 9 then "Septiembre"
            when month(ri.fecha) = 10 then "Octubre"
            when month(ri.fecha) = 11 then "Noviembre"
            when month(ri.fecha) = 12 then "Diciembre"
        end as mes,
        sum(ri.numero_sacos) as sacos
        from registro_ingreso ri
        where year(ri.fecha) =year or year='all'
        group by(month(ri.fecha))    

