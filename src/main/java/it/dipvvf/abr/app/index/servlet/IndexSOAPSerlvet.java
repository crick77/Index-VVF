package it.dipvvf.abr.app.index.servlet;

import javax.servlet.ServletConfig;
import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

import it.dipvvf.abr.app.index.soap.IndexSOAPImp;


public class IndexSOAPSerlvet extends CXFNonSpringServlet {
	private static final long serialVersionUID = 7246873684803220750L;

	@Override
	public void loadBus(ServletConfig servletConfig) {
		super.loadBus(servletConfig);
		Bus bus = getBus();
		BusFactory.setDefaultBus(bus);
		Endpoint.publish("/index", new IndexSOAPImp());
		System.out.println("SOAP Service published");
	}
}