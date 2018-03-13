package co.com.call.center.persistence.dao;

import java.util.List;

import co.com.call.center.dto.EmpleadoDTO;

/**
 * 
 * @author Andrea
 *
 */
public interface EmpleadoDAO {
	
	
	public List<EmpleadoDTO> obtenerEmpleadoLibre();
	public boolean actualizarEstado(EmpleadoDTO empleadoDto);

}
