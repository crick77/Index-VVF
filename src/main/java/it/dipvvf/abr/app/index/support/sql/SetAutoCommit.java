package it.dipvvf.abr.app.index.support.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe di supporto per la chiusura automatica dell'autocommit in un
 * try-with-resource. Essendo le connessioni in pool vanno reinserite con lo
 * stato di "autocommit" originale.
 * 
 * @author riccardo.iovenitti
 *
 */
public class SetAutoCommit implements AutoCloseable {
	private Connection con;
	private boolean oldAutoCommitStatus;

	public SetAutoCommit(Connection connection, boolean autoCommit) throws SQLException {
		// salva per dopo
        con = connection;
        // Salva l'autocommit attuale
        oldAutoCommitStatus = connection.getAutoCommit();
        connection.setAutoCommit(autoCommit);
    }

	@Override
	public void close() throws SQLException {
		// alla chiusura, ripristina
		con.setAutoCommit(oldAutoCommitStatus);
	}
}
