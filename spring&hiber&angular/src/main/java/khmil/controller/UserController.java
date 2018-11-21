package khmil.controller;

import khmil.controller.externalModel.RegisterUserDto;
import khmil.model.User;
import khmil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute(value = "user") User user, ModelAndView modelAndView) {
        return userService.getUserByEmail(user.getEmail())
                .map(r -> userService.verifyPassword(r, user))
                .filter(Optional::isPresent)
                .map(r -> {
                    modelAndView.setViewName("home");
                    return modelAndView;
                }).orElseGet(() -> {
                    modelAndView.setViewName("login");
                    return modelAndView;
                });
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public ModelAndView register(ModelAndView modelAndView) {
        modelAndView.setViewName("register");
        modelAndView.addObject("userDto", RegisterUserDto.empty());
        return modelAndView;

    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid RegisterUserDto userDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
            modelAndView.addObject("userDto", RegisterUserDto.empty());
            return modelAndView;
        }
        User user = User.of(userDto);
        userService.addUser(user);
        modelAndView.setViewName("home");
        modelAndView.addObject("user", user);
        return modelAndView;

    }

    @ModelAttribute
    public RegisterUserDto userDto() {
        return RegisterUserDto.empty();
    }
}
