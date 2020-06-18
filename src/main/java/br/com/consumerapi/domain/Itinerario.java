package br.com.consumerapi.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
public class Itinerario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod;
	
	private String idLinha;

	private String nome;

	private String codigo;

	@ElementCollection
	@CollectionTable(name="itineratio_rota", joinColumns = @JoinColumn(name="itinerario_id"))
	@MapKeyColumn(name="rota")
	private Map<String, Rota> rotas;

	public Itinerario() {
	}

	public Itinerario(String idLinha, String nome, String codigo) {
		this.idLinha = idLinha;
		this.nome = nome;
		this.codigo = codigo;
	}

	public String getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(String idLinha) {
		this.idLinha = idLinha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public Map<String, Rota> getRotas() {
		return rotas;
	}

	public void setRotas(Map<String, Rota> rotas) {
		this.rotas = rotas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLinha == null) ? 0 : idLinha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itinerario other = (Itinerario) obj;
		if (idLinha == null) {
			if (other.idLinha != null)
				return false;
		} else if (!idLinha.equals(other.idLinha))
			return false;
		return true;
	}


}
