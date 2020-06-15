package br.com.consumerapi.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.consumerapi.domain.Linha;
import br.com.consumerapi.repositories.LinhaRepository;
import br.com.consumerapi.services.exception.ObjectNotFoundException;

@Service
public class LinhaService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LinhaRepository repository;

	public List<Linha> findAllClient() {
		String url = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";

		Linha[] obj = restTemplate.getForObject(url, Linha[].class);
		List<Linha> lista = Arrays.asList(obj);
		repository.saveAll(lista);
		return lista;
	}
	
	public List<Linha> findAll() {
		return repository.findAll();
	}

	public List<Linha> findByNome(String nome) {
		List<Linha> obj = repository.findByNome(nome);
		return obj;

	}
	
	public Linha findByIdLinha(String id) {
		Optional<Linha> obj = repository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("ID da Linha não encontrado! Id: " + id + ", Tipo: " + Linha.class.getName()));
	}
	
		
	public Linha insert(Linha idLinha) {
		idLinha.setCod(null);
		return repository.save(idLinha);
	}
	
	public Linha update(Linha linha) {
		return repository.save(linha);
	}

	public void delete(Long cod) {
			repository.deleteById(cod);
		
		
	}

	public Linha findByCodigo(String codigo) {
		Optional<Linha> obj = repository.findByCodigo(codigo);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Codigo da Linha não encontrado! Id: " + codigo + ", Tipo: " + Linha.class.getName()));
	}

	
	
		
	
	
}
