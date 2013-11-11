package fileChose;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;

public class ListFileActivity extends ListActivity {

	String inicio;
	List<String> directorios;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inicio = "sdcard";
		directorios = new ArrayList<String>();
		directorios.add(inicio);
		setListAdapter(new FileArrayAdapter(this, listaDeArchivos()));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String valorEvento = ((String) getListAdapter().getItem(position));

		if (position != 0) {
			if (valorEvento.charAt(0) == '1') {

				try {
					directorios.add(valorEvento.substring(1));
					setListAdapter(new FileArrayAdapter(this, listaDeArchivos()));
				} catch (Exception e) {
					Toast.makeText(this, "Imposible abrir el directorio.",Toast.LENGTH_LONG).show();
					directorios.remove(directorios.size() - 1);
					setListAdapter(new FileArrayAdapter(this, listaDeArchivos()));
				}
			} else {exitFileChoose(valorEvento);}
		} else {
			directorios.remove(directorios.size() - 1);
			setListAdapter(new FileArrayAdapter(this, listaDeArchivos()));
		}

	}

	public String[] listaDeArchivos() {

		File[] listaArchivos;
		List<String> archivosFiltrados;
		String archivos[];
		String direccion = createUrl();
		byte suma;

		suma = directorios.size() != 1 ? (byte) 1 : 0;
		direccion = direccion.substring(0, direccion.length() - 1);

		listaArchivos = new File(direccion).listFiles();
		archivosFiltrados = new ArrayList<String>();

		for (File f : listaArchivos) {
			String titulo = f.getName();
			if (f.isFile() && titulo.endsWith(".js"))
				archivosFiltrados.add("0" + titulo);
			else if (f.isDirectory())
				archivosFiltrados.add("1" + titulo);
		}

		archivos = new String[(archivosFiltrados.size() + suma)];
		archivos[0] = "2" + directorios.get(directorios.size() - 1);
		for (int i = 0; i < archivosFiltrados.size(); i++)
			archivos[i + suma] = archivosFiltrados.get(i);

		return archivos;
	}

	public String createUrl() {
		String direccion = "";
		for (String directorio : directorios)
			direccion += directorio + "/";
		return direccion;
	}
	
	public void exitFileChoose(String file){
		String direccion = createUrl();
		direccion += file.substring(1);
		Intent intent = new Intent();
		intent.putExtra("result", direccion);
		setResult(1, intent);
		finish();
	}

}