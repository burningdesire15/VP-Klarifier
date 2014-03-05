package com.example.draweractivity;


import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
@SuppressLint("NewApi")
public class PostQuestionFragment extends Fragment
{
     
    public PostQuestionFragment(){}
     
   
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.post_question_fragment, container, false);
          
        return rootView;
    }
}