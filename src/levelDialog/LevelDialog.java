package levelDialog;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;

public class LevelDialog extends Builder implements OnMultiChoiceClickListener,
		OnClickListener {

	private CharSequence[] items;
	private boolean state[];
	private boolean initialState[];

	public LevelDialog(Context arg, CharSequence[] items, boolean state[]) {

		super(arg);
		this.items = items;
		this.state = state;
		this.initialState = new boolean[this.state.length];

		System.arraycopy(state, 0, this.initialState, 0, state.length);

		setTitle("seleccione niveles");
		setMultiChoiceItems(items, state, this);
		setNegativeButton("Cancel", this);
		setPositiveButton("OK", this);
		show();

	}

	@Override
	public void onClick(DialogInterface dialog, int which, boolean isChecked) {
		this.state[which] = isChecked;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {

		Intent intent = new Intent();
		intent.putExtra("result", "anda la osa");
		if (dialog.BUTTON_POSITIVE == which)
			System.arraycopy(this.state, 0, this.initialState, 0,
					this.state.length);

	}

}
