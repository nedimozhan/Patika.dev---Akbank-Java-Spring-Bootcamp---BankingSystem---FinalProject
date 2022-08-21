# BANKING SYSTEM PROJECT
# Used Technologies
> * Java Spring
> * MySQL
> * Apache Kafka
> * Log4j
> * MyBatis
> * Angular JS
> * Maven
> * Eclipse
> * CollectAPI

---

# About Project
**This project simulates basic banking system operations with real banking technologies.**

>

### The system has 4 authorities
> * CREATE_BANK
> * ACTIVATE_DEACTIVATE_USER
> * CREATE_ACCOUNT
> * REMOVE_ACCOUNT


### The system has 3 role 
> * User -> (CREATE_ACCOUNT)
> * Account Manager -> (CREATE_ACCOUNT, ACTIVATE_DEACTIVATE_USER, REMOVE_ACCOUNT)
> * Admin -> (CREATE_ACCOUNT, ACTIVATE_DEACTIVATE_USER, REMOVE_ACCOUNT, CREATE_BANK)

Newly registered user must be activated by admin or account manager for the succesful login operation.

### A user who logined succesfuly to the system can do some operations
> * Create Account
> * Control Account Details
> * Deposit to Account Balance
> * Transfer Balance to Another Account

### An account manager who logined succesfuly to the system can do some operations
> * Create Account
> * Control Account Details
> * Deposit to Account Balance
> * Transfer Balance to Another Account
> * Activate or Deactivate To User
> * Remove to User

### An admin who logined succesfuly to the system can do some operations
> * Create Account
> * Control Account Details
> * Deposit to Account Balance
> * Transfer Balance to Another Account
> * Activate or Deactivate To User
> * Remove to User
> * Create Bank

### Balance-based operations
> **In this system, balance based operations are based on the current exchange rate thanks to CollectAPI. When user do deposit and transfer operations, the system automatically create log and write to file with Log4j and Apache Kafka techlonogies**

---

# Test and Run The Project
> You must download the used techlonogies for testing and running to this project. After these downloadings I recommend that run **Zookeeper Software** and **Kafka Software** firstly. After this sections you can run the java application. You can use **Postman** for testing this applicaton. In this project I provide to you a template frontend section developed with **Angular JS**.  

> * http://localhost:4200 (Login)
> * http://localhost:4200/register (Sign In)
> * http://localhost:4200/Home (Home)
> * http://localhost:4200/transfer (Transfer)
> * http://localhost:4200/myaccounts (Detail)



