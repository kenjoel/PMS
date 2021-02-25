package com.moringaschool.pms.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.pms.R;
import com.moringaschool.pms.model.ApiReturn;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<ApiReturn> pmsArticles;
    private Context context;

    public ArticleAdapter(Context mContext, List<ApiReturn> articles){
        context = mContext;
        pmsArticles = articles;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pmsArticle = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_activity, parent, false);
        ArticleViewHolder viewHolder = new ArticleViewHolder(pmsArticle);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.bindArticle(pmsArticles.get(position));

    }

    @Override
    public int getItemCount() {
        return pmsArticles.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.articleHeadlineTextView) TextView pmsHeadlineArticle;
        @BindView(R.id.articleImageView) ImageView pmsImageView;

        private Context gContext;

        public ArticleViewHolder(View articleItemView){
            super(articleItemView);
            ButterKnife.bind(this, articleItemView);

            gContext = articleItemView.getContext();

        }



        public void bindArticle(ApiReturn pmsArticles){
            // picasso couldnt load empty image links, looking for possible ways to solve this

           //Picasso.get().load(pmsArticles.getImgurl()).into(pmsImageView);

            pmsHeadlineArticle.setText( pmsArticles.getHeadline());

        }
    }

}
