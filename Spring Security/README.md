# Spring Security

#### Table of Contents

- [InMemory Authentication](#inmemory-authentication)
- [JDBC Authentication](#jdbc-authentication)
- [Spring Security using ORM](#spring-security-using-orm)

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

#### [InMemory Authentication]()

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
#### [JDBC Authentication]()

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
#### Spring Security using ORM

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

##### ser Login and PasswordEncoder

- make our User Service Class to implements UserDetailsService Inteface and implement loadByUsername(String username) 
 method.

UserDetailsService(I) :  This interface is given by Spring Security.This is called by SecurityConfig, when we click on login button. It is used to load User data from DB and convert into Spring Security User Object.

- User(C) one is given by Programmer (entity class), One is given by Spring Security.
- Roles in String format must be Converted into Spring Security Format.\
   ie GrantedAuthority(I) and Impl : SimpleGrantedAuthority(C).
- SecurityConfig validates User data\
  if invalid, goto Login Page and display Error message\
  else create HttpSession, Stores user data, goto Default Success URL(Home Page)
- On Logout, come back to login page. (Destory HttpSession).

#### Security Internals

- Spring Security is designed over Servlets API.
- A Login page is generated and given default to Users

```html
<form method="post" action="/login">
                 Please sign in
Username: <input type="text" id="username" name="username">
Password:  <input type="password" id="password" name="password" >
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
UsernamePasswordAuthenticationFilter#attemptAuthentication()  ====> SessionFixationProtectionStrategy#onAuthentication()
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
```java
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
-  Spring Security supports to modify Logout URL: using AntPathRequestMatcher
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
<html ... xmlns:sec="https://www.thymeleaf.org/extras/spring-security5" >

Logged user: <span sec:authentication="name"></span>
Roles: <span sec:authentication="principal.authorities"></span>
```
##### Sample : HomePage.html
```html
<html xmlns:th="https://www.thymeleaf.org/"
		xmlns:sec="https://www.thymeleaf.org/extras/spring-security5"
>
	<head>
		<title>SECURITY APP</title>
	</head>
	<body>
		WELCOME TO HOME PAGE!
		<br/>
		Logged user: <span sec:authentication="name"></span>,
		Roles: <span sec:authentication="principal.authorities"></span>
		<br/>
		<a th:href="@{/logout}"> LOGOUT </a>
	</body>
</html>
```