package center;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.com.call.center.bussines.CallCenter;
import co.com.call.center.controller.EmpleadoController;
import co.com.call.center.dto.EmpleadoDTO;
import co.com.call.center.utils.EstadoEmpleado;

public class EmpleadoTest {
	EmpleadoDTO o1, o2, o3, s1, s2, d1;
	CallCenter<EmpleadoDTO> callCenter;
	EmpleadoController ec;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		o1 = new EmpleadoDTO("Celeste", "operator");
		o2 = new EmpleadoDTO("Octavio", "operator");
		o2.setEstado(EstadoEmpleado.LIBRE);
		o3 = new EmpleadoDTO("Raul", "operator");
		o3.setEstado(EstadoEmpleado.LIBRE);
		s1 = new EmpleadoDTO("Claudia", "supervisor");
		s2 = new EmpleadoDTO("Carlos", "supervisor");
		s2.setEstado(EstadoEmpleado.LIBRE);
		d1 = new EmpleadoDTO("Matias", "director");
		d1.setEstado(EstadoEmpleado.LIBRE);
		callCenter = new CallCenter<EmpleadoDTO>();
		callCenter.addEmployeeToShift(o1);
		callCenter.addEmployeeToShift(o2);
		callCenter.addEmployeeToShift(o3);
		callCenter.addEmployeeToShift(s1);
		callCenter.addEmployeeToShift(s2);
		callCenter.addEmployeeToShift(d1);
		ec = new EmpleadoController(callCenter);
	}

	@Test
	public void isSameEmployee() {
		assertEquals("Different employees", o1, o1);
	}

	@Test
	public void isNotSameEmployee() {
		assertNotEquals("Same employees", o1, o2);
	}

	@Test
	public void sameOperatorsNumber() {
		assertEquals("Not the right number of operators", callCenter.getAllEmployeesInShift().get("operator").size(),
				3);
	}

	@Test
	public void sameSupervisorsNumber() {
		assertEquals("Not the right number of supervisors",
				callCenter.getAllEmployeesInShift().get("supervisor").size(), 2);
	}

	@Test
	public void sameDirectorsNumber() {
		assertEquals("Not the right number of directors", callCenter.getAllEmployeesInShift().get("director").size(),
				1);
	}

	@Test
	public void getAvailableEmployee() throws InterruptedException {
		assertEquals(ec.getClass().getName(), "Celeste");
		assertEquals(ec.getClass().getName(), "Claudia");
	}

	/**
	 * Expecting 6 employees busy in the call center shift
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void employeesBusyCount() throws InterruptedException {
		assertEquals(ec.obtenerEmpleado(), 4);
	}

}