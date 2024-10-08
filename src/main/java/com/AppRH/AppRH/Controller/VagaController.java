package com.AppRH.AppRH.Controller;

import com.AppRH.AppRH.entity.Vaga;
import com.AppRH.AppRH.repository.CandidatoRepository;
import com.AppRH.AppRH.repository.VagaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import com.AppRH.AppRH.entity.Candidato;
import com.AppRH.AppRH.entity.Candidato;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;


@Controller
public class VagaController {
    @Autowired
    private VagaRepository vr;
    @Autowired
    private CandidatoRepository cr;

    //CADASTRA VAGA
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.GET)
    public String form(){


        return "vaga/formVaga";
    }
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.POST)
    public String form(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem ","Verifique os campos");
            return "redirect:/cadastrarVaga";
        }
        vr.save(vaga);
        attributes.addFlashAttribute("mensagem","Vaga cadastrada com Sucesso.");
        return  "redirect:/cadastrarVaga";

    }
    //LISTA VAGAS
    @RequestMapping("/vagas")
    public ModelAndView listaVagas() {
        ModelAndView mv = new ModelAndView("vaga/listaVaga");
        Iterable<Vaga> vagas = vr.findAll();
        mv.addObject("vagas", vagas);
        return mv;
    }

    //
    @RequestMapping(value = "/vaga/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesVaga(@PathVariable("codigo") long codigo) {
        Vaga vaga = vr.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("vaga/detalhesVaga");
        mv.addObject("vaga", vaga);

        Iterable<Candidato> canditados = cr.findByVaga(vaga);
        mv.addObject("candidatos", canditados);

        return mv;

    }

    //DETELA A VAGA
    @RequestMapping("/deletarVaga")
    public String deletarVaga(long codigo) {
        Vaga vaga = vr.findByCodigo(codigo);
        vr.delete(vaga);
        return "redirect:/vagas";
    }

    @RequestMapping(value = "/vaga/{codigo}", method = RequestMethod.POST)
    public String detalhesVagaPost(@PathVariable("codigo")long codigo, @Valid Candidato candidato, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem","Verifica os Campos");
            return "redirect:/vaga/{codigo}";
        }
        //TESTE DE CONSISTENCIA
        if(cr.findByRg(candidato.getRg())!= null){
            attributes.addFlashAttribute("mensagem_erro", "Rg duplicado");
            return "redirect:/vaga/{codigo}";
        }
        Vaga vaga = vr.findByCodigo(codigo);
        candidato.setVaga(vaga);
        cr.save(candidato);
        attributes.addFlashAttribute("mensagem", "Candidato Adicionado com sucesso");
        return "redirect:/vaga/{codigo}";
    }

    //DELETA CANDIDATO
    @RequestMapping("/deletarCandidato")
    public String deletarCandidato(String Rg){
        Candidato candidato = cr.findByRg(Rg);
        Vaga vaga = candidato.getVaga();
        String codigo = "" + vaga.getCodigo();
        cr.delete(candidato);

        return"redirect:/" +codigo;
    }
    //Metodos que atualiza vaga
    //Formulario edicao de vaga
    @RequestMapping(value = "/editar-vaga", method = RequestMethod.GET)
    public ModelAndView editarVaga(long codigo){
        Vaga vaga = vr.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("vaga/update-vaga");
        mv.addObject("vaga", vaga);
        return mv;
    }
    //UPDATE VAGA
    @RequestMapping(value = "/editar_vaga", method = RequestMethod.POST)
    public String updateVaga(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes){
       vr.save(vaga);
       attributes.addFlashAttribute("mensagem", "Vaga alterada com sucesso");
       long codigoLong = vaga.getCodigo();
       String codigo = "" +codigoLong;
       return "redirect:/" + codigo;
    }

    //ADICIONAR CANDIDATO

}
