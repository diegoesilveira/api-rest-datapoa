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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.consumerapi.domain.Linha;
import br.com.consumerapi.services.LinhaService;

@RestController
@RequestMapping(value = "linhas")
public class LinhaResource {

	@Autowired
	private LinhaService service;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/listar/datapoa")
	public ResponseEntity<List<Linha>> findAllClient() {
		List<Linha> list = service.findAllClient();
		return ResponseEntity.ok().body(list);

	}
	
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "/listar/datapoa/{id}")
	public ResponseEntity<List<Linha>> findNomeClient(@PathVariable String id) {
		String url = "www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
		UriComponents uri =  UriComponentsBuilder.newInstance()
			.scheme("http")
			.host(url)
			.build();
		List<Linha> obj = (List<Linha>) restTemplate.getForEntity(uri.toUriString(), Linha.class).getBody();
		obj.stream().filter(p -> p.getNome().equalsIgnoreCase(id)).toArray();
		return ResponseEntity.ok().body(obj);
		
			

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Linha>> findAll() {
		List<Linha> list = service.findAll();
		if (list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(list);

	}

	@RequestMapping(value = "/idlinha", method = RequestMethod.GET)
	public ResponseEntity<Linha> findByID(@RequestParam(value = "value") String idLinha) {
		Linha obj = service.findByIdLinha(idLinha);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/nome", method = RequestMethod.GET)
	public ResponseEntity<List<Linha>> findByNome(@RequestParam(value = "value") String nome) {
		List<Linha> obj = service.findByNome(nome);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/codigo", method = RequestMethod.GET)
	public ResponseEntity<Linha> findByCodigo(@RequestParam(value = "value") String codigo) {
		Linha obj = service.findByCodigo(codigo);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody @Valid Linha linha) {
		linha = service.insert(linha);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cod}").buildAndExpand(linha.getCod()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody @Valid Linha linha, @PathVariable Long id) {
		linha.setCod(id);
		linha = service.update(linha);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
