insert into permission_test(id,code,name,parent_id)values('421fc2deeb524989b4f8c15369a86712','security:all','权限管理',null);
insert into permission_test(id,code,name,parent_id)values('4028b2ea66f0f1240166f0f149b00000','permission:all','资源管理','421fc2deeb524989b4f8c15369a86712');
insert into permission_test(id,code,name,parent_id)values('4028b2ea66f0f1240166f0f14a170001','permission:retrieve','资源查询','4028b2ea66f0f1240166f0f149b00000');

insert into role_test(id,code,name)values('4028b2ea66f0f25d0166f0f27be90000','admin','管理员');

insert into role_permission_test(role_id,permission_id)values('4028b2ea66f0f25d0166f0f27be90000','4028b2ea66f0f1240166f0f14a170001');

insert into user_test(id,username,password,role_id)values('4028b2ea66f102b60166f102d5230000','admin','3a4ebf16a4795ad258e5408bae7be341','4028b2ea66f0f25d0166f0f27be90000');