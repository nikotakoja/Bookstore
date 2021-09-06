package fi.takoja.Bookstore.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fi.takoja.Bookstore.domain.Book;

public class BookController {
	
	@GetMapping("/index")
	public String lisaaYstava(Book book, Model model) {
		
		model.addAttribute("book", book);
		
		return "jotain";
	
	}

}
