package de.gdaag.corp.rp.damnvulnerablespringbootapp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.ResultSetExtractor
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

import javax.annotation.PostConstruct
import java.sql.ResultSet
import java.sql.SQLException

@RequestMapping("/sql")
@Controller
class SqlInjectionController {

    @Autowired
    JdbcTemplate jdbcTemplate

    @RequestMapping("/")
    def home() {
        def books = loadBooks()
        new ModelAndView("views/sql/home", [books: books])
    }


    @PostConstruct
    def bootstrap() {
        initDb()


        def books = [
                new Book(id: 1, name: "Moby Dick", author: "Herman Melville")
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
