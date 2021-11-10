package org.bcp.banking.exchange.rate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * QuotationCreate
 * <p>
 * Información de la cotización a crear
 *
 */
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO {
    private Integer id;
    private String message;
    private String extra;
}
