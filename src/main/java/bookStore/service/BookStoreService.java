package bookStore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import bookStore.model.Book;

@Service
public interface BookStoreService {
	
	List<Book> getAll();

	void save(Book book);
	
	void remove(Book book);
	
	Book findById(long id);
}
