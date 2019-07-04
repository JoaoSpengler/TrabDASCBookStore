package bookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bookStore.model.Book;

public interface IBookStore extends JpaRepository<Book, Long>{
	Book findByName(String nome);
}
