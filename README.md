# codefellowship-
 * create html pages in templates (feed,error,GomePage,LogIn,SignUp,Profile and ViewPage)
 * create css pages in static (style and style )
* Build an app that allows users to create their profile on CodeFellowship.
- create ApplicationUser implementing UserDetails -
-  create constructuer ,setter and getter for user information 
 - create interface ApplicationUserRepository with findByUsername method ;

- create UserDetailsServiceImp class  implementing UserDetails -

- create webSecurityConfig class  extending  WebSecurityConfigurerAdapter -


- create postPage class:
-  create constructuer ,setter and getter for user post ; 
 - create interface ApplicationUserRepository with findByUsername method ;


- create ApplicationUserControler class  -
- in the ApplicationUserControler class :
- 1 - ("/hiMan") route that display  Home page 
- 2 - ("/signup") route that display  signup page 
- 3 - ("/login") route that display  login page 
- 4 - ("/signup")postMapping  route that accept user information and save it in the dataBase display then  Home page 
- 5 - ("/myprofile") route that get the user information  from dataBase then display  it in profile page 
- 6 - ("/AddPost")postMapping  route that accept user post and save it in the dataBase then display it in  the  profile page 

- 7 - ("/view/{id}") route that accept id in the param then get the user id  from dataBase then display  the users information each user by his id  in th  viewPage page .

- 8 - PostMapping("/AddFriend") with addFriend method  that accept id in the param  and id friend then get the user id and friend id then add it to th friends list and save it into the data base then  from dataBase  display  the users information each user with   his posts  in th  feed page .

- 9 - ("/friends/{id}") route that accept id in the param then get the user id  from dataBase then display  the users information each user by his id  in th  test page .

- 10 - ("/login") route that display  feed page (frinds profile with ther posts )


*  Navigate to localhost:port8080 to basic splash page.
Home: http://localhost:8080/hiMan.
SignUp: http://localhost:8080/login 
logIn: http://localhost:8080/login 
user list: http://localhost:8080/view/{id}

friends paeg: http://localhost:8080/friends/{id} 

user Profile: http://localhost:8080/myprofile
 
Running the application:




