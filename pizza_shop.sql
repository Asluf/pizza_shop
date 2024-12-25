-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 25, 2024 at 01:19 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pizza_shop`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cartId` int(11) NOT NULL,
  `userEmail` varchar(100) NOT NULL,
  `pizzaId` int(11) NOT NULL DEFAULT 0,
  `pizzaName` varchar(100) NOT NULL,
  `crust` varchar(50) NOT NULL,
  `sauce` varchar(50) NOT NULL,
  `cheese` varchar(50) DEFAULT NULL,
  `toppings` text DEFAULT NULL,
  `price` double NOT NULL,
  `totalPrice` double NOT NULL,
  `quantity` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cartId`, `userEmail`, `pizzaId`, `pizzaName`, `crust`, `sauce`, `cheese`, `toppings`, `price`, `totalPrice`, `quantity`) VALUES
(31, 'ah@gmail.com', 11, 'Lankan Seafood Special', 'Thick Crust', 'Tomato Basil', 'Mozzarella', 'Bacon', 2000, 2000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `favorite`
--

CREATE TABLE `favorite` (
  `favoriteId` int(11) NOT NULL,
  `pizzaId` int(11) NOT NULL DEFAULT 0,
  `userEmail` varchar(100) NOT NULL,
  `pizzaName` varchar(100) NOT NULL,
  `crust` varchar(50) NOT NULL,
  `sauce` varchar(50) NOT NULL,
  `cheese` varchar(50) DEFAULT NULL,
  `toppings` text DEFAULT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `favorite`
--

INSERT INTO `favorite` (`favoriteId`, `pizzaId`, `userEmail`, `pizzaName`, `crust`, `sauce`, `cheese`, `toppings`, `price`) VALUES
(7, 13, 'ah@gmail.com', 'Pol Sambol Lovers', 'Thin Crust', 'Pol Sambol Sauce', 'Mozzarella', 'Chilies', 1400);

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedbackId` int(11) NOT NULL,
  `userEmail` varchar(100) NOT NULL,
  `pizzaId` int(11) NOT NULL,
  `pizzaName` varchar(100) NOT NULL,
  `orderId` int(11) NOT NULL,
  `message` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`feedbackId`, `userEmail`, `pizzaId`, `pizzaName`, `orderId`, `message`) VALUES
(1, 'ah@gmail.com', 11, 'Lankan Seafood Special', 8, 'Pizza was awesome');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL,
  `userEmail` varchar(100) NOT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `pizzaId` int(11) NOT NULL DEFAULT 0,
  `pizzaName` varchar(100) NOT NULL,
  `crust` varchar(50) DEFAULT NULL,
  `sauce` varchar(50) DEFAULT NULL,
  `cheese` varchar(50) DEFAULT NULL,
  `toppings` text DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `orderStatus` varchar(50) DEFAULT 'Placed',
  `paymentStatus` varchar(50) DEFAULT 'Pending',
  `createdAt` timestamp NOT NULL DEFAULT current_timestamp(),
  `specialPackaging` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderId`, `userEmail`, `mobile`, `pizzaId`, `pizzaName`, `crust`, `sauce`, `cheese`, `toppings`, `price`, `quantity`, `orderStatus`, `paymentStatus`, `createdAt`, `specialPackaging`) VALUES
(7, 'ah@gmail.com', '0712526716', 0, 'Jao', 'Thin Crust', 'Tomato Basil', 'Mozzarella', 'Pepperoni, Mushrooms, Onions', 1850, 1, 'Placed', 'Paid', '2024-12-25 08:14:59', 1),
(8, 'ah@gmail.com', '0778945612', 11, 'Lankan Seafood Special', 'Thick Crust', 'Tomato Basil', 'Mozzarella', 'Bacon', 2000, 1, 'Delivered', 'Paid', '2024-12-25 08:15:56', 0);

-- --------------------------------------------------------

--
-- Table structure for table `pizza`
--

CREATE TABLE `pizza` (
  `pizzaId` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `crust` varchar(50) DEFAULT NULL,
  `sauce` varchar(50) DEFAULT NULL,
  `cheese` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `discount` int(11) NOT NULL DEFAULT 0,
  `netPrice` int(11) NOT NULL DEFAULT 0,
  `type` varchar(50) NOT NULL DEFAULT 'normal',
  `toppings` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pizza`
--

INSERT INTO `pizza` (`pizzaId`, `name`, `crust`, `sauce`, `cheese`, `price`, `discount`, `netPrice`, `type`, `toppings`) VALUES
(10, 'Spicy Chicken Peri Peri', 'Thin Crust', 'Spicy Peri Peri', 'Mozzarella', 1700.00, 0, 1700, 'normal', 'Pepperoni'),
(11, 'Lankan Seafood Special', 'Thick Crust', 'Tomato Basil', 'Mozzarella', 2000.00, 0, 2000, 'normal', 'Bacon'),
(12, 'Veggie Kurakkan Delight', 'Kurakkan Crust', 'Coconut Sambol Sauce', 'Cheddar', 1500.00, 0, 1500, 'normal', 'Pepperoni'),
(13, 'Pol Sambol Lovers', 'Thin Crust', 'Pol Sambol Sauce', 'Mozzarella', 1400.00, 0, 1400, 'normal', 'Chilies'),
(14, 'Chicken Tikka Masala', 'Stuffed Crust', 'Tikka Masala Sauce', 'Mozzarella', 1800.00, 0, 1800, 'normal', 'Olives'),
(15, 'Devilled Sausage Feast', 'Thick Crust', 'Devilled Sauce', 'Mozzarella', 1900.00, 0, 1900, 'normal', 'Olives'),
(16, 'Paneer Paradise', 'Thin Crust', 'Garlic Butter Sauce', 'Paneer Cheese', 1600.00, 0, 1600, 'normal', 'Onions'),
(17, 'BBQ Mutton', 'Stuffed Crust', 'BBQ Sauce', 'Cheddar', 2200.00, 0, 2200, 'normal', 'Onions'),
(18, 'Hoppers Supreme', 'Rice Flour Crust', 'Coconut Milk Sauce', 'Mozzarella', 1700.00, 0, 1700, 'normal', 'Chilies'),
(19, 'Prawn and Mango Salsa', 'Thin Crust', 'Mango Salsa Sauce', 'Mozzarella', 2100.00, 0, 2100, 'normal', 'Bacon'),
(20, 'Margherita', 'Thin Crust', 'Tomato Basil', 'Mozzarella', 1200.00, 10, 1080, 'offer', 'Olives, Mushrooma'),
(21, 'Pepperoni Feast', 'Thick Crust', 'BBQ', 'Cheddar', 1500.00, 15, 1275, 'offer', 'Pepperoni, Onions'),
(22, 'Panneer Paradise', 'Stuffed Crust', 'Tikka Masala', 'Panneer', 1700.00, 20, 1360, 'offer', 'Panneer');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile` varchar(15) NOT NULL,
  `password` varchar(100) NOT NULL,
  `loyaltyPoints` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `name`, `email`, `mobile`, `password`, `loyaltyPoints`) VALUES
(2, 'asluf', 'a@gmail.com ', '0712526716', '$2a$10$c1bHiwfgHLTqZzdUGnAHnurwLLl32QnuKr36rCWOVk13A5XarKJhO', 0),
(10, 'Ahamed', 'ah@gmail.com', '0750785247', '$2a$10$Bvv5OEJ4vK1RZDzhHTLmS.erWDREfeITye6yDC1sHjSlhunwLRA0a', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cartId`),
  ADD KEY `userEmail` (`userEmail`);

--
-- Indexes for table `favorite`
--
ALTER TABLE `favorite`
  ADD PRIMARY KEY (`favoriteId`),
  ADD KEY `userEmail` (`userEmail`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedbackId`),
  ADD KEY `userEmail` (`userEmail`),
  ADD KEY `pizzaId` (`pizzaId`),
  ADD KEY `orderId` (`orderId`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `userEmail` (`userEmail`);

--
-- Indexes for table `pizza`
--
ALTER TABLE `pizza`
  ADD PRIMARY KEY (`pizzaId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `cartId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `favorite`
--
ALTER TABLE `favorite`
  MODIFY `favoriteId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedbackId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `pizza`
--
ALTER TABLE `pizza`
  MODIFY `pizzaId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`userEmail`) REFERENCES `user` (`email`) ON DELETE CASCADE;

--
-- Constraints for table `favorite`
--
ALTER TABLE `favorite`
  ADD CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`userEmail`) REFERENCES `user` (`email`) ON DELETE CASCADE;

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`userEmail`) REFERENCES `user` (`email`) ON DELETE CASCADE,
  ADD CONSTRAINT `feedback_ibfk_2` FOREIGN KEY (`pizzaId`) REFERENCES `pizza` (`pizzaId`) ON DELETE CASCADE,
  ADD CONSTRAINT `feedback_ibfk_3` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`) ON DELETE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userEmail`) REFERENCES `user` (`email`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
