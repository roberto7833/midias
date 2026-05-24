package midias.controller;

import midias.midia.Midias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class SearchAnoController implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janela;

    public SearchAnoController(SistemaMidias sistema, JFrame janela) {
        this.sistema = sistema;
        this.janela = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String anoPesq = JOptionPane.showInputDialog(janela, "Digite o ano de lançamento para pesquisar:",
                "Pesquisar por Ano", JOptionPane.QUESTION_MESSAGE);
        if (anoPesq != null && !anoPesq.trim().isEmpty()) {
            try {
                int ano = Integer.parseInt(anoPesq.trim());
                Collection<Midias> midiasEncontradas = sistema.pesquisarAno(ano);

                if (midiasEncontradas.isEmpty()) {
                    JOptionPane.showMessageDialog(janela, "Nenhuma mídia encontrada do ano " + ano + ".",
                            "Sem Resultados", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder relatorio = new StringBuilder("--- Mídias Encontradas (Ano: " + ano + ") ---\n\n");
                    for (Midias m : midiasEncontradas) {
                        relatorio.append(m.toString()).append("\n--------------------------\n");
                    }

                    JTextArea textArea = new JTextArea(15, 45);
                    textArea.setText(relatorio.toString());
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);

                    JOptionPane.showMessageDialog(janela, scrollPane, "Resultados da Busca", JOptionPane.PLAIN_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(janela, "Por favor, digite um ano válido com números (ex: 2026).",
                        "Entrada Inválida", JOptionPane.ERROR_MESSAGE);
            }
        } else if (anoPesq != null) {
            JOptionPane.showMessageDialog(janela, "Você precisa digitar um ano para realizar a pesquisa.",
                    "Campo Vazio", JOptionPane.ERROR_MESSAGE);
        }
    }
}