package pl.coderslab.charity.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.repository.UserRepository;

import java.security.Principal;
import java.util.Optional;

@Controller
public class UserController {
    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping("/welcome")
    public String userHomeAction(Principal principal, Model model){
        Optional<User> currentUser = userRepo.findByUsername(principal.getName());
        if(currentUser.isPresent()){
            User loggedUser = currentUser.get();
            if(loggedUser.getRole().equals("ROLE_ADMIN")){
                model.addAttribute("admin", loggedUser);
                return "redirect:/admin";
            }
            if(loggedUser.getRole().equals("ROLE_USER")) {
                model.addAttribute("user", loggedUser);
                return "user/home";
            }else{
            return "error";
        }
        }else{
            return "error";
        }
    }

    @RequestMapping("/admin")
    public String adminPanel(Principal principal, Model model){
//        model.addAttribute("user", loggedUser);
        return "admin/home";

    }
}
