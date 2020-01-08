package com.telran.a06_01_20_hw;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class ListViewModel {
    private StoreProvider provider;
    private MutableLiveData<List<Contact>> listLiveData = new MutableLiveData<>();
    private MutableLiveData<String> stateLiveData = new MutableLiveData<>();

    public ListViewModel(StoreProvider provider){
        this.provider = provider;
        listLiveData.setValue(provider.getAllContacts());
        stateLiveData.setValue("IDLE");

        provider.getContactLiveData().observeForever(list->{
            listLiveData.setValue(list);
        });
    }

    public LiveData<List<Contact>> getListLiveData() {
        return listLiveData;
    }


    public MutableLiveData<String> getStateLiveData() {
        return stateLiveData;
    }

//    public void update(){
//        listLiveData.setValue(provider.getAllContacts());
//    }

    public void logout(){
        provider.logout();
        stateLiveData.setValue("LOGOUT");
    }

    public void addContact(){
        stateLiveData.setValue("ADD");
    }

    public void showToast(){
        stateLiveData.setValue("SHOW");
    }
}
