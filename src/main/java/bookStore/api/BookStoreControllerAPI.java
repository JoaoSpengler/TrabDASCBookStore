package bookStore.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookStore.model.Book;
import bookStore.service.BookStoreService;;

@RestController
@RequestMapping("/api/book")
public class BookStoreControllerAPI {
	@Autowired
	private BookStoreService service;
	
	@GetMapping
	public ResponseEntity<List<Book>> listBook(){
		List<Book> list = new ArrayList<>();
		try {
			list = service.getAll();
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return new ResponseEntity<List<Book>>(list,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Book book){
		service.save(book);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, 
									@RequestBody Book book){
		Book actualBoolk = service.findById(id);
		if(actualBoolk != null) {
			actualBoolk.setName(book.getName());
			actualBoolk.setAuthor(book.getAuthor());
			actualBoolk.setEdition(book.getEdition());
			service.save(actualBoolk);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id){
		Book actualBoolk = service.findById(id);
		if(actualBoolk != null) {
			service.remove(actualBoolk);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
}
