package co.com.call.center.controller;

import java.util.Deque;

import org.springframework.stereotype.Controller;

import co.com.call.center.bussines.CallCenter;
import co.com.call.center.dto.EmpleadoDTO;
import co.com.call.center.dto.ClienteDTO;;

/**
 * 
 * @author Andrea
 *
 */

@Controller
public class ClienteController {

	private CallCenter<EmpleadoDTO> callCenter;
	private Deque<ClienteDTO> clientes;

	/**
	 * constructor de la clase para poder hacer el test
	 * @param cc
	 */
	public ClienteController(CallCenter<EmpleadoDTO> cc) {
		callCenter = cc;
		clientes = callCenter.getClienteQueued();
	}
	

	/**
	 * 
	 * @return
	 */
	public Deque<ClienteDTO> getCustomers() {
		return clientes;
	}

	/**
	 * 
	 * @return
	 */
	public ClienteDTO getProximoCliente() {
		return clientes.poll();
	}

	/**
	 * 
	 * @param c
	 */
	public void addProximoCliente(ClienteDTO c) {
		clientes.offerFirst(c);
	}

}
