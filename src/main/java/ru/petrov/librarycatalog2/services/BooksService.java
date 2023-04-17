package ru.petrov.librarycatalog2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.petrov.librarycatalog2.models.Book;
import ru.petrov.librarycatalog2.models.Person;
import ru.petrov.librarycatalog2.repositories.BooksRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {

    //TIME_OVERSTAY 10 day
    private final long TIME_OVERSTAY = 10*24*60*60*1000;
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findByOwner(Person person) {
        List<Book> byPerson = booksRepository.findByPerson(person);
        if (byPerson != null) {
            for (Book book : byPerson) {
                if (book.getDateOfIssue()!=null && (new Date().getTime() - book.getDateOfIssue().getTime()) > TIME_OVERSTAY) {
                    book.setOverstay(true);
                }
            }
        }
        return byPerson;
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
        if (person == null) {
            updatedBook.setDateOfIssue(null);
        } else {
            updatedBook.setDateOfIssue(new Date());
        }
        updatedBook.setOverstay(false);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}
