package services;

import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entity.Account;
import entity.AlertLocation;
import entity.AlertProfile;
import utils.SessionUtil;

@ApplicationPath("/app")
@Path("/contact")
public class AlertService extends Application {
	@SuppressWarnings({ "deprecation", "rawtypes" })
	@POST
	@Path("/create_alertLocation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAlertLocation(AlertLocation al) {
		System.out.print("Create Alert Location");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		Integer result = null;

		try {
			tx = session.beginTransaction();

			AlertProfile ap = (AlertProfile) session.get(AlertProfile.class,
					al.getAlertProfileID().getAlertProfileID());
			if (ap != null) {
				Criteria cr = session.createCriteria(AlertLocation.class);
				cr.add(Restrictions.eq("alertProfileID", ap));
				cr.add(Restrictions.eq("city", al.getCity()));
				cr.add(Restrictions.eq("country", al.getCountry()));
				List res = cr.list();
				
				System.out.println(res.size());
				if (res.size() == 0) {
					al.setAlertProfileID(ap);
					result = (Integer) session.save(al);
				}
			}

			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result == null ? Response.status(Status.BAD_REQUEST).build() : Response.status(Status.OK).build();
	}
	
	@POST
	@Path("/create_alertProfile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAlertProfile(AlertProfile ap) {
		System.out.print("Create Alert Location");

		SessionFactory factory = SessionUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		Integer result = null;

		try {
			tx = session.beginTransaction();

			Account account = session.get(Account.class, ap.getAccountID().getId());
			if (account != null) {
				ap.setAccountID(account);
				result = (Integer) session.save(ap);
			}

			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result == null ? Response.status(Status.BAD_REQUEST).build() : Response.status(Status.OK).build();
	}
}
