package midias.midia;

import java.io.Serializable;
import java.util.Objects;

public class Midias implements Serializable {
    private String titulo;
    private String genero;
    private int anoLancamento;
    private String descricao;
    private String [] elenco;
    private String diretor;

    public Midias(String titulo, String genero, int anoLancamento, String descricao, String[] elenco, String diretor) {
        this.titulo = titulo;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.descricao = descricao;
        this.elenco = elenco;
        this.diretor = diretor;
    }
    public String getTitulo() {
        return this.titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getGenero() {
        return this.genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public int getAnoLancamento() {
        return this.anoLancamento;
    }
    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String[] getElenco() {
        return this.elenco;
    }
    public void setElenco(String[] elenco) {
        this.elenco = elenco;
    }
    public String getDiretor() {
        return this.diretor;
    }
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
    public String toString(){
        return "Titulo: "+this.titulo+"\nGenero: "+this.genero+"\nAno de lançamento: "+this.anoLancamento+
                "\nDescrição: "+this.descricao+"\nElenco: "+String.join(",", this.elenco)+"\nDiretor: "+this.diretor;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Midias midias = (Midias) o;
        return anoLancamento == midias.anoLancamento && Objects.equals(titulo, midias.titulo) && Objects.equals(genero, midias.genero);
    }
    @Override
    public int hashCode() {
        return Objects.hash(titulo, genero, anoLancamento);
    }
}