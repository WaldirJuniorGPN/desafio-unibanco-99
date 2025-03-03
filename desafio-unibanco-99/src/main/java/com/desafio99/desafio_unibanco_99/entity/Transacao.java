package com.desafio99.desafio_unibanco_99.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;

@Getter
@NoArgsConstructor(force = true)
public class Transacao {

    private final BigDecimal valor;
    private final OffsetDateTime dataHora;

    public Transacao(BigDecimal valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }
}
