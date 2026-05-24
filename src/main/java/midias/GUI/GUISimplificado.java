package midias.GUI;

import midias.exception.MidiaInexistenteException;
import midias.exception.MidiaJaExisteException;
import midias.midia.MidiaFormulario;
import midias.midia.Midias;
import midias.sistema.ProgramaSistemaMidias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;

public class GUISimplificado {

    public static void main(String[] args) {
        // Configura globalmente os botões do JOptionPane para o Português
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");

        // Inicializa o sistema (carregando os dados salvos automaticamente)
        SistemaMidias sistema = new ProgramaSistemaMidias();

        boolean rodando = true;

        while (rodando) {
            // Monta o menu de opções em formato de texto
            String menu = "=== SISTEMA MÍDIAS (MENU PRINCIPAL) ===\n\n" +
                    "1. Cadastrar Mídia\n" +
                    "2. Pesquisar por Título\n" +
                    "3. Pesquisar por Gênero\n" +
                    "4. Pesquisar por Ano\n" +
                    "5. Pesquisar por Ator\n" +
                    "6. Pesquisar por Diretor\n" +
                    "7. Listar Filmes\n" +
                    "8. Listar Séries\n" +
                    "9. Listar Games\n" +
                    "10. Listar Todas as Mídias\n" +
                    "11. Atualizar Mídia\n" +
                    "12. Remover Mídia\n" +
                    "0. Sair\n\n" +
                    "Digite o número da opção desejada:";

            String opcaoInput = JOptionPane.showInputDialog(null, menu, "Menu do Sistema", JOptionPane.PLAIN_MESSAGE);

            // Se o usuário clicar em Cancelar ou fechar a janela no X, encerra o programa
            if (opcaoInput == null) {
                break;
            }

            switch (opcaoInput.trim()) {
                case "1": // Cadastrar Mídia (Chama o seu método original perfeitamente)
                    Midias novaMidia = MidiaFormulario.midiaFormularioBase();
                    if (novaMidia != null) {
                        try {
                            sistema.cadastrarMidia(novaMidia);
                            sistema.salvarDados();
                            JOptionPane.showMessageDialog(null, "Mídia '" + novaMidia.getTitulo() + "' cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        } catch (MidiaJaExisteException | IOException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case "2": // Pesquisar por Título
                    String titulo = JOptionPane.showInputDialog(null, "Digite o título da mídia:", "Pesquisa por Título", JOptionPane.QUESTION_MESSAGE);
                    if (titulo != null && !titulo.trim().isEmpty()) {
                        exibirResultados(sistema.pesquisarTitulo(titulo.trim()), "Resultados por Título");
                    }
                    break;

                case "3": // Pesquisar por Gênero
                    String genero = JOptionPane.showInputDialog(null, "Digite o gênero da mídia:", "Pesquisa por Gênero", JOptionPane.QUESTION_MESSAGE);
                    if (genero != null && !genero.trim().isEmpty()) {
                        exibirResultados(sistema.pesquisarGenero(genero.trim()), "Resultados por Gênero");
                    }
                    break;

                case "4": // Pesquisar por Ano
                    String anoInput = JOptionPane.showInputDialog(null, "Digite o ano de lançamento:", "Pesquisa por Ano", JOptionPane.QUESTION_MESSAGE);
                    if (anoInput != null && !anoInput.trim().isEmpty()) {
                        try {
                            int ano = Integer.parseInt(anoInput.trim());
                            exibirResultados(sistema.pesquisarAno(ano), "Resultados por Ano");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Por favor, insira um ano numérico válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case "5": // Pesquisar por Ator
                    String ator = JOptionPane.showInputDialog(null, "Digite o nome do actor/atriz:", "Pesquisa por Ator", JOptionPane.QUESTION_MESSAGE);
                    if (ator != null && !ator.trim().isEmpty()) {
                        exibirResultados(sistema.pesquisarAtor(ator.trim()), "Resultados por Ator");
                    }
                    break;

                case "6": // Pesquisar por Diretor
                    String diretor = JOptionPane.showInputDialog(null, "Digite o nome do diretor:", "Pesquisa por Diretor", JOptionPane.QUESTION_MESSAGE);
                    if (diretor != null && !diretor.trim().isEmpty()) {
                        exibirResultados(sistema.pesquisarDiretor(diretor.trim()), "Resultados por Diretor");
                    }
                    break;

                case "7": // Listar Filmes
                    exibirResultados(sistema.filmesCadastrados(), "Lista de Filmes");
                    break;

                case "8": // Listar Séries
                    exibirResultados(sistema.seriesCadastradas(), "Lista de Séries");
                    break;

                case "9": // Listar Games
                    exibirResultados(sistema.gamesCadastrados(), "Lista de Games");
                    break;

                case "10": // Listar Todas as Mídias
                    exibirResultados(sistema.listMidiasCadastradas(), "Catálogo Completo");
                    break;

                case "11": // Atualizar Mídia (Montado aqui para respeitar o seu MidiaFormulario original)
                    String tituloUp = JOptionPane.showInputDialog(null, "Digite o título da mídia que deseja atualizar:", "Atualizar", JOptionPane.QUESTION_MESSAGE);
                    if (tituloUp != null && !tituloUp.trim().isEmpty()) {
                        Collection<Midias> busca = sistema.pesquisarTitulo(tituloUp.trim());
                        if (busca.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Mídia não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Midias antiga = busca.iterator().next();

                            // Monta os campos gráficos locais preenchidos com os dados da "antiga"
                            JTextField tituloField = new JTextField(antiga.getTitulo(), 30);
                            JTextField generoField = new JTextField(antiga.getGenero(), 20);
                            JTextField anoField = new JTextField(String.valueOf(antiga.getAnoLancamento()), 4);
                            JTextField descricaoField = new JTextField(antiga.getDescricao(), 60);
                            JTextField elencoField = new JTextField(String.join(",", antiga.getElenco()), 60);
                            JTextField diretorField = new JTextField(antiga.getDiretor(), 60);

                            JPanel panel = new JPanel(new GridLayout(0, 1));
                            panel.add(new JLabel("Título"));
                            panel.add(tituloField);
                            panel.add(new JLabel("Gênero"));
                            panel.add(generoField);
                            panel.add(new JLabel("Ano de lançamento"));
                            panel.add(anoField);
                            panel.add(new JLabel("Descrição"));
                            panel.add(descricaoField);
                            panel.add(new JLabel("Elenco"));
                            panel.add(elencoField);
                            panel.add(new JLabel("Diretor"));
                            panel.add(diretorField);

                            while (true) {
                                int result = JOptionPane.showConfirmDialog(null, panel, "confirme os dados",
                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                                if (result != JOptionPane.OK_OPTION) {
                                    break; // Cancela a atualização de forma segura
                                }

                                String nTitulo = tituloField.getText();
                                String nGenero = generoField.getText();
                                String nAno = anoField.getText();
                                String nDescricao = descricaoField.getText();
                                String[] nElenco = elencoField.getText().split(",");
                                String nDiretor = diretorField.getText();

                                // Higieniza os espaços do elenco usando o loop trim()
                                for (int i = 0; i < nElenco.length; i++) {
                                    nElenco[i] = nElenco[i].trim();
                                }

                                if (nTitulo.trim().isEmpty() || nGenero.trim().isEmpty() || nAno.trim().isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Os campos titulo, genero e ano são obrigatorios");
                                    continue;
                                }

                                try {
                                    int nAnoLancamento = Integer.parseInt(nAno.trim());

                                    // Salva no sistema e no arquivo
                                    sistema.atualizarMidia(antiga.getTitulo(), nTitulo, nAnoLancamento, nDescricao, nElenco, nDiretor);
                                    sistema.salvarDados();

                                    JOptionPane.showMessageDialog(null, "Mídia atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                    break;
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Digite um numero valido para o ano.");
                                } catch (MidiaInexistenteException | IOException ex) {
                                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                            }
                        }
                    }
                    break;

                case "12": // Remover Mídia
                    String tituloDel = JOptionPane.showInputDialog(null, "Digite o título da mídia a ser removida:", "Remover Mídia", JOptionPane.WARNING_MESSAGE);
                    if (tituloDel != null && !tituloDel.trim().isEmpty()) {
                        int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover '" + tituloDel.trim() + "'?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (confirmacao == JOptionPane.YES_OPTION) {
                            try {
                                sistema.excluirMidia(tituloDel.trim());
                                sistema.salvarDados();
                                JOptionPane.showMessageDialog(null, "Mídia removida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            } catch (MidiaInexistenteException | IOException ex) {
                                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    break;

                case "0": // Sair do programa
                    rodando = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida! Selecione um número de 0 a 12.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    break;
            }
        }
        JOptionPane.showMessageDialog(null, "Sistema encerrado de forma segura. Até logo!", "Fim do Programa", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método auxiliar padrão para exibição dos relatórios textuais roláveis
    private static void exibirResultados(Collection<Midias> lista, String tituloJanela) {
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.", tituloJanela, JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder relatorio = new StringBuilder("--- " + tituloJanela.toUpperCase() + " ---\n\n");
            for (Midias m : lista) {
                relatorio.append(m.toString()).append("\n--------------------------\n");
            }
            JTextArea textArea = new JTextArea(15, 45);
            textArea.setText(relatorio.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            JOptionPane.showMessageDialog(null, scrollPane, tituloJanela, JOptionPane.PLAIN_MESSAGE);
        }
    }
}