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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class frm_docente extends JFrame {

	private JPanel contentPane;
	private JTextField txtcodigoDocente;
	private JTextField txtNombreDocente;
	private JTextField txtApellidoDocente;
	private JTextField txtDireccionDocente;
	private JTextField txtEmailDocente;
	private JTextField txtTelefonoDocente;
	private JTextField txtCedulaDocente;
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
					frm_docente frame = new frm_docente();
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
			String[] titulos={"CODIGO","CEDULA","NOMBRE","APELLIDO","DIRECCION","CORREO","TELEFONO"};
			String sql="select * from docente";
			modelo = new DefaultTableModel(null, titulos);
			PreparedStatement sent= con.getConexion().prepareStatement(sql);
			ResultSet rs=sent.executeQuery(sql);
			String[] fila=new String[7];
			while(rs.next()){
				fila[0]=rs.getString("IdDocente");
				fila[1]=rs.getString("cedulaDocente");
				fila[2]=rs.getString("nombreDocente");
				fila[3]=rs.getString("apellidoDocente");
				fila[4]=rs.getString("direccionDocente");
				fila[5]=rs.getString("correoDocente");
				fila[6]=rs.getString("telefonoDocente");
				modelo.addRow(fila);
			}
			table.setModel(modelo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public frm_docente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 656);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistrarDocente = new JLabel("FORMULARIO DOCENTE");
		lblRegistrarDocente.setBounds(222, 13, 160, 16);
		contentPane.add(lblRegistrarDocente);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(27, 60, 56, 16);
		contentPane.add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(27, 143, 56, 16);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(27, 189, 56, 16);
		contentPane.add(lblApellido);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setBounds(27, 236, 79, 16);
		contentPane.add(lblDireccion);
		
		JLabel lblEMail = new JLabel("E - mail:");
		lblEMail.setBounds(27, 289, 56, 16);
		contentPane.add(lblEMail);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(27, 331, 56, 16);
		contentPane.add(lblTelefono);
		
		txtcodigoDocente = new JTextField();
		txtcodigoDocente.setBounds(106, 57, 150, 22);
		contentPane.add(txtcodigoDocente);
		txtcodigoDocente.setColumns(10);
		
		txtNombreDocente = new JTextField();
		txtNombreDocente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int k=(int) e.getKeyChar();
				if(k>47 && k<58){
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
					JOptionPane.showMessageDialog(null, "No puede ingresar numeros", "Validando Datos",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		txtNombreDocente.setColumns(10);
		txtNombreDocente.setBounds(106, 140, 150, 22);
		contentPane.add(txtNombreDocente);
		
		txtApellidoDocente = new JTextField();
		txtApellidoDocente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int k=(int) e.getKeyChar();
				if(k>47 && k<58){
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
					JOptionPane.showMessageDialog(null, "No puede ingresar numeros", "Validando Datos",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		txtApellidoDocente.setColumns(10);
		txtApellidoDocente.setBounds(106, 186, 150, 22);
		contentPane.add(txtApellidoDocente);
		
		txtDireccionDocente = new JTextField();
		txtDireccionDocente.setColumns(10);
		txtDireccionDocente.setBounds(106, 233, 150, 22);
		contentPane.add(txtDireccionDocente);
		
		txtEmailDocente = new JTextField();
		txtEmailDocente.setColumns(10);
		txtEmailDocente.setBounds(106, 286, 150, 22);
		contentPane.add(txtEmailDocente);
		
		txtTelefonoDocente = new JTextField();
		txtTelefonoDocente.setColumns(10);
		txtTelefonoDocente.setBounds(106, 328, 150, 22);
		contentPane.add(txtTelefonoDocente);
		
		JButton btnRegistrarDocente = new JButton("Registrar docente");
		btnRegistrarDocente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtcodigoDocente.setText("");
				txtCedulaDocente.setText("");
				txtNombreDocente.setText("");
				txtApellidoDocente.setText("");
				txtDireccionDocente.setText("");
				txtEmailDocente.setText("");
				txtTelefonoDocente.setText("");
				txtcodigoDocente.setEnabled(true);
				txtCedulaDocente.setEnabled(true);
				txtNombreDocente.setEnabled(true);
				txtApellidoDocente.setEnabled(true);
				txtDireccionDocente.setEnabled(true);
				txtEmailDocente.setEnabled(true);
				txtTelefonoDocente.setEnabled(true);
			}
		});
		btnRegistrarDocente.setBounds(305, 56, 139, 25);
		contentPane.add(btnRegistrarDocente);
		
		JButton btnActualizarDocente = new JButton("Actualizar docente");
		btnActualizarDocente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					DatosDal.MetodoDocente docente=new DatosDal.MetodoDocente();
					boolean pres=docente.ActualizarDocente(txtcodigoDocente.getText(), txtCedulaDocente.getText(), txtNombreDocente.getText(), 
														   txtApellidoDocente.getText(), txtDireccionDocente.getText(), txtEmailDocente.getText(), txtTelefonoDocente.getText());
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
		btnActualizarDocente.setBounds(305, 154, 139, 25);
		contentPane.add(btnActualizarDocente);
		
		JButton btnEliminarDocente = new JButton("Eliminar docente");
		btnEliminarDocente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int fila = table.getSelectedRow();
					DatosDal.MetodoDocente docente=new DatosDal.MetodoDocente();
					boolean pres=docente.EliminarDocente(fila, table);
					if(pres){
						JOptionPane.showMessageDialog(null, "Datos NO Eliminados Correctamente");
					}else{
						JOptionPane.showMessageDialog(null, "Datos Eliminados Correctamente");
						txtcodigoDocente.setText("");
						txtCedulaDocente.setText("");
						txtNombreDocente.setText("");
						txtApellidoDocente.setText("");
						txtDireccionDocente.setText("");
						txtEmailDocente.setText("");
						txtTelefonoDocente.setText("");
						Llenar();
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Error... Seleccionar registro a eliminar");					
				}
			}
		});
		btnEliminarDocente.setBounds(305, 216, 139, 25);
		contentPane.add(btnEliminarDocente);
		
		JButton btnConsultarDocente = new JButton("Consultar docente");
		btnConsultarDocente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					txtCedulaDocente.setEnabled(false);
					txtNombreDocente.setEnabled(false);
					txtApellidoDocente.setEnabled(false);
					txtDireccionDocente.setEnabled(false);
					txtEmailDocente.setEnabled(false);
					txtTelefonoDocente.setEnabled(false);
					Datos.Docente docente=new Datos.Docente();
					DatosDal.MetodoDocente metodoDocente=new DatosDal.MetodoDocente();
					metodoDocente.ConsultarDocente(txtcodigoDocente.getText());
					txtcodigoDocente.setText(docente.getIdDocente());
					txtCedulaDocente.setText(docente.getCedulaDocente());
					txtNombreDocente.setText(docente.getNombreDocente());
					txtApellidoDocente.setText(docente.getApellidoDocente());
					txtDireccionDocente.setText(docente.getDireccionDocente());
					txtEmailDocente.setText(docente.getCorreoDocente());
					txtTelefonoDocente.setText(docente.getTelefonoDocente());
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Ingresar la cedula para la consulta");
					txtCedulaDocente.setEnabled(true);
					txtNombreDocente.setEnabled(true);
					txtApellidoDocente.setEnabled(true);
					txtDireccionDocente.setEnabled(true);
					txtEmailDocente.setEnabled(true);
					txtTelefonoDocente.setEnabled(true);
				}
			}
		});
		btnConsultarDocente.setBounds(305, 273, 139, 25);
		contentPane.add(btnConsultarDocente);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menu=new MenuPrincipal();
				menu.setVisible(false);
				setVisible(false);
			}
		});
		btnCancelar.setBounds(326, 327, 97, 25);
		contentPane.add(btnCancelar);
		
		txtCedulaDocente = new JTextField();
		txtCedulaDocente.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			}
		});
		txtCedulaDocente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		txtCedulaDocente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatosDal.MetodoDocente docente=new DatosDal.MetodoDocente();
				boolean pres=docente.ValidarCedula(txtCedulaDocente.getText());
			}
		});
		txtCedulaDocente.setBounds(105, 99, 151, 22);
		contentPane.add(txtCedulaDocente);
		txtCedulaDocente.setColumns(10);
		
		JLabel lblCedula = new JLabel("Cedula:");
		lblCedula.setBounds(27, 102, 56, 16);
		contentPane.add(lblCedula);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(39,381,553,202);
		getContentPane().add(tabbedPane);
		
		JTable table = new JTable();
		tabbedPane.addTab("New tab", null, table, null);
		
		JButton btnGuardarRegistro = new JButton("Guardar registro");
		btnGuardarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Se presionó el botón guardar");
				try{
					DatosDal.MetodoDocente docente=new DatosDal.MetodoDocente();
					boolean pres=docente.GuardarDocente(txtcodigoDocente.getText(), txtCedulaDocente.getText(), txtNombreDocente.getText(), 
														txtApellidoDocente.getText(), txtDireccionDocente.getText(), txtEmailDocente.getText(), txtTelefonoDocente.getText());
					System.out.println("Metodo guardar");
					if(pres){
						JOptionPane.showMessageDialog(null, "Se guardo el registro");
					}else{
						JOptionPane.showMessageDialog(null, "No se guardo el registro");
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Llenar las casillas de texto");
					if(txtcodigoDocente.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null, "El campo de texto esta vacio");
					}else{
						JOptionPane.showMessageDialog(null, "El valor ingresado es "+txtcodigoDocente.getText());
					}
				}
			}
		});
		btnGuardarRegistro.setBounds(305, 98, 139, 25);
		contentPane.add(btnGuardarRegistro);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0){
				int fila = table.getSelectedRow();
				txtcodigoDocente.setText(table.getValueAt(fila, 0).toString());
				txtCedulaDocente.setText(table.getValueAt(fila, 1).toString());
				txtNombreDocente.setText(table.getValueAt(fila, 2).toString());
				txtApellidoDocente.setText(table.getValueAt(fila, 3).toString());
				txtDireccionDocente.setText(table.getValueAt(fila, 4).toString());
				txtEmailDocente.setText(table.getValueAt(fila, 5).toString());
				txtTelefonoDocente.setText(table.getValueAt(fila, 6).toString());
				JOptionPane.showMessageDialog(null, table.getValueAt(fila, 1));
			}
		});
	}
}
