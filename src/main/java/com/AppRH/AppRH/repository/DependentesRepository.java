package com.AppRH.AppRH.repository;

import com.AppRH.AppRH.entity.Dependentes;
import com.AppRH.AppRH.entity.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DependentesRepository extends CrudRepository<Dependentes, String> {
    Iterable<Dependentes> findByFuncionario(Funcionario funcionario);
    //PENSANDO NO METODO DELETE
    Dependentes findByCpf(String cpf);
    Dependentes findById(long id);

    // criado para implementar

    @Query(value = " select u from Dependentes u where u.nome like %?1%")
    List<Dependentes> findByNomesDependetes(String nome);



}
