package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entities.Ordine;
import Entities.Rider;
import Database.DBConnection;

public class OrdineDAOPostgresImp implements OrdineDAO {

	
	@Override
	public void IniziaConsegna(Integer idRider,boolean annulla) {
		Connection conn = null;
		String data=null;
		
		if(annulla) {
			data="NULL";
		}
		else {
			data="CURRENT_TIMESTAMP";
		}
		
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
		
			String inizio="UPDATE ordine SET inizioconsegna = "+data+" WHERE id_rider='"+idRider+"' AND fineconsegna IS NULL";	
			st.executeQuery(inizio);
		
			st.close();
			conn.close();
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
	}
	
	@Override
	public void TerminaConsegna(Integer idRider) {
		Connection conn = null;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			conn.setAutoCommit(false);
			
			String termine="UPDATE ordine SET fineconsegna = CURRENT_TIMESTAMP WHERE id_rider='"+idRider+"' AND fineconsegna IS NULL";	
			st.addBatch(termine);
			
			String disponibile="UPDATE rider SET disponibilit\u00E0 = true , numeroordini=0  WHERE id_rider='"+idRider+"'"; 
			st.addBatch(disponibile);
			conn.commit();
			
			conn.setAutoCommit(true);
			st.close();
			conn.close();
		}catch(SQLException e){				
			e.printStackTrace();	
		}

	}

	
	@Override
	public void CancellaOrdine(Integer idOrdine) {
		Connection conn = null;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			
			
			st.executeUpdate("DELETE FROM ordine WHERE id_ordine='"+idOrdine+ "'");	
			
	
			st.close();
			conn.close();
		}catch(SQLException e){				
			e.printStackTrace();	
		}

	}
	
	@Override
	public void CancellaCodiceRider(Integer idRider) {
		Connection conn = null;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			conn.setAutoCommit(false);
			
			st.addBatch("UPDATE ordine SET id_rider=NULL WHERE id_rider='"+idRider+"' AND fineconsegna IS NULL");	
			st.addBatch("UPDATE rider SET numeroordini=0  WHERE id_rider='"+idRider+"'");
			
			conn.commit();
			conn.setAutoCommit(true);
			st.close();
			conn.close();
		}catch(SQLException e){				
			e.printStackTrace();	
		}

	}
	
	
	
	
	@Override
	public List<Object[]> getOrdiniTabella(int idSede) throws SQLException {
		
		List<Object[]> ordini = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT  id_ordine AS CodOrdine,id_rider AS CodRider, id_cliente AS CodCliente, nomec || ' ' || cognomec AS NomeCliente,\r\n"
				+ "				via || ' ' || numcivico || ',' || citt\u00E0 AS Indirizzo, telefonoc AS TelefonoCliente,\r\n"
				+ "			    totale AS Totale, inizioconsegna AS Stato\r\n"
				+ "				FROM ordine AS O NATURAL JOIN infoordine AS I\r\n"
				+ "				NATURAL JOIN cliente AS C\r\n"
				+ "				WHERE id_sede='"+idSede+"' AND fineconsegna IS NULL\r\n"
				+ "				ORDER BY id_ordine ASC");
		while(rs.next()) {
				
			int CodOrdine = rs.getInt(1);
			int CodRider = rs.getInt(2);
			int CodCliente = rs.getInt(3);
			String NomeCliente= rs.getString(4);
			String Indirizzo= rs.getString(5);
			String TelefonoCliente = rs.getString(6);
			String Totale= "\u20AC "+String.valueOf(rs.getFloat(7));		
			java.sql.Timestamp InizioConsegna = rs.getTimestamp(8);
			
			char Stato = 'A'; //Attesa default
			if(InizioConsegna != null) {
				Stato = 'S'; //Spedito se presente la data inizioConsegna
			}
				
			Object[] object = new Object[] {CodOrdine,CodRider,CodCliente,NomeCliente,Indirizzo,TelefonoCliente,Totale,Stato};
				
			ordini.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
		
		return ordini;
	}
	
	@Override
	public List<Object[]> getOrdiniFiltroRider(int idRider) throws SQLException{
		List<Object[]> ordini = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT  id_ordine AS CodOrdine,id_rider AS CodRider, id_cliente AS CodCliente, nomec || ' ' || cognomec AS NomeCliente,\r\n"
				+ "				via || ' ' || numcivico || ',' || citt\u00E0 AS Indirizzo, telefonoc AS TelefonoCliente,\r\n"
				+ "			    totale AS Totale, inizioconsegna AS Stato\r\n"
				+ "				FROM ordine AS O NATURAL JOIN infoordine AS I\r\n"
				+ "				NATURAL JOIN cliente AS C\r\n"
				+ "				WHERE id_rider='"+idRider+"' AND fineconsegna IS NULL\r\n"
				+ "				ORDER BY id_ordine ASC");
		//inserire contenuto
		
		
		while(rs.next()) {
			
			int CodOrdine = rs.getInt(1);
			int CodRider = rs.getInt(2);
			int CodCliente = rs.getInt(3);
			String NomeCliente= rs.getString(4);
			String Indirizzo= rs.getString(5);
			String TelefonoCliente = rs.getString(6);
			String Totale= "\u20AC "+String.valueOf(rs.getFloat(7));		
			java.sql.Timestamp InizioConsegna = rs.getTimestamp(8);
			
			char Stato = 'A'; //Attesa default
			if(InizioConsegna != null) {
				Stato = 'S'; //Spedito se presente la data inizioConsegna
			}
				
			Object[] object = new Object[] {CodOrdine,CodRider,CodCliente,NomeCliente,Indirizzo,TelefonoCliente,Totale,Stato};
				
			ordini.add(object);
		}
		
		rs.close();
		st.close();
		conn.close();
		
		return ordini;
		
	}
	

	@Override
	public List<Object[]> ricercaComplessaOrdini(Integer idSede, List<Integer> idProdotti, String Veicolo, Integer Min, Integer Max) throws SQLException {
		
		List<Object[]> risultato = new ArrayList<>();
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement queryRicerca = null;

			//Creazione stringa sql per la query ricerca
			
			StringBuilder sql = new StringBuilder(1024);
			sql.append("SELECT DISTINCT O.ID_Sede, O.ID_Ordine, C.ID_Cliente,  C.NomeC || ' ' || C.CognomeC AS NomeCliente, IO.via || ' ' || IO.numcivico || ',' || IO.citt\u00E0 AS Indirizzo, "
					 + "R.ID_Rider, R.NomeR|| ' ' || R.CognomeR AS NomeRider,  O.Totale "
					 + "FROM Rider AS R NATURAL JOIN Ordine AS O NATURAL JOIN CompOrdine AS CO NATURAL JOIN InfoOrdine AS IO NATURAL JOIN Cliente AS C " );
			
			
			
			//Costruzione della clausola where in base a quali campi sono riempiti
			String ClausolaWhere = "";
			if( idSede != null ) {
				ClausolaWhere += " R.ID_Sede = ?";
			}
			if( Veicolo != null ) {
				if(ClausolaWhere.length()>0)
					ClausolaWhere += " AND ";
				else
					ClausolaWhere += "";
				ClausolaWhere += "  R.Veicolo = ?";
			}
			if( Min != null ) {
				if(ClausolaWhere.length()>0)
					ClausolaWhere += " AND ";
				else
					ClausolaWhere += "";
				ClausolaWhere += "  O.Totale >= ?";
			}
			if( Max != null) {
				if(ClausolaWhere.length()>0)
					ClausolaWhere += " AND ";
				else
					ClausolaWhere += "";
				ClausolaWhere += "  O.Totale <= ?";
			}
			
			if(idProdotti!= null) {
				if(ClausolaWhere.length()>0) 
					ClausolaWhere += " AND " ;
					
				else ClausolaWhere += ""; 
				
				ClausolaWhere += "( CO.Id_Prodotto = ? ";
				int indice=1;
				while(indice<idProdotti.size()) {
					ClausolaWhere += " OR ";
					ClausolaWhere += "  CO.Id_Prodotto = ?";
				indice++;
				}
				
				ClausolaWhere += " )";
			}
			
	

			if(ClausolaWhere.length()>0)
				sql.append( " WHERE " ).append( ClausolaWhere );
			
			
			//Creazione Prepared Statement

					connection = DBConnection.getInstance().getConnection();
					queryRicerca = connection.prepareStatement( sql.toString() );
			
					//Inserimenti valori nelle condizioni (se esisitono)
					int indice = 1;
		
					if( idSede != null ) {
						queryRicerca.setInt(indice++, idSede);  
					}
					if( Veicolo != null) {
						queryRicerca.setString( indice++, Veicolo);  
					}
					if( Min != null ) {
						queryRicerca.setInt( indice++, Min );  
					}
					if( Max != null ) {
						queryRicerca.setInt( indice++, Max );  
					}
				
					if(idProdotti!= null) {
						for(int s =0; s<idProdotti.size(); s++) {
							queryRicerca.setInt(indice++, idProdotti.get(s) ); 
						}
					}
			
			
					rs = queryRicerca.executeQuery();
					while(rs.next()) {
					risultato.add(new Object[] { rs.getInt(1),
							     				rs.getInt(2),
							     				rs.getInt(3),
							     				rs.getString(4),
							     				rs.getString(5),
							     				rs.getInt(6),
							     				rs.getString(7),
							     				"\u20ac " + rs.getFloat(8)} );
				
					}
			

					queryRicerca.getResultSet().close();
					queryRicerca.close();
					connection.close();
				

		return risultato;
	}


}
