/* データベースはa5 */
/* データベースの作成 */

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
shift_start VARCHAR(6),
shift_end VARCHAR(6),
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
event_start VARCHAR(6),
event_end VARCHAR(6),
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

/* userに追加 */
/* dojouser1~5(1のみ店長) */

INSERT INTO user VALUES (null, 'dojouser1', '#SEplus2025SEplus', 1);
INSERT INTO user VALUES (null, 'dojouser2', '#SEplus2025SEplus', 0);
INSERT INTO user VALUES (null, 'dojouser3', '#SEplus2025SEplus', 0);
INSERT INTO user VALUES (null, 'dojouser4', '#SEplus2025SEplus', 0);
INSERT INTO user VALUES (null, 'dojouser5', '#SEplus2025SEplus', 0);

/* event_typeに追加 */
/* イベントの選択肢 */
INSERT INTO event_type VALUES (null, '貸切');
INSERT INTO event_type VALUES (null, '催事');
INSERT INTO event_type VALUES (null, '期間限定');
INSERT INTO event_type VALUES (null, '定期開催');
INSERT INTO event_type VALUES (null, 'シフト締切');
INSERT INTO event_type VALUES (null, '臨時休業');

/* eventに追加 */
INSERT INTO event VALUES ('2025-06-15', '12:00', '16:00', null, 2);
INSERT INTO event VALUES ('2025-06-22', '9:00', '17:00', null, 1);
INSERT INTO event VALUES ('2025-06-22', '12:00', '14:00', null, 5);
INSERT INTO event VALUES ('2025-06-27', '12:00', '16:00', null, 4);

/* shiftに追加 */
/* 6月 */
INSERT INTO shift VALUES (null, '2025-06-22', '9:00', '17:00', 3);
INSERT INTO shift VALUES (null, '2025-06-22', '12:00', '14:00', 5);
INSERT INTO shift VALUES (null, '2025-06-25', '12:00', '16:00', 3);
INSERT INTO shift VALUES (null, '2025-06-27', '12:00', '16:00', 4);
/* 7月 */
INSERT INTO shift VALUES (null, '2025-07-22', '9:00', '17:00', 3);
INSERT INTO shift VALUES (null, '2025-07-22', '12:00', '14:00', 5);
INSERT INTO shift VALUES (null, '2025-07-25', '12:00', '16:00', 3);
INSERT INTO shift VALUES (null, '2025-07-27', '12:00', '16:00', 4);
