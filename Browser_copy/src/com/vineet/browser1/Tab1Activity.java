package com.vineet.browser1;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.SslError;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


@SuppressWarnings("deprecation")
public class Tab1Activity extends Activity{
	static int login = 0;
	Thread thread;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        //Enabling Javascript
        webSettings.setJavaScriptEnabled(true);
        SharedPreferences prefs = PreferenceManager
        	    .getDefaultSharedPreferences(Tab1Activity.this);

        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
            @Override
            //For automatic Login
            public void onPageFinished(WebView view, String url) 
            {
                SharedPreferences prefs = PreferenceManager
                	    .getDefaultSharedPreferences(Tab1Activity.this);

            	String user = prefs.getString("t1_user", "");
            	String pass = prefs.getString("t1_pass", "");
                // Obvious next step is: document.forms[0].submit()
               // view.loadUrl("javascript:{var f = document.getElementById('fm1');f.submit();};");
            	//String s = "fm1";
                view.loadUrl("javascript:{var f = document.getElementById('loginform');f.j_username.value = '"+user+"';f.j_password.value = '"+pass+"';};");
                view.loadUrl("javascript:{var f = document.getElementById('loginform');f.submit();};");
//                Tab1Activity.this.progress.setProgress(0);
               // view.loadUrl("javascript:{var f = document.getElementById('login_form');f.email.value = 'vineet1902@gmail.com';f.pass.value = '!vineet!';};");
             //   view.loadUrl("javascript:{var f = document.getElementById('login_form');f.submit();};");
            }
            //Automatic login
            
        });
        
        //loading the default url as provided in the settings page
        String tab_text = prefs.getString("t1_name", "Default Tab");
        String url = prefs.getString("t1_url", "testing.novopay.in");
        
        final MainActivity activity = (MainActivity) this.getParent();
    	final TabHost host = activity.getTabHost();
    	TextView tv = (TextView) host.getTabWidget().getChildAt (0).findViewById (android.R.id.title);
    	tv.setText(tab_text);
        myWebView.loadUrl("http://"+url);
                
	}
	
	/*@Override
    protected void onResume() {
		super.onResume();

        SharedPreferences prefs = PreferenceManager
        	    .getDefaultSharedPreferences(Tab1Activity.this);
        String tab_text = prefs.getString("t1_name", "Default Tab");
        String url = prefs.getString("t1_url", "live.novopay.in");
        
        MainActivity activity = (MainActivity) this.getParent();
    	TabHost host = activity.getTabHost();
    	TextView tv = (TextView) host.getTabWidget().getChildAt(0).findViewById (android.R.id.title);
    	tv.setText(tab_text);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://"+url);
//        String user = prefs.getString("t1_user", "novopay");
  //  	String pass = prefs.getString("t1_pass", "khoslalabs");
        // Obvious next step is: document.forms[0].submit()
       // view.loadUrl("javascript:{var f = document.getElementById('fm1');f.submit();};");
    	//String s = "fm1";
  //      myWebView.loadUrl("javascript:{var f = document.getElementById('fm1');f.j_username.value = '"+user+"';f.j_password.value = '"+pass+"';};");
    //    myWebView.loadUrl("javascript:{var f = document.getElementById('fm1');f.submit();};");
	}*/
    
    @Override
    //To store history stack
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	WebView myWebView = (WebView) findViewById(R.id.webview);
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    //For devices with a hardware menu button.
    public boolean onOptionsItemSelected(MenuItem item) {
    	MainActivity activity = (MainActivity) this.getParent();
    	final TabHost host = activity.getTabHost();
        switch (item.getItemId()) {
        case R.id.url:
        	 Intent intent = new Intent(Tab1Activity.this,
        		      PrefsActivity.class);
        		      startActivity(intent);
            return true;
        case R.id.hidetab:
        	host.getTabWidget().setVisibility(View.GONE);
        	return true;
        case R.id.showtab:
        	host.getTabWidget().setVisibility(View.VISIBLE);
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    
    

}
