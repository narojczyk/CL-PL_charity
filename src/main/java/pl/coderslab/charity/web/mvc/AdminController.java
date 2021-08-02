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
import pl.coderslab.charity.domain.repository.InstitutionRepository;
import pl.coderslab.charity.domain.repository.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping("/")
@Slf4j
public class AdminController {
    private final UserRepository userRepo;
    private final InstitutionRepository institutionRepo;

    public AdminController(UserRepository userRepo, InstitutionRepository institutionRepo) {
        this.userRepo = userRepo;
        this.institutionRepo = institutionRepo;
    }

    @GetMapping("/admin")
    public String adminPanel(){
        return "admin/home";
    }

    @GetMapping("/admin/institutions")
    public String listInstitutions(Model model){
        model.addAttribute("getResource", "institutions");
        model.addAttribute("institutions", institutionRepo.findAll());
        return "admin/home";
    }

    @GetMapping("/admin/institutions/modify")
    public String modifyInstitutionDisplayForm(@RequestParam  Long id, Model model){
        Optional<Institution> optInst =  institutionRepo.findById(id);
        if(optInst.isPresent()) {
            model.addAttribute("getResource", "modify-inst-form");
            model.addAttribute("headderAction", "Modyfikuj");
            model.addAttribute("CRUDAction", "modify");
            model.addAttribute("institution", optInst.get());
            return "admin/home";
        }else{
            return "error";
        }
    }

    @PostMapping("/admin/institutions/modify")
    @Transactional
    public String modifyInstitution(@RequestParam Long id, @RequestParam(required=false) String name,
                                    @RequestParam(required=false) String description ){
        Optional<Institution> optInst =  institutionRepo.findById(id);
        if(optInst.isPresent()){
            Institution inst = optInst.get();
            if(name != null && name.length() > 0 ) {
                inst.setName(name);
            }
            if(description != null && description.length() > 0 ) {
                inst.setDescription(description);
            }
        }
        return "redirect:/admin/institutions";
    }

    @GetMapping("/admin/institutions/delete")
    public String deleteInstitutionMessage(@RequestParam  Long id, Model model){
        Optional<Institution> optInst =  institutionRepo.findById(id);
        if(optInst.isPresent()) {
            model.addAttribute("getResource", "delete-inst-form");
            model.addAttribute("headderAction", "Usuń");
            model.addAttribute("CRUDAction", "delete");
            model.addAttribute("institution", optInst.get());
            return "admin/home";
        }else{
            return "error";
        }
    }

    @PostMapping("/admin/institutions/delete")
    @Transactional
    public String deleteInstitutionConfirmed(@RequestParam  Long id){
        institutionRepo.deleteById(id);
        return "redirect:/admin/institutions";
    }

    @GetMapping("/admin/institutions/add")
    public String addInstitutionDisplayForm(Model model){
        model.addAttribute("getResource", "add-inst-form");
        model.addAttribute("headderAction", "Dodaj");
        model.addAttribute("CRUDAction", "add");
        model.addAttribute("institution", new Institution("Nazwa fundacji","Opis"));
        return "admin/home";
    }

    @PostMapping("/admin/institutions/add")
    public String addInstitution(@RequestParam String name, @RequestParam String description){
        institutionRepo.save(new Institution(name,description));
        return "redirect:/admin/institutions";
    }

    @GetMapping("/admin/users")
    public String listUsers(Model model){
        model.addAttribute("getResource", "users");
        model.addAttribute("users", userRepo.findAll());
        return "admin/home";
    }
}
