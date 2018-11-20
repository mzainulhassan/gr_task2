package gr_task2;

import static org.junit.Assert.assertNotNull;

import java.io.Console;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import dao.AccountDAO;
import dao.ContactDAO;
import entity.Account;
import entity.Address;
import entity.AlertLocation;
import entity.AlertProfile;
import entity.Contact;
import utils.SendAlert;
import utils.SessionUtil;

public class Main {

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if(SendAlert.Send(1)) {System.out.println("Sending Alert");}
		
//		System.out.print("Create Alert");
//
//		SessionFactory factory = SessionUtil.getSessionFactory();
//		Session session = factory.openSession();
//		Transaction tx = null;
//
//		try {
//			tx = session.beginTransaction();
//			
//			Criteria cr = session.createCriteria(AlertLocation.class);
//			AlertProfile ap = session.get(AlertProfile.class, 1);
//			cr.add(Restrictions.eq("alertProfileID", ap));
//			cr.add(Restrictions.eq("city", "islamabad"));
//			cr.add(Restrictions.eq("country", "pakistan"));
//			List result = cr.list();
//			
//			if(result.size() == 0)
//			{
//				AlertLocation al = new AlertLocation("islamabad", "pakistan", ap);
//				session.save(al);
//			}
			
			
//			for (int j = 0; j < result2.size(); j++) {
//				Object[] obj = (Object[]) result2.get(j);
//				Object[] obj2 = (Object[]) result1.get(0);
//				for (int i = 0; i < obj.length; i++) {
//					System.out.println(String.valueOf(obj[i]) == String.valueOf(obj2[i]));
//				}
//				System.out.println(result2.get(j));
//			}
			

//			Account account = new Account("test", "test@gmail.com", "GMT +10");
//			int a_id = (int)session.save(account);
//			Address address = new Address("satellite town", "rawalpindi", "punjab", "pakistan");
//			Contact contact = new Contact("zain", "hassan", "zain@gmail.com", "male", "3229255755", true, address, account);
//			address.setContact(contact);
//			session.save(contact);

//			session.save(al);
			
//			tx.commit();
//		} catch (HibernateException e) {
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
	}
}
