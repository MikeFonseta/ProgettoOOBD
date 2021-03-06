package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Controller.ControllerAmministratore;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class CreaSedeFrame extends JFrame{
	
	private JButton btnCrea;
	private JPanel pnlmain;
	private Point initialClick;
	private JFrame parent=this;
	private JTextField txfNome;
	private JTextField txfTelefono;
	private JComboBox cbxProvincia;
	private JTextField txfVia;
	private JTextField txfNumCivico;
	private JPasswordField psfPassword;
	private ControllerAmministratore controllerAmministratore=null;
	private JComboBox cbxCitta;
	private DefaultComboBoxModel CittaModel = new DefaultComboBoxModel();
	private boolean NomeSedeInserito=false,TelefonoInserito=false,ProvinciaInserito=false,CittaInserito=false,ViaInserito=false,NumCivicoInserito=false,PasswordInserito=false;
	
	
	public CreaSedeFrame(ControllerAmministratore ControllerAmministratore,int idProssimaSede,String NomeUtenteGestore) {
		this.controllerAmministratore = ControllerAmministratore;
		setUndecorated(true);
		setBounds(0,0,440,660);

		pnlmain = new JPanel();
		pnlmain.setBackground(UIManager.getColor("Panel.background"));
		pnlmain.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(pnlmain);
		pnlmain.setLayout(null);
		
		JLabel lblidSedeTxt = new JLabel("ID Sede");
		lblidSedeTxt.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblidSedeTxt.setBounds(66, 80, 89, 41);
		getContentPane().add(lblidSedeTxt);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNome.setBounds(66, 128, 113, 41);
		
		getContentPane().add(lblNome);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTelefono.setBounds(66, 183, 113, 41);
		getContentPane().add(lblTelefono);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblProvincia.setBounds(66, 241, 113, 41);
		getContentPane().add(lblProvincia);
		
		JLabel lblVia = new JLabel("Via");
		lblVia.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblVia.setBounds(66, 347, 43, 41);
		getContentPane().add(lblVia);
		
		JLabel lblGestoreTxt = new JLabel("Gestore");
		lblGestoreTxt.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblGestoreTxt.setBounds(66, 458, 89, 41);
		getContentPane().add(lblGestoreTxt);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPassword.setBounds(66, 510, 101, 41);
		getContentPane().add(lblPassword);
		
		txfNome = new JTextField();
		txfNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfNome.setBounds(163, 131, 228, 32);
		txfNome.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(txfNome.getText().length() > 0) {
					NomeSedeInserito=true;
				}else {
					NomeSedeInserito=false;
				}	
				ControllaModifiche();	
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(txfNome.getText().length() > 0) {
					NomeSedeInserito=true;
				}else {
					NomeSedeInserito=false;
				}	
				ControllaModifiche();	
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				
		}});
		getContentPane().add(txfNome);
		
		txfTelefono = new JTextField();
		txfTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfTelefono.setBounds(163, 186, 174, 32);
		txfTelefono.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(txfTelefono.getText().length() > 0) {
					TelefonoInserito=true;
				}else {
					TelefonoInserito=false;
				}	
				ControllaModifiche();	
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(txfTelefono.getText().length() > 0) {
					TelefonoInserito=true;
				}else {
					TelefonoInserito=false;
				}	
				ControllaModifiche();	
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				
		}});
		getContentPane().add(txfTelefono);
		
	
		cbxProvincia = new JComboBox(controllerAmministratore.getProvince());
		cbxProvincia.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxProvincia.setBounds(163, 244, 53, 32);
		getContentPane().add(cbxProvincia);
		
		cbxCitta = new JComboBox(CittaModel);
		cbxCitta.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxCitta.setBounds(163, 296, 228, 32);
		getContentPane().add(cbxCitta);
		
		
		txfVia = new JTextField();
		txfVia.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfVia.setBounds(163, 350, 228, 32);
		txfVia.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(txfVia.getText().length() > 0) {
					ViaInserito=true;
				}else {
					ViaInserito=false;
				}
				ControllaModifiche();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(txfVia.getText().length() > 0) {
					ViaInserito=true;
				}else {
					ViaInserito=false;
				}
				ControllaModifiche();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				
		}});
		getContentPane().add(txfVia);
		
		txfNumCivico = new JTextField();
		txfNumCivico.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfNumCivico.setBounds(163, 404, 70, 32);
		txfNumCivico.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(txfNumCivico.getText().length() > 0) {
					NumCivicoInserito=true;
				}else {
					NumCivicoInserito=false;
				}
				ControllaModifiche();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(txfNumCivico.getText().length() > 0) {
					NumCivicoInserito=true;
				}else {
					NumCivicoInserito=false;
				}
				ControllaModifiche();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				
		}});
		getContentPane().add(txfNumCivico);
		
		psfPassword = new JPasswordField();
		psfPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		psfPassword.setBounds(163, 510, 174, 32);
		psfPassword.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(psfPassword.getText().length() > 0) {
					PasswordInserito=true;
				}else {
					PasswordInserito=false;
				}
				ControllaModifiche();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(psfPassword.getText().length() > 0) {
					PasswordInserito=true;
				}else {
					PasswordInserito=false;
				}
				ControllaModifiche();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				
		}});
		getContentPane().add(psfPassword);
		
		JLabel lblN = new JLabel("N.");
		lblN.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblN.setBounds(66, 406, 43, 41);
		getContentPane().add(lblN);
		
		JLabel lblGestore = new JLabel("" + NomeUtenteGestore);
		lblGestore.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblGestore.setBounds(163, 458, 89, 41);
		getContentPane().add(lblGestore);
		
		JLabel lblIdSede = new JLabel("" + idProssimaSede);
		lblIdSede.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdSede.setBounds(163, 80, 89, 41);
		getContentPane().add(lblIdSede);
		
		JButton btnAnnulla = new JButton("ANNULLA");
		btnAnnulla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiCreaSedeFrame();
				}
			}
		});
		btnAnnulla.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnAnnulla.setBounds(66, 576, 113, 41);
		getContentPane().add(btnAnnulla);
		
		
		cbxProvincia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if(cbxProvincia.getSelectedItem() != null && !cbxProvincia.getSelectedItem().toString().equals("")) {	
            			
            		CittaModel.removeAllElements();
            		CittaModel.addAll(controllerAmministratore.getComuniProvincia(cbxProvincia.getSelectedItem().toString()));
            		
            		ProvinciaInserito=true;
            	}else {
            		CittaModel.removeAllElements();
            		ProvinciaInserito=false;
            	}
            	
            	ControllaModifiche();
            }
		});
				
		cbxCitta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	          	
            	if(cbxCitta.getSelectedItem() != null && !cbxCitta.getSelectedItem().toString().equals("")) {
            		CittaInserito=true;
            	}else {
            		CittaInserito=false;
            	}
            	
            	ControllaModifiche();
            }
		});

		
		btnCrea = new JButton("CREA");
		btnCrea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1 && btnCrea.isEnabled()) {
					controllerAmministratore.CreaSede(idProssimaSede, txfNome.getText().replaceAll("'", "''"), txfTelefono.getText(), cbxProvincia.getSelectedItem().toString(), cbxCitta.getSelectedItem().toString().replaceAll("'", "''"), txfVia.getText().replaceAll("'", "''"), txfNumCivico.getText(), NomeUtenteGestore, psfPassword.getText());
				}
			}
		});
		btnCrea.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnCrea.setEnabled(false);
		btnCrea.setBounds(278, 576, 113, 41);
		getContentPane().add(btnCrea);
		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 436, 35);
		getContentPane().add(pnlBarra);
		pnlBarra.setLayout(null);
		
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
		
		
		JLabel lblTitolo = new JLabel("Crea Sede");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setBounds(0, 0, 127, 35);
		pnlBarra.add(lblTitolo);
		
		
		JLabel lblCitta = new JLabel("Città");
		lblCitta.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCitta.setBounds(66, 293, 89, 41);
		getContentPane().add(lblCitta);
		
		JRadioButton rdbtnVisualizzaPassword = new JRadioButton("");
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
		rdbtnVisualizzaPassword.setBounds(343, 510, 30, 32);
		getContentPane().add(rdbtnVisualizzaPassword);
	
		setLocationRelativeTo(null);
		setVisible(true);
		
	}


	public void ControllaModifiche() {
		if(NomeSedeInserito==true && TelefonoInserito==true && ProvinciaInserito==true && CittaInserito==true && ViaInserito==true && NumCivicoInserito==true && PasswordInserito==true) {
			btnCrea.setEnabled(true);
		}else {
			btnCrea.setEnabled(false);
		}
	}
}
