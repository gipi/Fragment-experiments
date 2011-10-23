package org.ktln2.android;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;


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
		@Override
		public void onActivityCreated(Bundle b) {
			super.onActivityCreated(b);

			setListAdapter(
				new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_activated_1,
					new String[] {
						"miao",
						"bau",
					}
				)
			);
		}
	}

}
