CREATE USER coalive WITH PASSWORD '1q2w3e4r' CREATEDB;
CREATE DATABASE coalive;
\c coalive;

/* 유저 테이블 */
DROP TABLE COMUSER;
CREATE TABLE COMUSER (
    USER_ID VARCHAR(255) /* 유저 고유값 */
    , USER_NAME VARCHAR(255) /* 유저 이름 */
    , USER_EMAIL VARCHAR(255) /* 이메일 */
    , INST_TIME VARCHAR(14) /* 생성시간 */
    , PRIMARY KEY (USERID)
);

/* 알림 테이블 */
DROP TABLE COMALERT;
CREATE TABLE COMALERT (
    ALERT_SEQ SERIAL /* 유저별 시퀀스. 자동 증가. */
    , USER_ID VARCHAR(255) /* 카카오 UID */
    , TITLE VARCHAR(255) /* 제목 */
    , CONTENT VARCHAR(1024) /* 본문 */
    , TEL_NO_1 VARCHAR(32) /* 비상 연락망 1 ~ 5 */
    , TEL_NO_2 VARCHAR(32)
    , TEL_NO_3 VARCHAR(32)
    , TEL_NO_4 VARCHAR(32)
    , TEL_NO_5 VARCHAR(32)
    , END_TIME VARCHAR(14) /* 종료 시간. 해당 시간이 넘으면 문제가 있는 것으로 판단해서 메시지 전송 */
    , INST_TIME VARCHAR(14)
    , UPDT_TIME VARCHAR(14)
    , PRIMARY KEY (ALERT_SEQ) /* 한 사람이 여러 개 올릴 수 있음. */
);

/* 메시지 전송 로그 */
DROP TABLE SENDLOG;
CREATE TABLE SENDLOG (
    LOG_SEQ SERIAL
    , USER_ID
);