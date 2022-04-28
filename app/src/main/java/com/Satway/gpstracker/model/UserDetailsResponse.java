package com.Satway.gpstracker.model;

import java.util.List;

public class UserDetailsResponse {
    List<UserData> userlist;
    List<String> imeilist;

    public UserDetailsResponse() {
    }

    public UserDetailsResponse(List<UserData> userlist, List<String> imeilist) {
        this.userlist = userlist;
        this.imeilist = imeilist;
    }

    public List<UserData> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<UserData> userlist) {
        this.userlist = userlist;
    }

    public List<String> getImeilist() {
        return imeilist;
    }

    public void setImeilist(List<String> imeilist) {
        this.imeilist = imeilist;
    }
}
