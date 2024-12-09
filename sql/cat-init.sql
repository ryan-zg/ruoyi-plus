create database `cat`;
use `cat`;

/**
  公共字段：
    `id`                   bigint         not null auto_increment comment '主键',

    `remark`               varchar(50) comment '备注',
    `create_time`          datetime       not null comment '创建时间',
    `create_by`            varchar(20)    not null comment '创建人',
    `update_time`          datetime       not null comment '更新时间',
    `update_by`            varchar(20)    not null comment '更新人',
    `is_deleted`           tinyint(1)     default 0
 */


-- 连锁猫咖门店表 store
DROP TABLE IF EXISTS `store`;

create table `store`
(
    `id`                   bigint         not null auto_increment comment '主键',
    `id_number`            char(10)       not null comment '门店编号',
    `name`                 varchar(20)    not null comment '门店名称',
    `address`              varchar(50)    not null comment '门店地址',
    `city`                 varchar(20) comment '所在城市',
    `state`                varchar(20) comment '所在州/省份',
    `country`              varchar(20) comment '所在国家',
    `contact_phone`        char(11)       not null comment '联系电话',
    `email`                char(18)       not null comment '邮箱',
    `website`              varchar(50) comment '门店网址',
    `opening_hour`         datetime       not null comment '营业时间',
    `closed_hour`          datetime       not null comment '休息时间',
    `manager_name`         varchar(20)    not null comment '店长姓名',
    `manager_contact`      char(11)       not null comment '店长电话',
    `cat_count`            int            not null comment '猫咪数量',
    `description`          varchar(200) comment '门店描述',
    `pet_policy`           tinyint(1)     not null comment '宠物政策。1：允许自带。2：不允许自带',
    `staff_count`          int            not null comment '员工数量',
    `total_cases`          int            not null comment '总橱窗数量',
    `last_inspection_date` datetime       not null comment '上一次检查日期',
    `next_inspection_date` datetime       not null comment '下一次检查日期',
    `foot_traffic`         int            not null comment '门店人流量',
    `x`                    decimal(10, 6) not null comment '经度',
    `y`                    decimal(10, 6) not null comment '纬度',
    `rating`               decimal(2, 1) comment '评分'                                           default 0.0,
    `review_count`         int            not null comment '评论数量',
    `helpful_count`        int            not null comment '点赞数量',
    `status`               tinyint(1)     not null comment '状态。1：正常。2：停业。3：已关闭。4：已撤销' default 1,
    `remark`               varchar(50) comment '备注',
    `create_time`          datetime       not null comment '创建时间',
    `create_by`            varchar(20)    not null comment '创建人',
    `update_time`          datetime       not null comment '更新时间',
    `update_by`            varchar(20)    not null comment '更新人',
    `is_deleted`           tinyint(1)                                                             default 0
);

-- 门店照片表 store_photos
DROP TABLE IF EXISTS `store_photos`;

create table `store_photos`
(
    `id`          bigint       not null auto_increment comment '主键',
    `cat_id`      bigint       not null comment '猫咪id',
    `photo_url`   varchar(200) not null comment '照片地址',
    `description` varchar(50) comment '图片描述',
    `remark`      varchar(50) comment '备注',
    `create_time` datetime     not null comment '创建时间',
    `create_by`   varchar(20)  not null comment '创建人',
    `update_time` datetime     not null comment '更新时间',
    `update_by`   varchar(20)  not null comment '更新人',
    `is_deleted`  tinyint(1) default 0
);

-- 店内商品表 store_goods
DROP TABLE IF EXISTS `store_goods`;

create table `store_goods`
(
    `id`                 bigint       not null auto_increment comment '主键',
    `store_id`           bigint       not null comment '门店id',
    `number`             char(10)     not null comment '商品编号',
    `name`               varchar(20)  not null comment '商品名称',
    `brand`              varchar(20) comment '商品品牌名称',
    `photo_url`          varchar(200) not null comment '商品图片地址',
    `category_type`      tinyint(1)   not null comment '商品类型。1：猫砂。2：猫砂盆。3：猫砂包。4：猫砂球。5：猫砂盘。6：猫砂斗车。7：猫砂斗车包。8：猫砂斗车球。9：猫砂斗车盘。10：猫砂斗车盘包。11：猫砂斗车盘球。12：猫砂斗车',
    `inventory_quantity` int          not null comment '库存数量',
    `sold_quantity`      int          not null comment '已售数量',
    `price`              int          not null comment '商品单价',
    `discount`           int          not null comment '商品折扣',
    `status`             tinyint(1)   not null comment '商品状态。1：上架。2：下架。3：库存不足。4：已售完。5：已下架',
    `remark`             varchar(50) comment '备注',
    `create_time`        datetime     not null comment '创建时间',
    `create_by`          varchar(20)  not null comment '创建人',
    `update_time`        datetime     not null comment '更新时间',
    `update_by`          varchar(20)  not null comment '更新人',
    `is_deleted`         tinyint(1) default 0
);

-- 猫咪表 cat
DROP TABLE IF EXISTS `cat`;

create table `cat`
(
    `id`                 bigint        not null auto_increment comment '主键',
    `breed_id`           bigint        not null comment '品种id',
    `case_id`            bigint        not null comment '猫咪所在橱窗id',
    `id_number`          char(10)      not null comment '猫咪编号',
    `name`               varchar(20)   not null comment '猫咪名称',
    `photo_url`          varchar(200)  not null comment '猫咪照片地址',
    `gender`             tinyint(1)    not null comment '性别。1：男。2：女',
    `age`                int           not null comment '年龄',
    `date_of_birth`      datetime      not null comment '出生日期',
    `color`              varchar(20)   not null comment '毛发颜色',
    `coat_type`          tinyint(1)    not null comment '毛皮类型。1：短毛。2：中毛。3：长毛',
    `weight`             decimal(5, 2) not null comment '体重',
    `personality_traits` varchar(200)  not null comment '个性特点',
    `introduce`          varchar(200)  not null comment '猫咪简介',
    `adoption_status`    tinyint(1)    not null comment '领养状态。1：在养。2：已领养。3：已送走',
    `health_status`      tinyint(1)    not null comment '健康状况。1：健康。2：亚健康。3：不健康',
    `last_checkup_date`  datetime      not null comment '最近检查日期',
    `next_checkup_date`  datetime      not null comment '下一次检查日期',
    `last_feeding_time`  datetime      not null comment '最近一次喂食时间',
    `arrival_date`       datetime      not null comment '领养日期',
    `vaccination_count`  tinyint(1)    not null comment '疫苗针数',
    `remark`             varchar(50) comment '备注',
    `create_time`        datetime      not null comment '创建时间',
    `create_by`          varchar(20)   not null comment '创建人',
    `update_time`        datetime      not null comment '更新时间',
    `update_by`          varchar(20)   not null comment '更新人',
    `is_deleted`         tinyint(1) default 0
);

