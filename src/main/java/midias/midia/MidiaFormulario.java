package midias.midia;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

public class MidiaFormulario implements Serializable {

    public static Midias midiaFormularioBase(){
        JTextField tituloField = new JTextField(30);
        JTextField generoField = new JTextField(20);
        JTextField anoField = new JTextField(4);
        JTextField descricaoField = new JTextField(60);
        JTextField elencoField = new JTextField(60);
        JTextField diretorField = new JTextField(60);

        JPanel panel = new JPanel(new GridLayout(0,1));
        panel.add(new JLabel("Titulo"));
        panel.add(tituloField);
        panel.add(new JLabel("Genero"));
        panel.add(generoField);
        panel.add(new JLabel("Ano de lançamento"));
        panel.add(anoField);
        panel.add(new JLabel("Descrição"));
        panel.add(descricaoField);
        panel.add(new JLabel("Elenco"));
        panel.add(elencoField);
        panel.add(new JLabel("Diretor"));
        panel.add(diretorField);

        while (true){
            int result = JOptionPane.showConfirmDialog(null, panel, "confirme os dados",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result != JOptionPane.OK_OPTION){
                return null;
            }
            String titulo = tituloField.getText();
            String genero = generoField.getText();
            String ano = anoField.getText();
            String descricao = descricaoField.getText();
            String[] elenco = elencoField.getText().split(",");
            String diretor = diretorField.getText();

            if (titulo.trim().isEmpty() || genero.trim().isEmpty() || ano.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Os campos titulo, genero e ano são obrigatorios");
                continue;
            }
            try {
                int anoLancamento = Integer.parseInt(ano.trim());
                return new Midias(titulo, genero, anoLancamento, descricao, elenco, diretor);
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Digite um numero valido");
            }
        }
    }
}
