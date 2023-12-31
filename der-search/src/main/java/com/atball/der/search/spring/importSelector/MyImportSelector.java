package com.atball.der.search.spring.importSelector;

import com.atball.der.search.dao.IndexDao3;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        importingClassMetadata.getAnnotationTypes();
        return new String[]{IndexDao3.class.getName()};
    }
}
