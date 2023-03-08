package com.pws.admin.controller;

import com.pws.admin.dto.PermissionDto;
import com.pws.admin.dto.UserXrefDto;
import com.pws.admin.entity.*;
import com.pws.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {
    @Autowired
    public AdminService service;

    //Urls For Adding Data To Entities

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @PostMapping("/role")
    public void addRole(@RequestBody Role role) {
        service.addRole(role);
    }

    @PostMapping("/xref")
    public ResponseEntity<Object> addOrUpdateUserRoleXRef(@RequestBody UserXrefDto userXrefDto) throws Exception {
        service.addOrUpdateUserRoleXRef(userXrefDto);
        return ResponseEntity.ok("Data Saved");
    }

    @PostMapping("/model")
    public ResponseEntity<Object> addModel(@RequestBody Model model) {
        service.addModel(model);
        return ResponseEntity.ok("Data Saved");
    }

    @PostMapping("/permission")
    public ResponseEntity<Object> addPermission(@RequestBody PermissionDto permissionDto) throws Exception {
        service.addOrUpdatePermission(permissionDto);
        return ResponseEntity.ok("Data Saved ");

    }

    //Urls For Getting All Data From DB Of Entities

    @GetMapping("/allUsers")
    public List<User> fetcAllUsers() {
        return service.fetcAllUsers();
    }

    @GetMapping("/allRoles")
    public List<Role> fetcARoles() {
        return service.fetcAllRoles();
    }

    @GetMapping("/allPermissions")
    public List<Permission> fetcAllPermission() {
        return service.fetcAllPermission();
    }

    @GetMapping("/allModels")
    public List<Model> fetcAllModels() {
        return service.fetcAllMoldels();
    }

    @GetMapping("/allUserRoleXRefs")
    public List<UserRoleXRef> fetcAlUserRoleXRefs() {
        return service.fetcAllUserRoleXRefs();
    }

    //Urls For Getting Data From DB Of Entities Using Id's
    @GetMapping("/getUser")
    public Optional<User> getUser(@RequestParam Integer id) {
        return service.getUserById(id);
    }

    @GetMapping("/getModel")
    public Optional<Model> getModelById(@RequestParam Integer id) {
        return service.getModelById(id);
    }

    @GetMapping("/getUserRoleXRef")
    public Optional<UserRoleXRef> getUserRoleXRefById(@RequestParam Integer id) {
        return service.getUserRoleXrefById(id);
    }

    @GetMapping("/getPermission")
    public Optional<Permission> getPermissionById(@RequestParam Integer id) {
        return service.getPermissionById(id);
    }

    //Urls For Getting All Data From DB Of Entities


    @PutMapping("/update/role")
    public ResponseEntity<Object> updateRole(@RequestBody Role role) throws Exception {
        service.updateRole(role);
        return ResponseEntity.ok("Data Updated successfully");
    }

    @PutMapping("/update/user")
    public ResponseEntity<Object> updatUser(@RequestBody User user) throws Exception {
        service.updatrUser(user);
        return ResponseEntity.ok("User Data Updated");
    }

    @PutMapping("/update/model")
    public ResponseEntity<Object> updateModel(@RequestBody Model model) throws Exception {
        service.updateModel(model);
        return ResponseEntity.ok("Model Data Modified");
    }

    @PutMapping("/update/userRoleXref")
    public ResponseEntity<Object> updateUserRoleXRef(@RequestBody UserXrefDto userRoleXRefDto) throws Exception {
        service.updateUserXref(userRoleXRefDto);
        return ResponseEntity.ok("UserRoleXRef Data Replaced");
    }

    @PutMapping("/update/permission")
    public ResponseEntity<Object> updatePermission(@RequestBody PermissionDto permissionDto) throws Exception {
        service.updatePermission(permissionDto);
        return ResponseEntity.ok("Permission Data Altered");
    }

    //Urls For Deleting Data From DB Of Entities By Id

    @DeleteMapping("/delete/user")
    public ResponseEntity<Object> deleteUser(@RequestParam Integer id) {
        service.deleteUser(id);
        return ResponseEntity.ok("User Deleted Successfully");
    }

    @DeleteMapping("/delete/role")
    public ResponseEntity<Object> deleteRole(@RequestParam Integer id) {
        service.deleteRole(id);
        return ResponseEntity.ok("Role Deleted Succcessfully");
    }

    @DeleteMapping("/delete/Model")
    public ResponseEntity<Object> deleteModel(@RequestParam Integer id) {
        service.deleteModel(id);
        return ResponseEntity.ok("Model Deleted Succcessfully");
    }

    @DeleteMapping("/delete/userRoleXref")
    public ResponseEntity<Object> deleteUserRoleXref(@RequestParam Integer refId) throws Exception {
        service.deleteUserRoleXref(refId);
        return ResponseEntity.ok("UserRoleXRef Deleted Succcessfully");
    }

    @DeleteMapping("/delete/permission")
    public ResponseEntity<Object> deletePermission(@RequestParam Integer id) {
        service.deletePermission(id);
        return ResponseEntity.ok("Permission Deleted Succcessfully");
    }


}





