package co.com.call.center.persistence.dao.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.com.call.center.dto.EmpleadoDTO;
import co.com.call.center.dto.LlamadaDTO;
import co.com.call.center.persistence.dao.LlamadaDAO;
import co.com.call.center.utils.AccionLlamada;
import co.com.call.center.utils.Conexion;
import co.com.call.center.utils.Constantes;
import co.com.call.center.utils.EstadoEmpleado;

/**
 * 
 * @author Andrea
 *
 */
public class LlamadaDAOImpl implements LlamadaDAO {

	/**
	 * Se agenda la llamadad para que despues se pueda devolver al cliente
	 * para poder realizar el servicio
	 */
	public boolean agendarLllamada(LlamadaDTO clienteDto) {

		String query = "";
		PreparedStatement ps = null;
		Connection con = null;
		boolean respuesta = false;
		try {
			query = Constantes.SQL_AGENDAR_LLAMADA;
			con = Conexion.cargarConfiguracion();
			ps = con.prepareStatement(query);
			
			ps.setLong(1, clienteDto.getPing());
			ps.setString(2,clienteDto.getNombreContacto());
			ps.setLong(3, clienteDto.getNumeroContacto().longValue());
			ps.setString(4, Constantes.AGENDAR_LLAMADA);
			ps.setString(5, AccionLlamada.AGENDAR.toString());
			
			respuesta= ps.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
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

	/**
	 * se registra la atencion dada al cliente
	 */
	public boolean registrarLllamada(LlamadaDTO clienteDto) {

		String query = "";
		PreparedStatement ps = null;
		Connection con = null;
		boolean respuesta = false;
		try {
			query = Constantes.SQL_AGENDAR_LLAMADA;
			con = Conexion.cargarConfiguracion();
			ps = con.prepareStatement(query);
			ps.setLong(1, clienteDto.getPing());
			ps.setString(2,clienteDto.getNombreContacto());
			ps.setString(3, clienteDto.getNumeroContacto().toString());
			ps.setLong(4, clienteDto.getNumeroCedula().longValue());
			ps.setString(5, Constantes.REGISTRAR_LLAMADA);
			ps.setString(6, AccionLlamada.REGISTRAR.toString());
			
			respuesta= ps.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
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

	public List<LlamadaDTO> obtenerLlmadaAgendada()  {

		List<LlamadaDTO> llamadaDtoList = new ArrayList<LlamadaDTO>();

		String query = "";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = null;

		try {
			query = Constantes.SQL_CONSULTA_AGENDA;

			con = Conexion.cargarConfiguracion();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			if (rs != null) {
				while (rs.next()) {

					LlamadaDTO llamadaDto = new LlamadaDTO();
					llamadaDto.setNombreContacto((rs.getString("NOMBRE_CONTACTO")));
					llamadaDto.setNumeroContacto(new BigInteger(rs.getString("NUMERO_CONTACTO")));
					llamadaDto.setNumeroCedula(new BigInteger(rs.getString("NUMERO_CEDULA")));
					
					llamadaDtoList.add(llamadaDto);
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
		return llamadaDtoList;
	}
}
