package br.com.rest.Livraria_Virtual_Cliente;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import jakarta.ws.rs.core.Link;

@SuppressWarnings("hiding")
public class LinkAdapter<LinkJaxb> extends XmlAdapter<LinkJaxb, Link>{
	
	public LinkAdapter() {}
	
		
	public Link unmarshal(LinkJaxb p1) {
        Link.Builder builder = Link
                                .fromUri(((Link) p1).getUri())
                                .rel(((Link) p1).getRel())
                                .type(((Link) p1).getType());

        return builder.build();
    }

    public LinkJaxb marshal(Link p1) {
        @SuppressWarnings("unchecked")
		LinkJaxb linkJaxb = (LinkJaxb) new br.com.rest.Livraria_Virtual_Cliente.LinkJaxb(
                        p1.getUri(), 
                        p1.getRel(), 
                        p1.getType()
                    );
		return linkJaxb;
    }
}
