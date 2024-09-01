package com.AppRH.AppRH.Controller;
import com.AppRH.AppRH.entity.Funcionario;
import com.AppRH.AppRH.entity.Dependentes;
import com.AppRH.AppRH.entity.Candidato;
import com.AppRH.AppRH.entity.Vaga;


import com.AppRH.AppRH.repository.DependentesRepository;
import com.AppRH.AppRH.repository.FuncionarioRepository;
import com.AppRH.AppRH.repository.CandidatoRepository;
import com.AppRH.AppRH.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.jpa.repository.Query;
@Controller
public class BuscaController {
    @Autowired
    private FuncionarioRepository fr;
    @Autowired
    private VagaRepository vr;
    @Autowired
    private DependentesRepository dr;
    @Autowired
    private CandidatoRepository cr;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView abrirIndex( ){
        ModelAndView mv = new ModelAndView("index");
        return mv;

    }
    @RequestMapping(value = "/", method = RequestMethod.POST)

    public ModelAndView buscarIndex(@RequestParam("buscar") String buscar, @RequestParam("nome") String nome){
        ModelAndView mv = new ModelAndView("index");
        String mensagem = "Resultado da busca por" + buscar;

        if(nome.equals("nomefuncionario")){
            mv.addObject("funcionarios", fr.findByNomesFuncionarios(buscar));

        }else if(nome.equals("nomedependente")){
            mv.addObject("dependentes", dr.findByNomesDependetes(buscar));

        }else if(nome.equals("nomecandidato")){
            mv.addObject("candidatos", cr.findByNomeCandidatos(buscar));

        }else if(nome.equals("titulovaga")){
            mv.addObject("vagas", vr.findByNomesVagas(buscar));

        }else{
            mv.addObject("funcionarios", fr.findByNomesFuncionarios(buscar));
            mv.addObject("dependentes", dr.findByNomesDependetes(buscar));
            mv.addObject("candidatos", cr.findByNomeCandidatos(buscar));
            mv.addObject("vagas", vr.findByNomesVagas(buscar));
        }
        mv.addObject("mensagem",mensagem);
        return mv;

    }



}
