package com.techchallenge.soat3mspedidos.domain.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class I18nTest {

    @Test
    public void testMessages() {
        assertEquals("Cliente Invalido", I18n.CLIENTE_INVALIDO);
        assertEquals("Nem um dos produtos é valido", I18n.SEM_PRODUTOS_VALIDOS);
        assertEquals("Nao é possível adicionar itens ao pedido", I18n.NAO_E_POSSIVEL_ADICIONAR_ITENS_AO_PEDIDO);
        assertEquals("Pedido não é valido", I18n.PEDIDO_INVALIDO);
        assertEquals("Pedido já está pago", I18n.PEDIDO_JA_ESTA_PAGO);
        assertEquals("Não foi possível alterar o status do pedido", I18n.NAO_E_POSSIVEL_ALTERAR_STATUS_DO_PEDIDO);
        assertEquals("Cliente não pode ser nulo.", I18n.CLIENTE_NAO_PODE_SER_NULO);
        assertEquals("Cliente não encontrado com o ID: ", I18n.CLIENTE_NAO_ENCONTRADO_COM_O_ID);
        assertEquals("Cliente não encontrado para o CPF: ", I18n.CLIENTE_NAO_ENCONTRADO_PARA_O_CPF);
        assertEquals("Cliente já cadastrado para o CPF ", I18n.CLIENTE_JA_CADASTRADO_PARA_O_CPF);
    }
}
