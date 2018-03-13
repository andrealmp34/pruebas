package co.com.call.center.controller;

import java.util.ArrayList;
import java.util.List;

import co.com.call.center.dto.ClienteDTO;
import co.com.call.center.dto.LlamadaDTO;
import co.com.call.center.persistence.factory.CallCenterFactory;
import co.com.call.center.utils.Constantes;

/**
 * 
 * @author Andrea
 *
 */
public class LlamadaController {

	/**
	 * Agenda una llamada para que cuadno se libere un empleado la pueda devolver
	 * @param cleinteaDto
	 * @param ping
	 * @return
	 */
	public boolean agendarLlamada(ClienteDTO cleinteaDto, long ping) {

		boolean respuesta = false;
		LlamadaDTO llamadaDto = new LlamadaDTO();
		try {

			llamadaDto.setPing(ping);
			llamadaDto.setNombreContacto(cleinteaDto.getNombre());
			llamadaDto.setNumeroContacto(cleinteaDto.getNumeroContacto());
			llamadaDto.setNumeroCedula(cleinteaDto.getNumeroCedula());
			llamadaDto.setNumeroCedula(cleinteaDto.getNumeroCedula());

			respuesta = CallCenterFactory.getLlamadaDAO().agendarLllamada(llamadaDto);

		} catch (Exception e) {
			// TODO: handle exception
			Constantes.logger.error(e);
		}
		return respuesta;
	}

	/**
	 * Deja el registro de las llamadas recibidas para tener la auditoria
	 * del servicio
	 * @param cleinteaDto
	 * @param ping
	 * @return
	 */
	public boolean registrarLlamada(ClienteDTO cleinteaDto, long ping) {

		boolean respuesta = false;
		LlamadaDTO llamadaDto = new LlamadaDTO();
		try {

			llamadaDto.setPing(ping);
			llamadaDto.setNombreContacto(cleinteaDto.getNombre());
			llamadaDto.setNumeroContacto(cleinteaDto.getNumeroContacto());
			llamadaDto.setNumeroCedula(cleinteaDto.getNumeroCedula());

			respuesta = CallCenterFactory.getLlamadaDAO().registrarLllamada(llamadaDto);

		} catch (Exception e) {
			// TODO: handle exception
			Constantes.logger.error(e);
		}
		return respuesta;
	}

   /**
    * Devuelve en orden de fechas las llamadas que tienen que se devueltas para
    * poder atender el servicio
    * @return
    */
	public ClienteDTO verificarLllamadaAgendada() {

		List<LlamadaDTO> llamadaDtoList = new ArrayList<LlamadaDTO>();
		LlamadaDTO llamadaDto = null;
		ClienteDTO clienteDto = null;
		try {

			llamadaDtoList = CallCenterFactory.getLlamadaDAO().obtenerLlmadaAgendada();
			if(llamadaDtoList.size()>0){
				llamadaDto = llamadaDtoList.get(0);
				clienteDto.setNombre(llamadaDto.getNombreContacto());
				clienteDto.setNumeroCedula(llamadaDto.getNumeroCedula());
				clienteDto.setNumeroContacto(llamadaDto.getNumeroContacto());
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			Constantes.logger.error(e);
		}
		return clienteDto;
	}
	
	/**
	 * Da el numero de ping con el cual sera atendido la persona
	 * @return
	 */
	public long getNumeroPing() {

		long numeroAleatorio = 0;
		try {
			numeroAleatorio = (long) (Math.random() * 2500 + 1);
		} catch (Exception e) {
			// TODO: handle exception
			Constantes.logger.error(e);
		}
		return numeroAleatorio;
	}

}
