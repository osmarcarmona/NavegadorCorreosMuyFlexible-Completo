package cat.udl.eps.fragments.ejmoreflexible;

import cat.udl.eps.fragments.ejmoreflexible.FragmentListado.CorreosListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements CorreosListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCorreoSeleccionado(Correo c) {

		boolean hayDetalle =
				(getSupportFragmentManager().findFragmentById(R.id.FrgDetalle) != null);

		if (hayDetalle) {
            FragmentDetalle fgdet = (FragmentDetalle) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
			fgdet.mostrarDetalle(c.getTexto());
		}
		else {
            Intent i = new Intent( this, DetalleActivity.class);
            i.putExtra("cuerpo",c.getTexto());
            startActivity(i);
		}
		
	}
}
