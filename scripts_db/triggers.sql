DELIMITER //
create trigger tr_ingreso_secado
    after insert
    on ingreso_secado
    for each row
begin
    declare num int;
    declare l_id int;
    select LS.lote_id into l_id from lote_secado LS where new.lote_secado_id;
    set num = new.numero_sacos;
    update lote_arroz set numero_sacos = numero_sacos - num where lote_id = l_id;
end//



DELIMITER //
CREATE TRIGGER tr_insert
after INSERT on t2 
for EACH row
begin
	UPDATE t1 set numero = numero -new.numero WHERE id =1;

end//


DELIMITER //
CREATE TRIGGER tr_update
after update on t2 
for EACH row
begin
	declare diferencia int;    
    set diferencia=new.numero-old.numero; 
	UPDATE t1 set numero = numero - diferencia WHERE id = 1;
end//

DELIMITER //
CREATE TRIGGER tr_delete
after delete on t2 
for EACH row
begin
	UPDATE t1 set numero = numero + old.numero WHERE id = 1;
end//