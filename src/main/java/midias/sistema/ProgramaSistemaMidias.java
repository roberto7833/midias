package midias.sistema;

import midias.exception.MidiaInexistenteException;
import midias.exception.MidiaJaExisteException;
import midias.midia.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ProgramaSistemaMidias implements SistemaMidias{
    private HashMap<String, Midias> midias;
    private GravadorDeDados gravador;

    public ProgramaSistemaMidias(){
        this.midias = new HashMap<>();
        this.gravador = new GravadorDeDados();

        try {
            this.midias = gravador.recuperarDados();
        }
        catch (IOException e){
            this.midias = new HashMap<>();
        }
    }

    @Override
    public void cadastrarMidia(Midias midia) throws MidiaJaExisteException {
        if(this.midias.containsKey(midia.getTitulo())){
            throw new MidiaJaExisteException("Já existe uma midia cadastrada com esse titulo");
        }
        this.midias.put(midia.getTitulo(), midia);
    }

    @Override
    public Collection<Midias> pesquisarTitulo(String titulo) {
        Collection<Midias> tituloPesq = new ArrayList<>();
        for(Midias m: this.midias.values()){
            if(m.getTitulo().equalsIgnoreCase(titulo)){
                tituloPesq.add(m);
            }
        }
        return tituloPesq;
    }

    @Override
    public Collection<Midias> pesquisarGenero(String genero) {
        Collection<Midias> generoPesq = new ArrayList<>();
        for(Midias m: this.midias.values()){
            if(m.getGenero().equalsIgnoreCase(genero)){
                generoPesq.add(m);
            }
        }
        return generoPesq;
    }

    @Override
    public Collection<Midias> pesquisarAno(int ano) {
        Collection<Midias> anoPesq = new ArrayList<>();
        for(Midias m: this.midias.values()){
            if(m.getAnoLancamento()==ano){
                anoPesq.add(m);
            }
        }
        return anoPesq;
    }

    @Override
    public Collection<Midias> pesquisarAtor(String ator) {
        Collection<Midias> atorPesq = new ArrayList<>();
        for(Midias m: this.midias.values()){
            String [] elenco = m.getElenco();
            for(String e: elenco){
                if(e.equalsIgnoreCase(ator)){
                    atorPesq.add(m);
                }
            }
        }
        return atorPesq;
    }

    @Override
    public Collection<Midias> pesquisarDiretor(String diretor) {
        Collection<Midias> diretorPesq = new ArrayList<>();
        for(Midias m: this.midias.values()){
            if(m.getDiretor().equalsIgnoreCase(diretor)){
                diretorPesq.add(m);
            }
        }
        return diretorPesq;
    }

    @Override
    public Collection<Midias> filmesCadastrados() {
        Collection<Midias> filmesPesq = new ArrayList<>();
        for(Midias m: this.midias.values()){
            if(m instanceof Filme){
                filmesPesq.add(m);
            }
        }
        return filmesPesq;
    }

    @Override
    public Collection<Midias> seriesCadastradas() {
        Collection<Midias> seriesPesq = new ArrayList<>();
        for(Midias m: this.midias.values()){
            if(m instanceof Serie){
                seriesPesq.add(m);
            }
        }
        return seriesPesq;
    }

    @Override
    public Collection<Midias> gamesCadastrados() {
        Collection<Midias> gamesPesq = new ArrayList<>();
        for(Midias m: this.midias.values()){
            if(m instanceof Game){
                gamesPesq.add(m);
            }
        }
        return gamesPesq;
    }

    @Override
    public Collection<Midias> listMidiasCadastradas() {
        return this.midias.values();
    }

    @Override
    public void atualizarMidia(String titulo, String novoTitulo, int ano, String descricao, String[] elenco, String diretor) throws MidiaInexistenteException {
        if(!this.midias.containsKey(titulo)){
            throw new MidiaInexistenteException("Nenhum resultado encontrado com o titulo pesquisado "+titulo);
        }
        Midias midia = this.midias.get(titulo);
        if(!titulo.equalsIgnoreCase(novoTitulo) && this.midias.containsKey(novoTitulo)) {
            throw new IllegalArgumentException("Já existe uma midia cadastrada com o titulo " + novoTitulo);
        }
        this.midias.remove(titulo);
        midia.setTitulo(novoTitulo);
        midia.setAnoLancamento(ano);
        midia.setDescricao(descricao);
        midia.setElenco(elenco);
        midia.setDiretor(diretor);

        this.midias.put(novoTitulo, midia);
    }

    @Override
    public void excluirMidia(String titulo) throws MidiaInexistenteException {
        String chaveEncontrada = null;
        for (String k : this.midias.keySet()) {
            if (k.equalsIgnoreCase(titulo.trim())) {
                chaveEncontrada = k;
                break;
            }
        }
        if (chaveEncontrada == null) {
            throw new MidiaInexistenteException("Nenhuma Mídia encontrada com o título: " + titulo);
        }
        this.midias.remove(chaveEncontrada);
    }

    @Override
    public void salvarDados() throws IOException {
        gravador.salvarDados(midias);
    }

    @Override
    public void recuperarDados() throws IOException {
        this.midias = gravador.recuperarDados();
    }
}
