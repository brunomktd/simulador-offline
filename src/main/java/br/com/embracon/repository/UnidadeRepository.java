package br.com.embracon.repository;

import br.com.embracon.model.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {

    Optional<Unidade> findByCodigoUnidade(Integer codigoUnidade);
}
