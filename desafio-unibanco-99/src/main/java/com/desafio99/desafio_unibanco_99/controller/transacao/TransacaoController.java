package com.desafio99.desafio_unibanco_99.controller.transacao;

import com.desafio99.desafio_unibanco_99.controller.transacao.dto.request.TransacaoRequest;
import com.desafio99.desafio_unibanco_99.controller.transacao.dto.response.TransacaoResponse;
import com.desafio99.desafio_unibanco_99.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criar(@RequestBody @Valid TransacaoRequest request) {
        this.service.criar(request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deletar() {
        this.service.deletar();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<TransacaoResponse> calcularEstatistica() {
       var response = this.service.calcularEstatistica();

       return ResponseEntity.ok(response);
    }
}
