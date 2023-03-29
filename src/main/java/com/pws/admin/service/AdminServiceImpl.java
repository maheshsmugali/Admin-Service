package com.pws.admin.service;

import com.pws.admin.dto.*;
import com.pws.admin.entity.*;
import com.pws.admin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleXRefRepository userRoleXRefRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    PermissionRepository permissionRepository;


    @Override
    public void updateResetPasswordToken(String token, String email) throws Exception{
        User user=userRepository.findUserByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new Exception("Could not find any customer with the email " + email);
        }
    }

    @Override
    public User getByResetPasswordToken(String token) throws Exception {
        return userRepository.findByResetPasswordToken(token);
    }

    @Override
    public String showResetPasswordForm(String token, Model model) throws Exception {
        return null;
    }

    @Override
    public String processResetPassword(String token, String password, Model model) throws Exception {
        return null;
    }


    @Override
    public void updatePassword(User user, String newPassword) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate user session
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        if (session != null) {
            session.invalidate();
        }

        // Delete authentication cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth_token")) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }

        // Return success response
        return ResponseEntity.ok("Logout Successful");
    }


            //Metods For Adding Data To Entities
    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);

    }

    @Override
    public User userSignUp(SignUpDto signupDTO) throws Exception {
        // Check if the password is strong
        if (!isStrongPassword(signupDTO.getPassword())) {
            throw new Exception("Password is not strong , at least one uppercase letter, one lowercase letter, one digit, and one special character needed");
        }

        Optional<User> optionalUser = userRepository.findByEmail(signupDTO.getEmail());
        if (optionalUser.isPresent()) throw new Exception("User Already Exist with Email : " + signupDTO.getEmail());
        User user = new User();
        user.setDob(signupDTO.getDob());
        user.setFirstName(signupDTO.getFirstName());
        user.setIsActive(true);
        user.setLastName(signupDTO.getLastName());
        user.setEmail(signupDTO.getEmail());
        user.setPhoneNumber(signupDTO.getPhoneNumber());
        PasswordEncoder encoder = new BCryptPasswordEncoder(8);
        // Set new password
        user.setPassword(encoder.encode(signupDTO.getPassword()));

        userRepository.save(user);
        return user;
    }


    @Override
    public void addOrUpdateUserRoleXRef(UserXrefDto userXrefDto) throws Exception {
        Optional<UserRoleXRef> optionalUserRoleXRef = userRoleXRefRepository.findById(userXrefDto.getRefId());
        UserRoleXRef userRoleXRef = null;
        if (optionalUserRoleXRef.isPresent()) {
            userRoleXRef = optionalUserRoleXRef.get();
        } else {

            userRoleXRef = new UserRoleXRef();

        }
        Optional<User> optionalUser = userRepository.findById(userXrefDto.getUserId());
        if (optionalUser.isPresent()) {
            userRoleXRef.setUser(optionalUser.get());
        } else {
            throw new Exception("UserId not present");
        }

        Optional<Role> optionalRole = roleRepository.findById(userXrefDto.getRoleId());
        if (optionalRole.isPresent()) {
            userRoleXRef.setRole(optionalRole.get());
        } else {
            throw new Exception("RoleId Not present");
        }
        userRoleXRef.setIsActive(userXrefDto.getIsActive());

        userRoleXRefRepository.save(userRoleXRef);
    }

    public List<UserRoleXRef> fetcAll() {
        return userRoleXRefRepository.findAll();
    }

    @Override
    public void addModel(Model model) {
        modelRepository.save(model);
    }

    @Override
    public ResponseEntity<Object> addOrUpdatePermission(PermissionDto permissionDto) throws Exception {
        Optional<Permission> optionalPermission = permissionRepository.findById(permissionDto.getId());
        Permission permission = null;
        if (optionalPermission.isPresent()) {
            permission = optionalPermission.get();
        } else {
            permission = new Permission();
        }
        permission.setIsActive(permissionDto.getIsActive());
        permission.setIsView(permissionDto.getIsView());
        permission.setIsAdd(permissionDto.getIsAdd());
        permission.setIsUpdate(permissionDto.getIsUpdate());
        permission.setIsDelete(permissionDto.getIsDelete());
        Optional<Model> optionalModel = modelRepository.findById(permissionDto.getModelId());
        if (optionalModel.isPresent()) {
            permission.setModel(optionalModel.get());
        } else {
            throw new Exception("Module Id Not found");
        }
        Optional<Role> optionalRole = roleRepository.findById(permissionDto.getRoleId());
        if (optionalRole.isPresent()) {
            permission.setRole(optionalRole.get());
        } else {
            throw new Exception("Role Id Not found");
        }
        permissionRepository.save(permission);
        return ResponseEntity.ok("data Saved successfully");
    }

    //Metods For Grtting All Data From Entities

    @Override
    public List<User> fetcAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Permission> fetcAllPermission() {
        return permissionRepository.findAll();
    }

    @Override
    public List<Model> fetcAllMoldels() {
        return modelRepository.findAll();
    }

    @Override
    public List<UserRoleXRef> fetcAllUserRoleXRefs() {
        return userRoleXRefRepository.findAll();
    }

    @Override
    public List<Role> fetcAllRoles() {

        return roleRepository.findAll();
    }

    //Metods For Getting Data From Entities Using Id's

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }
    @Override
    public Optional<Role> getRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Permission> getPermissionById(Integer id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Optional<UserRoleXRef> getUserRoleXrefById(Integer id) {
        return userRoleXRefRepository.findById(id);
    }

    @Override
    public Optional<Model> getModelById(Integer id) {
        return modelRepository.findById(id);
    }




    //  Methods for Updating Data of Entities in DB

    @Override
    public void updateModel(Model model) throws Exception {
        Optional<Model> optionalModel = modelRepository.findById(model.getModelId());
        if (optionalModel.isPresent()) {
            modelRepository.save(model);
        } else {
            throw new Exception("Model Id Not Found");
        }
    }
    @Override
    public void updateRole(Role role) throws Exception {
    Optional<Role> optionalRole = roleRepository.findById(role.getId());
    if(optionalRole.isPresent()){
        roleRepository.save(role);
    }
    else {
        throw new Exception("Role Not found");
    }
    }
    @Override
    public void updatrUser(User user) throws Exception {
        Optional<User> optionalUser= userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()){
            userRepository.save(user);
        }
        else {
             throw new Exception("User not found");
        }
    }
    @Override
    public void updateUserXref(UserXrefDto userXrefDto) throws Exception {
        Optional<UserRoleXRef> optionalUserRoleXref = userRoleXRefRepository.findById(userXrefDto.getRefId());
        UserRoleXRef userRoleXref = null;
        if (optionalUserRoleXref.isPresent()) {
            userRoleXref = optionalUserRoleXref.get();
        } else {
            throw new Exception(" Id not Found");
        }
        Optional<User> optionalUser = userRepository.findById(userXrefDto.getUserId());
        if (optionalUser.isPresent()) {
            userRoleXref.setUser(optionalUser.get());
        } else {
            throw new Exception("User id not found");
        }
        Optional<Role> optionalRole = roleRepository.findById(userXrefDto.getRoleId());
        if (optionalRole.isPresent()) {
            userRoleXref.setRole(optionalRole.get());
        } else {
            throw new Exception("role id not found");
        }
        userRoleXref.setIsActive(userXrefDto.getIsActive());
        userRoleXRefRepository.save(userRoleXref);
    }

    @Override
    public void updatePermission(PermissionDto permissionDto) throws Exception {
        Optional<Permission> optionalPermission = permissionRepository.findById(permissionDto.getId());
        Permission permission = null;
        if (optionalPermission.isPresent()) {
            permission = optionalPermission.get();
        } else {
            throw new Exception("Permission Id Not Found");
        }
        Optional<Model> optionalModel = modelRepository.findById(permissionDto.getModelId());
        if (optionalModel.isPresent()) {
            permission.setModel(optionalModel.get());
        } else {
            throw new Exception("Model Id Not Found ");
        }
        Optional<Role> optionalRole = roleRepository.findById(permissionDto.getRoleId());
        if (optionalRole.isPresent()) {
            permission.setRole(optionalRole.get());
        } else {
            throw new Exception("Role Id Not Found");
        }
        permission.setIsActive(permissionDto.getIsActive());
        permission.setIsAdd(permissionDto.getIsAdd());
        permission.setIsView(permissionDto.getIsView());
        permission.setIsUpdate(permissionDto.getIsUpdate());
        permission.setIsDelete(permissionDto.getIsDelete());

        permissionRepository.save(permission);
    }

    //Methods For Deleting Data From DB Of Entities By Id

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteModel(Integer id) {
        modelRepository.deleteById(id);
    }

    @Override
    public void deletePermission(Integer id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public void deleteUserRoleXref(Integer refId) throws Exception {
        Optional<UserRoleXRef> optionalUserRoleXRef = userRoleXRefRepository.findById(refId);
        if (optionalUserRoleXRef.isPresent()) {
            userRoleXRefRepository.deleteById(refId);
        } else {
            throw new Exception(refId + " UserRoleXref Id Not found");
        }
    }

    @Override
    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void changePassword(PasswordDTO passwordDTO) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(passwordDTO.getEmail());
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = null;
        if (!optionalUser.isPresent()) {
            throw new Exception("Email Not Found");
        }
        user = optionalUser.get();
        if (encoder.matches(passwordDTO.getOldPassword(), user.getPassword())) {
            if (!isStrongPassword(passwordDTO.getNewPassword())) {
                throw new Exception("Password is not strong , at least one uppercase letter, one lowercase letter, one digit, and one special character needed");
            }

            if ( (passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword()))) {
                if (!passwordDTO.getOldPassword().equals(passwordDTO.getNewPassword())) {
                    user.setPassword(encoder.encode(passwordDTO.getNewPassword()));
                    userRepository.save(user);
                } else {
                    throw new Exception("OldPassword and newPassword are same or Password is not strong");
                }}
             else {
                throw new Exception("NewPassword and confirmPassword does not same");
            }
        } else {
            throw new Exception("please enter your correct old password");
        }
    }

    @Override
    public void updatePassword(ResetUpdatepassword resetUpdatepassword) throws Exception {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByResetPasswordToken(resetUpdatepassword.getToken()));
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = null;
        if (!optionalUser.isPresent()) {
            throw new Exception("email not found");
        }
        user = optionalUser.get();
        if (!encoder.matches(resetUpdatepassword.getNewPassword(), user.getPassword())) {
            if (!isStrongPassword(String.valueOf(resetUpdatepassword.getNewPassword().equals(resetUpdatepassword.getConfirmPassword())))) {
                if (resetUpdatepassword.getNewPassword().equals(resetUpdatepassword.getConfirmPassword())) {
                    user.setPassword(encoder.encode(resetUpdatepassword.getNewPassword()));
                    userRepository.save(user);
                } else {
                    throw new Exception("NewPassword and confirmPassword does not same");
                }
            } else {
                throw new Exception("Password should be strong or contains special characters");
            }
        } else {
            throw new Exception("old password cannot be your new password");
        }
    }


    private boolean isStrongPassword(String password) {
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        // check for at least one uppercase letter, one lowercase letter, one digit, and one special character
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (isSpecialChar(ch)) {
                hasSpecialChar = true;
            }
        }
        // check if password meets all criteria
        return password.length() >= 8 && hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }

    private boolean isSpecialChar(char ch) {
        String specialChars = "!@#$%^&*()_-+=[{]};:<>|./?";
        return specialChars.contains(Character.toString(ch));
    }


    @Override
    public void resetPassword(String email, String newPassword) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        User user = optionalUser.get();
        PasswordEncoder encoder = new BCryptPasswordEncoder(8);
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
    }


}
