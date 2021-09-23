package interfaceApp;

import bean.Produto;
import dao.ProdutoDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class JFProduto extends javax.swing.JFrame {

    // armazena a linha clicada na tabela
    private int linha = -1;

    // coleção de objetos da classe produto
    ArrayList<Produto> produtos = new ArrayList<>();

    public JFProduto() {
        initComponents();
        
        // centraliza o form
        setLocationRelativeTo(null);
        
        this.preencheTabela();
    }

    private void preencheTabela() {
        DefaultTableModel model = (DefaultTableModel) tblProduto.getModel();

        //limpa a tabela
        model.setRowCount(0);

        // lista todos os produtos 
        ProdutoDAO dao = new ProdutoDAO();
        this.produtos = dao.liste();

        // percorre cada objeto de produto
        for (int i = 0; i < this.produtos.size(); i++) {
            model.addRow(new String[]{
                this.produtos.get(i).getDescricao(),
                Double.toString(this.produtos.get(i).getPrecoCusto()).replace(".", ","),
                Double.toString(this.produtos.get(i).getPrecoVenda()).replace(".", ","),
                Integer.toString(this.produtos.get(i).getQuantidade())
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPrecoVenda = new javax.swing.JFormattedTextField();
        txtQuantidade = new javax.swing.JFormattedTextField();
        btnGravar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnFiltrar = new javax.swing.JButton();
        txtPrecoCusto = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduto = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Descrição");

        jLabel2.setText("Preço Custo");

        jLabel3.setText("Preço Venda");

        jLabel4.setText("Quantidade");

        txtPrecoVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        txtQuantidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        btnGravar.setBackground(new java.awt.Color(82, 144, 238));
        btnGravar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGravar.setText("Gravar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

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

        btnFiltrar.setBackground(new java.awt.Color(109, 105, 110));
        btnFiltrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        txtPrecoCusto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        tblProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Preço Custo", "Preço Venda", "Quantidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduto);
        if (tblProduto.getColumnModel().getColumnCount() > 0) {
            tblProduto.getColumnModel().getColumn(0).setPreferredWidth(500);
        }

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pacotes (1).png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(21, 21, 21))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnGravar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnExcluir)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnNovo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnFiltrar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(txtPrecoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(txtPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(40, 40, 40))
                                    .addComponent(txtPrecoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(40, 40, 40))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFiltrar)
                            .addComponent(btnNovo)
                            .addComponent(btnExcluir)
                            .addComponent(btnGravar)))
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed

        int id = -1;

        // verifica o modo de operação do form
        if (this.linha != -1) {
            id = this.produtos.get(this.linha).getProdutoId();
        }

        // cria um objeto da classe produto
        Produto produto = new Produto(
                id,
                txtDescricao.getText(),
                Double.parseDouble(txtPrecoCusto.getText().replace(",", ".")),
                Double.parseDouble(txtPrecoVenda.getText().replace(",", ".")),
                Integer.parseInt(txtQuantidade.getText())
        );

        // cria um objeto da classe DAO
        ProdutoDAO dao = new ProdutoDAO();

        // registra o produto no bando e recupera a msg
        String msg = dao.grave(produto);

        // preenche a tabela novamente 
        this.preencheTabela();

        // limpa o formulário
        this.limpeFormulario();

        // desabilita os botões
        this.controleButton(false);
        // exibe a mensagem que retornou da procedure
        JOptionPane.showMessageDialog(rootPane, msg);
    }//GEN-LAST:event_btnGravarActionPerformed

    private void tblProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoMouseClicked
        // determina a linha clicada
        this.linha = tblProduto.rowAtPoint(evt.getPoint());

        // preenche o formulário com o objeto clicado
        txtDescricao.setText(
                this.produtos.get(this.linha).getDescricao()
        );
        txtPrecoCusto.setText(
                Double.toString(this.produtos.get(
                        this.linha).getPrecoCusto()
                ).replace(",", ".")
        );
        txtPrecoVenda.setText(
                Double.toString(
                        this.produtos.get(this.linha).getPrecoVenda()
                ).replace(",", ".")
        );
        txtQuantidade.setText(
                Integer.toString(
                        this.produtos.get(this.linha).getQuantidade()
                )
        );

        // habilita os botões
        this.controleButton(true);
    }//GEN-LAST:event_tblProdutoMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // pergunta ao usuario se ele realmente deseja exluir o registro
        if (JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente remover o registro?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            /// cria um objeto DAO 
            ProdutoDAO dao = new ProdutoDAO();

            // remove o registro e recupera a msg
            String msg = dao.remova(this.produtos.get(this.linha).getProdutoId());

            // lista todos os produtos atualizados
            this.preencheTabela();

            // limpa o formulario
            this.limpeFormulario();

            // desabilita os botões
            this.controleButton(false);

            // exibe a msg da procedure
            JOptionPane.showMessageDialog(rootPane, msg);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // valida a descricao 
        if (txtDescricao.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Informe a descrição para filtrar.");
            return;
        }

        // cria objeto da classe dao
        ProdutoDAO dao = new ProdutoDAO();
        this.produtos = dao.pesquise(txtDescricao.getText());

        // recupera o objeto visual da tabela
        DefaultTableModel model = (DefaultTableModel) tblProduto.getModel();

        // limpa as linhas da tabela
        model.setRowCount(0);

        // percorre o arraylist e preenche ele
        for (int i = 0; i < this.produtos.size(); i++) {
            model.addRow(new String[]{
                this.produtos.get(i).getDescricao(),
                Double.toString(this.produtos.get(i).getPrecoCusto()).replace(".", ","),
                Double.toString(this.produtos.get(i).getPrecoVenda()).replace(".", ","),
                Integer.toString(this.produtos.get(i).getQuantidade())
            });
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        this.limpeFormulario();
        this.controleButton(false);
        this.preencheTabela();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void limpeFormulario() {
        this.linha = -1;

        //limpa o formulário
        txtDescricao.setText("");
        txtPrecoCusto.setText("");
        txtPrecoVenda.setText("");
        txtQuantidade.setText("");
    }

    private void controleButton(boolean habilita) {
        btnExcluir.setEnabled(habilita);
        btnNovo.setEnabled(habilita);
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
            java.util.logging.Logger.getLogger(JFProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFProduto().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProduto;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JFormattedTextField txtPrecoCusto;
    private javax.swing.JFormattedTextField txtPrecoVenda;
    private javax.swing.JFormattedTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables

}
