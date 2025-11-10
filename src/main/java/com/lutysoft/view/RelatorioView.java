package com.lutysoft.view;

import com.lutysoft.controller.RelatorioController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// Esta classe representa o conteúdo completo da aba/cartão de Relatórios
public class RelatorioView extends JPanel {

    private final RelatorioController relatorioController;

    private static final Color COR_PAINEL_DARK = new Color(45, 45, 45);
    private static final Color COR_TEXTO_CLARO = Color.WHITE;
    private static final Color COR_TEXTO_ESCURO = Color.BLACK; // Importado do TarefaView
    private static final Color COR_PADRAO_ESCURA = new Color(60, 60, 60);
    private static final Color COR_OK = new Color(61, 250, 76); // Cor de ação verde para o botão
    private static final Color COR_INICIAR = new Color(59, 246, 168);


    // --- Componentes Adicionais ---
    private JTable tabelaRelatorio;
    private DefaultTableModel tableModel;

    // --- NOVO COMPONENTE ---
    private JButton btnAtualizar;

    public RelatorioView() {
        // Configurações do painel base
        super(new BorderLayout(10, 10)); // Adicionado espaçamento para o botão
        setBackground(COR_PAINEL_DARK);

        // Chame um método para construir a interface do relatório aqui
        inicializarComponentes();

        // injeção do controller
        this.relatorioController = new RelatorioController(this);

        // Adiciona o listener ao novo botão
        events();
    }

    private void inicializarComponentes() {
        // --- PAINEL NORTE (Título) ---
        JLabel lblTitulo = new JLabel("Relatório de Tarefas");
        lblTitulo.setForeground(COR_TEXTO_CLARO);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 18f));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20)); // Borda ajustada
        add(lblTitulo, BorderLayout.NORTH);

        // --- PAINEL CENTRAL (Tabela) ---
        String[] colunas = {"ID", "Nome da Tarefa", "Descrição", "Início", "Fim", "Duração"};

        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaRelatorio = new JTable(tableModel);

        // Customização da Tabela (mantida)
        tabelaRelatorio.setBackground(COR_PADRAO_ESCURA);
        tabelaRelatorio.setForeground(COR_TEXTO_CLARO);
        tabelaRelatorio.setSelectionBackground(new Color(90, 90, 90));
        tabelaRelatorio.setSelectionForeground(Color.YELLOW);
        tabelaRelatorio.setGridColor(new Color(70, 70, 70));
        tabelaRelatorio.getTableHeader().setBackground(COR_PAINEL_DARK);
        tabelaRelatorio.getTableHeader().setForeground(COR_TEXTO_CLARO);
        tabelaRelatorio.getTableHeader().setFont(tabelaRelatorio.getFont().deriveFont(Font.BOLD));

        JScrollPane scrollPane = new JScrollPane(tabelaRelatorio);
        scrollPane.setBackground(COR_PAINEL_DARK);
        scrollPane.getViewport().setBackground(COR_PADRAO_ESCURA);

        add(scrollPane, BorderLayout.CENTER);

        // --- NOVO: PAINEL SUL (Botão Atualizar) ---
        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelRodape.setBackground(COR_PAINEL_DARK);

        btnAtualizar = new JButton("Atualizar Relatório");
        btnAtualizar.setBackground(COR_OK); // Cor verde de ação
        btnAtualizar.setForeground(COR_TEXTO_ESCURO);
        btnAtualizar.setPreferredSize(new Dimension(200, 35)); // Tamanho fixo

        painelRodape.add(btnAtualizar);

        add(painelRodape, BorderLayout.SOUTH);
    }

    private void events() {
        // Adiciona o controller como ActionListener para o botão
        btnAtualizar.addActionListener(relatorioController);
    }


    // ====================================================================
    // GETTERS PARA O CONTROLLER (Adicionado Getter para o novo botão)
    // ====================================================================

    public JTable getTabelaRelatorio() {
        return tabelaRelatorio;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getBtnAtualizar() {
        return btnAtualizar;
    }
}