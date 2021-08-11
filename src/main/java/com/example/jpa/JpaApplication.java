package com.example.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

@SpringBootApplication
public class JpaApplication {

	private static final Logger log = LoggerFactory.getLogger(JpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			repository.save(new Customer("Agus", "susanto"));
			repository.save(new Customer("Andi", "Arsa"));
			repository.save(new Customer("Tegar", "Bangun"));
			repository.save(new Customer("Ahmad", "Maulana"));
			repository.save(new Customer("Aldo", "Doi"));

			log.info("Customer found with findAll()");
			log.info("-----------------------------");

			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}

			log.info("");

			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L)");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			log.info("Customer found with findByLastName()");
			log.info("------------------------------------");
			repository.findByLastName("susanto").forEach(data -> {
				log.info(data.toString());
			});

			log.info("");
		};
	}

}
