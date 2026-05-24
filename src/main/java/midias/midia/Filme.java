package midias.midia;

import java.io.Serializable;
import java.util.Objects;

public class Filme extends Midias implements Serializable {
    private int duracao;

    public Filme(String titulo, String genero, int anoLancamento, String descricao, String[] elenco, String diretor, int duracao) {
        super(titulo, genero, anoLancamento, descricao, elenco, diretor);
        this.duracao = duracao;
    }
    public int getDuracao() {
        return this.duracao;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    @Override
    public String toString(){
        return super.toString()+"\nDuração: "+this.duracao;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Filme filme = (Filme) o;
        return duracao == filme.duracao;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), duracao);
    }
}
