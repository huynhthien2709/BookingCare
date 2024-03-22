package com.asm3.prj321x.thienhtfx17332.controller.privateApi;



import com.asm3.prj321x.thienhtfx17332.dto.MailDTO.MailDTO;
import com.asm3.prj321x.thienhtfx17332.dto.userDTO.*;
import com.asm3.prj321x.thienhtfx17332.entity.User;
import com.asm3.prj321x.thienhtfx17332.repository.UserRepository;
import com.asm3.prj321x.thienhtfx17332.security.JwtProvider;
import com.asm3.prj321x.thienhtfx17332.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/private")
public class PrivateUserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserRepository userRepository;




    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    @GetMapping("/valid-hsa")
    public boolean validHsaToken(@RequestParam String token) throws Exception{
        return jwtProvider.validateToken(token);
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token, @RequestBody UserDTO userDTO){
        if (!userDTO.getPassword().equals(userDTO.getRePassword())){
            return "Password not correct";
        }
        boolean validToken = jwtProvider.validateToken(token);
        if (validToken){
            userService.resetPassword(userDTO);
        }else {
            return "Token not valid";
        }
        return "Password has been reset";
    }

    @GetMapping("/getUserDetail")
    public UserDetailDTO getUserDetail(){
        Integer userId = jwtProvider.getUserFromToken();
        if (userId == null) return null;
        UserDetailDTO userDetailDTO = userService.getUserDetail(userId);
        if (userDetailDTO == null) return null;
        return userDetailDTO;
    }

    @PostMapping("/userBooking")
    public String userBooking(@RequestBody UserBookingDTO userBookingDTO){
        Integer userId = jwtProvider.getUserFromToken();
        if (userId == null) return "User invalid";
        UserBookingDTO userBookingDTO1 = userService.userBooking(userBookingDTO, userId);
        if (userBookingDTO1 == null) return "Booking failed";
        return "Booking successfully!";

    }
    @GetMapping("/getPatientList")
    public List<GetPatientDTO> getPatientList(){
        Integer userId = jwtProvider.getUserFromToken();
        if (userId == null) return null;
        List<GetPatientDTO> getPatientDTO = userService.getPatientList(userId);
        if (getPatientDTO == null) return null;
        return getPatientDTO;
    }
    @GetMapping("/getPostForDoctor")
    public List<GetPostForDoctorDTO> getPostForDoctor(){
        Integer doctorId = jwtProvider.getUserFromToken();
        if (doctorId == null) return null;
        return userService.getPostForDoctor(doctorId);
    }
    @PostMapping("/doctorConfirm")
    public String doctorConfirm(@RequestBody GetPostForDoctorDTO getPostForDoctorDTO){
        Integer userId = jwtProvider.getUserFromToken();
        if (userId == null) return "User invalid";
        userService.doctorConfirm(getPostForDoctorDTO, userId);
        return "Confirmed successfully";
    }

    @PostMapping("/lockUser")
    public String lockUserPatient(@RequestBody LockUserDTO lockUserDTO){
        Integer userId = jwtProvider.getUserFromToken();
        if (userId == null) return "User invalid";
        userService.lockUser(lockUserDTO, userId);
        return "Account is looked";
    }

    @PostMapping("/unLockUser")
    public String unLockUserPatient(@RequestBody LockUserDTO lockUserDTO){
        Integer userId = jwtProvider.getUserFromToken();
        if (userId == null) return "User invalid";
        userService.unLockUser(lockUserDTO, userId);
        return "Your account is unlooked";
    }

    @PostMapping("/addDoctorUser")
    public String addDoctorUser(@RequestBody DoctorUserDTO doctorUserDTO){
        Integer userId = jwtProvider.getUserFromToken();
        if (userId == null) return "User invalid";
        userService.addDoctorUser(doctorUserDTO,userId);
        return "Add success";

    }

    @PostMapping("/doctorSendMail")
    public String doctorSendMail(@RequestBody MailDTO mailDTO){
        Integer userId = jwtProvider.getUserFromToken();
        if (userId == null) return "User invalid";
        userService.doctorSendMail(mailDTO, userId);
        return "Email has been send";
    }
    @GetMapping("/getPostForAdnmin")
    public List<GetPostForAdminDTO> getPostForAdnmin(@RequestBody GetPostForAdminDTO getPostForAdminDTO){
        Integer userId = jwtProvider.getUserFromToken();
        if (userId == null) return null;
        return userService.getPostForAdmin(getPostForAdminDTO, userId);
    }

    @GetMapping("/getDoctorSchedule")
    public List<GetDoctorScheduleDTO> getDoctorSchedule(@RequestBody GetDoctorScheduleDTO getDoctorScheduleDTO){
        Integer userId = jwtProvider.getUserFromToken();
        if (userId == null) return null;
        return userService.getDoctorSchedule(getDoctorScheduleDTO, userId);
    }








}
