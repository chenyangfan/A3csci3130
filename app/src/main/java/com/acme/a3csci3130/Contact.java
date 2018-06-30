package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

/**
 * @see java.io.Serializable for more information about this interface
 */
public class Contact implements Serializable {

    public  String uid;
    public  String name;
    public  String email;
    public  String number;
    public  String primary_Business;
    public  String address;
    public  String province;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Contact(String uid, String name, String email,String number,String Primary_Business, String Address,String Province ){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.number = number;
        this.primary_Business = Primary_Business;
        this.address = Address;
        this.province = Province;
    }

    public Contact(String uid, String name, String email){
        this.uid = uid;
        this.name = name;
        this.email = email;
    }

    /**
     * @see Map
     * @return a HashMap that has uid,name,email,number,primary_Business,Address,Province
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("email", email);
        result.put("number",number);
        result.put("Primary_Business",primary_Business);
        result.put("Address",address);
        result.put("Province",province);

        return result;
    }
}
