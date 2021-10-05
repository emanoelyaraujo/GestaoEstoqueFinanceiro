package interfaceApp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import bean.Fornecedor;
import dao.FornecedorDAO;
import java.text.ParseException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class JFFornecedor extends javax.swing.JFrame {

    // arraylist da classe cliente
    ArrayList<Fornecedor> fornecedores = new ArrayList<>();

    // -1 é inserção e diferente disso é atualização ou remoção
    private int linha = -1;

    public JFFornecedor() {
        initComponents();

        // não busca o ultimo valor digitado
        txtCpfCnpj.setFocusLostBehavior(JFormattedTextField.PERSIST);

        // centraliza os componentes
        setLocationRelativeTo(null);

        this.preencheTabela();
    }

    private void preencheTabela() {
        // recupera o objeto da tabela
        DefaultTableModel model = (DefaultTableModel) tblFornecedor.getModel();

        // limpa a tabela
        model.setRowCount(0);

        // cria um objeto da classe FornecedorDAO
        FornecedorDAO dao = new FornecedorDAO();

        // preenche o arraylist
        this.fornecedores = dao.liste();

        // cria um objeto para formatar a data
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        // pecorre o arraylist
        for (int i = 0; i < fornecedores.size(); i++) {

            String cpfCnpj = this.mascaraCpfCnpj(this.fornecedores.get(i).getCpfCnpj());

            String telefone = "(" + this.fornecedores.get(i).getTelefone().substring(0, 2) + ") "
                    + this.fornecedores.get(i).getTelefone().substring(2, 7) + "-"
                    + this.fornecedores.get(i).getTelefone().substring(7, 11);

            // adiciona uma linha na tabela
            model.addRow(new String[] { 
                this.fornecedores.get(i).getNome(), 
                cpfCnpj, 
                telefone,
                format.format(this.fornecedores.get(i).getDataCadastro()) 
            });
        }
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jcbTipoPessoa = new javax.swing.JComboBox<>();
        txtCpfCnpj = new javax.swing.JFormattedTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtEmail = new javax.swing.JTextField();
        btnGravar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();
        txtNome = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnFiltro = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFornecedor = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();

        jComboBox1.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestão de Fornecedores");

        jcbTipoPessoa
                .setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pessoa Física", "Pessoa Jurídica" }));
        jcbTipoPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTipoPessoaActionPerformed(evt);
            }
        });

        try {
            txtCpfCnpj.setFormatterFactory(
                    new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
                    new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnGravar.setBackground(new java.awt.Color(82, 144, 238));
        btnGravar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGravar.setText("Gravar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome");

        btnExcluir.setBackground(new java.awt.Color(200, 41, 41));
        btnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnNovo.setBackground(new java.awt.Color(95, 219, 95));
        btnNovo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setEnabled(false);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo");

        btnFiltro.setBackground(new java.awt.Color(109, 105, 110));
        btnFiltro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFiltro.setText("Filtro");
        btnFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroActionPerformed(evt);
            }
        });

        jLabel3.setText("Nº Documento");

        tblFornecedor.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

        }, new String[] { "Nome", "Nº Documento", "Telefone", "Data de Cadastro" }) {
            boolean[] canEdit = new boolean[] { false, false, false, false };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFornecedorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblFornecedor);

        jLabel4.setText("Telefone");

        jLabel5.setText("E-mail");

        btnCancelar.setBackground(new java.awt.Color(51, 51, 51));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                        .createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addComponent(btnGravar).addGap(18, 18, 18)
                                        .addComponent(btnExcluir).addGap(18, 18, 18).addComponent(btnNovo)
                                        .addGap(18, 18, 18).addComponent(btnFiltro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnCancelar))
                                .addGroup(layout.createSequentialGroup().addGroup(layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2).addComponent(jLabel4)
                                        .addComponent(jcbTipoPessoa, 0, 125, Short.MAX_VALUE).addComponent(txtTelefone))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout
                                                        .createSequentialGroup().addGap(1, 1, 1).addComponent(jLabel5))
                                                .addComponent(jLabel3)
                                                .addComponent(txtCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 133,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 170,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 246, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup().addGroup(layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1)
                                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 407,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap()))));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(19, 19, 19).addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2).addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jcbTipoPessoa, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4).addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnGravar).addComponent(btnExcluir).addComponent(btnNovo)
                                .addComponent(btnFiltro).addComponent(btnCancelar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 212,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.limpaForm();
    }

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {
        this.limpaForm();
        this.controlaButton(false);
        this.preencheTabela();
    }

    private void jcbTipoPessoaActionPerformed(java.awt.event.ActionEvent evt) {
        try {

            // verifica se o tipo de pessoa informada é PF OU PJ
            if (jcbTipoPessoa.getSelectedItem().toString().equals("Pessoa Física")) {

                // define a mascara de cpf
                txtCpfCnpj.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
            } else {
                // define a mascara de cnpj
                txtCpfCnpj.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));
            }

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(rootPane, "Não foi possível converter a máscara.");
        }
    }

    private void btnFiltroActionPerformed(java.awt.event.ActionEvent evt) {
        // cria um obj da classe dao
        FornecedorDAO dao = new FornecedorDAO();

        this.fornecedores = dao.pesquise(txtNome.getText());

        // recupera o objeto da tabela
        DefaultTableModel model = (DefaultTableModel) tblFornecedor.getModel();

        // limpa a tabela
        model.setRowCount(0);

        // cria um objeto para formatar a data
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        // percorre o arraylist
        for (int i = 0; i < this.fornecedores.size(); i++) {

            String cpfCnpj = this.mascaraCpfCnpj(this.fornecedores.get(i).getCpfCnpj());

            String telefone = "(" + this.fornecedores.get(i).getTelefone().substring(0, 2) + ") "
                    + this.fornecedores.get(i).getTelefone().substring(2, 7) + "-"
                    + this.fornecedores.get(i).getTelefone().substring(7, 11);

            // adiciona uma linha na tabela
            model.addRow(new String[] { 
                this.fornecedores.get(i).getNome(), 
                cpfCnpj, 
                telefone,
                format.format(this.fornecedores.get(i).getDataCadastro()) 
            });
        }
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        /// pergunta ao usuario se deseja mesno excluir
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente remover o registro?", "Confirmação",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            // cria um obj da classe dao
            FornecedorDAO dao = new FornecedorDAO();

            // remove e recupera a msg da procedure
            String msg = dao.remove(this.fornecedores.get(this.linha).getPessoaId());

            // preecnhe a tabela
            this.preencheTabela();

            // controle de botões
            this.controlaButton(false);

            // limpa o form
            this.limpaForm();

            // exibe a msg da procedure
            JOptionPane.showMessageDialog(null, msg);
        }
    }

    private void tblFornecedorMouseClicked(java.awt.event.MouseEvent evt) {
        // determina a linha clicada na tabela
        this.linha = tblFornecedor.rowAtPoint(evt.getPoint());

        // seta os campos do formulário com os atributos do abj selecionado
        txtNome.setText(this.fornecedores.get(this.linha).getNome());

        // determina o valor do JComboBox
        if (this.fornecedores.get(this.linha).getTipoPessoa().equals("F")) {
            jcbTipoPessoa.setSelectedItem("Pessoa Física");
        } else {
            jcbTipoPessoa.setSelectedItem("Pessoa Jurídica");
        }

        txtCpfCnpj.setText(this.fornecedores.get(this.linha).getCpfCnpj());
        txtTelefone.setText(this.fornecedores.get(this.linha).getTelefone());
        txtEmail.setText(this.fornecedores.get(this.linha).getEmail());

        // controle de botoes
        this.controlaButton(true);
    }

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {
        int id = -1;

        // seta o cursor para o campo txtCpfCnpj
        txtNome.requestFocus();

        // verifica o modo de operação do formulário
        if (this.linha != -1) {
            id = this.fornecedores.get(this.linha).getPessoaId();
        }

        // determina o valor que sera passado para a procedure
        String tipoPessoa = "";

        if (jcbTipoPessoa.getSelectedItem().toString().equals("Pessoa Física")) {
            tipoPessoa = "F";
        } else {
            tipoPessoa = "J";
        }

        // cria um objeto da classe cliente
        Fornecedor fornecedor = new Fornecedor(id, txtNome.getText(), tipoPessoa,
            txtCpfCnpj.getText().replace(".", "").replace("-", "").replace("/", ""),
            txtTelefone.getText().replace("(", "").replace(")", "").replace(" ", "").replace("-", ""),
            txtEmail.getText(), null);

        // cria um objeto da classe DAO
        FornecedorDAO dao = new FornecedorDAO();

        // registra no banco e recupera a msg
        String msg = dao.grave(fornecedor);

        this.preencheTabela();

        // controle de botoes
        this.controlaButton(false);

        // limpa o form
        this.limpaForm();

        // exibe a msg
        JOptionPane.showMessageDialog(rootPane, msg);
    }

    private void controlaButton(boolean habilita) {
        btnExcluir.setEnabled(habilita);
        btnNovo.setEnabled(habilita);
    }

    private void limpaForm() {
        this.linha = -1;

        txtNome.setText("");
        jcbTipoPessoa.setSelectedItem("Pessoa Física");
        txtTelefone.setText("");
        txtEmail.setText("");
    }

    private String mascaraCpfCnpj(String fornecedores) {
        String cpfCnpj = "";

        if (fornecedores.length() == 11) {
            cpfCnpj = fornecedores.substring(0, 3) + "." + fornecedores.substring(3, 6) + "."
                + fornecedores.substring(6, 9) + "-" + fornecedores.substring(9, 11);
        } else {
            cpfCnpj = fornecedores.substring(0, 2) + "." + fornecedores.substring(2, 5) + "."
                + fornecedores.substring(5, 8) + "/" + fornecedores.substring(8, 11) + "-"
                + fornecedores.substring(11, 13);
        }

        return cpfCnpj;
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFFornecedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFiltro;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcbTipoPessoa;
    private javax.swing.JTable tblFornecedor;
    private javax.swing.JFormattedTextField txtCpfCnpj;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
