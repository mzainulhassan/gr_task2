package utils;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class SendAlert {
	@SuppressWarnings("rawtypes")
	public static boolean Send(int id) {
		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;

		try {
			tx = session.beginTransaction();
			String hql = "SELECT add.country FROM Address add WHERE add.addressID = " + id;
			Query query = session.createQuery(hql);
			List result_address = query.list();

			hql = "SELECT AL.country FROM AlertLocation AL, AlertProfile AP WHERE AL.alertProfileID = "
					+ "(SELECT AP.alertProfileID FROM Account A, AlertProfile AP WHERE AP.accountID = "
					+ "(SELECT con.account FROM Contact con WHERE con.account = " + id + "))";
			query = session.createQuery(hql);
			List result_AlertLocation = query.list();

			for (int i = 0; i < result_AlertLocation.size(); i++) {
				if (result_AlertLocation.get(i).equals(result_address.get(0))) {
					result = true;
					break;
				}
			}

			if (!result) {
				hql = "SELECT add.city FROM Address add WHERE add.addressID = " + id;
				query = session.createQuery(hql);
				result_address = query.list();

				hql = "SELECT AL.city FROM AlertLocation AL, AlertProfile AP WHERE AL.alertProfileID = "
						+ "(SELECT AP.alertProfileID FROM Account A, AlertProfile AP WHERE AP.accountID = "
						+ "(SELECT con.account FROM Contact con WHERE con.account = " + id + "))";
				query = session.createQuery(hql);
				result_AlertLocation = query.list();

				for (int i = 0; i < result_AlertLocation.size(); i++) {
					if (result_AlertLocation.get(i).equals(result_address.get(0))) {
						result = true;
						break;
					}
				}
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
