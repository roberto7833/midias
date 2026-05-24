package midias.controller;

import midias.exception.MidiaInexistenteException;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ExcluirController implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janela;

    public ExcluirController(SistemaMidias sistema, JFrame janela) {
        this.sistema = sistema;
        this.janela = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String titulo = JOptionPane.showInputDialog(janela, "Digite o título a ser removido:",
                "Remover Mídia", JOptionPane.QUESTION_MESSAGE);

        if (titulo != null && !titulo.trim().isEmpty()) {
            int confirmacao = JOptionPane.showConfirmDialog(janela,
                    "Tem certeza que deseja remover a mídia '" + titulo.trim() + "'?",
                    "Confirmar Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmacao == JOptionPane.YES_OPTION) {
                try {
                    sistema.excluirMidia(titulo.trim());
                    sistema.salvarDados();

                    JOptionPane.showMessageDialog(janela, "Mídia '" + titulo.trim() + "' removida com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
                catch (MidiaInexistenteException ex) {
                    JOptionPane.showMessageDialog(janela, ex.getMessage(),
                            "Mídia Não Encontrada", JOptionPane.ERROR_MESSAGE);
                }
                catch (IOException ex) {
                    JOptionPane.showMessageDialog(janela, "Erro ao salvar as alterações no arquivo de dados.",
                            "Erro de Arquivo", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (titulo != null) {
            JOptionPane.showMessageDialog(janela, "Você precisa digitar um título válido para realizar a exclusão.",
                    "Campo Vazio", JOptionPane.ERROR_MESSAGE);
        }
    }
}