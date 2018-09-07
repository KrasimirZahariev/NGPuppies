Payment System

1 Project description
      When there is a person(subscriber) who is client both to telecommunication company and bank or other financial institution, and this person want to automate the process of paying his utility bills, the payment system helps the financial institution(client) to process and take care of the payments of its clients. The web application has:
      * Public part (accessible without authentication)
      * Private part (available for registered users)
      * Administrative part (available for administrators only)
2 Public part
      The public part of the payment system is accessible without any authentication and contain only login page where Administrator and Users can login. The administrators are manually created in the system. Users are the financial institution(client). They can’t register by themselves. The Admin user only can create both admin and client users. 
3 Private part
      Client users have private part in the application, accessible when they are successfully authenticated. Users don’t have the ability to change their username and password. As a client you are able to:
      - have access to bill payment module where you can pay a particular bill (or selected list of bills) for your subscribers
      - see personal details of your subscribers
      - see the history of the payments for its subscribers sorted descending by the date of payment
      - see the average and max amount of money payed for subscribers bills for a defined period of time
      - see top 10 subscribers with the biggest amount of money payed
      - see his 10 most recent payments.
4 Administrative part
      System administrators have administrative access to the system and permissions to administer all major information objects in the system after they are successfully authenticated. As an admin you are able to:
      - Create administrative and client users with their own username and password
      - see a list of all users
      - update information for admin and client users
      - delete both other administrative and client accounts
      - generate payment with a given amount for any subscriber associated with any client
      - to change their username and password.

        The application is Bright Consulting’s final project assignment for the Spring MVC course and is created by Borislav Borisov ( https://github.com/borislavvb ) and Krasimir Zahariev ( https://github.com/KrasimirZahariev ), both students at Telerik Academy Alpha – Java.
