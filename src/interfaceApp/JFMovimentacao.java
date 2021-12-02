package interfaceApp;

import bean.Cliente;
import bean.Fornecedor;
import bean.Movimentacao;
import bean.Produto;
import dao.ClienteDAO;
import dao.FornecedorDAO;
import dao.MovimentacaoDAO;
import dao.ProdutoDAO;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class JFMovimentacao extends javax.swing.JFrame {

    ArrayList<Movimentacao> movimentacoes = new ArrayList<>();

    // coleção de objetos de produto, cliente e fornecedor
    ArrayList<Produto> produtos = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Fornecedor> fornecedores = new ArrayList<>();

    // -1 é inserção e diferente disso é atualização ou remoção
    private int linha = -1;

    public JFMovimentacao() {
        initComponents();

        // centraliza os componentes
        setLocationRelativeTo(null);

        // preenche inicialmente os comboboxes de produto
        this.preenchaComboBox();

        // preenche no formulário o combobox com informações de clientes
        this.preenchaComboBoxClientes();

        // preenche a tabela de movimentação
        this.preenchaTabela();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        btnFiltrar = new javax.swing.JButton();
        jcbStatus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMovimentacao = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtQuantidadeMovimentacao = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtValor = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtValorUnitario = new javax.swing.JFormattedTextField();
        btnGravar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jcbProduto = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jcbTipo = new javax.swing.JComboBox<>();
        lblTipo = new javax.swing.JLabel();
        jcbClienteFornecedor = new javax.swing.JComboBox<>();
        btnExcluir = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtEstoque = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel4.setText("Status");

        btnFiltrar.setBackground(new java.awt.Color(109, 105, 110));
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        jcbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aberto", "Quitado" }));

        tblMovimentacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Produto", "Cliente/Fornecedor", "Status", "Quantidade", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMovimentacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMovimentacaoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMovimentacao);

        jLabel5.setText("Quantidade");

        txtQuantidadeMovimentacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel6.setText("Valor");

        txtValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtValor.setEnabled(false);

        jLabel7.setText("Estoque");

        txtValorUnitario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtValorUnitario.setEnabled(false);

        btnGravar.setBackground(new java.awt.Color(82, 144, 238));
        btnGravar.setText("Gravar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        jLabel1.setText("Produto");

        jcbProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbProdutoActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo");

        jcbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Venda", "Compra" }));
        jcbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTipoActionPerformed(evt);
            }
        });

        lblTipo.setText("Cliente/Fornecedor");

        btnExcluir.setBackground(new java.awt.Color(200, 41, 41));
        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnNovo.setBackground(new java.awt.Color(95, 219, 95));
        btnNovo.setText("Novo");
        btnNovo.setEnabled(false);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jLabel3.setText("Valor Unitário");

        txtEstoque.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtEstoque.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFiltrar))
                    .addComponent(jLabel4)
                    .addComponent(jcbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTipo)
                                    .addComponent(jcbClienteFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtQuantidadeMovimentacao, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(37, 37, 37)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(txtEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbClienteFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtQuantidadeMovimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGravar)
                    .addComponent(btnExcluir)
                    .addComponent(btnNovo)
                    .addComponent(btnFiltrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        this.limpaForm();
        this.controlaButton(false);
        this.preenchaTabela();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {
        // cria obj da classe dao
        MovimentacaoDAO dao = new MovimentacaoDAO();
        
        this.movimentacoes = dao.pesquise(jcbProduto.getSelectedItem().toString());

        // recupera o obj visual da tabela
        DefaultTableModel model = (DefaultTableModel) tblMovimentacao.getModel();

        // limpa a tabela
        model.setRowCount(0);

        for (int i = 0; i < this.movimentacoes.size(); i++) {

            String tipo = "";
            if (this.movimentacoes.get(i).getTipo().equals("S")) {
                tipo = "Venda";
            } else {
                tipo = "Compra";
            }

            String status = "";

            if (this.movimentacoes.get(i).getStatus().equals("Q")) {
                status = "Quitado";
            } else {
                status = "Aberto";
            }
            // adiciona uma linha na tabela
            model.addRow(new String[]{
                this.movimentacoes.get(i).getTipo(),
                this.movimentacoes.get(i).getProduto(),
                this.movimentacoes.get(i).getNome(),
                this.movimentacoes.get(i).getStatus(),
                Integer.toString(this.movimentacoes.get(i).getQuantidadeMovimentacao()),
                Double.toString(this.movimentacoes.get(i).getValor())
            });
        }
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        // pergunta o usuario se realmente deseja excluir o registro
        if (JOptionPane.showConfirmDialog(this, "Deseja realmente exlcuír o registro?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            // cria um obj da classe DAO
            MovimentacaoDAO dao = new MovimentacaoDAO();

            //remove o registro do banco e recupera a msg
            String msg = dao.remove(this.movimentacoes.get(this.linha).getMovimentacaoId());

            // atualiza a tabela
            this.preenchaTabela();

            this.limpaForm();

            this.controlaButton(false);

            this.preenchaComboBox();

            JOptionPane.showMessageDialog(rootPane, msg);
        }
    }

    private void jcbProdutoActionPerformed(java.awt.event.ActionEvent evt) {
        if (jcbProduto.getSelectedIndex() - 1 >= 0) {
            txtEstoque.setText(
                    Integer.toString(
                            this.produtos.get(jcbProduto.getSelectedIndex() - 1).getQuantidade()
                    )
            );

            txtValorUnitario.setText(
                    Double.toString(
                            this.produtos.get(jcbProduto.getSelectedIndex() - 1).getPrecoVenda()
                    ).replace(".", ",")
            );
        }
    }

    private void tblMovimentacaoMouseClicked(java.awt.event.MouseEvent evt) {
        // determina qual foi a linha clicada na tabela
        this.linha = tblMovimentacao.rowAtPoint(evt.getPoint());

        // seta os campos do form com os atributos do obj de cliente selecionado
        jcbProduto.setSelectedItem(this.movimentacoes.get(this.linha).getProduto());

        // determina o valor do JComboBox de tipo pessoas
        if (this.movimentacoes.get(this.linha).getTipo().equals("S")) {
            jcbTipo.setSelectedItem("Venda");
        } else {
            jcbTipo.setSelectedItem("Compra");
        }

        // seta os campos do form com os atributos do obj de cliente selecionado
        jcbClienteFornecedor.setSelectedItem(this.movimentacoes.get(this.linha).getNome());

        if (this.movimentacoes.get(this.linha).getStatus().equals("Q")) {
            jcbStatus.setSelectedItem("Quitado");
        } else {
            jcbTipo.setSelectedItem("Aberto");
        }

        txtQuantidadeMovimentacao.setText(Integer.toString(this.movimentacoes.get(this.linha).getQuantidadeMovimentacao()));
        txtValor.setText(Double.toString(this.movimentacoes.get(this.linha).getValor()).replace(".", ","));

        this.controlaButton(true);
    }

    private void preenchaComboBox() {

        // lista os produtos do banco de dados para a coleção de objetos da classe
        ProdutoDAO produtoDao = new ProdutoDAO();
        this.produtos = produtoDao.liste();

        // remove todos os itens de produto do combobox
        jcbProduto.removeAllItems();
        jcbProduto.addItem("Selecione o produto");

        // laço de repetição para percorrer a coleção de objetos de produto por meio de
        // um iterador
        for (Iterator<Produto> produto = this.produtos.iterator(); produto.hasNext();) {
            // a cada produto na coleção de objetos inclui um item no ComboBox
            jcbProduto.addItem(produto.next().getDescricao());
        }
    }

    private void preenchaComboBoxClientes() {

        // lista os clientes do banco de dados para a coleção de objetos da classe
        ClienteDAO clienteDao = new ClienteDAO();
        this.clientes = clienteDao.liste();

        // remove todos os itens de cliente do combobox
        jcbClienteFornecedor.removeAllItems();
        jcbClienteFornecedor.addItem("Selecione o cliente");

        // laço de repetição para percorrer a coleção de objetos de cliente por meio de
        // um iterador
        for (Iterator<Cliente> cliente = this.clientes.iterator(); cliente.hasNext();) {
            // a cada cliente na coleção de objetos inclui um item no Combobox
            jcbClienteFornecedor.addItem(cliente.next().getNome());
        }
    }

    private void preenchaComboBoxFornecedores() {
        // lista os fornecedores do banco de dados para a coleção de objetos da classe
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        this.fornecedores = fornecedorDAO.liste();

        // remove todos os itens de fornecedor do combobox
        jcbClienteFornecedor.removeAllItems();
        jcbClienteFornecedor.addItem("Selecione o fornecedor");

        // laço de repetição para percorrer a coleção de objetos de fornecedor por meio
        // de um iterador
        for (Iterator<Fornecedor> fornecedor = this.fornecedores.iterator(); fornecedor.hasNext();) {
            // a cada fornecedor na coleção de objetos inclui um item no Combobox
            jcbClienteFornecedor.addItem(fornecedor.next().getNome());
        }
    }

    private void preenchaTabela() {

        // recpera o objeto visual da tabela
        DefaultTableModel model = (DefaultTableModel) tblMovimentacao.getModel();

        // limpa a tabela
        model.setRowCount(0);

        // cria um objeto da classe MovimentacaoDAO
        MovimentacaoDAO dao = new MovimentacaoDAO();

        // lista as movimentações para a coleção de objetos
        this.movimentacoes = dao.liste();

        for (int i = 0; i < this.movimentacoes.size(); i++) {

            String tipo = "";

            if (this.movimentacoes.get(i).getTipo().equals("S")) {
                tipo = "Venda";
            } else {
                tipo = "Compra";
            }

            String status = "";

            if (this.movimentacoes.get(i).getStatus().equals("Q")) {
                status = "Quitado";
            } else {
                status = "Aberto";
            }
            // adiciona uma linha na tabela
            model.addRow(new String[]{
                tipo,
                this.movimentacoes.get(i).getProduto(),
                this.movimentacoes.get(i).getNome(),
                status,
                Integer.toString(this.movimentacoes.get(i).getQuantidadeMovimentacao()),
                Double.toString(this.movimentacoes.get(i).getValor()).replace(".", ",")
            });
        }
    }

    public void limpaForm() {
        this.linha = -1;
        jcbProduto.setSelectedIndex(0);
        jcbStatus.setSelectedIndex(0);
        jcbTipo.setSelectedIndex(0);
        jcbClienteFornecedor.setSelectedIndex(0);
        txtQuantidadeMovimentacao.setText("");
        txtValor.setText("");

        txtValorUnitario.setText("");
        txtValorUnitario.setText("");
    }

    private void controlaButton(boolean habilita) {
        btnExcluir.setEnabled(habilita);
        btnNovo.setEnabled(habilita);
    }

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {

        int id = -1;

        // verifica o modo de operação do form
        if (this.linha != -1) {
            id = this.movimentacoes.get(this.linha).getMovimentacaoId();
        }

        // recupera o valor da chave primária do produto selecionado
        int produtoId = this.produtos.get(jcbProduto.getSelectedIndex() - 1).getProdutoId();

        // recupera o valor da chave primária do cliente selecionado
        int clienteId = this.clientes.get(jcbClienteFornecedor.getSelectedIndex() - 1).getPessoaId();

        String tipo = "S";

        if (jcbTipo.getSelectedItem().equals("Compra")) {
            tipo = "E";
        }

        String status = "A";

        if (jcbStatus.getSelectedItem().equals("Quitado")) {
            status = "Q";
        }

        int quantidade = Integer.parseInt(txtQuantidadeMovimentacao.getText());
        double preco = 0;

        if (jcbTipo.getSelectedItem().equals("Venda")) {
            preco = produtos.get(jcbProduto.getSelectedIndex() - 1).getPrecoVenda();
        } else {
            preco = produtos.get(jcbProduto.getSelectedIndex() - 1).getPrecoCusto();
        }

        double valor = quantidade * preco;

        txtValor.setText(Double.toString(valor));

        Movimentacao movimentacao = new Movimentacao(
                id,
                produtoId,
                "",
                clienteId,
                "",
                tipo,
                null,
                status,
                quantidade,
                valor);

        // cria um obj da classe DAO
        MovimentacaoDAO dao = new MovimentacaoDAO();

        // registra a movimentacao e recupera a msg
        String msg = dao.grave(movimentacao);

        this.preenchaTabela();

        this.limpaForm();

        this.controlaButton(false);

        this.preenchaComboBox();

        txtValorUnitario.setText("");

        JOptionPane.showMessageDialog(rootPane, msg);
    }

    private void jcbTipoActionPerformed(java.awt.event.ActionEvent evt) {
        if (jcbTipo.getSelectedItem().equals("Venda")) {
            this.preenchaComboBoxClientes();
        } else {
            this.preenchaComboBoxFornecedores();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
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
            java.util.logging.Logger.getLogger(JFMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFMovimentacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbClienteFornecedor;
    private javax.swing.JComboBox<String> jcbProduto;
    private javax.swing.JComboBox<String> jcbStatus;
    private javax.swing.JComboBox<String> jcbTipo;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTable tblMovimentacao;
    private javax.swing.JFormattedTextField txtEstoque;
    private javax.swing.JFormattedTextField txtQuantidadeMovimentacao;
    private javax.swing.JFormattedTextField txtValor;
    private javax.swing.JFormattedTextField txtValorUnitario;
    // End of variables declaration//GEN-END:variables
}
