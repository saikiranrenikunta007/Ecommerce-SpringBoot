insert into role(id,created_by,created_date,last_updated_by,last_updated_date,role_type)
select '1001','saikiran',CURRENT_DATE,'saikiran',CURRENT_DATE,'Admin'
where not exists (select id from role where id='1001');

insert into role(id,created_by,created_date,last_updated_by,last_updated_date,role_type)
select '1002','saikiran',CURRENT_DATE,'saikiran',CURRENT_DATE,'User'
where not exists (select id from role where id='1002');



