# Spring Boot Scheduling

#### Scheduling:-

Executing a task/method in a loop based on period of time or point of time.

Examples:-

- Bank account statement (Every for month)
- CC Bill Statement
- Weekly/Monthly/3months Reports
- EMI Reminder
- Birthday Messages
- Tax Payment
- Electricity Bill Payment
- Server reports/Log files/Billing...etc

#### period of time : Time gap

4hrs, 2mins, 5days, 6years

#### point of time : Exact start date/time

1st Jan- 9AM, 31st Dec-6PM ..etc

- Scheduling process in application can be enabled/disabled using- @EnableScheduling (by default it is disabled)
- Define a method with logic using below syntax
  public void <methodName>() { //body }
  This method is called in a loop by Container
- On top of method must provide @Scheduled using fixedDelay/ fixedRate/cron\*\*
- We can autowire some other objects, make DB calls inside method.

#### fixedDelay :-

- fixed Waiting time between last method call execution finish time
  to next method call start time.
- wait time is given/used b/w last method call finish--next method call start

#### fixedRate :-

- The max waiting time including method execution time.
- Wait time = fixedRate - Method Execution time
- if the value is -ve/zero then wait time is ZERO(0)

#### cron :-

> source :: https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions

- CRON Expression used to indicate date and time.
- It contains 6 positions. (Single space)
- When Month is provided and you want to specify ALL DAY / ALL WEEKDAY
  then must use Symbol ? in place of Star(\*).

```textile
  SEC   MIN    HRS   DAY  MONTH  WEEKDAY
  0-59  0-59  0-23  1-31  1-12  1-7/SUN-SAT
```

- Default cron expression indicates date and time (Point of time/at)
- This is not time gap by default**\*\***.
- Scheduling code exist in ScheduledAnnotationBeanPostProcessor.

#### Symbols allowed:-

1. Star \* => Every/All/Any
1. Dash - => Range of values
1. Comma , => Possible values
1. Slash / => Period of time

#### Macros : Short format annotation for an expressions

| Macro                 | Meaning                        |
| --------------------- | ------------------------------ |
| @yearly(or @annually) | once a year (0 0 0 1 1 ?)      |
| @monthly              | once a month (0 0 0 1 \* \*)   |
| @weekly               | once a week (0 0 0 \* \* 0)    |
| @daily (or @midnight) | once a day (0 0 0 \* \* \*)    |
| @hourly               | once an hour (0 0 \* \* \* \*) |

