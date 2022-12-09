package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frm_departamento extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigoDep;
	private JTextField txtNombreDep;
	private JTextField txtDirectorDep;
	private JTable table;
	private DefaultTableModel modelo;
	private int limite=10;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frm_departamento frame = new frm_departamento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	void Llenar(){
		try{
			Conection.conexion con=new Conection.conexion();
			String[] titulos={"CODIGO","NOMBRE","DIRECTOR"};
			String sql="select * from departamento";
			modelo = new DefaultTableModel(null, titulos);
			PreparedStatement sent= con.getConexion().prepareStatement(sql);
			ResultSet rs=sent.executeQuery(sql);
			String[] fila=new String[3];
			while(rs.next()){
				fila[0]=rs.getString("IdDep");
				fila[1]=rs.getString("nombreDep");
				fila[2]=rs.getString("directorDep");
				modelo.addRow(fila);
			}
			table.setModel(modelo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public frm_departamento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 656);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(39,381,553,202);
		getContentPane().add(tabbedPane);
		
		JTable table = new JTable();
		tabbedPane.addTab("New tab", null, table, null);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(48, 81, 56, 16);
		contentPane.add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(48, 118, 56, 16);
		contentPane.add(lblNombre);
		
		JLabel lblDirector = new JLabel("Director:");
		lblDirector.setBounds(48, 159, 56, 16);
		contentPane.add(lblDirector);
		
		txtCodigoDep = new JTextField();
		txtCodigoDep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int k=(int) e.getKeyChar();
				if(k>47 && k<58){
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
					JOptionPane.showMessageDialog(null, "No puede ingresar numeros", "Validando Datos",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		txtCodigoDep.setBounds(141, 78, 159, 22);
		contentPane.add(txtCodigoDep);
		txtCodigoDep.setColumns(10);
		
		txtNombreDep = new JTextField();
		txtNombreDep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int k=(int) e.getKeyChar();
				if(k>47 && k<58){
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
					JOptionPane.showMessageDialog(null, "No puede ingresar numeros", "Validando Datos",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		txtNombreDep.setColumns(10);
		txtNombreDep.setBounds(141, 115, 159, 22);
		contentPane.add(txtNombreDep);
		
		txtDirectorDep = new JTextField();
		txtDirectorDep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int k=(int) e.getKeyChar();
				if(k>47 && k<58){
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
					JOptionPane.showMessageDialog(null, "No puede ingresar numeros", "Validando Datos",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		txtDirectorDep.setColumns(10);
		txtDirectorDep.setBounds(141, 156, 159, 22);
		contentPane.add(txtDirectorDep);
		
		JLabel lblFormularioDepartamento = new JLabel("FORMULARIO DEPARTAMENTO");
		lblFormularioDepartamento.setBounds(217, 25, 201, 22);
		contentPane.add(lblFormularioDepartamento);
		
		JButton btnRegistrarDpto = new JButton("Registrar Dpto");
		btnRegistrarDpto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCodigoDep.setText("");
				txtNombreDep.setText("");
				txtDirectorDep.setText("");
				txtCodigoDep.setEnabled(true);
				txtNombreDep.setEnabled(true);
				txtDirectorDep.setEnabled(true);
			}
		});
		btnRegistrarDpto.setBounds(349, 77, 135, 25);
		contentPane.add(btnRegistrarDpto);
		
		JButton btnGuardarRegistro = new JButton("Guardar registro");
		btnGuardarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Se presionó el botón guardar");
				try{
					DatosDal.MetodoDepartamento dep=new DatosDal.MetodoDepartamento();
					boolean pres=dep.GuardarDepartamento(txtCodigoDep.getText(), txtNombreDep.getText(), txtDirectorDep.getText());
					System.out.println("Metodo guardar");
					if(pres){
						JOptionPane.showMessageDialog(null, "Se guardo el registro");
					}else{
						JOptionPane.showMessageDialog(null, "No se guardo el registro");
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Llenar las casillas de texto");
					if(txtCodigoDep.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null, "El campo de texto esta vacio");
					}else{
						JOptionPane.showMessageDialog(null, "El valor ingresado es "+txtCodigoDep.getText());
					}
				}
			}
		});
		btnGuardarRegistro.setBounds(349, 114, 135, 25);
		contentPane.add(btnGuardarRegistro);
		
		JButton btnActualizarDpto = new JButton("Actualizar Dpto");
		btnActualizarDpto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					DatosDal.MetodoDepartamento dep=new DatosDal.MetodoDepartamento();
					boolean pres=dep.ActualizarDepartamento(txtCodigoDep.getText(), txtNombreDep.getText(), txtDirectorDep.getText());
					System.out.println("Metodo guardar");
					if(!pres){
						JOptionPane.showMessageDialog(null, "Se actualizo el registro Correctamente");						
					}else{
						JOptionPane.showMessageDialog(null, "No se actualizo el registro");
					}
					Llenar();
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Llenar las casillas de texto");
				}
			}
		});
		btnActualizarDpto.setBounds(349, 155, 135, 25);
		contentPane.add(btnActualizarDpto);
		
		JButton btnEliminarDpto = new JButton("Eliminar Dpto");
		btnEliminarDpto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int fila = table.getSelectedRow();
					DatosDal.MetodoDepartamento dep=new DatosDal.MetodoDepartamento();
					boolean pres=dep.EliminarDepartamento(fila, table);
					if(pres){
						JOptionPane.showMessageDialog(null, "Datos NO Eliminados Correctamente");
					}else{
						JOptionPane.showMessageDialog(null, "Datos Eliminados Correctamente");
						txtCodigoDep.setText("");
						txtNombreDep.setText("");
						txtDirectorDep.setText("");
						Llenar();
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Error... Seleccionar registro a eliminar");					
				}
			}
		});
		btnEliminarDpto.setBounds(349, 195, 135, 25);
		contentPane.add(btnEliminarDpto);
		
		JButton btnConsultarDpto = new JButton("Consultar Dpto");
		btnConsultarDpto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					txtNombreDep.setEnabled(false);
					txtDirectorDep.setEnabled(false);
					Datos.Departamento dep=new Datos.Departamento();
					DatosDal.MetodoDepartamento metodoDep=new DatosDal.MetodoDepartamento();
					metodoDep.ConsultarDepartamento(txtCodigoDep.getText());
					txtCodigoDep.setText(dep.getIdDep());
					txtNombreDep.setText(dep.getNombreDep());
					txtDirectorDep.setText(dep.getDirectorDep());
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Ingresar la cedula para la consulta");
					txtCodigoDep.setEnabled(true);
					txtNombreDep.setEnabled(true);
					txtDirectorDep.setEnabled(true);
				}	
			}
		});
		btnConsultarDpto.setBounds(349, 245, 135, 25);
		contentPane.add(btnConsultarDpto);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menu=new MenuPrincipal();
				menu.setVisible(false);
				setVisible(false);
			}
		});
		btnCancelar.setBounds(349, 290, 127, 25);
		contentPane.add(btnCancelar);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0){
				int fila = table.getSelectedRow();
				txtCodigoDep.setText(table.getValueAt(fila, 0).toString());
				txtNombreDep.setText(table.getValueAt(fila, 1).toString());
				txtDirectorDep.setText(table.getValueAt(fila, 2).toString());
				JOptionPane.showMessageDialog(null, table.getValueAt(fila, 1));
			}
		});
	}
}
