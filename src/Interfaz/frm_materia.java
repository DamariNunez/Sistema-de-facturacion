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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frm_materia extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigoMateria;
	private JTextField txtNombreMateria;
	private JTextField txtCreditoMateria;
	private JTable table;
	private DefaultTableModel modelo;
	private JTextField txtCodigoDocente;
	private JTextField txtCodigoDep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frm_materia frame = new frm_materia();
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
			String[] titulos={"CODIGO","MATERIA","CREDITOS","DOCENTE","DEPARTAMENTO"};
			String sql="select * from materia";
			modelo = new DefaultTableModel(null, titulos);
			PreparedStatement sent= con.getConexion().prepareStatement(sql);
			ResultSet rs=sent.executeQuery(sql);
			String[] fila=new String[5];
			while(rs.next()){
				fila[0]=rs.getString("IdMateria");
				fila[1]=rs.getString("nombreMateria");
				fila[2]=rs.getString("creditoMateria");
				fila[3]=rs.getString("idDocente");
				fila[4]=rs.getString("idDep");
				modelo.addRow(fila);
			}
			table.setModel(modelo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public frm_materia() {
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
		
		JLabel lblFormularioMateria = new JLabel("FORMULARIO MATERIA");
		lblFormularioMateria.setBounds(202, 13, 151, 16);
		contentPane.add(lblFormularioMateria);
		
		JLabel lblCodigo = new JLabel("Codigo de materia:");
		lblCodigo.setBounds(41, 60, 117, 16);
		contentPane.add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(41, 99, 56, 16);
		contentPane.add(lblNombre);
		
		JLabel lblNumeroDeCreditos = new JLabel("Numero de creditos:");
		lblNumeroDeCreditos.setBounds(41, 144, 117, 16);
		contentPane.add(lblNumeroDeCreditos);
		
		txtCodigoMateria = new JTextField();
		txtCodigoMateria.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int k=(int) e.getKeyChar();
				if(k>47 && k<58){
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
					JOptionPane.showMessageDialog(null, "No puede ingresar numeros", "Validando Datos",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		txtCodigoMateria.setBounds(202, 57, 157, 22);
		contentPane.add(txtCodigoMateria);
		txtCodigoMateria.setColumns(10);
		
		txtNombreMateria = new JTextField();
		txtNombreMateria.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int k=(int) e.getKeyChar();
				if(k>47 && k<58){
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
					JOptionPane.showMessageDialog(null, "No puede ingresar numeros", "Validando Datos",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		txtNombreMateria.setBounds(202, 96, 157, 22);
		contentPane.add(txtNombreMateria);
		txtNombreMateria.setColumns(10);
		
		txtCreditoMateria = new JTextField();
		txtCreditoMateria.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int k=(int) e.getKeyChar();
				if(k>47 && k<58){
					e.setKeyChar((char) KeyEvent.VK_CLEAR);
					JOptionPane.showMessageDialog(null, "No puede ingresar numeros", "Validando Datos",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		txtCreditoMateria.setBounds(202, 141, 157, 22);
		contentPane.add(txtCreditoMateria);
		txtCreditoMateria.setColumns(10);
		
		JButton btnRegistrarDpto = new JButton("Registrar Dpto");
		btnRegistrarDpto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodigoMateria.setText("");
				txtNombreMateria.setText("");
				txtCreditoMateria.setText("");
				txtCodigoDocente.setText("");
				txtCodigoDep.setText("");
				txtCodigoMateria.setEnabled(true);
				txtNombreMateria.setEnabled(true);
				txtCreditoMateria.setEnabled(true);
				txtCodigoDocente.setEnabled(true);
				txtCodigoDep.setEnabled(true);
			}
		});
		btnRegistrarDpto.setBounds(395, 56, 143, 25);
		contentPane.add(btnRegistrarDpto);
		
		JButton btnGuardarRegistro = new JButton("Guardar registro");
		btnGuardarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Se presionó el botón guardar");
				try{
					DatosDal.MetodoMateria materia=new DatosDal.MetodoMateria();
					boolean pres=materia.GuardarMateria(txtCodigoMateria.getText(), txtNombreMateria.getText(), txtCreditoMateria.getText(),txtCodigoDocente.getText(),txtCodigoDep.getText());
					System.out.println("Metodo guardar");
					if(pres){
						JOptionPane.showMessageDialog(null, "Se guardo el registro");
					}else{
						JOptionPane.showMessageDialog(null, "No se guardo el registro");
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Llenar las casillas de texto");
					if(txtCodigoMateria.getText().trim().length()==0){
						JOptionPane.showMessageDialog(null, "El campo de texto esta vacio");
					}else{
						JOptionPane.showMessageDialog(null, "El valor ingresado es "+txtCodigoMateria.getText());
					}
				}
			}
		});
		btnGuardarRegistro.setBounds(395, 95, 143, 25);
		contentPane.add(btnGuardarRegistro);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0){
				int fila = table.getSelectedRow();
				txtCodigoMateria.setText(table.getValueAt(fila, 0).toString());
				txtNombreMateria.setText(table.getValueAt(fila, 1).toString());
				txtCreditoMateria.setText(table.getValueAt(fila, 2).toString());
				txtCodigoDocente.setText(table.getValueAt(fila, 3).toString());
				txtCodigoDep.setText(table.getValueAt(fila, 4).toString());
				JOptionPane.showMessageDialog(null, table.getValueAt(fila, 1));
			}
		});	
		
		JButton btnActualizarDpto = new JButton("Actualizar Dpto");
		btnActualizarDpto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					DatosDal.MetodoMateria materia=new DatosDal.MetodoMateria();
					boolean pres=materia.ActualizarMateria(txtCodigoMateria.getText(), txtNombreMateria.getText(), txtCreditoMateria.getText(), txtCodigoDocente.getText(), txtCodigoDep.getText());
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
		btnActualizarDpto.setBounds(395, 140, 143, 25);
		contentPane.add(btnActualizarDpto);
		
		JButton btnEliminarDpto = new JButton("Eliminar Dpto");
		btnEliminarDpto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int fila = table.getSelectedRow();
					DatosDal.MetodoMateria materia=new DatosDal.MetodoMateria();
					boolean pres=materia.EliminarMateria(fila, table);
					if(pres){
						JOptionPane.showMessageDialog(null, "Datos NO Eliminados Correctamente");
					}else{
						JOptionPane.showMessageDialog(null, "Datos Eliminados Correctamente");
						txtCodigoMateria.setText("");
						txtNombreMateria.setText("");
						txtCreditoMateria.setText("");
						txtCodigoDocente.setText("");
						txtCodigoDep.setText("");
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Error... Seleccionar registro a eliminar");					
				}
			}
		});
		btnEliminarDpto.setBounds(395, 182, 143, 25);
		contentPane.add(btnEliminarDpto);
		
		JButton btnConsultarDpto = new JButton("Consultar Dpto");
		btnConsultarDpto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					txtNombreMateria.setEnabled(false);
					txtCreditoMateria.setEnabled(false);
					txtCodigoDocente.setEnabled(false);
					txtCodigoDep.setEnabled(false);
					Datos.Materia materia=new Datos.Materia();
					DatosDal.MetodoMateria metodoMateria=new DatosDal.MetodoMateria();
					metodoMateria.ConsultarMateria(txtCodigoMateria.getText());
					txtCodigoMateria.setText(materia.getIdMateria());
					txtNombreMateria.setText(materia.getNombreMateria());
					txtCreditoMateria.setText(String.valueOf(materia.getCreditoMateria()));
					txtCodigoDocente.setText(materia.getIdDocente());
					txtCodigoDep.setText(materia.getIdDep());
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Ingresar la cedula para la consulta");
					txtNombreMateria.setEnabled(true);
					txtCreditoMateria.setEnabled(true);
					txtCodigoDocente.setEnabled(true);
					txtCodigoDep.setEnabled(true);
				}
			}
		});
		btnConsultarDpto.setBounds(395, 233, 143, 25);
		contentPane.add(btnConsultarDpto);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menu=new MenuPrincipal();
				menu.setVisible(false);
				setVisible(false);
			}
		});
		btnCancelar.setBounds(414, 284, 97, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblCodigoDeDocente = new JLabel("Codigo de docente:");
		lblCodigoDeDocente.setBounds(41, 186, 117, 16);
		contentPane.add(lblCodigoDeDocente);
		
		JLabel lblCodigoDeDepartamento = new JLabel("Codigo de departamento:");
		lblCodigoDeDepartamento.setBounds(39, 233, 151, 16);
		contentPane.add(lblCodigoDeDepartamento);
		
		txtCodigoDocente = new JTextField();
		txtCodigoDocente.setBounds(202, 183, 157, 22);
		contentPane.add(txtCodigoDocente);
		txtCodigoDocente.setColumns(10);
		
		txtCodigoDep = new JTextField();
		txtCodigoDep.setBounds(202, 234, 157, 22);
		contentPane.add(txtCodigoDep);
		txtCodigoDep.setColumns(10);
	}
}
