yieldUnescaped '<!DOCTYPE html>'
html {
    head {
        title('Damn Vulnerable Spring Boot App - Home')
    }
    body {
        h1 {
            yield 'Hello and Welcome to the DVSBA!'
        }
        p ("Take a look around! We have a ${$a(href:'/books/', "catalouge")} of our favourite books which you can browse. If you like it, leave a comment in the ${ $a(href: '/comments/', "comment section")}!")

    }
}