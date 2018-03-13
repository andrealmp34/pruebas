package co.com.call.center.controller;

import org.springframework.stereotype.Controller;

import co.com.call.center.bussines.CallCenter;
import co.com.call.center.dto.EmpleadoDTO;
import co.com.call.center.persistence.factory.CallCenterFactory;
import co.com.call.center.utils.Constantes;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Deque;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author Andrea
 *
 */
@Controller
public class EmpleadoController {

	CallCenter<EmpleadoDTO> callCenter;
	Map<String, Deque<EmpleadoDTO>> shiftEmployees;

	public EmpleadoController() {

	}

	public EmpleadoController(CallCenter<EmpleadoDTO> cc) {
		callCenter = cc;
		shiftEmployees = cc.getAllEmployeesInShift();
	}

	/**
	 * Obtiene el primer empleado que se encuentre libre
	 * @return
	 */
	public EmpleadoDTO obtenerEmpleado() {

		List<EmpleadoDTO> empleadoDto = new ArrayList<EmpleadoDTO>();
		EmpleadoDTO emepleado= null;

		try {

			empleadoDto = CallCenterFactory.getEmpleadoDAO().obtenerEmpleadoLibre();
			if(empleadoDto.size() > 0 ){
				emepleado=empleadoDto.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();;

		}
		return emepleado;
	}

	/**
	 * Actualiza los empleados para poder ocuparlos o liberlos segun sea el caso
	 * 
	 * @param emepleadoDto
	 * @return
	 */
	public boolean actualizarEstado(EmpleadoDTO emepleadoDto) {

		boolean respuesta=false;

		try {

			respuesta = CallCenterFactory.getEmpleadoDAO().actualizarEstado(emepleadoDto);

		} catch (Exception e) {
			Constantes.logger.entry(e);

		}
		return respuesta;
	}


}
