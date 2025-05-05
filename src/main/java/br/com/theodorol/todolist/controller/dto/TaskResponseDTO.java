package br.com.theodorol.todolist.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponseDTO(
        UUID id, String title,
        String description, LocalDateTime startAt,
        LocalDateTime endAt,
        String priority,
        UUID idUsers) {

}
