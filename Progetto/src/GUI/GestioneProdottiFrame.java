package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.PlainDocument;

import Controller.ControllerAmministratore;
import Controller.ControllerGestore;
import Utility.FiltroInteri;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

public class GestioneProdottiFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txfAllergeni;
	private JTextField txfMin;
	private JTextField txfMax;
	public JTable table;
	private ControllerAmministratore controllerAmministratore;
	private Point initialClick;
	private JFrame parent = this;
	private JComboBox cbxCategorie;
	private JButton  btnEliminaCategoria;
	private ControllerGestore controllerGestore;
	
	public GestioneProdottiFrame(ControllerAmministratore controllerAmministratore) {
		setResizable(false);
		this.controllerAmministratore = controllerAmministratore;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txfAllergeni = new JTextField();
		txfAllergeni.setBounds(586, 103, 396, 32);
		txfAllergeni.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfAllergeni.setColumns(10);
		contentPane.add(txfAllergeni);
		
		
		txfMin = new JTextField();
		 PlainDocument docMin = (PlainDocument) txfMin.getDocument();
	      docMin.setDocumentFilter(new FiltroInteri());
	      docMin.addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				controllaValoriMin();
				
			}
			
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				controllaValoriMin();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				controllaValoriMin();
			}
			
				
				
		});
		txfMin.setBounds(83, 103, 67, 32);
		txfMin.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfMin.setColumns(10);
		contentPane.add(txfMin);
		
		
		txfMax = new JTextField();
		PlainDocument docMax = (PlainDocument) txfMax.getDocument();
		docMax.setDocumentFilter(new FiltroInteri());	
		docMax.addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				controllaValoriMax();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				controllaValoriMax();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				controllaValoriMax();
				
			}
		});
		txfMax.setBounds(261, 103, 67, 32);
		txfMax.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfMax.setColumns(10);
		contentPane.add(txfMax);
		
		
		JLabel lblCategorie = new JLabel("Categoria");
		lblCategorie.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblCategorie.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCategorie.setBounds(10, 46, 67, 32);
		contentPane.add(lblCategorie);
		
		JLabel lblProdotti = new JLabel("Allergeni presenti");
		lblProdotti.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProdotti.setBounds(461, 109, 115, 26);
		lblProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblProdotti);
		
		
		JLabel lblMax = new JLabel("Max");
		lblMax.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMax.setBounds(218, 109, 33, 26);
		lblMax.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblMax);
	
		
		JLabel lblMin = new JLabel("Min");
		lblMin.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMin.setBounds(37, 109, 36, 26);
		lblMin.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblMin);
				
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					AggiornaTabella();
				}
			}
		});
		btnCerca.setBounds(1053, 102, 88, 35);
		btnCerca.setFont(new Font("Calibri", Font.PLAIN, 16));
		contentPane.add(btnCerca);
		
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiGestioneProdottiFrame();
				}
			}
		});
		btnChiudi.setBounds(946, 644, 195, 45);
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 16));
		contentPane.add(btnChiudi);
		
		
		JButton btnElimina = new JButton("ELIMINA");
		btnElimina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRowCount() == 1) {
					int riga = table.getSelectedRow();
						controllerAmministratore.ApriEliminaProdottoFrame((String) table.getValueAt(riga, 1), (int) table.getValueAt(riga, 0));
				}
				else if(table.getSelectedRowCount()>1){
					JOptionPane.showMessageDialog(parent, new String("Selezionare un solo prodotto!"),"Error",JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(parent, new String("Nessuna riga selezionata!"),"Error",JOptionPane.ERROR_MESSAGE);	
			}
		});
		btnElimina.setBounds(741, 644, 195, 45);
		btnElimina.setFont(new Font("Calibri", Font.PLAIN, 16));
		contentPane.add(btnElimina);
		
		
		JButton btnModifica = new JButton("MODIFICA");
		btnModifica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(table.getSelectedRowCount() == 1) {
							int idProdotto = (int) table.getValueAt(table.getSelectedRow(), 0);
							controllerAmministratore.ApriModificaProdotto(idProdotto);
					}
					else if(table.getSelectedRowCount()>1){
						JOptionPane.showMessageDialog(parent, new String("Selezionare un solo prodotto!"),"Error",JOptionPane.ERROR_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(parent, new String("Nessuna riga selezionata!"),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModifica.setBounds(536, 644, 195, 45);
		btnModifica.setFont(new Font("Calibri", Font.PLAIN, 16));
		contentPane.add(btnModifica);
		
		
		JButton btnAggiungi = new JButton("AGGIUNGI");
		btnAggiungi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() ==MouseEvent.BUTTON1) {
					controllerAmministratore.ApriCreaProdottoFrame();
				}
			}
		});
		btnAggiungi.setBounds(331, 644, 195, 45);
		btnAggiungi.setFont(new Font("Calibri", Font.PLAIN, 16));
		contentPane.add(btnAggiungi);
		
		btnEliminaCategoria = new JButton("Elimina");
		btnEliminaCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1 && btnEliminaCategoria.isEnabled()) {
					controllerAmministratore.EliminaCategoria(cbxCategorie.getSelectedItem().toString());
				}
			}
		});
		btnEliminaCategoria.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnEliminaCategoria.setBounds(386, 46, 98, 32);
		contentPane.add(btnEliminaCategoria);
		
		
		cbxCategorie = new JComboBox();
		cbxCategorie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	
                if(cbxCategorie.getSelectedItem().toString().equals("Nessuna") || cbxCategorie.getSelectedItem().equals("Tutte le categorie")) {
                	btnEliminaCategoria.setEnabled(false);
                }else {
                	btnEliminaCategoria.setEnabled(true);
                }
            }
        });
		cbxCategorie.setFont(new Font("Calibri", Font.PLAIN, 14));
//		DefaultComboBoxModel Modello  = new DefaultComboBoxModel();		
//		Modello.addElement("Tutte le categorie");
//		Modello.addAll(Arrays.asList(controllerAmministratore.getCategorie()));
		cbxCategorie.setModel(new DefaultComboBoxModel(this.controllerAmministratore.getCategorie()));
		cbxCategorie.setSelectedItem("Tutte");
		cbxCategorie.setMaximumRowCount(20);
		cbxCategorie.setBounds(82, 46, 206, 32);
		contentPane.add(cbxCategorie);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Calibri", Font.PLAIN, 14));
		scrollPane.setBounds(37, 172, 1103, 440);

		

		table = new JTable();
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			controllerAmministratore.ApriRicercaProdotto(getCategoriaSelezionata(), getMinSelezionato(), getMaxSelezionato(), getIdSede(), getIdProdottiPerAllergeni()),
			new String[] {
				"CodiceP", "NomeProdotto", "Categoria", "Descrizione", "Allergeni", "Prezzo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(133);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(252);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(253);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(35);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.getTableHeader().setReorderingAllowed(false);
		table.setFillsViewportHeight(true);
		
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(null);
		contentPane.add(scrollPane);
		
		

		JPanel pnlBarra = new JPanel();
		pnlBarra.setLayout(null);
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 1200, 35);
		getContentPane().add(pnlBarra);
		
		JLabel lblGestioneProdotti = new JLabel("Gestione Prodotti");
		lblGestioneProdotti.setForeground(Color.WHITE);
		lblGestioneProdotti.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblGestioneProdotti.setBounds(10, 0, 209, 35);
		pnlBarra.add(lblGestioneProdotti);
		
		JButton btnNuovaCategoria = new JButton("Nuova");
		btnNuovaCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ApriCreaCategoriaFrame();
				}
			}
		});
		btnNuovaCategoria.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnNuovaCategoria.setBounds(298, 46, 78, 32);
		contentPane.add(btnNuovaCategoria);
		
		JButton btnAggiungiACategoria = new JButton("AGGIUNGI A CATEGORIA");
		btnAggiungiACategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {				
				if(table.getSelectedRowCount() > 0) {	
					System.out.println("ciao");
					int[] Prodotti = new int[table.getSelectedRowCount()];
					int righe[] = table.getSelectedRows();
						for(int i = 0; i<table.getSelectedRowCount(); i++)
							Prodotti[i] = (int) table.getValueAt(righe[i], 0);
							controllerAmministratore.ApriAggiungiProdottiACategoriaFrame(Prodotti);
				}
			}
		}
		});
		btnAggiungiACategoria.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAggiungiACategoria.setBounds(494, 46, 186, 32);
		contentPane.add(btnAggiungiACategoria);
		
		
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
	
	
	
	/**
	 * @wbp.parser.constructor
	 */
	public GestioneProdottiFrame(ControllerGestore ControllerGestore) {
		setResizable(false);
		this.controllerGestore = ControllerGestore;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txfAllergeni = new JTextField();
		txfAllergeni.setBounds(586, 103, 396, 32);
		txfAllergeni.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfAllergeni.setColumns(10);
		contentPane.add(txfAllergeni);
		
		
		txfMin = new JTextField();
		 PlainDocument docMin = (PlainDocument) txfMin.getDocument();
	      docMin.setDocumentFilter(new FiltroInteri());
	      docMin.addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				controllaValoriMin();
				
			}
			
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				controllaValoriMin();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				controllaValoriMin();
			}
			
				
				
		});
		txfMin.setBounds(83, 103, 67, 32);
		txfMin.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfMin.setColumns(10);
		contentPane.add(txfMin);
		
		
		txfMax = new JTextField();
		PlainDocument docMax = (PlainDocument) txfMax.getDocument();
		docMax.setDocumentFilter(new FiltroInteri());	
		docMax.addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				controllaValoriMax();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				controllaValoriMax();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				controllaValoriMax();
				
			}
		});
		txfMax.setBounds(261, 103, 67, 32);
		txfMax.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfMax.setColumns(10);
		contentPane.add(txfMax);
		
		
		JLabel lblCategorie = new JLabel("Categoria");
		lblCategorie.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblCategorie.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCategorie.setBounds(10, 46, 67, 32);
		contentPane.add(lblCategorie);
		
		JLabel lblProdotti = new JLabel("Allergeni presenti");
		lblProdotti.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProdotti.setBounds(461, 109, 115, 26);
		lblProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblProdotti);
		
		
		JLabel lblMax = new JLabel("Max");
		lblMax.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMax.setBounds(218, 109, 33, 26);
		lblMax.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblMax);
	
		
		JLabel lblMin = new JLabel("Min");
		lblMin.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMin.setBounds(37, 109, 36, 26);
		lblMin.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblMin);
				
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					AggiornaTabella();
				}
			}
		});
		btnCerca.setBounds(1053, 102, 88, 35);
		btnCerca.setFont(new Font("Calibri", Font.PLAIN, 16));
		contentPane.add(btnCerca);
		
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					controllerGestore.ChiudiVisualizzaProdottiFrame();
				}
			}
		});
		btnChiudi.setBounds(946, 644, 195, 45);
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 16));
		contentPane.add(btnChiudi);
		
		
		
		
		cbxCategorie = new JComboBox();
		cbxCategorie.setFont(new Font("Calibri", Font.PLAIN, 14));
//		DefaultComboBoxModel Modello  = new DefaultComboBoxModel();		
//		Modello.addElement("Tutte le categorie");
//		Modello.addAll(Arrays.asList(controllerGestore.getCategorieBox()));
		cbxCategorie.setModel(new DefaultComboBoxModel(controllerGestore.getCategorieBox()));
		cbxCategorie.setSelectedItem("Tutte");
		cbxCategorie.setMaximumRowCount(20);
		cbxCategorie.setBounds(82, 46, 206, 32); 
		contentPane.add(cbxCategorie);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Calibri", Font.PLAIN, 14));
		scrollPane.setBounds(37, 172, 1103, 440);

		

		table = new JTable();
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
				controllerGestore.ApriRicercaProdotto(getCategoriaSelezionata(), getMinSelezionato(), getMaxSelezionato(), getIdSede(), getIdProdottiPerAllergeni()),
			new String[] {
				"CodiceP", "NomeProdotto", "Categoria", "Descrizione", "Allergeni", "Prezzo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(133);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(252);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(253);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(35);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.getTableHeader().setReorderingAllowed(false);
		table.setFillsViewportHeight(true);
		
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(null);
		contentPane.add(scrollPane);
		

		JPanel pnlBarra = new JPanel();
		pnlBarra.setLayout(null);
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 1200, 35);
		getContentPane().add(pnlBarra);
		
		JLabel lblGestioneProdotti = new JLabel("Visualizza Prodotti");
		lblGestioneProdotti.setForeground(Color.WHITE);
		lblGestioneProdotti.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblGestioneProdotti.setBounds(10, 0, 209, 35);
		pnlBarra.add(lblGestioneProdotti);
		
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
	
	
	public void AggiornaTabella() {
		Object[][] dati = null;
		if(this.controllerGestore != null) {
			dati = controllerGestore.ApriRicercaProdotto(getCategoriaSelezionata(), getMinSelezionato(), getMaxSelezionato(), getIdSede(), getIdProdottiPerAllergeni());
		}
		else if(this.controllerAmministratore != null) {
			dati = controllerAmministratore.ApriRicercaProdotto(getCategoriaSelezionata(), getMinSelezionato(), getMaxSelezionato(), getIdSede(), getIdProdottiPerAllergeni());
		}
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
				dati,
				new String[] {
				"CodiceP", "NomeProdotto", "Categoria", "Descrizione", "Allergeni", "Prezzo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(133);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(252);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(253);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(35);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);		
		table.setFillsViewportHeight(true);
	}
			

	public void controllaValoriMin() {
		if(!txfMin.getText().isBlank() && !txfMax.getText().isBlank()) {
			try {
				if(Integer.valueOf(this.txfMin.getText()) > Integer.valueOf(this.txfMax.getText()))
					txfMax.setText(txfMin.getText());
			}catch(NumberFormatException w) {
				txfMax.setText(txfMin.getText());
			}
		}
	}		


	public void controllaValoriMax() {
		if(!txfMin.getText().isBlank() && !txfMax.getText().isBlank()) {
			try {
				if(Integer.valueOf(this.txfMax.getText()) < Integer.valueOf(this.txfMin.getText())) 
					txfMin.setText(txfMax.getText());
			}catch(NumberFormatException w) {
				txfMin.setText(txfMax.getText());
			}
		}	
	}
	
	
	public String getCategoriaSelezionata() {
		if(this.cbxCategorie.getSelectedItem().toString()=="Tutte")
			return null;
		else 
			return this.cbxCategorie.getSelectedItem().toString();
	
	}
		
		
	public Integer getMinSelezionato() {
		if(this.txfMin.getText().isBlank())
			return null;
		else { 
			Integer min = Integer.valueOf(this.txfMin.getText());
			return min;
		}
	}
	
	
	public Integer getMaxSelezionato() {
		if(this.txfMax.getText().isBlank())
			return null;
		else { 
			Integer max = Integer.valueOf(this.txfMax.getText());
			return max;  
		}
	}
	
	
	public Integer getIdSede() {
		if(this.controllerGestore != null) {
			return Integer.valueOf(this.controllerGestore.getAccount().getSede().getIdSede());
		}
		else {
			return null;
		}
	}
	
	
	public List<Integer> getIdProdottiPerAllergeni() {
		if(this.controllerAmministratore != null) 
			return controllerAmministratore.ApriGetIdProdottiPerAllergeni(txfAllergeni.getText().toString());
		else 
			return controllerGestore.ApriGetIdProdottiPerAllergeni(txfAllergeni.getText().toString());
			
	}


	
	public void AggiornaCategorie() {
		if(this.controllerAmministratore != null)
			cbxCategorie.setModel(new DefaultComboBoxModel(controllerAmministratore.getCategorie()));
		else
			cbxCategorie.setModel(new DefaultComboBoxModel(controllerGestore.getCategorieBox()));
		
		cbxCategorie.setSelectedItem("Tutte");
		
//		DefaultComboBoxModel Modello  = new DefaultComboBoxModel();		
//		Modello.addElement("Tutte le categorie");
//		if(this.controllerAmministratore != null)
//			Modello.addAll(Arrays.asList(controllerAmministratore.getCategorie()));
//		else
//			Modello.addAll(Arrays.asList(controllerGestore.getCategorieBox()));
//		cbxCategorie.setModel(Modello);
//		cbxCategorie.setSelectedItem("Tutte le categorie");
	}
}		

