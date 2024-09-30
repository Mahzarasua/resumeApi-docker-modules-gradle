package dev.mhzars.projects.postgres.resumeapidockercompose.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static dev.mhzars.projects.commons.resumeapidockercompose.utils.CommonSpringUtils.OBJECT_MAPPER;


@Aspect
@Component
@Slf4j
public class LoggingAdvice {
    @Pointcut(value = "execution(* dev.mhzars.projects.postgres.resumeapidockercompose.*.*.*(..) ) && !execution(* dev.mhzars.projects.postgres.resumeapidockercompose.config.*.*(..) ) " +
            " && !execution(* dev.mhzars.projects.commons.resumeapidockercompose.config.*.*(..) ) && !execution(* dev.mhzars.projects.postgres.resumeapidockercompose.controller.CustomExceptionHandler.*(..) )"
    )
    public void myPointcut() {

    }

    @Around("myPointcut()")
    public Object appLogger(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString().replace("class", "");
        Object[] array = pjp.getArgs();

        log.info("Entering {}.{}(): Args:{}", className, methodName, OBJECT_MAPPER.writeValueAsString(array));
        Object object = pjp.proceed();

        log.info("Output {}.{}(): Response:{}", className, methodName, OBJECT_MAPPER.writeValueAsString(object));
        return object;
    }
}
