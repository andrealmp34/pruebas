package co.com.call.center.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author andrea.manrique
 */
public class Conexion {

   /**
    *
    * @return @throws NamingException
    * @throws SQLException
    * @throws ClassNotFoundException
    */
   public static Connection cargarConfiguracion()
           throws NamingException, SQLException, ClassNotFoundException {

      Class.forName( Constantes.DB_PROCESADOR_SERVICE_DRIVER );
      Connection conexion = DriverManager.getConnection( Constantes.DB_PROCESADOR_SERVICE_URL, Constantes.DB_PROCESADOR_SERVICE_USER, Constantes.DB_PROCESADOR_SERVICE_PASSWORD );

      return conexion;
   }

   public static void cerrrarConexion( Connection conexion )
           throws SQLException {
      if( conexion != null ) {
         conexion.close();
      }
   }
}