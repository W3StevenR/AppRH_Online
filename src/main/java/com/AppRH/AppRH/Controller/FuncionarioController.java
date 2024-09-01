package com.AppRH.AppRH.Controller;

import com.AppRH.AppRH.entity.Dependentes;
import com.AppRH.AppRH.entity.Funcionario;
import com.AppRH.AppRH.repository.DependentesRepository;
import com.AppRH.AppRH.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.directory.Attribute;

@Controller
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository fr;

    @Autowired
    private DependentesRepository dr;
    @RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.GET)
    public String form() {
        return "vaga/funcionario/formFuncionario";
    }

    @RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.POST)
    public String form(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os Campos");
            return "redirect:/cadastrarFuncionario";
        }
        fr.save(funcionario);
        attributes.addFlashAttribute("mensagem", "Funcionario cadastrado com Sucesso");
        return "redirect:/cadastrarFuncionario";
    }

    // listar funcionário
    @RequestMapping("/funcionarios")
    public ModelAndView listaFuncionarios() {
        ModelAndView mv = new ModelAndView("vaga/funcionario/listaFuncionario");
        Iterable<Funcionario> funcionarios = fr.findAll();
        mv.addObject("funcionarios", funcionarios);
        return mv;
    }

    @RequestMapping(value = "/dependentes/{id}", method = RequestMethod.GET)
    public ModelAndView dependentes(@PathVariable("id") long id) {
        Funcionario funcioario = fr.findById(id);
        ModelAndView mv = new ModelAndView("vaga/funcionario/dependentes");
        mv.addObject("funcionario", funcioario);

        Iterable<Dependentes> dependentes = dr.findByFuncionario(funcioario);
        mv.addObject("dependentes", dependentes);
        return mv;

    }

    @RequestMapping(value="/dependentes/{id}", method = RequestMethod.POST)
    public String dependentesPost(@PathVariable("id") long id, Dependentes dependentes, BindingResult result,
                                  RedirectAttributes attributes) {

        if(result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/dependentes/{id}";
        }

        if(dr.findByCpf(dependentes.getCpf()) != null) {
            attributes.addFlashAttribute("mensagem_erro", "CPF duplicado");
            return "redirect:/dependentes/{id}";
        }

        Funcionario funcionario = fr.findById(id);
        dependentes.setFuncionario(funcionario);
        dr.save(dependentes);
        attributes.addFlashAttribute("mensagem", "Dependente adicionado com sucesso");
        return "redirect:/dependentes/{id}";

    }

    @RequestMapping("/deletarFuncionario")
    public String deletarFuncionario(long id){
        Funcionario funcionario = fr.findById(id);
        fr.delete(funcionario);
        return "redirect:/funcionarios";
    }

    @RequestMapping(value = "/editar-funcionario", method = RequestMethod.GET)
    public ModelAndView editarFuncionario(long id){
        Funcionario funcionario = fr.findById(id);
        ModelAndView mv = new ModelAndView("vaga/funcionario/update-funcionario");
        mv.addObject("funcionario",funcionario);
        return mv;
    }
    @RequestMapping(value = "/editar-funcionario", method = RequestMethod.POST)
    public String updateFuncionario(@Valid Funcionario funcionario,BindingResult result, RedirectAttributes attributes){
        fr.save(funcionario);
        attributes.addFlashAttribute("mensagem","Funcionario Adicionado com Sucesso");

        long idLong = funcionario.getId();
        String id = "" + idLong;
        return "redirect:/dependentes/" + id;
    }


    @RequestMapping("/deletarDependente")
    public String deletarDependente(String cpf){
        Dependentes dependente = dr.findByCpf(cpf);
        Funcionario funcionario = dependente.getFuncionario();
        String codigo = "" + funcionario.getId();

        dr.delete(dependente);
        return  "redirect:/dependentes/" + codigo;
    }



}
