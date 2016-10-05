package br.com.moto.desafio.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.moto.desafio.data.MemberDao;
import br.com.moto.desafio.model.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml", "classpath:/META-INF/spring/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MemberDaoTest {
	@Autowired
	private MemberDao memberDao;

	@Test
	public void testFindById() {
		Member chris = create("Chris", "chris@hotmail.com", "11999999999");
		memberDao.register(chris);
		Member x = memberDao.findById(chris.getId());
		Assert.assertEquals(chris.getId(), x.getId());
	}

	@Test
	public void testFindByEmail() {
		Member chris = create("Chris", "chris@hotmail.com", "11999999999");
		memberDao.register(chris);
		Member x = memberDao.findByEmail(chris.getEmail());
		Assert.assertEquals(chris.getId(), x.getId());
	}

	@Test
	public void testRegister() {
		Member chris = create("Chris", "chris@hotmail.com", "11999999999");
		memberDao.register(chris);
		List<Member> members = memberDao.findAllOrderedByName();
		Assert.assertTrue(members.contains(chris));
	}

	@Test
	public void testFindAllOrderedByName() {
		Member ana = create("Ana", "ana@hotmail.com", "11999999999");
		memberDao.register(ana);
		Member bia = create("Bia", "bia@hotmail.com", "11999999999");
		memberDao.register(bia);
		Member chris = create("Chris", "chris@hotmail.com", "11999999999");
		memberDao.register(chris);
		List<Member> members = memberDao.findAllOrderedByName();
		boolean a = members.get(0).getName().charAt(0) == 'A';
		boolean b = members.get(1).getName().charAt(0) == 'B';
		boolean c = members.get(2).getName().charAt(0) == 'C';
		Assert.assertTrue(a && b && c);
	}

	private Member create(String nome, String email, String telefone) {
		Member member = new Member();
		member.setName(nome);
		member.setEmail(email);
		member.setPhoneNumber(telefone);
		return member;
	}

}
