--
-- Base de datos: `tetris`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DatosPersonalizacion`
--

CREATE TABLE IF NOT EXISTS `DatosPersonalizacion` (
  `CodigoPersonalizacion` int NOT NULL,
  `ColorFondo` varchar(40) NOT NULL,
  `ColorLadrillos` varchar(40) NOT NULL,
  `Sonido` varchar(40) NOT NULL,
PRIMARY KEY(CodigoPersonalizacion)

);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Partida`
--

CREATE TABLE IF NOT EXISTS `Partida` (
  `codPartida` int NOT NULL AUTO_INCREMENT,
  `codUsuario` int NOT NULL,
  `nivel` varchar(10) NOT NULL,
  `puntos` int NOT NULL,
  `listaLadrillos` varchar(30) NOT NULL,
PRIMARY KEY(codPartida)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuario`
--

CREATE TABLE IF NOT EXISTS `Usuario` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(30) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `Contraseña` varchar(50) NOT NULL,
  `CodigoPersonalizacion` int NOT NULL,
PRIMARY KEY(Id)
)