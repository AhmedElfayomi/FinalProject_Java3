-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 05, 2023 at 12:08 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clinic_appointments`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `id` int(11) NOT NULL,
  `appointment_date` date NOT NULL,
  `appointment_day` varchar(20) NOT NULL,
  `appointment_time` varchar(20) NOT NULL,
  `status` enum('free','booked') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`id`, `appointment_date`, `appointment_day`, `appointment_time`, `status`) VALUES
(2, '2023-05-03', 'Monday', '10-11', 'booked'),
(3, '2023-05-18', 'Sunday', '11-12', 'booked'),
(4, '2023-05-02', 'Monday', '10-13', 'booked'),
(6, '2023-05-11', 'Monday', '9-11', 'booked'),
(7, '2023-05-20', 'Saturday', '13 - 15', 'booked'),
(9, '2023-05-31', 'Tuesday', '10 - 14', 'booked'),
(10, '2023-06-01', 'Thursday', '11-12', 'booked'),
(11, '2023-06-01', 'Tuesday', '100', 'booked'),
(13, '2023-06-22', 'Tuesday', '8 - 9', 'free');

-- --------------------------------------------------------

--
-- Table structure for table `booked_appointments`
--

CREATE TABLE `booked_appointments` (
  `id` int(11) NOT NULL,
  `appointment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` enum('waiting','finished') NOT NULL,
  `doctor_comment` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `booked_appointments`
--

INSERT INTO `booked_appointments` (`id`, `appointment_id`, `user_id`, `status`, `doctor_comment`) VALUES
(9, 3, 12, 'finished', 'Finsihed Appointment'),
(10, 2, 12, 'finished', 'goodBye'),
(15, 6, 13, 'finished', '6'),
(16, 7, 12, 'finished', 'f'),
(17, 11, 13, 'finished', 'Finish'),
(18, 9, 13, 'waiting', 'waiting'),
(19, 10, 13, 'waiting', 'waiting');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `gender` enum('male','female') NOT NULL,
  `role` enum('admin','patient') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `firstname`, `lastname`, `age`, `email`, `phone`, `gender`, `role`) VALUES
(6, 'AA', '123', 'Ahmed', 'Hisham', 18, 'Ahmed@gmail.com', '0592827582', 'male', 'admin'),
(12, 'AhmedHisham2', '123', 'Ahmed2', 'Hisham', 20, 'sdcscsasc', '5651616', 'male', 'patient'),
(13, 'A', '123', 'Ahmed', 'Hisham', 19, 'a@gmail.com', '05928285782', 'male', 'patient');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `booked_appointments`
--
ALTER TABLE `booked_appointments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `appointment_id` (`appointment_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UC_users` (`username`,`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `booked_appointments`
--
ALTER TABLE `booked_appointments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booked_appointments`
--
ALTER TABLE `booked_appointments`
  ADD CONSTRAINT `booked_appointments_ibfk_1` FOREIGN KEY (`appointment_id`) REFERENCES `appointments` (`id`),
  ADD CONSTRAINT `booked_appointments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
