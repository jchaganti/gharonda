package org.jchlabs.gharonda.guice;

import java.util.Properties;

import javax.mail.Session;

import com.google.inject.Provider;

public class SessionProvider implements Provider<Session> {

	public Session get() {
		Session session = Session.getDefaultInstance(new Properties(), null);
		return session;
	}
}
