/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     2015/9/29 14:56:19                           */
/*==============================================================*/


if exists (select 1
            from  sysobjects
           where  id = object_id('课表')
            and   type = 'U')
   drop table 课表
go

/*==============================================================*/
/* Table: 课表                                                    */
/*==============================================================*/
create table 课表 (
   年级                   integer              null,
   专业                   varchar(88)          null,
   专业人数                 integer              null,
   课程名称                 varchar(88)          null,
   选修类型                 varbinary(88)        null,
   学分                   integer              null,
   学时                   integer              null,
   实验学时                 integer              null,
   上机学时                 integer              null,
   起始周                  integer              null,
   截止周                  integer              null,
   任课教师                 varchar(88)          null,
   备注                   varchar(Max)         null
)
go

