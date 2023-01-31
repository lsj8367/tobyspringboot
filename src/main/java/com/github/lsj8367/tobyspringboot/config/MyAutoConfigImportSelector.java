package com.github.lsj8367.tobyspringboot.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    // 패키지 path + ClassName을 통해 설정 정보들을 가져올 수 있게됨.
    // @Import 어노테이션에서 해당 selector만 넣어주게 되면 이 설정을 토대로 설정 정보들을 가져오게 된다.
    @Override
    public String[] selectImports(final AnnotationMetadata importingClassMetadata) {
        return new String[]{
            "com.github.lsj8367.tobyspringboot.config.autoconfig.DispatcherServletConfig",
            "com.github.lsj8367.tobyspringboot.config.autoconfig.TomcatWebServerConfig"
        };
    }

}
