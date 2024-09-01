package com.AppRH.AppRH.repository;
import com.AppRH.AppRH.entity.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, String>{
    //Busca
    Funcionario findById(long Id);
    Funcionario findByNome(String nome);

    //para busca

    @Query(value = " select u from Funcionario u where u.nome like %?1%")
    List<Funcionario> findByNomesFuncionarios(String nome);


}
