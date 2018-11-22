package services;

import javax.json.JsonArray;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dao.ContactDAO;
import entity.Address;
import entity.Contact;
import utils.SendAlert;

@ApplicationPath("/app")
@Path("/contact")
public class ContactService extends Application {

	@POST
	@Path("/create_contact")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response CreateContactService(Contact contact) {

		if (contact.getAddress() == null || contact.getAccount() == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		Address address = contact.getAddress();
		address.setContact(contact);

		try {
			int res = ContactDAO.createEmployee(contact);
			if (res == 0) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			boolean result = SendAlert.Send(res);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception ex) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/view_contact_by_accountID/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewAllAccountService(@PathParam("id") int id) {
		try {
			JsonArray res = ContactDAO.viewEmployeesByAccountID(id);
			return Response.status(Status.OK).entity(res).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@PUT
	@Path("/update_contact")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response UpdateContactService(Contact contact) {
		try {
			if (contact.getContactID() == 0) {
				return Response.status(Status.BAD_REQUEST).build();
			} else {
				int res = ContactDAO.updateEmployee(contact.getContactID(), contact);
				if (res == 0) {
					return Response.status(Status.BAD_REQUEST).build();
				}
				boolean result = SendAlert.Send(res);
				return Response.status(Status.OK).entity(result).build();
			}
		} catch (Exception ex) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("/delete_contact/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response DeleteAccountService(@PathParam("id") int id) {
		try {
			boolean result = ContactDAO.deleteEmployee(id);
			if (result) {
				return Response.status(Status.OK).build();
			} else {
				return Response.status(Status.BAD_REQUEST).build();
			}
		} catch (Exception ex) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}