# @ComponentScan

- All programmer defined files/classes must be under Starter class/Main class package (or its subpackage).

- If your create your class outside of Main class package (or any diff package)
  then Spring container can not find your clas, no object is created, so to resolve this error we use **@ComponentScan** on top of main class.

```textile
Example:
  Main class Package : com.example.demo
  Product            : com.example.demo(Detected)
  Admin              : com.example.demo.model (Detected)
  ExcelExport        : com.example.demo.b.c.d.e (Detected)
  Employee           : com.example (Not Detected)
  DatabaseCon        : com.app.test(Not Detected)
```

- Spring Boot , by default gives Main class Package name as basepackage Internally added  **@ComponentScan("com.example.demo")**

- We can modify by adding same annotation at main class **@ComponentScan("com.app.test")** but it is not recomanded in realtime.

- We can even provide multiple basePackages **@ComponentScan("com.example.demo","com.app.test")** (it is also not recomanded)
