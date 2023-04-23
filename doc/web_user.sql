create table web_user
(
    id        int auto_increment comment '用户ID',
    user      varchar(255) not null comment '用户名',
    password  varchar(255) not null comment '密码',
    date      varchar(255) null comment '注册日期',
    level     int          null comment '权限：0最低，9最高',
    name      varchar(255) null,
    introduce varchar(255) null,
    email     varchar(255) null,
    constraint id
        unique (id),
    constraint user
        unique (user)
)
    comment '权限：0最低，9最高';

INSERT INTO login_web.web_user (id, user, password, date, level, name, introduce, email) VALUES (1, 'qwe', '123456', '0000-00-00', 10, 'qwenba', '我叫qwe，一个用户a', '123@qq.com');
INSERT INTO login_web.web_user (id, user, password, date, level, name, introduce, email) VALUES (2, 'admin', 'admin', '0000-00-00', 9981, '管理员', '我是管理员!!!!', null);
INSERT INTO login_web.web_user (id, user, password, date, level, name, introduce, email) VALUES (3, 'asd', 'asd', '0000-00-00', 1, 'asd', 'asdasdasd', null);
INSERT INTO login_web.web_user (id, user, password, date, level, name, introduce, email) VALUES (21, 'phone', '123456', '2023-04-13', 1, '新用户17F294', '1
2
3
4
5
', null);
