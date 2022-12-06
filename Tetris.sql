-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 05-12-2022 a las 22:59:51
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `Tetris`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Partida`
--

CREATE TABLE `Partida` (
  `codPartida` int(30) NOT NULL,
  `codUsuario` int(30) NOT NULL,
  `nivel` varchar(10) NOT NULL,
  `puntos` int(30) NOT NULL,
  `listaLadrillos` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `Partida`
--

INSERT INTO `Partida` (`codPartida`, `codUsuario`, `nivel`, `puntos`, `listaLadrillos`) VALUES
(1, 0, 'Facil', 0, 'ladrillos'),
(2, 0, 'Facil', 0, 'ladrillos'),
(3, 0, 'Medio', 0, 'ladrillos'),
(4, 0, 'Facil', 0, 'ladrillos');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Partida`
--
ALTER TABLE `Partida`
  ADD PRIMARY KEY (`codPartida`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Partida`
--
ALTER TABLE `Partida`
  MODIFY `codPartida` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;