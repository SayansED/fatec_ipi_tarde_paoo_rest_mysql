package br.com.eduardo.controller.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.eduardo.model.beans.Livro;
import br.com.eduardo.model.repository.LivroRepository;

/* Faço uma chamada, pedindo os dados do banco e devolvo em formato json, utilizando apenas as bibliotecas*/

@RestController
@RequestMapping ("/livros")
public class LivroResource {
	
	@Autowired
	private LivroRepository livroRepo;
	
	@GetMapping ("/lista")
	public List <Livro> todosOsLivros () {
		return livroRepo.findAll();
	}
	
	@PostMapping ("/salvar")
	//@ResponseStatus (HttpStatus.CREATED)
	public ResponseEntity<Livro> salvar (@RequestBody Livro livro, HttpServletResponse response) {
		try {
			Livro livroCriado = livroRepo.save(livro);
			URI uri = ServletUriComponentsBuilder.
					fromCurrentServletMapping().path("/{id}").
					buildAndExpand(livroCriado.getId()).toUri();
			//response.setHeader("Location", uri.toASCIIString());
			return ResponseEntity.created(uri).body(livroCriado);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
		
	}
}
