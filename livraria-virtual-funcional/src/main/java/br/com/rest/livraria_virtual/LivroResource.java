package br.com.rest.livraria_virtual;

import java.net.URI;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;

@Path("livro")
public class LivroResource {
	
	private LivroRepositorio repositorio = LivroRepositorio.getInstance();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Livros getLivros(){
		Livros livros = new Livros();
		livros.setLivros(repositorio.getLivros());
		return livros;
	}
	
//	@GET sem link  //sem padrao hateoas
//	@Path("/{isbn}")
//	public Livro getLivroPorIsbn(@PathParam("isbn")String isbn) {
//		try {
//			return repositorio.getLivroPorIsbn(isbn);
//		}catch(livroNaoEncontradoException e) {
//			throw new WebApplicationException(Status.NOT_FOUND);
//		}				
//	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response criaLivro(Livro livro) {
		try {
			repositorio.adicionaLivro(livro);
		}catch(livroExistenteException e) {
			throw new WebApplicationException(Status.CONFLICT);
		}		
		URI uriLocation = UriBuilder.fromPath("livro/{isbn}").build(livro.getIsbn());
		
		return Response.created(uriLocation).entity(livro).build(); // tras a uri e criar a entidade 
	}
	
	@PUT
	@Path("/{isbn}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response atualizaLivro(@PathParam("isbn")String isbn, Livro livro) {
		try {
			Livro estoque= repositorio.getLivroPorIsbn(isbn);
			
			estoque.setAutor(livro.getAutor());
			estoque.setGenero(livro.getGenero());
			estoque.setPreco(livro.getPreco());
			estoque.setIsbn(livro.getTitulo());
			
			repositorio.atualizaLivro(estoque);
		} catch (livroNaoEncontradoException e) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		return Response.ok(livro).build();		
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public void removeLivro(@PathParam("id")Long id) {
		try {
			repositorio.removeLivro(id);			
			} catch (livroNaoEncontradoException e) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}			
	}

	@GET
	@Path("/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public ItemBusca getLivroPorIsbn(@PathParam("isbn")String isbn) {
		try {
			Livro livro = repositorio.getLivroPorIsbn(isbn);
			
			ItemBusca item = new ItemBusca();
			item.setLivro(livro);
			
			Link link =Link.fromUriBuilder(UriBuilder.fromUri("/carrinho/"+livro.getId())).rel("carrinho").type("POST").build();
			
			return item;
		}catch(livroNaoEncontradoException e) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}				
	}
	
	
	}

