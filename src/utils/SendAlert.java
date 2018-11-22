package utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class SendAlert {
	@SuppressWarnings({ "rawtypes" })
	public static boolean Send(int id) {
		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;

		try {
			tx = session.beginTransaction();
			
			String hql = "SELECT COUNT(add.addressID) FROM Address add WHERE (add.country IN ("
					+ "SELECT al.country FROM AlertLocation al WHERE al.alertProfileID = ("
					+ "SELECT ap.alertProfileID FROM AlertProfile ap WHERE ap.accountID = ("
					+ "SELECT con.account from Contact con where con.contactID = "
				    + id
				    + "))) "
				    + "OR add.city IN ("
					+ "SELECT al.city FROM AlertLocation al WHERE al.alertProfileID = ("
					+ "SELECT ap.alertProfileID FROM AlertProfile ap WHERE ap.accountID = ("
					+ "SELECT con.account from Contact con where con.contactID = "
				    + id
				    + ")))) "
				    + "AND add.addressID = "
				    + id;
			
			Query query = session.createQuery(hql);
			Long count = (Long)query.uniqueResult();
			if(count != 0)
			{
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
