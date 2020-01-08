-- 创建db：test_db
-- CREATE DATABASE IF NOT EXISTS test_db DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

-- 创建table：test_customer
create table if not exists test_customer (
    id int unsigned auto_increment primary key comment 'ID',
    first_name varchar(20) not null default '' comment 'first name',
    last_name varchar(20) not null default '' comment 'last name',
    country varchar(20) not null default '' comment 'country',
    create_datetime timestamp not null default current_timestamp comment '创建时间',
    update_datetime timestamp not null default current_timestamp comment '更新时间'
) engine=innodb auto_increment=10000 default charset=utf8mb4 comment='客户表';









