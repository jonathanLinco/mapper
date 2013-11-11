package fileChose;

import com.example.mapper.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileArrayAdapter extends ArrayAdapter<String> {
	private Context context;
	private String[] values;

	public FileArrayAdapter(Context context, String[] values) {
		super(context, R.layout.lista_file, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.lista_file, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values[position].substring(1));

		String s = values[position];
		if (s.charAt(0) == '1')
			imageView.setImageResource(R.drawable.carpeta);
		else if (s.charAt(0) == '2')
			imageView.setImageResource(R.drawable.volver);
		else
			imageView.setImageResource(R.drawable.file);

		return rowView;
	}

}
