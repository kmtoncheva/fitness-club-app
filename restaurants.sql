-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 16, 2024 at 07:07 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fitnes_club`
--

-- --------------------------------------------------------

--
-- Table structure for table `muscle_groups`
--

CREATE TABLE `muscle_groups` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `muscle_groups`
--

INSERT INTO `muscle_groups` (`id`, `name`) VALUES
(5, 'chest'),
(4, 'legs'),
(3, 'back'),
(6, 'arms'),
(8, 'shoulders');

-- --------------------------------------------------------

--
-- Table structure for table `allergens_for_recipe`
--

CREATE TABLE `muscle_groups_for_training` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `muscle_group_id` bigint(20) UNSIGNED NOT NULL,
  `training_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `allergens_for_recipe`
--

INSERT INTO `muscle_groups_for_training` (`id`, `muscle_group_id`, `training_id`) VALUES
(1, 5, 2),
(2, 3, 2),
(3, 4, 2),
(4, 3, 1),
(5, 4, 1),
(6, 6, 1),
(8, 8, 3),
(9, 6, 4),
(11, 4, 5),
(12, 3, 6),
(13, 3, 7);

-- --------------------------------------------------------

--
-- Table structure for table `drinks`
--

CREATE TABLE `equipment` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `difficulty` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `drinks`
--

INSERT INTO `equipment` (`id`, `name`, `type`, `difficulty`) VALUES
(2, 'wine', 'alcohol', 'cold'),
(3, 'water', 'non-alcoholic', 'cold'),
(4, 'cappuccino', 'coffee', 'hot'),
(5, 'orange juice', 'non-alcoholic', 'cold'),
(6, 'iced tea', 'non-alcoholic', 'cold');

-- --------------------------------------------------------

--
-- Table structure for table `drinks_for_recipe`
--

CREATE TABLE `equipment_for_training` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `equipment_id` bigint(20) UNSIGNED NOT NULL,
  `training_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `drinks_for_recipe`
--

INSERT INTO `equipment_for_training` (`id`, `equipment_id`, `training_id`) VALUES
(1, 4, 1),
(2, 3, 4),
(3, 5, 1),
(4, 6, 2),
(7, 3, 5),
(8, 4, 3),
(9, 3, 6),
(10, 5, 7),
(11, 3, 8);

-- --------------------------------------------------------

--
-- Table structure for table `ingredients`
--

CREATE TABLE `exercises` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ingredients`
--

INSERT INTO `exercises` (`id`, `name`) VALUES
(8, 'baking powder'),
(6, 'chocolate chips'),
(4, 'cream'),
(2, 'eggs'),
(5, 'flour'),
(3, 'sugar'),
(7, 'vanilla extract');

-- --------------------------------------------------------

--
-- Table structure for table `ingredients_for_recipe`
--

CREATE TABLE `exercises_for_training` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `exercise_id` bigint(20) UNSIGNED NOT NULL,
  `training_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ingredients_for_recipe`
--

INSERT INTO `exercises_for_training` (`id`, `exercise_id`, `training_id`) VALUES
(1, 4, 1),
(2, 2, 1),
(3, 5, 1),
(4, 2, 4),
(5, 4, 2),
(6, 6, 1),
(7, 7, 2),
(8, 8, 3),
(9, 6, 4),
(10, 7, 4),
(11, 8, 4),
(12, 2, 5),
(13, 2, 6),
(14, 5, 6),
(15, 5, 7),
(16, 4, 7),
(18, 5, 8);

-- --------------------------------------------------------

--
-- Table structure for table `recipes`
--

CREATE TABLE `trainings` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `difficulty` varchar(50) NOT NULL,
  `duration` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `recipes`
--

INSERT INTO `trainings` (`id`, `name`, `type`, `difficulty`, `duration`) VALUES
(1, 'cake', 'dessert', 'cold', 15),
(2, 'cookies', 'breakfast', 'hot', 5),
(3, 'pancakes', 'breakfast', 'hot', 10),
(4, 'sandwich', 'breakfast', 'cold', 10),
(5, 'salad', 'appetizer', 'cold', 8),
(6, 'Spaghetti Bolognese', 'main course', 'hot', 30),
(7, 'Margarita Pizza', 'main course', 'hot', 20),
(8, 'Fruit Salad', 'dessert', 'cold', 15);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `allergens`
--
ALTER TABLE `allergens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `allergens_for_recipe`
--
ALTER TABLE `allergens_for_recipe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `allergen_id` (`allergen_id`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Indexes for table `drinks`
--
ALTER TABLE `drinks`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `drinks_for_recipe`
--
ALTER TABLE `drinks_for_recipe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `drink_id` (`drink_id`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Indexes for table `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `ingredients_for_recipe`
--
ALTER TABLE `ingredients_for_recipe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ingredient_id` (`ingredient_id`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Indexes for table `recipes`
--
ALTER TABLE `recipes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `allergens`
--
ALTER TABLE `allergens`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `allergens_for_recipe`
--
ALTER TABLE `allergens_for_recipe`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `drinks`
--
ALTER TABLE `drinks`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `drinks_for_recipe`
--
ALTER TABLE `drinks_for_recipe`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `ingredients`
--
ALTER TABLE `ingredients`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `ingredients_for_recipe`
--
ALTER TABLE `ingredients_for_recipe`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `recipes`
--
ALTER TABLE `recipes`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `allergens_for_recipe`
--
ALTER TABLE `allergens_for_recipe`
  ADD CONSTRAINT `allergens_for_recipe_ibfk_1` FOREIGN KEY (`allergen_id`) REFERENCES `allergens` (`id`),
  ADD CONSTRAINT `allergens_for_recipe_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`);

--
-- Constraints for table `drinks_for_recipe`
--
ALTER TABLE `drinks_for_recipe`
  ADD CONSTRAINT `drinks_for_recipe_ibfk_1` FOREIGN KEY (`drink_id`) REFERENCES `drinks` (`id`),
  ADD CONSTRAINT `drinks_for_recipe_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`);

--
-- Constraints for table `ingredients_for_recipe`
--
ALTER TABLE `ingredients_for_recipe`
  ADD CONSTRAINT `ingredients_for_recipe_ibfk_1` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`id`),
  ADD CONSTRAINT `ingredients_for_recipe_ibfk_2` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
