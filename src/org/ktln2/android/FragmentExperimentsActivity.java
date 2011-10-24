package org.ktln2.android;

import android.app.Activity;
import android.app.ListFragment;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.res.Configuration;


public class FragmentExperimentsActivity extends Activity {
	public static String[] itemTitleArray = new String[] {
		"Item1",
		"Item2",
		"Item3",
		"Item4",
		"Item5",
	};

	public static String[] itemContentArray = new String[] {
		"text 1",
		"text 2",
		"text 3",
		"text 4",
		"text 5",
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	/**
	 * Since we have the detail in the same activity of the list
	 * if we pressed back we exit from the application instead of
	 * return to the list of items.
	 *
	 *  http://android-developers.blogspot.com/2009/12/back-and-other-hard-keys-three-stories.html
	 */
	@Override
	public void onBackPressed() {
		ViewSwitcher vs = (ViewSwitcher)findViewById(R.id.switchView);

		if (vs != null) {
			vs.showNext();
		} else {
			super.onBackPressed();
		}

		return;
	}

	/**
	 * The aim of this class is to show the items.
	 */
	static public class ItemsFragment extends ListFragment {
		private boolean mIsPaned;
		private boolean mIsThereSwitch;
		private ViewSwitcher mViewSwitcher;
		private int mCurrentCheckedIndex = 0;

		@Override
		public void onActivityCreated(Bundle b) {
			super.onActivityCreated(b);

			// use the layout to understand if the
			// application is multi paned
			View fragmentContainer = getActivity().findViewById(R.id.fragment_detail_container);
			mViewSwitcher = (ViewSwitcher)getActivity().findViewById(R.id.switchView);

			mIsPaned = fragmentContainer == null ? false : true;
			mIsThereSwitch = mViewSwitcher == null ? false : true;

			setListAdapter(
				new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_activated_1,
					itemTitleArray
				)
			);

			if (!mIsThereSwitch) {
				showDetail(mCurrentCheckedIndex);
			}
		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			showDetail(position);
		}

		private void showDetail(int index) {
			android.util.Log.i("FRAGMENT", "index: " + index);
			if (mIsPaned) {
				// TODO: is the best place for this?
				mCurrentCheckedIndex = index;

				DetailFragment df = DetailFragment.getNewInstance(index);
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				// replaces the fragment
				// NB: the resource id is the container of fragment one
				ft.replace(R.id.fragment_detail_container, df);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			} else {
			}

			if (mIsThereSwitch) {
				mViewSwitcher.showNext();
			}
		}
	}

	/**
	 * This will show the details.
	 */
	public static class DetailFragment extends Fragment {
		/**
		 * Seems that Fragment work like singleton, using a public constructor
		 * an empty argument one.
		 */
		public static DetailFragment getNewInstance(int index) {
			DetailFragment df = new DetailFragment();

			Bundle args = new Bundle();
			args.putInt("index", index);
			df.setArguments(args);

			return df;
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.detail, null);

			TextView tv = (TextView)v.findViewById(R.id.detail_text);

			android.util.Log.i("FRAGMENT", "v: " + v + "tv: " + tv);

			/**
			 * The first time is called when inflating from the XML
			 * and there is not arguments passed.
			 */
			Bundle args = getArguments();
			int index = (args != null ? args.getInt("index", 0) : 0);

			tv.setText(itemContentArray[index]);

			return v;
		}
	}
}
