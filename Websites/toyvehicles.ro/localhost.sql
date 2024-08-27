-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 17, 2022 at 04:21 PM
-- Server version: 10.3.36-MariaDB-log
-- PHP Version: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `toyvehic_resource`
--
CREATE DATABASE IF NOT EXISTS `toyvehic_resource` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `toyvehic_resource`;

-- --------------------------------------------------------

--
-- Table structure for table `anunt`
--

CREATE TABLE `anunt` (
  `titlu` varchar(256) NOT NULL,
  `pret` varchar(256) NOT NULL,
  `af` varchar(256) NOT NULL,
  `km` varchar(256) NOT NULL,
  `combustibil` varchar(256) NOT NULL,
  `transmisie` varchar(256) NOT NULL,
  `cm` varchar(256) NOT NULL,
  `descriere` varchar(1000) NOT NULL,
  `page` varchar(256) NOT NULL,
  `status` varchar(256) NOT NULL,
  `link` varchar(256) NOT NULL,
  `video_show` int(32) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `anunt`
--

INSERT INTO `anunt` (`titlu`, `pret`, `af`, `km`, `combustibil`, `transmisie`, `cm`, `descriere`, `page`, `status`, `link`, `video_show`, `id`) VALUES
('Toyota Land Cruiser kzj 73', '17.900', '1993', '320000', 'Diesel', 'Manual', '3.0TD', 'Toyota Land Cruiser kzj 73,\r\nMotor 3.0td kzt,\r\nSuspensie ome,\r\nMaxxis 33\',\r\nJante 10j,\r\nRestaurata complet,\r\nAutoutilitara,\r\nPret 17900.-euro\r\nLoc. Hunedoara,\r\nTel. 0720665336', '604b66770efd35.12572519.php', 'Vandut', 'https://youtu.be/HoR4rEal6Ls', 1, 70),
('Toyota Land Cruiser hj 61', '31000', '1987', '323k', 'Diesel', 'Manual', '4.0TD', 'Toyota Land Cruiser hj 61, \r\nAf.1987, \r\nMotor 4.0turbo diesel, \r\nDifflocks fata/spate,\r\nAer conditionat, \r\n330000km, \r\nProvenienta Italia, fara rugina! \r\nSuspensie noua nemontata,\r\nJante dunlop cu anvelope noi 33â€™ \r\nPret 31000euro', '604b65b0a955f3.87019637.php', 'Vandut', '', 0, 69),
('Nissan Patrol y60', '15900e', '1996', '118000', 'Diesel', 'Manual', '2798', 'Nissan Patrol y60,\r\n118000km,\r\nStare exceptionala, fara rugina,km reali verificabili!\r\nAer Conditionat,\r\nSuspensie noua, bari panhard havy duty, caseta directie havy duty, anvelope noi, troliu nou, ulei filtre si distributie noua!\r\n', '6059cf4b7cc783.09571655.php', 'Vandut', '', 0, 96),
('Mercedes E220 coupe w214', '13900 euro', '1994', '137k', 'Benzina', 'Manual', '2198', 'Mercedes w124 e220 coupe,\r\nStare ireprosabila, ca si nou,\r\nTrapa,\r\nAer conditionat,\r\nScaune electrice si incalzite,\r\nRevizuita recent cu piese originale!\r\n', '6055f1e9923837.08034703.php', 'Disponibil', '', 0, 95),
('Toyota Land Cruiser hdj 80', '15500', '1992', '274000', 'Diesel', 'Manual', '4198', 'Troliu mecanic,\r\nDiferential blocabil fata/spate si central,\r\nAer conditionat,\r\nFara rugina', '606ca0924709b7.83078276.php', 'Vandut', '..', 1, 137),
('Nissan Patrol y61 4.8i tb48', '47500', '2016', '24000', 'Benzina', 'Automatic', '4798', 'Raritate in comunitatea europeana!\r\nNissan Patrol tb48, recent importat si inmatriculat!\r\nStare ca si nou!\r\nDiferential blocabil spate!\r\n255cp', '60660c552e0062.65283724.php', 'Disponibil', '..', 0, 136),
('Toyota Land Cruiser kzj 77', '24500', '1993', '300000', 'Diesel', 'Manual', '2998', 'Toyota Land Cruiser kzj 77,\r\nRecent reconditionata, un fost lj78 modificat profesional in kzj 77!\r\nIn videoclipul de mai jos puteti viziona reconditionarea totala!', '6062ef65affce6.53156275.php', 'Disponibil', 'https://youtu.be/mBHGR-gwM6E', 1, 125),
('Toyota Land Cruiser lj73', '11500', '1992', '200000', 'Diesel', 'Manual', '2446', 'Stare excelenta, fara rugina!\r\nAutoturism in acte!\r\nAnvelope noi', '60832580b9d007.32983537.php', 'Vandut', '', 0, 138),
('Toyota Land Cruiser hdj80', '15900', '1991', '450000', 'Diesel', 'Automatic', '4198', 'Masina in stare decenta!\r\nAer conditionat fata/spate,\r\nSe incadreaza la retromobil!\r\nJante Mikey Thompson Nsr cu anvelope noi! \r\nDiferential central blocabil!\r\n', '60941795799396.37062175.php', 'Vandut', '', 0, 139),
('Toyota Land Cruiser lj70', '14900', '1988', '188000', 'Diesel', 'Manual', '2446', 'Reconditionat recent cu piese originale!\r\nSe incadreaza la retromobil!\r\nAutoutilitara in acte!\r\n', '60ad14d83c4d11.15591400.php', 'Disponibil', '', 0, 140),
('Iveco Bremach Trekking 4x4', '15900 euro', '1996', '66000', 'Diesel', 'Manual', '2798', 'Iveco Bremach Trekking 4x4,\r\nMotor 2.8 td iveco,\r\n3 blocanti fata/spate si central,\r\n2 reductoare,\r\nBasculabil pe 3 parti\r\n', '60e3476128dff4.81802202.php', 'Vandut', '', 0, 141),
('TOYOTA LAND CRUISER 105', '49000 euro', '2003', '300000', 'Diesel', 'Manual', '4198', 'De vanzare:\r\nToyota Land Cruiser 105 GX,\r\nAf.2003,\r\nMotor 4.2td hdft 24v, omologat!\r\nAutoutilitara 4 locuri,\r\nDiferential blocabil fata/spate si central,\r\nDoua rezervoare,\r\nMasina este revizuita de la A-Z, nu are rugina!\r\nSuspensie noua Koni +5 reglabila,\r\nAnvelope si jante noi!\r\nTroliu Warn,\r\nNavigatie, \r\nAer conditionat,\r\nGeamuri electrice!\r\n\r\nDIN PUNCTUL MEU DE VEDERE, CEL MAI REUSIT MODEL DE LA TOYOTA!\r\nO masina de lux ptr expeditii!\r\n\r\nPRET 49000.-euro\r\nTel.0720665336', '613396e2b6bfe9.16790063.php', 'Vandut', '', 0, 142),
('Audi S6', '10000â‚¬', '2001', '200.000', 'Benzina', 'Automatic', '4.2', 'Audi S6 \r\nAf:2001\r\n200.000km \r\n4.2 benzina \r\n350 cp\r\nMasina este in stare originala si foarte ingrijita.Nu a avut evenimente rutiere.\r\nPret: 10.000â‚¬\r\nMasina se poate vedea in Hunedoara.\r\nPosibilitate finantare prin TBI credit .', '6200f8587ef163.85183417.php', 'Disponibil', '', 0, 144),
('Mercedes ML', '23.000â‚¬', '2012', '300.000', 'Diesel', 'Automatic', '3.0', 'MERCEDES ML 350\r\nAf:2012\r\n300.000km\r\nMotor:3.0d\r\nRoti 21â€™ + anvelope de iarna noi\r\nProvenienta Germaniat cu istoric si carte service.\r\nPret:23.000â‚¬\r\nMasina se poate vedea in Hunedoara.', '6200f9b155f8b0.00808001.php', 'Disponibil', '', 0, 145),
('BMW 116 F20', '6200â‚¬', '2012', '260.000', 'Diesel', 'Manual', '1.6', 'BMW 116 F20\r\n\r\n1.6 diesel\r\nAf: 2012\r\nKm: 260.000\r\nPret: 6200â‚¬ \r\nâ€¼ï¸150 euro/luna avans 0% TBI CREDITâ€¼ï¸', '6200fa2023de87.36272666.php', 'Disponibil', '', 0, 146),
('Mercedes G-Klass 500', '39.000â‚¬', '2004', '113.000 mile', 'Benzina', 'Manual', '5000', 'Mercedes G Klass 500\r\n-Af: 2004;\r\n-113 000 mile;\r\n-Service-ul a fost efectuat doar in reteaua Mercedes;\r\n-Masina este extrem de bine intretinuta si fara urme de rugina;\r\n-Interiorul este bine ingrijit;\r\n-Este o masina de garaj, adusa din State!\r\n\r\n-Autoutilitara cu 4 locuri, rar efectuat!\r\nPret: 39000â‚¬\r\nLoc: Hunedoara\r\nTel: 0720665336', '6200fc3d5bc948.66267705.php', 'Disponibil', '', 0, 147),
('BMW E28 (S) M5', '39.000â‚¬', '1988', '230.000', 'Benzina', 'Manual', '3.5', 'Bmw e28 M5 af.1988!\r\n\r\nÃŽn anul 1985, BMW M5 (E28S) era pentru prima datÄƒ prezentat, È™i Ã®ncÄƒ doi ani construit la BMW M GmbH. La exterior automobilul era un sedan BMW 5 fÄƒrÄƒ modificÄƒri. DiferenÈ›a era motorul cu È™ase cilindrii Ã®n linie, patru supape la cilindru, cilindreea de 3,5 litri cu o putere de 286 CP. Acest motor era motorul lui BMW M1, cu care 5-ul ajungea o vitezÄƒ de 100 km/h Ã®n numai 6,1 s. Viteza maximÄƒ era de 251 km/h. Primul M5 avea, ca È™i toÈ›i succesorii sÄƒi, antrenarea pe roÈ›ile din spate È™i era echipat cu o cutie de schimbare manualÄƒ, de 5+1 viteze. Primele automobile fabricate Ã®n anul 1985, cu echipamentul de bazÄƒ, erau vÃ¢ndute la preÈ›ul de 81.000 DM.\r\nMasina are 230000km, este importata din America si nu are rugina absolut deloc!\r\nVopseaua este originala, in stare excelenta. Interiorul este bine intretinut, dar isi arata vechimea!\r\n\r\nPRET 39000â‚¬\r\nTel.0720665336\r\nHunedoara', '6200fcb644d5a6.73466598.php', 'Disponibil', '', 0, 148);

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `path` varchar(256) NOT NULL,
  `id` int(11) NOT NULL,
  `nr` int(32) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`path`, `id`, `nr`) VALUES
('604b66770f4355.60177302.jpg', 70, 6),
('604b65b2cdeaa6.56534277.jpeg', 69, 12),
('606ca092480aa7.47159921.jpeg', 137, 162),
('6061d7cd2eca71.46401836.jpg', 104, 67),
('6061d806c0f7d8.57012528.jpg', 105, 68),
('60660c5dbb38e4.21058825.jpeg', 136, 161),
('60832580bacc97.27245686.jpeg', 138, 182),
('6094179d37cb74.30725600.jpeg', 139, 205),
('60ad14e3d41290.61889943.jpeg', 140, 223),
('60e34763d05cd8.20406097.jpeg', 141, 230),
('613396ea30f5b2.82525610.jpeg', 142, 239),
('6200f85ae1de91.25081852.jpeg', 144, 257),
('6200f9b1577876.71607340.jpeg', 145, 260),
('6200fa211228f4.54791852.jpeg', 146, 270),
('6200fc3d5c9b55.94143661.jpeg', 147, 274),
('6200fcb6459f91.45913017.jpeg', 148, 275);

-- --------------------------------------------------------

--
-- Table structure for table `img_idx`
--

CREATE TABLE `img_idx` (
  `path` varchar(256) NOT NULL,
  `id` int(11) NOT NULL,
  `nr` int(32) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `img_idx`
--

INSERT INTO `img_idx` (`path`, `id`, `nr`) VALUES
('604b66770f4355.60177302.jpg', 70, 1),
('604b65b2cdeaa6.56534277.jpeg', 69, 2),
('6059cf4c1cb229.03447836.jpeg', 96, 3),
('6055f1edba7222.90619716.jpeg', 95, 4),
('606ca092480aa7.47159921.jpeg', 137, 20),
('6061d7cd2eca71.46401836.jpg', 104, 6),
('60660c5dbb38e4.21058825.jpeg', 136, 19),
('6062ef68a20717.97367586.jpeg', 125, 8),
('60832580bacc97.27245686.jpeg', 138, 21),
('6094179d37cb74.30725600.jpeg', 139, 22),
('60ad14e3d41290.61889943.jpeg', 140, 23),
('60e34763d05cd8.20406097.jpeg', 141, 24),
('613396ea30f5b2.82525610.jpeg', 142, 25),
('6200f85ae1de91.25081852.jpeg', 144, 27),
('6200f9b1577876.71607340.jpeg', 145, 28),
('6200fa211228f4.54791852.jpeg', 146, 29),
('6200fc3d5c9b55.94143661.jpeg', 147, 30),
('6200fcb6459f91.45913017.jpeg', 148, 31);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `username` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `username`, `password`) VALUES
(1, 'toy', 'ktmexc');

-- --------------------------------------------------------

--
-- Table structure for table `tumb`
--

CREATE TABLE `tumb` (
  `path` varchar(256) NOT NULL,
  `id` int(30) NOT NULL,
  `nr` int(32) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tumb`
--

INSERT INTO `tumb` (`path`, `id`, `nr`) VALUES
('604b65b2cdeaa6.56534277.jpeg', 69, 6),
('604b66770f4355.60177302.jpg', 70, 7),
('606ca092480aa7.47159921.jpeg', 137, 162),
('6061d7cd2eca71.46401836.jpg', 104, 67),
('6061d806c0f7d8.57012528.jpg', 105, 68),
('60660c5dbb38e4.21058825.jpeg', 136, 161),
('60832580bacc97.27245686.jpeg', 138, 182),
('6094179d37cb74.30725600.jpeg', 139, 205),
('60ad14e3d41290.61889943.jpeg', 140, 223),
('60e34763d05cd8.20406097.jpeg', 141, 230),
('613396ea30f5b2.82525610.jpeg', 142, 239),
('6200f85ae1de91.25081852.jpeg', 144, 257),
('6200f9b1577876.71607340.jpeg', 145, 260),
('6200fa211228f4.54791852.jpeg', 146, 270),
('6200fc3d5c9b55.94143661.jpeg', 147, 274),
('6200fcb6459f91.45913017.jpeg', 148, 275);

-- --------------------------------------------------------

--
-- Table structure for table `video`
--

CREATE TABLE `video` (
  `path` varchar(256) NOT NULL,
  `img` varchar(256) NOT NULL,
  `id` int(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `video`
--

INSERT INTO `video` (`path`, `img`, `id`) VALUES
('https://youtu.be/7pnDHU68XFw', '6054ac6e21e301.46379489.jpg', 2),
('https://youtu.be/Lzh6pJkOdt0', '6054ada66dde83.53594475.jpg', 3),
('https://youtu.be/LRrF4hn598U', '6054af8f3fd027.36939034.jpg', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anunt`
--
ALTER TABLE `anunt`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`nr`);

--
-- Indexes for table `img_idx`
--
ALTER TABLE `img_idx`
  ADD PRIMARY KEY (`nr`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tumb`
--
ALTER TABLE `tumb`
  ADD PRIMARY KEY (`nr`);

--
-- Indexes for table `video`
--
ALTER TABLE `video`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `anunt`
--
ALTER TABLE `anunt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=149;

--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `nr` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=288;

--
-- AUTO_INCREMENT for table `img_idx`
--
ALTER TABLE `img_idx`
  MODIFY `nr` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tumb`
--
ALTER TABLE `tumb`
  MODIFY `nr` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=288;

--
-- AUTO_INCREMENT for table `video`
--
ALTER TABLE `video`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
