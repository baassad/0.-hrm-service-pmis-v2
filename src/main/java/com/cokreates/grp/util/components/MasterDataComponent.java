package com.cokreates.grp.util.components;

import org.springframework.stereotype.Component;

@Component
public class MasterDataComponent {


    public String getMaritalStatus(String maritalStatus){

        if(maritalStatus == null){
            return "অবিবাহিত";
        }

        switch (maritalStatus){

            case "Married": return "বিবাহিত";
            case "Unarried": return "অবিবাহিত";
            case "Widow": return "বিধবা";
            case "Divorcee": return "তালাকপ্রাপ্ত";
            case "Divorced": return "তালাকপ্রাপ্ত";
            default: return "অবিবাহিত";


        }

    }

    public  String getGender(String gender){

        if(gender == null){
            return "পুরুষ";
        }

        switch (gender){

            case "Male": return "পুরুষ";
            case "Female": return "মহিলা";
            case "Other": return "অন্যান্য";
            default:return "পুরুষ";


        }


    }
}
