# Spring Boot Stop Watch, Banner and VM args
## StopWatch (C) : 
This is given by Spring f/w used to calculate time taken  for a process like method execution time, block execution time..etc\
**Methods** : start(), stop()\
**Note**
- If we call start() more than once in order then Spring Framework is going to throw IllelgalStateException: Can't start StopWatch : It's already running.
- If we call stop() more than once in order then Spring Framework is going to throw IllelgalStateException: Can't start StopWatch : It's not running.

## Banner : 
When we start spring boot application at console Spring LOGO is printed that is called as banner.
- To disable this we use **spring.main.banner.mode=off**
- we can even print out own banner by using this [link](https://devops.datenkollektiv.de/banner.txt/index.html), generate text and place the content in **banner.txt** under src/main/resources folder
- To modify file name from banner.txt to any other we should use key=val that is spring.banner.location=classpath:sample.txt

## VM Arguments:
A variable created at JVM level to provide common data to multiple application/VM as input in key=val format.
- To create this\
Syntax : **-Dkey=val**
- To read data
```textile
String value = System.getProperty("key");
```
- VM args are used for JVM inputs, less used in production.apps. But still Spring boot supports as 2nd priority input.

## Spring Boot priorities for inputs
1. Option-args 
```textile
--key=value
```
2. VM-args
```textile
-Dkey=value
```
3. application-profile.properties
4. application-profile.yml
5. application.properties
6. aplication.yml
