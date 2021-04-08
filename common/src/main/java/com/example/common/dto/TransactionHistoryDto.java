package com.example.common.dto;

import com.example.common.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionHistoryDto {
    private Long id;
    private String txId;
    private TransactionType txType;
    private Double value;
    private Long txUpdateTime;
    private int status = 3;
    private Long appUserId;
}
