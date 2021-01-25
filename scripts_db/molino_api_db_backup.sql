-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 25, 2021 at 05:33 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.3.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `molino_api_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `nombre` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `nombre`, `descripcion`) VALUES
(1, 'USUARIO', 'Es el role con el menor número de privilegios en el sistema'),
(2, 'ADMIN', 'es el role con el MAXIMO número de privilegios en el sistema'),
(3, 'RECEPCION', 'RECEPCION'),
(4, 'SECADO', 'SECADO');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `usuario_id` int(11) NOT NULL,
  `username` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `pwd` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `owner` varchar(100) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'public.user',
  `state` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`usuario_id`, `username`, `pwd`, `owner`, `state`) VALUES
(1, 'kevin002', '$2a$10$VrkUU5MF08FQFJa75HBOTOCCTZjpQY33p28PdHFjBuxUTLXNT.C8C', 'admin.1', 1),
(2, 'richard09', '$2a$10$WfRk/EEBgXp/vctfLDKb6.WC2VBQxI8/gdKPU.i6AxmPJOVaEZ6iq', 'usuario', 1),
(3, 'angel73', '$2a$10$1xthBNvgmxs/FzVPcjcg4.LNyxBzW45lKsUhaN8b9xBIt3DAZ.BLO', 'usuario', 1),
(4, 'jose2020', '$2a$10$fzEbPSGtTdsS69zVGefwZ.KoieuRYZDlecsI2hjgQCcl0/Ud63Kgy', 'usuario', 1),
(5, 'marcosx7', '$2a$10$UJooMyRWAg1dhWFhkugV6uJQ2byQ7zJinH2wH3ioV0M7MF.LpccZC', 'usuario', 1),
(6, 'user2020', '$2a$10$d0EBohCxSBTeCGHHcINXNuvZI99c4VlIEPg76Fgnn11Zk/YxXJ6ay', 'usuario', 1);

-- --------------------------------------------------------

--
-- Table structure for table `usuario_roles`
--

CREATE TABLE `usuario_roles` (
  `usuario_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `usuario_roles`
--

INSERT INTO `usuario_roles` (`usuario_id`, `role_id`) VALUES
(1, 2),
(2, 3),
(3, 4),
(5, 3),
(6, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usuario_id`);

--
-- Indexes for table `usuario_roles`
--
ALTER TABLE `usuario_roles`
  ADD PRIMARY KEY (`usuario_id`,`role_id`),
  ADD KEY `fk_usuariorole_role` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `usuario_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `usuario_roles`
--
ALTER TABLE `usuario_roles`
  ADD CONSTRAINT `fk_usuariorole_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  ADD CONSTRAINT `fk_usuarioroles_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
