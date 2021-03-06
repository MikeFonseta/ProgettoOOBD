package DAO;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.DBConnection;

public class MenuDAOPostgresImp implements MenuDAO {

	@Override
	public int aggiungiProdottoASede(int idSede, int idProdotto) throws SQLException {
		int risultato = 0;
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		risultato = st.executeUpdate("INSERT INTO menù VALUES ("+idSede + "," + idProdotto +")");
				
		st.close();
		conn.close();
			
		return risultato;
	}

	@Override
	public int EliminaProdottoDaSede(int idSede, int idProdotto)  throws SQLException {

		int risultato = 0;
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		risultato = st.executeUpdate("DELETE FROM menù WHERE id_sede="+ idSede + " AND id_prodotto=" + idProdotto +"");
				
		st.close();
		conn.close();
					
		return risultato;
	}

	@Override
	public List<Object[]> getProdottiPerUnaSede(int idSede)  throws SQLException {
		
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT P.id_prodotto,P.nomep,P.descrizione,P.prezzo,P.categoria FROM prodotto AS P "
					+ "WHERE P.id_prodotto NOT IN (SELECT id_prodotto FROM menù WHERE id_sede=" + idSede + ") ORDER BY P.id_prodotto ASC");
		
		while(rs.next()) {
				
			int idProdotto = rs.getInt(1);
			String nomeProdotto= rs.getString(2);
			String descrizione = rs.getString(3);
			String prezzo = rs.getString(4);
			
			Object[] object = new Object[] {idProdotto,nomeProdotto,descrizione,"€"+prezzo};
				
			prodotti.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
		
		return prodotti;
	}

	@Override
	public List<Object[]> getProdottiDellaSede(int idSede)  throws SQLException {
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT P.id_prodotto,P.nomep,P.prezzo,P.categoria FROM prodotto AS P "
					+ "WHERE P.id_prodotto IN (SELECT id_prodotto FROM menù WHERE id_sede=" + idSede + ") ORDER BY P.id_prodotto ASC");
			
		while(rs.next()) {
				
			int idProdotto = rs.getInt(1);
			String nomeProdotto= rs.getString(2);
			String prezzo = rs.getString(3);
			String categoria = rs.getString(4);
				
			Object[] object = new Object[] {idProdotto,nomeProdotto,categoria,"€" + prezzo};
				
			prodotti.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
			
		
		return prodotti;
	}
	
	@Override
	public List<Object[]> getProdottiSedeCategoria(int idSede, String categoria)  throws SQLException {
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = null;
		
		
		
		StringBuilder sql = new  StringBuilder(1024); 
		sql.append("SELECT P.id_prodotto,P.nomep,P.descrizione,P.prezzo,P.categoria FROM prodotto AS P "
				+ "WHERE P.id_prodotto NOT IN (SELECT id_prodotto FROM menù WHERE id_sede=" + idSede + ")");	
	
		
		if(categoria!=null) {
			sql.append("AND P.categoria='" + categoria + "'");
		}
		
		sql.append("ORDER BY P.id_prodotto ASC");
		
		rs= st.executeQuery(sql.toString());
		while(rs.next()) {
				
			int idProdotto = rs.getInt(1);
			String nomeProdotto= rs.getString(2);
			String descrizione = rs.getString(3);
			String prezzo = rs.getString(4);
				
			Object[] object = new Object[] {idProdotto,nomeProdotto,descrizione,"€" + prezzo};
				
			prodotti.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
			
		return prodotti;
	}

}
