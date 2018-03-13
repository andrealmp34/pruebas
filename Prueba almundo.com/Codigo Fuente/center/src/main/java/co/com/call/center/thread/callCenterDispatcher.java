package co.com.call.center.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import co.com.call.center.bussines.Llamada;
import co.com.call.center.controller.ClienteController;
import co.com.call.center.controller.EmpleadoController;
import co.com.call.center.controller.LlamadaController;
import co.com.call.center.dto.ClienteDTO;
import co.com.call.center.dto.EmpleadoDTO;
import co.com.call.center.utils.Constantes;
import co.com.call.center.utils.OpcionServicio;

/**
 * 
 * @author Andrea
 *
 * @param <E>
 */
public class callCenterDispatcher<E> {

	private ExecutorService ejecutar;
	private EmpleadoController empleadoCt;
	private ClienteController clienteCt;
	private LlamadaController llamadaCt;
	private static final Logger logger = LogManager.getLogger("Dispatcher");

	/**
	 * Constructo de la clase se cargan los contraladores de empleado y cliente
	 * 
	 * @param empleadoCt
	 * @param clienteCt
	 */
	public callCenterDispatcher(EmpleadoController empleadoCt, ClienteController clienteCt) {

		try {
			this.empleadoCt = empleadoCt;
			this.clienteCt = clienteCt;
			ejecutar = Executors.newFixedThreadPool(Constantes.NUMERO_LLAMADAS);
		} catch (Exception e) {
			// TODO: handle exception
			Constantes.logger.error(e);
		}

	}

	/**
	 * Metodo donde se procesan las llamadas , si hay empleados libres se ejecuta la llamada,
	 * si el cliente da la opcion de espera se encola para ser atendido cuand esten disponibles los empleados
	 * Si no se agenda una llamada tambien para cuando los empleados se liberen se realice la llamada.
	 * @param pingLlamada
	 * @param cleinteDTO
	 */
	public void dispatchCall(ClienteDTO cleinteDTO) {

		try {
			llamadaCt = new LlamadaController();
			long pingLlamada = llamadaCt.getNumeroPing();
			EmpleadoDTO empleadoDTO = empleadoCt.obtenerEmpleado();
			if (empleadoDTO != null) {
				Runnable call = new Llamada(pingLlamada, cleinteDTO, empleadoDTO);
				call.run();
			}
			else if (cleinteDTO.getOpcion().toString().equals(OpcionServicio.ESPERA.toString())) {
				System.out.println("No hay empleados disponibles para procesar la llamada #= " + pingLlamada + " -customer = "
						+ cleinteDTO.getNombre()) ;
				clienteCt.addProximoCliente(cleinteDTO);
			} else if (cleinteDTO.getOpcion().toString().equals(OpcionServicio.DEVLLAMADA.toString())) {
				System.out.println("Se registra la llamadax #= " + pingLlamada + " -cliente = " + cleinteDTO.getNombre() );
				llamadaCt.agendarLlamada(cleinteDTO, pingLlamada);
			}
			
			verificarLLamadaEnEspera();
			verificarLLamadasAgendadas();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Con este metodo se verifican las llamadas agendadas
	 * para que en el momento que se desocupe un empleado
	 * pueda atender el cliente
	 */
	public void verificarLLamadasAgendadas(){
		
		try{
			EmpleadoDTO empleadoDTO = empleadoCt.obtenerEmpleado();
			ClienteDTO clienteDto = llamadaCt.verificarLllamadaAgendada();
			
			if (empleadoDTO != null && clienteDto !=null ) {
				Runnable call = new Llamada(llamadaCt.getNumeroPing(), clienteDto, empleadoDTO);
				ejecutar.execute(call);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * En este metodo se verifican las llamadas en espera para que cuando 
	 * se libere un empleado sea atendido el cliente en cola
	 */
	public void verificarLLamadaEnEspera(){
		
		try{
			EmpleadoDTO empleadoDTO = empleadoCt.obtenerEmpleado();
			
			if (empleadoDTO != null ) {
				Runnable call = new Llamada(llamadaCt.getNumeroPing(), clienteCt.getProximoCliente(), empleadoDTO);
				ejecutar.execute(call);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @throws InterruptedException
	 */
	public void terminarDispatch() throws InterruptedException {
		ejecutar.shutdown();
		ejecutar.awaitTermination(11, TimeUnit.SECONDS);
		logger.info("All threads finished.");
	}

}
