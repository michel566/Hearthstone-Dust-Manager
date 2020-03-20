package com.michelbarbosa.hsdm_hearthstonedustmanager.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.michelbarbosa.hsdm_hearthstonedustmanager.R;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.SetWeight;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.response.InfoResponse;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.response.SingleCardResponse;
import com.michelbarbosa.hsdm_hearthstonedustmanager.presenters.HearthstoneContracts;
import com.michelbarbosa.hsdm_hearthstonedustmanager.presenters.HearthstonePresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

//todo: remover após testes antes de colocar em produção
public class ShowCardsActivity extends MainActivity implements HearthstoneContracts.presenterView.loadSingleCard, HearthstoneContracts.presenterView.loadInfo {

    protected HearthstoneContracts.IHearthstonePresenter singleCardPresenter = new HearthstonePresenter((loadSingleCard) this);
    protected HearthstoneContracts.IHearthstonePresenter loadInfoPresenter = new HearthstonePresenter((loadInfo)this);

    private EditText edApi;
    private TextView tvResults;
    private ImageView imCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutContent(R.layout.activity_showcards);
        setToolbarTitle(R.string.title_testes);
        edApi = findViewById(R.id.edAPI);
        Button btResult = findViewById(R.id.btResult);
        tvResults = findViewById(R.id.tvResults);
        imCard = findViewById(R.id.imCard);

        btResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResults.setText("");
                String input = edApi.getText().toString();

                //Uncomment and comment to get a test each api
                singleCardPresenter.getSingleCard(ShowCardsActivity.this, input);
               // loadInfoPresenter.getInfo(ShowCardsActivity.this);
            }
        });
    }

    @Override
    public void successOnLoadSingleCard(final SingleCardResponse response) {
        tvResults.setTextColor(getResources().getColor(R.color.colorPrimary));
        tvResults.setText(response.getText());
        final String contingencyUrl = "https://art.hearthstonejson.com/v1/render/latest/enUS/512x/";
        final String formatType = ".png";
        Picasso.get().load(contingencyUrl + response.getCardId() + formatType).into(imCard);

      /*
        Picasso.get().load(convertStringUrlToHTTPS(response.getImg())).into(imCard, new Callback() {
            @Override
            public void successOnLoadSingleCard() {
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(MainActivity.this, "erro: "+ response.getCardId(), Toast.LENGTH_SHORT).show();
                Picasso.get().load(contingencyUrl + response.getCardId() + formatType).into(imCard);
            }
        });
        Log.e("link: ", convertStringUrlToHTTPS(response.getImg()));
        Log.e("link: ", contingencyUrl + response.getCardId() + formatType);

       */
    }

    @Override
    public void failureOnLoadSingleCard(String messageFailure) {
        tvResults.setTextColor(getResources().getColor(R.color.colorErrorDialog));
        tvResults.setText(messageFailure);
    }

    @Override
    public void successOnLoadInfo(List<SetWeight> listSetWeight) {

      /*  tvResults.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        tvResults.setText(response.getClasses().get(0));

       */
    }

    @Override
    public void failureOnLoadInfo(String messageFailure) {
        tvResults.setTextColor(getResources().getColor(R.color.colorErrorDialog));
        tvResults.setText(messageFailure);
    }

    private static String convertStringUrlToHTTPS(String hsUrl) {
        return hsUrl.replace("http", "https");
    }

}
