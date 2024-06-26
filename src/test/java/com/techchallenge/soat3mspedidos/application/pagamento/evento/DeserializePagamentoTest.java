package com.techchallenge.soat3mspedidos.application.pagamento.evento;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techchallenge.soat3mspedidos.adapter.pagamento.model.PagamentoModel;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class DeserializePagamentoTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testDeserializePagamentoJson() {
        String pagamentoJson = "{\n" +
                "  \"id\": \"2afd735e-db9c-4b04-bd7e-d21990f96de5\",\n" +
                "  \"cliente\": {\n" +
                "    \"id\": \"8b1910d4-046c-4d7a-b0f5-8f4d1c6db6e4\",\n" +
                "    \"nome\": \"Carlos Souza\",\n" +
                "    \"cpf\": \"45678912334\",\n" +
                "    \"email\": \"carlos.souza@example.com\",\n" +
                "    \"telefone\": \"31977777777\"\n" +
                "  },\n" +
                "  \"preco\": 1234.45,\n" +
                "  \"statusPagamento\": \"PAGO\",\n" +
                "  \"codigoPix\": \"00020126580014br.gov.bcb.pix0136b76aa9c2-2ec4-4110-954e-ebfe34f05b6152040000530398654071234.455802BR5908VIzPl_JR6008SakRpDor62230519mpqrinter131869939663046176\",\n" +
                "  \"idPagamentoMP\": \"1318699396\",\n" +
                "  \"qrCode\": \"ImlWQk9SdzBLR2dvQUFBQU5TVWhFVWdBQUJXUUFBQVZrQVFBQUFBQjc5aXNjQUFBTk5rbEVRVlI0WHUzWFczSXJPdzVFVWMzZ3puK1dkd2JxTUJJb1BFaXBIZEhtT1NyM3pnOEZTWURnS3YvNThieFIvbjNNazA4TzJuTkJleTVvendYdHVhQTlGN1RuZ3ZaYzBKNEwybk5CZXk1b3p3WHR1YUE5RjdUbmd2WmMwSjRMMm5OQmV5NW96d1h0dWFBOUY3VG5ndlpjMEo0TDJuTkJleTVvendYdHVhQTlGN1RuZ3ZaYzBKNEwybk5CZXk1b3p3WHR1YUE5RjdUbmd2WmNxdll4ODgvWG1mM0VkdlI5M1krZk5tVVU2cGx0WTZpZCtlUllXYUVtM2tXN0s5UXoyNkpGcXkxYXROcWlSYXN0V3JUYW9rV3JMZG9QMStaNWJtM0k0L0xrOU5obWl5ZS9xbjF1SGRVK1k5SHVHQmEwYUJXMGFCVzBhQlcwYUJXMGFCVzBhSldiYS9QK2NzdmVpZFg0RnErMkYvT3NUc2szeGcxTHU3c3cwS0pWMEtKVjBLSlYwS0pWMEtKVjBLSlZmcUUyenp3cklOMWVYVnV5NnFzczdINHNhTkVxYU5FcWFORXFhTkVxYU5FcWFORXF2MXJyZytPSk1UTmx0ZHJpMVd5MmgwWTF0eThZSHJRdGFGKzIrUkMwcFpyYkZ3d1AyaGEwTDl0OENOcFN6ZTBMaGdkdEM5cVhiVDRFYmFubTlnWERnN1lGN2NzMkgvS3AyckgxSWMxVHE3c2JyY1ZYdStTSHgzWmNXN1pvMTJldjJ5MW9IMmd0YUI5b0xXZ2ZhQzFvSDJndGFCOW9MV2dmSDZzZHlYRi85bWRsb1AycG41V0I5cWQrVmdiYW4vcFpHV2gvNm1kbG9QMnBuNVdBOXFkK1ZnYmFuL3BaR1doLzZtZGxvUDJwbjVWeGU=\"\n" +
                "}";

        try {
            PagamentoModel pagamento = objectMapper.readValue(pagamentoJson, PagamentoModel.class);
            assertNotNull(pagamento);
            assertEquals(UUID.fromString("2afd735e-db9c-4b04-bd7e-d21990f96de5"), pagamento.getId());
            assertNotNull(pagamento.getCliente());
            assertEquals("Carlos Souza", pagamento.getCliente().getNome());
            // Adicione mais asserts conforme necessário para testar os demais campos
        } catch (Exception e) {
            fail("JSON parsing failed: " + e.getMessage());
        }
    }
}
