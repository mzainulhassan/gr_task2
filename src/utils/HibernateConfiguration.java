//package utils;
//
//import java.net.URL;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//import org.hibernate.service.ServiceRegistryBuilder;
//
//public class HibernateConfiguration {
//	private static SessionFactory sessionFactory = null;
//	private static ServiceRegistry serviceRegistry = null;
//	
//	public static SessionFactory getSessionFactory() {
//
//		if (sessionFactory == null) {
//			try {
//				URL cfgUrl = HibernateConfiguration.class.getClassLoader().getResource("hibernate.cfg.xml");
//				sessionFactory = buildSessionFactory(cfgUrl);
//			} catch (Exception ex) {
//				System.out.println("GET" + ex);
//				throw new ExceptionInInitializerError(ex);
//			}
//		}
//		return sessionFactory;
//	}
//
//	private static SessionFactory buildSessionFactory(URL cfgUrl) {
//		try {
//			Configuration configuration = new Configuration();
//			configuration.configure(cfgUrl);
//			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
//			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//		} catch (Exception ex) {
//			System.out.println("BUILD" + ex);
//		}
//		
//		return sessionFactory;
//	}
//	
//	public static Session openSession() {
//		return getSessionFactory().openSession();
//	}
//
//	/**
//	 * This method closes the given Hibernate session.
//	 * 
//	 * @param session
//	 *            Specifies Hibernate session to be closed.
//	 */
//	public static void closeSession(Session session) {
//		if (session.isOpen()) {
//			session.close();
//		}
//	}
//
//	/**
//	 * This method is used to get hibernate session for a JTA transaction.
//	 * 
//	 * @return Hibernate session for a JTA transaction
//	 */
//	public static Session getCurrentSession() {
//		Session session = null;
//		if (getSessionFactory().getCurrentSession() != null) {
//			session = getSessionFactory().getCurrentSession();
//		} else if (session == null || session.isOpen() == false) {
//			session = getSessionFactory().openSession();
//		}
//
//		return session;
//	}
//}
