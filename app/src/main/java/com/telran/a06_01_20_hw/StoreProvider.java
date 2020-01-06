package com.telran.a06_01_20_hw;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StoreProvider {
    private static final String AUTH = "AUTH";
    private static final String CONTACTS = "CONTACTS";
    private static final String TOKEN = "TOKEN";
    private Context context;

    public StoreProvider(Context context) {
        this.context = context;
    }

    public boolean login(String email, String password) {
        SharedPreferences sp = context.getSharedPreferences(AUTH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(TOKEN, email + password);
        return editor.commit();
    }

    public boolean logout() {
        return context.getSharedPreferences(AUTH, Context.MODE_PRIVATE)
                .edit()
                .remove(TOKEN)
                .commit();
    }

    public boolean isLoggined() {
        return context.getSharedPreferences(AUTH, Context.MODE_PRIVATE)
                .getString(TOKEN, null) != null;
    }

    public List<Contact> getAllContacts() {
        ArrayList<Contact> list = new ArrayList<>();
        Objects.requireNonNull(token());
        String contacts = context.getSharedPreferences(CONTACTS, Context.MODE_PRIVATE)
                .getString(token(), null);
        if (contacts != null) {
            String[] arr = contacts.split(";");
            for (String c : arr) {
                list.add(Contact.of(c));
            }
        }
        return list;
    }

    public void add(Contact contact) {
        List<Contact> list = getAllContacts();
        list.add(contact);
        save(list);
    }

    public void remove(int index){
        List<Contact> list = getAllContacts();
        list.remove(index);
        save(list);
    }

    public void update(int index, Contact contact){
        List<Contact> list = getAllContacts();
        list.set(index,contact);
        save(list);
    }

    private boolean save(List<Contact> list) {
        if (!list.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i));
                if (i < list.size() - 1) {
                    sb.append(";");
                }
            }
            return context.getSharedPreferences(CONTACTS, Context.MODE_PRIVATE)
                    .edit()
                    .putString(token(), sb.toString())
                    .commit();
        }
        return false;
    }

    private String token() {
        return context.getSharedPreferences(AUTH, Context.MODE_PRIVATE)
                .getString(TOKEN, null);
    }
}
