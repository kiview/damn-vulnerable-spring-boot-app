yieldUnescaped '<!DOCTYPE html>'
html {
    head {
        title('Spring Boot - Groovy templates example')
        link(rel: 'stylesheet', href: '/css/bootstrap.min.css')
    }
    body {
        div(class: 'container') {
            a(class: 'brand',
                href: 'http://beta.groovy-lang.org/docs/groovy-2.3.2/html/documentation/markup-template-engine.html',
                'Groovy - Template Engine docs')
            br()
            a(class: 'brand',
                href: 'hhttp://projects.spring.io/spring-boot/') {
                yield 'Spring Boot docs'
            }
            br()
            br()
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
                            td book.id
                            td "${book.name}"
                            td "${book.author}"
                        }
                    }
                }
            }
        }
    }
}