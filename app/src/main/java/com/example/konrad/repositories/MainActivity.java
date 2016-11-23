package com.example.konrad.repositories;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RepositoriesAdapter.RepositoryClickAction{

    @BindView(R.id.activity_main)
    protected RecyclerView mRepoList;

    private RepositoriesAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    // Ustawiamy co ma pokazac sie na ekranie  na tym oknie  (Activity)
        ButterKnife.bind(this);

        mAdapter = new RepositoriesAdapter();
        //Mówimy adapterowi ze biezacy obiekt (this) reaguje na zdarzenie klikniecia
        mAdapter.setmClickListener(this);
        // Tworzymy przykladową listę obiektów do pokazania na ekranie
        List<GithubRepository> repos = new LinkedList<>();
        GithubRepository r1 = new GithubRepository();
        // Obiekt testowy 1
        r1.setName ("Repo 1");
        r1.setHtmlUrl ("http://www.wp.pl");
        repos.add(r1); //dodanie repo do kolekcji

        // Obiekt testowy 2
        r1 = new GithubRepository();
        r1.setName ("Repo 2");
        r1.setHtmlUrl ("http://www.filmweb.pl");
        repos.add(r1);
        mAdapter.setmData(repos);

        mRepoList = (RecyclerView) findViewById(R.id.activity_main);
        mRepoList.setLayoutManager(new LinearLayoutManager(this));
        mRepoList.setAdapter(mAdapter);

        GithubApi api = GithubApiFactory.getApi();
        api.listRepositories("Konrad7005").enqueue(new Callback<List<GithubRepository>>(){ // wspisujemy ksywke i zmieniamy octo

            @Override
            public void onResponse(retrofit2.Call<List<GithubRepository>> call,
                                   Response<List<GithubRepository>> response) {
            mAdapter.setmData(response.body());
            }

            @Override
            public void onFailure(retrofit2.Call<List<GithubRepository>> call, Throwable t) {

            }


        });
    }

    @Override
    public void onClick(GithubRepository repository) {
       Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(repository.getHtmlUrl()));
        startActivity(websiteIntent);

    }
}
