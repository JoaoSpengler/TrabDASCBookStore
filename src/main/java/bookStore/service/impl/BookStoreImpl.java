package bookStore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookStore.model.Book;
import bookStore.repository.IBookStore;
import bookStore.service.BookStoreService;

@Service
public class BookStoreImpl implements BookStoreService{

	@Autowired
	private IBookStore bookStoreRepository;
	
	@Override
	public List<Book> getAll() {
		return bookStoreRepository.findAll();
	}

	@Override
	public void save(Book book) {
		bookStoreRepository.save(book);
	}

	@Override
	public void remove(Book book) {
		bookStoreRepository.delete(book);
	}

	@Override
	public Book findById(long id) {
		Optional<Book> response = bookStoreRepository.findById(id);
		if(response.isPresent())
			return response.get();
		return null;
	}
	
}
