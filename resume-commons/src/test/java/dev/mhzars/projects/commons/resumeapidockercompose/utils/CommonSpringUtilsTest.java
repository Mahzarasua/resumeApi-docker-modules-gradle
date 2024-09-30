package dev.mhzars.projects.commons.resumeapidockercompose.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommonSpringUtilsTest {
    @Test
    public void testGetUuid_ValidUUIDString() {
        String validUUIDString = "550e8400-e29b-41d4-a716-446655440000";
        UUID uuid = CommonSpringUtils.getUuid(validUUIDString);
        assertEquals(UUID.fromString(validUUIDString), uuid);
    }

    @Test
    public void testGetRandomId() {
        UUID uuid = CommonSpringUtils.getRandomId();
        assertNotNull(uuid);
    }

    @Test
    public void testMapToJson() throws JsonProcessingException {
        TestObject obj = new TestObject("test", 123);
        String json = CommonSpringUtils.mapToJson(obj);
        assertNotNull(json);
    }

    @Test
    public void testMapFromJson() throws JsonProcessingException {
        String json = "{\"name\":\"test\",\"value\":123}";
        TestObject obj = CommonSpringUtils.mapFromJson(json, TestObject.class);
        assertNotNull(obj);
        assertEquals("test", obj.getName());
        assertEquals(123, obj.getValue());
    }

    @Test
    public void testMapFromJsonList() throws JsonProcessingException {
        String json = "[{\"name\":\"test1\",\"value\":123},{\"name\":\"test2\",\"value\":456}]";
        List<TestObject> list = CommonSpringUtils.mapFromJsonList(json, new TypeReference<>() {
        });
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    public void testRemoveFromList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Predicate<Integer> predicate = num -> num % 2 == 0; // Remove even numbers
        CommonSpringUtils.removeFromList(list, predicate);
        assertEquals(3, list.size());
        assertFalse(list.contains(2));
        assertFalse(list.contains(4));
    }

    @Test
    public void testGetExceptionMessageChain() {
        Throwable throwable = new RuntimeException("Root cause", new IllegalArgumentException("Nested cause"));
        List<String> messageChain = CommonSpringUtils.getExceptionMessageChain(throwable);
        assertEquals(2, messageChain.size());
        assertEquals("Root cause", messageChain.get(0));
        assertEquals("Nested cause", messageChain.get(1));
    }

    @Test
    public void testGenerateUniqueObjectId() {
        String uniqueId = CommonSpringUtils.generateUniqueObjectId();
        assertNotNull(uniqueId);
        assertEquals(36, uniqueId.length()); // UUID length
    }

    // Dummy class for testing JSON serialization/deserialization
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class TestObject {
        private String name;
        private int value;

    }
}