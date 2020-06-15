package br.com.consumerapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.consumerapi.domain.Linha;

@Repository
public interface LinhaRepository extends JpaRepository<Linha, Long> {


	default <S extends Linha> void saveAll(Object[] entities) {
	}
	
	@Transactional(readOnly=true)
	List<Linha> findByNome(String nome);

	@Transactional(readOnly=true)
	Optional<Linha> findByCodigo(String codigo);
			
	
	@Transactional(readOnly=true)
	Optional<Linha> findById(String id);
	
}
