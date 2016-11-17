package com.learnjava.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 *
 * @author vikmalik
 */
public class JacksonConverterExamples {

    private static final String EMP_ID = "employeeId";

    @JsonPropertyOrder({
        EMP_ID,
        "name",
        "salary"
    })
    private static class Employee {

        @JsonProperty(EMP_ID)
        private String id;

        private String name;

        @JsonIgnore
        private String password = "Password";
        private String salary;

        public String getId() {
            System.out.println("com.learnjava.json.JacksonConverterExamples.Employee.getId()");
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            System.out.println("com.learnjava.json.JacksonConverterExamples.Employee.getName()");
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalary() throws IOException {
            System.out.println("com.learnjava.json.JacksonConverterExamples.Employee.getSalary()");

            String processedSalary = "{\"basic-salary\": \"1000\", \"HRA\": \"200\" }";
            
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode actualObj = objectMapper.readTree(processedSalary);
            
            System.out.println("com.learnjava.json.JacksonConverterExamples.Employee.getSalary()" + actualObj);
            return actualObj.toString();
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        Employee emp = new Employee();
        emp.setId("1001");
        emp.setName("Vikas");
        emp.setPassword("my-password");

        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("com.learnjava.json.JacksonConverterExamples.main() : " 
                + objectMapper.writeValueAsString(emp));

        System.out.println("com.learnjava.json.JacksonConverterExamples.main() :\n" 
                + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp));
    }

}
