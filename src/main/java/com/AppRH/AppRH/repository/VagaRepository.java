package com.AppRH.AppRH.repository;

import com.AppRH.AppRH.entity.Funcionario;
import com.AppRH.AppRH.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VagaRepository extends CrudRepository<Vaga, Long> {
    Vaga findByCodigo(long codigo);
    @Query(value = " select u from Vaga u where u.nome like %?1%")
    List<Vaga> findByNomesVagas(String nome);
}
