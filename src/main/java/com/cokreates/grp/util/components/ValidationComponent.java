package com.cokreates.grp.util.components;

import org.springframework.stereotype.Component;

@Component
public class ValidationComponent {


    public String validateStringInput(String input){

        input = input.replace("'","''");

        return input;
    }

}
