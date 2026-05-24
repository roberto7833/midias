package midias.controller;

import midias.exception.MidiaJaExisteException;
import midias.midia.MidiaFormulario;
import midias.midia.Midias;
import midias.sistema.SistemaMidias;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MidiaAddController implements ActionListener {
    private SistemaMidias sistema;
    private JFrame janela;

    public MidiaAddController(SistemaMidias sistema, JFrame janela) {
        this.sistema = sistema;
        this.janela = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Midias midia = MidiaFormulario.midiaFormularioBase();

        if(midia != null){
            try {
                sistema.cadastrarMidia(midia);

                sistema.salvarDados();

                JOptionPane.showMessageDialog(janela, "Midia "+ midia.getTitulo(), " cadastrada com sucesso ", JOptionPane.INFORMATION_MESSAGE);
            }catch (MidiaJaExisteException ex){
                JOptionPane.showMessageDialog(janela, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }catch (IOException ex){
                JOptionPane.showMessageDialog(janela, "Erro ao salvar os dados "+ ex.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
