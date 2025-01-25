# cw2-android

This program handles Employee and Administrator data, splitting the two responsibilities using a UserFactory.java class after the initial registration. When an employee initially regissters, they will be automatically placed in the employee bin, only then can the administrator grant administrative permissions to the user.

How to Use:

_Administrators_
1. Once you have been granted Administrator permissions you will have access to the Administrator Dashboard
2. In this dashboard you will have access to the following information
   a) Employee Details - via an API you will be able to upload Employee Details to the HR website
   b) Holiday Management - this page will give you the ability to manage employee holiday requests
   c) Notification Center - management of notifications


_Administrator Dashboard_

1. Submit EMployee Details

   a) This button is connected to a layout page called activity_submit_employee;

   b) When you submit information on this page it uses the POST method in a RESTful API to submit data to https://web.socem.plymouth.ac.uk/COMP2000/api/.

2. Holiday Center

   a) This button is connected to layout page activity_holiday_request;

   b) This page uses a simple text field for reason and two (2) date pickers for start and end date to request vacation time;

   c) There is also a stored function that will show you the requested date below the input information, this data will be erased after logout

3. Admin Profile

   a) This button leads to layout activity_user_details;

   b) You can find all user information in this page; Profile Picture, First name, Last Name, Username and Pin

   c) The following items are editable; First Name and Last Name

   d) You can reseet your PIN using the reset password button

   e) Username is autopopulated based on the logged in user

   f) Back to Dashboard takes you back to the main menu

4. Logout

   a) Logout calls upon the logout method to logout the currently logged in user

5. Privacy Policy

   a) This is linked to layout file activity_privacy_policy and shows the current privacy policy of the application



_Test Administrator Credentials_

Username: admin

Password: 1234

Coding Contributors
=====================
Login/Logout Fragments - **https://mycurlycode.blogspot.com/2017/04/login-logout-features-in-android.html**
