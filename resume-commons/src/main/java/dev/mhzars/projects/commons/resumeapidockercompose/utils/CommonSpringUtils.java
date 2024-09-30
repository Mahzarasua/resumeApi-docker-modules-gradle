package dev.mhzars.projects.commons.resumeapidockercompose.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class CommonSpringUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .registerModule(new Jdk8Module())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    protected CommonSpringUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static UUID getUuid(String resumeId) {
        return UUID.fromString(resumeId);
    }

    public static UUID getRandomId() {
        return UUID.randomUUID();
    }

    public static String mapToJson(Object obj) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }

    public static <T> T mapFromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(json, clazz);
    }

    public static <T> T mapFromJsonList(String json, TypeReference<T> clazz) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(json, clazz);
    }

    public static <T> List<T> jsonArrayToList(String json, Class<T> elementClass) throws IOException {
        CollectionType listType =
                OBJECT_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
        return OBJECT_MAPPER.readValue(json, listType);
    }

    public static <T, X> List<T> readFile(String filename, Class<T> entityClass, Class<X> objectClass) {
        String json;
        List<T> entities;
        try (InputStream in = objectClass.getClassLoader().getResourceAsStream(filename)) {
            JsonNode jsonNode = OBJECT_MAPPER.readValue(in, JsonNode.class);
            json = OBJECT_MAPPER.writeValueAsString(jsonNode);
            entities = jsonNode.isArray() ?
                    jsonArrayToList(json, entityClass) :
                    Collections.singletonList(OBJECT_MAPPER.readValue(json, entityClass));
            return entities;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static <T> void removeFromList(List<T> list, Predicate<T> predicate) {
        list.removeIf(predicate);
    }

    public static List<String> getExceptionMessageChain(Throwable throwable) {
        List<String> result = new ArrayList<>();
        while (throwable != null) {
            result.add(throwable.getMessage());
            throwable = throwable.getCause();
        }
        return result;
    }

    public static <T> T generateUniqueObjectId() {
        return (T) UUID.randomUUID().toString();
    }
}
