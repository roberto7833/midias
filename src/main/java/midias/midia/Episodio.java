package midias.midia;

import java.io.Serializable;
import java.util.Objects;

public abstract class Episodio implements Serializable {
    private String nomeEp;
    private int duracaoEp;

    public Episodio(String nomeEp, int duracaoEp) {
        this.nomeEp = nomeEp;
        this.duracaoEp = duracaoEp;
    }
    public String getNomeEp() {
        return this.nomeEp;
    }
    public void setNomeEp(String nomeEp) {
        this.nomeEp = nomeEp;
    }
    public int getDuracaoEp() {
        return this.duracaoEp;
    }
    public void setDuracaoEp(int duracaoEp) {
        this.duracaoEp = duracaoEp;
    }
    public String toString(){
        return "nome Ep: "+this.nomeEp+"\nDuração Ep: "+this.duracaoEp;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Episodio episodio = (Episodio) o;
        return duracaoEp == episodio.duracaoEp && Objects.equals(nomeEp, episodio.nomeEp);
    }
    @Override
    public int hashCode() {
        return Objects.hash(nomeEp, duracaoEp);
    }
}
