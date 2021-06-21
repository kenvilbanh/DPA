package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.CustomerAccount;
import com.fa.DPA.model.PasswordResetToken;
import com.fa.DPA.service.CustomerAccountService;
import com.fa.DPA.service.ResetPasswordTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/forgot-password")
public class ForgotPasswordController {
    private CustomerAccountService customerAccountService;
    private ResetPasswordTokenService passwordTokenService;
    private JavaMailSender mailSender;

    @Autowired
    public ForgotPasswordController(CustomerAccountService customerAccountService,
                                    ResetPasswordTokenService passwordTokenService ,JavaMailSender mailSender) {
        this.customerAccountService = customerAccountService;
        this.mailSender = mailSender;
        this.passwordTokenService = passwordTokenService;
    }


    /**
     *
     * @param user
     * @param token
     */
    protected void createPasswordResetTokenForUser(CustomerAccount user, String token) {
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setUser(user);
        myToken.setToken(token);
        passwordTokenService.save(myToken);
    }

    /**
     *
     * @param toEmail
     * @param url
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    protected void sendEmail(String toEmail, String url) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom("dinhanh1797@gmail.com", "<Reset Password>");

        helper.setTo(toEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + url + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }


    /**
     *
     * @param email
     * @return
     */
    @PostMapping("/send-email")
    public ResponseEntity<CustomerAccount> sendEmailResetPassword(@RequestBody Map<String, String> email){
        System.out.println(email.get("email"));
        CustomerAccount customerAccount;
        try{
            customerAccount = customerAccountService.findByEmail(email.get("email"));
        }catch (EntityNotFoundException ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        if(customerAccount == null){

        }
        System.out.println(customerAccount);
        String token = UUID.randomUUID().toString();
        createPasswordResetTokenForUser(customerAccount, token);
        try{
            sendEmail(customerAccount.getEmail(), Constant.URL_RESET_PASSWORD + "/" + token);
        }catch (MessagingException | UnsupportedEncodingException ex){
            System.out.println("Error in send mail");
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(customerAccount, HttpStatus.OK);
    }


    /**
     *
     * @param token
     * @return
     */
    @GetMapping("/change-password/{token}")
    public ResponseEntity<Long> getResetPasswordPage(@PathVariable("token") String token){
        LocalDateTime timeNow = LocalDateTime.now();
        CustomerAccount returnCustomerAccount;

        try{
            returnCustomerAccount = customerAccountService.findByTokenPassword(token, timeNow);
        }catch (EntityNotFoundException ex){
            System.out.println(ex);
            return new ResponseEntity<>( null, HttpStatus.SERVICE_UNAVAILABLE);
        }

        return new ResponseEntity<>(returnCustomerAccount.getId(), HttpStatus.OK);
    }

    /**
     *
     * @param newPassword
     * @return
     */
    @PutMapping("/change-password/reset")
    public ResponseEntity<CustomerAccount> resetPassword(@RequestBody Map<String, String> newPassword){
        String _newPassword = newPassword.get("new-password");
        if(_newPassword == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Long id;
        try{
            id = Long.parseLong(newPassword.get("id"));
        }catch (NumberFormatException | NullPointerException ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        CustomerAccount customerAccount;
        try{
            customerAccount  = customerAccountService.findById(id);
        }catch (EntityNotFoundException ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        customerAccount.setPassword(_newPassword);
        CustomerAccount customerAccountReturn = customerAccountService.saveAccount(customerAccount);
        if(customerAccountReturn == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(customerAccountReturn, HttpStatus.OK);
    }
}
