package dao;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entity.Account;
import entity.Address;
import entity.Contact;
import utils.SessionUtil;

public class ContactDAO {
	public Integer createEmployee(Contact contact) {
		System.out.print("Create Employee");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		Integer result = null;

		try {
			tx = session.beginTransaction();
			Account account = (Account) session.get(Account.class, contact.getAccount().getId());
			if (account != null) {
				contact.setAccount(account);
				result = (Integer) session.save(contact);
			}

			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public JsonArray viewEmployeesByAccountID(int id) {
		System.out.println("View All Accounts");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		List results = null;
		JsonArrayBuilder builder = Json.createArrayBuilder();

		try {
			tx = session.beginTransaction();

			Criteria cr = session.createCriteria(Contact.class);
			Account account = (Account) session.get(Account.class, id);
			cr.add(Restrictions.eq("account", account));
			results = cr.list();

			for (int i = 0; i < results.size(); i++) {
				Contact contact = (Contact) results.get(i);
				Address address = contact.getAddress();
				JsonObject temp = Json.createObjectBuilder().add("contactID", contact.getContactID())
						.add("fullName", contact.getFirstName() + " " + contact.getLastName())
						.add("emailAddress", contact.getEmailAddress()).add("gender", contact.getGender())
						.add("phoneNumber", contact.getPhoneNumber()).add("activeStatus", contact.getActiveStatus())
						.add("address",
								address.getStreet() + " " + address.getCity() + " " + address.getState() + " "
										+ address.getCountry())
						.add("firstName", contact.getFirstName()).add("lastName", contact.getLastName())
						.add("street", address.getStreet()).add("city", address.getCity())
						.add("state", address.getState()).add("country", address.getCountry()).build();

				builder.add(temp);
			}
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return builder.build();
	}

	public boolean addEmployeeAccount(Contact contact) {
		System.out.print("Add employee account");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			Account account = (Account) session.get(Account.class, 0);
			if (account != null) {
				contact.setAccount(account);
				session.update(contact);
				result = true;
			}

			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	public Integer updateEmployee(int id, Contact contact) {
		System.out.print("Update employee");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		Contact con = null;
		try {
			tx = session.beginTransaction();
			con = (Contact) session.get(Contact.class, id);
			con.setFirstName(contact.getFirstName());
			con.setLastName(contact.getLastName());
			con.setEmailAddress(contact.getEmailAddress());
			con.setGender(contact.getGender());
			con.setPhoneNumber(contact.getPhoneNumber());
			con.setActiveStatus(contact.getActiveStatus());
			Address address = session.get(Address.class, id);
			address.setStreet(contact.getAddress().getStreet());
			address.setCity(contact.getAddress().getCity());
			address.setState(contact.getAddress().getState());
			address.setCountry(contact.getAddress().getCountry());
			session.update(con);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return (con != null) ? id : null;
	}

	public boolean deleteEmployee(int id) {
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
