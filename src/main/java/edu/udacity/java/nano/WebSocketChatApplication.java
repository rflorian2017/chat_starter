package edu.udacity.java.nano;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
public class WebSocketChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketChatApplication.class, args);
    }

    /**
     * Login Page
     */
    @GetMapping("/")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }

    /**
     * Chatroom Page
     */
    @RequestMapping("/chat")///{username}")
    public ModelAndView index(@RequestParam String username, HttpServletRequest request) throws UnknownHostException {
        //TODO: add code for login to chatroom.
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("chat");
    	//modelAndView.getModel().put("username", username);
    	modelAndView.getModelMap().addAttribute("username", username);
    	modelAndView.addObject("username", username);
    	
        return modelAndView;
    }
}
