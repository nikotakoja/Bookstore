package fi.takoja.Bookstore.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fi.takoja.Bookstore.domain.Book;
import fi.takoja.Bookstore.domain.BookRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository; 
	
	@GetMapping({"/index","/", "/booklist"})
	public String kirjaListaus(Model model) {
		
		model.addAttribute("kirjat", repository.findAll());
		
		return "/booklist";
	
	}
	
    @GetMapping("/add")
    public String lisaaKirja(Model model){
    	model.addAttribute("book", new Book());
        return "/addbook";
    }
    
    @PostMapping("/save")
    public String tallennaKirja(Book book){
        repository.save(book);
        return "redirect:/booklist";
    }
    
    @GetMapping("/edit/{id}")
    public String muokkaaKirja(@PathVariable("id") Long bookId, Model model){
    	
    	Book book = repository.findById(bookId)
    			.orElseThrow(() -> new IllegalArgumentException("Väärä kirja ID:" + bookId));
    	
    	model.addAttribute("book", book);
        return "/editbook";
    }
    
    @PostMapping("/edit/{id}")
    public String paivitaKirja(@PathVariable("id") long id, @Valid Book book, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            return "update-user";
        }            
        repository.save(book);
        return "redirect:/index";
    }
	
    @GetMapping("/delete/{id}")
    public String poistaKirja(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:/index";
    }

}
