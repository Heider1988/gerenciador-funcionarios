package br.com.heider.domain.funcionario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

	List<Funcionario> findBySetor(FuncionarioSetor funcionarioSetor);
}
