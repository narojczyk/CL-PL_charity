package pl.coderslab.charity.web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.repository.UserRepository;
import pl.coderslab.charity.domain.service.SecurityService;


import java.util.Optional;

@Controller
@Slf4j
public class RegistrationController {

    private final SecurityService security;
    private final UserRepository userRepository;

    public RegistrationController(SecurityService security, UserRepository userRepository) {
        this.security = security;
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String prepareRegistrationPage(){
        return "security/registration";
    }

    @PostMapping("/registration")
    public String processRegistrationPage(User user, @RequestParam("passwdConfirmed") String passwdConfirmed){
        if(passwdConfirmed.equals(user.getPasswd())){
            security.registerUser(user);
            return "redirect:/login";
        }
        log.debug("Failed to register user "+user.getUsername()+". Confirmed password does not match.");
        return "security/registration";
    }

    @GetMapping("/passwd/reset")
    public String passwdResetConfirmation(Model model, @RequestParam("id") Long id){
        Optional<User> optUser = userRepository.findById(id);
        if(optUser.isPresent()){
            model.addAttribute("user", optUser.get());
            return "security/passwd-reset-confirmation";
        }
        return "error";
    }

    @PostMapping("/passwd/reset")
    public String passwdReset(@RequestParam("id") Long id,
                              @RequestParam("passwd") String passwd,
                              @RequestParam("passwdConfirmed") String passwdConfirmed){
        if(passwd.equals(passwdConfirmed)){
            security.passwdReset(id,passwd);
            return "redirect:/logout";
        }
        return "redirect:/passwd/reset";
    }
}
