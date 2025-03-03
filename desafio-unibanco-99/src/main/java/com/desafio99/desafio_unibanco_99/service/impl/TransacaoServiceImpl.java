package com.desafio99.desafio_unibanco_99.service.impl;

import com.desafio99.desafio_unibanco_99.controller.transacao.dto.request.TransacaoRequest;
import com.desafio99.desafio_unibanco_99.controller.transacao.dto.response.TransacaoResponse;
import com.desafio99.desafio_unibanco_99.entity.Transacao;
import com.desafio99.desafio_unibanco_99.service.TransacaoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    private final ArrayList<Transacao> transacoes = new ArrayList<>();

    @Override
    public void criar(TransacaoRequest request) {
        var transacao = new Transacao(request.valor(), request.dataHora());
        transacoes.add(transacao);
    }

    @Override
    public void deletar() {
        transacoes.clear();
    }

    @Override
    public TransacaoResponse calcularEstatistica() {
        var transacoesRecentes = buscarTransacoesRecentes(transacoes);

        var count = contarTransacoes(transacoesRecentes);
        var sum = somarValorTransacionado(transacoesRecentes);
        var avg = mediaValorTransacionado(count, sum);
        var min = menorValorTransacionado(transacoesRecentes);
        var max = maiorValorTransacionado(transacoesRecentes);

        return new TransacaoResponse(count, sum, avg, min, max);
    }

    private List<Transacao> buscarTransacoesRecentes(List<Transacao> transacoes) {
        return transacoes.stream()
                .filter(t -> t.getDataHora().isAfter(OffsetDateTime.now().minusSeconds(60)))
                .collect(Collectors.toList());
    }

    private BigDecimal somarValorTransacionado(List<Transacao> transacoes) {
        if (transacoes == null || transacoes.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return transacoes.stream()
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal mediaValorTransacionado(int quantidadeTransacoes, BigDecimal valorTotal) {
        return quantidadeTransacoes > 0
                ? valorTotal.divide(new BigDecimal(quantidadeTransacoes), 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
    }

    private BigDecimal menorValorTransacionado(List<Transacao> transacoes) {
        return transacoes.stream()
                .map(Transacao::getValor)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal maiorValorTransacionado(List<Transacao> transacoes) {
        return transacoes.stream()
                .map(Transacao::getValor)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }

    private int contarTransacoes(List<Transacao> transacoes) {
        return transacoes.size();
    }
}
