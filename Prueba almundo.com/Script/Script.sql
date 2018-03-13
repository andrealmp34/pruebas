CREATE DATABASE 'center' /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE  empleado (
  CEDULA numeric(11) NOT NULL COMMENT 'cedula empleado',
  NOMBRE varchar(50) NOT NULL COMMENT 'nombre emepleado',
  CARGO numeric(2) NOT NULL COMMENT 'cargos: 1 =operador ,2=supervisro , 3=Director',
  ESTADO varchar(15) DEFAULT 'LIBRE',
  PRIMARY KEY (CEDULA)
) ;

CREATE TABLE  LLAMADA (
  PING numeric(11) NOT NULL COMMENT 'ping de registro ',
  NOMBRE_CONTACTO varchar(50) NOT NULL COMMENT 'nombre cliente',
  NUMERO_CONTACTO varchar(10) NOT NULL COMMENT 'móvil',
  NUMERO_CEDULA numeric(10) NOT NULL COMMENT 'móvil',
  DESCRIPCION varchar(50) DEFAULT 'LLAMADA',
  ACCION varchar(15) DEFAULT 'REGISTRO' COMMENT 'REgistro cuando se atiende una llamada y Agendar cuando el cliente queda en espera de atencion',
  FECHA timestamp ,
  PRIMARY KEY (PING)
) ;

INSERT INTO empleado (cedula,nombre,cargo,estado) values(10225070,'CARLOS PEREZ',1,'LIBRE');
INSERT INTO empleado (cedula,nombre,cargo,estado) values(10225071,'CAMILO FLOREZ',1,'LIBRE');
INSERT INTO empleado (cedula,nombre,cargo,estado) values(10225072,'ANDRES ROJAS',1,'LIBRE');
INSERT INTO empleado (cedula,nombre,cargo,estado) values(10225073,'PEDRO SUAREZ',1,'LIBRE');
INSERT INTO empleado (cedula,nombre,cargo,estado) values(10225074,'JUAN PINILLA',1,'LIBRE');
INSERT INTO empleado (cedula,nombre,cargo,estado) values(10225075,'PEDRO PEEREZ',2,'LIBRE');
INSERT INTO empleado (cedula,nombre,cargo,estado) values(10225076,'JULIO URIAN',2,'LIBRE');
INSERT INTO empleado (cedula,nombre,cargo,estado) values(10225077,'DAVID AVILA',2,'LIBRE');
INSERT INTO empleado (cedula,nombre,cargo,estado) values(10225078,'OSCAR GALINDO',3,'LIBRE');
INSERT INTO empleado (cedula,nombre,cargo,estado) values(10225079,'ALVARO HERRERA',3,'LIBRE');
