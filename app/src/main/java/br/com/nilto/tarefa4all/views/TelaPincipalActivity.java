package br.com.nilto.tarefa4all.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.nilto.tarefa4all.R;
import br.com.nilto.tarefa4all.adapter.ComentarioAdapter;
import br.com.nilto.tarefa4all.adapter.PopUp;
import br.com.nilto.tarefa4all.model.Comentario;
import br.com.nilto.tarefa4all.model.Tarefa;
import br.com.nilto.tarefa4all.network.HttpGet;


public class TelaPincipalActivity extends AppCompatActivity implements OnMapReadyCallback {


    private Tarefa tarefa;
    private ImageButton btnLiga, btnServico, btnComentario, btnFavorito, btnEndereco, btnLogo;
    private ImageView imgTop;
    private TextView txtTexto, txtTitulo, txtEndereco, barraEndereco;
    private MenuItem voltar, lupa, txtMenu;
    private ListView lstComentarios;



    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pincipal);

        imgTop = (ImageView) findViewById(R.id.imgTop);
        btnLiga = (ImageButton) findViewById(R.id.btnLigar);
        btnComentario = (ImageButton) findViewById(R.id.btnComentario);
        btnEndereco = (ImageButton) findViewById(R.id.btnEndereco);
        btnServico = (ImageButton) findViewById(R.id.btnServico);
        btnFavorito = (ImageButton) findViewById(R.id.btnFavorito);
        btnLogo = (ImageButton) findViewById(R.id.btnLogo);
        txtTexto = (TextView) findViewById(R.id.txtTexto);
        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtEndereco = (TextView) findViewById(R.id.txtEndereco);
        barraEndereco = (TextView) findViewById(R.id.barraEndereco);
        voltar = (MenuItem) findViewById(R.id.menu_voltar);
        lupa = (MenuItem) findViewById(R.id.menu_lupa);
        lstComentarios = (ListView) findViewById(R.id.lstComentarios);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Intent dado = getIntent();
        String idRetorno = dado.getStringExtra("id");




        try {


            tarefa = HttpGet.retornarDadosPorId(idRetorno);
            btnLogo.setImageBitmap(getBitmapFromURL(tarefa.getUrlLogo()));
            imgTop.setImageBitmap(getBitmapFromURL(tarefa.getUrlFoto()));
            txtTitulo.setText(tarefa.getTitulo());
            txtTexto.setText(tarefa.getTexto());
            barraEndereco.setText(tarefa.getEndereco());
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.maps);
            mapFragment.getMapAsync(this);


            btnEndereco.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    Intent txtEnd = new Intent(TelaPincipalActivity.this, PopUp.class);
                    txtEnd.putExtra("endereco", tarefa.getEndereco().toString());
                    startActivity(new Intent(txtEnd));

                }
            });

            btnLiga.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri uri = Uri.parse("tel:" + tarefa.getTelefone());
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);

                    startActivity(intent);
                }
            });

            btnServico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent vaiParaServico = new Intent(TelaPincipalActivity.this, ServicoActivity.class);
                    startActivity(vaiParaServico);

                }
            });

            btnFavorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getBaseContext(), "Favoritos", Toast.LENGTH_SHORT).show();
                }
            });


            btnComentario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getBaseContext(), "Comentarios", Toast.LENGTH_SHORT).show();
                }
            });

            //Cria a lista de comentarios


            ListView resultadoListaComentarios = (ListView) findViewById(R.id.lstComentarios);
            List<Comentario> coment = new ArrayList<>();

            int tamanho = tarefa.getComentario().size();
            for (int i = 0; i < tamanho; i++) {
                coment.add(tarefa.getComentario().get(i));
            }
            ComentarioAdapter adapter = new ComentarioAdapter(getBaseContext(), R.layout.lista_coments, coment);
            resultadoListaComentarios.setAdapter(adapter);


        } catch (Exception ex) {
            Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        return true;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng local = new LatLng(tarefa.getLatitude(), tarefa.getLongitude());
        mMap.addMarker(new MarkerOptions().position(local).title("Marcado em"+ tarefa.getEndereco()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(local));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_voltar:
                Intent vaiParaLista = new Intent(TelaPincipalActivity.this, ListaActivity.class);
                startActivity(vaiParaLista);
                return true;
            case R.id.menu_lupa:
                Toast.makeText(getBaseContext(), "Pesquisar", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap meuBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap", "returned");
            return meuBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return null;
        }
    }
}
