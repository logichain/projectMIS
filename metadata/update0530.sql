ALTER TABLE `newprojectmis`.`project_budget` 
ADD COLUMN `pb_onaccount` INT NULL AFTER `pb_type`;
ALTER TABLE `newprojectmis`.`check_task` 
ADD COLUMN `ct_project_budget` INT NULL AFTER `ct_project_approval_record`;

DROP TABLE IF EXISTS `budget_item_model`;
CREATE TABLE `budget_item_model` (
  `bim_id` int(10) NOT NULL AUTO_INCREMENT,
  `bim_code` varchar(45) DEFAULT NULL,
  `bim_name` varchar(45) DEFAULT NULL,
  `bim_tender_editable` int(11) DEFAULT NULL,
  `bim_apply_editable` int(11) DEFAULT NULL,
  `bim_order` int(11) DEFAULT NULL,
  `bim_gather_flag` int(11) DEFAULT NULL,
  `bim_tender_flag` int(11) DEFAULT NULL,
  `bim_apply_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`bim_id`),
  UNIQUE KEY `bim_id_UNIQUE` (`bim_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=gbk;

LOCK TABLES `budget_item_model` WRITE;
/*!40000 ALTER TABLE `budget_item_model` DISABLE KEYS */;
INSERT INTO `budget_item_model` VALUES (1,NULL,'Ԥ����Ŀ����(�ݶ�)',0,0,1,1,1,1),(2,'1)','�豸����',1,1,2,0,1,1),(3,'2)','��װ���뼰����',1,1,3,0,1,1),(4,NULL,'��Ŀ�ɱ�(�ݶ�)',0,0,4,1,1,1),(5,'1)','�豸�ɱ�',1,1,5,0,1,1),(6,'2)','��װ�ɱ�������',1,1,6,0,1,1),(7,NULL,'��Ŀ���',0,0,7,1,1,1),(8,'1)','��Ŀ�豸���',1,1,8,0,1,1),(9,'2)','��Ŀ��װ���',1,1,9,0,1,1),(10,NULL,'�����ɱ�',0,0,10,1,1,1),(11,'1)','Ͷ�����',1,1,11,0,1,1),(12,'2)','��Ŀ����ѣ�����ѣ�',1,1,12,0,1,1),(13,'3)','��������',1,1,13,0,1,1),(14,'4)','�渶�ʽ���Ϣ',1,1,14,0,1,1),(15,'5)','�б�����',1,1,15,0,1,1),(16,NULL,'��Ŀë��',0,0,16,1,1,1),(17,NULL,'�г���Ŀ����',1,0,17,1,1,NULL),(18,NULL,'����ʵʩ��Ŀ����',1,0,18,1,1,1),(19,'1)','�˹���(�����г���Ա)',1,1,19,0,NULL,1),(20,'2)','���̱���',1,1,20,0,NULL,1),(21,'3)','��Ŀ����Ա����',1,1,21,0,NULL,1),(22,'4)','������',1,1,22,0,NULL,1),(23,'5)','ҵ���',1,1,23,0,NULL,1),(24,'6)','��Ŀ�������',1,1,24,0,NULL,1),(25,'7)','��Ŀ����֧',1,1,25,0,NULL,1),(26,'8)','ס��',1,1,26,0,NULL,1),(27,'9)','���÷�',1,1,27,0,NULL,1),(28,'10)','ͨѶ��',1,1,28,0,NULL,1),(29,'11)','�ֳ�����',1,1,29,0,NULL,1),(30,'12)','��ȫ��������',1,1,30,0,NULL,1),(31,NULL,'˰�𼰸���',0,0,31,1,1,1),(32,'1)','��ֵ˰������',1,1,32,0,1,1),(33,'2)','Ӫҵ˰������',1,1,33,0,1,1),(34,'3)','ӡ��˰',1,1,34,0,1,1),(35,NULL,'�������',0,0,35,1,1,NULL),(36,'1)','����',1,1,36,0,1,NULL),(37,NULL,'�ɱ�˰����úϼ�',0,0,37,1,1,1),(38,NULL,'��Ŀ����',0,0,38,1,1,1),(39,NULL,'��Ŀ������',0,0,39,1,1,1);
/*!40000 ALTER TABLE `budget_item_model` ENABLE KEYS */;
UNLOCK TABLES;

delete  FROM newprojectmis.budget_item where bi_id > 0;
delete  FROM newprojectmis.project_budget where pb_id > 0;

INSERT INTO `newprojectmis`.`post` (`p_id`, `p_name`, `p_flag`) VALUES ('14', '��Ŀ�г�����', '1');
ALTER TABLE `newprojectmis`.`project_approval_record` 
ADD COLUMN `par_implement_dept` INT NULL AFTER `par_dept`;

ALTER TABLE `newprojectmis`.`tender_project` 
ADD COLUMN `tp_tenderdoc_check_status` INT NULL AFTER `tp_dept`;

DROP TABLE IF EXISTS `tender_attachment`;
CREATE TABLE `tender_attachment` (
  `ta_id` int(11) NOT NULL AUTO_INCREMENT,
  `ta_tender_project` int(11) DEFAULT NULL,
  `ta_attachment_category` int(11) DEFAULT NULL,
  `ta_code` varchar(45) DEFAULT NULL,
  `ta_name` varchar(100) DEFAULT NULL,
  `ta_url` varchar(100) DEFAULT NULL,
  `ta_flag` int(11) NOT NULL,
  `ta_create_user` varchar(45) NOT NULL,
  `ta_create_time` datetime NOT NULL,
  `ta_local_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ta_id`),
  UNIQUE KEY `ta_id_UNIQUE` (`ta_id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=gbk;

INSERT INTO `newprojectmis`.`attachment_category` (`ac_id`, `ac_name`, `ac_flag`) VALUES ('6', '����', '-1');

ALTER TABLE `newprojectmis`.`project_approval_record` 
ADD COLUMN `par_market_manager` VARCHAR(45) NULL AFTER `par_implement_dept`;
ALTER TABLE `newprojectmis`.`tender_project` 
ADD COLUMN `tp_market_manager` VARCHAR(45) NULL AFTER `tp_tenderdoc_check_status`;

ALTER TABLE `newprojectmis`.`project_contract` 
ADD COLUMN `pc_category` INT NULL AFTER `pc_quality_amount_percent`;
update project_contract set pc_category = 1 where pc_id > 0;


CREATE TABLE `customer_attachment` (
  `ca_id` int(11) NOT NULL AUTO_INCREMENT,
  `ca_customer` int(11) DEFAULT NULL,
  `ca_name` varchar(100) DEFAULT NULL,
  `ca_url` varchar(100) DEFAULT NULL,
  `ca_flag` int(11) NOT NULL,
  `ca_create_user` varchar(45) NOT NULL,
  `ca_create_time` datetime NOT NULL,
  `ca_local_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ca_id`),
  UNIQUE KEY `ca_id_UNIQUE` (`ca_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

