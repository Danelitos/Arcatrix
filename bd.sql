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
  `ColorZSHAPE` varchar(40) NOT NULL,
  `ColorSSHAPE` varchar(40) NOT NULL,
  `ColorLINESHAPE` varchar(40) NOT NULL,
  `ColorSQUARESHAPE` varchar(40) NOT NULL,
  `ColorTSHAPE` varchar(40) NOT NULL,
  `ColorLSHAPE` varchar(40) NOT NULL,
  `ColorMIRROREDLSHAPE` varchar(40) NOT NULL,
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
  `listaLadrillos` varchar(9000) NOT NULL,
  `fechaHora` varchar(50) NOT NULL,
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
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuario`
--
CREATE TABLE IF NOT EXISTS `Ranking` (
  `CodRanking` int NOT NULL AUTO_INCREMENT,
  `IdUsr` int NOT NULL ,
  `NombreUsr` varchar(30) NOT NULL,
  `Nivel` varchar(40) NOT NULL,
  `Puntuacion` int NOT NULL,
PRIMARY KEY(CodRanking)
);

