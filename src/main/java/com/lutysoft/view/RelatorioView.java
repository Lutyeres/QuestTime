package com.lutysoft.view;

import javax.swing.*;
import java.awt.*;

// Esta classe representa o conteúdo completo da aba/cartão de Relatórios
public class RelatorioView extends JPanel {

    private static final Color COR_PAINEL_DARK = new Color(45, 45, 45);
    private static final Color COR_TEXTO_CLARO = Color.WHITE;

    public RelatorioView() {
        // Configurações do painel base
        super(new BorderLayout());
        setBackground(COR_PAINEL_DARK);

        // Chame um método para construir a interface do relatório aqui
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Exemplo: Adicionando o JLabel que estava na TarefaView
        JLabel lblTitulo = new JLabel("Tela de Relatórios em construção.");
        lblTitulo.setForeground(COR_TEXTO_CLARO);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 18f));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Adiciona um espaçamento
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Aqui você adicionaria tabelas, gráficos, botões de filtro, etc.
        add(lblTitulo, BorderLayout.NORTH);

        // Exemplo de Placeholder para o conteúdo principal do relatório
        JTextArea areaRelatorio = new JTextArea("Detalhes do relatório...\nEm breve, a tabela de dados!");
        areaRelatorio.setEditable(false);
        areaRelatorio.setBackground(new Color(60, 60, 60)); // Usando COR_PADRAO_ESCURA
        areaRelatorio.setForeground(COR_TEXTO_CLARO);

        add(new JScrollPane(areaRelatorio), BorderLayout.CENTER);
    }

    // Adicione getters para quaisquer componentes que o Controller precisar acessar
    // public JTable getTabelaRelatorio() { ... }
}