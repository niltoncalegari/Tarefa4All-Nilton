package br.com.nilto.tarefa4all.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import br.com.nilto.tarefa4all.R;
import br.com.nilto.tarefa4all.model.Comentario;

import static br.com.nilto.tarefa4all.views.TelaPincipalActivity.getBitmapFromURL;

/**
 * Created by nilto on 02/11/2016.
 */

public class ComentarioAdapter extends ArrayAdapter<Comentario> {

    private List<Comentario> items ;
    public ComentarioAdapter(Context context, int textViewResourceId, List<Comentario> items) {
        super (context, textViewResourceId, items);
        this.items = items;
    }
    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null ) {
            Context ctx = getContext();
            LayoutInflater vi =
                    (LayoutInflater)ctx.getSystemService(Context. LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout. lista_coments, null );
        }
        Comentario comentario = items .get(position);
        if (comentario != null ) {
            ((TextView) v.findViewById(R.id.txtNome)).setText(comentario.getNome());
            ((TextView) v.findViewById(R.id.txtComentario)).setText(comentario.getComentario());
            ((TextView) v.findViewById(R.id.txtTitulo)).setText(comentario.getTitulo());
            ((ImageView) v.findViewById(R.id.imgFoto)).setImageBitmap(getBitmapFromURL(comentario.getUrlFoto()));
            ((RatingBar) v.findViewById(R.id.ratingBar)).setRating(comentario.getNota());
        }
        return v;
    }



}
