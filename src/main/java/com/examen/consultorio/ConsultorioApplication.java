package com.examen.consultorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsultorioApplication {

	public static void main(String[] args) {
		System.out.println("Iniciando la aplicación...");
		SpringApplication.run(ConsultorioApplication.class, args);
		System.out.println("La aplicación se ha iniciado correctamente.");
	}

}
