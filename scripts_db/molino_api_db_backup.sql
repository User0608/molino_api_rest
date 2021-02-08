-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 08, 2021 at 06:15 AM
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
-- Table structure for table `camion_transporte`
--

CREATE TABLE `camion_transporte` (
  `camion_transporte_id` int(11) NOT NULL,
  `placa` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `chofer` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `camion_transporte`
--

INSERT INTO `camion_transporte` (`camion_transporte_id`, `placa`, `chofer`, `descripcion`) VALUES
(1, 't7r-fh5', 'no tien', 'no tien'),
(2, 'Z47-AR1', 'Junior Romero', 'Junior Romero'),
(3, 'F5U-597', 'Terrones Santos', 'Terrones Santos');

-- --------------------------------------------------------

--
-- Table structure for table `empleado`
--

CREATE TABLE `empleado` (
  `empleado_id` int(11) NOT NULL,
  `nombre` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `apellido_paterno` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `apellido_materno` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `dni` char(8) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `sueldo` double(8,2) NOT NULL,
  `fecha_contrato` date NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `empleado`
--

INSERT INTO `empleado` (`empleado_id`, `nombre`, `apellido_paterno`, `apellido_materno`, `dni`, `telefono`, `direccion`, `email`, `sueldo`, `fecha_contrato`, `estado`) VALUES
(1, 'Luis Angel', 'Burga', 'Bazan', '78374878', '8989767467', 'San Jose de Moro', 'lluser@hotmail.com', 1500.00, '2021-01-03', 1),
(2, 'Edex', 'Saucedo', 'Huaman', '73491346', '998758477', 'San Jose de moro', 'user002@gamil.com', 1500.00, '2021-01-31', 1),
(3, 'Doris', 'Garicia', 'Albarado', '38598743', '989878767', 'Pacangilla jr.20', 'correo@telefono.com', 2500.00, '2021-01-31', 0),
(4, 'Raul Migel', 'Alcantara', 'Peralta', '98987678', '9876478768', 'Chepen calle 450.', 'rmraul0808@hotmail.com', 1500.00, '2021-01-31', 1);

-- --------------------------------------------------------

--
-- Table structure for table `lote_arroz`
--

CREATE TABLE `lote_arroz` (
  `lote_id` int(11) NOT NULL,
  `numero_sacos` int(11) NOT NULL,
  `productor_id` int(11) DEFAULT NULL,
  `tipo_arroz_id` int(11) DEFAULT NULL,
  `procedencia_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `lote_arroz`
--

INSERT INTO `lote_arroz` (`lote_id`, `numero_sacos`, `productor_id`, `tipo_arroz_id`, `procedencia_id`) VALUES
(5, 50, 1, 1, 1),
(7, 350, 5, 2, 1),
(8, 400, 1, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `procedencia`
--

CREATE TABLE `procedencia` (
  `procedencia_id` int(11) NOT NULL,
  `lugar` varchar(100) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `procedencia`
--

INSERT INTO `procedencia` (`procedencia_id`, `lugar`) VALUES
(1, 'San Jose'),
(2, 'Valle Jequetepeque');

-- --------------------------------------------------------

--
-- Table structure for table `productor`
--

CREATE TABLE `productor` (
  `productor_id` int(11) NOT NULL,
  `dni` char(8) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `apellido_paterno` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `apellido_materno` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(60) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `productor`
--

INSERT INTO `productor` (`productor_id`, `dni`, `nombre`, `apellido_paterno`, `apellido_materno`, `direccion`, `telefono`, `email`) VALUES
(1, '73491355', 'Jose Armando', 'Alatamirano', 'Quiroz', 'Calle, San Pedro 201, Chepén', '998677654', 'jose002_@gamil.com'),
(5, '45673467', 'Jesus', 'Rubiños', 'Linares', 'Chepen', '989898974', 'linaresll@gmai.com');

-- --------------------------------------------------------

--
-- Table structure for table `registro_ingreso`
--

CREATE TABLE `registro_ingreso` (
  `lote_id` int(11) NOT NULL,
  `numero_sacos` int(11) NOT NULL,
  `kilos_saco` decimal(10,0) DEFAULT NULL,
  `total_kilos` decimal(10,0) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `empleado_id` int(11) NOT NULL,
  `camion_transporte_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `registro_ingreso`
--

INSERT INTO `registro_ingreso` (`lote_id`, `numero_sacos`, `kilos_saco`, `total_kilos`, `fecha`, `hora`, `empleado_id`, `camion_transporte_id`) VALUES
(5, 50, '100', '100', '2021-02-07', '23:16:48', 1, 1),
(7, 350, '80', '80', '2021-02-07', '23:53:16', 1, 2),
(8, 400, '80', '80', '2021-02-08', '00:03:48', 1, 3);

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
-- Table structure for table `tipo_arroz`
--

CREATE TABLE `tipo_arroz` (
  `tipo_arroz_id` int(11) NOT NULL,
  `nombre` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Dumping data for table `tipo_arroz`
--

INSERT INTO `tipo_arroz` (`tipo_arroz_id`, `nombre`, `descripcion`) VALUES
(1, 'INIA 514', 'Semilla Registrada.'),
(2, 'IR 43', 'Origen filipinas, adaptacion region costa'),
(3, 'BG-90', 'Nueva ');

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
(1, 'kevin002', '$2a$10$oj8yNieGKuCMAD0RkBmDOuRK5AzB3wkBlFKehmYFbOsNhpFavqzXq', 'empleado.1', 1),
(2, 'richard09', '$2a$10$WfRk/EEBgXp/vctfLDKb6.WC2VBQxI8/gdKPU.i6AxmPJOVaEZ6iq', 'usuario', 0),
(3, 'angel73', '$2a$10$1xthBNvgmxs/FzVPcjcg4.LNyxBzW45lKsUhaN8b9xBIt3DAZ.BLO', 'usuario', 1),
(9, 'nuevo002', '$2a$10$Vy9kiMDH94PuhHHm8kIsDOLBjPrvBs1Mg8TY/yvXplALyW9JiHXQ.', 'empleado.2', 1),
(10, 'dgarcia_50', '$2a$10$9tu621RLNHKMeioZRtT/ouQkBei96Cr/MvzlaFlQMyPn/EqDF853S', 'empleado.3', 1),
(12, 'mraul777', '$2a$10$mBa7zs5ffJ9nyCGBva/40uQblvMf9nJDZGmuJ14QkiIT0htmZNxpW', 'empleado.4', 1);

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
(9, 3),
(9, 4),
(10, 4),
(12, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `camion_transporte`
--
ALTER TABLE `camion_transporte`
  ADD PRIMARY KEY (`camion_transporte_id`);

--
-- Indexes for table `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`empleado_id`);

--
-- Indexes for table `lote_arroz`
--
ALTER TABLE `lote_arroz`
  ADD PRIMARY KEY (`lote_id`),
  ADD KEY `fk_lotearroz_tipo` (`tipo_arroz_id`),
  ADD KEY `fk_lotearroz_productor` (`productor_id`),
  ADD KEY `fk_lotearroz_procedencia` (`procedencia_id`);

--
-- Indexes for table `procedencia`
--
ALTER TABLE `procedencia`
  ADD PRIMARY KEY (`procedencia_id`);

--
-- Indexes for table `productor`
--
ALTER TABLE `productor`
  ADD PRIMARY KEY (`productor_id`);

--
-- Indexes for table `registro_ingreso`
--
ALTER TABLE `registro_ingreso`
  ADD PRIMARY KEY (`lote_id`),
  ADD KEY `fk_registroingreso_empleado` (`empleado_id`),
  ADD KEY `fk_ingreso_camion` (`camion_transporte_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `tipo_arroz`
--
ALTER TABLE `tipo_arroz`
  ADD PRIMARY KEY (`tipo_arroz_id`);

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
-- AUTO_INCREMENT for table `camion_transporte`
--
ALTER TABLE `camion_transporte`
  MODIFY `camion_transporte_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `empleado`
--
ALTER TABLE `empleado`
  MODIFY `empleado_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `lote_arroz`
--
ALTER TABLE `lote_arroz`
  MODIFY `lote_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `procedencia`
--
ALTER TABLE `procedencia`
  MODIFY `procedencia_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `productor`
--
ALTER TABLE `productor`
  MODIFY `productor_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tipo_arroz`
--
ALTER TABLE `tipo_arroz`
  MODIFY `tipo_arroz_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `usuario_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `lote_arroz`
--
ALTER TABLE `lote_arroz`
  ADD CONSTRAINT `fk_lotearroz_procedencia` FOREIGN KEY (`procedencia_id`) REFERENCES `procedencia` (`procedencia_id`),
  ADD CONSTRAINT `fk_lotearroz_productor` FOREIGN KEY (`productor_id`) REFERENCES `productor` (`productor_id`),
  ADD CONSTRAINT `fk_lotearroz_tipo` FOREIGN KEY (`tipo_arroz_id`) REFERENCES `tipo_arroz` (`tipo_arroz_id`);

--
-- Constraints for table `registro_ingreso`
--
ALTER TABLE `registro_ingreso`
  ADD CONSTRAINT `fk_ingreso_camion` FOREIGN KEY (`camion_transporte_id`) REFERENCES `camion_transporte` (`camion_transporte_id`),
  ADD CONSTRAINT `fk_registroingreso_empleado` FOREIGN KEY (`empleado_id`) REFERENCES `empleado` (`empleado_id`),
  ADD CONSTRAINT `fk_registroingreso_lotearroz` FOREIGN KEY (`lote_id`) REFERENCES `lote_arroz` (`lote_id`);

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
