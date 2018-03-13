package co.com.call.center.controller;

import java.math.BigInteger;

import org.junit.Test;

import center.callCenterDispatcherTest;
import co.com.call.center.bussines.CallCenter;
import co.com.call.center.dto.ClienteDTO;
import co.com.call.center.dto.EmpleadoDTO;
import co.com.call.center.thread.callCenterDispatcher;
import co.com.call.center.utils.OpcionServicio;

public class MainController<E> {
	private EmpleadoController employeeController;
	private ClienteController customerController;
	private callCenterDispatcher<E> dispatcher;
	private CallCenter<EmpleadoDTO> callCenter;

	/**
	 * @param callCenter
	 */
	
	public MainController(CallCenter<EmpleadoDTO> cc) {
		callCenter = cc;
		employeeController = new EmpleadoController(callCenter);
		customerController = new ClienteController(callCenter);
		dispatcher = new callCenterDispatcher<E>(employeeController, customerController);
	}

	@Test
	public void runCallCenter() throws InterruptedException {

		ClienteDTO tempCustomer = customerController.getProximoCliente();
		while (tempCustomer != null) {
			getDispatcher().dispatchCall(tempCustomer);
			tempCustomer = customerController.getProximoCliente();
		}
		getDispatcher().terminarDispatch();
	}

	public static void main(String[] args) throws InterruptedException {
		// create Employees
		EmpleadoDTO s1 = new EmpleadoDTO("Laurent", "supervisor");
		EmpleadoDTO d1 = new EmpleadoDTO("Rolph", "director");
		EmpleadoDTO o1 = new EmpleadoDTO("Celeste", "operator");
		EmpleadoDTO s2 = new EmpleadoDTO("Laurent", "supervisor");
		EmpleadoDTO o2 = new EmpleadoDTO("Rolph", "operator");
		EmpleadoDTO o3 = new EmpleadoDTO("Ramon", "operator");
		EmpleadoDTO o4 = new EmpleadoDTO("Cecil", "operator");
		EmpleadoDTO o5 = new EmpleadoDTO("Keith", "operator");
		EmpleadoDTO o6 = new EmpleadoDTO("Ramiro", "operator");
		EmpleadoDTO o7 = new EmpleadoDTO("Charles", "operator");
		EmpleadoDTO o8 = new EmpleadoDTO("Robert", "operator");
		EmpleadoDTO o9 = new EmpleadoDTO("Zoe", "operator");
		// Create Call Center
		CallCenter<EmpleadoDTO> callCenter = new CallCenter<EmpleadoDTO>();
		// Add employees to call center
		callCenter.addEmployeeToShift(s1);
		callCenter.addEmployeeToShift(d1);
		callCenter.addEmployeeToShift(o1);
		callCenter.addEmployeeToShift(o2);
		callCenter.addEmployeeToShift(o3);
		callCenter.addEmployeeToShift(o4);
		callCenter.addEmployeeToShift(o5);
		callCenter.addEmployeeToShift(o6);
		callCenter.addEmployeeToShift(o7);
		callCenter.addEmployeeToShift(o8);
		callCenter.addEmployeeToShift(o9);
		callCenter.addEmployeeToShift(s2);
		// Create a main controller
		callCenterDispatcherTest<EmpleadoDTO> mainController = new callCenterDispatcherTest<EmpleadoDTO>(callCenter);
		// create customers
		ClienteDTO c1 = new ClienteDTO("Claude",new BigInteger("3003128160"),new BigInteger("1022350790"),OpcionServicio.DEVLLAMADA);
		ClienteDTO c2 = new ClienteDTO("John", new BigInteger("3003128161"),new BigInteger("1022350791"),OpcionServicio.DEVLLAMADA);
		ClienteDTO c3 = new ClienteDTO("Andrea",new BigInteger("3003128162"),new BigInteger("1022350792"), OpcionServicio.DEVLLAMADA);
		ClienteDTO c4 = new ClienteDTO("Carlos",new BigInteger("3003128163"),new BigInteger("1022350793"), OpcionServicio.DEVLLAMADA);
		ClienteDTO c5 = new ClienteDTO("pedro",new BigInteger("3003128164"),new BigInteger("1022350794"), OpcionServicio.DEVLLAMADA);
		ClienteDTO c6 = new ClienteDTO("mario",new BigInteger("3003128165"),new BigInteger("1022350795"), OpcionServicio.DEVLLAMADA);
		ClienteDTO c7 = new ClienteDTO("camilo",new BigInteger("300312816"),new BigInteger("1022350796"), OpcionServicio.DEVLLAMADA);
		ClienteDTO c8 = new ClienteDTO("perz",new BigInteger("3003128167"),new BigInteger("1022350797"), OpcionServicio.ESPERA);
		ClienteDTO c9 = new ClienteDTO("suria",new BigInteger("3003128168"),new BigInteger("1022350798"), OpcionServicio.ESPERA);
		ClienteDTO c10 = new ClienteDTO("iris",new BigInteger("3003128169"),new BigInteger("1022350799"), OpcionServicio.ESPERA);
		ClienteDTO c11 = new ClienteDTO("lola",new BigInteger("3003128170"),new BigInteger("1022350710"), OpcionServicio.ESPERA);
		ClienteDTO c12 = new ClienteDTO("jairo",new BigInteger("3003128171"),new BigInteger("1022350711"), OpcionServicio.DEVLLAMADA);
		ClienteDTO c13 = new ClienteDTO("pepito",new BigInteger("3003128172"),new BigInteger("1022350712"), OpcionServicio.DEVLLAMADA);
		
		// add customers to call center
		callCenter.addCliente(c1);
		callCenter.addCliente(c2);
		callCenter.addCliente(c3);
		callCenter.addCliente(c4);
		callCenter.addCliente(c5);
		callCenter.addCliente(c6);
		callCenter.addCliente(c7);
		callCenter.addCliente(c8);
		callCenter.addCliente(c9);
		callCenter.addCliente(c10);
		callCenter.addCliente(c11);
		callCenter.addCliente(c12);
		callCenter.addCliente(c13);
		mainController.runCallCenter();

	}

	@Test
	public callCenterDispatcher<E> getDispatcher() {
		return dispatcher;
	}
}
