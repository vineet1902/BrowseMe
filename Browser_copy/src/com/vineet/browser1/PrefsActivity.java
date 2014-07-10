package com.vineet.browser1;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class PrefsActivity extends PreferenceActivity{
	 
@Override
protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   addPreferencesFromResource(R.layout.prefs);
   Toast.makeText(getApplicationContext(), "Changes will be applied the next time you run browser.",
		   Toast.LENGTH_LONG).show();
}

@Override
protected void onResume(){
	super.onResume();
	Toast.makeText(getApplicationContext(), "Changes will be applied the next time you run browser.",
			   Toast.LENGTH_LONG).show();
}
}
