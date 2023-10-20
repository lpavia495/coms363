
set sql_safe_updates = 0;
update students set name = 'Scott' where ssn = 746897816;
update major set name = 'Computer Science', level = 'MS' where snum in (select snum from students where ssn = 746897816);
delete from register where regtime = 'Spring2021';
set sql_safe_updates = 1;