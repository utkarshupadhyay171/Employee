//package com.cts.employee.controller;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.CookieValue;
//import com.cts.employee.entity.LoginRequest;
//
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletResponse;
//
//@RestController
//public class LoginController {
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest,HttpServletResponse response) {
//        // Validate the credentials
//        
//            // Save user id in session
////            HttpSession session = request.getSession();
////            int userId = getEmployeeIdByUsername(loginRequest.getName());
////            session.setAttribute("userId", userId);
////            return ResponseEntity.ok("Login successful. User ID: " + userId);
//    	
//			Cookie cookie = new Cookie("username", loginRequest.getName());
//			response.addCookie(cookie); 
//		
//    	return ResponseEntity.ok("Logged in");
//       
//    }
//    
//   
//
//   
//
//   
//}
package com.cts.emp.Employe.controll;


