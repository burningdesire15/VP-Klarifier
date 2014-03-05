package com.example.draweractivity;

import java.util.Arrays;


import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.android.Facebook;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.ProfilePictureView;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment {

	private static final String TAG = "MainFragment";
	private UiLifecycleHelper uiHelper;
	private Button publishButton;
	ProfilePictureView userImage;
	TextView welcomeTextView;
	
	protected static final String ApiKey = "wm6ajsapmpbctbntkdxusv5h";
	GridView gridview1;
	Button btn1,btn2,btn3;
	TextView textview1,textview2;
	ImageView image1;
	 private Facebook facebook;

	  private SharedPreferences mPrefs,rssprefs;
	  private String appid="136005446607432";
	  String rssitem;
	
	MainFragment mainFragment;
	public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;
    public  int curstatus;
    Button bt,bt2,bt3;
	@Override
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.homescreen, container, false);
	    //for user name and profilepic
	    userImage = (ProfilePictureView) view.findViewById(R.id.userImage);
	  //  welcomeTextView = (TextView)view.findViewById(R.id.welcomeTextView);
	    personalizeHomeFragment();
	    LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	    authButton.setFragment(this);
	    bt=(Button) view.findViewById(R.id.bLogin);
		bt2=(Button) view.findViewById(R.id.button2);
		bt3=(Button) view.findViewById(R.id.bContinue);
		Typeface tf=Typeface.createFromAsset(getActivity().getAssets(),"fonts/Imprima-Regular.ttf");
		bt.setTypeface(tf);
		bt2.setTypeface(tf);
		bt3.setTypeface(tf);
	
	    authButton.setReadPermissions(Arrays.asList("user_likes", "user_status"));
	  //  publishButton = (Button) view.findViewById(R.id.publishButton);
	   
			
			facebook=new Facebook(appid);
			rssprefs=getActivity().getSharedPreferences("rssitems", Context.MODE_PRIVATE);
			//mPrefs=this.getSharedPreferences("Facebook", Context.MODE_PRIVATE);
			rssitem=rssprefs.getString("rssitems","");
	
	    
	    return view;
	}
	static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
 
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;
             
            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }	
	String getConnectivityStatusString(Context context) {
        int conn = MainFragment.getConnectivityStatus(context);
        String status = null;
        if (conn == MainFragment.TYPE_WIFI) {
            status = "Wifi enabled";
        } else if (conn == MainFragment.TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == MainFragment.TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
    }
	public void personalizeHomeFragment() {
		
	/*
	if (application.getCurrentFBUser() != null) {
	    userImage.setProfileId(application.getCurrentFBUser().getId());
	    userImage.setCropped(true);
	    welcomeTextView.setText("Welcome, " + application.getCurrentFBUser().getFirstName());
	}
	*/
	}
	private void onSessionStateChange(com.facebook.Session session, SessionState state, Exception exception) {
	    if (state.isOpened()) {
	      
	   //     publishButton.setVisibility(View.VISIBLE);
	        com.facebook.Request.executeMeRequestAsync(session, new com.facebook.Request.GraphUserCallback() {
				
				@Override
			public void onCompleted(GraphUser user, Response response) {
					// TODO Auto-generated method stub
					//callback after graph api response with user object
					if(user!=null){
						
						
						userImage.setProfileId(user.getId());
						bt.setText("Welcome! " + user.getFirstName());
					//	textview2.setText(user.getBirthday());
						

					}
				}
			});
	    } else if (state.isClosed()) {
	      
	     //   publishButton.setVisibility(View.INVISIBLE);
	        userImage.setProfileId(null);
	    }
	}
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	}
	@Override
	public void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
	
	private void publishFeedDialog() {
	    Bundle params = new Bundle();
	    params.putString("name", "MyeNews Share dialog");
	    params.putString("caption", "What's in your mind? ");
	    params.putString("description", "Now it is easy to share");
	    
	    params.putString("picture", "https://raw.github.com/fbsamples/ios-3.x-howtos/master/Images/iossdk_logo.png");

	    WebDialog feedDialog = (
	        new WebDialog.FeedDialogBuilder(getActivity(),
	            Session.getActiveSession(),
	            params))
	        .setOnCompleteListener(new OnCompleteListener() {

	            @Override
	            public void onComplete(Bundle values,
	                FacebookException error) {
	                if (error == null) {
	                    // When the story is posted, echo the success
	                    // and the post Id.
	                    final String postId = values.getString("post_id");
	                    if (postId != null) {
	                        Toast.makeText(getActivity(),
	                            "Posted story, id: "+postId,
	                            Toast.LENGTH_SHORT).show();
	                    } else {
	                        // User clicked the Cancel button
	                        Toast.makeText(getActivity().getApplicationContext(), 
	                            "Publish cancelled", 
	                            Toast.LENGTH_SHORT).show();
	                    }
	                } else if (error instanceof FacebookOperationCanceledException) {
	                    // User clicked the "x" button
	                    Toast.makeText(getActivity().getApplicationContext(), 
	                        "Publish cancelled", 
	                        Toast.LENGTH_SHORT).show();
	                } else {
	                    // Generic, ex: network error
	                    Toast.makeText(getActivity().getApplicationContext(), 
	                        "Error posting story", 
	                        Toast.LENGTH_SHORT).show();
	                }
	            }

				
	        })
	        .build();
	    feedDialog.show();
	}
}
