package com.groovycoder.dvsba.books;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.*;

@RequestMapping("/books")
@Controller
class BookController {

    private final JdbcTemplate jdbcTemplate;

    public BookController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping("/")
    public ModelAndView home() {
        List<Book> books = loadBooks();

        Map<String, Object> model = new HashMap<>();
        model.put("books", books);

        return new ModelAndView("views/sql/home", model);
    }

    @RequestMapping("/detail")
    public ModelAndView detail(@RequestParam(value = "id") String id) {
        String sql = "SELECT * FROM books WHERE id=" + id;
        final Book[] book = new Book[1];
        jdbcTemplate.query(sql, (ResultSetExtractor) rs -> {
            if (rs.next())
                book[0] = new Book(rs.getLong(1), rs.getString(2), rs.getString(3));

            return null;

        });

        Map<String, Object> model = new HashMap<>();
        model.put("book", book[0]);

        return new ModelAndView("views/sql/detail", model);
    }

    @PostConstruct
    private void bootstrap() {
        initDb();

        List<Book> books = Arrays.asList(
                new Book(1L, "Moby Dick", "Herman Melville"),
                new Book(2L, "Unsichtbare Spuren", "Andreas Franz"),
                new Book(3L, "Das Paket", "Sebastian Fitzek")
        );
        saveBooks(books);
    }

    private void saveBooks(List<Book> books) {
        books.forEach(book ->
                jdbcTemplate.update("INSERT INTO books (id, `name`, author) values (?, ?, ?)",
                        book.id, book.name, book.author)
        );
    }

    private List<Book> loadBooks() {

        return jdbcTemplate.query("SELECT * FROM books", rs -> {
            List<Book> books = new LinkedList<>();

            while (rs.next()) {
                books.add(new Book(rs.getLong(1), rs.getString(2), rs.getString(3)));
            }

            return books;
        });

    }

    private void initDb() {
        jdbcTemplate.execute("CREATE TABLE books (id NUMBER, name VARCHAR(255), author VARCHAR(255))");
    }

}
