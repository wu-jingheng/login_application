## A sample application for role-based access control with multi-language support

>> ### Features
>> - User login/logout functionality
>> - Role-based access control (User, Manager)
>> - Account creation (To facilitate demo testing)
>> - Multi-language support (English, Malay, Mandarin, Tamil)
>> - Secure password hashing
>
>> ### Frameworks and Tools Used
>> - **Build Tool**: Maven
>> - **Web Framework**: Spring Boot
>> - **Security Framework**: Spring Security
>> - **Template Engine**: Thymeleaf
>> - **Relational Databases**:
>>     - MySQL Database
>>     - H2 Database (for demo contingency)
>> - **Markup and Styling**: HTML, CSS
>
>> ### Installation Pre-requisites
>> 1. Java 21 JDK
>> 2. Maven 3.9.5 or higher
>
>> ### MySQL Database Setup
>> 1. Access MySQL via the workbench / command line interface
>> 2. Create a schema: `CREATE DATABASE DXC_DEMO;`
> 
>> ### Installation Instructions
>> 1. Clone the repository: `git clone <repository-url>`
>> 2. Navigate to the project directory: `cd <project-directory>`
>> 3. Build the project with Maven: `mvn clean compile install`
>> 4. If you have set up MySQL database:
>>    - `mvn spring-boot:run -Dspring-boot.run.profiles=uat`
>> 5. Alternatively:
>>    - `mvn spring-boot:run -Dspring-boot.run.profiles=dev`
>
>> ### Usage
>> - Open your browser and go to `http://localhost:8080/login`
>> - Default accounts are created on start-up with the password **123**
>>   - Usernames
>>      - _user_
>>      - _manager_
>> - Enter either credentials to log in.

---

  <figure>
      <figcaption>Figure 1: Login page</figcaption>
      <img src="./images/1_login_page.png" alt="Login Page" width="50%"/>
  </figure>

---

  <figure>
      <figcaption>Figure 2: User home page</figcaption>
      <img src="./images/2_user_home_page.png" alt="User Home Page" width="50%"/>
  </figure>

---


  <figure>
      <figcaption>Figure 3: Manager home page</figcaption>
      <img src="./images/3_manager_home_page.png" alt="Manager Home Page" width="50%"/>
  </figure>

---


  <figure>
      <figcaption>Figure 4: Manager exclusive page</figcaption>
      <img src="./images/4_manager_exclusive_page.png" alt="Manager Exclusive Page" width="50%"/>
  </figure>

---


  <figure>
      <figcaption>Figure 5: Language selection feature</figcaption>
      <img src="./images/5_language_selection.png" alt="Language selection feature" width="50%"/>
  </figure>

