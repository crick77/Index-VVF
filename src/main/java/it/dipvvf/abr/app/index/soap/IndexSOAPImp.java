package it.dipvvf.abr.app.index.soap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.dipvvf.abr.app.index.support.InverseIndex;
import it.dipvvf.abr.app.index.support.sql.Database;

public class IndexSOAPImp implements IndexSOAP {

	@Override
	public void reindex() {
		List<Integer> ids = new ArrayList<>();
		List<String> titoli = new ArrayList<>();
		
		System.out.print("Richiesta di reindicizzazione in corso...");
		try (Connection con = Database.getInstance().getConnection()) {
			String sql = "SELECT p.id, p.titolo FROM pubblicazione p";
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {
					while(rs.next()) {
						ids.add(rs.getInt("id"));
						titoli.add(rs.getString("titolo"));
					}
				}
			}
		}
		catch(SQLException sqle) {
			System.err.println("\nReindicizzazione annullata a causa di errore SQL: "+sqle);
		}

		System.out.println("Documenti caricati da database: "+ids.size());
		InverseIndex.access().wipe();
		System.out.println("Indice reinizializzato. Ricreazione indice in corso...");
		for(int i = 0;i<ids.size();i++) {
			InverseIndex.access().add(ids.get(i), titoli.get(i));
		}
		
		try {
			Thread.sleep(2500);
		}
		catch(InterruptedException ie) {
		}
		
		System.out.println("Reindicizzazione completata: "+InverseIndex.access().getStatistics());
	}
}
