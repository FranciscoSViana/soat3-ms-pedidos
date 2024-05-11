package com.techchallenge.soat3mspedidos.commons.utils;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataProviderImplTest {

    @Test
    public void testObterDataAtual() {
        DataProviderImpl dataProvider = new DataProviderImpl();
        LocalDate localDate = dataProvider.obterDataAtutal();
        assertNotNull(localDate);
    }

    @Test
    void testObterDataHoraAtual() {
        DataProviderImpl dataProvider = new DataProviderImpl();
        LocalDateTime currentDateTime = dataProvider.obterDataHoraAtual();
        assertNotNull(currentDateTime);
    }
}
