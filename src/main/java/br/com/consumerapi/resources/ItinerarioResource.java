package br.com.consumerapi.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.consumerapi.domain.Itinerario;
import br.com.consumerapi.services.ItinerarioService;

@RestController
@RequestMapping(value = "itinerarios")
public class ItinerarioResource {

	@Autowired
	private ItinerarioService service;


	@RequestMapping(method = RequestMethod.GET, value ="/listar/datapoa/{id}")
	public ResponseEntity<List<Itinerario>> findAllClient(@PathVariable String id) {
		List<Itinerario> list = service.findItinerarioClient(id);
		return ResponseEntity.ok().body(list);

	}	

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Void> insert(@RequestBody @Valid Itinerario itinerario) {

		itinerario = service.insert(itinerario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(itinerario.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody @Valid Itinerario itinerario, @PathVariable String id) {
		itinerario.setCodigo(id);
		itinerario = service.update(itinerario);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Itinerario> findByID(@PathVariable String id) {
		Itinerario obj = service.findByIdLinha(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/nome", method = RequestMethod.GET)
	public ResponseEntity<List<Itinerario>> findByNome(@RequestParam(value = "value") String nome) {
		List<Itinerario> obj = service.findByNome(nome);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/codigo", method = RequestMethod.GET)
	public ResponseEntity<Itinerario> findByCodigo(@RequestParam(value = "value") String codigo) {
		Itinerario obj = service.findByCodigo(codigo);
		return ResponseEntity.ok().body(obj);
	}


}