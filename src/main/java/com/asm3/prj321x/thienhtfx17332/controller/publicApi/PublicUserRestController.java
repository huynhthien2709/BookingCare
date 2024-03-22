package com.asm3.prj321x.thienhtfx17332.controller.publicApi;

import com.asm3.prj321x.thienhtfx17332.dto.userDTO.UserDTO;
import com.asm3.prj321x.thienhtfx17332.entity.User;
import com.asm3.prj321x.thienhtfx17332.repository.UserRepository;
import com.asm3.prj321x.thienhtfx17332.security.JwtProvider;
import com.asm3.prj321x.thienhtfx17332.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicUserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) throws Exception{
        String pass = userDTO.getPassword();
        User user = userService.login(userDTO);
        if (user == null) {
            return "User not valid";
        }
        if(user.getIsActive() == 0){
            return "Your account is looked, please contact to Admin";
        }
        boolean check = BCrypt.checkpw(pass, user.getPassword());
        if(check){
            return "token: " + jwtProvider.createToken(user);
        }else {
            return "User not valid";
        }
    }
    @PostMapping("/saveUser")
    public String saveUser(@RequestBody UserDTO userDTO) throws Exception{
        User user = userRepository.findUserByEmail(userDTO.getEmail());
        if (user != null){
            return "email is existed!";
        }else {
            if (!(userDTO.getPassword().equals(userDTO.getRePassword()))){
                return "Password not correct";
            }
            userService.saveUser(userDTO);
            return "register success!";
        }
    }
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody UserDTO userDTO) {
        User user = userService.findUserByEmail(userDTO.getEmail());
        if (user == null){
            return "Email not existed!";
        }
        String token = jwtProvider.createToken(user);
        userService.forgotPassword(userDTO, token);
        return "Email has been sent";
    }
}
