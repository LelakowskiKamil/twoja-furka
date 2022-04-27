# Automotive classifieds website 
![Preview](https://i.ibb.co/T01LF4W/image.png)

# Preview
http://34.116.175.118:8080/

## Technologies used
  - Java 11 version
  - Spring Boot 2
  - Thymeleaf 3
  - Hibernate
  - Spring Security
  - PostgreSQL
  
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
  - imageURL
  
  ## How to start?
Application is deployed on Google cloud and available here:
http://34.116.175.118:8080/
You can also download the project manually, change flag in application-test.properties: `spring.jpa.hibernate.ddl-auto=create` and start the application
