package com.arenapernambuco.web;

import com.arenapernambuco.model.Evento;
import com.arenapernambuco.service.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class SiteController {

    private final EventoService eventoService;

    public SiteController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/eventos")
    public String eventos(@RequestParam(name = "categoria", required = false) String categoria, Model model) {
        List<Evento> lista = eventoService.listar(categoria);
        model.addAttribute("eventos", lista);
        model.addAttribute("categoriaAtiva", categoria); // Mantém o rastro do filtro selecionado
        return "eventos";
    }

    @GetMapping("/verificar")
    public String verificarForm() {
        return "verificar";
    }

    @PostMapping("/verificar")
    public String verificar(
            @RequestParam(name = "codigo", required = false) String codigo,
            Model model) {
        Optional<Evento> encontrado = eventoService.verificarPorCodigo(codigo);
        if (encontrado.isPresent()) {
            model.addAttribute("ok", true);
            model.addAttribute("evento", encontrado.get());
        } else {
            model.addAttribute("ok", false);
            model.addAttribute("codigoInformado", codigo != null ? codigo.trim() : "");
        }
        return "verificar";
    }
}
