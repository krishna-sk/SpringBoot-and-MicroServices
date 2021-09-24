# Spring Boot Web MVC

#### Projects

[SpringBootWebMvcFirstApp](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/SpringBootWebMvc/SpringBootWebMvcFirstApp)\
[SpringBootWebMvcJSPFirstApp](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/SpringBootWebMvc/SpringBootWebMvcJspFirstApp)

- MVC is a design pattern used to develop web applications.\
  M - Model (Data)\
  V - View (UI/Display)\
  C - Controller (Class/request process)\
- At least one Servlet is required to develop web applications using Java.
- Web Browser can run/understand only : HTML,CSS and Javascript.
- Servlets --> (.java)-->(.class) ->service() output : HTML => Sent to browser
- Angular --> .ts ->(bundle) .js --> .js + .css + .html -> Sent to browser

###### Controller --> for a module in a project

Ex:

```textile
 --------------------------------
  Module      ControllerName
 --------------------------------
  User        UserController
  Search      SearchController
  Cart        CartController
  Payment     PaymentController
  ..etc
```

- HandlerMapping helps FC to identify one controller based on Path + httpMethod
- Controller returns ViewName(no Location and Extension of File)
  to be independent of UI technologies.

- Controlle provides Data(Stored inside Model-->Container)
  which is optional
- Expression Language : reads data from Container at runtime
  provide to UI page
  Ex: Welcome to ${varaible}\

Ex ViewName(String) : Home, Profile, Settings (Controller returns)

ViewResolver will add prefix (location) and suffix(extension)

```textile
ViewPage ==> Prefix   +  ViewName + suffix\
  	        /mypages/      Home     .jsp
```

JSP : Sun(Oracle)\
Thymeleaf : https://www.thymeleaf.org/\
freemarker : https://freemarker.apache.org/

- Non-Java UI : Angular/ReactJS

- In Spring Boot Autoconfiguration is provided for

  1. FrontCotroller
  1. ViewResolver (provide prefix and suffix using properties)
  1. HandlerMapping

- only code for : Controller, View pages and Model(I)

- Spring Boot provides servers too (Embedded Server) No need to Download and Install

  1. Apache Tomcat (default)
  1. JBoss undertow
  1. Eclipse Jetty

- **RequestMethod :** RequestMethod is enum given by web mvc having values like
  - GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
- RequestMethod default type is GET\
  @RequestMapping(value = "/show",method = RequestMethod.GET) and\
  @RequestMapping(value = "/show") are same.

- **@Controller :** @Controller is sub-type of @Component, i.e: Spring container (ServletWebServerApplicationContext) creates object while starting server.
- Spring container create object for all the controllers/components/service/repository at the time of application startup.
- Controller#method (it is like servlets#service method) called for every request.
- If we use @Component/@Service/@Repository inplace of @Controller
  then object is created but http protocol is not supported.
- DispatchServlet need not be configured in Spring boot.\
  Spring : AbstractAnnotationConfigDispatcherServletInitializer
- ViewResolver, object need not be configured,provide direct inputs using
  spring.mvc.view.prefix=/WEB-INF/pages/\
  spring.mvc.view.suffix=.jsp
- **Spring Core Basic Rule:**\
  **@Lazy**\
   Every Component class object is created while starting application only. To make this object creation on first request/ first method call/first access
  use annotation @Lazy(+@Component) over class or @Bean + @Lazy

#### Cases we see output as 404/Not Found

1. Controller is not under base-package
1. Controller class not having @Controller annotation
1. Request URL and Controller#url not matching
1. PageName not matching , Page not exist
1. folder is created inside main insted of webapp
1. provided spaces in properties file
1. If using JSP check tomcat-embeeded JASPER added or not?

---

## FAQ

**Q) In how many ways end user can make request using browser?**\
**A)** It can be made in 3 ways

- I. Enter URL in addressbar (GET)
- II. On Click HyperLink (<a> tag) (GET)
- III. Form submit (GET/POST)

**Q) Where request processing code exist? Why Controllers?**\
**A)** Controllers are classes (supports HTTP protocl),contains methods that supports request processing code.\
Every Request contains URL(Path) and HttpMethod (GET/POST)\
GET :- Fetch Data from Server to browser\
POST :- Create new Data/Send data to server from browser.

**Q) How can we link Request with one Controller#method?**\
**A)** using annotation\
@RequestMapping(value="/path",method=RequestMethod.\_\_\_)\
Ex: /emp + GET

```java
@Controller
class EmployeeController {
  @RequestMapping(value="/emp",method=RequestMethod.GET)
  public String showHome() {
    return "EmpHome";
  }
}
```

If we enter full URL: http://localhost:8080/emp in addressbar
above showHome() method is called. It loads EmpHome.html
