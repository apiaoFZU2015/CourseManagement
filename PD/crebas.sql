/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     2015/9/29 14:56:19                           */
/*==============================================================*/


if exists (select 1
            from  sysobjects
           where  id = object_id('�α�')
            and   type = 'U')
   drop table �α�
go

/*==============================================================*/
/* Table: �α�                                                    */
/*==============================================================*/
create table �α� (
   �꼶                   integer              null,
   רҵ                   varchar(88)          null,
   רҵ����                 integer              null,
   �γ�����                 varchar(88)          null,
   ѡ������                 varbinary(88)        null,
   ѧ��                   integer              null,
   ѧʱ                   integer              null,
   ʵ��ѧʱ                 integer              null,
   �ϻ�ѧʱ                 integer              null,
   ��ʼ��                  integer              null,
   ��ֹ��                  integer              null,
   �ον�ʦ                 varchar(88)          null,
   ��ע                   varchar(Max)         null
)
go

