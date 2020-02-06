package it.dipvvf.abr.app.index.support.sql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Centralizza l'accesso al datasource recuperato tramite JNDI.
 * Implementa pattern singleton.
 * 
 * @author riccardo.iovenitti
 *
 */
public class Database {
	private static Database _instance = new Database();
	private DataSource ds;

	private Database() {
		try {
			InitialContext cxt = new InitialContext();
			ds = (DataSource)cxt.lookup("java:/comp/env/jdbc/bacheca");

			if (ds == null) {
				throw new RuntimeException("Data source not found!");
			}
		} catch (NamingException ne) {
			throw new RuntimeException("Impossibile recuperare il context!", ne);
		}
	}
	
	public synchronized static Database getInstance() {
		return _instance;
	}
	
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
