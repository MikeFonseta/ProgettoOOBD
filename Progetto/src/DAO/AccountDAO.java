package DAO;

import java.sql.SQLException;
import Entities.Account;

public interface AccountDAO{ 
	
	public Account ControlloCredenziali(String username, String Password) throws SQLException ;
	public Account CercaAccountPerIdSede(int idSede) throws SQLException ;
	public String NomeUtentePerNuovaSede(int idSede) throws SQLException ;
	public String[] getDatiCliente(int idCliente) throws SQLException;
	public int getClienteID() throws SQLException;
	public void CreaCliente(int idCliente,String nome,String cognome) throws SQLException;
	public int getClienteOrdine(int idOrdine) throws SQLException;
	public boolean VerificaCliente(int idCliente) throws SQLException;
	public int ModificaPassword(Account account,String NuovaPassword)throws SQLException;
	
}