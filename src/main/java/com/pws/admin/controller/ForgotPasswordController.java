package com.pws.admin.controller;


import com.pws.admin.dto.ResetUpdatepassword;
import com.pws.admin.entity.User;
import com.pws.admin.jwtconfiguration.JwtUtil;
import com.pws.admin.service.AdminService;
import com.pws.admin.utility.ApiSuccess;
import com.pws.admin.utility.CommonUtils;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("public/forgot_password")
    public ResponseEntity<Object> processForgotPassword(@RequestParam String email, Model model) throws Exception, MessagingException, UnsupportedEncodingException {
        String token = RandomString.make(30);

        try {
            adminService.updateResetPasswordToken(token, email);
            String resetPasswordLink = (email) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.OK,("\"We have sent a reset password link to your email. Please check.\"")));

         //   return "We have sent a reset password link to your email. Please check.";

        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.OK,("forgot_password_form")));

       // return "forgot_password_form";
    }


    public void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@PWS.com", "PWS Support");
        String recipientEmail = email;
        helper.setTo(email);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + resetPasswordLink + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);

    }


    @GetMapping("public/`reset_password`")
    public ResponseEntity<Object> showResetPasswordForm(@RequestParam String token, Model model) throws Exception {
        User user = adminService.getByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.OK,("message")));

           // return "message";
        }
        return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.OK,("reset_password_form")));

        //return "reset_password_form";
    }


    @PostMapping("public/reset_password")
    public ResponseEntity<Object> processResetPassword(@RequestBody ResetUpdatepassword resetUpdatepassword, Model model) throws Exception {
        Optional<User> user = Optional.ofNullable(adminService.getByResetPasswordToken(resetUpdatepassword.getToken()));
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.OK,("message In_valid token")));

            //return "message In_valid token";
        } else {
            adminService.updatePassword(resetUpdatepassword);

            model.addAttribute("message", "You have successfully changed your password.");
            return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.OK,("valid token")));

            // return "message valid token";
        }}
}