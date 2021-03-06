package GUI;

import java.awt.Color;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerAmministratore;

import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public class AggiungiAllergeniFrame extends JFrame {

	private JPanel contentPane;
	private JFrame parent = this;
	private Point initialClick;
	private ControllerAmministratore controllerAmministratore = null;


	public AggiungiAllergeniFrame(ControllerAmministratore ControllerAmministratore, int idProdotto) {
		this.controllerAmministratore = ControllerAmministratore;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,442,580);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 67, 375, 435);
		contentPane.add(scrollPane);
		
		JTable tblAllergeni = new JTable();
		tblAllergeni.setRowHeight(30);
		tblAllergeni.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblAllergeni.setFillsViewportHeight(true);
		tblAllergeni.setModel(new DefaultTableModel(
			controllerAmministratore.getAllergeniMancanti(idProdotto),
			new String[] {
				"Allergeni"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblAllergeni.getColumnModel().getColumn(0).setResizable(false);
		tblAllergeni.setFont(new Font("Calibri", Font.PLAIN, 14));
		scrollPane.setViewportView(tblAllergeni);
		
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(tblAllergeni.getSelectedRowCount() > 0) {
						
						String[] Allergeni = new String[tblAllergeni.getSelectedRowCount()];
						int righe[] = tblAllergeni.getSelectedRows();
							for(int i = 0; i<tblAllergeni.getSelectedRowCount(); i++)
								Allergeni[i] = (String) tblAllergeni.getValueAt(righe[i], 0);
				
								controllerAmministratore.AggiungiAllergeniAProdotto(idProdotto, Allergeni);
					}
				}
			}
		});
		btnAggiungi.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAggiungi.setBounds(100, 513, 104, 38);
		contentPane.add(btnAggiungi);
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					controllerAmministratore.ChiudiAggiungiAllergeniFrame();
			}
		});
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnChiudi.setBounds(228, 513, 104, 38);
		contentPane.add(btnChiudi);
		
		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setName("");
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 448, 45);
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
	    
		JLabel lblTitolo = new JLabel("Aggiungi Allergeni");
		lblTitolo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setBounds(0, 0, 148, 35);
		pnlBarra.add(lblTitolo);
		
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
	}

}
