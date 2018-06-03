package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Main.class, args);
	}

	//TODO
	// Zuordnung Studenten Kurse
	// Plausibilität (Email, Datum, Name, etc.)
	// current Page anzeigen beim löschen von Studenten (nicht makiert)
	// Rückmeldung beim geblockten User (Access denied)
	// Bei der Userverwaltung darf der aktuelle User nicht auftauchen
}