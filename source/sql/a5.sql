/* データベースはa5 */
/* データベースの作成 */
CREATE DATABASE a5;
USE a5;

/* user作成 */
create table user (
id int PRIMARY KEY AUTO_INCREMENT,
user_name VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
tenchou_flag int NOT NULL
);

/* shift作成 */
create table shift (
shift_id int PRIMARY KEY AUTO_INCREMENT,
shift_date date NOT NULL,
shift_start datetime,
shift_end datetime,
user_id int,
FOREIGN KEY (user_id) REFERENCES user(id)
);

/* event_type作成 */
create table event_type (
type_id int PRIMARY KEY AUTO_INCREMENT,
type_name VARCHAR(8) NOT NULL
);

/* event作成 */
create table event (
event_date date NOT NULL,
event_start datetime,
event_end datetime,
event_id int PRIMARY KEY AUTO_INCREMENT,
type_id int,
FOREIGN KEY (type_id) REFERENCES event_type(type_id)
);

/* manual作成 */
create table manual (
manual_file VARCHAR(297),
importance int DEFAULT 2,
date_up date,
file_id int PRIMARY KEY AUTO_INCREMENT
);
