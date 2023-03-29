package com.pws.admin.service;

import com.pws.admin.dto.*;
import com.pws.admin.entity.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    //Methods For Adding Data To Entities
    Role addRole(Role role);


    User userSignUp(SignUpDto signUpDto) throws Exception;

    void addOrUpdateUserRoleXRef(UserXrefDto userXrefDto) throws Exception;

    void addModel(Model model);

    ResponseEntity<Object> addOrUpdatePermission(PermissionDto permissionDto) throws Exception;

    //Methods For Grtting All Data From Entities
    List<User> fetcAllUsers();

    List<Role> fetcAllRoles();

    List<Permission> fetcAllPermission();

    List<Model> fetcAllMoldels();

    List<UserRoleXRef> fetcAllUserRoleXRefs();

    //Methods For Getting Data From Entities Using Id's

    Optional<User> getUserById(Integer id);

    Optional<Role> getRoleById(Integer id);

    Optional<Permission> getPermissionById(Integer id);

    Optional<UserRoleXRef> getUserRoleXrefById(Integer id);

    Optional<Model> getModelById(Integer id);


    //  Methods for Updating Data of Entities in DB

    void updateRole(Role role) throws Exception;

    void updatrUser(User user) throws Exception;

    void updateModel(Model model) throws Exception;

    void updateUserXref(UserXrefDto userXrefDto) throws Exception;

    void updatePermission(PermissionDto permissionDto) throws Exception;

    //Methods For Deleting Data From DB Of Entities By Id

    void deleteUser(Integer id);

    void deleteModel(Integer id);

    void deletePermission(Integer id);

    void deleteUserRoleXref(Integer refId) throws Exception;

    void deleteRole(Integer id);

    public void changePassword(PasswordDTO passwordDTO) throws Exception ;

    public void resetPassword(String email, String newPassword) throws Exception;

    void updateResetPasswordToken(String token, String email)throws Exception;

    public String showResetPasswordForm( String token, Model model) throws Exception;

    public String processResetPassword(String token, String password, Model model)throws Exception;

    public User getByResetPasswordToken(String token)throws Exception;

    public void updatePassword(User user, String newPassword) throws Exception;

    void updatePassword(ResetUpdatepassword resetUpdatepassword) throws Exception;
}
