package fi.takoja.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fi.takoja.Bookstore.domain.Book;
import fi.takoja.Bookstore.domain.BookRepository;
import fi.takoja.Bookstore.domain.CategoryRepository;


@RestController
public class RestBookController {

	@Autowired
	BookRepository repository; 
	@Autowired
	CategoryRepository crepository;
	
		// Kirjat tietokannasta
		@GetMapping("/books")
	    public List<Book> bookListRest(){
			System.out.println("RestBookController: /books");
	        return (List<Book>) repository.findAll();
	    }
	
		// Hae kirja ID:llä
	    @GetMapping("/books/{id}")
	    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
	    	return repository.findById(bookId);
	    }       
	    
//	    @GetMapping("/add")
//	    public String lisaaKirja(Model model){
//	    	model.addAttribute("book", new Book());
//	    	model.addAttribute("luokat", crepository.findAll());
//	        return "/addbook";
//	    }
	    
	    @PostMapping("/books")
	    public List<Book> tallennaKirja(@RequestBody Book book){
	    	System.out.println("RestBookController: tallennaKirja");
	    	repository.save(book);
	        return (List<Book>) repository.findAll();
	    }
	    
//	    @GetMapping("/edit/{id}")
//	    public String muokkaaKirja(@PathVariable("id") Long bookId, Model model){
//	    	Book book = repository.findById(bookId)
//	    			.orElseThrow(() -> new IllegalArgumentException("Väärä kirja ID:" + bookId));
//	    	model.addAttribute("book", book);
//	    	model.addAttribute("luokat", crepository.findAll());
//	        return "/editbook";
//	    }
//	    
//	    @PostMapping("/edit/{id}")
//	    public String paivitaKirja(@PathVariable("id") long id, @Valid Book book, 
//	      BindingResult result, Model model) {
//	        if (result.hasErrors()) {
//	            book.setId(id);
//	            return "/editbook";
//	        }            
//	        repository.save(book);
//	        return "redirect:/index";
//	    }
//		
//	    @GetMapping("/delete/{id}")
//	    public String poistaKirja(@PathVariable("id") Long bookId, Model model) {
//	    	repository.deleteById(bookId);
//	        return "redirect:/index";
//	    }
	 
	 
}
