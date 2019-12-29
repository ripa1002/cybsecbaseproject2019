package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        if (signupRepository.findByName(name).isEmpty()) {
            signupRepository.save(new Signup(name, address));
            return "done";
        } else {
            return "redirect:/registered?name=" + name;
        }
    }
    
    @RequestMapping(value = "/change", method = RequestMethod.GET)
    public String changePassword() {
        return "change";
    }
    
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    @ResponseBody
    public String submitChangePassword(@RequestParam String password, @RequestParam String confirm) {
        if (password.equals(confirm)) {
            return "Password change succesfull";
        } else {
            return "New password does not match confirmation";
        }
    }
    
    @RequestMapping(value = "/registered", method = RequestMethod.GET)
    @ResponseBody
    public String alreadyRegistered(@RequestParam String name) {
        if (signupRepository.findByName(name).isEmpty()) {
            return name + " is not registered.";
        } else {
            return name + " is already registered with address " +
                    signupRepository.findByName(name).get(0).getAddress() + ".";
        }
    }
}
