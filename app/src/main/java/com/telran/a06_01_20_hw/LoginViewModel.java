package com.telran.a06_01_20_hw;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel {
    public String email = "";
    public String password = "";
    private StoreProvider provider;
    private MutableLiveData<Boolean> authStatusChanged = new MutableLiveData<>();

    public LoginViewModel(StoreProvider provider) {
        this.provider = provider;
        authStatusChanged.setValue(provider.isLoggined());
    }

    public MutableLiveData<Boolean> getAuthStatusChanged() {
        return authStatusChanged;
    }

    public void onLogin(){
        provider.login(email,password);
        authStatusChanged.setValue(true);
    }
}
