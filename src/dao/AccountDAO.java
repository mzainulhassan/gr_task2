package dao;

import java.io.StringReader;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entity.Account;
import utils.SessionUtil;

public class AccountDAO {

	@SuppressWarnings({ "deprecation" })
	public static Account loginAccount(String json) {
		System.out.println("Login Account");

		JsonReader reader = Json.createReader(new StringReader(json));
		JsonStructure jsonst = reader.read();
		JsonObject jsonObject = (JsonObject) jsonst;

		SessionFactory factory = null;
		Session session = null;
		Account acc = null;
		
		try {
			factory = SessionUtil.getSessionFactory();
			session = factory.openSession();
			Transaction tx = null;

			Criteria cr = session.createCriteria(Account.class);
			cr.add(Restrictions.eq("email", jsonObject.getString("email") ));
			cr.add(Restrictions.eq("password", jsonObject.getString("password")));
			Account account = (Account) cr.uniqueResult();
			
			acc = new Account();
			acc.setId(account.getId());
			acc.setEmail(account.getEmail());
			acc.setName(account.getName());
			acc.setTimezone(account.getTimezone());

			tx = session.beginTransaction();

			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return acc;
	}

	public static Integer createAccount(Account account) {
		System.out.println("Create Account");

		SessionFactory factory = null;
		Session session = null;
		Integer result = null;

		try {
			factory = SessionUtil.getSessionFactory();
			session = factory.openSession();
			Transaction tx = null;

			tx = session.beginTransaction();
			result = (Integer) session.save(account);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return result;
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static List viewAllAccounts() {
		System.out.print("View All Accounts");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		List results = null;

		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Account.class);
			results = cr.list();
//			for (int i = 0; i < results.size(); i++) {
//				Account account = (Account) results.get(i);
//				System.out.print("ID: " + account.getId());
//				System.out.print("	First Name: " + account.getName());
//				System.out.print("  Last Name: " + account.getEmail());
//				System.out.println("  Salary: " + account.getTimezone());
//			}

			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return results;
	}

	public static Account updateAccount(int id, Account account) {
		System.out.print("Update Account");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		Account acc = null;

		try {
			tx = session.beginTransaction();
			acc = (Account) session.get(Account.class, id);
			if (account.getName() != null) {
				acc.setName(account.getName());
			}
			if (account.getEmail() != null) {
				acc.setEmail(account.getEmail());
			}
			if (account.getTimezone() != null) {
				acc.setTimezone(account.getTimezone());
			}
			session.update(acc);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return acc;

	}

	public static boolean deleteAccount(int id) {
		System.out.print("Delete Account");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;

		try {
			tx = session.beginTransaction();

			Account account = (Account) session.get(Account.class, id);
			if (account != null) {
				session.delete(account);
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
}
