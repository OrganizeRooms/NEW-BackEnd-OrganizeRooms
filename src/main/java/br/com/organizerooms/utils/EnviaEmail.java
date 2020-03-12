package br.com.organizerooms.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eder Jean Dias
 */
@Service
public class EnviaEmail {

    private final String smtp = "smtp.gmail.com";
    private final Integer portaSmtp = 587;
    private final String usuarioSmtp = "projetoorganizerooms@gmail.com"; // ALTERAR PARA O NOVO EMAIL QUE DEVE DE SER CRIADO
    private final String senhaUsuarioSmtp = "organizerooms2019"; // ALTERAR PARA O NOVO EMAIL QUE DEVE DE SER CRIADO
    private DefaultAuthenticator autenticador;
    private HtmlEmail email;

    public EnviaEmail() {
    }

    private boolean validaEnvio(String emailDestino) {        
        return !emailDestino.trim().isEmpty();
    }

    public Boolean enviar(String emailDestino, String subject, String content) {
        autenticador = new DefaultAuthenticator(usuarioSmtp, senhaUsuarioSmtp);
        email = new HtmlEmail();
        if (validaEnvio(emailDestino)) {
            try {
                email.setHostName(smtp);
                email.setSmtpPort(portaSmtp);
                email.setAuthenticator(autenticador);
                email.setTLS(true);
                email.setFrom(usuarioSmtp);
                email.addTo(emailDestino.trim());
                email.setSubject(subject.trim());
                email.setHtmlMsg(content.trim());
                email.send();
            } catch (EmailException e) {
                System.err.println("Falha ao enviar email para: " + emailDestino.trim() + " | Erro: " + e.toString());
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
