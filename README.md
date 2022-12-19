# REST API for Online Cab Booking Application

We have create a REST API for online cab booking application which can be used by customers to login into their profile, update their information and 
book cabs, by a driver to login and update their details along with their cab details and accept a ride from the customer. All this is over looked by the 
admin who can also login and update their information as well as access the data in the application. 

### This is a collaborative project, completed by a team of 5 backend developers at Masai School.

# Collaborators

- [Anurag Shekhawat](https://github.com/Anurag-shekawat)
- [Aman Sharma](https://github.com/Aman103767)
- [Rohit Kumar](https://github.com/RohitJsr)
- [Akash Kumar](https://github.com/akashkumar124)
- [Khaja Moinuddin](https://github.com/KhajaMoinuddin9836)

# Tech Stack
- Java
- Hibernate
- Spring Framework
- Spring Boot
- Spring Data JPA
- MySQL
- Swagger UI
- Maven

# Modules

- Login Module
- Cab Driver Module
- Customer Module
- Admin Module
- Trip Details Module

# Features

- Data Authentication and Validation for all the users (Admin, Customer and Cab Driver)

## Admin Features
- Admin can access all Trip Details along with specific trip details using a particular cab or a customer.


## Customer Features
- Customer can login in the application and update their information using their mobile number and password.
- Customer can book trips using pickup location and destination.
- Customer can access the bill after the trip is completed.


## Cab Driver features
- Cab Driver can login in the application and update their information using generated key at the time of login.
- Cab driver can add and update their cab details.
- Cab Driver can mark their availability according to the trips status.
- Cab Driver can end the trip and application generates a bill for the trip.

# Installation & Run
 - Before running the API server, you should update the database config inside the application.properties file.
- Update the port number, username and password as per your local database configuration.

```
    server.port=8888

    spring.datasource.url=jdbc:mysql://localhost:3306/sb201dbcw;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
```

# API Root Endpoint
```
https://localhost:8888/
```
```
http://localhost:8888/swagger-ui/
```
# API Reference

## Login Requests

```http
  Admin Login
```
| Request | METHOD     |  URI | Description                |
| :-------- | :------- | :----- | :------------------------- |
| `POST` | `Login` | `http://localhost:8888/admin/login` | Login Admin |
| `GET` | `Logout` | `http://localhost:8888/admin/logout` | Logout Admin |

```http
   Customer Login
```
| Request | METHOD     |  URI | Description                |
| :-------- | :------- | :----- | :------------------------- |
| `POST` | `Login` | `http://localhost:8888/customers/login` | Login Customer |
| `GET` | `Logout` | `http://localhost:8888/customers/logout` | Logout Customer |

```http
   Driver Login
```
| Request | METHOD     |  URI | Description                |
| :-------- | :------- | :----- | :------------------------- |
| `POST` | `Login` | `http://localhost:8888/driver/login` | Login Driver |
| `GET` | `Logout` | `http://localhost:8888/driver/logout` | Logout Driver |

## Admin Requests

```http
  Admin Controller
```

| Request | METHOD     |  URI | Description                |
| :-------- | :------- | :----- | :------------------------- |
| `POST` | `Create` | `http://localhost:8888/admin/create` | Create Admin |
| `PUT` | `Update` | `http://localhost:8888/admin/update` | Update Admin |
| `PUT` | `Update Rate Of Cab` | `http://localhost:8888/admin/updateRateOfCab` | Update Rate Of Cab |
| `DELETE` | `Delete` | `http://localhost:8888/admin/delete/{aId}` | Delete Admin |
| `POST` | `Get All Trip` | `http://localhost:8888/admin/getAllTrips/{adminId}` | Show All Trip |
| `GET` | `Get Trip By Cab` | `http://localhost:8888/admin/getAllTripsByCab/{cabId}/{adminId}` | Get All Trip By Cab ID |
| `GET` | `Get Cab By Car Type` | `http://localhost:8888/admin/getCabByCarType/{carType}` | Get Cab By Car Type |
| `GET` | `Count All Cabs` | `http://localhost:8888/admin/countCabs/{carType}` | Count All Cabs |


## Customer Requests

```http
  Customer Controller
```

| Request | METHOD     |  URI | Description                |
| :-------- | :------- | :----- | :------------------------- |
| `POST` | `Create` | `http://localhost:8888/customer/create` | Create Customer |
| `PUT` | `Update` | `http://localhost:8888/customer/update` | Update Customer |
| `DELETE` | `Delete` | `http://localhost:8888/customer/delete/{cId}` | Delete Customer |
| `POST` | `Book Trip` | `http://localhost:8888/customer/createTrip` | Book Trip |
| `DELETE` | `Cancel Trip` | `http://localhost:8888/customer/canceltrip` | Cancel Trip |
| `POST` | `Trip List` | `http://localhost:8888/customer/triplist` | Trip List |
| `POST` | `Generate Bill` | `http://localhost:8888/customer/generateBill` | Generate Bill |
| `GET`  |  `View All Customer` | `http://localhost:8888/customer/getAll`| View All Customer|
| `GET`  |  `View Customer` | `http://localhost:8888/customer/getCustomer/{customerId}


## Cab Driver Requests

```http
  Cab Driver Controller 
```

| Request | METHOD     |  URI | Description                |
| :-------- | :------- | :----- | :------------------------- |
| `POST` | `Create` | `http://localhost:8888/driver/create` | Create Cab Driver |
| `PUT` | `Update` | `http://localhost:8888/driver/update` | Update Cab Driver |
| `DELETE` | `Delete` | `http://localhost:8888/driver/delete` | Delete Cab Driver |
| `GET` | `See Best Drivers` | `http://localhost:8888/driver/viewBestDrivers` |View best drivers|
