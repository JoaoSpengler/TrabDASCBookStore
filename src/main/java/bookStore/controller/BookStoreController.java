package bookStore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bookStore.model.Book;
import bookStore.service.BookStoreService;

@Controller
@RequestMapping("/bookStore")
public class BookStoreController {

	@Autowired
	private BookStoreService service;
	
	@GetMapping
	public ModelAndView index() {
		return new ModelAndView("bookStore/index", "books", service.getAll());
	}
	
	@GetMapping("/new")
	public ModelAndView form(@ModelAttribute Book book) {
		return new ModelAndView("bookStore/form");
	}
	
	@PostMapping(params="form")
	public ModelAndView save(@Valid Book book) {
		service.save(book);
		return new ModelAndView("redirect:/bookStore");
	}
	
	@GetMapping(value="/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Book book) {
		return new ModelAndView("bookStore/form", "book", book);
	}
	
	@GetMapping(value="/remove/{id}")
	public ModelAndView remove(@PathVariable("id") Book book) {
		service.remove(book);
		return new ModelAndView("redirect:/bookStore");
	}
	
}
