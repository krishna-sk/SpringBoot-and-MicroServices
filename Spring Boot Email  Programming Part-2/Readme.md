# Spring Boot Email Programming part-2

#### Sending Mail as Text (or) HTML :-

- Here setText() is overloaded
- setText(text) internally calling setText(text,false)
- setText(String , boolean) is the actual method contains logic to set data.
- if boolean html=false, Text data is sent as Plain Text.
  html=true , Text data is sent as HTML Content.

#### Working with Multiple Attachments :-

- Resource(I) : it indicates a file exist in a location.
- A File exist in your System (D:/abcd folder ex) ==> FileSystemResource()
- A File Exist in internet location ==> UrlResource()
- A File Exist in Project src/main/resources folder ==> ClassPathResource() ==> Use Resource Parameter as Array

#### Overloading

- using same method name and but different in paremeters.
- send() method is overloaded as
  - to,subject,text (find in SimpleEmailRunner)
  - to,subject,text,file (find in EmailWithOneAttachmentRunner)
  - to,subject,text,cc,bcc,files (find in FullRunner)

