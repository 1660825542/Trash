--���ű�
create table department(
dept_id number(6) primary key,
dept_name varchar2(50) not null,
dept_info varchar2(200)
);

--ְ���
create table position(
pos_id number(6) primary key,
pos_name varchar2(50) not null,
pos_info varchar2(200)
);

--Ա��
create table EMPLOYEE
(
  EMP_ID        NUMBER(6) primary key,
  EMP_NAME      VARCHAR2(50) not null,
  EMP_NUM	VARCHAR2(50) not null,
  PASSWORD      VARCHAR2(50) not null,
  GENDER        NUMBER(1) not null,
  BIRTHDAY    	DATE not null,
  DEPARTMENT_ID NUMBER(6) references department(dept_id),
  POSITION_ID   NUMBER(6) references position(pos_id),
  PHONE         VARCHAR2(50) not null,
  EMAIL         VARCHAR2(50) not null,
  QQ            VARCHAR2(50),
  INTRODUCTION  VARCHAR2(200)
);

--Ȩ�ޱ�
create table PERMISSION
(
  PERM_ID   NUMBER(6) primary key,
  PERM_NAME VARCHAR2(50) not null,
  PERM_INFO VARCHAR2(200) not null
);

--��ɫ��
create table ROLE
(
  ROLE_ID   NUMBER(6) primary key,
  ROLE_NAME VARCHAR2(50) not null,
  ROLE_INFO VARCHAR2(200) not null
);

--��ɫȨ�޹�����
create table ROLE_PERMISSION
(
  RP_ID   NUMBER(6) primary key,
  ROLE_ID NUMBER(6) references role (role_id) on delete cascade,
  PERM_ID NUMBER(6) references permission (perm_id) on delete cascade
);


--Ա����ɫ������
create table EMP_ROLE
(
  ER      NUMBER(6) primary key,
  EMP_ID  NUMBER(6) references employee (emp_id) on delete cascade,
  ROLE_ID NUMBER(6) references role (role_id) on delete cascade
);

--�ļ���
create table folder(
folder_id number(6) primary key,
folder_name varchar2(50) not null,
folder_size number(20) not null,
folder_description varchar2(200),
folder_share number(1) not null,
folder_parent number(6) references folder(folder_id) on delete cascade,
emp_id number(6) references employee(emp_id) on delete cascade,
folder_left_size number(20),
can_delete number(1)
);

--�ļ�
create table files(
file_id number(6) primary key,
file_name varchar2(300) not null,
file_folder number(6) references folder(folder_id) on delete cascade,
file_size number(20) not null,
file_content blob
);

--���
create table LEAVE
(
  LEAVE_ID    NUMBER(6) primary key,
  TIME        DATE not null,
  START_TIME  DATE not null,
  END_TIME    DATE not null,
  EMP_ID      NUMBER(6) references EMPLOYEE (EMP_ID) on delete cascade,
  REASON      VARCHAR2(1000) not null,
  LEADER_ID   NUMBER(6) references EMPLOYEE (EMP_ID) on delete set null,
  STATUS      NUMBER(1),
  ENDSTATUS   NUMBER(1),
  EMP_NAME    VARCHAR2(50),
  LEADER_NAME VARCHAR2(50)
);

-- �������������־
create table LEAVE_FLOW_LOG
(
  ID        NUMBER(6) primary key ,
  USERID    NUMBER(6),
  ACTION    NUMBER(6),
  LOGTIME   DATE,
  OPINION   VARCHAR2(500),
  TASKID    VARCHAR2(50),
  DEFINEID  VARCHAR2(50),
  PROCINSID VARCHAR2(50),
  RECORDID  VARCHAR2(50),
  USERNAME  VARCHAR2(50)
);

--����
create table REIMBURSEMENT
(
  REIM_ID     NUMBER(6) primary key,
  REIM_TIME   DATE not null,
  REIM_SUM    NUMBER(10,2) not null,
  EMP_ID      NUMBER(6) references EMPLOYEE (EMP_ID) on delete cascade,
  REIM_REASON VARCHAR2(1000) not null,
  LEADER_ID   NUMBER(6) references EMPLOYEE (EMP_ID) on delete set null,
  EMP_NAME    VARCHAR2(50),
  LEADER_NAME VARCHAR2(50),
  STATUS      NUMBER(1),
  ENDSTATUS   NUMBER(1)
);

--��������������־
create table REIM_FLOW_LOG
(
  ID        NUMBER(6) primary key,
  USERID    VARCHAR2(50),
  ACTION    NUMBER(6),
  LOGTIME   DATE,
  OPINION   VARCHAR2(500),
  TASKID    VARCHAR2(50),
  DEFINEID  VARCHAR2(50),
  PROCINSID VARCHAR2(50),
  RECORDID  VARCHAR2(50),
  USERNAME  VARCHAR2(50)
);
--��Ϣʵ��
create table message(
message_id number(10) primary key,
sender_id number(6),
recipient_id number(6),
send_date date,
send_status number(1),
read_status number(1),
content varchar2(1000)
);

--������
create table sended_message(
sm number(6) primary key,
emp_id number(6) references employee(emp_id),
sended_message_id number(10) references message(message_id)
);

--�ռ���
create table received_message(
rm number(6) primary key,
emp_id number(6) references employee(emp_id),
received_message_id number(10) references message(message_id)
);


-- ���������б�
create table TASK
(
  TASK_ID      NUMBER(6) primary key,
  TASK_NAME    VARCHAR2(50),
  TASK_CONTENT VARCHAR2(200),
  TASK_DATE    DATE,
  STATE        NUMBER(1),
  START_TIME   DATE,
  FINISH_TIME  DATE
);
-- �����嵥�б�
create table TASK_LIST
(
  UT_ID      NUMBER(6) primary key,
  EMP_ID     NUMBER(6) references EMPLOYEE (EMP_ID) on delete cascade,
  MANAGER_ID NUMBER(6) references EMPLOYEE (EMP_ID) on delete cascade,
  TASK_ID    NUMBER(6) references TASK (TASK_ID) on delete cascade
);

-- ����
create table AGENT
(
  UA_ID      NUMBER(6) primary key,
  EMP_ID     NUMBER(6) references EMPLOYEE (EMP_ID) on delete cascade,
  MANAGER_ID NUMBER(6) references EMPLOYEE (EMP_ID) on delete cascade,
  AGENT      NUMBER(1)
);

-- ���� ���ɹ�˾ ��
create table ASSIGN_COM
(
  ASS_COM_ID    NUMBER(10) primary key,
  COM_NAME      VARCHAR2(50) not null,
  CONTACT_NAME  VARCHAR2(20),
  CONTACT_PHONE VARCHAR2(20),
  CONTACT_QQ    VARCHAR2(20),
  REMARKS       VARCHAR2(50)
);

-- ���� ����Ա�� ��
create table ASSIGN_EMP
(
  ASS_ID        NUMBER(10) primary key,
  ASS_NAME      VARCHAR2(20) not null,
  EDUCATION     VARCHAR2(20),
  WORK_EXP      VARCHAR2(50),
  SKILL         VARCHAR2(50),
  RESIDENT_CITY VARCHAR2(20),
  SALARY        NUMBER(10),
  AVAILABLE     VARCHAR2(20) not null,
  RESUME        BLOB
);

-- ���� Ա����ǲ ��
create table EMP_COM
(
  EMP_COM_ID NUMBER(6) primary key,
  ASS_COM_ID NUMBER(10),
  ASS_ID     NUMBER(10),
  ASS_NAME   VARCHAR2(20),
  COM_NAME   VARCHAR2(50)
);

-- ���� Ա������ ��
create table COM_REQ
(
  COM_REQ_ID      NUMBER(6) primary key,
  ASS_COM_ID      NUMBER(10) references ASSIGN_COM (ASS_COM_ID) on delete cascade,
  POS_REQ         VARCHAR2(50),
  TYPE_OF_WORK    VARCHAR2(50),
  SALARY_PERMONTH VARCHAR2(10)

);

-- ���� ���� ��
create table NOTICE
(
  NOTICE_ID    NUMBER(6) primary key,
  TIME         DATE not null,
  IS_TOP       NUMBER(1),
  NOTICE_TITLE VARCHAR2(200) not null,
  CONTENT      VARCHAR2(2000)
);

-- ���� ���� ��
create table CAR
(
  CAR_ID   NUMBER primary key,
  CAR_TYPE VARCHAR2(20) not null,
  LICENSE  VARCHAR2(20) not null,
  PICTURE  BLOB not null
);


-- ���� �������� ��
create table CAR_APPLY
(
  CARAPPLY_ID NUMBER(6) primary key,
  EMP_ID      NUMBER(6),
  APPLY_TIME  DATE,
  REASON      VARCHAR2(1000),
  START_TIME  DATE,
  END_TIME    DATE,
  LEADER_ID   NUMBER(6),
  EMP_NAME    VARCHAR2(50),
  LEADER_NAME VARCHAR2(50),
  STATUS      NUMBER(1),
  ENDSTATUS   NUMBER(1),
  ROUTE       VARCHAR2(1000)
);

-- ���� ������������ ��
create table CAR_FLOW_LOG
(
  ID        NUMBER(6) primary key,
  USERID    VARCHAR2(50),
  ACTION    NUMBER(6),
  LOGTIME   DATE,
  OPINION   VARCHAR2(500),
  TASKID    VARCHAR2(50),
  DEFINEID  VARCHAR2(50),
  PROCINSID VARCHAR2(50),
  RECORDID  VARCHAR2(50),
  USERNAME  VARCHAR2(50)
);


-- ���� ���������¼ ��
create table CAR_RECORD
(
  CARRECORD_ID NUMBER(6) primary key,
  CARAPPLY_ID  NUMBER(6),
  CAR_ID       NUMBER(6),
  DRIVER_ID    NUMBER(6),
  ROUTE        VARCHAR2(100),
  START_TIME   DATE,
  END_TIME     DATE
);

-- �����ұ�
create table ROOM
(
ROOM_ID NUMBER(6) primary key,
ROOM_NAME VARCHAR2(50),
ROOM_PLACE VARCHAR2(50),
ROOM_STATE NUMBER(4),
ACCOM_NUM NUMBER(8),
AIR_CON NUMBER(4)
);

-- �����
create table MEETING
(
MEET_ID NUMBER(6) primary key,
MEET_DATEBEGIN DATE not null,
ROOM_ID NUMBER(6) references ROOM (ROOM_ID) on delete cascade,
ORIGINATOR NUMBER(6) references EMPLOYEE (EMP_ID) on delete cascade,
THEME VARCHAR2(200),
MEET_DATEEND DATE not null,
MEET_STATE NUMBER(6)
);

-- Ա���������
create table EMP_MEETING
(
EM_ID NUMBER(6) primary key,
EMP_ID NUMBER(6) references EMPLOYEE (EMP_ID) on delete cascade,
MEET_ID NUMBER(6) 
);

-- �����¼��
create table RECORD
(
REC_ID NUMBER(6) not null,
REC_HTML BLOB,
MEET_ID NUMBER(6) references MEETING (MEET_ID)
);

-- ��Ƭ��
create table CARD_CATEGORY
(
  CATA_ID    NUMBER(9) primary key,
  EMP_ID     NUMBER(9) references EMPLOYEE (EMP_ID) on delete cascade,
  CATA_NAME  VARCHAR2(50),
  CAN_DELETE NUMBER(1)
);

--��Ƭ��
create table CARD
(
  CARD_ID       NUMBER(9) primary key,
  CATA_ID       NUMBER(9) references CARD_CATEGORY (CATA_ID) on delete cascade,
  CARD_NAME     VARCHAR2(50),
  CARD_PHONE    VARCHAR2(50),
  CARD_POSITION VARCHAR2(50),
  CARD_DEPT     VARCHAR2(50)
);

--�ʲ���
create table possession(
poss_id number(6) primary key,
poss_name varchar2(50) not null,
poss_category varchar2(50),
poss_type varchar2(50),
poss_price number(10,2),
poss_unused number(10),
poss_used number(10)
);

--�칫��Ʒ���ļ�¼��
create table possession_log(
log_id number(6) primary key,
emp_id number(6),
borrow_date date,
borrow_num number(10),
borrow_description varchar2(200),
poss_id number(6)
);