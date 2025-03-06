package com.sistema_contable;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistContableApplication {

	public static void main(String[] args) {
            // CARGAR LAS VARIABLES DE ENTORNO DE .ENV
            
            Dotenv dotenv = Dotenv.configure().load();
            
            dotenv.entries().forEach(entry->{
                System.setProperty(entry.getKey(), entry.getValue());
            });
            
		SpringApplication.run(SistContableApplication.class, args);
	}

}
