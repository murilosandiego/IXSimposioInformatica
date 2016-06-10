package br.edu.ifnmg.ixsimposioinformatica.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


import br.edu.ifnmg.ixsimposioinformatica.R;
import br.edu.ifnmg.ixsimposioinformatica.activity.NoticiaDetalhadaCertaActivity;
import br.edu.ifnmg.ixsimposioinformatica.adapter.NoticiasAdapter;
import br.edu.ifnmg.ixsimposioinformatica.domain.Noticia;

public class NoticiasFragment extends Fragment {

    private static final String JSON_URL = "http://murilosandiego.96.lt/noticias.json";
    JSONArray noticias = null;

    private ArrayList<Noticia> listaDeNoticias;


    public NoticiasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.content_noticias, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.ltvNoticias);
        listView.setAdapter(new NoticiasAdapter(getActivity(), listaDeNoticias));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), NoticiaDetalhadaCertaActivity.class);
                intent.putExtra("noticia", listaDeNoticias.get(position));
                startActivity(intent);
            }
        });
        //  setRetainInstance(true);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("LOG", "onCreate");
        Fresco.initialize(getActivity());
        if (savedInstanceState != null) {
            listaDeNoticias = savedInstanceState.getParcelableArrayList("listaDeNoticias");
        } else {
            listaDeNoticias = new ArrayList<>();
            if (isOnline(getActivity())) {
                this.getJSON(JSON_URL);
                this.lerDadosCache();
            } else if (lerDadosCache() != null) {
                this.lerDadosCache();
            } else {
                Toast.makeText(getActivity(), "Não foi possível abrir as notícas, pois não ha conexão com a internet", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("LOG", "onActivityCreatedNoticias");
        //Executando Json
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected())
            return true;
        else
            return false;
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    public void extrairJson(String strJsonRecuperada) {
        Log.e("LOG", "extrairJson");
        if (strJsonRecuperada != null) {
            JSONObject jsonObj = null;
            try {

                jsonObj = new JSONObject(strJsonRecuperada);
                noticias = jsonObj.getJSONArray("noticias");

                for (int i = 0; i < noticias.length(); i++) {
                    JSONObject noticia = noticias.getJSONObject(i);

                    String id = noticia.getString("id");
                    String urlImg = noticia.getString("urlImg");
                    String titulo = noticia.getString("titulo");
                    String descricao = noticia.getString("descricao");
                    String conteudo = noticia.getString("conteudo");

                    Noticia objetoNoticia = new Noticia();

                    objetoNoticia.setConteudo(conteudo);
                    objetoNoticia.setDescricao(descricao);
                    objetoNoticia.setTitulo(titulo);
                    objetoNoticia.setUrlNoticia(urlImg);
                    objetoNoticia.setId(id);

                    listaDeNoticias.add(objetoNoticia);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    public void gravarJsonCache(String strJson) {
        // Instantiate a JSON object from the request response
        Log.e("LOG", "gravarJsonCache");
        if (strJson != null) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(strJson);
                String jsonObjectSerialized = jsonObject.toString();
                ObjectOutput out = new ObjectOutputStream(new FileOutputStream(new File(getActivity().getCacheDir(), "") + "cacheFile.srl"));
                out.writeObject(jsonObjectSerialized);
                out.close();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String lerJsonCache() {
        Log.e("LOG", "lerJsonCache");
        String jsonObjectString = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(new File(getActivity().getCacheDir(), "") + "cacheFile.srl")));
            jsonObjectString = (String) in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

//        if (jsonObjectString == null) {
//            Toast.makeText(getActivity(), "Não foi possível abrir as notícas, pois não ha conexão com a internet", Toast.LENGTH_LONG).show();
//            return null;
//        } else {
        return jsonObjectString;
//        }
    }

    public String lerDadosCache() {
        Log.e("LOG", "lerDadosCache");
        String strJsonCache = null;
        strJsonCache = lerJsonCache();

        if (strJsonCache != null) {
            extrairJson(strJsonCache);
            return strJsonCache;
        } else {
            return null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("listaDeNoticias", (ArrayList<Noticia>) listaDeNoticias);
    }


    private void getJSON(String url) {
        class GetJSON extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Por favor espere...", null, true, true);
            }

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                gravarJsonCache(s);
                loading.dismiss();
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute(url);
    }
}
