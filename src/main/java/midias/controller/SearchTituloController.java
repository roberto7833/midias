package midias.controller;

import midias.midia.Midias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class SearchTituloController implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janela;

    public SearchTituloController(SistemaMidias sistema, JFrame janela) {
        this.sistema = sistema;
        this.janela = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String titulo = JOptionPane.showInputDialog(janela, "Digite o título que deseja pesquisar:",
                "Pesquisar por Título", JOptionPane.QUESTION_MESSAGE);
        if (titulo != null && !titulo.trim().isEmpty()) {
            Collection<Midias> midiasEncontradas = sistema.pesquisarTitulo(titulo.trim());

            if (midiasEncontradas.isEmpty()) {
                JOptionPane.showMessageDialog(janela, "Nenhuma mídia encontrada com o título: '" + titulo + "'",
                        "Sem Resultados", JOptionPane.INFORMATION_MESSAGE);
            } else {
                StringBuilder relatorio = new StringBuilder("--- Mídias Encontradas ---\n\n");
                for (Midias m : midiasEncontradas) {
                    relatorio.append(m.toString()).append("\n--------------------------\n");
                }

                JTextArea textArea = new JTextArea(15, 45);
                textArea.setText(relatorio.toString());
                textArea.setEditable(false); // Impede o usuário de apagar o texto gerado
                JScrollPane scrollPane = new JScrollPane(textArea);

                JOptionPane.showMessageDialog(janela, scrollPane, "Resultados da Busca", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}