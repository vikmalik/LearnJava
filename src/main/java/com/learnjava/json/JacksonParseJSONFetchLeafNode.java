package com.learnjava.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 *
 * @author vikmalik
 */
public class JacksonParseJSONFetchLeafNode {
    public static void main(String[] args) throws IOException {
        String employeeStr = "{ \"name\": \"Vikas\", \"basic-salary\": \"1000\", \"HRA\": \"200\","
                + " \"addresses\" : [{\"home\": {\"city\": \"Jind\"}, \"office\": {\"city\": \"Gurgaon\"}}] }";
        
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode employee = objectMapper.readTree(employeeStr);
        JsonNode basicSalary = employee.get("basic-salary");
        JsonNode addresses = employee.get("addresses");
        
        
        
        System.out.println("salary.asText() = " + employee.asText());
        System.out.println("salary.toString() = " + employee.toString());
        
        System.out.println();
        System.out.println("basicSalary.asText() = " + basicSalary.asText());
        System.out.println("basicSalary.toString() = " + basicSalary.toString());
        
        System.out.println();
        System.out.println("addresses.isArray() = " + addresses.isArray());
        System.out.println("addresses.toString() = " + addresses.toString());
        
    }
}
