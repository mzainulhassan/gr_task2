//package test;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Test;
//
//import dao.AccountDAO;
//import dao.ContactDAO;
//import entity.Account;
//import entity.Address;
//import entity.Contact;
//
//public class ContactTest {
//	@Test
//	public void testCreateEmployee() {
//		Account account = new Account("test", "test@gmail.com", "GMT +10");
//		int a_id = AccountDAO.createAccount(account);
//		Address address = new Address("satellite town", "sargodha", "punjab", "pakistan");
//		Contact contact = new Contact("zain", "hassan", "zain@gmail.com", "male", "3229255755", true, address, account);
//		address.setContact(contact);
//		int c_id = ContactDAO.createEmployee(contact);
//		assertNotNull(c_id);
//		ContactDAO.deleteEmployee(c_id);
//		AccountDAO.deleteAccount(a_id);
//	}
//
//	@Test
//	public void testViewEmployeesByAccountID() {
//		Account account = new Account("test", "test@gmail.com", "GMT +10");
//		int a_id = AccountDAO.createAccount(account);
//		Address address1 = new Address("satellite town", "sargodha", "punjab", "pakistan");
//		Contact contact1 = new Contact("zain", "hassan", "zain@gmail.com", "male", "3229255755", true, address1,
//				account);
//		address1.setContact(contact1);
//		Address address2 = new Address("satellite town", "sargodha", "punjab", "pakistan");
//		Contact contact2 = new Contact("zain", "hassan", "zain@gmail.com", "male", "3229255755", true, address1,
//				account);
//		address2.setContact(contact2);
//		int c1_id = ContactDAO.createEmployee(contact1);
//		int c2_id = ContactDAO.createEmployee(contact2);
//		assertTrue(ContactDAO.viewEmployeesByAccountID(a_id));
//		ContactDAO.deleteEmployee(c1_id);
//		ContactDAO.deleteEmployee(c2_id);
//		AccountDAO.deleteAccount(a_id);
//	}
//
//	@Test
//	public void testAddEmployeeAccount() {
//		Account account1 = new Account("test1", "test1@gmail.com", "GMT +10");
//		Account account2 = new Account("test2", "test2@gmail.com", "GMT +10");
//		int a1_id = AccountDAO.createAccount(account1);
//		int a2_id = AccountDAO.createAccount(account2);
//		Address address = new Address("satellite town", "sargodha", "punjab", "pakistan");
//		Contact contact = new Contact("zain", "hassan", "zain@gmail.com", "male", "3229255755", true, address,
//				account1);
//		address.setContact(contact);
//		int c1_id = ContactDAO.createEmployee(contact);
//		contact.setAccount(account2);
//		assertTrue(ContactDAO.addEmployeeAccount(contact));
//		ContactDAO.deleteEmployee(c1_id);
//		AccountDAO.deleteAccount(a1_id);
//		AccountDAO.deleteAccount(a2_id);
//	}
//
//	@Test
//	public void testUpdateEmployee() {
//		Account account = new Account("test", "test@gmail.com", "GMT +10");
//		int a_id = AccountDAO.createAccount(account);
//		Address address = new Address("satellite town", "sargodha", "punjab", "pakistan");
//		Contact contact = new Contact("zain", "hassan", "zain@gmail.com", "male", "3229255755", true, address, account);
//		address.setContact(contact);
//		int c_id = ContactDAO.createEmployee(contact);
//
//		contact.setFirstName("zeeshan");
//		contact.setLastName("sohail");
//		contact.setEmailAddress("zeeshan@gmail.com");
//
//		assertTrue(ContactDAO.updateEmployee(contact));
//		ContactDAO.deleteEmployee(c_id);
//		AccountDAO.deleteAccount(a_id);
//	}
//
//	@Test
//	public void testDeleteEmployee() {
//		Account account = new Account("test", "test@gmail.com", "GMT +10");
//		int a_id = AccountDAO.createAccount(account);
//		Address address = new Address("satellite town", "sargodha", "punjab", "pakistan");
//		Contact contact = new Contact("zain", "hassan", "zain@gmail.com", "male", "3229255755", true, address, account);
//		address.setContact(contact);
//		int c_id = ContactDAO.createEmployee(contact);
//		assertTrue(ContactDAO.deleteEmployee(c_id));
//		AccountDAO.deleteAccount(a_id);
//	}
//}
