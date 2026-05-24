package midias.controller;

import midias.exception.MidiaInexistenteException;
import midias.midia.Midias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;

public class UpdateController implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janela;

    public UpdateController(SistemaMidias sistema, JFrame janela) {
        this.sistema = sistema;
        this.janela = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tituloPesquisado = JOptionPane.showInputDialog(janela, "Digite o título da mídia que deseja atualizar:",
                "Atualizar Mídia", JOptionPane.QUESTION_MESSAGE);

        if (tituloPesquisado == null || tituloPesquisado.trim().isEmpty()) {
            if (tituloPesquisado != null) {
                JOptionPane.showMessageDialog(janela, "Você precisa digitar um título para pesquisar.", "Campo Vazio", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
        Collection<Midias> resultado = sistema.pesquisarTitulo(tituloPesquisado.trim());

        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(janela, "Nenhuma mídia encontrada com o título: '" + tituloPesquisado + "'",
                    "Mídia Não Encontrada", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Midias midiaAntiga = resultado.iterator().next();

        JTextField tituloField = new JTextField(midiaAntiga.getTitulo(), 30);
        JTextField generoField = new JTextField(midiaAntiga.getGenero(), 20);
        JTextField anoField = new JTextField(String.valueOf(midiaAntiga.getAnoLancamento()), 4);
        JTextField descricaoField = new JTextField(midiaAntiga.getDescricao(), 60);
        JTextField elencoField = new JTextField(String.join(",", midiaAntiga.getElenco()), 60);
        JTextField diretorField = new JTextField(midiaAntiga.getDiretor(), 60);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Título:"));
        panel.add(tituloField);
        panel.add(new JLabel("Gênero:"));
        panel.add(generoField);
        panel.add(new JLabel("Ano de Lançamento:"));
        panel.add(anoField);
        panel.add(new JLabel("Descrição:"));
        panel.add(descricaoField);
        panel.add(new JLabel("Elenco (separado por vírgulas):"));
        panel.add(elencoField);
        panel.add(new JLabel("Diretor:"));
        panel.add(diretorField);

        while (true) {
            int result = JOptionPane.showConfirmDialog(janela, panel, "Modifique os dados da Mídia",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String novoTitulo = tituloField.getText().trim();
                String novoGenero = generoField.getText().trim();
                String anoStr = anoField.getText().trim();
                String novaDescricao = descricaoField.getText().trim();
                String[] novoElenco = elencoField.getText().split(",");
                String novoDiretor = diretorField.getText().trim();

                for (int i = 0; i < novoElenco.length; i++) {
                    novoElenco[i] = novoElenco[i].trim();
                }

                if (novoTitulo.isEmpty() || novoGenero.isEmpty() || anoStr.isEmpty()) {
                    JOptionPane.showMessageDialog(janela, "Os campos Título, Gênero e Ano são obrigatórios.",
                            "Erro de Validação", JOptionPane.WARNING_MESSAGE);
                    continue;
                }

                try {
                    int novoAno = Integer.parseInt(anoStr);
                    sistema.atualizarMidia(midiaAntiga.getTitulo(), novoTitulo, novoAno, novaDescricao, novoElenco, novoDiretor);
                    sistema.salvarDados();

                    JOptionPane.showMessageDialog(janela, "Mídia '" + novoTitulo + "' atualizada com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    break;

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(janela, "Por favor, digite um ano numérico válido.",
                            "Erro no Ano", JOptionPane.ERROR_MESSAGE);
                } catch (MidiaInexistenteException ex) {
                    JOptionPane.showMessageDialog(janela, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(janela, ex.getMessage(), "Título Duplicado", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(janela, "Erro ao salvar as alterações no arquivo de dados.",
                            "Erro de Arquivo", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            } else {
                break;
            }
        }
    }
}