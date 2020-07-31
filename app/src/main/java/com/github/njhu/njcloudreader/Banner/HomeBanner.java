package com.github.njhu.njcloudreader.Banner;

import android.content.Context;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.bumptech.glide.Glide;
import com.github.njhu.njcloudreader.Activity.MainActivity;
import com.github.njhu.njcloudreader.Base.BaseFrameLayout;
import com.github.njhu.njcloudreader.Base.MyApplication;
import com.github.njhu.njcloudreader.Bean.Banner;
import com.github.njhu.njcloudreader.Bean.BannerData;
import com.github.njhu.njcloudreader.R;
import com.github.njhu.njcloudreader.Tool.ActivityCollector;
import com.github.njhu.njcloudreader.Tool.ServiceCreator;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import kotlin.reflect.KFunction;
import me.jingbin.sbanner.SBannerView;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeBanner extends BaseFrameLayout {

    class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {
       private List<Banner> banners = new ArrayList<>();
        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;

            ViewHolder(@NotNull View view){
                super(view);
               this.imageView = view.findViewById(R.id.banner_image_view);
               this.textView = view.findViewById(R.id.title_text_view);
            }
        }

        public List<Banner> getBanners() {
            return banners;
        }

        public void setBanners(List<Banner> banners) {
            this.banners = banners;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_banner, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Banner banner = banners.get(position);
            holder.textView.setText(banner.getTitle());
            ImageView imageView = holder.imageView;
            String url = banner.getImagePath();
            Glide.with(getContext()).load(url).into(imageView);
        }

        @Override
        public int getItemCount() {
            return banners.size();
        }
    }

    private static final String TAG = "HomeBanner";
    private RecyclerView recyclerView;
    private List<Banner> banners = new ArrayList<>();

    public HomeBanner(Context context) {
        this(context, null);
    }
    public HomeBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public HomeBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        BannerAdapter bannerAdapter = new BannerAdapter();
        recyclerView.setAdapter(bannerAdapter);
        loadBanner();
    }

    private void loadBanner(){

        (ServiceCreator.INSTANCE).createAppWanService().getBannerList().enqueue(new Callback<BannerData>() {
            @Override
            public void onResponse(Call<BannerData> call, Response<BannerData> response) {
                BannerData data = response.body();
                List<Banner> banners = data.getData();
                for (int i = 0; i < banners.size(); i++) {
                    Banner banner = banners.get(i);
                    Log.d(TAG, banner.getTitle());
                }
                BannerAdapter bannerAdapter = (BannerAdapter)recyclerView.getAdapter();
                bannerAdapter.setBanners(banners);
                bannerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BannerData> call, Throwable t) {

            }
        });
    }
}
