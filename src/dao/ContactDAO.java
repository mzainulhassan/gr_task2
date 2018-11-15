package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entity.Account;
import entity.Contact;
import utils.SessionUtil;

public class ContactDAO {
	public static Integer createEmployee(Contact contact) {
		System.out.print("Create Employee");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		Integer result = null;

		try {
			tx = session.beginTransaction();
			result = (Integer) session.save(contact);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	@SuppressWarnings("deprecation")
	public static boolean viewEmployeesByAccountID(int id) {
		System.out.print("View All Accounts");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;

		try {
			tx = session.beginTransaction();

			Criteria cr = session.createCriteria(Contact.class);
			Account account = (Account) session.get(Account.class, id);
			cr.add(Restrictions.eq("account", account));
			List results = cr.list();
			for (int i = 0; i < results.size(); i++) {
				Contact contact = (Contact) results.get(i);
				System.out.print("ID: " + contact.getContactID());
				System.out.print("	 First Name: " + contact.getFirstName());
				System.out.print("  Last Name: " + contact.getLastName());
				System.out.print("  Email Address: " + contact.getEmailAddress());
				System.out.print("  Gender: " + contact.getGender());
				System.out.print("  Phone Number: " + contact.getPhoneNumber());
				System.out.print("  Active Status: " + contact.getActiveStatus());
				System.out.print("  Address: " + contact.getAddress());
				System.out.println("	Account " + contact.getAccount());
			}
			tx.commit();
			result = true;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	public static boolean addEmployeeAccount(Contact contact) {
		System.out.print("Add employee account");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			session.update(contact);
			tx.commit();
			result = true;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	public static boolean updateEmployee(Contact contact) {
		System.out.print("Update employee");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			session.update(contact);
			tx.commit();
			result = true;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	public static boolean deleteEmployee(int id) {
		System.out.print("Delete Employee");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;

		try {
			tx = session.beginTransaction();

			Contact contact = (Contact) session.get(Contact.class, id);
			session.delete(contact);

			tx.commit();
			result = true;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}
}
