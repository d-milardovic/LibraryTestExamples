package com.appLabIT.onlineLibrary.helper;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import java.util.*;

public class JsonMapper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static JsonNode toNode(String json) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(json, JsonNode.class);
    }

    public static Response.User toUser(String json) throws JsonProcessingException {
        var node = toNode(json);
        return new Response.User(
                node.get("id").asInt(),
                node.get("name").asText(),
                node.get("lastName").asText(),
                node.get("email").asText(),
                node.get("borrowBookCounter").asInt(),
                node.get("counterNewBookType").asInt()
        );
    }

    public static Response.Book toBook(String json) throws JsonProcessingException {
        var node = toNode(json);
        return new Response.Book(
                node.get("id").asInt(),
                node.get("name").asText(),
                node.get("author").asText(),
                node.get("bookType").asText(),
                node.get("numberOfBooks").asInt()
        );
    }

    public static String toJson(Request.User user) {
        var node = OBJECT_MAPPER.createObjectNode();
        node.put("name", user.name);
        node.put("lastName", user.lastName);
        node.put("email", user.email);
        node.put("borrowBookCounter", 0);
        node.put("bookType", "Classic");
        node.put("counterNewBookType", 0);
        return node.toString();
    }

    public static String toJson(Request.Book book) {
        var node = OBJECT_MAPPER.createObjectNode();
        node.put("name", book.name);
        node.put("author", book.author);
        node.put("numberOfBooks", 1);
        node.put("bookType", book.bookType);
        return node.toString();
    }

    public static String toJson(List<Integer> ints) {
        var node = OBJECT_MAPPER.createArrayNode();
        ints.forEach(node::add);
        return node.toString();
    }

    public static List<Integer> getRentIds(String json) throws JsonProcessingException {
        var node = toNode(json);
        List<Integer> rentIds = new ArrayList<>();
        node.forEach(rent -> rentIds.add(rent.get("id").asInt()));
        return rentIds;
    }

}
