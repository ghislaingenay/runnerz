package com.running.runnerz.utils;

import java.io.File;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
  private final ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Parses a JSON string into an object of the specified type.
   *
   * @param json the JSON string to parse
   * @param valueType the class of the type to parse into
   * @param <T> the type of the object to return
   * @return an object of type T parsed from the JSON string
   * @throws Exception if parsing fails
   */
  public <T> T parseJson(String json, Class<T> valueType) throws Exception {
    return objectMapper.readValue(json, valueType);
  }
  public <T> T parseJson(String json, TypeReference<T> valueType) throws Exception {
    return objectMapper.readValue(json, valueType);
  }
  /**
   * Converts an object to its JSON string representation.
   *
   * @param value the object to convert to JSON
   * @return a JSON string representation of the object
   * @throws Exception if conversion fails
   */
  public String toJson(Object value) throws Exception {
    return objectMapper.writeValueAsString(value);
  }

  /**
   * Parses a JSON file into an object of the specified type.
   * @param <T>
   * @param filePath the path to the JSON file
   * @param valueType the class of the type to parse into
   * @return 
   * @throws Exception
   */
  public <T> T parseJsonFile(String filePath, Class<T> valueType) throws Exception {
    return objectMapper.readValue(new File(filePath), valueType);
  }
  public <T> T parseJsonFile(String filePath,  TypeReference<T> valueType) throws Exception {
    return objectMapper.readValue(new File(filePath), valueType);
  }
 
}
