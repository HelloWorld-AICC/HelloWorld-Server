package com.example.helloworldmvc.web.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenListDTO {
    private List<TokenDTO> tokenList;
    private Boolean isExist;            // 회원여부 (0:비회원, 1:회원)
}
