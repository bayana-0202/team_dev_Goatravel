-- 各種テーブル削除
DROP TABLE IF EXISTS admins;
DROP TABLE IF EXISTS accommodations; 
DROP TABLE IF EXISTS reserves;   
DROP TABLE IF EXISTS plans;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS room_types;
DROP TABLE IF EXISTS languages;
DROP TABLE IF EXISTS bath_types;
--DROP TYPE IF EXISTS bath;

-- 管理者テーブル
CREATE TABLE admins
(
id SERIAL PRIMARY KEY,
name VARCHAR(50),
password VARCHAR,
authority INTEGER,
email TEXT,
tel TEXT
);

--宿泊室の種類
CREATE TABLE categories
(
id SERIAL PRIMARY KEY,
name TEXT
);
--言語の種類
CREATE TABLE languages
(
id SERIAL PRIMARY KEY,
name TEXT
);
--風呂の種類
CREATE TABLE bath_types
(
id SERIAL PRIMARY KEY,
name TEXT
);
--CREATE TYPE bath AS ENUM ('ユニットバス', '風呂', 'シャワールーム');
---- 宿泊施設テーブル
CREATE TABLE accommodations
(
id SERIAL PRIMARY KEY,
category_id INTEGER REFERENCES categories(id),
bath_id INTEGER REFERENCES bath_types(id),
--bath bath NOT NULL,
name VARCHAR(200),
tel TEXT,
address TEXT,
language_id INTEGER REFERENCES languages(id),
content VARCHAR
);
----部屋の種類
CREATE TABLE room_types
(
id SERIAL PRIMARY KEY,
name TEXT
);
-- 顧客向け提供プランテーブル
CREATE TABLE plans
(
id SERIAL PRIMARY KEY,
room_id INTEGER REFERENCES room_types(id),
price INTEGER,
accommodation_id INTEGER,
date DATE
);
--
-- 顧客テーブル
CREATE TABLE users
(
id SERIAL PRIMARY KEY,
name VARCHAR,
tel TEXT,
address TEXT,
password VARCHAR,
gender INTEGER,
nickname VARCHAR(10),
email VARCHAR,
birthday DATE
);

-- 予約テーブル
CREATE TABLE reserves
(
id SERIAL PRIMARY KEY,
user_id INTEGER REFERENCES users(id),
plan_id INTEGER REFERENCES plans(id),
quantity INTEGER,
room_count INTEGER
);

