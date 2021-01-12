package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.DBConnection;
import Entities.Rider;

public class RiderDAOPostgresImp implements RiderDAO{

	@Override
	public Rider CercaRiderPerId(int idRider) throws SQLException {
		
		Rider rider = new Rider();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT R.nomer,R.cognomer,R.telefonor,R.veicolo FROM rider AS R WHERE id_rider=" + idRider +"");	
			
		if(rs.next()){
				
			String nomeRider= rs.getString(1);
			String cognomeRider = rs.getString(2);
			String telefonoRider = rs.getString(3);
			String veicolo = rs.getString(4);
						
			rider = new Rider(idRider,nomeRider,cognomeRider,telefonoRider,veicolo);
				
		}
				
		rs.close();
		st.close();
		conn.close();
		
		return rider;
	}

	@Override
	public List<Object[]> getRiderDaSede(int idSede) throws SQLException {
		
		List<Object[]> rider = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT R.id_rider,R.nomer,R.cognomer,R.telefonor,R.veicolo FROM rider AS R WHERE id_sede=" + idSede +" ORDER BY id_rider ASC");
		
		while(rs.next()) {
				
			String idRider = rs.getString(1);
			String nomeRider = rs.getString(2);
			String cognomeRider = rs.getString(3);
			String telefonoRider = rs.getString(4);
			String veicolo = rs.getString(5);
				
			Object[] object = new Object[] {idRider,nomeRider,cognomeRider,telefonoRider,veicolo};
				
			rider.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
			
		return rider;
	}



	@Override
	public int InserisciRider(int idRider, String nome, String cognome, String telefono, String veicolo, int idSede) throws SQLException {
		
		Connection conn = null;
		int  risultato = 0;		
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		 risultato = st.executeUpdate("INSERT INTO rider(id_rider,nomer,cognomer,telefonor,veicolo,id_sede) VALUES ("+idRider+ ",'" + nome +"','" + cognome + "','"
					+ telefono + "','" + veicolo + "',"+ idSede + ")");
	
		st.close();
		conn.close();
		
		return  risultato;
	}

	@Override
	public int EliminaRider(int idRider,int idSede) throws SQLException  {
		
		Connection conn = null;
		int  risultato = 0;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		risultato = st.executeUpdate("DELETE FROM rider WHERE id_rider="+idRider+" AND id_sede=" + idSede + "");
	
		st.close();
		conn.close();
		
		return risultato;
	}

	@Override
	public int AggiornaRider(int idRider,String telefono, String veicolo) throws SQLException {
		Connection conn = null;
		int risultato = 0;
		
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		risultato = st.executeUpdate("UPDATE rider SET veicolo='"+ veicolo + "', telefonor='" + telefono + "' WHERE id_rider=" + idRider);
	
		st.close();
		conn.close();
		
		return risultato;
	}

	@Override
	public int idProssimoRider() throws SQLException{
		
		Connection conn = null;
		int risultato = 0;
		
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT idProssimoRider()");	
			
		if(rs.next()){
			 risultato = rs.getInt(1);
		}
				
		rs.close();
		st.close();
		conn.close();
		
		return  risultato;
	}


	public List<Object[]> getRiderPerSede(int idSede) throws SQLException {
		
		List<Object[]> rider = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT disponibilit\u00E0, id_rider, nomer || ' ' || cognomer AS nome, telefonor AS telefono,\r\n"
				+ "numeroordini \r\n"
				+ "FROM rider\r\n"
				+ "WHERE id_sede = '" + idSede +"'");
		
		while(rs.next()) {
				
			char disponibilita;	
			int idRider = rs.getInt(2);
			String nomeRider = rs.getString(3);
			String telefonoRider = rs.getString(4);
			int numeroordini = rs.getInt(5);
			
			boolean libero=rs.getBoolean(1);
			if(libero) disponibilita='L'; //libero
			else if(!libero && numeroordini>0) disponibilita='C'; //in consegna 
			else disponibilita='X'; //non disponibile
			
			Object[] object = new Object[] {disponibilita,idRider,nomeRider,telefonoRider,numeroordini};
				
			rider.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
			
		return rider;
		
		
		
	}
}
