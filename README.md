# Automotive classifieds website 
![version](https://zapodaj.net/images/bbf9ec3fabc6b.png) ![status](https://zapodaj.net/images/83334bb6d63dc.png)

## Technologies used
  - Java 11 version
  - Spring Boot 2
  - Thymeleaf 3
  - Hibernate
  - Spring Security
  - MS-SQL database
  
## Information about the project
The application is called "TwojaFurka" and it is similar to the popular OTO-MOTO website. The user can browse ads added by other users in search of his dream car.

![home](https://zapodaj.net/images/a5cc7026cf604.png)

## Table of Contents
- [The main functions of the application](#The-main-functions-of-the-application)
- [Entities](#Entities)
- [How to start](#How-to-start?)

## The main functions of the application
  - Management of advertisements:
    - Viewing the list of user announcements.
    ![offers](https://zapodaj.net/images/36b76e2970309.png)
    - Editing and deleting ads, if the logged in user has the right to do so.
    - Filtering and sorting the displayed results by categories.
    - Paging the results.
  - Displaying information about an advertisement.
  ![offers](https://zapodaj.net/images/338528b735cad.png)
  - User login and registration.
  ![offers](https://zapodaj.net/images/9dc10357a3f60.png)

## Entities
### User
  - email
  - username
  - password
  - details:
    - firstname
    - lastname
    - phone
    
### Role
  - name

### Offer
  - title
  - year of production
  - mileage
  - engine size
  - number of doors
  - colour 
  - description
  - price
  - car model
  - fuel type
  - body style
  - author of the advertisement
  
  ## How to start?
When launching the application for the first time, check that the flag is set in the production settings of the application (application-prod.properties)
`spring.jpa.hibernate.ddl-auto=create` .
After the first start of the project, the database schema will be created and when the application is restarted, the flag should be changed to
`spring.jpa.hibernate.ddl-auto=validate` .
