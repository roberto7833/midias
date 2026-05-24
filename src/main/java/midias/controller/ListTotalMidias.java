package midias.controller;

import midias.midia.Midias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class ListTotalMidias implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janela;

    public ListTotalMidias(SistemaMidias sistema, JFrame janela) {
        this.sistema = sistema;
        this.janela = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Collection<Midias> todasAsMidias = sistema.listMidiasCadastradas();
        if (todasAsMidias.isEmpty()) {
            JOptionPane.showMessageDialog(janela, "Não há nenhuma mídia cadastrada no sistema até ao momento.",
                    "Catálogo Vazio", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder relatorio = new StringBuilder("=== TODOS OS FILMES, SÉRIES E GAMES ===\n\n");

            for (Midias m : todasAsMidias) {
                relatorio.append(m.toString()).append("\n--------------------------\n");
            }
            JTextArea textArea = new JTextArea(18, 45);
            textArea.setText(relatorio.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            JOptionPane.showMessageDialog(janela, scrollPane, "Catálogo Completo do Sistema", JOptionPane.PLAIN_MESSAGE);
        }
    }
}