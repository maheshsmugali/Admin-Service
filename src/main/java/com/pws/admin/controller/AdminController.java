package com.pws.admin.controller;

import com.pws.admin.dto.*;
import com.pws.admin.entity.*;
import com.pws.admin.jwtconfiguration.JwtUtil;
import com.pws.admin.jwtconfiguration.UserDetailsServiceImpl;
import com.pws.admin.openapiconfig.SwaggerLogsConstants;
import com.pws.admin.service.AdminService;
import io.jsonwebtoken.impl.DefaultClaims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class AdminController {
    @Autowired
    public AdminService service;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceimpl;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/public/reset-password")
    public String resetPasswordForm(@RequestParam("email") String email, @RequestParam("newPassword") String newPassword, RedirectAttributes attributes) {
        try {
            service.resetPassword(email, newPassword);
            attributes.addFlashAttribute("successMessage", "Password reset successfully");
            return "redirect:/login";
        } catch (Exception e) {
            attributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/reset-password";
        }
    }

    @DeleteMapping("/logout/token")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            if(jwtUtil.isTokenBlacklisted(jwt))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalidated.");
            String userDetails = jwtUtil.getUsernameFromToken(jwt);
            // Invalidate the token
            jwtUtil.invalidateToken(jwt);
            // Clear user details from session
            request.getSession().removeAttribute("userDetails");
            return ResponseEntity.ok("Successfully logged out.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
        }
    }

    @Operation(summary = "Authentication For Generating Token ")
    @PostMapping("/authenticate")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", examples = {
                    @ExampleObject(value = SwaggerLogsConstants.USER_Authentication_UPDATE_200_SUCCESSFUL)})}),
            @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {
                    @ExampleObject(value = SwaggerLogsConstants.USER_Authentication_UPDATE_400_FAILURE)})})})
    public ResponseEntity<String> generateToken(@RequestBody LoginDto loginDto) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        UserDetails userDetails = userDetailsServiceimpl.loadUserByUsername(loginDto.getEmail());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }

    @RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
    public ResponseEntity<?> refreshtoken(HttpServletRequest httpServletRequest) throws Exception {
        // From the HttpRequest get the claims
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) httpServletRequest.getAttribute("claims");

        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        //  String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        return ResponseEntity.ok( jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString()));
    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }
    //Urls For Adding Data To Entities

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_SAVED_200_SUCCESSFULL)})}), @ApiResponse(responseCode = "500", description = "Internal Server Exception", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_SAVE_500_FAILURE)})}), @ApiResponse(responseCode = "400", description = "Improper Syntax", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_SAVE_400_FAILURE)})}), @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @Operation(summary = "Adding user")

    @PostMapping("/public/user")
    public User addUser(@RequestBody SignUpDto signUpDto) throws Exception {
        return service.userSignUp(signUpDto);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfrul Operation", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.ROLE_SAVED_200_SUCCESSFULL)})}), @ApiResponse(responseCode = "400", description = "Failure Due To Synatax Error", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.ROLE_SAVE_400_FAILURE)})}), @ApiResponse(responseCode = "500", description = "Internal Server Exception", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.ROLE_SAVE_400_FAILURE)})}), @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})

    @Operation(summary = "Adding Role")
    @PostMapping("/role")
    public String addRole(@RequestBody Role role) {
        service.addRole(role);
        return "Role Data Added";

    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_ROLE_X_REF_SAVE_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_ROLE_X_REF_SAVE_400_FAILURE)})})})
    @Operation(summary = "Adding UserRoleXREf")
    @PostMapping("/xref")
    public ResponseEntity<Object> addOrUpdateUserRoleXRef(@RequestBody UserXrefDto userXrefDto) throws Exception {
        service.addOrUpdateUserRoleXRef(userXrefDto);
        return ResponseEntity.ok("Data Saved");

    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.MODEL_SAVE_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.MODEL_SAVE_400_Failure)})})})
    @Operation(summary = "Adding Model")
    @PostMapping("/model")
    public ResponseEntity<Object> addModel(@RequestBody Model model) {
        service.addModel(model);
        return ResponseEntity.ok("Data Saved");
        //return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.OK, ("Model Data Added")));

    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.PERMISSION_SAVE_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.PERMISSION_SAVE_400_FAILURE)})})})
    @Operation(summary = "Adding Permission")
    @PostMapping("/permission")
    public ResponseEntity<Object> addPermission(@RequestBody PermissionDto permissionDto) throws Exception {
        service.addOrUpdatePermission(permissionDto);
        return ResponseEntity.ok("Data Saved ");
        // return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.OK, ("Permission Data Added")));


    }

    //Urls For Getting All Data From DB Of Entities

    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_GET_ALL_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_GET_ALL_400_FAILURE)})})})
    @Operation(summary = "Getting All Users")
    @GetMapping("/allUsers")
    public List<User> fetcAllUsers() {
        return service.fetcAllUsers();

    }


    @Operation(summary = "Getting All Roles")
    @GetMapping("/allRoles")
    public List<Role> fetcARoles() {
        return service.fetcAllRoles();
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.PERMISSION_GET_ALL_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.PERMISSION_GET_ALL_400_FAILURE)})})})
    @Operation(summary = "Getting All Permissions")
    @GetMapping("/allPermissions")
    public List<Permission> fetcAllPermission() {
        return service.fetcAllPermission();
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.MODEL_GET_ALL_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.MODEL_GET_ALL_400_FAILURE)})})})
    @Operation(summary = "Getting All Models")
    @GetMapping("/allModels")
    public List<Model> fetcAllModels() {
        return service.fetcAllMoldels();
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_ROLE_X_REF_GET_ALL_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_ROLE_X_REF_GET_ALL_400_FAILURE)})})})
    @Operation(summary = "Getting All UserRoleXRefs")
    @GetMapping("/allUserRoleXRefs")
    public List<UserRoleXRef> fetcAlUserRoleXRefs() {
        return service.fetcAllUserRoleXRefs();
    }

    //Urls For Getting Data From DB Of Entities Using Id's

    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_GET_BY_ID_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_GET_BY_ID_400_FAILURE)})})})
    @Operation(summary = "Getting User")
    @GetMapping("/getUser")
    public Optional<User> getUser(@RequestParam Integer id) {
        return service.getUserById(id);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.MODEL_GET_BY_ID_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.MODEL_GET_BY_ID_400_FAILURE)})})})
    @Operation(summary = "Getting Model")
    @GetMapping("/getModel")
    public Optional<Model> getModelById(@RequestParam Integer id) {
        return service.getModelById(id);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_ROLE_X_REF_GET_BY_ID_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_ROLE_X_REF_GET_BY_ID_400_FAILURE)})})})
    @Operation(summary = "Getting UserRoleXRef")
    @GetMapping("/getUserRoleXRef")
    public Optional<UserRoleXRef> getUserRoleXRefById(@RequestParam Integer id) {
        return service.getUserRoleXrefById(id);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.PERMISSION_GET_BY_ID_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.PERMISSION_GET_BY_ID_400_FAILURE)})})})
    @Operation(summary = "Getting Permission")
    @GetMapping("/getPermission")
    public Optional<Permission> getPermissionById(@RequestParam Integer id) {
        return service.getPermissionById(id);
    }


    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.ROLE_UPDATED_200_SUCCESSFULL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.ROLE_UPDATE_400_FAILURE)})})})
    @Operation(summary = "Update Role")
    @PutMapping("/update/role")
    public ResponseEntity<Object> updateRole(@RequestBody Role role) throws Exception {
        service.updateRole(role);
        return ResponseEntity.ok("Data Updated successfully");
        //return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.OK, ("Data Updated successfully")));

    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_UPDATE_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_UPDATE_400_FAILURE)})})})
    @Operation(summary = "Update user")
    @PutMapping("/update/user")
    public ResponseEntity<Object> updatUser(@RequestBody User user) throws Exception {
        service.updatrUser(user);
        return ResponseEntity.ok("User Data Updated");
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.MODEL_UPDATE_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.MODEL_UPDATE_400_FAILURE)})})})
    @Operation(summary = "Update Model")
    @PutMapping("/update/model")
    public ResponseEntity<Object> updateModel(@RequestBody Model model) throws Exception {
        service.updateModel(model);
        return ResponseEntity.ok("Model Data Modified");
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_ROLE_X_REF_UPDATE_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_ROLE_X_REF_UPDATE_400_FAILURE)})})})
    @Operation(summary = "Update UserRoleXRef")
    @PutMapping("/update/userRoleXref")
    public ResponseEntity<Object> updateUserRoleXRef(@RequestBody UserXrefDto userRoleXRefDto) throws Exception {
        service.updateUserXref(userRoleXRefDto);
        return ResponseEntity.ok("UserRoleXRef Data Replaced");
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.PERMISSION_UPDATE_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.PERMISSION_UPDATE_400_FAILURE)})})})
    @Operation(summary = "Update Permission")
    @PutMapping("/update/permission")
    public ResponseEntity<Object> updatePermission(@RequestBody PermissionDto permissionDto) throws Exception {
        service.updatePermission(permissionDto);
        return ResponseEntity.ok("Permission Data Altered");
    }

    //Urls For Deleting Data From DB Of Entities By Id
    @Operation(summary = "Deleting User")
    @DeleteMapping("/delete/user")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_DELETE_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_DELETE_400_FAILURE)})})})
    public ResponseEntity<Object> deleteUser(@RequestParam Integer id) {
        service.deleteUser(id);
        return ResponseEntity.ok("User Deleted Successfully");
    }

    @Operation(summary = "Deleting Role")
    @DeleteMapping("/delete/role")
    public ResponseEntity<Object> deleteRole(@RequestParam Integer id) {
        service.deleteRole(id);
        return ResponseEntity.ok("Role Deleted Succcessfully");
    }

    @Operation(summary = "Deleting Model")
    @DeleteMapping("/delete/Model")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.MODEL_DELETE_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.MODEL_DELETE_400_FAILURE)})})})
    public ResponseEntity<Object> deleteModel(@RequestParam Integer id) {
        service.deleteModel(id);
        return ResponseEntity.ok("Model Deleted Succcessfully");
    }

    @Operation(summary = "Deleting UserRoleXRef")
    @DeleteMapping("/delete/userRoleXref")
    public ResponseEntity<Object> deleteUserRoleXref(@RequestParam Integer refId) throws Exception {
        service.deleteUserRoleXref(refId);
        return ResponseEntity.ok("UserRoleXRef Deleted Succcessfully");
    }

    @Operation(summary = "Deleting Permission")
    @DeleteMapping("/delete/permission")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.PERMISSION_DELETE_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.PERMISSION_DELETE_400_FAILURE)})})})
    public ResponseEntity<Object> deletePermission(@RequestParam Integer id) {
        service.deletePermission(id);
        return ResponseEntity.ok("Permission Deleted Succcessfully");
    }

    //
//    @PostMapping("/user/updatePassword")
//    @PreAuthorize("hasRole('READ_PRIVILEGE')")
//    public GenericResponse changeUserPassword(Locale locale,
//                                              @RequestParam("password") String password,
//                                              @RequestParam("oldpassword") String oldPassword) {
//        User user = Provider.Service.findUserByEmail(
//                SecurityContextHolder.getContext().getAuthentication().getName());
//
//        if (!adminService.checkIfValidOldPassword(user, oldPassword)) {
//            throw new InvalidOldPasswordException();
//        }
//        userService.changeUserPassword(user, password);
//        return new GenericResponse(messages.getMessage("message.updatePasswordSuc", null, locale));
//    }
    @Operation(summary = "Changing The password")
    @PostMapping("/update/user/password")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_PASSWORD_UPDATE_200_SUCCESSFUL)})}), @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerLogsConstants.USER_PASSWORD_UPDATE_400_FAILURE)})})})
    public ResponseEntity<Object> updateUserPassword(@RequestBody PasswordDTO passwordDTO) throws Exception {
        service.changePassword(passwordDTO);
        return ResponseEntity.ok("password updated successfully");
    }

}





