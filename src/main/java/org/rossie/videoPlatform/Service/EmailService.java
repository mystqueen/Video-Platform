package org.rossie.videoPlatform.Service;

public interface EmailService {
    String sendMail(String to, String cc,String subject,String body);
}
