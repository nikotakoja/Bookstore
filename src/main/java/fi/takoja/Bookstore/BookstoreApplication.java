package fi.takoja.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.takoja.Bookstore.domain.Book;
import fi.takoja.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner Kirjalistaus(BookRepository repository) {
		return (args) -> {
			log.info("Tallennetaan pari kirjaa");
			// String title, String author, String isbn, int year, double price
			repository.save(new Book("Peräkylän parhaat", "Pertti Peräkylä", "1234567890123", 1990, 29.90));
			repository.save(new Book("Kouvolan kauniit", "Kati Kotioja", "9876543210987", 2001, 15.40));
			repository.save(new Book("Kouvolan kauniit", "Kati Kotioja", "9876543210987", 2001, 15.40));
			repository.save(new Book("Kouvolan kauniit", "Kati Kotioja", "9876543210987", 2001, 15.40));
			
			log.info("Listataan kaikki kirjat");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
