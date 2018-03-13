package co.com.call.center.persistence.dao;

import co.com.call.center.dto.LlamadaDTO;

/**
 * 
 * @author Andrea
 *
 */
public interface LlamadaDAO {

	public boolean agendarLllamada(LlamadaDTO llamadaDto);
	public boolean registrarLllamada(LlamadaDTO clienteDto);

}
