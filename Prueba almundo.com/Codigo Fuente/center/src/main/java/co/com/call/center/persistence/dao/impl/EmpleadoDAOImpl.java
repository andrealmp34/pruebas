package co.com.call.center.persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.com.call.center.dto.EmpleadoDTO;
import co.com.call.center.persistence.dao.EmpleadoDAO;
import co.com.call.center.utils.Conexion;
import co.com.call.center.utils.Constantes;
import co.com.call.center.utils.EstadoEmpleado;

/**
 * 
 * @author Andrea
 *
 */
public class EmpleadoDAOImpl implements EmpleadoDAO {

	/**
	 * 
	 */
	public List<EmpleadoDTO> obtenerEmpleadoLibre() {

		List<EmpleadoDTO> emepleadoDtoList = new ArrayList<EmpleadoDTO>();

		String query = "";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;
		String estado = null;

		try {
			query = Constantes.SQL_CONSULTA_EMPLEADO;

			con = Conexion.cargarConfiguracion();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			if (rs != null) {
				while (rs.next()) {

					EmpleadoDTO empleadoDto = new EmpleadoDTO();
					empleadoDto.setNombre((rs.getString("NOMBRE")));
					empleadoDto.setNumeroCedula((rs.getLong("CEDULA")));
					estado = rs.getString("ESTADO");
					if (estado.equals(EstadoEmpleado.OCUPADO.toString())) {
						empleadoDto.setEstado(EstadoEmpleado.OCUPADO);
					} else {
						empleadoDto.setEstado(EstadoEmpleado.LIBRE);
					}
					emepleadoDtoList.add(empleadoDto);
				}
			}
		} catch (Exception e) {
			Constantes.logger.error(e);
		}finally{
			try {
				Conexion.cerrrarConexion(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return emepleadoDtoList;
	}


	/**
	 * 
	 */
	public boolean actualizarEstado(EmpleadoDTO empleadoDto) {

		String query = "";
		boolean respuesta =false;
		PreparedStatement ps = null;
		Connection con = null;

		try {
			query = Constantes.SQL_ACTUALIZAR_EMPLEADO;

			con = Conexion.cargarConfiguracion();
			ps = con.prepareStatement(query);
			ps.setString(1, empleadoDto.getEstado().toString());
			ps.setLong(2,  empleadoDto.getNumeroCedula());
			
			respuesta= ps.execute();
			/*rs = ps.executeQuery();
			if (rs != null) {
				respuesta=true;
			}*/
			

		} catch (Exception e) {
			Constantes.logger.error(e);
		}finally{
			try {
				Conexion.cerrrarConexion(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return respuesta;
	}

}
