package it.dipvvf.abr.app.index.soap;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "index")
public interface IndexSOAP {
	@WebMethod
	@Oneway
	public void reindex();
}
