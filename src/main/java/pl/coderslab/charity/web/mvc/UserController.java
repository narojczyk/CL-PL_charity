package pl.coderslab.charity.web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.domain.model.Institution;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.repository.InstitutionRepository;
import pl.coderslab.charity.domain.repository.UserRepository;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/")
@Slf4j
public class UserController {
    private final UserRepository userRepo;
    private final InstitutionRepository institutionRepo;

    public UserController(UserRepository userRepo, InstitutionRepository institutionRepo) {
        this.userRepo = userRepo;
        this.institutionRepo = institutionRepo;
    }

    @GetMapping("/welcome")
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

}
