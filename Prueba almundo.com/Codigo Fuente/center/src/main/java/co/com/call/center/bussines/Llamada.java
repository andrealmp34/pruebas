package co.com.call.center.bussines;

import co.com.call.center.controller.EmpleadoController;
import co.com.call.center.controller.LlamadaController;
import co.com.call.center.dto.ClienteDTO;
import java.time.LocalDateTime;
import java.util.Random;

import co.com.call.center.dto.EmpleadoDTO;
import co.com.call.center.utils.EstadoEmpleado;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Andrea
 *
 */
public class Llamada implements Runnable {

	private long id;
	private EmpleadoDTO empleadoDto;
	private ClienteDTO clienteDTO;
	private EmpleadoController empleadoCt;
	private LlamadaController llamadaCt;

	private static final Logger logger = LogManager.getLogger("Call");

	public Llamada(long l, ClienteDTO c, EmpleadoDTO em) {

		try {
			id = l;
			empleadoDto = em;
			clienteDTO = c;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	/**
	 * Metodo para ejecutar las llamadas realiza un registro de la llamada
	 * y actualiza el empleado que la atendio 
	 */
	public void run() {

		llamadaCt = new LlamadaController();
		empleadoCt = new EmpleadoController();
		int duracionLlamada = getRandomDuration(5, 11) * 5000;
		System.out.println("LLamada en curso #" + id + " -Duracion= " + duracionLlamada + " -Tiempo = " + LocalDateTime.now()
				+ " -Cliente= " + clienteDTO.getNombre() + " -Empleado = " + empleadoDto.getNombre());
		try {

			// se registra la llamada atendida
			llamadaCt.registrarLlamada(clienteDTO, id);
			
			
			Thread.sleep(duracionLlamada);
			System.out.println("Ended #" + id + " -EndTime= " + LocalDateTime.now());
			// se termina la llamada y se libera el empleado
			empleadoDto.setEstado(EstadoEmpleado.LIBRE);
			empleadoCt.actualizarEstado(empleadoDto);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 *Devuelve el empleado  
	 * @return
	 */
	public EmpleadoDTO getEmpleado() {
		return empleadoDto;
	}

	/**
	 * Duracion de la llamada
	 * @param min
	 * @param max
	 * @return
	 */
	private int getRandomDuration(int min, int max) {
		Random r = new Random();
		return r.nextInt(max - min) + min;
	}

}
