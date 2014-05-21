package com.learnjava.general;

import com.learnjava.bean.Person;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author vikmalik
 */
public class LearnHashCode {

    public static void main(String[] args) throws IOException {
        new LearnHashCode().doMain(args);
    }

    public void doMain(String[] args) throws IOException {
        System.out.printf("true ^ true = %b\n", true ^ true);
        System.out.printf("true ^ false = %b\n", true ^ false);
        System.out.printf("false ^ false = %b\n", false ^ false);
        
        long i = 1;
        System.out.printf("1>>>32 = %s :: %s :: %s\n",
                Long.toBinaryString(i),
                Long.toBinaryString(i >>> 32),
                Long.toBinaryString((i ^ (i >>> 32))));
        
        

        populateData(0);
        populateData(1);
        populateData(Integer.MAX_VALUE);

    }

    private Person populateData(int id) throws IOException {

        Random random = new Random();

        Person person = new Person();
        person.setId(id);
        person.setFirstName("fname");
        person.setLastName("lname");
        person.setAge(random.nextInt(100));
        System.out.printf("%d \n", person.hashCode());

        return person;
    }

}
