package com.cokreates.grp.util.components;

import org.springframework.stereotype.Component;

@Component
public class UtilCharacter {

    public String convertNumberEnToBn(String txt) {
        if (txt == null) return null;
        String replacedVersion = txt
                .replaceAll("0", "০").replaceAll("1", "১")
                .replaceAll("2", "২").replaceAll("3", "৩")
                .replaceAll("4", "৪").replaceAll("5", "৫")
                .replaceAll("6", "৬").replaceAll("7", "৭")
                .replaceAll("8", "৮").replaceAll("9", "৯");
        return replacedVersion;
    }

    public String convertNumberBnToEn(String txt) {
        if (txt == null) return null;
        String replacedVersion = txt
                .replaceAll("০", "0").replaceAll("১", "1")
                .replaceAll("২", "2").replaceAll("৩", "3")
                .replaceAll("৪", "4").replaceAll("৫", "5")
                .replaceAll("৬", "6").replaceAll("৭", "7")
                .replaceAll("৮", "8").replaceAll("৯", "9");
        return replacedVersion;
    }

    public boolean noData(String txt) {
        //return txt == null || txt.trim().equals("");
        boolean flag = false;
        if (null == txt) {
            flag = true;
        } else if (txt.trim().isEmpty()) {
            flag = true;
        }
        return flag;
    }

    public boolean noData(Object obj) {
        return obj == null;
    }

}