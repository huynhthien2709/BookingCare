package com.asm3.prj321x.thienhtfx17332.service;

import com.asm3.prj321x.thienhtfx17332.dto.MailDTO.MailDTO;
import com.asm3.prj321x.thienhtfx17332.dto.userDTO.*;
import com.asm3.prj321x.thienhtfx17332.entity.*;
import com.asm3.prj321x.thienhtfx17332.repository.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorUserRepository doctorUserRepository;
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private SpecializationRepository specializationRepository;







    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    @Override
    public User saveUser(UserDTO userDTO) {
        User user = new User();
        Role role = new Role();
        role.setId(Integer.parseInt(userDTO.getRole()));
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        user.setName(userDTO.getName());
        user.setGender(userDTO.getGender());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setPassword(BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(12)));
        user.setRole(role);
        user.setCreatedAt(formattedDate);
        userRepository.save(user);
        return user;


    }
    @Override
    public UserDTO getUser(UserDTO userDTO) {
        User user = userRepository.findUserByEmail(userDTO.getEmail());
        if(user == null) return null;
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setGender(user.getGender());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setAddress(user.getAddress());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(Integer.toString(user.getRole().getId()));
        return userDTO;
    }


    @Override
    public User login(UserDTO userDTO) {
        User user = userRepository.findUserByEmail(userDTO.getEmail());
        if (user == null){
            return null;
        }
        return  user;
    }

    @Override
    public void forgotPassword(UserDTO userDTO, String token) {
        User user = userRepository.findUserByEmail(userDTO.getEmail());
        MailDTO mailDTO = new MailDTO();
        if (user != null){
            mailDTO.setTo("huynhthien2709@gmail.com");
            mailDTO.setSubject("Verification User Account");
            mailDTO.setContent("http://localhost:8081/private/reset-password?token=" + token);
            emailService.sendMail(mailDTO);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return user;
    }

    @Override
    public void resetPassword(UserDTO userDTO) {
        User user = userRepository.findUserByEmail(userDTO.getEmail());
        if (user != null){
            user.setPassword(BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(12)));
            userRepository.save(user);
        }    }

    @Override
    public UserDetailDTO getUserDetail(int userId) {
        User user = userRepository.findUserById(userId);
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        if(user == null) return null;
        userDetailDTO.setId(user.getId());
        userDetailDTO.setName(user.getName());
        userDetailDTO.setGender(user.getGender());
        userDetailDTO.setEmail(user.getEmail());
        userDetailDTO.setPhone(user.getPhone());
        userDetailDTO.setAddress(user.getAddress());
        userDetailDTO.setRole(Integer.toString(user.getRole().getId()));
        return userDetailDTO;
    }

    @Override
    @Transactional
    public UserBookingDTO userBooking(UserBookingDTO userBookingDTO, int userId) {
        User user = userRepository.findUserById(userId);
        if (user == null || user.getIsActive() == 0) return null;
        Post post = new Post();
        post.setTitle(userBookingDTO.getTitle());
        post.setContentMarkdown(userBookingDTO.getContentMarkdown());
        post.setContentHTML(userBookingDTO.getContentHtml());
        post.setDoctorUser(doctorUserRepository.findDoctorUserById(userBookingDTO.getForDoctorId()));
        post.setSpecialization(specializationRepository.findSpecializationById(userBookingDTO.getForSpecializationId()));
        post.setClinic(clinicRepository.findClinicsById(userBookingDTO.getForClinicId()));
        post.setWriterId(userId);
        post.setConfirmByDoctor(0);
        postRepository.save(post);
        return userBookingDTO;
    }

    @Override
    public List<GetPatientDTO> getPatientList(int userId) {
        User user = userRepository.findUserById(userId);
        if (user.getRole().getId() == 2){
            List<Map<String, Object>> resultList = doctorUserRepository.getPatientList(userId);
            List<GetPatientDTO> patientList = new ArrayList<>();
            for (Map e : resultList) {
                String patientName = (String) e.get("patientName");
                String gender = (String) e.get("gender");
                String address = (String) e.get("address");
                String description = (String) e.get("description");
                String historyBreath = (String) e.get("historyBreath");
                patientList.add(new GetPatientDTO(patientName, gender, address, description, historyBreath));
            }
            return patientList;
        }else {
            return null;
        }
    }

    @Override
    public List<GetPostForDoctorDTO> getPostForDoctor(int doctorId) {
        User user = userRepository.findUserById(doctorId);
        if (user.getRole().getId() == 2){
            List<Map<String, Object>> resultList = postRepository.getPostForDoctor(doctorId);
            List<GetPostForDoctorDTO> postList = new ArrayList<>();
            for (Map e : resultList) {
                int postId = (Integer) e.get("postId");
                String title = (String) e.get("title");
                int specId = (Integer) e.get("specId");
                int clinId = (Integer) e.get("clinId");
                int writerId = (Integer) e.get("writerId");
                int confirm = (Integer) e.get("confirm");
                String cancel = (String) e.get("cancel");
                postList.add(new GetPostForDoctorDTO(postId, title, specId, clinId, writerId, confirm, cancel));
            }
            return postList;
        }else {
            return null;
        }
    }

    @Override
    public void doctorConfirm(GetPostForDoctorDTO getPostForDoctorDTO, int userId) {
        User user = userRepository.findUserById(userId);
        if (user.getRole().getId() == 2 && user != null){
            Post post = postRepository.findPostById(getPostForDoctorDTO.getPostId());
            post.setConfirmByDoctor(getPostForDoctorDTO.getConfirm());
            if (getPostForDoctorDTO.getConfirm() == 2){
                post.setCancel(getPostForDoctorDTO.getCancel());
            }
            postRepository.save(post);
        }
    }

    @Override
    public void lockUser(LockUserDTO lockUserDTO, int userId) {
        User user = userRepository.findUserById(userId);
        if (user.getRole().getId() == 3){
            User patientUser = userRepository.findUserById(lockUserDTO.getUserId());
            patientUser.setIsActive(0);
            patientUser.setDescription(lockUserDTO.getDescription());
            userRepository.save(user);
        }
    }

    @Override
    public void unLockUser(LockUserDTO lockUserDTO, int userId) {
        User user = userRepository.findUserById(userId);
        if (user.getRole().getId() == 3){
            User patientUser = userRepository.findUserById(lockUserDTO.getUserId());
            patientUser.setIsActive(1);
            userRepository.save(user);
        }
    }

    @Override
    public void addDoctorUser(DoctorUserDTO doctorUserDTO, int userId) {
        User user = userRepository.findUserById(userId);
        DoctorUser doctor = doctorUserRepository.findDoctorUserByUserId(doctorUserDTO.getDoctorId());
        if (user.getRole().getId() == 3){
            DoctorUser doctorUser = new DoctorUser();
            if (doctor != null){
                doctorUser.setId(doctor.getId());
            }
            doctorUser.setUser(userRepository.findUserById(doctorUserDTO.getDoctorId()));
            doctorUser.setClinic(clinicRepository.findClinicsById(doctorUserDTO.getClinId()));
            doctorUser.setSpecialization(specializationRepository.findSpecializationById(doctorUserDTO.getSpecId()));
            doctorUser.setOverview(doctorUserDTO.getOverview());
            doctorUser.setTrainingProcess(doctorUserDTO.getTrainingProcess());
            doctorUser.setAchievements(doctorUserDTO.getAchievements());
            doctorUserRepository.save(doctorUser);
        }
    }

    @Override
    public void doctorSendMail(MailDTO mailDTO, int userId) {
        User user = userRepository.findUserById(userId);
        if (user.getRole().getId() == 2){
            mailDTO.setTo("huynhthien2709@gmail.com");
            emailService.sendMailWithAttachment(mailDTO);
        }
    }

    @Override
    public List<GetPostForAdminDTO> getPostForAdmin(GetPostForAdminDTO getPostForAdminDTO, int userId) {
        User user = userRepository.findUserById(userId);
        if (user.getRole().getId() == 3) {
            List<Map<String, Object>> resultList = postRepository.getPostForAdmin(getPostForAdminDTO.getPatientId());
            List<GetPostForAdminDTO> postList = new ArrayList<>();
            for (Map e : resultList) {
                String patientName = (String) e.get("patientName");
                String title = (String) e.get("title");
                int forDoctorId = (Integer) e.get("forDoctorId");
                int forSpecializationId = (Integer) e.get("forSpecializationId");
                int forClinicId = (Integer) e.get("forClinicId");
                int patientId = (Integer) e.get("patientId");
                postList.add(new GetPostForAdminDTO(patientName, title, forDoctorId, forSpecializationId, forClinicId, patientId));
            }
            return postList;
        } else {
            return null;
        }
    }

    @Override
    public List<GetDoctorScheduleDTO> getDoctorSchedule(GetDoctorScheduleDTO getDoctorScheduleDTO, int userId) {
        User user = userRepository.findUserById(userId);
        if (user.getRole().getId() == 3) {
            List<Map<String, Object>> resultList = postRepository.getDoctorSchedule(getDoctorScheduleDTO.getForDoctorId());
            List<GetDoctorScheduleDTO> postList = new ArrayList<>();
            for (Map e : resultList) {
                String doctorName = (String) e.get("doctorName");
                String title = (String) e.get("title");
                int doctorId = (Integer) e.get("doctorId");
                int specId = (Integer) e.get("specId");
                int clinId = (Integer) e.get("clinId");
                int patientId = (Integer) e.get("patientId");
                int confirm = (Integer) e.get("confirm");
                String cancel = (String) e.get("cancel");
                postList.add(new GetDoctorScheduleDTO(doctorName, title, doctorId, specId, clinId, patientId, confirm, cancel));
            }
            return postList;
        } else {
            return null;
        }
    }
}

