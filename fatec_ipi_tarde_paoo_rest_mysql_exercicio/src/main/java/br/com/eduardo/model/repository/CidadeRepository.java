package br.com.eduardo.model.repository;

import java.util.List;

import javax.xml.ws.Action;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eduardo.model.beans.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	public List <Cidade> buscaPelaLetra(String buscar);
	public Cidade buscaPelaLatiLong(String latitude, String longitude);
}
