/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     1/8/2015 11:27:18 AM                         */
/*==============================================================*/


drop table if exists CLASSES;

drop table if exists COURSES;

drop table if exists FACULTY;

drop table if exists INSTRUCTORS;

drop table if exists MAJORS;

drop table if exists MAJOR_COURSE;

drop table if exists PERMISSIONS;

drop table if exists REGISTRATION;

drop table if exists ROLES;

drop table if exists ROLE_PERMISSION;

drop table if exists SEMESTERS;

drop table if exists STUDENTS;

drop table if exists SUBSCRIBERS;

drop table if exists USERS;

drop table if exists USER_ROLE;

drop table if exists YEARS;

/*==============================================================*/
/* Table: CLASSES                                               */
/*==============================================================*/
create table CLASSES
(
   ID                   int not null auto_increment,
   COURSE               int not null,
   SEMESTER             int not null,
   YEAR                 int not null,
   INS_ID               int not null,
   NAME                 varchar(50),
   WEEK                 int,
   CAPACITY             int,
   MAX_ABSENT           decimal(3,1),
   MIDTERM              int,
   FINAL_EXAM           int,
   ASSIGNMENT           int,
   primary key (ID)
);

/*==============================================================*/
/* Table: COURSES                                               */
/*==============================================================*/
create table COURSES
(
   ID                   int not null auto_increment,
   CODE                 varchar(20) not null,
   NAME                 varchar(100) not null,
   CREDIT               int not null,
   COST                 decimal(5,2),
   DESCRIPTION          varchar(500),
   HOUR                 int,
   PASSING_SCORE        int,
   primary key (ID),
   key AK_COU_CODE (CODE)
);

/*==============================================================*/
/* Table: FACULTY                                               */
/*==============================================================*/
create table FACULTY
(
   ID                   int not null auto_increment,
   NAME                 varchar(50) not null,
   DESCRIPTION          varchar(100),
   primary key (ID),
   key AK_FAL_NAME (NAME)
);

/*==============================================================*/
/* Table: INSTRUCTORS                                           */
/*==============================================================*/
create table INSTRUCTORS
(
   ID                   int not null auto_increment,
   FACULTY              int not null,
   CODE                 varchar(20) not null,
   LAST_NAME            varchar(50) not null,
   FIRST_NAME           varchar(20) not null,
   DOB                  date not null,
   GENDER               bool,
   PHONE                varchar(20),
   EMAIL                varchar(30),
   ADDRESS              varchar(150),
   IMAGE                varchar(100),
   primary key (ID),
   key AK_INS_CODE (CODE)
);

/*==============================================================*/
/* Table: MAJORS                                                */
/*==============================================================*/
create table MAJORS
(
   ID                   int not null auto_increment,
   FACULTY              int not null,
   NAME                 varchar(50) not null,
   DESCRIPTION          varchar(100),
   primary key (ID),
   key AK_MAJ_NAME (NAME)
);

/*==============================================================*/
/* Table: MAJOR_COURSE                                          */
/*==============================================================*/
create table MAJOR_COURSE
(
   MAJ_ID               int not null,
   COU_ID               int not null,
   primary key (MAJ_ID, COU_ID)
);

/*==============================================================*/
/* Table: PERMISSIONS                                           */
/*==============================================================*/
create table PERMISSIONS
(
   ID                   int not null auto_increment,
   NAME                 varchar(100) not null,
   primary key (ID),
   key AK_PER_NAME (NAME)
);

/*==============================================================*/
/* Table: REGISTRATION                                          */
/*==============================================================*/
create table REGISTRATION
(
   STU_ID               int not null,
   CLA_ID               int not null,
   AVERAGE              decimal(4,1),
   MIDTERM              int,
   FINAL_EXAM           int,
   ASSIGNMENT           int,
   primary key (STU_ID, CLA_ID)
);

/*==============================================================*/
/* Table: ROLES                                                 */
/*==============================================================*/
create table ROLES
(
   ID                   int not null auto_increment,
   NAME                 varchar(50) not null,
   primary key (ID),
   key AK_ROL_NAME (NAME)
);

/*==============================================================*/
/* Table: ROLE_PERMISSION                                       */
/*==============================================================*/
create table ROLE_PERMISSION
(
   ROL_ID               int not null,
   PER_ID               int not null,
   primary key (ROL_ID, PER_ID)
);

/*==============================================================*/
/* Table: SEMESTERS                                             */
/*==============================================================*/
create table SEMESTERS
(
   ID                   int not null auto_increment,
   NAME                 varchar(20),
   primary key (ID)
);

/*==============================================================*/
/* Table: STUDENTS                                              */
/*==============================================================*/
create table STUDENTS
(
   ID                   int not null auto_increment,
   MAJOR                int not null,
   CODE                 varchar(50) not null,
   PID                  varchar(9) not null,
   LAST_NAME            varchar(50) not null,
   FIRST_NAME           varchar(20) not null,
   DOB                  date,
   POB                  varchar(50),
   GENDER               bool,
   PHONE                varchar(20),
   EMAIL                varchar(30),
   ADDRESS              varchar(150),
   BARCODE              varchar(100),
   IMAGE                varchar(100),
   primary key (ID),
   key AK_STU_CODE (CODE),
   key AK_STU_PID (PID)
);

/*==============================================================*/
/* Table: SUBSCRIBERS                                           */
/*==============================================================*/
create table SUBSCRIBERS
(
   ID                   int not null auto_increment,
   EMAIL                varchar(100),
   primary key (ID)
);

/*==============================================================*/
/* Table: USERS                                                 */
/*==============================================================*/
create table USERS
(
   ID                   int not null auto_increment,
   USERNAME             varchar(50) not null,
   PASSWORD             varchar(30) not null,
   DATE_CREATED         date,
   LAST_NAME            varchar(50),
   FIRST_NAME           varchar(20),
   GENDER               bool,
   PHONE                varchar(20),
   EMAIL                varchar(30),
   ADDRESS              varchar(150),
   primary key (ID),
   key AK_USR_USERNAME (USERNAME)
);

/*==============================================================*/
/* Table: USER_ROLE                                             */
/*==============================================================*/
create table USER_ROLE
(
   USR_ID               int not null,
   ROL_ID               int not null,
   primary key (USR_ID, ROL_ID)
);

/*==============================================================*/
/* Table: YEARS                                                 */
/*==============================================================*/
create table YEARS
(
   ID                   int not null auto_increment,
   VALUE                numeric(4,0),
   primary key (ID)
);

alter table CLASSES add constraint FK_BELONG_TO foreign key (COURSE)
      references COURSES (ID) on delete cascade on update restrict;

alter table CLASSES add constraint FK_CLA_SEM foreign key (SEMESTER)
      references SEMESTERS (ID) on delete cascade on update restrict;

alter table CLASSES add constraint FK_HAPPEN_IN foreign key (YEAR)
      references YEARS (ID) on delete cascade on update restrict;

alter table CLASSES add constraint FK_TAUGHT_BY foreign key (INS_ID)
      references INSTRUCTORS (ID) on delete cascade on update restrict;

alter table INSTRUCTORS add constraint FK_WORK_IN foreign key (FACULTY)
      references FACULTY (ID) on delete cascade on update restrict;

alter table MAJORS add constraint FK_OFFERED_BY foreign key (FACULTY)
      references FACULTY (ID) on delete cascade on update restrict;

alter table MAJOR_COURSE add constraint FK_MAJOR_COURSE foreign key (MAJ_ID)
      references MAJORS (ID) on delete cascade on update restrict;

alter table MAJOR_COURSE add constraint FK_MAJOR_COURSE2 foreign key (COU_ID)
      references COURSES (ID) on delete cascade on update restrict;

alter table REGISTRATION add constraint FK_REGISTRATION foreign key (STU_ID)
      references STUDENTS (ID) on delete cascade on update restrict;

alter table REGISTRATION add constraint FK_REGISTRATION2 foreign key (CLA_ID)
      references CLASSES (ID) on delete cascade on update restrict;

alter table ROLE_PERMISSION add constraint FK_ROLE_PERMISSION foreign key (ROL_ID)
      references ROLES (ID) on delete cascade on update restrict;

alter table ROLE_PERMISSION add constraint FK_ROLE_PERMISSION2 foreign key (PER_ID)
      references PERMISSIONS (ID) on delete cascade on update restrict;

alter table STUDENTS add constraint FK_ENROL_IN foreign key (MAJOR)
      references MAJORS (ID) on delete cascade on update restrict;

alter table USER_ROLE add constraint FK_USER_ROLE foreign key (USR_ID)
      references USERS (ID) on delete cascade on update restrict;

alter table USER_ROLE add constraint FK_USER_ROLE2 foreign key (ROL_ID)
      references ROLES (ID) on delete cascade on update restrict;

