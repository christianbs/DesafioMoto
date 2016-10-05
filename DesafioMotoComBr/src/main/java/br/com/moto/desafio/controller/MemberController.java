package br.com.moto.desafio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.moto.desafio.data.MemberDao;
import br.com.moto.desafio.model.Member;
import br.com.moto.desafio.validator.MemberValidator;

@Controller
@Transactional
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired
	private MemberDao memberDao;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new MemberValidator());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String displaySortedMembers(Model model) {
		List<Member> members = memberDao.findAllOrderedByName();
		model.addAttribute("members", members);
		model.addAttribute("newMember", new Member());
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerNewMember(@Valid @ModelAttribute("newMember") Member newMember, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "index";
		}
		memberDao.register(newMember);
		return displaySortedMembers(model);
	}

}
