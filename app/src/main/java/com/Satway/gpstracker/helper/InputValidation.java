//package com.example.gpstracker.helper;
//
//import android.content.Context;
//
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.android.material.textfield.TextInputLayout;
//
//public class InputValidation {
//
//    public Context context;
//
//    public InputValidation(Context context){
//        this.context = context;
//    }
//
//    public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message){
//        String Value = textInputEditText.getText().toString().trim();
//        if (Value.isEmpty()){
//            textInputLayout.setError(message);
//            hideKeyboardFrom(textInputEditText);
//            return false;
//        }else {
//            textInputLayout.setErrorEnabled(false);
//        }
//        return true;
//    }
//
//    public boolean isInputEditTextUsername(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message){
//        String value = textInputEditText.getText().toString().trim();
//        if (value.isEmpty() || !isInputEditTextUsername("value").matcher(value).matches()){
//            textInputLayout.setError(message);
//            hideKeyboardFrom(textInputEditText);
//            return false;
//        }else {
//            textInputLayout.setErrorEnabled(false);
//        }
//        return true;
//    }
//    public boolean isInputEditTextMatches(TextInputEditText textInputEditText1, TextInputEditText textInputEditText2, TextInputLayout textInputLayout, String message){
//        String value = textInputEditText2
//    }
//}
