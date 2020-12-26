package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import Controller.ControllerAmministratore;
import Entities.Rider;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

public class GestioneRiderFrame extends JFrame{
	
	private ControllerAmministratore controllerAmministratore = null;

	public GestioneRiderFrame(ControllerAmministratore controllerAmministratore, String idSede) {
		this.controllerAmministratore = controllerAmministratore;
		setUndecorated(true);
		setResizable(false);
		setSize(new Dimension(430, 496));
		setPreferredSize(new Dimension(430, 496));
		setMinimumSize(new Dimension(430, 496));
		getContentPane().setLayout(null);
			
		JLabel lblIdRiderTxt = new JLabel("ID RIder ");
		lblIdRiderTxt.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdRiderTxt.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdRiderTxt.setBounds(27, 21, 116, 39);
		getContentPane().add(lblIdRiderTxt);
		
		JLabel lblIdRider = new JLabel("" + controllerAmministratore.getIdProssimoRider());
		lblIdRider.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdRider.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdRider.setBounds(178, 21, 116, 39);
		getContentPane().add(lblIdRider);
		
		JButton btnSalva = new JButton("AGGIUNGI");
		btnSalva.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnSalva.setBounds(228, 378, 130, 44);
		getContentPane().add(btnSalva);
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.BUTTON1 == MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiGestioneRiderFrame();
				}
			}
		});
		btnChiudi.setBounds(67, 378, 130, 44);
		getContentPane().add(btnChiudi);
		
		JTextField txfNome = new JTextField();
		txfNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfNome.setBounds(178, 89, 210, 30);
		getContentPane().add(txfNome);
		txfNome.setColumns(10);
		
		JTextField txfCognome = new JTextField();
		txfCognome.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfCognome.setColumns(10);
		txfCognome.setBounds(178, 145, 210, 30);
		getContentPane().add(txfCognome);
		
		JTextField txfTelefono = new JTextField();
		txfTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfTelefono.setColumns(10);
		txfTelefono.setBounds(178, 208, 210, 30);
		getContentPane().add(txfTelefono);
		
		JComboBox cbxVeicolo = new JComboBox();
		cbxVeicolo.setModel(new DefaultComboBoxModel(new String[] {"Nessun veicolo", "Auto", "Scooter", "Scooter elettrico"}));
		cbxVeicolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxVeicolo.setBounds(178, 269, 210, 30);
		getContentPane().add(cbxVeicolo);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNome.setBounds(27, 89, 130, 30);
		getContentPane().add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCognome.setBounds(27, 145, 130, 30);
		getContentPane().add(lblCognome);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTelefono.setBounds(27, 208, 130, 30);
		getContentPane().add(lblTelefono);
		
		JLabel lblVeicolo = new JLabel("Veicolo");
		lblVeicolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblVeicolo.setBounds(27, 269, 130, 30);
		getContentPane().add(lblVeicolo);
		
		btnSalva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.BUTTON1 == MouseEvent.BUTTON1) {
					controllerAmministratore.CreaRider(Integer.parseInt(lblIdRider.getText()),txfNome.getText(),txfCognome.getText(),txfTelefono.getText(),cbxVeicolo.getSelectedItem().toString(),idSede);
				}
			}
		});
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public GestioneRiderFrame(ControllerAmministratore controllerAmministratore, Rider rider) {
		this.controllerAmministratore = controllerAmministratore;
		setUndecorated(true);
		setResizable(false);
		setSize(new Dimension(430, 496));
		setPreferredSize(new Dimension(430, 496));
		setMinimumSize(new Dimension(430, 496));
		getContentPane().setLayout(null);
		
		JLabel lblIdRiderTxt = new JLabel("ID RIder");
		lblIdRiderTxt.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdRiderTxt.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdRiderTxt.setBounds(27, 27, 96, 39);
		getContentPane().add(lblIdRiderTxt);
		
		JButton btnSalva = new JButton("SALVA");
		btnSalva.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSalva.setBounds(228, 378, 130, 44);
		getContentPane().add(btnSalva);
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.BUTTON1 == MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiGestioneRiderFrame();
				}
			}
		});
		btnChiudi.setBounds(67, 378, 130, 44);
		getContentPane().add(btnChiudi);
		
		JTextField txfNome = new JTextField(rider.getNomeRider());
		txfNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfNome.setBounds(178, 89, 210, 30);
		getContentPane().add(txfNome);
		txfNome.setColumns(10);
		
		JTextField txfCognome = new JTextField(rider.getCognomeRider());
		txfCognome.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfCognome.setColumns(10);
		txfCognome.setBounds(178, 145, 210, 30);
		getContentPane().add(txfCognome);
		
		JTextField txfTelefono = new JTextField(rider.getTelefonoRider());
		txfTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfTelefono.setColumns(10);
		txfTelefono.setBounds(178, 208, 210, 30);
		getContentPane().add(txfTelefono);
		
		JComboBox cbxVeicolo = new JComboBox();
		cbxVeicolo.setModel(new DefaultComboBoxModel(new String[] {"Nessun veicolo", "Auto", "Scooter", "Scooter elettrico"}));
		cbxVeicolo.setSelectedItem("" + rider.getVeicoloRider() + "");
		cbxVeicolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxVeicolo.setBounds(178, 269, 210, 30);
		getContentPane().add(cbxVeicolo);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNome.setBounds(27, 89, 130, 30);
		getContentPane().add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCognome.setBounds(27, 145, 130, 30);
		getContentPane().add(lblCognome);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTelefono.setBounds(27, 208, 130, 30);
		getContentPane().add(lblTelefono);
		
		JLabel lblVeicolo = new JLabel("Veicolo");
		lblVeicolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblVeicolo.setBounds(27, 269, 130, 30);
		getContentPane().add(lblVeicolo);
		
		JLabel lblIdRider = new JLabel("" + rider.getIdRider());
		lblIdRider.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdRider.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdRider.setBounds(178, 27, 96, 39);
		getContentPane().add(lblIdRider);
		setLocationRelativeTo(null);
		setVisible(true);
	}


}
