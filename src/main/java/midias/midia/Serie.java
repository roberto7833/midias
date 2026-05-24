package midias.midia;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Serie extends Midias implements Serializable {
    private List<Episodio> episodios;

    public Serie(String titulo, String genero, int anoLancamento, String descricao, String[] elenco, String diretor, List<Episodio> episodios) {
        super(titulo, genero, anoLancamento, descricao, elenco, diretor);
        this.episodios = episodios;
    }
    public List<Episodio> getEpisodios(){
        return this.episodios;
    }
    public void setEpisodios(List<Episodio> episodios){
        this.episodios = episodios;
    }
    @Override
    public String toString(){
        return super.toString()+"\nepisodios: "+this.episodios.size();
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Serie serie = (Serie) o;
        return Objects.equals(episodios, serie.episodios);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), episodios);
    }
}
