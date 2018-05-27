package com.example.jw.exboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView tvSubject;
    private TextView tvAuthor;
    private TextView tvHitCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvSubject = (TextView) findViewById(R.id.tvSubject);
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);
        tvHitCount = (TextView) findViewById(R.id.tvHitCount);

        Intent intent = getIntent();
        /**
         * 객체를 받아옵니다.
         */
        ArticleVO article = (ArticleVO) intent.getSerializableExtra("article");

        tvSubject.setText(article.getSubject());
        tvAuthor.setText(article.getAuthor());
        /**
         * 조회수(HitCount)는 정수형이기 때문에 [+ ""]을 통해서 문자열로 바꿔줍니다.
         */
        tvHitCount.setText(article.getHitCoutn() + "");
    }
}