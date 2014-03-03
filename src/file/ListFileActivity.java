package file;

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

	public String filter;
	public String first;
	public List<String> path;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.filter = getIntent().getExtras().getString("filtro");
		this.path = new ArrayList<String>();
		this.first = "sdcard";
		this.path.add(this.first);
		setListAdapter(new FileArrayAdapter(this, listaDeArchivos()));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String valueEvent = ((String) getListAdapter().getItem(position));
		if (position != 0) {
			if (valueEvent.charAt(0) == '1') {

				try {
					this.path.add(valueEvent.substring(1));
					setListAdapter(new FileArrayAdapter(this, listaDeArchivos()));
				} catch (Exception e) {
					Toast.makeText(this, "Imposible abrir el directorio.",
							Toast.LENGTH_LONG).show();
					this.path.remove(this.path.size() - 1);
					setListAdapter(new FileArrayAdapter(this, listaDeArchivos()));
				}
			} else {
				exitFileChoose(valueEvent);
			}
		} else {
			this.path.remove(this.path.size() - 1);
			setListAdapter(new FileArrayAdapter(this, listaDeArchivos()));
		}

	}

	public String[] listaDeArchivos() {
		
		int sum;
		File[] listOfFile;
		List<String> filteredFiles;
		String files[];
		String path = createUrl();
		
		listOfFile = new File(path).listFiles();
		filteredFiles = new ArrayList<String>();

		sum = this.path.size() != 1 ? (byte) 1 : 0;
		path = path.substring(0, path.length() - 1);


		for (File f : listOfFile) {
			String titulo = f.getName();
			if (f.isFile() && titulo.endsWith(filter))
				filteredFiles.add("0" + titulo);
			else if (f.isDirectory())
				filteredFiles.add("1" + titulo);
		}

		files = new String[(filteredFiles.size() + sum)];
		files[0] = "2" + this.path.get(this.path.size() - 1);

		for (int i = 0; i < filteredFiles.size(); i++)
			files[i + sum] = filteredFiles.get(i);

		return files;
	}

	public String createUrl() {
		String path = "";
		for (String directory : this.path)
			path += directory + "/";
		return path;
	}

	public void exitFileChoose(String file) {
		String address = createUrl() + file.substring(1);
		setResult(1, new Intent().putExtra("result", address));
		finish();
	}

}