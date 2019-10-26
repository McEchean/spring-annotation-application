package com.whf.annotation.study.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenRedPacketParam implements Serializable {
    private static final long serialVersionUID = 2640270642809340726L;
    @NotNull
    private String userId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Integer number;
    @NotNull
    @Length(min = 13, max = 13)
    private String timestamp;
}
