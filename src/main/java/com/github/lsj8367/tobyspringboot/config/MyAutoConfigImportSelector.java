package com.github.lsj8367.tobyspringboot.config;

import java.util.stream.StreamSupport;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(final ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    // 패키지 path + ClassName을 통해 설정 정보들을 가져올 수 있게됨.
    // @Import 어노테이션에서 해당 selector만 넣어주게 되면 이 설정을 토대로 설정 정보들을 가져오게 된다.
    @Override
    public String[] selectImports(final AnnotationMetadata importingClassMetadata) {
//        List<String> autoConfigs = new ArrayList<>();
//        for (String s : ImportCandidates.load(MyAutoConfiguration.class, classLoader)) {
//            autoConfigs.add(s);
//        }
//        return autoConfigs.toArray(new String[0]);
        final Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);
    }

}
