package com.example.study;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.example.study.fragment.ContentFragment;

public class ContentActivity extends Activity
implements ContentFragment.Listener {
	
	private ContentFragment fragment;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_content);
		
		final String content = this.getIntent().getExtras().getString("content");
		this.fragment = ContentFragment.newInstance(content);
				
		final FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
		transaction.replace(R.id.content_fragment, this.fragment, "content");
		transaction.commit();
	}

	@Override
	public void fragmentCreated() {
		Log.d("ContentFragment", "fragmentCreated");
	}
	
	
	/**
	1- How to get a fragment by tag ?
	
		final FragmentManager manager = this.getFragmentManager();
		this.fragment = (ContentFragment) manager.findFragmentByTag("content");
	*/

}
