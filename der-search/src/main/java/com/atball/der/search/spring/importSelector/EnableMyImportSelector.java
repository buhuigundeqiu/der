package com.atball.der.search.spring.importSelector;

import org.springframework.context.annotation.Import;

@Import(MyImportSelector.class)
public @interface EnableMyImportSelector {
}
