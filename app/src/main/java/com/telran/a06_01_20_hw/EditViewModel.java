package com.telran.a06_01_20_hw;

import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class EditViewModel {

    public String name = "";
    public String email = "";
    public String phone = "";
    public String address = "";

    private StoreProvider store;
    private int currIndex;

    public ObservableBoolean isEdit = new ObservableBoolean();
    private MutableLiveData<String> stateLiveData = new MutableLiveData<>();


    public EditViewModel(int index, StoreProvider store) {
        this.store = store;
        currIndex = index;
        if(index >= 0){
            Contact cnt = store.get(index);
            name = cnt.name;
            email = cnt.email;
            phone = cnt.phone;
            address = cnt.address;
            isEdit.set(false);
        }else{
            isEdit.set(true);
        }
    }

    public LiveData<String> getStateLiveData(){
        return stateLiveData;
    }

    public void remove(){
        store.remove(currIndex);
        stateLiveData.setValue("REMOVED");
    }

    public void save(){
        Contact cnt = new Contact(name,email,phone,address);
        if(currIndex < 0){
            store.add(cnt);
            stateLiveData.setValue("ADDED");
        }else{
            store.update(currIndex,cnt);
            stateLiveData.setValue("UPDATED");
        }
    }

    public void makeEdit(){
        isEdit.set(true);
    }
}
