package pl.coderslab.charity.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.repository.DonationRepository;
import pl.coderslab.charity.domain.repository.InstitutionRepository;
import pl.coderslab.charity.domain.repository.UserRepository;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserRepository userRepo;
    private final InstitutionRepository institutionRepo;
    private final DonationRepository donationRepo;

    public UserController(UserRepository userRepo,
                          InstitutionRepository institutionRepo,
                          DonationRepository donationRepo) {
        this.userRepo = userRepo;
        this.institutionRepo = institutionRepo;
        this.donationRepo = donationRepo;
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
            return "user/home";
        }else{
            return "error";
        }
    }

    @GetMapping("/user/mypanel")
    public String userPanel(Principal principal, Model model){
        Optional<User> currentUser = userRepo.findByUsername(principal.getName());
        if(currentUser.isPresent()){
            User loggedUser = currentUser.get();
            model.addAttribute("getResource", "panel");
            return "user/home";
        }else{
            return "error";
        }
    }

    @GetMapping("/user/donations")
    @Transactional
    public String userDonations(Model model) {
        model.addAttribute("getResource", "donations");
        model.addAttribute("donations", donationRepo.findAll());
        return "user/home";
    }


}
