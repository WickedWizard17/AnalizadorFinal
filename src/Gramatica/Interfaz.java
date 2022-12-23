package Gramatica;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Interfaz extends javax.swing.JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private NumeroLinea nm;
    private JFileChooser jfc;
    private FileNameExtensionFilter filtro = new FileNameExtensionFilter("archivos TURING .TUR", "TUR");
    private String ruta = "";
    private FileOutputStream output; // flujo de salida de datos
    private FileInputStream input; // flujo de entrada de datos
    
    public Interfaz() {
        initComponents();
        setLocationRelativeTo(this);
	setResizable(false);
        getContentPane().setBackground(new Color(0,0,0));
        nm = new NumeroLinea(areaCodigo);
	jScrollPane1.setRowHeaderView(nm);
        bNuevo.addActionListener((ActionListener) this);
	bAbrir.addActionListener(this);
	bGuardarComo.addActionListener(this);
	bGuardar.addActionListener(this);
	bCerrar.addActionListener(this);
	bAnalizar.addActionListener(this);
        jTabbedPane1.setEnabled(false);
        bGuardar.setEnabled(false);
	bGuardarComo.setEnabled(false);
	bCerrar.setEnabled(false);
        areaCodigo.setEnabled(false);
        areaTokens.setEnabled(false);
        areaPila.setEnabled(false);
        areaSimbolos.setEnabled(false);
        areaErrores.setEnabled(false);
        areaPosfija.setEnabled(false);
        areaIntermedio.setEnabled(false);
    }
    
        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bAbrir)
		{
			this.Abrir();
		}
		else if (e.getSource() == bGuardarComo)
		{
			this.GuardarComo();
		}
		else if (e.getSource() == bNuevo)
		{
			this.Nuevo();
		}
		else if (e.getSource() == bGuardar)
		{
			this.Guardar();
		}
		else if (e.getSource() == bCerrar)
		{
			this.Cerrar();
		}
		else if (e.getSource() == bAnalizar)
		{
                    
		}
    }
        
    private void Abrir()
	{
		try
		{
			jfc = new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jfc.setFileFilter(filtro);
			jfc.showOpenDialog(this);
			ruta = jfc.getSelectedFile().toString();
			try
			{
				output = new FileOutputStream(ruta, true);
			}
			catch (IOException e)
			{
				JOptionPane.showMessageDialog(null, "Error en el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			String contenido = "";
			byte datos[] = new byte[30];
			int leidos = 0;
			try
			{
				input = new FileInputStream(ruta);
				do
				{
					leidos = input.read(datos);
					if (leidos != -1)
						contenido += new String(datos, 0, leidos);
				}
				while (leidos != -1);
				input.close();
				output.close();
			}
			catch (IOException e)
			{
				JOptionPane.showMessageDialog(null, "Error en el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			areaCodigo.setText(contenido);
			areaCodigo.setEnabled(true);
                        areaTokens.setEnabled(true);
                        areaPila.setEnabled(true);
                        areaSimbolos.setEnabled(true);
                        areaErrores.setEnabled(true);
			bNuevo.setEnabled(false);
			bAbrir.setEnabled(false);
			bGuardar.setEnabled(true);
			bGuardarComo.setEnabled(true);
			bCerrar.setEnabled(true);
			bAnalizar.setEnabled(true);
			bAnalizar.setEnabled(true);
			jTabbedPane1.setEnabled(true);
                        areaPosfija.setEnabled(true);
                        areaIntermedio.setEnabled(true);
		}
		catch (NullPointerException e)
		{
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
    
    private void GuardarComo()
	{
		try
		{
			String contenido = areaCodigo.getText();
			jfc = new JFileChooser();
			jfc.setSelectedFile(new File("programa.TUR"));
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jfc.setFileFilter(filtro);
			int opcion = jfc.showSaveDialog(this);
			ruta = jfc.getSelectedFile().toString();
			File archivo = jfc.getSelectedFile();
			if (opcion == JFileChooser.APPROVE_OPTION)
			{
				if (archivo.exists())
				{
					int resultado = JOptionPane.showConfirmDialog(this, "Ya existe un archivo con el mismo nombre ¿Desea sobrescribirlo?", "Archivo ya existe", JOptionPane.YES_NO_OPTION);
					if (resultado == JOptionPane.YES_OPTION)
						try
						{
							output = new FileOutputStream(ruta);
							output.write(contenido.getBytes());
							output.close();
						}
						catch (IOException e)
						{
							JOptionPane.showMessageDialog(null, "Error en el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					else
						this.GuardarComo();
				}
				else
					try
					{
						output = new FileOutputStream(ruta);
						output.write(contenido.getBytes());
						output.close();
					}
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(null, "Error en el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
			}
		}
		catch (NullPointerException e)
		{
			JOptionPane.showMessageDialog(null, "Error al guardar el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
    
    private void Nuevo()
	{
		areaCodigo.setEnabled(true);
                areaTokens.setEnabled(true);
                areaPila.setEnabled(true);
                areaSimbolos.setEnabled(true);
                areaErrores.setEnabled(true);
                areaPosfija.setEnabled(true);
                areaIntermedio.setEnabled(true);
		areaCodigo.setText("");
		bNuevo.setEnabled(false);
		bAbrir.setEnabled(false);
		bGuardar.setEnabled(true);
		bGuardarComo.setEnabled(true);
		bCerrar.setEnabled(true);
		bAnalizar.setEnabled(true);
		jTabbedPane1.setEnabled(true);
	}
    
    private void Guardar()
	{
		try
		{
			String contenido = areaCodigo.getText();
			if (ruta.isEmpty())
				this.GuardarComo();
			try
			{
				output = new FileOutputStream(ruta);
				output.write(contenido.getBytes());
				output.close();
			}
			catch (IOException e)
			{
				JOptionPane.showMessageDialog(null, "Error en el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch (NullPointerException e)
		{
			JOptionPane.showMessageDialog(null, "Error al guardar el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
    
    private void Cerrar()
	{
		int resultado = JOptionPane.showConfirmDialog(this, "¿Desea guardar antes de cerrar el archivo?", "Aviso", JOptionPane.YES_NO_OPTION);
		if (resultado == JOptionPane.YES_OPTION)
			if (ruta.isEmpty())
				this.GuardarComo();
			else
				this.Guardar();
		areaCodigo.setEnabled(false);
                areaTokens.setEnabled(false);
                areaPila.setEnabled(false);
                areaSimbolos.setEnabled(false);
                areaErrores.setEnabled(false);
		bNuevo.setEnabled(true);
		bAbrir.setEnabled(true);
		bGuardar.setEnabled(false);
		bGuardarComo.setEnabled(false);
		bCerrar.setEnabled(false);
		bAnalizar.setEnabled(false);
		jTabbedPane1.setEnabled(false);
                areaPosfija.setEnabled(false);
                areaIntermedio.setEnabled(false);
                areaIntermedio.setText("");
                areaPosfija.setText("");
		ruta = "";
                areaCodigo.setText("");
                areaTokens.setText("");
                areaPila.setText("");
                areaErrores.setText("");
                areaSimbolos.setText("");
	}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bNuevo = new javax.swing.JButton();
        bAbrir = new javax.swing.JButton();
        bGuardar = new javax.swing.JButton();
        bGuardarComo = new javax.swing.JButton();
        bCerrar = new javax.swing.JButton();
        etTitulo = new javax.swing.JLabel();
        bAnalizar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaCodigo = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaTokens = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaPila = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        areaSimbolos = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        areaErrores = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        areaPosfija = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        areaIntermedio = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COMPILADOR TURING");

        bNuevo.setBackground(new java.awt.Color(0, 204, 204));
        bNuevo.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        bNuevo.setText("Nuevo");

        bAbrir.setBackground(new java.awt.Color(102, 255, 102));
        bAbrir.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        bAbrir.setText("Abrir");

        bGuardar.setBackground(new java.awt.Color(255, 51, 51));
        bGuardar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        bGuardar.setText("Guardar");

        bGuardarComo.setBackground(new java.awt.Color(255, 255, 51));
        bGuardarComo.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        bGuardarComo.setText("Guardar Como");

        bCerrar.setBackground(new java.awt.Color(255, 51, 255));
        bCerrar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        bCerrar.setText("Cerrar");

        etTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        etTitulo.setForeground(new java.awt.Color(255, 255, 255));
        etTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etTitulo.setText("COMPILADOR TURING");

        bAnalizar.setBackground(new java.awt.Color(0, 51, 102));
        bAnalizar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        bAnalizar.setForeground(new java.awt.Color(255, 255, 255));
        bAnalizar.setText("Analizar");
        bAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnalizarActionPerformed(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        areaCodigo.setColumns(20);
        areaCodigo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        areaCodigo.setRows(5);
        jScrollPane1.setViewportView(areaCodigo);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Código Fuente", jPanel1);

        areaTokens.setColumns(20);
        areaTokens.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        areaTokens.setRows(5);
        jScrollPane2.setViewportView(areaTokens);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Tokens", jPanel2);

        areaPila.setColumns(20);
        areaPila.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        areaPila.setRows(5);
        jScrollPane3.setViewportView(areaPila);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Pila", jPanel3);

        areaSimbolos.setColumns(20);
        areaSimbolos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        areaSimbolos.setRows(5);
        jScrollPane5.setViewportView(areaSimbolos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Tabla de Símbolos", jPanel4);

        areaErrores.setColumns(20);
        areaErrores.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        areaErrores.setRows(5);
        jScrollPane4.setViewportView(areaErrores);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Errores", jPanel5);

        areaPosfija.setColumns(20);
        areaPosfija.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        areaPosfija.setRows(5);
        jScrollPane6.setViewportView(areaPosfija);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Notación Posfija", jPanel6);

        areaIntermedio.setColumns(20);
        areaIntermedio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        areaIntermedio.setRows(5);
        jScrollPane7.setViewportView(areaIntermedio);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Código Intermedio", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(etTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bGuardarComo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bNuevo)
                    .addComponent(bAbrir)
                    .addComponent(bGuardar)
                    .addComponent(bGuardarComo)
                    .addComponent(bCerrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bAnalizar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnalizarActionPerformed
        // TODO add your handling code here:
                areaTokens.setText("");
                areaPila.setText("");
                areaErrores.setText("");
                areaSimbolos.setText("");
                PrincipalF obp = new PrincipalF();
                
                obp.textoPila = "";          
                obp.textoTokens = "";
                obp.textoErrores = "";
                obp.textoCodigoInter = "";
                obp.textoNotacion = "";
                
                obp.Accion(obp.Entrada(obp.Separar(areaCodigo.getText().replaceAll("\\n", ""))));
                obp.mostrarSimbolos();
                areaPila.setText(obp.textoPila);
                areaTokens.setText(obp.textoTokens);
                areaErrores.setText(obp.textoErrores);
                areaSimbolos.setText(obp.textoSimbolos);
                areaIntermedio.setText(obp.textoCodigoInter);
                areaPosfija.setText(obp.textoNotacion);
    }//GEN-LAST:event_bAnalizarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaCodigo;
    private javax.swing.JTextArea areaErrores;
    private javax.swing.JTextArea areaIntermedio;
    private javax.swing.JTextArea areaPila;
    private javax.swing.JTextArea areaPosfija;
    private javax.swing.JTextArea areaSimbolos;
    private javax.swing.JTextArea areaTokens;
    private javax.swing.JButton bAbrir;
    private javax.swing.JButton bAnalizar;
    private javax.swing.JButton bCerrar;
    private javax.swing.JButton bGuardar;
    private javax.swing.JButton bGuardarComo;
    private javax.swing.JButton bNuevo;
    private javax.swing.JLabel etTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

}