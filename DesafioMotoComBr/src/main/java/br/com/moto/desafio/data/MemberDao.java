package br.com.moto.desafio.data;

import java.util.List;

import br.com.moto.desafio.model.Member;

public interface MemberDao {
    public Member findById(Long id);

    public Member findByEmail(String email);

    public List<Member> findAllOrderedByName();

    public void register(Member member);
}
