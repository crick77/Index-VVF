package it.dipvvf.abr.app.index.support.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Effettua automaticamente il rollback nei casi in cui venga usato un try-with-resource.
 * 
 * @author riccardo.iovenitti
 *
 */
public class Rollback implements AutoCloseable {
	private Connection con;
    private boolean hasBeenCommitted = false;

    public Rollback(Connection conn) throws SQLException {
        this.con = conn;        
    }

    public void commit() throws SQLException {
    	// commit ok!
        con.commit();
        hasBeenCommitted = true;
    }

    @Override
    public void close() throws SQLException {
    	// stiamo chiudendo, abbiamo un commit fatto?
        if(!hasBeenCommitted) {
        	// no, annulla tutto!
            con.rollback();
        }
    }
}
