package br.com.embracon.repository;

import br.com.embracon.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Optional<Produto> findByBem(String bem);
}
