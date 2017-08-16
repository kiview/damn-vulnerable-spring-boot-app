yieldUnescaped '<!DOCTYPE html>'
html {
    head {
        title('Damn Vulnerable Spring Boot App')
    }
    body {
        div(class: 'container') {
            form(method: "GET", action: "/books/detail") {
                input(name: "id", placeholder: "Search for a book id!")
            }
            br
            table(border:1) {
                thead {
                    tr {
                        td 'ID'
                        td 'Name'
                        td 'Author'
                      }
                    }
                tbody {
                    books.each { book ->
                        tr {
                            td {
                                a(href: "/books/detail?id=${book.id}", "${book.id}")
                            }
                            td {
                                a(href: "/books/detail?id=${book.id}", "${book.name}")
                            }
                            td {
                                a(href: "/books/detail?id=${book.id}", "${book.author}")
                            }
                        }
                    }
                }
            }
        }
    }
}