package com.arenapernambuco.service;

import com.arenapernambuco.model.Evento;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventoService {

    private final List<Evento> eventos = List.of(
            new Evento("1", "Campeonato Pernambucano", LocalDateTime.of(2026, 4, 12, 16, 0), "Futebol", "AP-FUT", "Jogo oficial."),
            new Evento("2", "Show Nacional", LocalDateTime.of(2026, 5, 3, 21, 0), "Música", "AP-MSC", "Arena lotada."),
            new Evento("3", "Feira de Negócios", LocalDateTime.of(2026, 4, 28, 9, 0), "Corporativo", "AP-CORP", "Evento empresarial.")
    );

    public List<Evento> listar(String categoria) {
        return eventos.stream()
                .filter(e -> categoria == null || e.categoria().equalsIgnoreCase(categoria))
                .sorted(Comparator.comparing(Evento::dataHora))
                .collect(Collectors.toList());
    }

    public Optional<Evento> verificarPorCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) return Optional.empty();
        return eventos.stream()
                .filter(e -> e.codigoVerificacao().equalsIgnoreCase(codigo.trim()))
                .findFirst();
    }
}