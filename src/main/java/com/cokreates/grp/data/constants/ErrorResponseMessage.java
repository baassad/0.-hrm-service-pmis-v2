package com.cokreates.grp.data.constants;

public interface ErrorResponseMessage {
    String TOKEN_NOT_VALID = "অনুগ্রহপূর্বক, সঠিক টোকেন প্রদান করুন";
    String TOKEN_NOT_FOUND = "অনুগ্রহপূর্বক, টোকেন প্রদান করুন";
    String NO_SEND_ALLOWED_FOR_EMPTY_HISTORY = "অনুগ্রহপূর্বক, কর্তৃপক্ষের নিকট \"রায়\" এর জন্য প্রেরণ করুন";
    String NO_INVESTIGATION_ALLOWED_WITHOUT_SHOW_CAUSE = "দুঃখিত, কারণ দর্শানো/শুনানি ব্যতীত তদন্তের আদেশ গ্রহণযোগ্য নয়";
    String NO_ACTIVE_EMPLOYEE_SHIFT = "ইনার জন্যে কোনো সক্রিয় শিফট পাওয়া যায় নি : ";
    String NO_MAIN_RESPONSIBILITY = "ইনার কোন স্বপদ খুঁজে পাওয়া যায় নি : ";
    String NO_COMMITTEE = "এই আইডি দিয়ে কোনো কমিটি পাওয়া যায় নি : ";
    String NO_EMAIL_IN_GENERAL = "ইনার সাধারণ(ব্যক্তিগত) তথ্যে কোনো ইমেইল ঠিকানা খুঁজে পাওয়া যায় নি : ";
    String NO_OFFICE = "কোন অফিস খুঁজে পাওয়া যায় নি : ";
    String NO_ACTIVE_SHIFT = "এই শিফট এর কোনো সক্রিয় রুটিন পাওয়া যায় নি : ";
    String NO_GENERAL_SHIFT = "এই অফিস এর কোনো 'সাধারণ' শিফট খুঁজে পাওয়া যায় নি : ";
    String PAST_FROM_DATE_NOT_EDITABLE = "দুঃখিত, অতীতে বিদ্যমান 'হইতে' তারিখ পরিবর্তন করা যাবে না";
    String CUURENNT_DATE_EMP_SHIFT_NOT_ASSIGNABLE = "দুঃখিত, আজকের তারিখে কোনো কর্মকর্তা/কর্মচারীকে নতুন শিফট এ অর্পণ করা যাবে না";
    String CUURENNT_DATE_SHIFT_TIME_NOT_CHANGEABLE = "দুঃখিত, আজকের তারিখের শিফটের আগমনের সময়/প্রস্থানের সময়/অনুগ্রহ আগমন/অনুগ্রহ প্রস্থান পরিবর্তন করা যাবে না";
    String PAST_TO_DATE_NOT_EDITABLE = "দুঃখিত, অতীতে বিদ্যমান শিফট পরিবর্তন করা যাবে না";
    String ROUTINE_AFTER_NULL_TO_DATE = "অনির্দিষ্টকাল ধরে ব্যাপ্ত রুটিনটি অন্যান্য রুটিন এর সাথে অসামাঞ্জস্যপূর্ণ";
    String NULL_FROM_DATE = "'হইতে' তারিখ আবশ্যক";
    String PAST_FROM_DATE = "'হইতে' তারিখ অতীতে হতে পারে না";
    String PAST_TO_DATE = "'পর্যন্ত' তারিখ অতীতে হতে পারে না";
    String FROM_DATE_GREATER_THAN_TO_DATE = "দুঃখিত, 'হইতে' তারিখ 'পর্যন্ত' তারিখ এর পরে হতে পারে না";
    String OVERLAP = "দুঃখিত, প্রদত্ত রুটিনটি বিদ্যমান রুটিনগুলোর সাথে অসামঞ্জস্যপূর্ণ";
    String CAN_NOT_ALTER_GENERAL = "দুঃখিত, 'সাধারণ' শিফট এর নাম পরিবর্তন যোগ্য নয়";
    String DUPLICATE_NAME = "দুঃখিত, এই অফিস এ প্রদত্ত নামে আরেকটি শিফট বিদ্যমান ";

}
