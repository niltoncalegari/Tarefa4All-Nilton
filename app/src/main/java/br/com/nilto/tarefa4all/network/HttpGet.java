package br.com.nilto.tarefa4all.network;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.nilto.tarefa4all.model.Comentario;
import br.com.nilto.tarefa4all.model.IdList;
import br.com.nilto.tarefa4all.model.Tarefa;

public class HttpGet {

    private static String URL_API = "http://dev.4all.com:3003/tarefa/";
    private static JSONArray list = null;
    private static ArrayList<Comentario> comentario;


    private static String readStream(InputStream in) {
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;

        try {
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return total.toString();
    }

    public static String request(String stringUrl) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        System.out.println(stringUrl);
        try {
            url = new URL(stringUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return "";
    }


    public static IdList retornarTarefaPorId() throws IOException, JSONException {

        String content = request(URL_API);
        JSONObject obj = new JSONObject(content);
        list = obj.getJSONArray("lista");
        String lista = list.optString(0);
        for (int i = 1; i < list.length(); i++) {
            lista += "," + list.optString(i);
        }

        return new IdList(lista);
    }


    public static Tarefa retornarDadosPorId(String auxId) throws IOException, JSONException {

        String content = request(URL_API + auxId);
        JSONObject obj = new JSONObject(content);

        String idList = obj.getString("id");
        String cidade = obj.getString("cidade");
        String bairro = obj.getString("bairro");
        String urlFoto = obj.getString("urlFoto");
        String urlLogo = obj.getString("urlLogo");
        String titulo = obj.getString("titulo");
        String telefone = obj.getString("telefone");
        String texto = obj.getString("texto");
        String endereco = obj.getString("endereco");
        double latitude = Double.parseDouble(obj.getString("latitude"));
        double longitude = Double.parseDouble(obj.getString("longitude"));
        JSONArray comentariosJSON = (JSONArray) obj.get("comentarios");
        ArrayList<Comentario> list = new ArrayList(comentariosJSON.length());
        if(comentariosJSON != null) {
            for(int i = 0; i < comentariosJSON.length(); i++) {
                JSONObject y = comentariosJSON.getJSONObject(i);
                Comentario x = new Comentario(y.getString("urlFoto"), y.getString("nome"), y.getInt("nota"), y.getString("titulo"), y.getString("comentario"));
                list.add(x);
            }
        }

        return new Tarefa(idList, cidade, bairro, urlFoto, urlLogo, titulo, telefone, texto, endereco, latitude, longitude, list);
    }
}