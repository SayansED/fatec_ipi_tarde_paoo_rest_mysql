package br.com.eduardo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eduardo.model.beans.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
	
}
