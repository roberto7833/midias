package midias.controller;

import midias.midia.Midias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class SearchAtorController implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janela;

    public SearchAtorController(SistemaMidias sistema, JFrame janela) {
        this.sistema = sistema;
        this.janela = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ator = JOptionPane.showInputDialog(janela, "Digite o nome do ator/atriz para pesquisar:",
                "Pesquisar por Ator", JOptionPane.QUESTION_MESSAGE);
        if (ator != null && !ator.trim().isEmpty()) {
            Collection<Midias> midiasEncontradas = sistema.pesquisarAtor(ator.trim());
            if (midiasEncontradas.isEmpty()) {
                JOptionPane.showMessageDialog(janela, "Nenhuma mídia encontrada com o ator/atriz: '" + ator + "'",
                        "Sem Resultados", JOptionPane.INFORMATION_MESSAGE);
            } else {
                StringBuilder relatorio = new StringBuilder("--- Mídias Encontradas (Ator: " + ator + ") ---\n\n");
                for (Midias m : midiasEncontradas) {
                    relatorio.append(m.toString()).append("\n--------------------------\n");
                }

                JTextArea textArea = new JTextArea(15, 45);
                textArea.setText(relatorio.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);

                JOptionPane.showMessageDialog(janela, scrollPane, "Resultados da Busca", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (ator != null) {
            JOptionPane.showMessageDialog(janela, "Você precisa digitar um nome para realizar a pesquisa.",
                    "Campo Vazio", JOptionPane.ERROR_MESSAGE);
        }
    }
}