package com.telran.a06_01_20_hw;

public class Contact {
    String name;
    String email;
    String phone;
    String address;

    public Contact(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString(){
        return name+","+email+","+phone+","+address;
    }

    public static Contact of(String str){
        String[] arr = str.split(",");
        return new Contact(arr[0],arr[1],arr[2],arr[3]);
    }
}
