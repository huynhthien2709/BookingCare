package com.asm3.prj321x.thienhtfx17332.service;

import com.asm3.prj321x.thienhtfx17332.dto.MailDTO.MailDTO;
import com.asm3.prj321x.thienhtfx17332.dto.userDTO.*;
import com.asm3.prj321x.thienhtfx17332.entity.Post;
import com.asm3.prj321x.thienhtfx17332.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService  {
    public List<User> getAllUser();
    public User saveUser(UserDTO userDTO);

    public  UserDTO getUser(UserDTO userDTO);

    public User login(UserDTO userDTO);

    public void forgotPassword(UserDTO userDTO, String token);

    public User findUserByEmail(String email);

    public void resetPassword(UserDTO userDTO);

    public UserDetailDTO getUserDetail(int userId);

    public UserBookingDTO userBooking(UserBookingDTO userBookingDTO, int userId);

    public List<GetPatientDTO> getPatientList(int userId);

    public List<GetPostForDoctorDTO> getPostForDoctor(int doctorId);

    public void doctorConfirm(GetPostForDoctorDTO getPostForDoctorDTO, int userId);

    public void lockUser(LockUserDTO lockUserDTO, int userId);

    public void unLockUser(LockUserDTO lockUserDTO, int userId);

    public void addDoctorUser(DoctorUserDTO doctorUserDTO, int userId);

    public void doctorSendMail(MailDTO mailDTO, int userId);

    public List<GetPostForAdminDTO> getPostForAdmin(GetPostForAdminDTO getPostForAdminDTO, int userId);

    public List<GetDoctorScheduleDTO> getDoctorSchedule(GetDoctorScheduleDTO getDoctorScheduleDTO, int userId);




}
