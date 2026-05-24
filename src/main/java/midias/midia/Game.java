package midias.midia;

import java.io.Serializable;

public class Game extends Midias implements Serializable {

    public Game(String titulo, String genero, int anoLancamento, String descricao, String[] elenco, String diretor) {
        super(titulo, genero, anoLancamento, descricao, elenco, diretor);
    }
    @Override
    public String toString(){
        return super.toString();
    }
}
