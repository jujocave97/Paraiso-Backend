-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 05-06-2025 a las 11:12:57
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `paraiso`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cakes`
--

CREATE TABLE `cakes` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas_cake`
--

CREATE TABLE `reservas_cake` (
  `id` bigint(20) NOT NULL,
  `cantidad_reservada` int(11) NOT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  `estado` enum('CANCELADA','EN_CURSO','LISTO_PARA_RECOGER','PENDIENTE') NOT NULL,
  `fecha_reserva` datetime(6) DEFAULT NULL,
  `cake_id` bigint(20) NOT NULL,
  `usuario_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  `token_expiration` datetime(6) DEFAULT NULL,
  `token_recuperacion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `apellido`, `email`, `nombre`, `password`, `rol`, `telefono`, `token_expiration`, `token_recuperacion`) VALUES
(1, 'Admin', 'admin@paraiso.com', 'Super', '$2a$10$UA8YwhVaY0h9ECyNeqdRSOz0lErkq.bxsgBp7uce7ssrud2DLTzY2', 'ADMIN', '999999999', NULL, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cakes`
--
ALTER TABLE `cakes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKo2amtkl0q4w55q647xdv81k1b` (`nombre`);

--
-- Indices de la tabla `reservas_cake`
--
ALTER TABLE `reservas_cake`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7iwph2h375m0cecr9yrcltytw` (`cake_id`),
  ADD KEY `FKsl76hcc0mu0q8oxogwswdhuif` (`usuario_id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKkfsp0s1tflm1cwlj8idhqsad0` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cakes`
--
ALTER TABLE `cakes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `reservas_cake`
--
ALTER TABLE `reservas_cake`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reservas_cake`
--
ALTER TABLE `reservas_cake`
  ADD CONSTRAINT `FK7iwph2h375m0cecr9yrcltytw` FOREIGN KEY (`cake_id`) REFERENCES `cakes` (`id`),
  ADD CONSTRAINT `FKsl76hcc0mu0q8oxogwswdhuif` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
