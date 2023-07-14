package com.company.app.entitygraphextractor.example.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Node {

    private String name;
    private Node child;

}
