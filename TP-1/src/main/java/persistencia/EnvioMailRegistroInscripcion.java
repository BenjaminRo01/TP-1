package persistencia;

import actividad1.Concurso;
import actividad1.Participante;
import actividad1.RegistroDeInscripcion;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EnvioMailRegistroInscripcion implements RegistroDeInscripcion {
    private String host;
    private String port;
    private String username;
    private String password;
    private String from;
    private String to;

    public EnvioMailRegistroInscripcion(String host, String port, String username, String password, String from, String to) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.from = from;
        this.to = to;
    }
    @Override
    public void registrar(LocalDate fecha, Participante participante, Concurso concurso) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.port", this.port);
        //create the Session object
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            //Se crea un objeto MimeMessage para poder realizar las operaciones de configuracion del email
            Message message = new MimeMessage(session);
            //Se añade "quien" envia el mail
            message.setFrom(new InternetAddress(this.from));
            //Se añade "a quien" se va a enviar el mail
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
            //Se añade el asunto del email
            message.setSubject("Registro de Inscripción");
            //Se añade el contenido del mensaje de texto
            String registro = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(fecha)
                    + " || " + participante.obtenerNombre()
                    + " || " + concurso.obtenerNombre() + "\n";
            message.setText(registro);
            //Se envia el mensaje email
            Transport.send(message);
            System.out.println("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException("No se ha podido enviar el mail", e);
        }
    }
}
