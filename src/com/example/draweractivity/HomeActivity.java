package com.example.draweractivity;

import java.util.ArrayList;

import android.annotation.SuppressLint;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuInflater;





public class HomeActivity extends SherlockFragmentActivity 
{
	 private DrawerLayout mDrawerLayout;
	    private ListView mDrawerList;
	    private ActionBarDrawerToggle mDrawerToggle;
	    private ArrayAdapter<String> navAdapter;
	    private String[] navMenuItems;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mDrawerList=(ListView)findViewById(R.id.list_slidermenu);
		
		navMenuItems=getResources().getStringArray(R.array.nav_drawer_items);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		navAdapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,navMenuItems);
		mDrawerList.setAdapter(navAdapter);		
		/*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
	     getSupportActionBar().setHomeButtonEnabled(true);*/
		
		/* Toggle Button at top */
		 mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
	                R.drawable.ic_menu, //nav menu toggle icon
	                R.string.app_name, // nav drawer open - description for accessibility
	                R.string.app_name // nav drawer close - description for accessibility
	        ){
	            public void onDrawerClosed(View view) {
	            	
	                getSupportActionBar().setTitle("String");
	                // calling onPrepareOptionsMenu() to show action bar icons
	                invalidateOptionsMenu();
	            }
	 
	            public void onDrawerOpened(View drawerView) 
	            {
	            	//Log.e("onDrawer_Opened","mDrawerTitle "+mDrawerTitle);
	                getSupportActionBar().setTitle("Main");
	                // calling onPrepareOptionsMenu() to hide action bar icons
	               invalidateOptionsMenu();
	            }
	        };
	        mDrawerLayout.setDrawerListener(mDrawerToggle);
	        
	        
	        if (savedInstanceState == null) {
	            // on first time display view for first nav item
	            displayView(0);
	        }
	        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
	    }
	    private class SlideMenuClickListener implements ListView.OnItemClickListener 
	    {	
	    		@Override
	    		public void onItemClick(AdapterView<?> parent, View view, int position,long id) 
	    		{
	    // display view for selected nav drawer item
	    			displayView(position);
	    		}
	    } 
		
	   
	
	@Override
		public boolean onPrepareOptionsMenu(com.actionbarsherlock.view.Menu menu) {
			// TODO Auto-generated method stub
		// if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
		}

	 @SuppressLint("NewApi")
	private void displayView(int position) {
	        // update the main content by replacing fragments
	        android.support.v4.app.Fragment fragment = null;
	        switch (position) {
	        case 0:
	            fragment = new HomeFragment();
	            break;
	        case 1:
	            fragment = new ProfileFragment();
	            break;
	        case 2:
	            fragment = new PostQuestionFragment();
	            break;
	        case 3:
	            fragment = new ProfileFragment();
	            break;
	        case 4:
	            fragment = new HomeFragment();
	            break;
	        case 5:
	            fragment = new ProfileFragment();
	            break;
	 
	        default:
	            break;
	        }
	 
	        if (fragment != null) {
	            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
	            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
	 
	            // update selected item and title, then close the drawer
	            mDrawerList.setItemChecked(position, true);
	            mDrawerList.setSelection(position);
	            
	            mDrawerLayout.closeDrawer(mDrawerList);
	        } else {
	            // error in creating fragment
	            Log.e("MainActivity", "Error in creating fragment");
	        }
	    }



	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) 
	{
		//TODO Auto-generated method stub
		
		MenuInflater inflater = getSupportMenuInflater();
		   inflater.inflate(R.menu.main, menu);
		  getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#186CAE"))); 
		return true;
	}
	
	
	


}
