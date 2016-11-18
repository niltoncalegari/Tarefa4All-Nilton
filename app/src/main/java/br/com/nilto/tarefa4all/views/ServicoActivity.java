package br.com.nilto.tarefa4all.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.nilto.tarefa4all.R;

public class ServicoActivity extends Activity {

    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiParaLista = new Intent(ServicoActivity.this, ListaActivity.class);
                startActivity(vaiParaLista);
            }
        });

    }
}
