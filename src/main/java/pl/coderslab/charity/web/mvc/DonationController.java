package pl.coderslab.charity.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.repository.CategoryRepository;
import pl.coderslab.charity.domain.repository.InstitutionRepository;

@Controller
@RequestMapping("/donations")
public class DonationController {
    private final CategoryRepository categoryRepo;
    private final InstitutionRepository institutionRepo;

    public DonationController(
            CategoryRepository categoryRepo,
            InstitutionRepository institutionRepo) {
        this.categoryRepo = categoryRepo;
        this.institutionRepo = institutionRepo;
    }

    @GetMapping("/add")
    public String donationFormDisplay(Model model){
         model.addAttribute("categories", categoryRepo.findAll());
         model.addAttribute("institutions", institutionRepo.findAll());
        return "donation-form";
    }
}
