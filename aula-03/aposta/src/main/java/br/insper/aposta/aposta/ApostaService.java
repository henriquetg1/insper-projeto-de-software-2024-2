package br.insper.aposta.aposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class ApostaService {

    @Autowired
    private ApostaRepository apostaRepository;

    public void salvar(Aposta aposta) {
        aposta.setId(UUID.randomUUID().toString());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RetornarPartidaDTO> partida = restTemplate.getForEntity(
                "http://localhost:8080/partida/" + aposta.getIdPartida(),
                RetornarPartidaDTO.class);

        if (partida.getStatusCode().is2xxSuccessful())  {
            apostaRepository.save(aposta);
        }

    }

    public List<Aposta> listarNormalOuPorStatus(String status) {
        if (status != null) {
            return apostaRepository.findByStatus(status);
        } else {
            return apostaRepository.findAll();
        }
    }

    public String buscarSeAcertou(String id) {

        Aposta aposta = apostaRepository.findById(id).get();

        if (aposta.getStatus().equals("REALIZADA")) {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<RetornarPartidaDTO> partida = restTemplate.getForEntity(
                    "http://localhost:8080/partida/" + aposta.getIdPartida(),
                    RetornarPartidaDTO.class);

            if (partida.getStatusCode().is2xxSuccessful()) {
                if (!partida.getBody().getResultado().equals("NAO_REALIZADA")) {
                    if (partida.getBody().getResultado().equals(aposta.getResultado())) {
                        aposta.setStatus("GANHOU");
                        apostaRepository.save(aposta);
                        return "GANHOU";
                    } else {
                        aposta.setStatus("PERDIDA");
                        apostaRepository.save(aposta);
                        return "PERDIDA";
                    }
                } else {
                    return "NAO_REALIZADA";
                }
            } else {
                return "Partida não encontrada";
            }

        } else {
            return "Aposta não realizada";
        }
    }

}
