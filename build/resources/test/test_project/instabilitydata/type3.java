package ke.ke.acquireIP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public abstract class type3 impliment type2 {
    private static final Logger LOG = LoggerFactory.getLogger(SendMail.class);

    @Autowired
    private JavaMailSender jms;


    @Scheduled(fixedRate = 7200000)
    public void sendEmail() {
        try {
            Email email = new Email();
            SimpleMailMessage mainMessage = new SimpleMailMessage();

            mainMessage.setFrom(email.getSender());
            mainMessage.setTo(email.getReceiver());
            mainMessage.setSubject(email.getSubject());
            mainMessage.setText(email.getMsg());

            jms.send(mainMessage);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.debug("fail to send email", e);
        }
    }
}