package dev.mhzars.projects.commons.resumeapidockercompose.mapper;

import dev.mhzars.projects.commons.resumeapidockercompose.config.MyUserDetails;
import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonAuthUser;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class CommonCustomMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));
        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDateTime.class));
        factory.getConverterFactory().registerConverter(new BidirectionalStringAndUUIDConverter());

        factory.classMap(CommonAuthUser.class, MyUserDetails.class)
                .byDefault().mapNulls(false)
                .register();
    }
}
