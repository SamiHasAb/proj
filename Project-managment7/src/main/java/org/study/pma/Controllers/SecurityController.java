package org.study.pma.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.study.pma.Entities.UserAccount;
import org.study.pma.Repository.UserAccountRepository;



@Controller

public class SecurityController {
	
	@Autowired
	UserAccountRepository accountRepo;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder ;
	
	
	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		
		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String saveUser(Model model, UserAccount user) {
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		
		//do service for this
		accountRepo.save(user);
		return "redirect:/";
	}
	

}
