package fr.uha.appintav.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.uha.appintav.controller.AuthController;

@RestController
@RequestMapping("/auth")
public class AuthRoutes {
	
	@Autowired
	private AuthController authController;
	
	@PostMapping(path="/login")
	public @ResponseBody String login(
			@RequestParam(required = true) String email,
			@RequestParam(required = true) String password) {
		return this.authController.login(email, password);
	}
	
	@PostMapping(path="/logout")
	public @ResponseBody String logout(
			@RequestParam(required = true) String token) {
		return this.authController.logout(token);
	}
	
}
