package ar.com.maxwell.android_warehouse.network;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.annotation.Nullable;
import ar.com.maxwell.android_warehouse.BaseActivity;
import ar.com.maxwell.android_warehouse.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatPhotoActivity extends BaseActivity {
    ImageView ivCatPicture;
    private final String catError = "No fue posible traerte gatitos :(";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_photo);

        ivCatPicture = findViewById(R.id.ivCatPicture);

        findViewById(R.id.btCatOtherPicture).setOnClickListener(view -> setCatPhoto());
    }

    private void setCatPhoto() {
        showProgress("Cargando Gatitos!");

        MeowLoader.getApi().getCatPicture().enqueue(new Callback<Cat>() {
            @Override
            public void onResponse(Call<Cat> call, Response<Cat> response) {
                hideProgress();

                if(response.isSuccessful() && response.body().getFile() != null){
                    Glide.with(CatPhotoActivity.this)
                            .load(response.body().getFile())
                            .into(ivCatPicture);
                } else {
                    showOKDialog(catError);
                }

            }

            @Override
            public void onFailure(Call<Cat> call, Throwable t) {
                hideProgress();
                showOKDialog(catError);
            }
        });
    }
}
