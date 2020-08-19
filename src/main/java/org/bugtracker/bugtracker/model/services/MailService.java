package org.bugtracker.bugtracker.model.services;

import javax.mail.MessagingException;

public interface MailService {
    void generateResetPasswordMail(String email) throws MessagingException;
}
