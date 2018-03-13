package co.com.call.center.dto;

import java.math.BigInteger;

import co.com.call.center.utils.AccionLlamada;

/**
 * @author Andrea
 *
 */
public class LlamadaDTO {

	private long ping;
	private String nombreContacto;
	private BigInteger numeroContacto;
	private BigInteger numeroCedula;
	private String descripcion;
	private AccionLlamada accion;

	public long getPing() {
		return ping;
	}

	public void setPing(long ping) {
		this.ping = ping;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public BigInteger getNumeroContacto() {
		return numeroContacto;
	}

	public void setNumeroContacto(BigInteger numeroContacto) {
		this.numeroContacto = numeroContacto;
	}

	public AccionLlamada getAccion() {
		return accion;
	}

	public void setAccion(AccionLlamada accion) {
		this.accion = accion;
	}

	public BigInteger getNumeroCedula() {
		return numeroCedula;
	}

	public void setNumeroCedula(BigInteger numeroCedula) {
		this.numeroCedula = numeroCedula;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
