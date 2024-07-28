use estateadvance;

insert into role(code, name)
values ('MANAGER', 'Quan tri he thong');
insert into role(code, name)
values ('USER', 'Nguoi dung');

insert into user(username, password, fullname, status)
values ('admin', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', 'admin', 1);
insert into user(username, password, fullname, status)
values ('nguyenvana', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', 'nguyen van a', 1);
insert into user(username, password, fullname, status)
values ('nguyenvanb', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', 'nguyen van b', 1);

INSERT INTO user_role(user_id, role_id)
VALUES (1, 1);
INSERT INTO user_role(user_id, role_id)
VALUES (2, 2);
INSERT INTO user_role(user_id, role_id)
VALUES (3, 2);


select * from user;
select * from role;
select * from user_role;

