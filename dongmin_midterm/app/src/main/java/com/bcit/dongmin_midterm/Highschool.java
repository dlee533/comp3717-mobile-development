package com.bcit.dongmin_midterm;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Highschool extends Fragment {

    public Highschool() {
        super(R.layout.fragment_highschool);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebView webView = getActivity().findViewById(R.id.webView_highschool_website);

        MyWebViewClient myWebViewClient = new MyWebViewClient();
        webView.setWebViewClient(myWebViewClient);
//        webView.loadUrl("https://wjmouat.abbyschools.ca/"); // website breaks too much
        webView.loadUrl("https://bcit.ca/");
    }
}
