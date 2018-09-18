package br.com.ipet.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.ipet.PetAppApplication;
import br.com.ipet.R;
import br.com.ipet.database.entities.Usuario;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.usuario_text_input)
    TextInputLayout usuarioTextInput;
    @BindView(R.id.usuario_edit_text)
    TextInputEditText usuarioEditText;

    @BindView(R.id.password_text_input)
    TextInputLayout passwordTextInput;
    @BindView(R.id.password_edit_text)
    TextInputEditText passwordEditText;

    @BindView(R.id.entrar_button)
    MaterialButton entrarButton;
    @BindView(R.id.cadastrar_button)
    MaterialButton cadastrarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(PetAppApplication.usuario != null){
            navegarParaMenu();
        }

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        cadastrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navegarParaCadastro();
            }
        });
        entrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PetAppApplication.usuario = new Usuario();
                PetAppApplication.usuario.nome = usuarioEditText.getText().toString();
                PetAppApplication.usuario.senha = passwordEditText.getText().toString();

                navegarParaMenu();
            }
        });
    }

    private void navegarParaCadastro(){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    private void navegarParaMenu(){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}