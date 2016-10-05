package br.com.moto.desafio.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.moto.desafio.model.Member;

@Repository
@Transactional
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private EntityManager em;

	public Member findById(Long id) {
		return em.find(Member.class, id);
	}

	public Member findByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
		Root<Member> member = criteria.from(Member.class);
		Predicate predicate = cb.equal(member.get("email"), email);
		criteria.select(member);
		criteria.where(predicate);
		return em.createQuery(criteria).getSingleResult();
	}

	public List<Member> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
		Root<Member> member = criteria.from(Member.class);
		criteria.select(member);
		criteria.orderBy(cb.asc(member.<String>get("name")));
		return em.createQuery(criteria).getResultList();
	}

	public void register(Member member) {
		em.persist(member);
	}

}
