package services;

import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dao.AccountDAO;
import entity.Account;

@ApplicationPath("/app")
@Path("/account")
public class AccountService extends Application {

	@POST
	@Path("/create_account")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response CreateAccountService(Account account) {
		try {
			AccountDAO.createAccount(account);
			return Response.status(Status.OK).build();
		} catch (Exception ex) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/view_all_account")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewAllAccountService() {
		try {
			List res = AccountDAO.viewAllAccounts();
			return Response.status(Status.OK).entity(res).build();
		} catch (Exception ex) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@POST
	@Path("/update_account")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response UpdateAccountService(Account account) {
		try {
			if (account.getId() == 0) {
				return Response.status(Status.BAD_REQUEST).build();
			} else {
				Account res = AccountDAO.updateAccount(account.getId(), account);
				return Response.status(Status.OK).entity(res).build();
			}
		} catch (Exception ex) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@POST
	@Path("/delete_account/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response DeleteAccountService(@PathParam("id") int id) {
		try {
			boolean result = AccountDAO.deleteAccount(id);
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
