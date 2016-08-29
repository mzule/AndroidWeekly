package com.github.mzule.androidweekly.ui.viewtype;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mzule.androidweekly.R;
import com.github.mzule.androidweekly.entity.Article;
import com.github.mzule.layoutannotation.Layout;

import butterknife.Bind;

/**
 * Created by CaoDongping on 3/24/16.
 */
@Layout(R.layout.item_article)
public class ArticleViewType extends BaseViewType<Article> {
    @Bind(R.id.nameView)
    TextView nameView;
    @Bind(R.id.briefView)
    TextView briefView;
    @Bind(R.id.domainView)
    TextView domainView;
    @Bind(R.id.iconView)
    ImageView iconView;

    @Override
    public void onRender(int position, Article data) {
        nameView.setText(data.getTitle());
        briefView.setText(data.getBrief());
        domainView.setText(data.getDomain());
        Glide.with(getContext()).load(data.getImageUrl()).centerCrop().placeholder(R.color.placeholder).into(iconView);
        iconView.setVisibility(TextUtils.isEmpty(data.getImageUrl()) ? View.GONE : View.VISIBLE);
    }
}
