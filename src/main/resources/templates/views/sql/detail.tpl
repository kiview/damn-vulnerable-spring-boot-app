yieldUnescaped '<!DOCTYPE html>'
html {
    head {
        title('Damn Vulnerable Spring Boot App')
    }
    body {
        div(class: 'container') {
            a(class: 'brand',
                href: '/books/',
                'List')
            br()
            br()
            p "ID: ${book.id}, Name: ${book.name}, Author: ${book.author}"
        }
    }
}