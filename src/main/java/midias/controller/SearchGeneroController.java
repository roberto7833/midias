package midias.controller;

import midias.midia.Midias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class SearchGeneroController implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janela;

    public SearchGeneroController(SistemaMidias sistema, JFrame janela) {
        this.sistema = sistema;
        this.janela = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String genero = JOptionPane.showInputDialog(janela, "Digite o gênero que deseja pesquisar:",
                "Pesquisar por Gênero", JOptionPane.QUESTION_MESSAGE);

        if (genero != null && !genero.trim().isEmpty()) {
            Collection<Midias> midiasEncontradas = sistema.pesquisarGenero(genero.trim());

            if (midiasEncontradas.isEmpty()) {
                JOptionPane.showMessageDialog(janela, "Nenhuma mídia encontrada com o gênero: '" + genero + "'",
                        "Sem Resultados", JOptionPane.INFORMATION_MESSAGE);
            } else {
                StringBuilder relatorio = new StringBuilder("--- Mídias Encontradas (Gênero: " + genero + ") ---\n\n");
                for (Midias m : midiasEncontradas) {
                    relatorio.append(m.toString()).append("\n--------------------------\n");
                }
                JTextArea textArea = new JTextArea(15, 45);
                textArea.setText(relatorio.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);

                JOptionPane.showMessageDialog(janela, scrollPane, "Resultados da Busca", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (genero != null) {
            JOptionPane.showMessageDialog(janela, "Você precisa digitar um gênero para realizar a pesquisa.",
                    "Campo Vazio", JOptionPane.ERROR_MESSAGE);
        }
    }
}