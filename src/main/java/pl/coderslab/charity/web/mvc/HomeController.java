package pl.coderslab.charity.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.repository.DonationRepository;
import pl.coderslab.charity.domain.repository.InstitutionRepository;

// BUG - na pustej bazie donations wywala sie strona główna

@Controller
public class HomeController {

    private final InstitutionRepository institutionRepo;
    private final DonationRepository donationRepo;

    public HomeController(InstitutionRepository institutionRepo, DonationRepository donationRepo) {
        this.institutionRepo = institutionRepo;
        this.donationRepo = donationRepo;
    }


    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutions", institutionRepo.findAll());
        model.addAttribute("bagsDonated", donationRepo.sumDonatedBags());
        model.addAttribute("numberOfDonations", donationRepo.countDonationsBy());
        return "index";
    }

    @RequestMapping("/mydonations")
    public String userHomeAction(Model model){
        return "user/home";
    }
}
