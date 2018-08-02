drop table orders_list;
drop table pay;
drop table store_stock;
drop table head_stock;
drop table admin_order;
drop table orders;
drop table members;
drop table product;


create table product (
    product_id      number,
    product_code    varchar2(20),
    product_kind    varchar2(20),
    product_name    varchar2(20),
    product_epl     long,
    price           number,
    product_ea      number,
    product_date    date,
    Constraint product_product_id_pk primary key (product_id)
    );

    
create table admin_order (
   ad_order_seq     number,
   product_id       number,
   ad_indate        date,
   ad_orderdate     date,
   ad_order_ea      number,
   Constraint admin_order_ad_order_seq_pk Primary key (ad_order_seq),
   Constraint admin_order_product_id_fk foreign key (product_id)
   References product (product_id)
   );
   

  
create table head_stock(
    product_id      number,
    product_ea      number,
    last_date        date,
    Constraint head_stock_product_id_pk primary key (product_id),
    Constraint head_stock_product_id_fk foreign key (product_id)
    References product (product_id)
    );
    
create table store_stock(
    product_id  number,
    product_ea  number,
    last_date    date,
    Constraint store_stock_product_id_pk primary key (product_id),
    Constraint store_stock_product_id_fk foreign key (product_id)
    References product (product_id)
    );
    drop table members ;
create table members(
    member_id       varchar2(20),
    mem_pw          varchar2(20),
    mem_name        varchar2(20),
    e_mail          varchar2(20),
    phone_number    varchar2(30),
    gender          varchar2(50),
    address         varchar2(100),
    point           number,
    Constraint members_member_id_pk primary key (member_id)
    );
    
create table orders(
    order_id        number,
    member_id       varchar2(20),
    product_id      number,
    product_ea      number,
    order_time      date,
    order_code      number,
    price           number,
    Constraint orders_order_id_pk primary key (order_id),
    Constraint orders_member_id_fk foreign key(member_id)
    References members (member_id),
    constraint oders_product_id_fk foreign key(product_id)
    References product (product_id)
    );
    
create table pay (
    order_id        number,
    money           number,
    card            number,
    pay_time        date,
    Constraint pay_order_id_pk primary key (order_id),
    Constraint pay_order_id_fk foreign key (order_id)
    References orders (order_id)
    );
    
create table orders_list(
    order_id        number,
    product_id      number,
    pay_price       number,
    member_id       varchar2(20),
    order_code      number,
    Constraint orders_list_order_id_pk primary key (order_id),
    Constraint orders_list_order_id_fk foreign key (order_id)
    References orders (order_id),
--    constraint orders_list_product_id_fk foreign key (product_id)
--    references orders (product_id),
--    constraint orders_list_order_code_fk foreign key (order_code)
--    References orders (order_code),
    Constraint orders_list_member_id_fk foreign key (member_id)
    References members (member_id)
 );
        
insert all
   into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)  --code는 (본사||매장) kind는 (1: 빵 2: 음료 3: 케잌 4: 자재)
   Values(100, '본사','빵','크림빵','달콤한 크림이 듬쁙 크림빵',1000, 4, to_date(20180406,'yyyymmdd'))    
   into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)  
   Values(101,'본사','빵','단팥빵','달달한 팥앙금이 가득 단팥빵',1000, 3, to_date(20180406,'yyyymmdd'))
   into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
   Values(102,'본사','빵','단팥크림빵','동서양의 조화 퓨전빵',1200, 0, to_date(20180406,'yyyymmdd'))
   into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
   Values(103,'본사','빵','소보루','손이가요 손이가 소보루빵',1200, 5, to_date(20180406,'yyyymmdd'))
   into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
   Values(104,'본사','빵','페스츄리','여러겹의 쫀득한 식감과 버터향이 가득',1500, 3, to_date(20180406,'yyyymmdd'))
   into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
   Values(105,'본사','빵','피자빵','아이들과 어른 둘다 좋아하는 피자빵',1700, 10, to_date(20180406,'yyyymmdd'))
   into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
   Values(200,'매장','빵','마늘빵','마늘맛이나는 마늘빵',2200, 12, to_date(20180406,'yyyymmdd'))
   into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
   Values(201,'매장','빵','식빵','결대로 찢어지는 포근한 식빵',2500, 5, to_date(20180406,'yyyymmdd'))
   into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
   Values(202,'매장','빵','바게트','프랑스 본토의 맛이 있는 바게트빵',2500, 0, to_date(20180406,'yyyymmdd'))
   into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
   Values(203,'매장','빵','맘모스빵','맘모스가 다 먹지 못하는 크기의 빵',3500, 2, to_date(20180406,'yyyymmdd'))
   into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
   Values(204,'매장','빵','모카빵','커피향이 진득하게 나는 모카빵',3000, 3, to_date(20180406,'yyyymmdd'))
   into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
   Values(106,'본사','음료','아메리카노','좋은 원두를 이용한 아메리카노',2000, 10, to_date(20180406,'yyyymmdd'))
   into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
   Values(107,'본사','음료','에스프레소','좋은 원두를 이용한 에스프레소',2000, 5, to_date(20180406,'yyyymmdd'))
   into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
   Values(108,'본사','음료','카페모카','좋은 원두와 우유를 이용한 카페모카',2500, 0, to_date(20180406,'yyyymmdd'))
   into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
    Values(205,'매장','케잌','딸기케잌','딸기 매니아들을 위한 케잌',15500, 2, to_date(20180406,'yyyymmdd'))
    into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
    Values(206,'매장','케잌','생크림케잌','생크림의 포근함과 달콤함이 가득',12500, 3, to_date(20180406,'yyyymmdd'))
    into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
    Values(207,'매장','케잌','초코케잌','찐한 초코코팅이 되어있는 다크초코케잌',15000, 3, to_date(20180406,'yyyymmdd'))
    into Product(product_id, product_code, product_kind, product_name, product_epl, price, product_ea, product_date)
    Values(208,'매장','케잌','아이스크림케잌','아이스크림의 시원함과 부드러움이 케잌속으로',18000, 2, to_date(20180406,'yyyymmdd'))
select * from dual;

insert all
    into MEMBERS(member_id, mem_pw, mem_name, e_mail, phone_number, gender)
    values('gungyea', '1q2w3e','강전욱','kjw@naver.com','010-1234-1234','female')
    into MEMBERS(member_id,  mem_pw, mem_name, e_mail, phone_number, gender)
    values('hibahiba', '1q2w3e','김희은','khe@naver.com','010-1234-1234','female')     
    into MEMBERS(member_id,  mem_pw, mem_name, e_mail, phone_number,  gender)
    values('gamer',  '1q2w3e','남윤지','nyj@naver.com','010-1234-1234','female') 
    into MEMBERS(member_id, mem_pw, mem_name, e_mail, phone_number,  gender)
    values('misterNam','1q2w3e','주현우','jhw@naver.com','010-1234-1234','male') 
    into MEMBERS(member_id,  mem_pw, mem_name, e_mail, phone_number, gender)
    values('whitebear','1q2w3e','이종민','kjw@naver.com','010-1234-1234','male') 
    into MEMBERS(member_id,  mem_pw, mem_name, e_mail, phone_number, gender)
    values('noleader', '1q2w3e','김민수','hukmkms@naver.com','010-7933-5203','male')
     into MEMBERS(member_id,  mem_pw, mem_name, e_mail, phone_number, gender)
    values('admin', '1234','관리자씨','hukmkms@naver.com','010-7933-5203','male')
select * from dual;

insert all                                                  
    into store_stock(product_id, product_ea, last_date)
    values(100, 15, to_date(20180505,'yyyymmdd'))
    into store_stock(product_id, product_ea, last_date)
    values(101, 10, to_date(20180512,'yyyymmdd'))
    into store_stock(product_id, product_ea, last_date)
    values(102, 5, to_date(20180520,'yyyymmdd'))
    into store_stock(product_id, product_ea, last_date)
    values(103, 0, to_date(20180520,'yyyymmdd'))
select * from dual;
   
insert all
    into HEAD_STOCK(product_id, product_ea, last_date)
    values (103,20,to_date(20180505,'yyyymmdd'))
    into HEAD_STOCK(product_id, product_ea, last_date)
    values (104, 10, to_date(20180512,'yyyymmdd'))
    into HEAD_STOCK(product_id, product_ea, last_date)
    values (105, 0, to_date(20180520,'yyyymmdd'))
select * from dual;

drop sequence order_seq;

create sequence order_seq
start with 46 increment by 1;

insert all
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (0,'gungyea', 100, 5, to_date(20180404,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (1,'gungyea', 100, 5, to_date(20180405,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (2, 'hibahiba', 101, 7, to_date(20180405,'yyyymmdd'),1)    
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (3, 'gamer', 102, 6, to_date(20180405,'yyyymmdd'),2) 
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (4, 'noleader', 103, 7, to_date(20180405,'yyyymmdd'),2)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (5, 'gungyea', 104, 5, to_date(20180405,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (6, 'hibahiba', 105, 8, to_date(20180405,'yyyymmdd'),1)    
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (7, 'gamer', 106, 6, to_date(20180405,'yyyymmdd'),2) 
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (8, 'noleader', 200, 5, to_date(20180405,'yyyymmdd'),2)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (9, 'gungyea', 104, 3, to_date(20180406,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (10, 'hibahiba', 200, 3, to_date(20180406,'yyyymmdd'),1)    
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (11, 'gamer', 201, 5, to_date(20180406,'yyyymmdd'),2) 
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (12, 'noleader', 202, 6, to_date(20180406,'yyyymmdd'),2)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (13, 'gungyea', 100, 5, to_date(20180406,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (14, 'hibahiba', 102, 3, to_date(20180406,'yyyymmdd'),1)    
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (15, 'gamer', 104, 5, to_date(20180406,'yyyymmdd'),2) 
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (16, 'noleader', 103, 4, to_date(20180406,'yyyymmdd'),2)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (17, 'gungyea', 106, 4, to_date(20180406,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (18, 'hibahiba', 107, 4, to_date(20180406,'yyyymmdd'),1)    
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (19, 'gamer', 202, 5, to_date(20180406,'yyyymmdd'),2) 
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (20, 'noleader', 103, 12, to_date(20180407,'yyyymmdd'),2)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (21, 'gungyea', 100, 3, to_date(20180407,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (22, 'hibahiba', 101, 4, to_date(20180407,'yyyymmdd'),1)    
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (23, 'gamer', 102, 3, to_date(20180407,'yyyymmdd'),2) 
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (24, 'noleader', 200, 3, to_date(20180407,'yyyymmdd'),2)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (25, 'gungyea', 201, 3, to_date(20180407,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (26, 'hibahiba', 103, 3, to_date(20180407,'yyyymmdd'),1)    
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (27, 'gamer', 104, 5, to_date(20180408,'yyyymmdd'),2) 
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (28, 'noleader', 105, 3, to_date(20180408,'yyyymmdd'),2)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (30,'gungyea', 104, 2, to_date(20180408,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (31,'gungyea', 200, 3, to_date(20180408,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (32,'gungyea', 201, 2, to_date(20180408,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (33,'gungyea', 105, 3, to_date(20180408,'yyyymmdd'),1)

    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (34, 'gamer', 103, 6, to_date(20180409,'yyyymmdd'),2) 
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (35, 'noleader', 104, 5, to_date(20180409,'yyyymmdd'),2)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (36,'gungyea', 102, 4, to_date(20180409,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (37,'gungyea', 201, 4, to_date(20180409,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (38,'gungyea', 200, 4, to_date(20180409,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (39,'gungyea', 100, 5, to_date(20180409,'yyyymmdd'),1)   

    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (40, 'gamer', 102, 6, to_date(20180410,'yyyymmdd'),2) 
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (41, 'noleader', 103, 5, to_date(20180410,'yyyymmdd'),2)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (42,'gungyea', 101, 4, to_date(20180410,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (43,'gungyea', 201, 4, to_date(20180410,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (44,'gungyea', 200, 4, to_date(20180410,'yyyymmdd'),1)
    into orders(order_id, member_id, product_id, product_ea, order_time, order_code)
    values (45,'gungyea', 104, 5, to_date(20180410,'yyyymmdd'),1)   
select * from dual;

insert all
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (1, 200, 10, to_date(20180505,'yyyymmdd'), to_date(20180506,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (2, 201, 10, to_date(20180505,'yyyymmdd'), to_date(20180507,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (3, 202, 10, to_date(20180505,'yyyymmdd'), to_date(20180505,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (4, 203, 10, to_date(20180505,'yyyymmdd'), to_date(20180506,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (5, 204, 10, to_date(20180505,'yyyymmdd'), to_date(20180505,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (6, 205, 10, to_date(20180505,'yyyymmdd'), to_date(20180506,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (7, 206, 10, to_date(20180505,'yyyymmdd'), to_date(20180508,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (8, 207, 10, to_date(20180505,'yyyymmdd'), to_date(20180506,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (9, 208, 10, to_date(20180505,'yyyymmdd'), to_date(20180506,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (10, 100, 10, to_date(20180505,'yyyymmdd'), to_date(20180506,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (11, 101, 10, to_date(20180505,'yyyymmdd'), to_date(20180506,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (12, 102, 10, to_date(20180505,'yyyymmdd'), to_date(20180506,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (13, 103, 10, to_date(20180505,'yyyymmdd'), to_date(20180506,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (14, 104, 10, to_date(20180505,'yyyymmdd'), to_date(20180506,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (15, 105, 10, to_date(20180505,'yyyymmdd'), to_date(20180506,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (16, 106, 10, to_date(20180505,'yyyymmdd'), to_date(20180506,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (17, 107, 10, to_date(20180505,'yyyymmdd'), to_date(20180506,'yyyymmdd'))
   into admin_order(ad_order_seq, product_id, ad_order_ea, ad_orderdate, ad_indate)
   values (18, 108, 10, to_date(20180505,'yyyymmdd'), to_date(20180506,'yyyymmdd'))
   select * from dual;
   
drop Sequence ad_order_seq;

create sequence ad_order_seq start with 34 increment BY 1 maxvalue 999;

insert into admin_order values(ad_order_seq.nextval,100,to_date(20180505,'yyyymmdd'),to_date(20180505,'yyyymmdd'),2);

commit;

--select * 
--from product;
--
--select *
--from orders;
--
--select to_char(o.Order_Time,'mmdd'), o.product_id, o.product_ea, P.Price
--from orders o, product p
--where o.product_id = p.product_id
--	and Order_time > sysdate - 7;
--
--
--update product
--set product_ea = 5
--where product_id = 101;
--
--commit;
--select *
--from product;
--
--select *
--from admin_order;
--
--select *
--from orders;
--select *
--from Members;

--select product_ea
--from product
--where product_id = ?;

--select p.product_id, p.product_code, p.product_kind, p.PRODUCT_NAME, ao.ad_indate, p.price, p.product_ea
--from product p, admin_order ao
--where p.product_id = ao.product_id
--and p.product_code = '본사' 
--order by p.product_id;
--
--select to_char(o.Order_Time,'mmdd'), o.product_id, o.product_ea, P.Price
--from orders o, product p
--where o.product_id = p.product_id
--    and Order_time > sysdate - 7;
--select * 
--from Members;

--update members set point = 1000;
--
update members
set point = 100000000
where member_id = 'admin';
--
insert into members(member_id)
values ('이타치');
--
commit;
--
--select * 
--from orders;

--select o.order_id, p.PRODUCT_NAME, o.PRODUCT_EA, p.PRICE, o.price, o.ORDER_TIME 
--from orders o, PRODUCT p
--where o.PRODUCT_ID = p.PRODUCT_ID 
--and o.MEMBER_ID = 'gungyea'
--order by o.order_time desc;
--
--select to_char(o.Order_Time,'mm"월"dd"일"'), o.product_id, o.product_ea, P.Price
--from orders o, product p
--where o.product_id = p.product_id
--    and Order_time > sysdate - 7
--    order by o.order_time;
--    
--select *
--from product;
--
--select *
--from admin_order;
--
--DELETE from admin_order
--where product_id = 208;
--
--DELETE from product
--where product_id = 208;