package dev.mhzars.projects.commons.resumeapidockercompose;

import dev.mhzars.projects.commons.resumeapidockercompose.podam.EmailStrategy;
import dev.mhzars.projects.commons.resumeapidockercompose.podam.LocalDateFutureStrategy;
import dev.mhzars.projects.commons.resumeapidockercompose.podam.LocalDatePastStrategy;
import dev.mhzars.projects.commons.resumeapidockercompose.podam.MyDataProviderStrategy;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Past;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategy;


public class CommonTestUtils {
    public static final String RESUME_ID = "resumeId";

    public static <T> T manufacturedPojo(Class<T> clazz) {
        PodamFactory factory = new PodamFactoryImpl(new MyDataProviderStrategy());
        return factory.manufacturePojo(clazz);
    }

    public static <T> T manufacturedCustomPojo(Class<T> clazz) {
        PodamFactory factory = new PodamFactoryImpl(new MyDataProviderStrategy());
        EmailStrategy emailStrategy = new EmailStrategy();
        LocalDatePastStrategy pastStrategy = new LocalDatePastStrategy();
        LocalDateFutureStrategy futureStrategy = new LocalDateFutureStrategy();
        ((RandomDataProviderStrategy) factory.getStrategy()).addOrReplaceAttributeStrategy(Email.class, emailStrategy);
        ((RandomDataProviderStrategy) factory.getStrategy()).addOrReplaceAttributeStrategy(Past.class, pastStrategy);
        ((RandomDataProviderStrategy) factory.getStrategy()).addOrReplaceAttributeStrategy(FutureOrPresent.class, futureStrategy);
        ((RandomDataProviderStrategy) factory.getStrategy()).addOrReplaceAttributeStrategy(javax.validation.constraints.Past.class, pastStrategy);
        ((RandomDataProviderStrategy) factory.getStrategy()).addOrReplaceAttributeStrategy(javax.validation.constraints.FutureOrPresent.class, futureStrategy);

        return factory.manufacturePojo(clazz);
    }


}
