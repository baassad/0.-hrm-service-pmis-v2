package com.cokreates.grp.util.components;

import org.springframework.stereotype.Component;

@Component
public class ValidationComponent {


    public String validateStringInput(String input){

        if(input == null){
            return null;
        }

        input = input.replace("'","");

        input = input.replace("--" , "");


        return input;
    }

}
