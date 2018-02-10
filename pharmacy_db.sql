-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Час створення: Лют 10 2018 р., 13:15
-- Версія сервера: 5.7.16-log
-- Версія PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База даних: `pharmacy_db`
--
DROP DATABASE IF EXISTS `pharmacy_db`;
CREATE DATABASE IF NOT EXISTS `pharmacy_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `pharmacy_db`;

-- --------------------------------------------------------

--
-- Структура таблиці `balance` - відповідає таблиці 'Сальдо' в ER-моделі
--

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблиці `cash_flow` - відповідає таблиці 'Рух коштів' в ER-моделі
-- purpose_of_cash - витратили на доставку або отримали за покупку
--

DROP TABLE IF EXISTS `cash_flow`;
CREATE TABLE IF NOT EXISTS `cash_flow` (
  `id_cash_flow` int(11) NOT NULL AUTO_INCREMENT,
  `purpose_of_cash` varchar(50) NOT NULL,
  `total` decimal(13,2) NOT NULL,
  `id_delivery` int(11) NOT NULL,
  `id_purch` int(11) NOT NULL,
  PRIMARY KEY (`id_cash_flow`),
  KEY `id_delivery` (`id_delivery`),
  KEY `id_purch` (`id_purch`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблиці `delivery` - відповідає таблиці 'Поставка' в ER-моделі
--

DROP TABLE IF EXISTS `delivery`;
CREATE TABLE IF NOT EXISTS `delivery` (
  `id_delivery` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `id_pharmacy` int(11) NOT NULL,
  PRIMARY KEY (`id_delivery`),
  KEY `id_pharmacy` (`id_pharmacy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблиці `delivery_medicine` - створена внаслідок звязку 'багато-до-багатьох' між таблицями 'Поставка' та 'Ліки'
--

DROP TABLE IF EXISTS `delivery_medicine`;
CREATE TABLE IF NOT EXISTS `delivery_medicine` (
  `id_delivery` int(11) NOT NULL,
  `id_medicine` int(11) NOT NULL,
  `box_quantity` int(11) NOT NULL,
  KEY `id_medicine` (`id_medicine`),
  KEY `id_delivery` (`id_delivery`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблиці `doctor` - відповідає таблиці 'Лікар' в ER-моделі
--

DROP TABLE IF EXISTS `doctor`;
CREATE TABLE IF NOT EXISTS `doctor` (
  `id_doctor` int(11) NOT NULL AUTO_INCREMENT,
  `surname` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `speciality` varchar(50) NOT NULL,
  `years_of_practice` int(11) NOT NULL,
  PRIMARY KEY (`id_doctor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Дамп даних таблиці `doctor`
--

INSERT INTO `doctor` (`id_doctor`, `surname`, `name`, `speciality`, `years_of_practice`) VALUES
(2, 'М\'ясоєдов', 'Андрій', 'Хірург', 3),
(3, 'Глазнюк', 'Сергій', 'Офтальмолог', 7),
(4, 'Зубнюк', 'Дарина', 'Стоматолог', 18),
(5, 'Селівестрова', 'Катерина', 'Терапевт', 4);

-- --------------------------------------------------------

--
-- Структура таблиці `medicine` - відповідає таблиці 'Ліки' в ER-моделі
--

DROP TABLE IF EXISTS `medicine`;
CREATE TABLE IF NOT EXISTS `medicine` (
  `id_medicine` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `producer` varchar(255) NOT NULL,
  `box_price` decimal(13,2) NOT NULL,
  `quantity_per_box` int(11) NOT NULL,
  PRIMARY KEY (`id_medicine`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Дамп даних таблиці `medicine`
--

INSERT INTO `medicine` (`id_medicine`, `title`, `producer`, `box_price`, `quantity_per_box`) VALUES
(2, 'Travomel', 'Znahar', '100.00', 25),
(3, 'Notta', 'Znahar', '100.00', 30),
(4, 'Mezym', 'Znahar', '100.00', 30),
(5, 'Hilac', 'Znahar', '200.00', 10),
(6, 'Penicillin VK', 'AstraZeneca', '500.00', 24),
(7, 'Diazepam', 'Novartis', '100.00', 24),
(8, 'Metformin HCl', 'Merck', '250.00', 100),
(9, 'Digoxin', 'GlaxoSmithKline', '375.00', 24),
(10, 'Lovaza', 'Pfizer', '750.00', 24),
(11, 'Lorazepam', 'Sanofi', '750.00', 100),
(12, 'Ocuvite Forte', 'JELFA', '220.00', 20),
(13, 'Lorazepam', 'AstraZeneca', '375.00', 24),
(14, 'Penicillin VK', 'Novartis', '500.00', 30);

-- --------------------------------------------------------

--
-- Структура таблиці `patient` - відповідає таблиці 'Пацієнт' в ER-моделі
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `id_patient` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `date_of_birth` date NOT NULL,
  `phone` varchar(16) NOT NULL,
  PRIMARY KEY (`id_patient`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Дамп даних таблиці `patient`
--

INSERT INTO `patient` (`id_patient`, `name`, `surname`, `date_of_birth`, `phone`) VALUES
(2, 'Denzel', 'Washington', '1954-12-28', '039-499-1604'),
(3, 'Leonardo', 'DiCaprio', '1974-11-11', '082-270-6766'),
(4, 'Julia', 'Stiles', '1981-05-28', '085-698-4968'),
(5, 'Tom', 'Hanks', '1956-07-09', '047-688-2690'),
(6, 'Halle', 'Berry', '1966-08-14', '040-801-0454'),
(7, 'Dwayne', 'Johnson', '1975-05-02', '022-304-7031'),
(8, 'Morgan', 'Freeman', '1937-06-01', '036-163-4367');

-- --------------------------------------------------------

--
-- Структура таблиці `pharmacy` - відповідає таблиці 'Аптека' в ER-моделі
--

DROP TABLE IF EXISTS `pharmacy`;
CREATE TABLE IF NOT EXISTS `pharmacy` (
  `id_pharmacy` int(11) NOT NULL AUTO_INCREMENT,
  `pharm_title` varchar(50) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id_pharmacy`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Дамп даних таблиці `pharmacy`
--

INSERT INTO `pharmacy` (`id_pharmacy`, `pharm_title`, `address`) VALUES
(8, 'Green apteka', 'Zelena, 12 str.'),
(9, 'Znahar', 'Levandivka, 12 str.'),
(10, 'АНЦ', 'проспект Бажана, 36'),
(11, 'Соціальна Аптека', 'вул. Цвєтаєвої, 32'),
(12, 'АНЦ', 'вул. Гришко, 6');

-- --------------------------------------------------------

--
-- Структура таблиці `pharmacy_medicine` - створена внаслідок звязку 'багато-до-багатьох' між таблицями 'Аптека' та 'Ліки'
--

DROP TABLE IF EXISTS `pharmacy_medicine`;
CREATE TABLE IF NOT EXISTS `pharmacy_medicine` (
  `id_pharmacy` int(11) NOT NULL,
  `id_medicine` int(11) NOT NULL,
  `pack_price` decimal(13,2) NOT NULL,
  `date_of_change` date NOT NULL,
  `pack_quantity` int(11) NOT NULL,
  PRIMARY KEY (`id_pharmacy`,`id_medicine`,`date_of_change`),
  KEY `id_pharmacy` (`id_pharmacy`),
  KEY `id_medicine` (`id_medicine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблиці `prescription` - відповідає таблиці 'Рецепт' в ER-моделі
--

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

-- --------------------------------------------------------

--
-- Структура таблиці `prescr_medicine` - створена внаслідок звязку 'багато-до-багатьох' між таблицями 'Рецепт' та 'Ліки'
--

DROP TABLE IF EXISTS `prescr_medicine`;
CREATE TABLE IF NOT EXISTS `prescr_medicine` (
  `id_prescr` int(11) NOT NULL,
  `id_medicine` int(11) NOT NULL,
  `pack_quantity` int(11) NOT NULL,
  `pack_bought` int(11) DEFAULT NULL,
  KEY `id_prescr` (`id_prescr`),
  KEY `id_medicine` (`id_medicine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблиці `purchase` - відповідає таблиці 'Покупка' в ER-моделі
--

DROP TABLE IF EXISTS `purchase`;
CREATE TABLE IF NOT EXISTS `purchase` (
  `id_purch` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `id_prescr` int(11) NOT NULL,
  `id_pharmacy` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  PRIMARY KEY (`id_purch`),
  KEY `id_prescr` (`id_prescr`),
  KEY `id_pharmacy` (`id_pharmacy`),
  KEY `id_patient` (`id_patient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблиці `purch_medicine` - створена внаслідок звязку 'багато-до-багатьох' між таблицями 'Покупка' та 'Ліки'
--

DROP TABLE IF EXISTS `purch_medicine`;
CREATE TABLE IF NOT EXISTS `purch_medicine` (
  `id_purch` int(11) NOT NULL,
  `id_medicine` int(11) NOT NULL,
  `pack_quantity` int(11) NOT NULL,
  KEY `id_purch` (`id_purch`),
  KEY `id_medicine` (`id_medicine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Обмеження зовнішнього ключа збережених таблиць
--

--
-- Обмеження зовнішнього ключа таблиці `balance`
--
ALTER TABLE `balance`
  ADD CONSTRAINT `balance_ibfk_1` FOREIGN KEY (`id_cash_flow`) REFERENCES `cash_flow` (`id_cash_flow`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `balance_ibfk_2` FOREIGN KEY (`id_pharmacy`) REFERENCES `pharmacy` (`id_pharmacy`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `cash_flow`
--
ALTER TABLE `cash_flow`
  ADD CONSTRAINT `cash_flow_ibfk_1` FOREIGN KEY (`id_delivery`) REFERENCES `delivery` (`id_delivery`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `cash_flow_ibfk_2` FOREIGN KEY (`id_purch`) REFERENCES `purchase` (`id_purch`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `delivery`
--
ALTER TABLE `delivery`
  ADD CONSTRAINT `delivery_ibfk_1` FOREIGN KEY (`id_pharmacy`) REFERENCES `pharmacy` (`id_pharmacy`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `delivery_medicine`
--
ALTER TABLE `delivery_medicine`
  ADD CONSTRAINT `delivery_medicine_ibfk_1` FOREIGN KEY (`id_medicine`) REFERENCES `medicine` (`id_medicine`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `delivery_medicine_ibfk_2` FOREIGN KEY (`id_delivery`) REFERENCES `delivery` (`id_delivery`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `pharmacy_medicine`
--
ALTER TABLE `pharmacy_medicine`
  ADD CONSTRAINT `pharmacy_medicine_ibfk_1` FOREIGN KEY (`id_pharmacy`) REFERENCES `pharmacy` (`id_pharmacy`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `pharmacy_medicine_ibfk_2` FOREIGN KEY (`id_medicine`) REFERENCES `medicine` (`id_medicine`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `prescription`
--
ALTER TABLE `prescription`
  ADD CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`id_doctor`) REFERENCES `doctor` (`id_doctor`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_patient`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `prescr_medicine`
--
ALTER TABLE `prescr_medicine`
  ADD CONSTRAINT `prescr_medicine_ibfk_1` FOREIGN KEY (`id_prescr`) REFERENCES `prescription` (`id_prescr`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `prescr_medicine_ibfk_2` FOREIGN KEY (`id_medicine`) REFERENCES `medicine` (`id_medicine`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`id_prescr`) REFERENCES `prescription` (`id_prescr`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `purchase_ibfk_2` FOREIGN KEY (`id_pharmacy`) REFERENCES `pharmacy` (`id_pharmacy`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `purchase_ibfk_3` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_patient`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Обмеження зовнішнього ключа таблиці `purch_medicine`
--
ALTER TABLE `purch_medicine`
  ADD CONSTRAINT `purch_medicine_ibfk_1` FOREIGN KEY (`id_purch`) REFERENCES `purchase` (`id_purch`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `purch_medicine_ibfk_2` FOREIGN KEY (`id_medicine`) REFERENCES `medicine` (`id_medicine`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
