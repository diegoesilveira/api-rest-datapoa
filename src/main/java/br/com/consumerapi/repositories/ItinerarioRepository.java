package br.com.consumerapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.consumerapi.domain.Itinerario;
import br.com.consumerapi.domain.Linha;

@Repository
public interface ItinerarioRepository extends JpaRepository<Itinerario, Long>{
	
	default <S extends Itinerario> void saveAll(Object[] entities) {
	}
	
	@Transactional(readOnly=true)
	Optional<Itinerario> findByidLinha(String idLinha);

	@Transactional(readOnly=true)
	List<Itinerario> findByNome(String nome);
	
	
	@Transactional(readOnly=true)
	Optional<Itinerario> findByCodigo(String codigo);

	

}
