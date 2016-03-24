package com.github.mzule.androidweekly;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mzule.easyadapter.ViewType;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CaoDongping on 3/24/16.
 */
public class ArticleViewType extends ViewType<Article> {
    @Bind(R.id.name_view)
    TextView nameView;
    @Bind(R.id.brief_view)
    TextView briefView;
    @Bind(R.id.domain_view)
    TextView domainView;
    @Bind(R.id.icon_view)
    ImageView iconView;

    @Override
    public void onCreate() {
        setContentView(R.layout.item_article);
        ButterKnife.bind(this, getView());
    }

    @Override
    public void onRender(int position, Article data) {
        nameView.setText(data.getTitle());
        briefView.setText(data.getBrief());
        domainView.setText(data.getDomain());
        Glide.with(getContext()).load(data.getImageUrl()).centerCrop().placeholder(R.color.placeholder).into(iconView);
        iconView.setVisibility(TextUtils.isEmpty(data.getImageUrl()) ? View.GONE : View.VISIBLE);
    }
}
