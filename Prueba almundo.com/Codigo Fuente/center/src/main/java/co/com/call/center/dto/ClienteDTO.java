package co.com.call.center.dto;

import java.math.BigInteger;

import co.com.call.center.utils.OpcionServicio;


/**
 * 
 * @author Andrea
 *
 */
public class ClienteDTO {

	private String nombre;
	private BigInteger numeroContacto;
	private BigInteger numeroCedula;
	private OpcionServicio  opcion;

	public ClienteDTO(String nombre ,BigInteger numeroContacto,BigInteger numeroCedula, OpcionServicio op ) {
		this.nombre = nombre;
		this.numeroContacto = numeroContacto ;
		this.numeroCedula  = numeroCedula;
		this.opcion = op;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigInteger getNumeroContacto() {
		return numeroContacto;
	}

	public void setNumeroContacto(BigInteger numeroContacto) {
		this.numeroContacto = numeroContacto;
	}

	public BigInteger getNumeroCedula() {
		return numeroCedula;
	}

	public void setNumeroCedula(BigInteger numeroCedula) {
		this.numeroCedula = numeroCedula;
	}

	public OpcionServicio getOpcion() {
		return opcion;
	}

	public void setOpcion(OpcionServicio opcion) {
		this.opcion = opcion;
	}

}
