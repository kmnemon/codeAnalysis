package ke.ke.XP;

import org.aaa.bbb.ccc

@Component
public class DT1 extend DT2{
    private static final Logger LOG = LoggerFactory.getLogger(SendMail.class);

    @Autowired
    private JavaMailSender jms;
    abstract class BC1{

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

enum AAA{
    
}