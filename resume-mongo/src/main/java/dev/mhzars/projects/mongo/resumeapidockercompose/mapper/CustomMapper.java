package dev.mhzars.projects.mongo.resumeapidockercompose.mapper;

import dev.mhzars.projects.commons.resumeapidockercompose.config.MyUserDetails;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.auth.UserResponse;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.education.EducationDomain;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.experience.ExperienceDomain;
import dev.mhzars.projects.commons.resumeapidockercompose.domain.skill.SkillDomain;
import dev.mhzars.projects.commons.resumeapidockercompose.mapper.BidirectionalStringAndUUIDConverter;
import dev.mhzars.projects.commons.resumeapidockercompose.mapper.CommonCustomMapper;
import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonAuthRole;
import dev.mhzars.projects.commons.resumeapidockercompose.model.CommonAuthUser;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.AuthRole;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.AuthUser;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Education;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Experience;
import dev.mhzars.projects.mongo.resumeapidockercompose.model.Skill;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Primary
public class CustomMapper extends CommonCustomMapper {
    public static final String AUTH_ROLE = "role";
    public static final String DOMAIN_ROLE_NAME = "roleName";

    @Override
    protected void configure(MapperFactory factory) {
        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));
        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDateTime.class));
        factory.getConverterFactory().registerConverter(new BidirectionalStringAndUUIDConverter());

        factory.classMap(Experience.class, ExperienceDomain.class)
//                .field(PARENT_RESUME_ID, DOMAIN_RESUME_ID)
                .byDefault().mapNulls(false)
                .register();

        factory.classMap(Education.class, EducationDomain.class)
//                .field(PARENT_RESUME_ID, DOMAIN_RESUME_ID)
                .byDefault().mapNulls(false)
                .register();

        factory.classMap(Skill.class, SkillDomain.class)
//                .field(PARENT_RESUME_ID, DOMAIN_RESUME_ID)
                .byDefault().mapNulls(false)
                .register();

        factory.classMap(AuthUser.class, UserResponse.class)
                .byDefault().mapNulls(false)
                .register();

        factory.classMap(AuthRole.class, UserResponse.Role.class)
                .field(AUTH_ROLE, DOMAIN_ROLE_NAME)
                .byDefault().mapNulls(false)
                .register();

        factory.classMap(AuthUser.class, CommonAuthUser.class)
                .byDefault().mapNulls(false)
                .register();

        factory.classMap(AuthRole.class, CommonAuthRole.class)
                .byDefault().mapNulls(false)
                .register();

        factory.classMap(CommonAuthUser.class, MyUserDetails.class)
                .byDefault().mapNulls(false)
                .register();
    }
}
