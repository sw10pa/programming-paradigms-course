package PPC.controller;

import PPC.database.PPCDatabase;
import PPC.database.PPCDatabaseManager;
import PPC.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

@Controller
public class RegistrationHandler {

    private final PPCDatabaseManager dbManager;

    public RegistrationHandler() throws SQLException, ClassNotFoundException {
        PPCDatabase ppcDatabase = new PPCDatabase();
        dbManager = new PPCDatabaseManager(ppcDatabase.getConnection());
    }


    @GetMapping("/signup")
    public void get(HttpServletResponse resp, HttpSession ses) throws IOException {
        ses.setAttribute("type", "registration");
        resp.sendRedirect("/");
    }

    @PostMapping("/signup")
    public void post(HttpServletRequest req,
                     HttpServletResponse resp,
                     @RequestParam String firstName,
                     @RequestParam String lastName,
                     @RequestParam String username) throws IOException, SQLException {

        User user = dbManager.getUserByEmail(username);

        HttpSession ses = req.getSession();
        if (illegalCredentials(ses, user, firstName, lastName, username)) {
            ses.setAttribute("type", "registration");
        } else {
            String randomPassword = sendRandomPasswordToEmail(username);
            dbManager.addUser(new User(firstName, lastName, username, AuthenticationHandler.hashPassword(randomPassword)));
            ses.setAttribute("success", "Registration completed successfully. Check your Email for password");
        }
        resp.sendRedirect("/");
    }


    private boolean illegalCredentials(HttpSession ses,
                                       User user,
                                       String firstName,
                                       String lastName,
                                       String username) {

        if (firstName.length() == 0 || lastName.length() == 0 ||
                username.length() == 0) {
            ses.setAttribute("error", "Please fill every field");
            return true;
        }

        if (user != null) {
            ses.setAttribute("error", "Username is already taken");
            return true;
        }

        if (!EmailValidator.getInstance().isValid(username) || !isFreeuniMail(username)) {
            ses.setAttribute("error", "Please enter valid email");
            return true;
        }

        return false;
    }

    private boolean isFreeuniMail(String username) {
        int index = username.indexOf("@freeuni.edu.ge");
        return index > 1;
    }

    private static String generateRandomPassword() {
        SecureRandom rand = new SecureRandom();
        int randomLength = rand.nextInt(6) + 5;
        return RandomStringUtils.randomAlphabetic(randomLength);
    }


    protected static synchronized String sendRandomPasswordToEmail(String recipient) {
        String randomPassword = generateRandomPassword();
        Session session = initializeSession();
        sendEmail(session, recipient, randomPassword);
        return randomPassword;
    }

    private static Session initializeSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, PASSWORD);
            }
        };

        return Session.getInstance(props, auth);
    }

    private static void sendEmail(Session session, String recipient, String randomPassword) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "PPC"));
            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
            msg.setSubject(EMAIL_SUBJECT, "UTF-8");
            msg.setText(EMAIL_TEXT + randomPassword, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient, false));
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static final String EMAIL_FROM = "ProgrammingParadigmsCourse@gmail.com";
    private static final String PASSWORD = "Stephane27";
    private static final String EMAIL_SUBJECT = "Your Password";
    private static final String EMAIL_TEXT = "You can change your password anytime. Your current Password is: ";
}