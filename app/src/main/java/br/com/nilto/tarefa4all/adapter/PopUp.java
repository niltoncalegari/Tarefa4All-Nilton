package br.com.nilto.tarefa4all.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import br.com.nilto.tarefa4all.R;
import br.com.nilto.tarefa4all.model.Tarefa;

/**
 * Created by nilto on 24/10/2016.
 */

public class PopUp extends Activity {

    TextView txtEndereco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);
        Intent dado = getIntent();
        String enderecoInt = dado.getStringExtra("endereco");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.6),(int)(height*.3));
        txtEndereco = (TextView) findViewById(R.id.txtEndereco);
        txtEndereco.setText("Endereço: "+ enderecoInt +"\r\n" +
                "Clique fora do popup para fechar!");


    }
}
