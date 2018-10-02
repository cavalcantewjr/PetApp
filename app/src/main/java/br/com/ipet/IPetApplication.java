package br.com.ipet;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseUser;

import br.com.ipet.model.entities.Carrinho;

public class IPetApplication extends Application {
    private static IPetApplication instance;
    private static Context context;
    public static FirebaseUser usuarioLogado;
    public static Carrinho carrinho = new Carrinho();

    public static IPetApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        this.setContext(getApplicationContext());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
