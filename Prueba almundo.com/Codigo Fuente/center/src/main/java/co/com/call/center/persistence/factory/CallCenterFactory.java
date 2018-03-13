package co.com.call.center.persistence.factory;

import co.com.call.center.persistence.dao.impl.EmpleadoDAOImpl;
import co.com.call.center.persistence.dao.impl.LlamadaDAOImpl;

/**
 * 
 * @author Andrea
 *
 */
public class CallCenterFactory {

	public static EmpleadoDAOImpl getEmpleadoDAO() {
		return new EmpleadoDAOImpl();
	}

	public static LlamadaDAOImpl getLlamadaDAO() {
		return new LlamadaDAOImpl();
	}

}
