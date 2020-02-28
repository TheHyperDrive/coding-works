-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Feb 28, 2020 at 05:35 PM
-- Server version: 8.0.18
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `store_db`
--
CREATE DATABASE IF NOT EXISTS `store_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `store_db`;

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
CREATE TABLE IF NOT EXISTS `inventory` (
  `itemId` varchar(256) NOT NULL,
  `itemName` varchar(20) NOT NULL,
  `itemDesc` text NOT NULL,
  `qty` int(11) NOT NULL,
  `price` double NOT NULL,
  `image` varchar(256) NOT NULL,
  `itemType` varchar(20) NOT NULL,
  `searchTerms` text NOT NULL,
  PRIMARY KEY (`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`itemId`, `itemName`, `itemDesc`, `qty`, `price`, `image`, `itemType`, `searchTerms`) VALUES
('dl01', 'Dell XPS 13', 'Dell · Dell XPS · Dell XPS 13 · Windows OS · 512 GB drive · 16 GB RAM <br/> XPS 13 9380 Laptop', 145, 1099.95, 'resources/images/dellxps13.png', 'laptop', 'dellxps13laptopcomputer'),
('dl02', 'Dell Optiplex 7770', 'Dell · OptiPlex · Windows · All-in-one · 128 GB RAM · 128 GB Storage · Intel CPU <br/> OptiPlex 7770 All-in-One with Intel Unite', 250, 1499.95, 'resources/images/optiplex7770.png', 'desktop', 'delloptiplex7770desktopwindowscomputer'),
('ic01', 'iMac', 'Apple 21.5\" i Mac measures at just 5mm at its edges, and its widescreen display features LED backlighting for vibrant and accurate support for millions of colors. With in-plane switching (Ips) technology, the i Mac has a wide viewing angle. The screen has a 16:9 aspect ratio and a Full HD 1920 x 1080 screen resolution, supported by integrated Intel Iris Plus Graphics 640. The i Mac\'s processor has been upgraded and now houses a 7th-gen Kaby Lake 2.3 GHz Intel Core i5 Dual-Core processor that can be overclocked up to 3.6 GHz, as well as 8GB of 2133 MHz Ddr4 Ram. Visuals are powered by the integrated Intel Iris Plus Graphics 640 chipset, and for storage, there is a 1TB 5400 rpm hard drive. Outfitted with 802.11ac Wi-Fi and backwards compatible with 802.11a/b/g/n for speedy hook-ups to your home network, this i Mac is also configured with Bluetooth 4.2, as well as a Uhs-Ii Sdxc card slot, two 40 Gb/s Thunderbolt 3 ports, four 5 Gb/s USB 3.0 Type-A ports, a Gigabit Ethernet jack, and a Face Time HD camera. It also has a Kensington lock slot for added security.', 100, 1799.95, 'resources/images/imac.png', 'desktop', 'appledesktopimaccomputer'),
('ip01', 'iPhone 11', 'Shoot 4K videos, beautiful portraits, and sweeping landscapes with the all-new dual-camera system. Capture your best low-light photos with Night mode. See true-to-life color in your photos, videos, and games on the 6.1-inch Liquid Retina display. Experience unprecedented performance with A13 Bionic for gaming, augmented reality (AR), and photography. Do more and charge less with long-lasting battery life. Check it out ', 50, 499.95, 'resources/images/iphone11.png', 'phone', 'bappleiphone11aplecell'),
('ip02', 'iPhone 11 Pro', 'A transformative triple-camera system that adds tons of capability without complexity. An unprecedented leap in battery life. And a mind-blowing chip that doubles down on machine learning and pushes the boundaries of what a smartphone can do. Welcome to the iPhone powerful enough to be called Pro', 25, 699.95, 'resources/images/iphone11pro.png', 'phone', 'bappleiphone11procellaple'),
('kf01', 'Kindle Fire', '8\" HD display; 16 or 32 GB of internal storage (up to 400 GB with microSD) 1.3 GHz quad-core processor Up to 10 hours of battery life Alexa hands-free enabled 1.5 GB of RAM 2 MP front-facing camera + 2 MP rear-facing HD camera Dual-band Wi-Fi', 25, 49.99, 'resources/images/kindleFire.png', 'tablet', 'tabletkindlefireamazonmobile'),
('mb01', 'MacBook Pro', 'MacBook Pro elevates the notebook to a whole different level of performance and portability. Wherever your ideas take you, you\'ll get there faster than ever with high-performance processors and memory, advanced graphics, blazing-fast storage, and more.', 100, 1999.95, 'resources/images/macbookpro.png', 'laptop', 'macbookprolaptopbappleaplecomputer'),
('mb02', 'MacBook Air', 'Thinner and lighter, the MacBook Air features a brilliant Retina display with True Tone technology, Touch ID, the next-generation keyboard, and a Force Touch trackpad. The iconic wedge is created from 100 percent recycled aluminum. And with all-day battery life, MacBook Air is your perfectly portable, do-it-all notebook.', 192, 999.95, 'resources/images/macbookair.png', 'laptop', '194'),
('ms01', 'Surface Laptop 3', 'Make a powerful statement, and fuel your ideas with new Surface Laptop 3. Sleek and light, with improved speed, performance, typing comfort, and battery life, it travels with ease and makes every day more productive. Now with improved speed and performance to do what you want, Surface Laptop 3 13.5\" is two times faster than Surface Laptop 2. And whether you\'re creating, catching up, or chilling out, Surface Laptop 3 is ready when you are. Listen to the improved Omnisonic Speakers, now louder with a more natural sound, discreetly hidden below the keyboard. Open effortlessly, and feel the rich, warm Alcantara or cool, new metal.', 200, 999.95, 'resources/images/surfaceLap.png', 'laptop', 'microsoftsurfacelaptop3computerwindows'),
('sg01', 'Samsung Galaxy s10', 'Galaxy S10 is a phone as powerful, intelligent and intuitive as you are. In a radically reimagined interface, with a ridiculously powerful camera and battery, your ideas are unstoppable. Now, you can see more with an all-new nearly bezel-less Cinematic Infinity Display. Go longer with an all-day intelligent battery(1) smart enough to learn from you and big enough to share power wirelessly with other devices. Unlock from any angle with the in-display Ultrasonic Fingerprint ID(2) unlocks in any light and fits seamlessly with your natural grip. Capture epic shots with an all-new ultra-wide lens that sees the world like you do. Bring it, with the next generation of Galaxy.', 50, 399.95, 'resources/images/galaxys10.png', 'phone', 'androidsamsunggalaxys10cellphone'),
('sg02', 'Samsung Galaxy S10e', 'It has a 5.8-inch, 2280 x 1080 (Fhd+) resolution, dynamic Amoled touchscreen cinematic-infinity display that gives more immersive and uninterrupted content with more clarity and detail. The phone has a 10 megapixels front, and 12-megapixels/16 megapixels dual rear camera with an intelligent camera assistant that helps you get the perfect shot with the perfect settings. Galaxy S10e comes with Wireless Power Share that can boost energy to other devices wirelessly, just place the phone on the back of yours, and see it getting charged. The Fingerprint Scanner is moved to the power button that now makes unlocking the phone simple and natural.', 25, 649.95, 'resources/images/galaxys10e.png', 'phone', 'androidsamsunggalaxys10ecellphone');

-- --------------------------------------------------------

--
-- Table structure for table `logins`
--

DROP TABLE IF EXISTS `logins`;
CREATE TABLE IF NOT EXISTS `logins` (
  `email` varchar(50) NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'user',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `logins`
--

INSERT INTO `logins` (`email`, `password`, `type`) VALUES
('admin@admin.net', 'nbgYr0DpdB8=', 'user');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
