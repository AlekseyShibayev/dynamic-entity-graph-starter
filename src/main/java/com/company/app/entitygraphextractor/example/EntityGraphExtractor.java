package com.company.app.entitygraphextractor.example;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.example.common.EntityGraphExtractorFinisher;
import com.company.app.entitygraphextractor.example.context.FirstContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class EntityGraphExtractor {

    @Autowired
    private EntityGraphExtractorFinisher entityGraphExtractorFinisher;

    public FirstContext createContext(First first) {
        return FirstContext.of(Collections.singletonList(first), entityGraphExtractorFinisher);
    }

    public FirstContext createContext(List<First> firsts) {
        return FirstContext.of(firsts, entityGraphExtractorFinisher);
    }

}
