package com.cokreates.grp.data.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface NodeNameBn {

    HashMap<List<String>, String> nodeNameToBangla = new HashMap<List<String>, String>() {{

        put(Arrays.asList("personal", "bank"), "ব্যাংক বিষয়ক তথ্য");
        put(Arrays.asList("personal", "birthPlaceAddress"), "জন্মস্থান");
        put(Arrays.asList("personal", "childEducation"), "");
        put(Arrays.asList("personal", "emergencyContactAddress"), "জরুরি ঠিকানা");
        put(Arrays.asList("personal", "familyInfo"), "পারিবারিক তথ্য");
        put(Arrays.asList("personal", "file"), "সংযুক্তি বিষয়ক তথ্য");
        put(Arrays.asList("personal", "foreignAddress"), "বৈদেশিক ঠিকানা");
        put(Arrays.asList("personal", "general"), "সাধারণ তথ্য");
        put(Arrays.asList("personal", "health"), "স্বাস্থ্য বিষয়ক তথ্য");
        put(Arrays.asList("personal", "permanentAddress"), "স্থায়ী ঠিকানা");
        put(Arrays.asList("personal", "presentAddress"), "বর্তমান ঠিকানা");
        put(Arrays.asList("personal", "injuriesSickness"), "বিশেষ অসুস্থতা/দুর্ঘটনা-আঘাত বিষয়ক তথ্য");
        put(Arrays.asList("personal", "travel"), "ভ্রমণ বিষয়ক তথ্য");

        put(Arrays.asList("professional", "disciplinaryAction"), "শাস্তিমূলক ব্যবস্থা বিষয়ক তথ্য");
        put(Arrays.asList("professional", "serviceHistory"), "সার্ভিস ইতিহাস");
        put(Arrays.asList("professional", "leave"), "ছুটি বিষয়ক তথ্য");
        put(Arrays.asList("professional", "posting"), "পোস্টিং বিষয়ক তথ্য");
        put(Arrays.asList("professional", "professionalGeneral"), "পেশাগত সাধারণ তথ্য");
        put(Arrays.asList("professional", "promotion"), "পদোন্নতি বিষয়ক তথ্য");
        put(Arrays.asList("professional", "jobHistory"), "প্রাক সরকারী ইতিহাস");

        put(Arrays.asList("qualification", "award"), "অ্যাওয়ার্ড বিষয়ক তথ্য");
        put(Arrays.asList("qualification", "education"), "শিক্ষা বিষয়ক তথ্য");
        put(Arrays.asList("qualification", "language"), "ভাষা বিষয়ক তথ্য");
        put(Arrays.asList("qualification", "trainingAndProfessionalCertification", "professionalCertification"), "পেশাগত সনদ বিষয়ক তথ্য");
        put(Arrays.asList("qualification", "trainingAndProfessionalCertification", "training"), "প্রশিক্ষণ বিষয়ক তথ্য");
        put(Arrays.asList("qualification", "professionalQualification"), "পেশাগত যোগ্যতা বিষয়ক তথ্য");
        put(Arrays.asList("qualification", "publication"), "প্রকাশনা বিষয়ক তথ্য");

    }};

}