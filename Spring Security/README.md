# Spring Security

#### Table of Contents

- [InMemory Authentication](#inmemory-authentication)
- [JDBC Authentication](#jdbc-authentication)
- [Spring Security using ORM](#spring-security-using-orm)
- [Spring Rest Security using JWT](#spring-rest-security-using-jwt)

###### 11-December-2021

**JAAS :** Java Authentication and Authorization Service

**Authentication :** Authentication is validating username and password. Authentication is 3 types.

1. **InMemory Authentication :** Using RAM (temp) to store user data [Testing purpose only]
2. **JDBC Authentication :** Using Database + JDBC(SQL) to store and validate user data
3. **UserDetails Service / ORM Authentication :** Using Database + Data JPA to store and validate user data

**Authorization :** Validate User Role. Authorization is 3 types.

1. **permitAll :** URL can be accessed by everyone [ No login is required ]\
   ex: /home, /register, /contactUs, /welcome

2. **authenticated :** URL can be accessed only after login [only login + any role is valid]\
   ex: /profile, /inbox, /settings, /logout

3. **hasAuthority :** Must Login + Matching Role to access URL\
   ex: /approveLoan [ Login + Role(MANAGER) ]

Ex: Bank Application:-\
Customer --> Open A/c, checkBal , Req Loan\
Manager --> View Loan Req, Approve Loan\
Cashier --> Deposite, Withdraw ..etc\

**Role :** Decides what are operations allowed to a user.

- Filters are components used to execute any logic over request(Pre-Process)/response(Post-Process) connected with a servlet.
- Spring Security has given pre-defined filter named as: DelegatingFilterProxy.

#### [InMemory Authentication](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Spring%20Security/inMemoryAuthentication)

###### 13-December-2021

**InMemoryAuthentication :** Storing data inside RAM(Random Access Memory).it is used for Testing Purpose only, not used in production.

- We must define one implementation class for "WebSecurityConfigurerAdapter" (Abstract Class) and override 2 methods:
  - configure(AuthenticationManagerBuilder) //Authenitcation\
  - configure(HttpSecurity) //Authorization

user data (inside RAM)

| username | password | authority |
| -------- | -------- | --------- |
| SAM      | SAM      | ADMIN     |
| SYED     | SYED     | EMPLOYEE  |
| AJAY     | AJAY     | STUDENT   |

**PasswordEncoder :** {noop} -> NoOperation Password Encoder.

```java
auth.inMemoryAuthentication()
.withUser("SAM")
.password("{noop}SAM").authorities("ADMIN");
```

| URL      | PageName         | Access Level                                           |
| -------- | ---------------- | ------------------------------------------------------ |
| /welcome | WelcomePage.html | Every one can access this, no security {**permitAll**} |
| /home    | HomePage.html    | Only after login (Any User Role) {**authenticated**}   |
| /emp     | EmpPage.html     | Login + Role(EMPLOYEE) { **hasAuthority("EMPLOYEE")**} |
| /admin   | AdminPage.html   | Login + Role(ADMIN) { **hasAuthority("ADMIN")**}       |
| /denied  | DeniedPage.html  | Access Denied Page [**403 Forbidden**]                 |

- antMatchers() compares Request URL with given input value, if matching then execute selected Access Level.
- When User access any URL which is Out of his Role, then FC throws Error : Forbidden, status=403

#### InMemoryUserDetailsManager with dynamic users (Not discussed in class)

- to create users during the runtime by using inMemory Authentication we can use InMemoryUserDetailsManager
- the good thing about the auto-configuration is that any bean of type UserDetailsService will automatically be wired to the Authentication managers. In this case, I defined a simple InMemoryUserDetailsManager so that I can add or remove users from it on the fly.

Anywhere at your @Configuration class, add the following.

```java
@Bean
public InMemoryUserDetailsManager getInMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager();
}
```

If you look at the implementation of InMemoryUserDetailsManager it is a type of UserDetailsService and has methods to create and manage user entries. So let’s add logic to create users.

```java
@RestController
public class UserController {

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    public UserController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password) {
        inMemoryUserDetailsManager.createUser(User.withUsername(username).password("{noop}" + password).roles("USER").build());
        return username + " Created!";
    }
}
```

- This /register endpoint will help us add users to the system dynamically. Even though the builder methods have a lot to offer, I’m restricting the use to just username, password and a default role.

- Because the endpoint won’t call itself, I created a simple registration form at src/main/resources/static/register.html. Here is the content of that file.

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
    />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Register a new user</title>
    <link
      rel="stylesheet"
      href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.3/css/bootstrap.min.css"
    />
  </head>
  <body class="container mt-5">
    <h1 class="text-center">Register</h1>
    <form method="post" action="/register" autocomplete="off">
      <div class="form-group">
        <label for="username">Username</label>
        <input
          type="text"
          class="form-control"
          id="username"
          name="username"
          autocomplete="new-password"
          placeholder="Enter username"
        />
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input
          type="password"
          class="form-control"
          id="password"
          name="password"
          placeholder="Enter password"
          autocomplete="new-password"
        />
      </div>
      <input type="submit" class="btn btn-primary btn-block btn-lg" />
    </form>
  </body>
</html>
```

###### 16-December-2021

#### [JDBC Authentication](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Spring%20Security/JDBC%20Authentication)

- We should write all Security related code only in one class.\
  ie SecurityConfig (that extends WebSecurityConfigurerAdapter)

- Override 2 methods : configure(AuthenticationManagerBuilder) [Authentication]\
   configure(HttpSecurity) [Authorization]

- We can use PasswordEncoder:-
- Storing Plain text password inside Database is not a good Approch.
- Better Encode this (Convert into UnReadable format). ex: SAM -> Hrgs%2BS$
- There is no Decode concept, you input at Login time is encoded and compared with DB value.

- For all these security concept only one Dependency we need to add\
  ie : Spring Security

##### SQL Commands to Create Database

```sql

mysql> drop database boot9am;
Query OK, 1 row affected (0.12 sec)

mysql> create database boot9am;
Query OK, 1 row affected (0.02 sec)

mysql> use boot9am;
Database changed

mysql> show tables;
Empty set (0.00 sec)

```

##### SQL Commands to Create Table

```sql
create table users_tab(
   uid int,uname varchar(25),
   upwd varchar(70), uenable int,
   urole varchar(20));

Query OK, 0 rows affected (0.05 sec)
```

##### Create a java class to encode the password

```java
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {

	public static void main(String[] args) {
		String pwd = "AJAY";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encPwd = encoder.encode(pwd);
		System.out.println(encPwd);
	}
}

```

##### SQL Commands to Insert Users into Table

```sql
 insert into users_tab values(1,'sam','$2a$10$AMMx5M.0VUUL1PfePYI8eu2eJKyscfj2dOY/HhVZknRksRJFDw9ZW',1,'ADMIN');
 insert into users_tab values(2,'syed','$2a$10$5ELjJk0LNqrfs3QdfUEDEe/5HLdtbXbAAXgv4g9UAuSaAfgO0UM1.',1,'EMPLOYEE');
 insert into users_tab values(3,'ajay','$2a$10$ZEnM2pedJxZKmjTtXmRdX.NSxH82t65GqEfQBEjZOAPM2tAS.tfUq',1,'STUDENT');
```

```sql
mysql> show tables;
+-------------------+
| Tables_in_boot9am |
+-------------------+
| users_tab         |
+-------------------+
1 row in set (0.01 sec)

mysql> select * from users_tab;
+------+-------+--------------------------------------------------------------+---------+----------+
| uid  | uname | upwd                                                         | uenable | urole    |
+------+-------+--------------------------------------------------------------+---------+----------+
|    1 | sam   | $2a$10$AMMx5M.0VUUL1PfePYI8eu2eJKyscfj2dOY/HhVZknRksRJFDw9ZW |       1 | ADMIN    |
|    2 | syed  | $2a$10$5ELjJk0LNqrfs3QdfUEDEe/5HLdtbXbAAXgv4g9UAuSaAfgO0UM1. |       1 | EMPLOYEE |
|    3 | ajay  | $2a$10$ZEnM2pedJxZKmjTtXmRdX.NSxH82t65GqEfQBEjZOAPM2tAS.tfUq |       1 | STUDENT  |
+------+-------+--------------------------------------------------------------+---------+----------+
3 rows in set (0.00 sec)
```

##### configure(AuthenticationManagerBuilder auth) method

```java

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("SELECT uname,upwd,uenable FROM users_tab WHERE uname=?")
		.authoritiesByUsernameQuery("SELECT uname,urole FROM users_tab WHERE uname=?")
		.passwordEncoder(passwordEncoder);
	}

```

###### 17-December-2021

#### [Spring Security using ORM](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Spring%20Security/Spring%20Securiy%20Using%20ORM)

Spring Security using ORM involves in four steps.

1. User Registration Process
1. User Login and PasswordEncoder
1. Customer Login Page
1. HttpSession and CSRF Token

##### User Registration Process

- Create an User Entity Class
- Create an Repository to the User Entity Class
- Create an Service Class to the User Entity to save the user.
- Create controller class to recieve User Object.
- Create a User Registration Page.

##### set Login and PasswordEncoder

- make our User Service Class to implements UserDetailsService Inteface and implement loadByUsername(String username)
  method.

UserDetailsService(I) : This interface is given by Spring Security.This is called by SecurityConfig, when we click on login button. It is used to load User data from DB and convert into Spring Security User Object.

- User(C) one is given by Programmer (entity class), One is given by Spring Security.
- Roles in String format must be Converted into Spring Security Format.\
   ie GrantedAuthority(I) and Impl : SimpleGrantedAuthority(C).
- SecurityConfig validates User data\
  if invalid, goto Login Page and display Error message\
  else create HttpSession, Stores user data, goto Default Success URL(Home Page)
- On Logout, come back to login page. (Destory HttpSession).

###### 18-December-2021

#### Security Internals

- Spring Security is designed over Servlets API.
- A Login page is generated and given default to Users

```html
<form method="post" action="/login">
  Please sign in Username:
  <input type="text" id="username" name="username" /> Password:
  <input type="password" id="password" name="password" />
  <button type="submit">Sign in</button>
</form>
```

- On Click Login Submit button, UsernamePasswordAuthenticationFilter is called. and method #attemptAuthentication() is called.
- It reading un,pwd and validating using Any one Authentication Manager given by programmer
- Principal(I) Sun Microsersystem : To hold current user data\
   spring Security has given subtype : Authentication(I) [username + roles]\
   Impl class is : UsernamePasswordAuthenticationToken(C)
- once it is created, SessionFixationProtectionStrategy#onAuthentication() method
- Finally redirect to defaultSuccessurl
  UsernamePasswordAuthenticationFilter#attemptAuthentication() ====> SessionFixationProtectionStrategy#onAuthentication()
- On Click Logout link\
   SecurityContextLogoutHandler#logout() method is called.
- This method is reading existed HttpSession and calling invalidate()/destory.

#### Adv Java Points

1. Creating new Session (When user login successful)

```java
HttpSession ses = request.getSession();
	Or
HttpSession ses = request.getSession(true);
```

2. Reading Existed Session

```java
HttpSession ses = request.getSession(false);
```

3. Add/Put data in session

```java
 ses.setAttribute(String key, Object value);
```

4. Read data from session

```java
Object value = ses.getAttribute(key);
```

5. Destory Session (on logout)

```java
   ses.invalidate();
```

6. Modify Max Inactive time for one session

```java
   ses.setMaxInactiveInterval(int sec);

   ses.setMaxInactiveInterva(-1); //never expire

   ses.setMaxInactiveInterva(600); //10 mins exp.
```

- Spring Security supports to modify Logout URL: using AntPathRequestMatcher
- You logged in using ADMIN role and trying to access EMPLOYEE data which is not for you. Then Front Controller throw 403-Forbidden.
  Just to customize (Show our own error page) use accessDeniedPage("/denied"). here "/denied" is our custom end point and it can be anything. need not to be denied only.

- SecurityContextHolder(C) #getContext() : SecurityContext

- SecurityContext(I) #getAuthentication(): Authentication

```java
// simple code
SecurityContext context = SecurityContextHolder.getContext();
Authentication auth = context.getAuthentication();

// short format
Authentication auth = SecurityContextHolder.getContext().getAuthentication();

// current username and roles
System.out.println(auth.getName()); //username
System.out.println(auth.getAuthorities()); //roles

// for only username use Principal p as method param in Controller
System.out.println(p.getName()); //only username
```

- This process is even recomanded by Spring Security to read current user data.Works in both MVC and REST Security.

##### Example Code

```java
@Controller
public class HomeController {
	...

	@GetMapping("/home")
	public String showHome() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName()); //username
		System.out.println(auth.getAuthorities()); //roles
		return "HomePage";
	}

	@GetMapping("/emp")
	public String showEmp(Principal p) {
		System.out.println(p.getName()); //only username
		return "EmpPage";
	}
	...
}
```

#### Thymleaf

We can directly access Authentication object at Thymeleaf UI using

```java
<dependency>
	<groupId>org.thymeleaf.extras</groupId>
	<artifactId>thymeleaf-extras-springsecurity5</artifactId>
</dependency>
```

At HTML tag level:

```html
<html ... xmlns:sec="https://www.thymeleaf.org/extras/spring-security5">
  Logged user:
  <span sec:authentication="name"></span>
  Roles:
  <span sec:authentication="principal.authorities"></span>
</html>
```

##### Sample : HomePage.html

```html
<html
  xmlns:th="https://www.thymeleaf.org/"
  xmlns:sec="https://www.thymeleaf.org/extras/spring-security5"
>
  <head>
    <title>SECURITY APP</title>
  </head>
  <body>
    WELCOME TO HOME PAGE!
    <br />
    Logged user: <span sec:authentication="name"></span>, Roles:
    <span sec:authentication="principal.authorities"></span>
    <br />
    <a th:href="@{/logout}"> LOGOUT </a>
  </body>
</html>
```

###### 20-December-2021

#### Using HttpSession in Spring Security

- HttpSession is created and destoryed by Spring Security
- We can store our user data,read and modify where you want.
- We can print data at UI using implicite object session.\
  ${session.key} : is equals to session.getAttribute("key");

- Session Stores data in KEY=VAL Format
- KEY is String Type, VALUE is Object Type. It means we can store any type of data, ex: Full User Object, Image Links, Collection data..etc
- Only User specific data and used at multiple pages, such data store in Session object. Dont store all types of data. ex: userName, EmailId...etc

#### Cross-Site Request Forgery (CSRF)

- A link/Web page created by a different person(attacker) and send to your browser using different channles ex: Ads, Spam Emails, links..etc

- A Request prepared by some one is executed by you and server processes it. That is called as CSRF Attack.

```text
Q) How this is Handled by Spring Security?
A) CSRF Token is created when user loads a Web page.
   This token is stored for HttpSession of such user.
   Once User submit the request along with Request even Token also submitted.
   Server validates such token and process request..

   If attacker creates a Webpage that may not have any Token.
   If provided that may not match for such user. Bcoz token is specific to request/user.
```

[ctrl+shift+T and ctrl+O]

CsrfRequestDataValueProcessor(C) : This class validates token when request comes

CsrfToken(I) - DefaultCsrfToken(C) : This class is used to indicate token data

CsrfTokenRepository(I) - HttpSessionCsrfTokenRepository(C) : This class uses HttpSession, creates new token, stores token at session, returns token when it need to comapre with current request.

Example Token:\
f18b9fe2-33f8-4ab3-9777-80a2aa3a7f77\
722ffe99-1663-4b1a-aa26-2ab1e1615428

- This is internally : UUID.randomUUID().toString()
- This token exist in browser area/Web page as a hidden input, which can not be read by other systems/users.

  Sample code:

```html
<input
  name="_csrf"
  type="hidden"
  value="390b17f6-bec9-4297-b6fa-22a32c1428ad"
/>
```

#### Spring Rest Security using JWT

**Token :**- It is a generated number using User details, Provider and one secret key. Once login it is generated and on logout it is removed.

- JJWT : Java JWT API, used for any Java Technologies.
- Generated token = id, subject, issure, date, sign(alg) + secret key.
- Claims : Reading token data by providing details (token + secret)

```xml
   <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>
    <dependencies>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.1</version>
        </dependency>

        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.0</version>
        </dependency>

    </dependencies>
```

#### jwt code

```java

public class JwtUtil {

	public static void main(String[] args) {
		//1. generate token
		String secret = "SECRET";

		//xxx.yyy.zzzz
		String token =
				Jwts.builder()
				.setId("123456") //userid
				.setSubject("ajay") //username
				.setIssuer("MYAPP") //JWT provider
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1)))
				.signWith(SignatureAlgorithm.HS256, secret.getBytes())
				.compact();

		//String token ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NTYiLCJzdWIiOiJhamF5IiwiaXNzIjoiTklULUhZRCIsImlhdCI6MTY0MDMxNzUwMCwiZXhwIjoxNjQwMzE3NTYwfQ.d88xnSpWaLeUvnl-IAbV8q8a2NpNqk6O9KpLMfw8Z0g";
		System.out.println(token);

		Claims claims =
				Jwts.parser()
				.setSigningKey(secret.getBytes())
				.parseClaimsJws(token)
				.getBody();

		System.out.println(claims.getId());
		System.out.println(claims.getSubject());
		System.out.println(claims.getIssuer());

	}
}
```

- Client applications has to store token data. Ex Browser session area/local storage. Browser submits token along with request.Server validates token, if valid then it will process request.

- OncePerRequestFilter : (only after login) A filter which gets executed once per one request.

  - Read Authorization Header
  - validate Token
  - if valid create Authentication object\
   Principal(I) - Authentication(I) - UsernamePasswordAuthenticationToken(C)
  - No session is used, so set to current request and pass to FC.

- AuthenticationManager : during login filter is not used. In this case UserDetailsService must be called manually and generate token
  if user is valid.

- AuthenticationEntryPoint: Incase of any Exception while login/access any Resource then SC_UNAUTHORIZED (401), "Unauthorized User!"
