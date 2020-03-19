package test;


import model.Pais;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import service.PaisService;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest {
    Pais pais, copia;
    PaisService paisService;
    static int id = 0;

    @Before
    public void setUp() throws Exception {
        System.out.println("setup");
        pais = new Pais();
        pais.setId(id);
        pais.setNome("Alemanha");
        pais.setPopulacao(Long.valueOf("12344232"));
        pais.setArea(String.valueOf(312312));
        copia = new Pais();
        copia.setId(id);
        copia.setNome("EUA");
        copia.setPopulacao(Long.valueOf("123213133"));
        copia.setArea(String.valueOf(312312));
        paisService = new PaisService();
        System.out.println(pais);
        System.out.println(copia);
        System.out.println(id);
    }

    @Test
    public void test00Carregar() {
        System.out.println("carregar");
        Pais fixture = new Pais();
        fixture.setId(1);
        fixture.setNome("Portugal");
        fixture.setPopulacao(Long.valueOf("12333321"));
        fixture.setArea(String.valueOf(312312));
        PaisService novoService = new PaisService();
        Pais novo = novoService.carregar(1);
        assertEquals("testa inclusao", novo, fixture);
    }

    @Test
    public void test01Criar() {
        System.out.println("criar");
        id = paisService.criar(pais);
        System.out.println(id);
        copia.setId(id);
        assertEquals("testa criacao", pais, copia);
    }

    @Test
    public void test02Atualizar() {
        System.out.println("atualizar");
        pais.setPopulacao(Long.valueOf("123213213333"));
        copia.setArea(String.valueOf(312312));
        paisService.atualizar(pais);
        pais = paisService.carregar(pais.getId());
        assertEquals("testa atualizacao", pais, copia);
    }

    @Test
    public void test03Excluir() {
        System.out.println("excluir");
        copia.setId(-1);
        copia.setNome(null);
        copia.setPopulacao(null);
        copia.setArea(null);
        paisService.excluir(id);
        pais = paisService.carregar(id);
        assertEquals("testa exclusao", pais, copia);
    }
}