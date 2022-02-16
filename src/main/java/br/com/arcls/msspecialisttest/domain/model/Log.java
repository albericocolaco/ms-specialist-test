package br.com.arcls.msspecialisttest.domain.model;

import br.com.arcls.msspecialisttest.domain.dto.CardFlagEnum;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "logs")
@Data
@Getter
@Setter
@NoArgsConstructor
public class Log implements Serializable {
    @Id
    private ObjectId id;

    private String client;
    private BigDecimal value;
    private LocalDateTime transactionAt;
    @CreatedDate
    private LocalDateTime createdAt;
    @Indexed
    private CardFlagEnum flag;

}
