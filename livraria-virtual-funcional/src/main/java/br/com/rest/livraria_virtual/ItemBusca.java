package br.com.rest.livraria_virtual;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.Link.JaxbAdapter;

@SuppressWarnings("deprecation")
@XmlRootElement(name="itemBusca")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemBusca {
	
	@XmlElement
	private Livro livro;
	
	@SuppressWarnings("deprecation")
	@XmlElement
	@XmlJavaTypeAdapter(LinkAdapter.class)
	private List<Link> links = new ArrayList<>();
	
	public void addLink(Link link) {
		this.links.add(link);
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	

}
