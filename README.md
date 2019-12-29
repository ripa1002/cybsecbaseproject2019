# cybsecbaseproject2019
Course project for cybsec base course.

Link to the projects repository: https://github.com/ripa1002/cybsecbaseproject2019

This is my first course project for cyber security base course. This program doesn't really do anything, it just demonstrates most common security flaws in web applications. I chose 5 flaws from OWASP Top 10 Application Security Risks 2017-list. Other flaws from the list that are not covered in this project are: Injection, Security misconfiguration, XML external entities, Insecure deserialization and insufficient logging & monitoring (although logging and monitoring in this project is definitely insufficient).

#### Installation instructions
Open the project in your IDE (I used netbeans), run it and open the browser (preferably chrome) at address http://localhost:8080/


### A9:2017-Using Components with Known Vulnerabilities
This project uses very old version of spring (1.4.2.RELEASE).

Components, such as libraries, frameworks, and other software modules, run with the same privileges as the application. If a vulnerable component is exploited, such an attack can facilitate serious data loss or server takeover. Applications and APIs using components with known vulnerabilities may undermine application defenses and enable various attacks and impacts.

1. Open the project in IDE
2. Locate pom.xml file in project files folder
3. You can see spring framework version number in line 16

How to fix: In the pom.xml file, change spring version to 2.2.1.RELEASE

### A5:2017-Broken Access Control
This project contains vulnerability in access control that makes it easy to find names and addresses of users.

Restrictions on what authenticated users are allowed to do are often not properly enforced. Attackers can exploit these flaws to access unauthorized functionality and/or data, such as access other users' accounts, view sensitive files, modify other users’ data, change access rights, etc.

1. Run project
2. Go to localhost:8080
3. Type ted as username and ted as password and click Login button
4. Type ted as name and some address as address to the form and submit
5. Go to localhost:8080/registered?name=ted and you can see ted is already registered and his address

How to fix: In this project names are used as parameters in URL. Addresses should be unique to each user and usage time.

### A3:2017-Sensitive Data Exposure
There is sensitive data exposure vulnerability in this project that reveales persons address if same name is entered and submitted in the event registration form that is already registered.

Many web applications and APIs do not properly protect sensitive data, such as financial, healthcare, and PII. Attackers may steal or modify such weakly protected data to conduct credit card fraud, identity theft, or other crimes. Sensitive data may be compromised without extra protection, such as encryption at rest or in transit, and requires special precautions when exchanged with the browser.

1. Run project
2. Go to localhost:8080
3. Type ted as username and ted as password and click Login button
4. Type ted as name and some address as address to the form and submit
5. Go to localhost:8080/form
6. Type ted as name and submit, message will show ted's address

How to fix: In SignupController.java class, insert "httpSession.invalidate();" line in the beginning of loadForm() and defaultMapping() methods.

### A7:2017-Cross-Site Scripting (XSS)
There is cross-site scripting vulnerability in this project that allows uploading of malicious code.

XSS flaws occur whenever an application includes untrusted data in a new web page without proper validation or escaping, or updates an existing web page with user-supplied data using a browser API that can create HTML or JavaScript. XSS allows attackers to execute scripts in the victim’s browser which can hijack user sessions, deface web sites, or redirect the user to malicious sites.

1. Run project
2. Go to localhost:8080
3. Type ted as username and ted as password and click Login button
4. Type ted as name and "<script>alert('Malicious')</script>" as address, click submit and after that refresh(F5) and malicious javascript code is runned.

How to fix: Validate inputs in form to prevent malicious data from client.

### A2:2017-Broken Authentication
In this project you can change your password without providing old password. There is field for old password but it is not verificated.

Application functions related to authentication and session management are often implemented incorrectly, allowing attackers to compromise passwords, keys, or session tokens, or to exploit other implementation flaws to assume other users’ identities temporarily or permanently.

1. Run project
2. Go to localhost:8080
3. Type ted as username and ted as password and click Login button
4. Click Change password link
5. Type something in the old password field and same password to the new password and password confirmation fields and submit

How to fix: User should provide old password and that password should be verificated before changing the password.

### Notifications
There is also CSRF vulnerability in this project (located in SecurityConfiguration.java class) that is removed from the newest 2017 OWASP top 10 list. You can disable it by commenting out or removing http.csrf().disable(); line.
Nowadays many frameworks contain CSRF defenses which is the main reason it has dropped out from the list.
