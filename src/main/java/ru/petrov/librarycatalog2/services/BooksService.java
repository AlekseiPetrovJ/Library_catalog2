package ru.petrov.librarycatalog2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.petrov.librarycatalog2.models.Book;
import ru.petrov.librarycatalog2.models.Person;
import ru.petrov.librarycatalog2.repositories.BooksRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findByOwner(Person person) {
        return booksRepository.findByPerson(person);
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public List<Book> findByStartName(String startName) {
        return booksRepository.findByNameStartingWith(startName);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        updatedBook.setPerson(booksRepository.getOne(id).getPerson());
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void setPerson(int id, Person person) {
        Book updatedBook = booksRepository.findById(id).orElse(null);
        updatedBook.setPerson(person);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}
