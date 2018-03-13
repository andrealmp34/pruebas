package co.com.call.center.dto;

import co.com.call.center.utils.EstadoEmpleado;


/**
 * 
 * @author Andrea
 *
 */
public class EmpleadoDTO {
	
	private String nombre;
	private String cargo;
	private long numeroCedula;
	private String estado;
	public String getNombre() {
		return nombre;
	}
	
	public EmpleadoDTO(String n, String t) {
		nombre = n;
		cargo = t;
		estado = "available";
	}
	
	public EmpleadoDTO() {
	}
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public long getNumeroCedula() {
		return numeroCedula;
	}
	public void setNumeroCedula(long numeroCedula) {
		this.numeroCedula = numeroCedula;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(EstadoEmpleado ocupado) {
		this.estado = ocupado.name();
	}
	
	

}
