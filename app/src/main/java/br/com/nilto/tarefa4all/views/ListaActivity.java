package br.com.nilto.tarefa4all.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import br.com.nilto.tarefa4all.R;
import br.com.nilto.tarefa4all.model.IdList;
import br.com.nilto.tarefa4all.model.Tarefa;
import br.com.nilto.tarefa4all.network.HttpGet;

public class ListaActivity extends AppCompatActivity {

    IdList idList;
    ListView lstResultados;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        lstResultados = (ListView) findViewById(R.id.lstResultados);

        try {
            idList = HttpGet.retornarTarefaPorId();
            String nova = idList.getIdList();
            String[] listaId = nova.split(",");
            ListView resultadoLista = (ListView) findViewById(R.id.lstResultados);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, listaId);
            resultadoLista.setAdapter(adapter);

            //Escuta em qual posição da listview o usuario clicou e envia para tela Principal com o Id a ser buscado
            lstResultados.setOnItemClickListener(new ListView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String idEnvio = lstResultados.getItemAtPosition(position).toString();
                    Intent vaiParaPrincipal = new Intent(ListaActivity.this, br.com.nilto.tarefa4all.views.TelaPincipalActivity.class);
                    vaiParaPrincipal.putExtra("id", idEnvio);
                    idList.setIdAux(idEnvio);

                    startActivity(vaiParaPrincipal);





                    Toast.makeText(getBaseContext(), "Id " + idEnvio, Toast.LENGTH_LONG).show();
                }
            });


        } catch (Exception ex) {
            Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }


    }


}

