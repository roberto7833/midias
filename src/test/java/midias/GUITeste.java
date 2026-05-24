package midias;

import midias.exception.MidiaInexistenteException;
import midias.exception.MidiaJaExisteException;
import midias.midia.Midias;
import midias.sistema.ProgramaSistemaMidias;
import midias.sistema.SistemaMidias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class GUITeste {

    private SistemaMidias sistema;

    @BeforeEach
    public void setUp() {
        sistema = new ProgramaSistemaMidias();
        try {
            sistema.listMidiasCadastradas().clear();
        } catch (Exception e) {

        }
    }

    @Test
    public void testCadastrarEListarTodasAsMidias() throws MidiaJaExisteException {
        String[] elenco = {"Ator A", "Ator B"};
        Midias filme = new Midias("Matrix", "Filme", 1999, "Sci-Fi clássico", elenco, "Wachowskis");

        sistema.cadastrarMidia(filme);
        Collection<Midias> todas = sistema.listMidiasCadastradas();

        assertEquals(1, todas.size(), "O sistema deveria conter exatamente 1 mídia cadastrada.");
        assertTrue(todas.contains(filme), "A mídia cadastrada deveria estar presente na lista total.");
    }

    @Test
    public void testCadastrarMidiaDuplicadaDisparaExcecao() throws MidiaJaExisteException {
        String[] elenco = {"Ator A"};
        Midias midia1 = new Midias("mario", "Game", 2023, "Jogo do Mario", elenco, "Nintendo");
        Midias midia2 = new Midias("mario", "Game", 2023, "Outra versão", elenco, "Nintendo");

        sistema.cadastrarMidia(midia1);

        assertThrows(MidiaJaExisteException.class, () -> {
            sistema.cadastrarMidia(midia2);
        }, "Deveria lançar MidiaJaExisteException ao tentar cadastrar título duplicado.");
    }

    @Test
    public void testPesquisarPorGenero() throws MidiaJaExisteException {
        String[] elenco = {"Ninguém"};
        Midias filme1 = new Midias("Inception", "Filme", 2010, "Sonhos", elenco, "Nolan");
        Midias serie1 = new Midias("Breaking Bad", "Série", 2008, "Química", elenco, "Vince");
        Midias filme2 = new Midias("Avatar", "Filme", 2009, "Pandora", elenco, "Cameron");

        sistema.cadastrarMidia(filme1);
        sistema.cadastrarMidia(serie1);
        sistema.cadastrarMidia(filme2);

        Collection<Midias> filmes = sistema.pesquisarGenero("Filme");
        assertEquals(2, filmes.size(), "Deveria ter encontrado exatamente 2 mídias com o gênero 'Filme'.");

        Collection<Midias> series = sistema.pesquisarGenero("Série");
        assertEquals(1, series.size(), "Deveria ter encontrado exatamente 1 mídia com o gênero 'Série'.");
    }

    @Test
    public void testExcluirMidiaComSucesso() throws MidiaJaExisteException, MidiaInexistenteException {
        String[] elenco = {"Ator X"};
        Midias jogo = new Midias("mario", "Game", 2023, "Plataforma", elenco, "Miyamoto");

        sistema.cadastrarMidia(jogo);

        sistema.excluirMidia("  MARIO  ");

        Collection<Midias> todas = sistema.listMidiasCadastradas();
        assertTrue(todas.isEmpty(), "O catálogo deveria estar vazio após a exclusão da única mídia.");
    }

    @Test
    public void testExcluirMidiaInexistenteDisparaExcecao() {
        assertThrows(MidiaInexistenteException.class, () -> {
            sistema.excluirMidia("Zelda");
        }, "Deveria lançar MidiaInexistenteException ao tentar remover um título que não existe.");
    }

    @Test
    public void testAtualizarMidiaComSucesso() throws MidiaJaExisteException, MidiaInexistenteException {
        String[] elencoOriginal = {"Ator 1"};
        Midias midia = new Midias("mario", "Game", 2023, "Versão Antiga", elencoOriginal, "Nintendo");
        sistema.cadastrarMidia(midia);

        String[] novoElenco = {"Ator 1", "Ator 2"};
        sistema.atualizarMidia("mario", "Super Mario", 2026, "Versão Atualizada", novoElenco, "Nintendo");

        assertTrue(sistema.pesquisarTitulo("mario").isEmpty(), "A busca pelo título antigo não deveria retornar nada.");

        Collection<Midias> resultadoNovo = sistema.pesquisarTitulo("Super Mario");
        assertFalse(resultadoNovo.isEmpty(), "A mídia atualizada deveria ser encontrada pelo novo título.");

        Midias atualizada = resultadoNovo.iterator().next();
        assertEquals(2026, atualizada.getAnoLancamento());
        assertEquals("Versão Atualizada", atualizada.getDescricao());
        assertEquals(2, atualizada.getElenco().length);
    }
}