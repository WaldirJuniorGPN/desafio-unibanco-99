package com.desafio99.desafio_unibanco_99.service;

import com.desafio99.desafio_unibanco_99.controller.transacao.dto.request.TransacaoRequest;
import com.desafio99.desafio_unibanco_99.controller.transacao.dto.response.TransacaoResponse;

public interface transacaoService {

    void criar(TransacaoRequest request);
    void deletar();

    TransacaoResponse calcularEstatistica();
}
