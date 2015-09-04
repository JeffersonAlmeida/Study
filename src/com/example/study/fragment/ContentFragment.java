package com.example.study.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.study.R;

public class ContentFragment extends Fragment {
	
	private static final String LOG = "Fragment";
	
	private TextView contentText;
	private String content;
	private Listener listener;
	
	public interface Listener {
		void fragmentCreated();
	}
	
	public ContentFragment(){}
	
	@Override
	public void onAttach(final Activity activity) {
		super.onAttach(activity);
		Log.v(LOG, "onAttach");
		if (! (activity instanceof Listener) )
			throw new IllegalStateException("Activity must implement fragment's listener");
		this.listener = (Listener) activity;
	}
	
	public static ContentFragment newInstance(final String content) {
		final ContentFragment fragment = new ContentFragment();
		final Bundle args = new Bundle();
		args.putString("content", content);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onCreate(final Bundle saved) {
		super.onCreate(saved);
		this.content = this.getArguments().getString("content", "");
	}
	
	@Override
	public View onCreateView
	(final LayoutInflater inflater, final ViewGroup root, final Bundle saved) {
		Log.v(LOG, "onCreateView");
		final View layout = inflater.inflate(R.layout.content_fragment, root, false );
		this.contentText = (TextView) layout.findViewById(R.id.content_id);
		this.contentText.setText(this.content);
		return layout;
	}
	
	@Override
	public void onViewCreated(final View layout, final Bundle savedInstanceState) {
		super.onViewCreated(layout, savedInstanceState);
		Log.v(LOG, "onViewCreated");
	}
	
	
	@Override
	public void onActivityCreated(final Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.v(LOG, "onActivityCreated");
		this.listener.fragmentCreated();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.v(LOG, "onStart");
	}
	
	@Override
	public void onSaveInstanceState(final Bundle outState) {
		Log.v(LOG, "onSaveInstanceState");
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.v(LOG, "onPause");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.v(LOG, "onResume");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.v(LOG, "onStop");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v(LOG, "onDestroy");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.v(LOG, "onDestroyView");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		Log.v(LOG, "onDetach");
	}
	
	public void updateContent(final String content){
		this.content = content;
		this.contentText.setText(this.content);
	}

}
