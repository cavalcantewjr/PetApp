package br.com.ipet.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import br.com.ipet.IPetApplication;
import br.com.ipet.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CadastroActivity extends AppCompatActivity {
    @BindView(R.id.usuario_text_input)
    TextInputLayout usuarioTextInput;
    @BindView(R.id.usuario_edit_text)
    TextInputEditText usuarioEditText;

    @BindView(R.id.email_text_input)
    TextInputLayout emailTextInput;
    @BindView(R.id.email_edit_text)
    TextInputEditText emailEditText;

    @BindView(R.id.password_text_input)
    TextInputLayout passwordTextInput;
    @BindView(R.id.password_edit_text)
    TextInputEditText passwordEditText;

    @BindView(R.id.cadastrar_button)
    MaterialButton cadastrarButton;

    @BindView(R.id.voltar_button)
    MaterialButton voltarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        ButterKnife.bind(this);

        if(IPetApplication.usuarioLogado != null){
            navegarParaMenu();
        }

        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navegarParaLogin();
            }
        });
        cadastrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: persistir novo usuário no firebase
//                IPetApplication.usuarioLogado = new Usuario();
//                IPetApplication.usuarioLogado.nome = usuarioEditText.getText().toString();
//                IPetApplication.usuarioLogado.email = emailEditText.getText().toString();
//                IPetApplication.usuarioLogado.senha = passwordEditText.getText().toString();

                navegarParaMenu();
            }
        });
    }

    private void navegarParaLogin(){
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void navegarParaMenu(){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public boolean isValidEmail(String target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}