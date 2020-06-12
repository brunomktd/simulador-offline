package br.com.embracon.repository;

import br.com.embracon.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

    Optional<Grupo> findByGrupo(Integer grupo);

    List<Grupo> findAllByStatus(boolean ativos);
}
