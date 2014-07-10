package com.vineet.browser1;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //To keep the screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        
        
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabSpec tab3 = tabHost.newTabSpec("Third tab");
        TabSpec tab4 = tabHost.newTabSpec("Fourth Tab");
        TabSpec tab5 = tabHost.newTabSpec("Fifth Tab");

       // Set the Tab name and Activity
       // that will be opened when particular Tab will be selected
        SharedPreferences prefs = PreferenceManager
        	    .getDefaultSharedPreferences(MainActivity.this);

        //Setting tab name and content for 1st tab
        String tab_text = prefs.getString("t1_name", "Default Tab");
        tab1.setIndicator(tab_text);
        tab1.setContent(new Intent(this,Tab1Activity.class));
 
        //Setting tab name and content for 2nd tab
        tab_text = prefs.getString("t2_name", "Default Tab");
        tab2.setIndicator(tab_text);
        tab2.setContent(new Intent(this,Tab2Activity.class));

        //Setting tab name and content for 3rd tab
        tab_text = prefs.getString("t3_name", "Default Tab");
        tab3.setIndicator(tab_text);
        tab3.setContent(new Intent(this,Tab3Activity.class));
        
        //Setting tab name and content for 4th tab
        tab_text = prefs.getString("t4_name", "Default Tab");
        tab4.setIndicator(tab_text);
        tab4.setContent(new Intent(this,Tab4Activity.class));
        //Setting tab name and content for settings tab
        tab5.setIndicator("Settings");
        tab5.setContent(new Intent(this,PrefsActivity.class));
        
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        tabHost.addTab(tab4);
        tabHost.addTab(tab5);
        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++){
        	tabHost.getTabWidget().getChildAt(i).getLayoutParams().height = 80;
	     }
        
        
        
        //Thread for circling through the tabs
		Thread thread =new Thread()
    	{
			final TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
    		int tab =0;
    		@Override
    		public void run()
    		{	
    	    	while(true)
    			{	
    				try {
						Thread.sleep(20000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        		runOnUiThread(new Runnable(){
	        		    @Override
	        		    public void run(){
	                        	tabHost.setCurrentTab(tab++);
                            	tab = tab%4;
            		    }
	        		});
    			}
	        }
	      };
	      thread.start();
        //Thread
	      	      
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
}
