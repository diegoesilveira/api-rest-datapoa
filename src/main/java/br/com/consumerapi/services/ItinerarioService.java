package br.com.consumerapi.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.consumerapi.domain.Itinerario;
import br.com.consumerapi.domain.Linha;
import br.com.consumerapi.repositories.ItinerarioRepository;
import br.com.consumerapi.services.exception.ObjectNotFoundException;

@Service
public class ItinerarioService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ItinerarioRepository repository;
	
	public List<Itinerario> findItinerarioClient(String cod) {
		String url = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p="+cod;

		Itinerario[] obj = restTemplate.getForObject(url, Itinerario[].class);
		List<Itinerario> itinerario = Arrays.asList(obj);
		repository.saveAll(itinerario);
		return itinerario;
	}
	
	public List<Itinerario> findByNome(String nome) {
		List<Itinerario> obj = repository.findByNome(nome);
		return obj;

	}
	
	public Itinerario findByIdLinha(String id) {
		Optional<Itinerario> obj = repository.findByidLinha(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Id Linha não encontrado! Id: " + id + ", Tipo: " + Linha.class.getName()));
	}
	
	public Itinerario findByCodigo(String id) {
		Optional<Itinerario> obj = repository.findByCodigo(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Codigo não encontrado! Id: " + id + ", Tipo: " + Linha.class.getName()));
	}
	
		
	public Itinerario insert(Itinerario id) {
		id.setCodigo(null);
		return repository.save(id);
	}
	
	public Itinerario update(Itinerario id) {
		return repository.save(id);
	}

	public void delete(Long cod) {
			repository.deleteById(cod);
		
		
	}

}
