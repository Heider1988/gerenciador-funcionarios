package br.com.heider.infrastructure.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.heider.domain.funcionario.Funcionario;
import br.com.heider.domain.funcionario.FuncionarioRepository;

@Controller
public class FuncionarioController {

	@Autowired
	FuncionarioRepository funcionarioRepository;

	@GetMapping("/form")
	public String funcionariosForm(Funcionario funcionario) {

		return "addFuncionariosForm";
	}

	@PostMapping("/add")
	public String novo(@Valid Funcionario funcionario, BindingResult result) {

		if (result.hasFieldErrors()) {
			return "redirect:/form";
		}

		funcionarioRepository.save(funcionario);

		return "redirect:/home";

	}

	@GetMapping("form/{id}")
	public String updateForm(Model model, @PathVariable(name = "id") int id) {

		Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow();

		model.addAttribute("funcionario", funcionario);
		return "atualizaForm";
	}

	@PostMapping("update/{id}")
	public String alterarProduto(@Valid Funcionario funcionario, BindingResult result, @PathVariable int id) {

		if (result.hasErrors()) {
			return "redirect:/form";
		}

		funcionarioRepository.save(funcionario);
		return "redirect:/home";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable(name = "id") int id, Model model) {

		Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow();

		funcionarioRepository.delete(funcionario);
		return "redirect:/home";
	}

}
