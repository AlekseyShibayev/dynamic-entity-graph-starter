package com.company.app.example.component;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatEntityGraphExtractorNode {

    private String nodeName;
    private ChatEntityGraphExtractorNode child;

}
