package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import uk.co.jemos.podam.api.AbstractRandomDataProviderStrategy;

public class MyDataProviderStrategy extends AbstractRandomDataProviderStrategy {
    @Override
    public int getMaxDepth(Class<?> type) {
        return 1;
    }
}
