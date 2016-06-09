package br.edu.ifnmg.ixsimposioinformatica.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import br.edu.ifnmg.ixsimposioinformatica.R;

/**
 * Created by murilo on 20/04/16.
 */
public class SobreFragment extends Fragment {

    WebView myWebView;

    public SobreFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sobre, container, false);

        return rootView;
    }

}
