package com.techchallenge.soat3mspedidos.infrastruture.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

import static com.techchallenge.soat3mspedidos.Soat3MsPedidosApplication.PACKAGE;


@Configuration
@EnableFeignClients(PACKAGE)
public class FeignClientConfig {
}
