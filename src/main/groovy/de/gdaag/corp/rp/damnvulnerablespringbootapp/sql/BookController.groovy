package de.gdaag.corp.rp.damnvulnerablespringbootapp.sql

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

import javax.annotation.PostConstruct
import java.sql.ResultSet
import java.sql.SQLException

@RequestMapping("/books")
@Controller
class BookController {

    @Autowired
    JdbcTemplate jdbcTemplate

    @RequestMapping("/")
    def home() {
        def books = loadBooks()
        new ModelAndView("views/sql/home", [books: books])
    }

    @RequestMapping("/detail")
    def detail(@RequestParam(value = "id", required = true) String id) {
        def sql = "SELECT * FROM books WHERE id=${id}"
        Book book
        jdbcTemplate.query(sql, new ResultSetExtractor() {
            @Override
            Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next())
                    book = new Book(id: rs.getInt(1), name: rs.getString(2), author: rs.getString(3))

                return null

            }
        })

        new ModelAndView("views/sql/detail", ["book": book])
    }

    @PostConstruct
    def bootstrap() {
        initDb()


        def books = [
                new Book(id: 1, name: "Moby Dick", author: "Herman Melville"),
                new Book(id: 2, name: "Unsichtbare Spuren", author: "Andreas Franz"),
                new Book(id: 3, name: "Das Paket", author: "Sebastian Fitzek")
        ]
        saveBooks(books)
    }

    def saveBooks(List<Book> books) {
        books.each {
            jdbcTemplate.update("INSERT INTO books (id, `name`, author) values (?, ?, ?)", it.id, it.name, it.author)
        }
    }

    List<Book> loadBooks() {

        jdbcTemplate.query("SELECT * FROM books", new ResultSetExtractor<List<Book>>() {
            @Override
            List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {

                def books = []

                while(rs.next()) {
                    books.add(new Book(id: rs.getInt(1), name: rs.getString(2), author: rs.getString(3)))
                }

                return books
            }
        })

    }

    def initDb() {
        jdbcTemplate.execute("CREATE TABLE books (id NUMBER, name VARCHAR(255), author VARCHAR(255))")
    }

}
