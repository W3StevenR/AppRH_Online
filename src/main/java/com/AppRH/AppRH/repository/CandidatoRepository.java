package com.AppRH.AppRH.repository;

import com.AppRH.AppRH.entity.Candidato;
import com.AppRH.AppRH.entity.Funcionario;
import com.AppRH.AppRH.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface CandidatoRepository extends CrudRepository<Candidato, Long> {


    Iterable<Candidato> findByVaga(Vaga vaga);
    Candidato findByRg(String rg);
    Candidato findById(long id);

    @Query(value = " select u from Candidato u where u.nomeCandidato like %?1%")
    List<Candidato> findByNomeCandidatos(String nomeCandidato);

}



