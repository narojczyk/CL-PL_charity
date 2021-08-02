package pl.coderslab.charity.web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.domain.model.Donation;
import pl.coderslab.charity.domain.repository.CategoryRepository;
import pl.coderslab.charity.domain.repository.DonationRepository;
import pl.coderslab.charity.domain.repository.InstitutionRepository;

@Controller
@RequestMapping("/donations")
@Slf4j
public class DonationController {
    private final CategoryRepository categoryRepo;
    private final InstitutionRepository institutionRepo;
    private final DonationRepository donationRepo;

    public DonationController(
            CategoryRepository categoryRepo,
            InstitutionRepository institutionRepo,
            DonationRepository donationRepo) {
        this.categoryRepo = categoryRepo;
        this.institutionRepo = institutionRepo;
        this.donationRepo = donationRepo;
    }

    @GetMapping("/add")
    public String donationFormDisplay(Model model){
         model.addAttribute("categories", categoryRepo.findAll());
         model.addAttribute("institutions", institutionRepo.findAll());
         model.addAttribute("donation", new Donation());
        return "user/donation-form";
    }

    @PostMapping("/add")
    public String donationFormProcessing(@ModelAttribute("donation") Donation donation){
        log.debug(donation.toString());
        donationRepo.save(donation);
        return "redirect:/";
    }
}
