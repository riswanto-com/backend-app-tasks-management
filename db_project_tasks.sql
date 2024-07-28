-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 28, 2024 at 11:02 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_project_tasks`
--

-- --------------------------------------------------------

--
-- Table structure for table `history_log`
--

CREATE TABLE `history_log` (
  `his_log_id` bigint(20) NOT NULL,
  `his_log_action` varchar(255) DEFAULT NULL,
  `his_log_create_date` datetime(6) DEFAULT NULL,
  `his_log_description` tinytext DEFAULT NULL,
  `his_log_project_id` bigint(20) DEFAULT NULL,
  `his_log_tasks_id` bigint(20) DEFAULT NULL,
  `his_log_create_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `history_log`
--

INSERT INTO `history_log` (`his_log_id`, `his_log_action`, `his_log_create_date`, `his_log_description`, `his_log_project_id`, `his_log_tasks_id`, `his_log_create_id`) VALUES
(1, 'Add project', '2024-07-28 10:17:00.000000', 'Membuat project dengan namaMembuat apa', 1, NULL, 1),
(2, 'Add project', '2024-07-28 10:17:33.000000', 'Membuat project dengan namaMembuat apa', NULL, 1, 1),
(3, 'Add project', '2024-07-28 10:25:51.000000', 'Membuat project dengan namaMembuat apa', 2, NULL, 1),
(4, 'Add project', '2024-07-28 10:31:38.000000', 'Membuat project dengan namaMembuat apa', NULL, 2, 1),
(5, 'Add project', '2024-07-28 10:33:02.000000', 'Membuat project dengan namaMembuat xxxxxxx', NULL, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `note`
--

CREATE TABLE `note` (
  `id` bigint(20) NOT NULL,
  `note_active` bit(1) DEFAULT NULL,
  `note_create_date` datetime(6) DEFAULT NULL,
  `note_description` varchar(1000) DEFAULT NULL,
  `note_type` bit(1) DEFAULT NULL,
  `note_project_id` bigint(20) DEFAULT NULL,
  `note_tasks_id` bigint(20) DEFAULT NULL,
  `note_create_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `note`
--

INSERT INTO `note` (`id`, `note_active`, `note_create_date`, `note_description`, `note_type`, `note_project_id`, `note_tasks_id`, `note_create_id`) VALUES
(1, b'1', '2024-07-28 11:06:09.000000', 'Membuat apa', b'1', NULL, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `project_id` bigint(20) NOT NULL,
  `project_active` bit(1) DEFAULT NULL,
  `project_create_date` datetime(6) DEFAULT NULL,
  `project_create_id` int(11) DEFAULT NULL,
  `project_description` tinytext DEFAULT NULL,
  `project_end_date` datetime(6) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `project_status_id` int(11) DEFAULT NULL,
  `project_user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`project_id`, `project_active`, `project_create_date`, `project_create_id`, `project_description`, `project_end_date`, `project_name`, `project_status_id`, `project_user_id`) VALUES
(1, b'1', '2024-07-28 10:17:00.000000', 1, 'jiwa suraya', NULL, 'Membuat apa', 1, 1),
(2, b'1', '2024-07-28 10:25:51.000000', 1, 'jiwa suraya', NULL, 'Membuat apa', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` enum('ROLE_ADMIN','ROLE_MODERATOR','ROLE_USER') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_MODERATOR'),
(3, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `status_id` int(11) NOT NULL,
  `status_available` bit(1) DEFAULT NULL,
  `status_name` varchar(100) DEFAULT NULL,
  `status_type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`status_id`, `status_available`, `status_name`, `status_type`) VALUES
(1, b'1', 'ToDo', 'PROJECT'),
(2, b'1', 'In process', 'PROJECT'),
(3, b'1', 'ToDo', 'TASKS');

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `tasks_id` bigint(20) NOT NULL,
  `tasks_active` bit(1) DEFAULT NULL,
  `tasks_create_date` datetime(6) DEFAULT NULL,
  `tasks_create_id` int(11) DEFAULT NULL,
  `tasks_description` tinytext DEFAULT NULL,
  `tasks_end_date` datetime(6) DEFAULT NULL,
  `tasks_name` varchar(255) DEFAULT NULL,
  `tasks_project_id` bigint(20) DEFAULT NULL,
  `tasks_status_id` int(11) DEFAULT NULL,
  `tasks_user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`tasks_id`, `tasks_active`, `tasks_create_date`, `tasks_create_id`, `tasks_description`, `tasks_end_date`, `tasks_name`, `tasks_project_id`, `tasks_status_id`, `tasks_user_id`) VALUES
(1, b'1', '2024-07-28 10:33:02.000000', 1, 'jiwa suraya', NULL, 'Membuat xxxxxxx', 1, 3, 1),
(2, b'1', '2024-07-28 10:31:38.000000', 1, 'jiwa suraya', NULL, 'Membuat apa', 1, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(120) NOT NULL,
  `username` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `username`) VALUES
(1, 'email123@gmail.com', '$2a$10$w5kd/DjI4VDDL6758VhxX.5yFholKkz1mQkX8TO52PxHNG0d8I8AO', 'werty');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `history_log`
--
ALTER TABLE `history_log`
  ADD PRIMARY KEY (`his_log_id`),
  ADD KEY `FK78arwl6d2yuh18yrm8k3dnl9p` (`his_log_project_id`),
  ADD KEY `FKnv0349ldb7p6ki1g4p31cp9lq` (`his_log_tasks_id`),
  ADD KEY `FKpyh3eu59hrsmkfeo98b2g6vv3` (`his_log_create_id`);

--
-- Indexes for table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa37gx0bv8efaagiuv4aqrccif` (`note_project_id`),
  ADD KEY `FKq652l8ro0xtkkny7rxl65ovyj` (`note_tasks_id`),
  ADD KEY `FK3y1i3tmld1fl4dn1lbw62c2km` (`note_create_id`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`project_id`),
  ADD KEY `FKm0nxbfec6nnjbfroy4bjmc2s3` (`project_status_id`),
  ADD KEY `FKj1jltmdadkxy1tfiu6e73qmu0` (`project_user_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`status_id`);

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`tasks_id`),
  ADD KEY `FK5y81ioox7vpsknwpcugrn7rk1` (`tasks_project_id`),
  ADD KEY `FKe9ebtlhw6t98ojnbsecawu60t` (`tasks_status_id`),
  ADD KEY `FKgtqp4c5t8by7wba2keqdhhcae` (`tasks_user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `history_log`
--
ALTER TABLE `history_log`
  MODIFY `his_log_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `note`
--
ALTER TABLE `note`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `project_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `tasks_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `history_log`
--
ALTER TABLE `history_log`
  ADD CONSTRAINT `FK78arwl6d2yuh18yrm8k3dnl9p` FOREIGN KEY (`his_log_project_id`) REFERENCES `project` (`project_id`),
  ADD CONSTRAINT `FKnv0349ldb7p6ki1g4p31cp9lq` FOREIGN KEY (`his_log_tasks_id`) REFERENCES `tasks` (`tasks_id`),
  ADD CONSTRAINT `FKpyh3eu59hrsmkfeo98b2g6vv3` FOREIGN KEY (`his_log_create_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `FK3y1i3tmld1fl4dn1lbw62c2km` FOREIGN KEY (`note_create_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKa37gx0bv8efaagiuv4aqrccif` FOREIGN KEY (`note_project_id`) REFERENCES `project` (`project_id`),
  ADD CONSTRAINT `FKq652l8ro0xtkkny7rxl65ovyj` FOREIGN KEY (`note_tasks_id`) REFERENCES `tasks` (`tasks_id`);

--
-- Constraints for table `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `FKj1jltmdadkxy1tfiu6e73qmu0` FOREIGN KEY (`project_user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKm0nxbfec6nnjbfroy4bjmc2s3` FOREIGN KEY (`project_status_id`) REFERENCES `status` (`status_id`);

--
-- Constraints for table `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `FK5y81ioox7vpsknwpcugrn7rk1` FOREIGN KEY (`tasks_project_id`) REFERENCES `project` (`project_id`),
  ADD CONSTRAINT `FKe9ebtlhw6t98ojnbsecawu60t` FOREIGN KEY (`tasks_status_id`) REFERENCES `status` (`status_id`),
  ADD CONSTRAINT `FKgtqp4c5t8by7wba2keqdhhcae` FOREIGN KEY (`tasks_user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKrhfovtciq1l558cw6udg0h0d3` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
