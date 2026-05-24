package midias.sistema;

import midias.exception.MidiaInexistenteException;
import midias.exception.MidiaJaExisteException;
import midias.midia.Midias;

import java.io.IOException;
import java.util.Collection;

public interface SistemaMidias {
    void cadastrarMidia(Midias midia)throws MidiaJaExisteException;
    Collection<Midias> pesquisarTitulo(String titulo);
    Collection<Midias> pesquisarGenero(String genero);
    Collection<Midias> pesquisarAno(int ano);
    Collection<Midias> pesquisarAtor(String ator);
    Collection<Midias> pesquisarDiretor(String diretor);
    Collection<Midias> filmesCadastrados();
    Collection<Midias> seriesCadastradas();
    Collection<Midias> gamesCadastrados();
    Collection<Midias> listMidiasCadastradas();
    void atualizarMidia(String titulo, String novoTitulo, int ano, String descricao, String [] elenco, String diretor)throws MidiaInexistenteException;
    void excluirMidia(String titulo)throws MidiaInexistenteException;
    void salvarDados()throws IOException;
    void recuperarDados()throws IOException;
}
