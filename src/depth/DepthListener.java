package depth;

import levelDialog.LevelDialog;
import com.example.mapper.MainActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class DepthListener implements OnClickListener {

	public MainActivity main;

	public DepthListener(MainActivity main) {
		this.main = main;
	}

	@Override
	public void onClick(View view) {
		final CharSequence[] items = { "Nivel 1", "Nivel 2", "Nivel 3" };
		final boolean[] estados = { true, true, true };
		new LevelDialog(this.main, items, estados);

	}

}
