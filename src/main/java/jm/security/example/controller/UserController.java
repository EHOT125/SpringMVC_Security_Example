package jm.security.example.controller;

import jm.security.example.model.User;
import jm.security.example.service.UserDetailsServiceImpl;
import jm.security.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @RequestMapping("admin")
    public String mainList(Model model) {
        List<User> list = userService.getUsers();
        model.addAttribute("listUser", list);
        return "all_users";
    }

    @RequestMapping("createUser")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/admin";
    }

    @RequestMapping("info_user")
    public String infoUser(Model model) {
        User user = new User();
        model.addAttribute("info_user", user);
        return "save_user";
    }

    @RequestMapping("updateUser/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("info_user", user);
        return "save_user";
    }
//    @RequestMapping("soloUpdateUser/{id}")
//    public String soloUpdate(@PathVariable("id") Long id, Model model) {
//        User user = userService.update(id);
//        model.addAttribute("user", user);
//        return "soloUpdate";
//    }
//    @RequestMapping("soloUser")
//    public String soloUser(@ModelAttribute User user) {
//        userService.createUser(user);
//        return "redirect:/user";
//    }

    @RequestMapping("user")
    public String readUser(Model model) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userDetailsService.loadUserByUsername(user.getUsername()));
        return "user";
    }

    @RequestMapping("anigilation_user_in_hell/{id}")
    public String anigilation(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/danger")
    public String dangerUser() {
        return "index";
    }

}
