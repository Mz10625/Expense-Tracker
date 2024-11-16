//package Controllers;
//import Modules.User;
//import Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @GetMapping("/login")
//    public String showLoginForm() {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam String email, @RequestParam String password, Model model) {
//        User user = userRepository.findByEmail(email);
//        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//            return "redirect:/dashboard";
//        }
//        model.addAttribute("error", "Invalid email or password");
//        return "login";
//    }
//
//    @GetMapping("/register")
//    public String showRegistrationForm() {
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String register(@RequestParam String email, @RequestParam String password, Model model) {
//        if (userRepository.findByEmail(email) != null) {
//            model.addAttribute("error", "Email already in use");
//            return "register";
//        }
//        User newUser = new User( email, passwordEncoder.encode(password));
//        userRepository.save(newUser);
//        return "redirect:/login";
//    }
//
//    @GetMapping("/dashboard")
//    public String dashboard() {
//        return "dashboard";
//    }
//}
//
//
