package co.com.call.center.bussines;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import co.com.call.center.dto.ClienteDTO;
import co.com.call.center.dto.EmpleadoDTO;

/**
 * 
 * @author Andrea
 *
 * @param <E>
 */
public class CallCenter<E> {
	private Map<String, Deque<EmpleadoDTO>> emepleadosCola;
	private Deque<ClienteDTO> queuedClientes;
	
	/**
	 * Metodo para poder agendar y en colar las llamadas en espera sin funcion de una base de datos
	 */
	public CallCenter() {
		emepleadosCola = new HashMap<String, Deque<EmpleadoDTO>>();
		queuedClientes = new LinkedList<ClienteDTO>();
		emepleadosCola.put(EmpleadoCargo.empleadoCargo[0], new LinkedList<EmpleadoDTO>());//0 = operador
		emepleadosCola.put(EmpleadoCargo.empleadoCargo[1], new LinkedList<EmpleadoDTO>());//1 = supervisor
		emepleadosCola.put(EmpleadoCargo.empleadoCargo[2], new LinkedList<EmpleadoDTO>());//2 = director
	}

	/**
	 * se encolan los emepleados para atender las llamadas
	 * @param e
	 */
	public void addEmployeeToShift(EmpleadoDTO e) {
		emepleadosCola.get(e.getCargo()).push(e);
	}

	/**
	 * devuelv los empleados para poder realizar la atencion de llamada
	 * @return
	 */
	public Map<String, Deque<EmpleadoDTO>> getAllEmployeesInShift() {
		return emepleadosCola;
	}

	/**
	 * 
	 * @return
	 */
	public Deque<ClienteDTO> getClienteQueued() {
		return queuedClientes;
	}
	
	/**
	 * 
	 * @param c
	 */
	public void addCliente(ClienteDTO c) {
		queuedClientes.add(c);
	}

}

