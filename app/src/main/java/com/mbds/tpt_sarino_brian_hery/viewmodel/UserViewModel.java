package com.mbds.tpt_sarino_brian_hery.viewmodel;

import android.content.Context;
import com.mbds.tpt_sarino_brian_hery.model.user.User;
import com.mbds.tpt_sarino_brian_hery.model.utils.AccesLocal;

import static java.security.AccessController.getContext;

public class UserViewModel {
    private static AccesLocal accesLocal;
    private static User user;

    public UserViewModel(Context context) {
        accesLocal = new AccesLocal(context);
        user = accesLocal.getLastUser();
    }
    public static User getUser(){
        return user;
    }

    /**
     * Créer un nouveau compte.
     */
    public static void createAccount(User user) {
        accesLocal.ajouterUser(user);
    }

}
