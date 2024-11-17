//package com.expense.trackig.ExpenseTracking.Security;
//
//import com.expense.trackig.ExpenseTracking.Modules.User;
//import com.expense.trackig.ExpenseTracking.Services.Interface.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//@Configuration
//public class UserDetailSeviceImpl implements UserDetailsService {
//    @Autowired
//    UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userService.getUser(email);
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getEmail())
//                .password(user.getPassword())
//                .build();
//    }
//}
