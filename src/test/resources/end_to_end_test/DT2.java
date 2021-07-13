package ke.ke.UBANTU;

import org.slf4j.Logger;


@Component
public class DT2 {
    private static final Logger LOG = LoggerFactory.getLogger(SendMail.class);

    @Autowired
    private JavaMailSender jms;
    abstrct class BC2{
        
    }


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