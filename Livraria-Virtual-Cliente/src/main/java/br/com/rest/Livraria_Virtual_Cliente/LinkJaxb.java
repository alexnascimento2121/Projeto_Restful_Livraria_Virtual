package br.com.rest.Livraria_Virtual_Cliente;

import java.net.URI;

import javax.xml.bind.annotation.XmlAttribute;

public class LinkJaxb {
	private URI uri;
    private String rel;
    private String type;

    public LinkJaxb() {
        this(null, null, null);
    }

    public LinkJaxb(URI uri, 
                    String rel, 
                    String type) 
    {
        this.uri = uri;
        this.rel = rel;
        this.type = type;
    }

    @XmlAttribute(name = "href")
    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    @XmlAttribute(name = "rel")
    public String getRel(){
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    @XmlAttribute(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
