package it.dipvvf.abr.app.index.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;

import it.dipvvf.abr.app.index.support.InverseIndex;

public class IndexRestServlet extends CXFNonSpringJaxrsServlet {
	private static final long serialVersionUID = -2242818805107181424L;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		
		// Inizializza l'indice
		InverseIndex.access();
	}

	@Override
	public void destroy() {
		// Salva l'indice prima di chiudere
		InverseIndex.access().saveToFile();
		
		super.destroy();
	}
}