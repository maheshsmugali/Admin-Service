package com.pws.admin.service;

import com.pws.admin.dto.PermissionDto;
import com.pws.admin.dto.UserXrefDto;
import com.pws.admin.entity.*;
import com.pws.admin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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


    //Metods For Adding Data To Entities
    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);

    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
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
    public void updateRole(Role role) throws Exception {
        Optional<Role> optionalRole = roleRepository.findById(role.getId());
        if (optionalRole.isPresent()) {
            roleRepository.save(role);
        } else {
            throw new Exception("Role Id Not present");
        }
    }

    @Override
    public void updatrUser(User user) throws Exception {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            userRepository.save(user);
        } else {
            throw new Exception("User Id Not Found");
        }
    }


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


}
