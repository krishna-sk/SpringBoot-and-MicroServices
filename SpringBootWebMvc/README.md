# Spring Boot Web MVC

### [SpringBootWebMvcFirstApp](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/SpringBootWebMvc/SpringBootWebMvcFirstApp)

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

### [SpringBootWebMvcJSPFirstApp](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/SpringBootWebMvc/SpringBootWebMvcJspFirstApp)

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

### Spring Boot Web MVC + Thymeleaf UI

### [SpringBootWebMvcThymeleafBasic](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/SpringBootWebMvc/SpringBootWebMvcThymeleafBasic)

**JSP : Java Server Pages (JASPER)**

- Servlets are heavy weight objects (which takes lot of memory).
- Every JSP file internally one Servlet.
- JSP even converts static data into Java code.
- Lot of object are created while executing it.
  Servlet object, config, context, request, response...etc
- **Recomanded to keep single servlet in project, ie only dispatcher servlet.**

**Thymeleaf :**

- It is java based runtime engine/ UI Technology.
- Static data will not be processed by Thymeleaf, only dynamic data is processed.
- It will not be converted to any servlet class. So no additional memory required.
- It is a simple HTML file internally.
- only Dynamic tags are processed and replaced with its result by Thymeleaf Runtime Engine. (https://www.thymeleaf.org/).

Ex: static content

```html
<h3>Welcome</h3>
```

Ex: dynamic content

```js
[[${service.printDate()}]]
```

- Spring Boot supports Integration with Thyemeleaf UI:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

- Thymeleaf Configuration is done by Spring boot (Autoconfiguration)\
  **default prefix = classpath:/templates/**\
  **[classpath is src/main/resources folder]**\
  **default suffix = .html**
- Thymeleaf supports resource reading by default.

```textile
  static folder
  js
  css
  images
  ..etc (all we can access)
```

### Create Executable WAR and run

**Step : 1** Create WAR file

- Right click on project > Run as > Maven Clean
- Right click on project > Run as > Maven Install

**Step : 2** Refresh project (F5)

- Right click on project > Refresh

**Step : 3** Check WAR file

- Goto target folder > check for file name \_\_\_\_.war
- file name looks like: (artifactId)-(version).(packaging)
- ex: SpringBoot2WebMvcThymeleafBasic-1.0.war

**Step : 4** Open cmd location

- Right click on project > Proeprties > Click on Arrow Symbol > [show in system explorer]
- open target folder
- open cmd location for same
- (or click inside addressbar > enter cmd > press enter key)

**Ex:** D:\SB9AM_AUG2021\SpringBoot2WebMvcThymeleafBasic\target>

**Step : 5** Run server with your inputs

- java -jar SpringBoot2WebMvcThymeleafBasic-1.0.war

- java -jar SpringBoot2WebMvcThymeleafBasic-1.0.war --spring.profiles.active=prod

- java -jar SpringBoot2WebMvcThymeleafBasic-1.0.war --server.port=9900

- Enter URL: http://localhost:9900/show

### Controller

- It is a class that process request.
- One Method - One Request process concept\
  "\login" -- login()\
  "\register" -- register()

#### Rules for defining controller

1. Class must have @Controller, else object is not created, request is not processed.
2. If we use @Component/@Service .. in place of @Controller, object is created, request is not processed.
3. Every method exist in Controller, link with path/url + HttpMethod using @RequestMapping\
   Ex:

```java
@Controller
class UserController{

  @RequestMapping(value="/login", method=RequestMapping.GET)
  public String showLogin(){
       return "/login";
  }

  @RequestMapping(value="/signUp", method=RequestMapping.GET)
  public String showSignUp(){
       return "/login";
  }
```

4. Default Method is GET, if we did not specify any RequestMethod.

```java
@RequestMapping(value="/login", method=RequestMapping.GET) ,
@RequestMapping(value="/login") both are same
```

5. URL is case senstive. /show, /SHOW are different.

```java
@Controller
class UserController{

  @RequestMapping(value="/login")
  public String showLogin(){
      return "/login";
  }

  @RequestMapping(value="/LOGIN")
  public String showSignUp(){
      return "/signup";
  }
```

6. Duplicate URL/Path is allowed with combination of HTTP Method. (Combination should not be duplicated)

```java
@RequestMapping(value="/login",method=RequestMapping.GET)
  public String showLogin1(){
      return "/login1";
  }

  @RequestMapping(value="/login",method=RequestMapping.POST)
  public String showLogin2(){
      return "/login2";
  }
```

7. Default path URL is /. If we provide only @RequestMapping.

```java
@RequestMapping(value="/", method=RequestMapping.GET) ,
@RequestMapping(value="/")
@RequestMapping are same
```

8. We can provide multiple url to one Controller Method. All paths must be either GET or POST.

```java
@RequestMapping(value={"/","/login","/welcome"},method=RequestMethod.GET)
public String showLogin(){
    return "/login";
}
```

9. We can even use multiple HttpMethod types for one URL.

```java
@RequestMapping(value="/show",method={RequestMethod.GET,RequestMethod.POST})
public String showLogin(){
    return "/login";
}
```

10. Even mixing of point 8 and point 9 is allowed.

```java
@RequestMapping(value={"/","/login","/welcome"},method={RequestMethod.GET,RequestMethod.POST})
public String showLogin(){
    return "/login";
}

```

11. We can define one normal java method inside controller (without @RequestMapping). It can never be executed by front controllre for a request.\
    **Use Case :** If two or more controller methods having common code then user normal java method for common code and call it where you want.

```java
@Controller
class UserController{

  @GetMapping("/show)       // @RequestMapping(value="/login",method=RequestMapping.GET) both are same
    getData();
    return "show";
  }

  @PostMapping("/login)    // @RequestMapping(value="/login",method=RequestMapping.POST) both are same
  public String method2(){
    getData();
    return "login";
  }

  private String getData(){
    return "data";
  }

}
```

12. Multiple Controllers can be define for multiple modules in project.\
    **Ex:**\
    UserController\
    SearchController\
    EmployeeController\
    GridController\
    ... etc

13. RequestMapping is optional at class level.\
    **Recommended to provide one common path at class level using @RequestMapping**
 ```java
  @RequestMapping("/user)
  class UserController{

  }

  @RequestMapping("/search)
  class SearchController{

  }

```
14. Even we can define @RequestMapping("/") or @RequestMapping (which is valid) at controller class level.

```java
  @RequestMapping("/")
  class UserController{

  }
```

15. **Latest annotations used for HTTP Methods**\
    **GET -** @GetMapping("/login) , @RequestMapping(value="/login",method=RequestMapping.GET) are same\
    **POST -** @PostMapping("/login) , @RequestMapping(value="/login",method=RequestMapping.POST) are same

16. If we provide same path/http method type then application will <span style="color:red">fail to start.</span>
17. Default server port number is 8080. If we set to 80.(sever.port=80) then need not to enter port number in URL (http default port number is 80)\
    http://localhost:80/show, http://localhost/show are same if server.port=80
18. One Controller method should have only one time @RequestMapping

<span style="color:red">**Invalid**</span>

```java
class UserController(){
  @RequestMapping(value="/login")
  @RequestMapping(value="/show")  // application failed to start. Instead of this use point 8
  public String show(){

  }

}
```

19.We can provide same path at class and method level too.

```java
@RequestMapping("/show")
class UserController(){

  @RequestMapping(value="/login")
  public String show(){

  }

}

// URL : http://localhost:8080/show/show
```

20. Do not provide any prefix and suffix while returning viewName.(viewName is case senstive)
21. Incase of Spring/servlets app, URL may contain project name. But here, no project name is used in URL. In spring boot default is set to "/".
    To set your own context path(project name in URL) use application.properties
    **Ex:** server.servlet.context-path=/project-name

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
