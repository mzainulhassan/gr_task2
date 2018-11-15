package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Account;
import utils.SessionUtil;

public class AccountDAO {
	public static Integer createAccount(Account account) {
		System.out.print("Create Account");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		Integer result = null;

		try {
			tx = session.beginTransaction();
			result = (Integer) session.save(account);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	@SuppressWarnings("deprecation")
	public static boolean viewAllAccounts() {
		System.out.print("View All Accounts");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;

		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Account.class);
			List results = cr.list();
			for (int i = 0; i < results.size(); i++) {
				Account account = (Account) results.get(i);
				System.out.print("ID: " + account.getId());
				System.out.print("	First Name: " + account.getName());
				System.out.print("  Last Name: " + account.getEmail());
				System.out.println("  Salary: " + account.getTimezone());
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

	public static boolean updateAccount(Account account) {
		System.out.print("Update Account");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;

		try {
			tx = session.beginTransaction();
			session.update(account);
			tx.commit();
			result = true;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;

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
