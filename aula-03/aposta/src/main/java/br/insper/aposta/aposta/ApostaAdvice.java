package br.insper.aposta.aposta;

import br.insper.aposta.common.Erro;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApostaAdvice {

    @ExceptionHandler(ApostaNaoEncontradaException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Erro apostaNaoEncontradaHandler(ApostaNaoEncontradaException e) {
        Erro erro = new Erro();
        erro.setMensagem(e.getMessage());
        erro.setData(LocalDateTime.now());
        erro.setCodigo(404);
        return erro;
    }
}