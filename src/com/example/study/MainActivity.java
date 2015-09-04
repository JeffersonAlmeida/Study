package com.example.study;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.study.data.Content;
import com.example.study.data.Content.Item;
import com.example.study.fragment.ContentFragment;

public class MainActivity extends Activity
implements ContentFragment.Listener {

	private static final String LOG = "MainActivity";
	
	private boolean tablet;
	private ListView list;
	private ContentFragment fragment;
	private String content;
	
	@Override
	protected void onCreate(final Bundle saved) {
		super.onCreate(saved);
		Log.e(LOG, "onCreate");
		this.setContentView(R.layout.activity_main);
		this.list = (ListView) this.findViewById(R.id.list);
		this.list.setAdapter(new ArrayAdapter<Item>
		(this,  R.layout.layout_list_item, R.id.text_id, Content.items));
		this.list.setOnItemClickListener(new OnItemClick());
		this.list.setActivated(true);
		
		tablet = this.findViewById(R.id.fragment) != null;
		if ( tablet ) this.createFragment(saved);

	}

	private void createFragment(final Bundle saved) {
		this.content = ( saved != null ) ? saved.getString("content") : "" ;
		this.fragment = ContentFragment.newInstance(content);
		final FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment, this.fragment, "content");
		transaction.commit();
	}
    
    private class OnItemClick implements OnItemClickListener {
    	@Override
		public void onItemClick(final AdapterView<?> av, final View v, final int p, final long id) {
			final Item item = (Item) list.getItemAtPosition(p);
			MainActivity.this.itemClicked(item);
		}
    }
    
    public void itemClicked (final Item item){
    	this.content = item.getContent();
    	this.showContent(this.content);
    }
    
    public void showContent (final String content){
    	if (tablet)
    		this.updateFragmentsContent(content);
    	else
    		this.showContentInOtherActivity(content);
    }

	private void showContentInOtherActivity(final String content) {
		final Intent intent = new Intent(this, ContentActivity.class);
		intent.putExtra("content", content);
		this.startActivity(intent);
	}

	private void updateFragmentsContent(final String content) {
		fragment.updateContent(content);
	}
    
    @Override
    protected void onPause() {
    	super.onPause();
    	Log.e(LOG, "onPause");
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();
    	Log.e(LOG, "onRestart");
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	Log.e(LOG, "onResume");
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	Log.e(LOG, "onStop");
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	Log.e(LOG, "onDestroy");
    }
    
    @Override
    public View onCreateView(final String name, final Context context, final AttributeSet attrs) {
    	Log.e(LOG, "onCreateView-1");
    	return super.onCreateView(name, context, attrs);
    }
    
    @Override
    public View onCreateView(final View parent, final String name, final Context context,	final AttributeSet attrs) {
    	Log.e(LOG, "onCreateView-2");
    	return super.onCreateView(parent, name, context, attrs);
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle outState) {
    	Log.e(LOG, "onSaveInstanceState");
    	outState.putString("content", this.content);
    	super.onSaveInstanceState(outState);
    }
    
	@Override
	public void fragmentCreated() {
		Log.d("MainActivity", "fragmentCreated");
	}
}
