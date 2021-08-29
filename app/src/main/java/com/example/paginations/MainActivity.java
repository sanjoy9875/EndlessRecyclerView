package com.example.paginations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.paginations.api.ApiUtilities;
import com.example.paginations.model.ImageModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ImageModel> list;
    private GridLayoutManager gridLayoutManager;
    private ImageAdapter adapter;
    private int page = 1;
    private ProgressDialog progressDialog;

    private int pageSize = 30;
    private boolean isLoading;
    private boolean isLastPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();
        adapter = new ImageAdapter(this,list);
        gridLayoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        
        getData();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItem = gridLayoutManager.getChildCount();
                int totalItem = gridLayoutManager.getItemCount();
                int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage){
                    if ((visibleItem+firstVisibleItemPosition >= totalItem)
                    && firstVisibleItemPosition >= 0 && totalItem >= pageSize){
                        page++;
                        getData();
                    }
                }
            }
        });

    }

    private void getData() {

        isLoading = true;

        ApiUtilities.getApiService().getImages(page,30)
                .enqueue(new Callback<List<ImageModel>>() {
                    @Override
                    public void onResponse(Call<List<ImageModel>> call, Response<List<ImageModel>> response) {
                        if (response.body()!=null){
                            list.addAll(response.body());
                            adapter.notifyDataSetChanged();

                        }
                        isLoading = false;
                        progressDialog.dismiss();

                        if (list.size()>0){
                            isLastPage = list.size() < pageSize;
                        }
                        else
                            isLastPage = true;

                    }

                    @Override
                    public void onFailure(Call<List<ImageModel>> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText((MainActivity.this), "error! "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}