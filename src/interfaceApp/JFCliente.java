package interfaceApp;

import bean.Cliente;
import dao.ClienteDAO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class JFCliente extends javax.swing.JFrame {
    
    // arraylist da classe cliente
    ArrayList<Cliente> clientes = new ArrayList<>();

    // -1 é inserção e diferente é atualização ou remoção
    private int linha = -1;
    
    public JFCliente() {
        initComponents();

        // centraliza os componentes
        setLocationRelativeTo(null);

        this.preenchaTabela();
    }

    private void preenchaTabela() {

        // recupera o objeto da tabela 
        DefaultTableModel model = (DefaultTableModel) tblCliente.getModel();

        // limpa a tabela
        model.setRowCount(0);

        // cria um objeto da classe ClienteDAO
        ClienteDAO dao = new ClienteDAO();

        // preenche o arraylist
        this.clientes = dao.liste();

        // cria um objeto para formatar a data
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        // percorre o arraylist
        for (int i = 0; i < this.clientes.size(); i++) {
           
            String cpfCnpj = this.clientes.get(i).getCpfCnpj().substring(0, 3) + "." +
                    this.clientes.get(i).getCpfCnpj().substring(3, 6) + "." + 
                    this.clientes.get(i).getCpfCnpj().substring(6, 9) + "-" + 
                    this.clientes.get(i).getCpfCnpj().substring(9, 11);
            
            String telefone = "(" + this.clientes.get(i).getTelefone().substring(0, 2) + ") " +
                    this.clientes.get(i).getTelefone().substring(2, 7) + "-" + 
                    this.clientes.get(i).getTelefone().substring(7, 11);
            
            // adiciona uma linha na tabela
            model.addRow(new String[]{
                this.clientes.get(i).getNome(),
                cpfCnpj,
                telefone,
                format.format(this.clientes.get(i).getDataCadastro())
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jcbTipoPessoa = new javax.swing.JComboBox<>();
        txtCpfCnpj = new javax.swing.JFormattedTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtEmail = new javax.swing.JTextField();
        btnGravar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnFiltro = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestão de Clientes");

        jLabel1.setText("Nome");

        jLabel2.setText("Tipo");

        jLabel3.setText("Nº Documento");

        jLabel4.setText("Telefone");

        jLabel5.setText("E-mail");

        jcbTipoPessoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pessoa Física", "Pessoa Jurídica" }));

        try {
            txtCpfCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnGravar.setText("Gravar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);

        btnNovo.setText("Novo");
        btnNovo.setEnabled(false);

        btnFiltro.setText("Filtro");

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Nº Documento", "Telefone", "Data de Cadastro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCliente);
        if (tblCliente.getColumnModel().getColumnCount() > 0) {
            tblCliente.getColumnModel().getColumn(0).setPreferredWidth(300);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGravar)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluir)
                                .addGap(18, 18, 18)
                                .addComponent(btnNovo)
                                .addGap(18, 18, 18)
                                .addComponent(btnFiltro))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jcbTipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtTelefone))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel5))
                                    .addComponent(jLabel3)
                                    .addComponent(txtCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbTipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGravar)
                    .addComponent(btnExcluir)
                    .addComponent(btnNovo)
                    .addComponent(btnFiltro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        
        int id = -1;
        
        // verifica o modo de operação do formulário
        if(this.linha != -1){
            id = this.clientes.get(this.linha).getPessoaId();
        }
        
        // determina o valor que será passado para a procedure tipo pessoa
        String tipoPessoa = "";
        
        if(jcbTipoPessoa.getSelectedItem().toString().equals("Pessoa Física")){
            tipoPessoa = "F";
        } else {
            tipoPessoa = "J";
        }
        
        // cria um objeto da classe cliente
        Cliente cliente = new Cliente(
                id,
                txtNome.getText(),
                tipoPessoa,
                txtCpfCnpj.getText().replace(".", "").replace("-", "").replace("/", ""),
                txtTelefone.getText().replace("(", "").replace(")", "").replace(" ", "").replace("-", ""),
                txtEmail.getText(),
                null
        );
        
        // cria um objeto da classe DAO
        ClienteDAO dao = new ClienteDAO();
        
        // registra no banco e recupera a msg
        String msg = dao.grave(cliente);
        
        this.preenchaTabela();
        
        // controle de botoes
        this.controlaButton(false);
        
        // limpa o form
        this.limpaForm();
        
        // exibe a msg
        JOptionPane.showMessageDialog(rootPane, msg);
    }//GEN-LAST:event_btnGravarActionPerformed

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        
        // determina a linha clicada na tabela
        this.linha = tblCliente.rowAtPoint(evt.getPoint());
        
        // seta os campos do formulário com os atributos do abj selecionado
        txtNome.setText(this.clientes.get(this.linha).getNome());
        
        // determina o valor do JComboBox
        if ( this.clientes.get( this.linha ).getTipoPessoa().equals("F") ) {
            jcbTipoPessoa.setSelectedItem("Pessoa Física");
        } else {
            jcbTipoPessoa.setSelectedItem("Pessoa Jurídica");
        }
        txtCpfCnpj.setText(this.clientes.get(this.linha).getCpfCnpj());
        txtTelefone.setText(this.clientes.get(this.linha).getTelefone());
        txtEmail.setText(this.clientes.get(this.linha).getEmail());
        
        // controle de botoes
        this.controlaButton(true);
    }//GEN-LAST:event_tblClienteMouseClicked

    private void controlaButton(boolean habilita){
        btnExcluir.setEnabled(habilita);
        btnNovo.setEnabled(habilita);
    }
    
    private void limpaForm(){
        
        this.linha = -1;
        
        txtNome.setText("");
        jcbTipoPessoa.setSelectedItem("Pessoa Física");
        txtTelefone.setText("");
        txtEmail.setText("");
    }
    
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
            java.util.logging.Logger.getLogger(JFCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFiltro;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jcbTipoPessoa;
    private javax.swing.JTable tblCliente;
    private javax.swing.JFormattedTextField txtCpfCnpj;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
