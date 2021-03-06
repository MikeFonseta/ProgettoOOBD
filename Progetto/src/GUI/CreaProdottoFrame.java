 package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.PlainDocument;

import Controller.ControllerAmministratore;
import Utility.FiltroDecimali;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.UIManager;

public class CreaProdottoFrame extends JFrame {
	private Point initialClick;
	private JFrame parent = this;
	private JTextField txfNome;
	private JTextField txfPrezzo;
	private ControllerAmministratore controllerAmministratore= null;
	private boolean PrezzoInserito, NomeInserito, CategoriaInserita;
	private JButton btnCrea;
	private JButton btnAnnulla;
	private JComboBox cbxCategorie;


	public CreaProdottoFrame(ControllerAmministratore ControllerAmministratore, int idProssimoProdotto) {
		this.controllerAmministratore = ControllerAmministratore;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 608);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setLayout(null);
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 450, 41);
		getContentPane().add(pnlBarra);
		
		JLabel lblCreaProdotto = new JLabel("Crea Prodotto");
		lblCreaProdotto.setForeground(Color.WHITE);
		lblCreaProdotto.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCreaProdotto.setBounds(10, 0, 209, 35);
		pnlBarra.add(lblCreaProdotto);
		
		txfNome = new JTextField();
		txfNome.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(txfNome.getText().isBlank())
					NomeInserito = false;
				else 
					NomeInserito = true;
				ControllaModifiche();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(txfNome.getText().isBlank())
					NomeInserito = false;
				else 
					NomeInserito = true;
				ControllaModifiche();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(txfNome.getText().isBlank())
					NomeInserito = false;
				else 
					NomeInserito = true;
				ControllaModifiche();
			}
		});
		txfNome.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txfNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfNome.setColumns(10);
		txfNome.setBounds(131, 145, 288, 29);
		contentPane.add(txfNome);
		
		JTextArea txpDescrizione = new JTextArea();
		txpDescrizione.setLocation(new Point(145, 141));
		txpDescrizione.setLineWrap(true);
		txpDescrizione.setFont(new Font("Calibri", Font.PLAIN, 18));
		txpDescrizione.setBounds(131, 277, 288, 169);
		contentPane.add(txpDescrizione);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDescrizione.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDescrizione.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblDescrizione.setBounds(0, 276, 99, 26);
		contentPane.add(lblDescrizione);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNome.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNome.setBounds(39, 146, 62, 26);
		contentPane.add(lblNome);
		
		JLabel lblIDProdotto = new JLabel("ID Prodotto : "+idProssimoProdotto+"");
		lblIDProdotto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblIDProdotto.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIDProdotto.setBounds(10, 89, 228, 23);
		contentPane.add(lblIDProdotto);
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrezzo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrezzo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPrezzo.setBounds(39, 480, 62, 24);
		contentPane.add(lblPrezzo);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCategoria.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCategoria.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCategoria.setBounds(24, 207, 77, 29);
		contentPane.add(lblCategoria);
		
		JLabel lblEuro = new JLabel("€");
		lblEuro.setVerticalAlignment(SwingConstants.BOTTOM);
		lblEuro.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblEuro.setBounds(218, 481, 46, 23);
		contentPane.add(lblEuro);
		
		txfPrezzo = new JTextField();
	      PlainDocument docMin = (PlainDocument) txfPrezzo.getDocument();
	      docMin.setDocumentFilter(new FiltroDecimali());
		txfPrezzo.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!txfPrezzo.getText().isBlank() && Float.parseFloat(txfPrezzo.getText().toString()) > 0) {
					
						PrezzoInserito = true;
				}
				else 
					PrezzoInserito = false;
					
				ControllaModifiche();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!txfPrezzo.getText().isBlank() && Float.parseFloat(txfPrezzo.getText().toString()) > 0) {
					
						PrezzoInserito = true;
				}
				else 
					PrezzoInserito = false;
					
				ControllaModifiche();
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!txfPrezzo.getText().isBlank() && Float.parseFloat(txfPrezzo.getText().toString()) > 0) {
					
						PrezzoInserito = true;
				}
				else 
					PrezzoInserito = false;
					
				ControllaModifiche();
			}		
		});
		txfPrezzo.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfPrezzo.setColumns(10);
		txfPrezzo.setBounds(131, 475, 77, 29);
		contentPane.add(txfPrezzo);
		
		cbxCategorie = new JComboBox();
		cbxCategorie.setModel(new DefaultComboBoxModel(controllerAmministratore.getCategorie()));
		cbxCategorie.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxCategorie.setBounds(131, 207, 288, 29);
		contentPane.add(cbxCategorie);
		cbxCategorie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbxCategorie.getSelectedItem()!= null && cbxCategorie.getSelectedItem().toString() != "" )
					CategoriaInserita = true;
				else 
					CategoriaInserita = false;
				ControllaModifiche();
			} 
		});
		
		
		btnAnnulla = new JButton("Annulla");
		btnAnnulla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiCreaProdottoFrame();
				}
			}
		});
		btnAnnulla.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnAnnulla.setBounds(91, 556, 99, 41);
		contentPane.add(btnAnnulla);
		
		btnCrea = new JButton("Crea");
		btnCrea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1 && btnCrea.isEnabled()) {
					controllerAmministratore.CreaProdotto(idProssimoProdotto, txfNome.getText().replaceAll("'", "''"), txpDescrizione.getText().replaceAll("'", "''"), Float.parseFloat(txfPrezzo.getText().toString()), cbxCategorie.getSelectedItem().toString().replaceAll("'", "''"));
				}
			} 
		});
		btnCrea.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnCrea.setBounds(253, 556, 105, 41);
		contentPane.add(btnCrea);
		btnCrea.setEnabled(false);
		

		
		
		
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
		this.setVisible(true);
	}
	
	public void ControllaModifiche() {
		if(NomeInserito == true && CategoriaInserita == true && PrezzoInserito == true )
			btnCrea.setEnabled(true);
		else
			btnCrea.setEnabled(false);
		}

}
