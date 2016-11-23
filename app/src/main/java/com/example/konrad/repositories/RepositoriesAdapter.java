package com.example.konrad.repositories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Konrad on 2016-11-21.
 */

// Ta klasa odpowiada dla widoku listy (RecycylerView) na pytania :
    // - ile elementow ( getItemCount)
    // - jak maja wygladac ( on CreateViewHolder)
    // - jakie maja dane zawierac

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.RepositoryViewHolder>{

    // Zmienna w ktotrej trzymamy zbior obiektow, ktore chcemy wyswietlic na ekranie w postaci listy

    private List<GithubRepository> mData = Collections.emptyList();

    public void setmClickListener(RepositoryClickAction mClickListener) {
        this.mClickListener = mClickListener;
    }

    private RepositoryClickAction mClickListener;



    // W zwiazku z tym, ze mData jest prywatna - dodalismy metode setData, pozwalajaca na utawienie danych do wyswietlania

    public void setmData(List<GithubRepository> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    // Ta funkcja ma za zadanie stworzyc obiekt widoku pojedynczego wiersza, czyli odpowiedziec

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // LayoutInflater - komponent do tworzenia obiektow View na podstawie plikow XML
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Metoda inflate tworzy obiekt View na podstawie podanego pliku XML
        // Drugi jej parametr to kontener wzgledem ktorego ma wymirowac nowo tworzony widok
        //Trzeci parametr mowi czy chcemy nowo tworzony widok dodac od razu do parent
        View rowView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new RepositoryViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
            // pobierz dane z zadanej pozycji (parametr position)
        GithubRepository repository = mData.get(position);
        // uzupelnij widok wiersza ( parametr holder) danymi
        holder.mLabel.setText(repository.getName());
        holder.mRepository = repository;
    }

    @Override
    public int getItemCount() {
        return mData.size();

    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder{
        @BindView( android.R.id.text1 )
        TextView mLabel;
        GithubRepository mRepository;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            // Podpinamy sie pod klikniecie danego wiersza (widoku) na liscie / na ekanie w celu
            // poinfomowania mClickListenera o zdarzeniu.

        }
        @OnClick
        protected void onViewClick() {
            mClickListener.onClick(mRepository);
        }
    }

    // Ten interfejs definiuje sposob powiadamiania zainteresowanych z zewnatrz o kliknieciach na wiersze
    // reprezentuje ze zostal klikniety
    public interface RepositoryClickAction {        // shift+f6 wtedy zmieniamy calosc kazdy plik
        void onClick(GithubRepository repository);

    }
}