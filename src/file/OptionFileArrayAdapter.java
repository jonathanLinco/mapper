package file;

import com.example.mapper.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class OptionFileArrayAdapter extends ArrayAdapter<String> {
	private Context context;
	private String[] itemValues;
	private String[] descriptionValues;
	
	public OptionFileArrayAdapter(Context context, String[] values,String[] descriptionValues) {
		super(context, R.layout.lista_file, values);
		this.context = context;
		this.itemValues = values;
		this.descriptionValues=descriptionValues;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.opciones_abrir, parent, false);
		TextView item = (TextView) rowView.findViewById(R.id.item);
		TextView descripcion = (TextView) rowView.findViewById(R.id.desc);
		//ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		item.setText(this.itemValues[position]);
		descripcion.setText(this.descriptionValues[position]);
		
		return rowView;
	}

}
