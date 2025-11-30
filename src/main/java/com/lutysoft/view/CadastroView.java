package com.lutysoft.view;

import javax.swing.*;
import java.awt.*;

public class CadastroView extends JFrame {

    // --- Cores do Tema ---
    private static final Color COR_FUNDO_DARK = new Color(30, 30, 30);
    private static final Color COR_PAINEL_DARK = new Color(45, 45, 45);
    private static final Color COR_TEXTO_CLARO = Color.WHITE;
    private static final Color COR_TEXTO_ESCURO = Color.BLACK;
    private static final Color COR_OK = new Color(61, 250, 76);
    private static final Color COR_CANCELAR = new Color(231, 76, 60);

    // --- Componentes ---
    private JTextField txtNome;
    private JPasswordField txtSenha;
    private JPasswordField txtConfirmaSenha;
    private JButton btnCadastrar;
    private JButton btnCancelar;

    public CadastroView() {
        super("Cadastro de Usuário - QuestTime");

        // Garante que o Nimbus esteja ativo
        aplicarTemaNimbus();

        // --- Configurações Básicas do JFrame ---
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 300); // Largura ajustada para 500px
        setLocationRelativeTo(null);
        setResizable(false); // Impede o redimensionamento

        setLayout(new BorderLayout());
        getContentPane().setBackground(COR_FUNDO_DARK);

        inicializarComponentes();

        SwingUtilities.updateComponentTreeUI(this);

        setVisible(true);
    }

    // Método auxiliar para aplicar o Nimbus Look and Feel
    private void aplicarTemaNimbus() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) { /* ... */ }
    }

    private void inicializarComponentes() {
        // 4 linhas (Nome, Senha, Confirma Senha, Botões) e 2 colunas
        JPanel painelCentral = new JPanel(new GridLayout(4, 2, 10, 10));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelCentral.setBackground(COR_PAINEL_DARK);

        Font labelFont = new Font("SansSerif", Font.BOLD, 14);

        // --- Linha 1: Nome ---
        JLabel lblNome = new JLabel("Usuário:");
        lblNome.setForeground(COR_TEXTO_CLARO);
        lblNome.setFont(labelFont);
        txtNome = new JTextField(20);
        txtNome.setCaretColor(COR_TEXTO_CLARO);

        // --- Linha 2: Senha ---
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(COR_TEXTO_CLARO);
        lblSenha.setFont(labelFont);
        txtSenha = new JPasswordField(20);
        txtSenha.setCaretColor(COR_TEXTO_CLARO);

        // --- Linha 3: Confirmação de Senha ---
        JLabel lblConfirmaSenha = new JLabel("Confirme a Senha:");
        lblConfirmaSenha.setForeground(COR_TEXTO_CLARO);
        lblConfirmaSenha.setFont(labelFont);
        txtConfirmaSenha = new JPasswordField(20);
        txtConfirmaSenha.setCaretColor(COR_TEXTO_CLARO);

        // --- Linha 4: Botões ---

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(COR_OK);
        btnCadastrar.setForeground(COR_TEXTO_ESCURO);
        btnCadastrar.setFont(btnCadastrar.getFont().deriveFont(Font.BOLD, 14f));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(COR_CANCELAR);
        btnCancelar.setForeground(COR_TEXTO_CLARO);
        btnCancelar.setFont(btnCancelar.getFont().deriveFont(Font.BOLD, 14f));

        // Adicionando Campos (Linhas 1, 2, 3)
        painelCentral.add(lblNome);
        painelCentral.add(txtNome);
        painelCentral.add(lblSenha);
        painelCentral.add(txtSenha);
        painelCentral.add(lblConfirmaSenha);
        painelCentral.add(txtConfirmaSenha);

        // Painel auxiliar que centraliza os dois botões (Cadastrar, Cancelar)
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        painelBotoes.setBackground(COR_PAINEL_DARK);

        // Ordem invertida: Cadastrar (Verde) e depois Cancelar (Vermelho)
        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnCancelar);

        // Adicionando a Linha 4 ao GridLayout:
        painelCentral.add(new JLabel("")); // Coluna 1: Célula vazia
        painelCentral.add(painelBotoes);   // Coluna 2: Painel com os dois botões

        // Wrapper para centralizar o painel de formulário no Frame
        JPanel wrapperPainelCentral = new JPanel(new GridBagLayout());
        wrapperPainelCentral.setBackground(COR_FUNDO_DARK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        wrapperPainelCentral.add(painelCentral, gbc);

        add(wrapperPainelCentral, BorderLayout.CENTER);
    }

    // ====================================================================
    // GETTERS
    // ====================================================================

    public JTextField getTxtNome() { return txtNome; }
    public JPasswordField getTxtSenha() { return txtSenha; }
    public JPasswordField getTxtConfirmaSenha() { return txtConfirmaSenha; }
    public JButton getBtnCadastrar() { return btnCadastrar; }
    public JButton getBtnCancelar() { return btnCancelar; }

    public void fechar() {
        this.dispose();
    }
}