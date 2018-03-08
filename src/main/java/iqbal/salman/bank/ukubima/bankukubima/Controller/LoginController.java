package iqbal.salman.bank.ukubima.bankukubima.Controller;

import iqbal.salman.bank.ukubima.bankukubima.Service.Implementation;
import iqbal.salman.bank.ukubima.bankukubima.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    Implementation userService;

    private final Logger console = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(value = {"/", "/login"})
    public String Loginpriview(ModelMap params) {
        params.addAttribute("login");
        return "/pages/user/login";
    }

    @GetMapping("/registration")
    public String Registrasi(User user, ModelMap params) {
        params.addAttribute("user", user);
        params.addAttribute("roles",userService.findAll());
        return "/pages/user/registrasi";
    }

    @PostMapping("/submit")
    public String submitFormRegistrasi(@Valid @ModelAttribute User user,
                              BindingResult bindingResult, RedirectAttributes ridek) {
        System.out.println("metthod called!");
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user",
                    "There is already a user registered with the email provided");
        }

        if (bindingResult.hasErrors()) {
            return "/pages/user/registrasi";
        }
        console.info("{}", user);
        userService.saveUser(user);
        ridek.addFlashAttribute("sukses", "Registrasi berhasi dilakukan");
        return "redirect:/user/login";

    }

    @GetMapping("/admin/home")
    public String AdminUser(ModelMap prs) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        prs.addAttribute("user", user);
        return "/pages/user/admin";
    }


}
