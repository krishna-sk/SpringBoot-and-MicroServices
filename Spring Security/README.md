# Spring Security

#### Table of Contents

- [InMemory Authentication](#inmemory-authentication)

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

#### InMemory Authentication

###### 13-December-2021

**InMemoryAuthentication :** Storing data inside RAM(Random Access Memory).it is used for Testing Purpose only, not used in production.

- We must define one implementation class for "WebSecurityConfigurerAdapter" (Abstract Class) and override 2 methods:
  - configure(AuthenticationManagerBuilder) //authenitcation\
  - configure(HttpSecurity) //authorization

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
