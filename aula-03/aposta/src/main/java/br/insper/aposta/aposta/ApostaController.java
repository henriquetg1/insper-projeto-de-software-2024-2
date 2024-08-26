package br.insper.aposta.aposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aposta")
public class ApostaController {

    @Autowired
    private ApostaService apostaService;

    // listra com possibilidade de filtrar por status
    @GetMapping
    public List<Aposta> listar(@RequestParam(required = false) String status) {
        return apostaService.listarNormalOuPorStatus(status);
    }

    @GetMapping("/{id}")
    public String buscarSeAcertou(@PathVariable String id) {
        return apostaService.buscarSeAcertou(id);
    }

//    @GetMapping("status/{status}")
//    public List<Aposta> listarPorStatus(@PathVariable String status) {
//        return apostaService.listarPorStatus(status);
//    }

    @PostMapping
    public void salvar(@RequestBody Aposta aposta) {
        apostaService.salvar(aposta);
    }
}
