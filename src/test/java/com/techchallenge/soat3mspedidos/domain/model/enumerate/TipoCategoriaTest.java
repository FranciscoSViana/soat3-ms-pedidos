package com.techchallenge.soat3mspedidos.domain.model.enumerate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TipoCategoriaTest {

    @Test
    public void testLanche() {
        String validName = "LANCHE";
        TipoCategoria tipoCategoria = TipoCategoria.fromName(validName);
        assertEquals(TipoCategoria.LANCHE, tipoCategoria);
        assertEquals("Lanche", tipoCategoria.getDescricao());
    }

    @Test
    public void testInvalido() {
        String invalidName = "Invalido";
        assertThrows(IllegalArgumentException.class, () -> TipoCategoria.fromName(invalidName));
    }
}
