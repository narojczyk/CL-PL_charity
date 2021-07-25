package pl.coderslab.charity;

//import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.repository.DonationRepository;
import pl.coderslab.charity.domain.repository.InstitutionRepository;


@Controller
//@Slf4j
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
        return "index";
    }
}
