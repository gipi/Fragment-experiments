package org.ktln2.android;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;

public class FragmentExperimentsActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	/**
	 * The aim of this class is to show the details related to
	 * the item chosen from the list.
	 */
	static public class DettaglioActivity extends Activity {
	}

	/**
	 * The aim of this class is to show the items.
	 */
	static public class ItemsFragment extends ListFragment {
	}

}
