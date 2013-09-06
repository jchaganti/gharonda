package org.jchlabs.gharonda.notifier.schedule;

import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jchlabs.gharonda.notifier.data.JobContextDataProviderIFace;
import org.jchlabs.gharonda.notifier.data.PostJobFireCBIFace;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class EmailExecutor implements Job {
	/*
	 * private static final String SMTP_HOST_NAME = "smtp.gmail.com"; private static final String SMTP_PORT = "465";
	 * private static final String emailSubjectTxt = "A test from gmail"; private static final String SSL_FACTORY =
	 * "javax.net.ssl.SSLSocketFactory"; private static Properties props = new Properties(); private static Session
	 * session = null; static { props.put("mail.smtp.host", SMTP_HOST_NAME); props.put("mail.smtp.auth", "true");
	 * props.put("mail.debug", "true"); props.put("mail.smtp.port", SMTP_PORT);
	 * props.put("mail.smtp.socketFactory.port", SMTP_PORT); props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
	 * props.put("mail.smtp.socketFactory.fallback", "false");
	 * 
	 * session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	 * 
	 * protected PasswordAuthentication getPasswordAuthentication() { return new
	 * PasswordAuthentication("jchaganti@gmail.com", "xxxxxx"); } }); }
	 */
	private static final String SMTP_HOST_NAME = "mail.Gharonda.com";
	private static final String SMTP_PORT = "25";
	private static final String emailSubjectTxt = "A test from Gharonda";
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private static Properties props = new Properties();
	private static Session session = null;
	static {
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		// props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");
		// props.put("mail.smtp.starttls.enable","true");

		session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("bilgi@Gharonda.com", "h8gmnL-7bgln");
			}
		});
	}

	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		JobContextDataProviderIFace ctxDataProvider = (JobContextDataProviderIFace) ctx.getJobDetail().getJobDataMap()
				.get("data");
		if (ctxDataProvider != null) {
			Map<String, String> jobData = (Map<String, String>) ctxDataProvider.getJobData();
			// Send mail to each user
			Message msg = new MimeMessage(session);
			InternetAddress addressFrom = null;
			try {
				addressFrom = new InternetAddress("bilgi@Gharonda.com");

				msg.setFrom(addressFrom);
				msg.setSubject(emailSubjectTxt);
				System.out.println("jobData.size()=" + jobData.size());
				InternetAddress[] addressTo = new InternetAddress[jobData.size()];
				int index = 0;

				for (Entry<String, String> entry : jobData.entrySet()) {

					String message = "No properties found!!!";
					if (entry.getValue() != null) {
						message = entry.getValue();
					}
					String email = entry.getKey();
					System.out.println("key = " + email + "   value=" + entry.getValue());
					if (email.indexOf(".com") == -1 || entry.getValue() == null) {
						continue;
					}

					addressTo[index++] = new InternetAddress(email);
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
					msg.setContent(message, "text/plain");

					Transport.send(msg);
				}
				/*
				 * msg.addRecipient(Message.RecipientType.TO, new InternetAddress("jchaganti@yahoo.com"));
				 * msg.setContent("Test message being sent", "text/plain"); Transport.send(msg);
				 */

			} catch (AddressException e1) {
				e1.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Invoke postJobFireCB
			PostJobFireCBIFace postJobCB = ctxDataProvider.getPostJobFireDataCB();
			if (postJobCB != null) {
				postJobCB.callback(ctxDataProvider.getPostJobFireData());
			}
		}

	}
}
