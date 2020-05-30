#4.创建表，仅通过import.sql创建并插入数据
#重新构建，运行
insert into users (account,password) values('a123456','123456');
insert into users (account,password) values('b123456','123456');
insert into project_info (buyer_id,pcode,pname,pprice,pscript,pothers)values(1,'001001','苹果1',3.3,'酸酸甜甜苹果','卖家联系qq：268268');
insert into project_info (buyer_id,pcode,pname,pprice,pscript,pothers) values(2,'002001','橘子1',3.3,'酸酸甜甜橘子','卖家联系qq：268268');

#insert into order_info (uid,oprice) values('00001',33);