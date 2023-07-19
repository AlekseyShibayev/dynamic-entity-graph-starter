package com.company.app.entitygraphextractor.example.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntityGraphExtractorNode {

    private String name;
    private EntityGraphExtractorNode child;

}
