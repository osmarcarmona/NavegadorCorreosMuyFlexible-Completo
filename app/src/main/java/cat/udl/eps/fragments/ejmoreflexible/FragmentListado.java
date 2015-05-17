package cat.udl.eps.fragments.ejmoreflexible;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class FragmentListado extends Fragment {

	private Correo[] datos =
	    	new Correo[]{
	    		new Correo("Persona 1", "Asunto del correo 1", "Texto del correo 1"),
	    		new Correo("Persona 2", "Asunto del correo 2", "Texto del correo 2"),
	    		new Correo("Persona 3", "Asunto del correo 3", "Texto del correo 3"),
	    		new Correo("Persona 4", "Asunto del correo 4", "Texto del correo 4"),
	    		new Correo("Persona 5", "Asunto del correo 5", "Texto del correo 5")};
	
	private ListView lstListado;
	private ArrayList<Correo> listCorreo = new ArrayList<Correo>();
	private CorreosListener listener;
    FragmentListado a;
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {

        for (int i=0;i<5;i++){
        listCorreo.add(datos[i]);
        }

		return inflater.inflate(R.layout.fragment_listado, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        a= (FragmentListado) getFragmentManager().findFragmentById(R.id.FrgListado);
		lstListado = (ListView)getView().findViewById(R.id.LstListado);

		lstListado.setAdapter(new AdaptadorCorreos(getActivity(), R.layout.listitem_correo, listCorreo));
		
		lstListado.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
               /*FragmentDetalle frag = (FragmentDetalle) getFragmentManager().findFragmentById(R.id.FrgDetalle);
                String cuerpo = listCorreo.get(pos).getTexto();
                if (frag != null && frag.isInLayout()) {
                    //frag.showText(getCapt(item));
                    frag.mostrarDetalle(cuerpo);
                }
                else{
                    Intent i = new Intent ( getActivity(), DetalleActivity.class);
                    i.putExtra("cuerpo",cuerpo);
                    startActivity(i);
                }*/
                Correo sel= listCorreo.get(pos);
                listener.onCorreoSeleccionado(sel);
                a.setCorreosListener(listener);
			}
			
		});
	}
	
	
	@Override
	public void onAttach(Activity ac) {
		super.onAttach(ac);
		try {
        listener = (CorreosListener) ac;
		}
		catch (ClassCastException e) {
			throw new ClassCastException(ac.toString() + " must implement OnCorreosListener");
		}
	}
			
			
	class AdaptadorCorreos extends ArrayAdapter<Correo> {

        public AdaptadorCorreos(Context context, int textViewResourceId, List<Correo> objects) {
            super(context, textViewResourceId, objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            View item = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.listitem_correo, parent, false);

			TextView lblDe = (TextView)item.findViewById(R.id.LblDe);
			lblDe.setText(datos[position].getDe());
			
			TextView lblAsunto = (TextView)item.findViewById(R.id.LblAsunto);
			lblAsunto.setText(datos[position].getAsunto());
			
			return(item);
		}
    }
	
	public interface CorreosListener {
		public void onCorreoSeleccionado(Correo c);

	}
	
	public void setCorreosListener(CorreosListener listener) {
		this.listener = listener;
	}
}
