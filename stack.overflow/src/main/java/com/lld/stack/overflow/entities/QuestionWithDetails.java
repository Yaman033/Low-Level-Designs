package com.lld.stack.overflow.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWithDetails {
    private Long id;
    private String heading;
    private String body;
    private Long userId;
    private String userName;
    private int answerCount;
    private int votes;
}
