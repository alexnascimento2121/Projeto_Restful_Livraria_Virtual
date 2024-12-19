package br.com.rest.livraria_virtual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LivroRepositorio {
	
	private Map<Long,Livro> livros = new HashMap<>();
	
	private static LivroRepositorio repositorio;
	
	public static LivroRepositorio getInstance(){
		if(repositorio == null) {
			repositorio = new LivroRepositorio();
		}
		return repositorio;
	}
	
	private LivroRepositorio() {
		Livro livro1 = new Livro(1L,"livro A","ISBN-1234","Genero Drama",23.99,"ALex");
		Livro livro2 = new Livro(2L,"livro B","ISBN-4321","Genero Romantico",23.99,"Gonçalves");
		
		livros.put(livro1.getId(), livro1);
		livros.put(livro2.getId(), livro2);

	}
	
	public List<Livro> getLivros(){
		return new ArrayList<>(livros.values());
	}

	public Livro getLivroPorIsbn(String isbn) {
		for(Livro livro: livros.values()) {
			if(isbn.equals(livro.getIsbn())) {
				return livro;
			}
		}
		throw new livroNaoEncontradoException();
	}
	
	
	public void adicionaLivro(Livro livro) {
		if(livros.containsKey(livro.getId())) {
			throw new livroExistenteException();
		}
		
		livros.put(livro.getId(), livro);
	}
	
	public void atualizaLivro(Livro livro) {	
			livros.put(livro.getId(),livro);		
	}
	
	public void removeLivro(Long id) {
		if(livros.containsKey(id)) {
			livros.remove(id);
		}else {
			throw new livroNaoEncontradoException();
		}
		
	}

}
