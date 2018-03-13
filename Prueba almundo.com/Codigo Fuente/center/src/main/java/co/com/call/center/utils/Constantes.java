package co.com.call.center.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Andrea
 * 
 */
public class Constantes {

	public static PropertiesLoader properties = PropertiesLoader.getInstance();
	public static final Logger logger = LogManager.getLogger("Call");
	public static String DB_PROCESADOR_SERVICE_DRIVER =  properties.getProperty("call.driver");
	public static final String DB_PROCESADOR_SERVICE_URL = properties.getProperty("call.url");
	public static final String DB_PROCESADOR_SERVICE_USER = properties.getProperty("call.user");
	public static final String DB_PROCESADOR_SERVICE_PASSWORD =  properties.getProperty("call.password"); 
	public static final String VALIDADORSTP_SERVICE_PATH = "src/main/resources/call.properties";
	public static final String AGENDAR_LLAMADA = "POR FAVOR COMUNICARSE CON EL CLIENTE";
	public static final String REGISTRAR_LLAMADA = " REGISTRO LLAMADA ATENDIDO";
	public static final int NUMERO_LLAMADAS = 10;
	public static final String SQL_CONSULTA_EMPLEADO = " SELECT  CE.NOMBRE , CE.CEDULA , CE.CARGO ,CE.ESTADO "
			+ " FROM CENTER.EMPLEADO CE " + " WHERE CE.ESTADO = 'LIBRE' " + " ORDER BY CARGO ";
	public static final String SQL_ACTUALIZAR_EMPLEADO = " UPDATE  CENTER.EMPLEADO CE  SET CE.ESTADO= ?  WHERE CE.CEDULA = ? ";
	public static final String SQL_AGENDAR_LLAMADA = " INSERT INTO LLAMADA "
			+ " (PING ,NOMBRE_CONTACTO ,NUMERO_CONTACTO,NUMERO_CEDULA,DESCRIPCION,ACCION,FECHA) VALUES (?,?,?,?,?,?,sysdate()) ";
	
	public static final String SQL_CONSULTA_AGENDA = " SELECT  LA.PING , LA.NOMBRE_CONTACTO , LA.NUMERO_CONTACTO ,LA.DESCRIPCION "
			+ " FROM CENTER.LLAMADA LA " + " WHERE LA.ACCION = 'AGENDAR' " + " ORDER BY FECHA ASC";

	private Constantes() {
	}
}