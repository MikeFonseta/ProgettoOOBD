package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerAmministratore;
import Entities.Account;

import javax.swing.JPasswordField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JComboBox;

public class GestioneSedeFrame extends JFrame{	
	
	private JPanel pnlmain;
	private JTable tblProdotti;
	private JTable tblRider;
	private ControllerAmministratore controllerAmministratore = null;
	private JButton btnSalva;
	private Point initialClick;
	private JFrame parent=this;
	private Account gestoreSede;
	private DefaultComboBoxModel CittaModel = new DefaultComboBoxModel();
	private boolean NomeSedeModificato=false,TelefonoModificato=false,ProvinciaModificato=false,CittaModificato=false,ViaModificato=false,NumCivicoModificato=false,PasswordModificato=false;
	
	public GestioneSedeFrame(ControllerAmministratore ControllerAmministratore,Account gestoreSede) {
		
		this.controllerAmministratore = ControllerAmministratore;
		setResizable(false);
		setBounds(0,0,1200,700);
		this.gestoreSede = gestoreSede;
		pnlmain = new JPanel();
		pnlmain.setBackground(UIManager.getColor("Panel.background"));
		pnlmain.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(pnlmain);
		pnlmain.setLayout(null);
		
		btnSalva = new JButton("AGGIORNA");
		btnSalva.setEnabled(false);
		btnSalva.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnSalva.setBounds(1056, 41, 118, 39);
		getContentPane().add(btnSalva);
		
		
		JLabel lblIdSede = new JLabel("ID Sede:" + gestoreSede.getSede().getIdSede());
		lblIdSede.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdSede.setBounds(10, 41, 160, 39);
		getContentPane().add(lblIdSede);
		
		JLabel lblNome = new JLabel("Nome ");
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNome.setBounds(180, 41, 49, 39);
		getContentPane().add(lblNome);
		
		JTextField txfNomeSede = new JTextField(gestoreSede.getSede().getNomeSede());
		txfNomeSede.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!txfNomeSede.getText().equals(gestoreSede.getSede().getNomeSede())) {
					NomeSedeModificato=true;
				}else {
					NomeSedeModificato=false;
				}	
				ControllaModifiche();	
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!txfNomeSede.getText().equals(gestoreSede.getSede().getNomeSede())) {
					NomeSedeModificato=true;
				}else {
					NomeSedeModificato=false;
				}	
				ControllaModifiche();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				
		}});
		txfNomeSede.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfNomeSede.setBounds(231, 46, 183, 28);
		getContentPane().add(txfNomeSede);
		txfNomeSede.setColumns(10);

		JComboBox cbxProvincia = new JComboBox(controllerAmministratore.getProvince());
		cbxProvincia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if(cbxProvincia.getSelectedItem() != null && !cbxProvincia.getSelectedItem().toString().equals("")) {	
        			
            		
            		CittaModel.removeAllElements();
            		CittaModel.addAll(controllerAmministratore.getComuniProvincia(cbxProvincia.getSelectedItem().toString()));
    
            		ProvinciaModificato=true;
            	}else {
            		CittaModel.removeAllElements();
            		ProvinciaModificato=false;
            	}
            	
            	ControllaModifiche();
            }
		});
		cbxProvincia.setSelectedItem(gestoreSede.getSede().getProvincia());
		cbxProvincia.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxProvincia.setBounds(503, 46, 49, 28);
		getContentPane().add(cbxProvincia);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblProvincia.setBounds(424, 41, 76, 39);
		getContentPane().add(lblProvincia);
		
		
		JComboBox cbxCitta = new JComboBox(CittaModel);
		CittaModel.addAll(controllerAmministratore.getComuniProvincia(cbxProvincia.getSelectedItem().toString()));
		cbxCitta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if(cbxCitta.getSelectedItem() != null && !cbxCitta.getSelectedItem().toString().equals(gestoreSede.getSede().getCitta()) && !cbxCitta.getSelectedItem().toString().equals("")) {
            		CittaModificato=true;
            	}else {
            		CittaModificato=false;
            	}
            	
            	ControllaModifiche();
            }
		});
		cbxCitta.setSelectedItem(gestoreSede.getSede().getCitta());
		cbxCitta.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxCitta.setBounds(604, 46, 146, 28);
		getContentPane().add(cbxCitta);
		
		JLabel lblCitta = new JLabel("Città");
		lblCitta.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCitta.setBounds(562, 41, 42, 39);
		getContentPane().add(lblCitta);
		
		JTextField txfVia = new JTextField(gestoreSede.getSede().getVia());
		txfVia.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!txfVia.getText().equals(gestoreSede.getSede().getVia()) && !txfVia.getText().isBlank()) {
					ViaModificato=true;
				}else {
					ViaModificato=false;
				}
				ControllaModifiche();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!txfVia.getText().equals(gestoreSede.getSede().getVia())  && !txfVia.getText().isBlank()){
					ViaModificato=true;
				}else {
					ViaModificato=false;
				}
				ControllaModifiche();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!txfVia.getText().equals(gestoreSede.getSede().getVia())  && !txfVia.getText().isBlank()){
					ViaModificato=true;
				}else {
					ViaModificato=false;
				}
				ControllaModifiche();
		}});
		txfVia.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfVia.setColumns(10);
		txfVia.setBounds(787, 46, 172, 28);
		getContentPane().add(txfVia);
		
		JLabel lblVia = new JLabel("Via");
		lblVia.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblVia.setBounds(760, 41, 49, 39);
		getContentPane().add(lblVia);
		
		JTextField txfNumCivico = new JTextField(gestoreSede.getSede().getNumCivico());
		txfNumCivico.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!txfNumCivico.getText().equals(gestoreSede.getSede().getNumCivico()) && !txfNumCivico.getText().isBlank()) {
					NumCivicoModificato=true;
				}else {
					NumCivicoModificato=false;
				}
				ControllaModifiche();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!txfNumCivico.getText().equals(gestoreSede.getSede().getNumCivico()) && !txfNumCivico.getText().isBlank()) {
					NumCivicoModificato=true;
				}else {
					NumCivicoModificato=false;
				}
				ControllaModifiche();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!txfNumCivico.getText().equals(gestoreSede.getSede().getNumCivico()) && !txfNumCivico.getText().isBlank()) {
					NumCivicoModificato=true;
				}else {
					NumCivicoModificato=false;
				}
				ControllaModifiche();
		}});
		txfNumCivico.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfNumCivico.setColumns(10);
		txfNumCivico.setBounds(987, 46, 59, 28);
		getContentPane().add(txfNumCivico);
		
		JLabel lblNumCivico = new JLabel("N.");
		lblNumCivico.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNumCivico.setBounds(969, 41, 18, 39);
		getContentPane().add(lblNumCivico);
		
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiGestioneSedeFrame();
				}
			}
		});
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnChiudi.setBounds(1056, 91, 118, 39);
		getContentPane().add(btnChiudi);
		
		JPasswordField psfPassword = new JPasswordField(gestoreSede.getPassword());
		psfPassword.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!psfPassword.getText().equals(gestoreSede.getPassword())) {
					PasswordModificato=true;
				}else {
					PasswordModificato=false;
				}
				ControllaModifiche();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!psfPassword.getText().equals(gestoreSede.getPassword())){
					PasswordModificato=true;
				}else {
					PasswordModificato=false;
				}
				ControllaModifiche();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				
		}});
		psfPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		psfPassword.setColumns(10);
		psfPassword.setBounds(343, 96, 164, 28);
		getContentPane().add(psfPassword);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPassword.setBounds(267, 91, 87, 39);
		getContentPane().add(lblPassword);
		
		JLabel lblNomeUtente = new JLabel("Gestore: " + gestoreSede.getNomeUtente());
		lblNomeUtente.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNomeUtente.setBounds(10, 91, 236, 39);
		getContentPane().add(lblNomeUtente);
		
		JRadioButton rdbtnVisualizzaPassword = new JRadioButton();
		rdbtnVisualizzaPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(rdbtnVisualizzaPassword.isSelected()) {
						psfPassword.setEchoChar((char)0);
					}else {
						psfPassword.setEchoChar((char)'●');
					}
				}
			}
		});
		rdbtnVisualizzaPassword.setBounds(513, 98, 109, 23);
		getContentPane().add(rdbtnVisualizzaPassword);
	
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTelefono.setBounds(686, 91, 76, 39);
		getContentPane().add(lblTelefono);

		JTextField txfTelefono = new JTextField(gestoreSede.getSede().getTelefonoSede());
		txfTelefono.setText(gestoreSede.getSede().getTelefonoSede());
		txfTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfTelefono.setColumns(10);
		txfTelefono.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!txfTelefono.getText().equals(gestoreSede.getSede().getTelefonoSede()) && !txfTelefono.getText().isBlank()) {
					TelefonoModificato=true;
				}else {
					TelefonoModificato=false;
				}
				ControllaModifiche();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!txfTelefono.getText().equals(gestoreSede.getSede().getTelefonoSede()) && !txfTelefono.getText().isBlank()) {
					TelefonoModificato=true;
				}else {
					TelefonoModificato=false;
				}
				ControllaModifiche();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!txfTelefono.getText().equals(gestoreSede.getSede().getTelefonoSede()) && !txfTelefono.getText().isBlank()) {
					TelefonoModificato=true;
				}else {
					TelefonoModificato=false;
				}
				ControllaModifiche();
		}});
		txfTelefono.setBounds(760, 96, 143, 28);
		getContentPane().add(txfTelefono);
		
		
		JScrollPane scpProdotti = new JScrollPane();
		scpProdotti.setBounds(10, 150, 578, 494);
		getContentPane().add(scpProdotti);
		
		tblProdotti = new JTable();
		tblProdotti.setFillsViewportHeight(true);
		tblProdotti.setRowHeight(30);
		tblProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		tblProdotti.getTableHeader().setReorderingAllowed(false);
		tblProdotti.setDragEnabled(false);
		tblProdotti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblProdotti.setModel(new DefaultTableModel(
			controllerAmministratore.getMenuSede(gestoreSede.getSede().getIdSede()),
			new String[] {
				"ID", "Nome", "Categoria", "Prezzo"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblProdotti.getColumnModel().getColumn(0).setResizable(false);
		tblProdotti.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblProdotti.getColumnModel().getColumn(0).setMinWidth(40);
		tblProdotti.getColumnModel().getColumn(0).setMaxWidth(40);
		tblProdotti.getColumnModel().getColumn(1).setResizable(false);
		tblProdotti.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblProdotti.getColumnModel().getColumn(1).setMinWidth(150);
		tblProdotti.getColumnModel().getColumn(2).setResizable(false);
		tblProdotti.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblProdotti.getColumnModel().getColumn(2).setMinWidth(100);
		tblProdotti.getColumnModel().getColumn(2).setMaxWidth(100);
		tblProdotti.getColumnModel().getColumn(3).setResizable(false);
		tblProdotti.getColumnModel().getColumn(3).setPreferredWidth(61);
		tblProdotti.getColumnModel().getColumn(3).setMinWidth(60);
		tblProdotti.getColumnModel().getColumn(3).setMaxWidth(60);
		scpProdotti.setViewportView(tblProdotti);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		tblProdotti.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tblProdotti.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		
		JScrollPane scpRider = new JScrollPane();
		scpRider.setBounds(604, 150, 570, 494);
		getContentPane().add(scpRider);
		
		tblRider = new JTable();
		tblRider.setFillsViewportHeight(true);
		tblRider.setRowHeight(30);
		tblRider.setFont(new Font("Calibri", Font.PLAIN, 14));
		tblRider.getTableHeader().setReorderingAllowed(false);
		tblRider.setModel(new DefaultTableModel(
			controllerAmministratore.getRiderDaSede(gestoreSede.getSede().getIdSede()),
			new String[] {
				"ID", "Nome", "Cognome", "Telefono", "Veicolo"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblRider.getColumnModel().getColumn(0).setResizable(false);
		tblRider.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblRider.getColumnModel().getColumn(0).setMinWidth(40);
		tblRider.getColumnModel().getColumn(0).setMaxWidth(40);
		tblRider.getColumnModel().getColumn(1).setResizable(false);
		tblRider.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblRider.getColumnModel().getColumn(1).setMinWidth(150);
		tblRider.getColumnModel().getColumn(1).setMaxWidth(150);
		tblRider.getColumnModel().getColumn(2).setResizable(false);
		tblRider.getColumnModel().getColumn(2).setPreferredWidth(150);
		tblRider.getColumnModel().getColumn(2).setMinWidth(150);
		tblRider.getColumnModel().getColumn(2).setMaxWidth(150);
		tblRider.getColumnModel().getColumn(3).setResizable(false);
		tblRider.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblRider.getColumnModel().getColumn(3).setMinWidth(100);
		tblRider.getColumnModel().getColumn(3).setMaxWidth(100);
		tblRider.getColumnModel().getColumn(4).setResizable(false);
		tblRider.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblRider.getColumnModel().getColumn(4).setMinWidth(150);
		tblRider.getColumnModel().getColumn(4).setMaxWidth(150);
		scpRider.setViewportView(tblRider);
		
		tblRider.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		
		JButton btnEliminaProdotto = new JButton("ELIMINA");
		btnEliminaProdotto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(tblProdotti.getSelectedRowCount() > 0) {
						controllerAmministratore.EliminaProdottoDaSede(gestoreSede.getSede().getIdSede(), (int)tblProdotti.getValueAt(tblProdotti.getSelectedRow(), 0));
					}else {
						Errore();
					}
				}
			}
		});
		btnEliminaProdotto.setBounds(483, 655, 105, 34);
		getContentPane().add(btnEliminaProdotto);
		
		JButton btnAggiungiProdotto = new JButton("AGGIUNGI");
		btnAggiungiProdotto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ApriAggiungiProdottoFrame(gestoreSede.getSede().getIdSede());
				}
			}
		});
		btnAggiungiProdotto.setBounds(376, 655, 105, 34);
		getContentPane().add(btnAggiungiProdotto);
		
		JButton btnEliminaRider = new JButton("ELIMINA");
		btnEliminaRider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(tblRider.getSelectedRowCount() > 0) {
						controllerAmministratore.EliminaRider(gestoreSede.getSede().getIdSede(), tblRider.getValueAt(tblRider.getSelectedRow(), 0).toString());
					}else {
						Errore();
					}
				}
			}
		});
		btnEliminaRider.setBounds(1069, 655, 105, 34);
		getContentPane().add(btnEliminaRider);
		
		JButton btnAggiungiRider = new JButton("AGGIUNGI");
		btnAggiungiRider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ApriNuovoRiderFrame(gestoreSede.getSede().getIdSede());
				}
			}
		});
		btnAggiungiRider.setBounds(962, 655, 105, 34);
		getContentPane().add(btnAggiungiRider);
		
		JButton btnModificaRider = new JButton("MODIFICA");
		btnModificaRider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(tblRider.getSelectedRowCount() > 0) {
						controllerAmministratore.ApriModificaRiderFrame(tblRider.getValueAt(tblRider.getSelectedRow(), 0).toString(), gestoreSede.getSede().getIdSede());
					}else {
						Errore();
					}
				}
			}
		});
		btnModificaRider.setBounds(854, 655, 105, 34);
		getContentPane().add(btnModificaRider);
		
		btnSalva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1 && btnSalva.isEnabled()) {
					controllerAmministratore.SalvaSede(btnSalva,txfNomeSede.getText().replaceAll("'", "''"),txfTelefono.getText(),cbxProvincia.getSelectedItem().toString(),cbxCitta.getSelectedItem().toString().replaceAll("'", "''"),txfVia.getText().replaceAll("'", "''"),txfNumCivico.getText(),gestoreSede,psfPassword.getText());
			}
			}
		});
		
		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setLayout(null);
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 1200, 35);
		getContentPane().add(pnlBarra);
		
		JLabel lblTitolo = new JLabel("Gestione Sede");
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTitolo.setBounds(10, 0, 209, 35);
		pnlBarra.add(lblTitolo);
	
		
		pnlBarra.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            initialClick = e.getPoint();
	            getComponentAt(initialClick);
	        }
	    });

	    pnlBarra.addMouseMotionListener(new MouseMotionAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {

	            // Posizione Finestra
	            int thisX = parent.getLocation().x;
	            int thisY = parent.getLocation().y;

	            // Determinazione Spostamento
	            int xMoved = e.getX() - initialClick.x;
	            int yMoved = e.getY() - initialClick.y;

	            // Spostamento finestra
	            int X = thisX + xMoved;
	            int Y = thisY + yMoved;
	            parent.setLocation(X, Y);
	        	
	        }
	    });
	    
	    
		
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
	}

	private void ControllaModifiche() {
			if(NomeSedeModificato==true || TelefonoModificato==true || (ProvinciaModificato==true && CittaModificato==true ) || ViaModificato==true || NumCivicoModificato==true || PasswordModificato==true) {
				btnSalva.setEnabled(true);
			}else {
				btnSalva.setEnabled(false);
			}
	}
	
	public void AggiornaProdotti() {
		tblProdotti.setModel(new DefaultTableModel(
				controllerAmministratore.getMenuSede(gestoreSede.getSede().getIdSede()),
				new String[] {
					"ID", "Nome", "Categoria", "Prezzo"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			tblProdotti.getColumnModel().getColumn(0).setResizable(false);
			tblProdotti.getColumnModel().getColumn(0).setPreferredWidth(40);
			tblProdotti.getColumnModel().getColumn(0).setMinWidth(40);
			tblProdotti.getColumnModel().getColumn(0).setMaxWidth(40);
			tblProdotti.getColumnModel().getColumn(1).setResizable(false);
			tblProdotti.getColumnModel().getColumn(1).setPreferredWidth(150);
			tblProdotti.getColumnModel().getColumn(1).setMinWidth(150);
			tblProdotti.getColumnModel().getColumn(2).setResizable(false);
			tblProdotti.getColumnModel().getColumn(2).setPreferredWidth(100);
			tblProdotti.getColumnModel().getColumn(2).setMinWidth(100);
			tblProdotti.getColumnModel().getColumn(2).setMaxWidth(100);
			tblProdotti.getColumnModel().getColumn(3).setResizable(false);
			tblProdotti.getColumnModel().getColumn(3).setPreferredWidth(61);
			tblProdotti.getColumnModel().getColumn(3).setMinWidth(60);
			tblProdotti.getColumnModel().getColumn(3).setMaxWidth(60);
			
			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
			tblProdotti.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
			tblProdotti.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
			
	}
	
	public void AggiornaRider() {
		tblRider.setModel(new DefaultTableModel(
				controllerAmministratore.getRiderDaSede(gestoreSede.getSede().getIdSede()),
				new String[] {
					"ID", "Nome", "Cognome", "Telefono", "Veicolo"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		});
		tblRider.getColumnModel().getColumn(0).setResizable(false);
		tblRider.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblRider.getColumnModel().getColumn(0).setMinWidth(40);
		tblRider.getColumnModel().getColumn(0).setMaxWidth(40);
		tblRider.getColumnModel().getColumn(1).setResizable(false);
		tblRider.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblRider.getColumnModel().getColumn(1).setMinWidth(150);
		tblRider.getColumnModel().getColumn(1).setMaxWidth(150);
		tblRider.getColumnModel().getColumn(2).setResizable(false);
		tblRider.getColumnModel().getColumn(2).setPreferredWidth(150);
		tblRider.getColumnModel().getColumn(2).setMinWidth(150);
		tblRider.getColumnModel().getColumn(2).setMaxWidth(150);
		tblRider.getColumnModel().getColumn(3).setResizable(false);
		tblRider.getColumnModel().getColumn(3).setPreferredWidth(100);
		tblRider.getColumnModel().getColumn(3).setMinWidth(100);
		tblRider.getColumnModel().getColumn(3).setMaxWidth(100);
		tblRider.getColumnModel().getColumn(4).setResizable(false);
		tblRider.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblRider.getColumnModel().getColumn(4).setMinWidth(150);
		tblRider.getColumnModel().getColumn(4).setMaxWidth(150);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tblRider.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		
	}
	
	private void Errore() {
		JOptionPane.showMessageDialog(this,"Nessuna riga selezionata","Errore",JOptionPane.ERROR_MESSAGE);
	}
}
