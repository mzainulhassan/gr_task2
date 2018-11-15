package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dao.AccountDAO;
import entity.Account;

public class AccountTest {
	@Test
	public void testCreateAccount() {
		Account account = new Account("test", "test@gmail.com", "GMT +10");
		int id = AccountDAO.createAccount(account);
		assertNotNull(id);
		AccountDAO.deleteAccount(id);
	}

	@Test
	public void testViewAllAccounts() {
		Account account1 = new Account("test1", "test1@gmail.com", "GMT +10");
		Account account2 = new Account("test2", "test2@gmail.com", "GMT +10");
		int id1 = AccountDAO.createAccount(account1);
		int id2 = AccountDAO.createAccount(account2);
		assertTrue(AccountDAO.viewAllAccounts());
		AccountDAO.deleteAccount(id1);
		AccountDAO.deleteAccount(id2);
	}

	@Test
	public void testUpdateAccount() {
		Account account = new Account("test", "test@gmail.com", "GMT +10");
		int id = AccountDAO.createAccount(account);
		account.setName("test2");
		account.setEmail("test2@gmail.com");
		assertTrue(AccountDAO.updateAccount(account));
		AccountDAO.deleteAccount(id);
	}

	@Test
	public void testDeleteAccount() {
		Account account = new Account("test", "test@gmail.com", "GMT +10");
		int id = AccountDAO.createAccount(account);
		assertTrue(AccountDAO.deleteAccount(id));
	}
}
