package file;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.view.View;

public class ListOptionFileActivity extends ListActivity {

	String[] item = { "Poligon(DKF)", "Poliline(DXF)", "Surface(DXF)","Modelos(CSU)" };
	String[] descValues = { "descrip", "descrip2", "descrip3", "descrip4" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new OptionFileArrayAdapter(this, item, descValues));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent(this, ListFileActivity.class);
		i.putExtra("filtro", (id == 3 ? ".csv" : ".dxf"));
		startActivityForResult(i, 0);

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 1) {
			String result = data.getStringExtra("result");
			exitFileChoose(result);
		}
	}

	public void exitFileChoose(String result) {
		setResult(1, new Intent().putExtra("result", result));
		finish();
	}

}