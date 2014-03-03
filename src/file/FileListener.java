package file;

import com.example.mapper.MainActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class FileListener implements OnClickListener  {
	public MainActivity main;

	public FileListener(MainActivity main) {
		this.main = main;
	}

	@Override
	public void onClick(View view) {	
		Intent i = new Intent(this.main, ListOptionFileActivity.class);
		this.main.startActivityForResult(i, 0);
	}

}
