package midias.GUI;

import midias.controller.*;
import midias.sistema.ProgramaSistemaMidias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI extends JFrame {
    private JLabel linha1, linha2;
    private ImageIcon imagemFinal;
    private SistemaMidias sistema;
    private JMenuBar barraDeMenu = new JMenuBar();

    public GUI() {
        this.sistema = new ProgramaSistemaMidias();
        setTitle("Sistema Mídias");
        setLocation(150, 150);
        setSize(650, 550);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            ImageIcon imagem = new ImageIcon(("imgs/filmes.png"));
            Image imagemRecondicionada = imagem.getImage().getScaledInstance(350, 250, Image.SCALE_SMOOTH);
            imagemFinal = new ImageIcon(imagemRecondicionada);
            linha2 = new JLabel(imagemFinal);
        } catch (Exception e) {
            linha2 = new JLabel("Imagem (src/imgs/filmes.png) não encontrada.");
            linha2.setForeground(Color.RED);
        }
        linha1 = new JLabel("Sistema Mídias");
        linha1.setFont(new Font("Segoe UI", Font.BOLD, 38));
        linha1.setForeground(new Color(50, 50, 50));

        JPanel painelCentralizado = new JPanel(new GridBagLayout());
        painelCentralizado.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0);
        painelCentralizado.add(linha1, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        painelCentralizado.add(linha2, gbc);

        setLayout(new BorderLayout());
        add(painelCentralizado, BorderLayout.CENTER);

        JMenu cadastrar = new JMenu("Cadastrar");
        JMenuItem cadastrarMidia = new JMenuItem("Cadastrar Título");
        cadastrar.add(cadastrarMidia);

        JMenu pesquisar = new JMenu("Pesquisar");
        JMenuItem pesquisarTitulo = new JMenuItem("Pesquisar por Título");
        pesquisar.add(pesquisarTitulo);
        JMenuItem pesquisarGenero = new JMenuItem("Pesquisar por Gênero");
        pesquisar.add(pesquisarGenero);
        JMenuItem pesquisarAno = new JMenuItem("Pesquisar por Ano");
        pesquisar.add(pesquisarAno);
        JMenuItem pesquisarAtor = new JMenuItem("Pesquisar por Ator");
        pesquisar.add(pesquisarAtor);
        JMenuItem pesquisarDiretor = new JMenuItem("Pesquisar por Diretor");
        pesquisar.add(pesquisarDiretor);

        JMenu listMidias = new JMenu("Listar");
        JMenuItem listMidiasCadastradas = new JMenuItem("Listar todas as mídias");
        listMidias.add(listMidiasCadastradas);

        JMenu atualizar = new JMenu("Atualizar");
        JMenuItem atualizarMidia = new JMenuItem("Atualizar Mídia");
        atualizar.add(atualizarMidia);

        JMenu remover = new JMenu("Remover");
        JMenuItem removerMidia = new JMenuItem("Remover Mídia");
        remover.add(removerMidia);

        // Adicionando tudo na Barra Superior
        barraDeMenu.add(cadastrar);
        barraDeMenu.add(pesquisar);
        barraDeMenu.add(listMidias);
        barraDeMenu.add(atualizar);
        barraDeMenu.add(remover);
        setJMenuBar(barraDeMenu);

        ExcluirController excluir = new ExcluirController(this.sistema, this);
        ListTotalMidias midiasTotal = new ListTotalMidias(this.sistema, this);
        MidiaAddController addController = new MidiaAddController(this.sistema, this);
        SearchAnoController ano = new SearchAnoController(this.sistema, this);
        SearchAtorController ator = new SearchAtorController(this.sistema, this);
        SearchDiretorController diretor = new SearchDiretorController(this.sistema, this);
        SearchGeneroController genero = new SearchGeneroController(this.sistema, this);
        SearchTituloController titulo = new SearchTituloController(this.sistema, this);
        UpdateController update = new UpdateController(this.sistema, this);

        removerMidia.addActionListener(excluir);
        listMidiasCadastradas.addActionListener(midiasTotal);
        cadastrarMidia.addActionListener(addController);
        pesquisarAno.addActionListener(ano);
        pesquisarAtor.addActionListener(ator);
        pesquisarDiretor.addActionListener(diretor);
        pesquisarGenero.addActionListener(genero);
        pesquisarTitulo.addActionListener(titulo);
        atualizarMidia.addActionListener(update);
    }
    //...
    public static void main(String[] args) {
        // Configura os componentes do Swing para usarem o Português (Brasil)
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI janela = new GUI();
                janela.setVisible(true);
            }
        });
    }
}
