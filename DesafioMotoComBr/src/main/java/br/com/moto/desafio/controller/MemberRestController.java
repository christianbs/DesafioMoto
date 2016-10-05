package br.com.moto.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.moto.desafio.data.MemberDao;
import br.com.moto.desafio.model.Member;

@RestController
@RequestMapping("/rest/members")
public class MemberRestController {

	@Autowired
	private MemberDao dao;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Member> listAllMembers() {
		return dao.findAllOrderedByName();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody Member lookupMemberById(@PathVariable("id") Long id) {
		return dao.findById(id);
	}
}
