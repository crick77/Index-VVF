package it.dipvvf.abr.app.index.rest;


import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.dipvvf.abr.app.index.support.InverseIndex;

public class IndexService implements Index {

	@Override
	public Response index(int id, String body) {
		InverseIndex.access().add(id, body);
		try {
			System.out.println("Indexing...elaborazione per 5 secondi...");
			Thread.sleep(5*1000);
		}
		catch(InterruptedException ie) {}
		
		System.out.println("Indexing EFFETTUATO.");
		return Response.ok(new IndexResponse(id)).build();
	}

	@Override
	public Response search(String query) {
		List<Integer> docIds = InverseIndex.access().search(query);
		if(docIds.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		else {
			return Response.ok(docIds).build();
		}
	}

	@Override
	public Response delete(int id) {
		InverseIndex.access().delete(id);
		return Response.ok().build();
	}

	@Override
	public Response statistics() {
		return Response.ok(InverseIndex.access().getStatistics()).build();
	}
	
	/**
	 * Inner class per la response json 
	 * 
	 * @author ospite
	 *
	 */
	class IndexResponse {
		private int id;
		
		public IndexResponse() {
		}

		public IndexResponse(int id) {
			this.id = id;
		}
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}
}
