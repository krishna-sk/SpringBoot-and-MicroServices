# Configuration Properties

- @ConfigurationProperties is called as Bulk Load.
- It supports reading multiple keys data into variables by defining this annotation only once at class level.\
- **Rules**
  1. Must use one common word/prefix for every key.
  1. key name => prefix + variableName.
  1. Define @ConfigurationProperties(prefix="COMMON WORD") at class
level.
  1. Provide variables with setter methods.
  1. If we did not specify matching key-name, prefix is not spelled/wrong, variable name not matching, setter methods not provided then No Error, No Exception, No Value is injected.
  1. prefix must, one prefix--one class always.
  1. Two or more prefixes can never be connected to one class.
 