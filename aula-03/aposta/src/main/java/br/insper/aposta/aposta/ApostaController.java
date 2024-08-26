package br.insper.aposta.aposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aposta")
public class ApostaController {

    @Autowired
    private ApostaService apostaService;

    @GetMapping
    public List<Aposta> listar() {
        return apostaService.listar();
    }

    @GetMapping("/{id}")
    public String buscarSeAcertou(@PathVariable String id) {
        return apostaService.buscarSeAcertou(id);
    }

    @GetMapping("status/{status}")
    public List<Aposta> listarPorStatus(@PathVariable String status) {
        return apostaService.listarPorStatus(status);
    }

    @PostMapping
    public void salvar(@RequestBody Aposta aposta) {
        apostaService.salvar(aposta);
    }
}
