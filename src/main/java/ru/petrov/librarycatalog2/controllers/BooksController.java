package ru.petrov.librarycatalog2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.petrov.librarycatalog2.models.Book;
import ru.petrov.librarycatalog2.models.Person;
import ru.petrov.librarycatalog2.services.BooksService;
import ru.petrov.librarycatalog2.services.PeopleService;

import javax.validation.Valid;

@Controller
@RequestMapping("books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findOne(id));
        if (booksService.findOne(id).getPerson() != null) {
            model.addAttribute("person", booksService.findOne(id).getPerson());
        } else if (!peopleService.findAll().isEmpty()) {
            model.addAttribute("people", peopleService.findAll());
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/setowner")
    public String updateSetOwner(@ModelAttribute("book") @Valid Book book,
                                 BindingResult bindingResult,
                                 @ModelAttribute("person") Person person,
                                 @PathVariable("id") int id) {

        booksService.setPerson(id, person);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/clearowner")
    public String updateClearOwner(@ModelAttribute("book") @Valid Book book,
                                   BindingResult bindingResult,
                                   @PathVariable("id") int id) {
        booksService.setPerson(id, null);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
}