package com.lutysoft.view;

import com.lutysoft.controller.TarefaController;

import javax.swing.*;
import java.awt.*;

public class TarefaView extends JFrame {
    //Injeção do controller
    private final TarefaController tarefaController;
    //Injeção view Relatorio
    private RelatorioView relatorioView;
    //Botoes
    private JButton btnNova;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnOk;
    private JButton btnIniciar;
    private JButton btnFinalizar;

    //Text
    private JTextField txtIdTarefa;
    private JTextField txtNome;
    private JTextArea txtDescricao;

    //Label de Consulta
    private JLabel lblStatus;

    private JButton btnCancelar;
    // --- Cores do Tema Dark (Mantidas para uso específico, mas as bases estão no UIManager) ---
    private static final Color COR_FUNDO_DARK = new Color(30, 30, 30);
    private static final Color COR_PAINEL_DARK = new Color(45, 45, 45);
    private static final Color COR_TEXTO_CLARO = Color.WHITE;
    private static final Color COR_TEXTO_ESCURO = Color.BLACK;

    // --- Cores de Ação (Ainda usadas via setBackground para botões coloridos) ---
    private static final Color COR_OK = new Color(61, 250, 76);
    private static final Color COR_CANCELAR = new Color(231, 76, 60);
    private static final Color COR_INICIAR = new Color(59, 246, 168);
    private static final Color COR_FINALIZAR = new Color(237, 116, 9);
    // COR_PADRAO_ESCURA não é mais estritamente necessária, mas mantida para consistência de código.
    private static final Color COR_PADRAO_ESCURA = new Color(60, 60, 60);

    // Contêiner principal para alternar telas
    private JPanel cardPanel;
    private CardLayout cardLayout;

    private static final String GERENCIADOR_TAREFAS = "GerenciadorTarefas";
    private static final String RELATORIOS = "Relatorios";


    public TarefaView() {
        super("QuestTime");

        // 1. APLICA O TEMA DARK DO NIMBUS
        aplicarTemaNimbusDark();

        try {
            // 2. APLICA O NIMBUS LOOK AND FEEL
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Se Nimbus falhar, tenta usar o sistema ou o Metal (padrão)
        }

        // --- Configurações Básicas do JFrame ---
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);

        // O conteúdo principal ainda usa a cor DARK_FUNDO
        getContentPane().setBackground(COR_FUNDO_DARK);

        criarMenuPrincipal();

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(COR_FUNDO_DARK);

        relatorioView = new RelatorioView();
        cardPanel.add(criarPainelGerenciador(), GERENCIADOR_TAREFAS);
        cardPanel.add(relatorioView, RELATORIOS);

        add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, GERENCIADOR_TAREFAS);

        // Garante que a interface seja reconstruída com o novo L&F e tamanhos
        SwingUtilities.updateComponentTreeUI(this);

        setVisible(true);
        this.tarefaController = new TarefaController(this);
        events();
        layoutPadrao();
    }

    public void layoutPadrao(){
        btnNova.setEnabled(true);
        btnNova.setVisible(true);
        btnAlterar.setEnabled(false);
        btnAlterar.setVisible(true);
        btnExcluir.setEnabled(false);
        btnExcluir.setVisible(true);
        btnIniciar.setEnabled(false);
        btnFinalizar.setEnabled(false);
        txtNome.setEnabled(false);
        txtDescricao.setEnabled(false);
        txtIdTarefa.setEnabled(true);
        txtIdTarefa.setFocusable(true);
        lblStatus.setVisible(true);

        txtIdTarefa.setText("");
        txtNome.setText("");
        txtDescricao.setText("");


        btnOk.setVisible(false);
        btnCancelar.setVisible(false);
        //btnIniciar.setVisible(false);
        //btnFinalizar.setVisible(false);
    }

    public void events(){

        btnNova.addActionListener(tarefaController);
        btnAlterar.addActionListener(tarefaController);
        btnExcluir.addActionListener(tarefaController);
        btnOk.addActionListener(tarefaController);
        btnCancelar.addActionListener(tarefaController);
        btnIniciar.addActionListener(tarefaController);
        btnFinalizar.addActionListener(tarefaController);
        txtIdTarefa.addActionListener(tarefaController);

    }

    /**
     * Define as propriedades do Nimbus para criar um tema Dark Mode.
     * Isso precisa ser chamado ANTES de setLookAndFeel.
     */
    private void aplicarTemaNimbusDark() {
        Color nimbusBase = new Color(30, 30, 30);
        Color nimbusBaseClaro = new Color(45, 45, 45);
        Color textoClaro = Color.WHITE;

        // Configurações de Cores Base
        UIManager.put("control", nimbusBaseClaro);
        UIManager.put("nimbusBase", nimbusBase);
        UIManager.put("nimbusFocus", new Color(100, 100, 100));

        // Backgrounds
        UIManager.put("Panel.background", nimbusBaseClaro);
        UIManager.put("Desktop.background", nimbusBaseClaro);
        UIManager.put("OptionPane.background", nimbusBaseClaro);

        // Texto e Entradas
        UIManager.put("text", textoClaro);
        UIManager.put("TextField.background", COR_PADRAO_ESCURA);
        UIManager.put("TextField.foreground", textoClaro);
        UIManager.put("TextArea.background", COR_PADRAO_ESCURA);
        UIManager.put("TextArea.foreground", textoClaro);

        // Menu
        UIManager.put("MenuBar.background", nimbusBaseClaro);
        UIManager.put("Menu.background", nimbusBaseClaro);
        UIManager.put("Menu.foreground", textoClaro);
        UIManager.put("MenuItem.background", COR_PADRAO_ESCURA);
        UIManager.put("MenuItem.foreground", textoClaro);
        UIManager.put("PopupMenu.background", COR_PADRAO_ESCURA);

        // JScrollPane (para a descrição)
        UIManager.put("ScrollPane.background", COR_PAINEL_DARK);
        UIManager.put("Viewport.background", COR_PADRAO_ESCURA);

        // Botões (Cores padrão são herdadas, mas cores de ação serão sobrescritas)
        UIManager.put("Button.background", COR_PADRAO_ESCURA);
        UIManager.put("Button.foreground", textoClaro);
    }

    // ====================================================================
    // I. MENU BAR (Simplificado graças ao Nimbus)
    // ====================================================================
    private void criarMenuPrincipal() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(COR_PAINEL_DARK);

        // === Menu Gerenciador ===
        JMenu menuGerenciador = new JMenu("Gerenciador");
        menuGerenciador.setForeground(COR_TEXTO_CLARO);

        JMenuItem itemTarefas = new JMenuItem("Tarefas");
        itemTarefas.addActionListener(e -> cardLayout.show(cardPanel, GERENCIADOR_TAREFAS));

        menuGerenciador.add(itemTarefas);
        menuBar.add(menuGerenciador);

        // === Menu Relatórios ===
        JMenu menuRelatorios = new JMenu("Relatórios");
        menuRelatorios.setForeground(COR_TEXTO_CLARO);

        JMenuItem itemRelatoriosTarefas = new JMenuItem("Relatório por tarefas");
        itemRelatoriosTarefas.addActionListener(e -> cardLayout.show(cardPanel, RELATORIOS));

        menuRelatorios.add(itemRelatoriosTarefas);
        menuBar.add(menuRelatorios);

        setJMenuBar(menuBar);
    }


    // ====================================================================
    // III. TELA GERENCIADOR DE TAREFAS
    // ====================================================================
    private JPanel criarPainelGerenciador() {
        JPanel painelGerenciador = new JPanel(new BorderLayout(10, 10));
        painelGerenciador.setBackground(COR_FUNDO_DARK);
        painelGerenciador.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelGerenciador.add(criarPainelSuperiorAcoes(), BorderLayout.NORTH);

        JPanel painelConteudoCentral = new JPanel();
        painelConteudoCentral.setLayout(new BoxLayout(painelConteudoCentral, BoxLayout.Y_AXIS));
        painelConteudoCentral.setBackground(COR_FUNDO_DARK);

        painelConteudoCentral.add(criarPainelFormularioEdicao());

        // Revertido: Adiciona o GLUE de volta para empurrar o painel de edição para o topo
        // e deixar o restante do espaço vazio abaixo (onde a tabela provavelmente irá).
        painelConteudoCentral.add(Box.createVerticalGlue());

        painelGerenciador.add(painelConteudoCentral, BorderLayout.CENTER);

        return painelGerenciador;
    }

    // ====================================================================
    // IV. PAINEL SUPERIOR DE AÇÕES (Botões e Consulta ID)
    // ====================================================================
    private JPanel criarPainelSuperiorAcoes() {
        JPanel painelSuperior = new JPanel(new BorderLayout(0, 10));
        painelSuperior.setBackground(COR_PAINEL_DARK);

        // 1. BARRA DE BOTÕES (NORTH)
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        painelBotoes.setBackground(COR_PAINEL_DARK);

        btnNova = new JButton("Nova Tarefa");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnOk = new JButton("Salvar Tarefa");
        btnCancelar = new JButton("Cancelar");


        // Aplica cores de ação
        btnOk.setBackground(COR_OK);
        btnOk.setForeground(COR_TEXTO_ESCURO);

        btnCancelar.setBackground(COR_CANCELAR);
        btnCancelar.setForeground(COR_TEXTO_CLARO);

        btnNova.setBackground(COR_INICIAR);
        btnNova.setForeground(COR_TEXTO_ESCURO);

        btnAlterar.setBackground(COR_FINALIZAR);
        btnAlterar.setForeground(COR_TEXTO_CLARO);

        btnExcluir.setBackground(COR_CANCELAR);
        btnExcluir.setForeground(COR_TEXTO_CLARO);


        painelBotoes.add(btnNova);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnOk);
        painelBotoes.add(btnCancelar);

        painelSuperior.add(painelBotoes, BorderLayout.NORTH);

        // 2. BLOCO DE CONSULTA ID E STATUS (CENTER)
        JPanel painelConsulta = new JPanel(new BorderLayout(10, 5));
        painelConsulta.setBackground(COR_PAINEL_DARK);

        // Sub-painel para ID (LEFT)
        JPanel painelId = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        painelId.setBackground(COR_PAINEL_DARK);

        JLabel lblId = new JLabel("ID da Tarefa:");
        lblId.setForeground(COR_TEXTO_CLARO);
        lblId.setFont(lblId.getFont().deriveFont(Font.BOLD, 14f));
        painelId.add(lblId);

        txtIdTarefa = new JTextField(5);
        txtIdTarefa.setPreferredSize(new Dimension(50, 30));
        txtIdTarefa.setCaretColor(COR_TEXTO_CLARO);
        painelId.add(txtIdTarefa);

        painelConsulta.add(painelId, BorderLayout.WEST);


        // Sub-painel para Status (RIGHT)
        JPanel painelStatus = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        painelStatus.setBackground(COR_PAINEL_DARK);

        lblStatus = new JLabel("Status: Aguardando consulta...");
        lblStatus.setForeground(COR_INICIAR);
        lblStatus.setFont(lblStatus.getFont().deriveFont(Font.BOLD, 14f));
        painelStatus.add(lblStatus);

        painelConsulta.add(painelStatus, BorderLayout.CENTER);

        painelSuperior.add(painelConsulta, BorderLayout.CENTER);

        return painelSuperior;
    }

    // ====================================================================
    // V. PAINEL DE CADASTRO/EDIÇÃO (Detalhes da Tarefa) - Labels Maiores, Layout Fixo
    // ====================================================================
    private JPanel criarPainelFormularioEdicao() {
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new BoxLayout(painelFormulario, BoxLayout.Y_AXIS));
        painelFormulario.setBackground(COR_PAINEL_DARK);

        painelFormulario.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Revertido: Mantendo as restrições de tamanho fixo do layout original
        painelFormulario.setMaximumSize(new Dimension(580, 500));
        painelFormulario.setPreferredSize(new Dimension(600, 350));
        // Revertido: Tamanho máximo do painelFormulario (para quem usa BoxLayout no pai)
        painelFormulario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 700));

        painelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 70)),
                "Detalhes da Tarefa",
                0, 0,
                painelFormulario.getFont().deriveFont(Font.BOLD, 14f),
                COR_TEXTO_CLARO
        ));

        int preferredWidth = 500;

        // A. NOME DA TAREFA
        JLabel lblNome = new JLabel("Nome da Tarefa");
        lblNome.setForeground(COR_TEXTO_CLARO);
        lblNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        // ALTERAÇÃO: Aumentando a fonte para 16f
        lblNome.setFont(lblNome.getFont().deriveFont(Font.BOLD, 16f));

        txtNome = new JTextField(20);
        txtNome.setMaximumSize(new Dimension(preferredWidth, 35));
        txtNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtNome.setCaretColor(COR_TEXTO_CLARO);

        // B. DESCRIÇÃO
        JLabel lblDescricao = new JLabel("Descrição");
        lblDescricao.setForeground(COR_TEXTO_CLARO);
        lblDescricao.setAlignmentX(Component.CENTER_ALIGNMENT);
        // ALTERAÇÃO: Aumentando a fonte para 16f
        lblDescricao.setFont(lblDescricao.getFont().deriveFont(Font.BOLD, 16f));

        txtDescricao = new JTextArea(5, 30);
        txtDescricao.setCaretColor(COR_TEXTO_CLARO);

        JScrollPane scrollDescricao = new JScrollPane(txtDescricao);
        scrollDescricao.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70)));
        // Revertido: Restringindo a altura máxima para 150 pixels (layout original)
        scrollDescricao.setMaximumSize(new Dimension(preferredWidth, 150));
        scrollDescricao.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollDescricao.getViewport().setBackground(COR_PADRAO_ESCURA);


        // C. BOTÕES DE AÇÃO ESPECÍFICA (INICIAR/FINALIZAR)
        JPanel painelAcoesTempo = new JPanel(new GridLayout(1, 2, 10, 0));
        painelAcoesTempo.setBackground(COR_PAINEL_DARK);
        painelAcoesTempo.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        btnIniciar = new JButton("Iniciar Tarefa");
        btnFinalizar = new JButton("Finalizar Tarefa");

        btnIniciar.setFont(btnIniciar.getFont().deriveFont(Font.BOLD, 14f));
        btnIniciar.setBackground(COR_INICIAR);
        btnIniciar.setForeground(COR_TEXTO_ESCURO);

        btnFinalizar.setFont(btnFinalizar.getFont().deriveFont(Font.BOLD, 14f));
        btnFinalizar.setBackground(COR_FINALIZAR);
        btnFinalizar.setForeground(COR_TEXTO_CLARO);

        painelAcoesTempo.add(btnIniciar);
        painelAcoesTempo.add(btnFinalizar);

        // Adiciona todos os componentes ao formulário
        painelFormulario.add(Box.createVerticalStrut(10));
        painelFormulario.add(lblNome);
        painelFormulario.add(Box.createVerticalStrut(5));
        painelFormulario.add(txtNome);
        painelFormulario.add(Box.createVerticalStrut(15));
        painelFormulario.add(lblDescricao);
        painelFormulario.add(Box.createVerticalStrut(5));
        painelFormulario.add(scrollDescricao);
        painelFormulario.add(Box.createVerticalStrut(20));

        JPanel wrapperAcoes = new JPanel(new BorderLayout());
        wrapperAcoes.setBackground(COR_PAINEL_DARK);
        wrapperAcoes.setMaximumSize(new Dimension(preferredWidth, 50));
        wrapperAcoes.add(painelAcoesTempo, BorderLayout.CENTER);

        painelFormulario.add(wrapperAcoes);
        painelFormulario.add(Box.createVerticalStrut(10));

        return painelFormulario;
    }

    // Get and Setters


    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JTextArea getTxtDescricao() {
        return txtDescricao;
    }

    public void setTxtDescricao(JTextArea txtDescricao) {
        this.txtDescricao = txtDescricao;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(JTextField txtNome) {
        this.txtNome = txtNome;
    }

    public JTextField getTxtIdTarefa() {
        return txtIdTarefa;
    }

    public void setTxtIdTarefa(JTextField txtIdTarefa) {
        this.txtIdTarefa = txtIdTarefa;
    }

    public JButton getBtnFinalizar() {
        return btnFinalizar;
    }

    public void setBtnFinalizar(JButton btnFinalizar) {
        this.btnFinalizar = btnFinalizar;
    }

    public JButton getBtnIniciar() {
        return btnIniciar;
    }

    public void setBtnIniciar(JButton btnIniciar) {
        this.btnIniciar = btnIniciar;
    }

    public JButton getBtnOk() {
        return btnOk;
    }

    public void setBtnOk(JButton btnOk) {
        this.btnOk = btnOk;
    }

    public JButton getBtnExcluir() {
        return btnExcluir;
    }

    public void setBtnExcluir(JButton btnExcluir) {
        this.btnExcluir = btnExcluir;
    }

    public JButton getBtnAlterar() {
        return btnAlterar;
    }

    public void setBtnAlterar(JButton btnAlterar) {
        this.btnAlterar = btnAlterar;
    }

    public JButton getBtnNova() {
        return btnNova;
    }

    public void setBtnNova(JButton btnNova) {
        this.btnNova = btnNova;
    }

    public JLabel getLblStatus() {
        return lblStatus;
    }

    public void setLblStatus(JLabel lblStatus) {
        this.lblStatus = lblStatus;
    }

}