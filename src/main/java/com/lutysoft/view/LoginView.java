package com.lutysoft.view;

import com.lutysoft.controller.LoginController;
import com.lutysoft.model.entiny.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    // --- Cores do Tema (Reutilizando as constantes de estilo) ---
    private static final Color COR_FUNDO_DARK = new Color(30, 30, 30);
    private static final Color COR_PAINEL_DARK = new Color(45, 45, 45);
    private static final Color COR_TEXTO_CLARO = Color.WHITE;
    private static final Color COR_TEXTO_ESCURO = Color.BLACK;
    private static final Color COR_OK = new Color(61, 250, 76); // Verde para Login
    private static final Color COR_CANCELAR = new Color(231, 76, 60); // Vermelho para Cadastrar

    // --- Componentes ---
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private JButton btnCadastrar;

    // Injeção do Controller (Deixado para referência, como nos códigos anteriores)
    private final LoginController loginController;
    public static Usuario usuarioAtual = new Usuario();

    public LoginView() {
        super("Login - QuestTime");

        // 🎯 MODIFICAÇÃO: Aplica o Nimbus LAF para consistência de design
        aplicarTemaNimbus();

        // --- Configurações Básicas do JFrame ---
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null); // Centraliza na tela
        setResizable(false); // Impede o redimensionamento e a maximização da janela

        setLayout(new BorderLayout());
        getContentPane().setBackground(COR_FUNDO_DARK);

        // --- Construção e Estilização ---
        inicializarComponentes();

        // Garante que o Look and Feel (Nimbus Dark) seja aplicado
        SwingUtilities.updateComponentTreeUI(this);

        // --- Eventos e Controller (Deixado para referência) ---
        this.loginController = new LoginController(this);
        events();

        setVisible(true);
    }

    //Escutadores
    private void events(){
        btnLogin.addActionListener(loginController);
        btnCadastrar.addActionListener(loginController);
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
        } catch (Exception e) {
            // Não faz nada se o Nimbus não for encontrado
        }
    }

    private void inicializarComponentes() {
        JPanel painelCentral = new JPanel(new GridLayout(3, 2, 10, 10));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        painelCentral.setBackground(COR_PAINEL_DARK);

        // --- Linha 1: Usuário/Email ---
        JLabel lblUsuario = new JLabel("Usuário:"); // 🎯 CORREÇÃO: Restaurado para "Usuário/Email:"
        lblUsuario.setForeground(COR_TEXTO_CLARO);
        lblUsuario.setFont(lblUsuario.getFont().deriveFont(Font.BOLD, 14f));
        txtUsuario = new JTextField(15);
        txtUsuario.setCaretColor(COR_TEXTO_CLARO); // Cor do cursor

        // --- Linha 2: Senha ---
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(COR_TEXTO_CLARO);
        lblSenha.setFont(lblSenha.getFont().deriveFont(Font.BOLD, 14f));
        txtSenha = new JPasswordField(15);
        txtSenha.setCaretColor(COR_TEXTO_CLARO);

        // --- Linha 3: Botões ---
        btnLogin = new JButton("Entrar");
        btnLogin.setBackground(COR_OK);
        btnLogin.setForeground(COR_TEXTO_ESCURO);
        btnLogin.setFont(btnLogin.getFont().deriveFont(Font.BOLD, 14f));

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(COR_CANCELAR);
        btnCadastrar.setForeground(COR_TEXTO_CLARO);
        btnCadastrar.setFont(btnCadastrar.getFont().deriveFont(Font.BOLD, 14f));

        // Adicionando ao Painel
        painelCentral.add(lblUsuario);
        painelCentral.add(txtUsuario);
        painelCentral.add(lblSenha);
        painelCentral.add(txtSenha);

        // Os botões estão sendo adicionados diretamente ao GridLayout (3x2)
        // Linha 3, Coluna 1:
        painelCentral.add(btnLogin);
        // Linha 3, Coluna 2:
        painelCentral.add(btnCadastrar);

        // Wrapper para centralizar
        JPanel wrapperPainelCentral = new JPanel(new GridBagLayout());
        wrapperPainelCentral.setBackground(COR_FUNDO_DARK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        wrapperPainelCentral.add(painelCentral, gbc);

        add(wrapperPainelCentral, BorderLayout.CENTER);
    }

    // ====================================================================
    // GETTERS PARA O CONTROLLER
    // ====================================================================

    public JTextField getTxtUsuario() { return txtUsuario; }
    public JPasswordField getTxtSenha() { return txtSenha; }
    public JButton getBtnLogin() { return btnLogin; }
    public JButton getBtnCadastrar() { return btnCadastrar; }

    public void fechar() {
        this.dispose();
    }

    // /*
    // private void events() {
    //    // Adiciona o Controller como Listener
    //    btnLogin.addActionListener(loginController);
    //    btnCadastrar.addActionListener(loginController);
    //
    //    // Opcional: Permitir login ao apertar ENTER no campo Senha
    //    txtSenha.addActionListener(loginController);
    // }
    // */
}