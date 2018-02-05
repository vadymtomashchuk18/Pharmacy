
--
-- 1 ========== cash_flow ==============
DROP TABLE IF EXISTS `cash_flow`;
CREATE TABLE IF NOT EXISTS `cash_flow` (
    `id_cash_flow` int(11) NOT NULL AUTO_INCREMENT,
    `purpose_of_cash` varchar(50) NOT NULL,
    `total` decimal(13,2) NOT NULL,
    `id_delivery` int(11) NOT NULL,
    `id_purch` int(11) NOT NULL,
    `id_balance` int(11) NOT NULL,
    PRIMARY KEY (`id_cash_flow`),
    KEY `id_delivery` (`id_delivery`),
    KEY `id_purch` (`id_purch`),
    KEY `id_balance` (`id_balance`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

-- 2 ========== balance ==============
DROP TABLE IF EXISTS `balance`;
CREATE TABLE IF NOT EXISTS `balance` (
  `id_balance` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `income` decimal(13,2) NOT NULL,
  `spending` decimal(13,2) NOT NULL,
  `cash_balance` decimal(13,2) NOT NULL,
  `id_cash_flow` int(11) NOT NULL,
  `id_pharmacy` int(11) NOT NULL,
  PRIMARY KEY (`id_balance`),
  KEY `id_cash_flow` (`id_cash_flow`),
  KEY `id_pharmacy` (`id_pharmacy`) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 3 ========== pharmacy ==============
DROP TABLE IF EXISTS `pharmacy`;
CREATE TABLE IF NOT EXISTS `pharmacy` (
  `id_pharmacy` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `address` varchar(255) NOT NULL,
  `extra` double NOT NULL,
  PRIMARY KEY (`id_pharmacy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 4 ========== purchase ==============
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE IF NOT EXISTS `purchase` (
  `id_purch` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `id_prescr` int(11) NOT NULL,
  `id_pharmacy` int(11) NOT NULL,
  PRIMARY KEY (`id_purch`),
  KEY `id_prescr` (`id_prescr`),
  KEY `id_pharmacy` (`id_pharmacy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 5 ========== delivery ==============
DROP TABLE IF EXISTS `delivery`;
CREATE TABLE IF NOT EXISTS `delivery` (
  `id_delivery` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `id_pharmacy` int(11) NOT NULL,
  `id_cash_flow` int(11) NOT NULL,
  PRIMARY KEY (`id_delivery`),
  KEY `id_pharmacy` (`id_pharmacy`),
  KEY `id_cash_flow` (`id_cash_flow`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 6 ========== medicine ==============
DROP TABLE IF EXISTS `medicine`;
CREATE TABLE IF NOT EXISTS `medicine` (
  `id_medicine` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `producer` varchar(255) NOT NULL,
  `box_price` decimal(13,2) NOT NULL,
  `quantity_per_box` int(11) NOT NULL,
  PRIMARY KEY (`id_medicine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 7 ========== delivery_medicine ==============
DROP TABLE IF EXISTS `delivery_medicine`;
CREATE TABLE IF NOT EXISTS `delivery_medicine` (
  `id_delivery` int(11) NOT NULL,
  `id_medicine` int(11) NOT NULL,
  `box_quantity` int(11) NOT NULL,
  KEY `id_medicine` (`id_medicine`),
  KEY `id_delivery` (`id_delivery`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 8 ========== pharmacy_medicine ==============
DROP TABLE IF EXISTS `pharmacy_medicine`;
CREATE TABLE IF NOT EXISTS `pharmacy_medicine` (
  `id_pharmacy` int(11) NOT NULL,
  `id_medicine` int(11) NOT NULL,
  `pack_price` decimal(13,2) NOT NULL,
  `pack_quantity` int(11) NOT NULL,
  KEY `id_pharmacy` (`id_pharmacy`),
  KEY `id_medicine` (`id_medicine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 9 ========== doctor ==============
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE IF NOT EXISTS `doctor` (
  `id_doctor` int(11) NOT NULL AUTO_INCREMENT,
  `surname` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `occupation` varchar(50) NOT NULL,
  `experience` int(11) NOT NULL,
  PRIMARY KEY (`id_doctor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 10 ========== patient ==============
DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `id_patient` int(11) NOT NULL AUTO_INCREMENT,
  `surname` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(16) NOT NULL,
  PRIMARY KEY (`id_patient`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 11 ========== prescription ==============
DROP TABLE IF EXISTS `prescription`;
CREATE TABLE IF NOT EXISTS `prescription` (
  `id_prescr` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `id_doctor` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  PRIMARY KEY (`id_prescr`),
  KEY `id_doctor` (`id_doctor`),
  KEY `id_patient` (`id_patient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 12 ========== prescr_medicine ==============
DROP TABLE IF EXISTS `prescr_medicine`;
CREATE TABLE IF NOT EXISTS `prescr_medicine` (
  `id_prescr` int(11) NOT NULL,
  `id_medicine` int(11) NOT NULL,
  `pack_quantity` int(11) NOT NULL,
  `pack_bought` int(11) DEFAULT NULL,
  KEY `id_prescr` (`id_prescr`),
  KEY `id_medicine` (`id_medicine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 13 ========== purchase_medicine ==============
DROP TABLE IF EXISTS `purch_medicine`;
CREATE TABLE IF NOT EXISTS `purch_medicine` (
  `id_purch` int(11) NOT NULL,
  `id_medicine` int(11) NOT NULL,
  `pack_quantity` int(11) NOT NULL,
  KEY `id_purch` (`id_purch`),
  KEY `id_medicine` (`id_medicine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

