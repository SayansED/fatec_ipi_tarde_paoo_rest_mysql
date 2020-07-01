package br.com.eduardo.controller.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.eduardo.model.beans.Cidade;
import br.com.eduardo.model.repository.CidadeRepository;

@RestController
@RequestMapping ("/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	// Exercício 1.1
	@GetMapping ("/listaTodas")
	public List <Cidade> todasCidades () {
		return cidadeRepo.findAll();
	}
	
	// Exercício 1.2
	@GetMapping ("/{letra}")
	public List<Cidade>  pelaLetra (@PathVariable String letra) {
		return cidadeRepo.buscaPelaLetra(letra);
	}
	
	// Exercício 1.3
	@GetMapping ("/{latitude}/{longitude}")
	public Cidade pelaLatiLong (@PathVariable String latitude, @PathVariable String longitude) {
		return cidadeRepo.buscaPelaLatiLong(latitude, longitude);
	}
	
	// Exercício 1.4
	@PostMapping ("/salvar")
	public ResponseEntity<Cidade> salvar (@RequestBody Cidade cidade, HttpServletResponse response) {
		try {
			Cidade cidadeCadastro = cidadeRepo.save(cidade);
			URI uri = ServletUriComponentsBuilder.
					fromCurrentServletMapping().path("/{id}").
					buildAndExpand(cidadeCadastro.getId()).toUri();
			return ResponseEntity.created(uri).body(cidadeCadastro);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
