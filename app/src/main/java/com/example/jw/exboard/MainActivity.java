package com.example.jw.exboard;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.example.jw.exboard.ArticleVO;


public class MainActivity extends AppCompatActivity {

    private ListView lvArticleList;
    List articleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvArticleList = (ListView) findViewById(R.id.lvArticleList);

        articleList = new ArrayList();
        for (int i = 10; i >= 1; i--) {
            articleList.add(new ArticleVO("제목입니다..." + i, "글쓴이입니다.", new Random().nextInt(9999)));
        }

        lvArticleList.setAdapter(new ArticleListViewAdapter(articleList, this));

        lvArticleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            ArticleVO article;
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                article = (ArticleVO) articleList.get(position);
                //Log.e("articleList",articleList.get(position).toString());
                //Log.e("article : ",article.toString());
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("article", article);
                startActivity(intent);
            }
        });
    }

    // Ctrl + i
    private class ArticleListViewAdapter extends BaseAdapter {

        /**
         * ListView에 세팅할 Item 정보들
         */
        private List articleList;

        /**
         * ListView에 Item을 세팅할 요청자의 정보가 들어감
         */
        private Context context;

        /**
         * 생성자
         *
         * @param articleList
         * @param context
         */
        public ArticleListViewAdapter(List articleList, Context context) {
            this.articleList = articleList;
            this.context = context;
        }

        /**
         * ListView에 세팅할 아이템의 갯수
         * @return
         */
        @Override
        public int getCount() {
            return articleList.size();
        }

        /**
         * position 번째 Item 정보를 가져옴
         * @param position
         * @return
         */
        @Override
        public Object getItem(int position) {
            return articleList.get(position);
        }

        /**
         * 아이템의 index를 가져옴
         * Item index == position
         * @param position
         * @return
         */
        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * ListView에 Item들을 세팅함
         * position 번 째 있는 아이템을 가져와서 converView에 넣은다음 parent에서 보여주면된다.
         * @param position : 현재 보여질 아이템의 인덱스, 0 ~ getCount() 까지 증가
         * @param convertView : ListView의 Item Cell(한 칸) 객체를 가져옴
         * @param parent : ListView
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            /**
             * 가장 간단한 방법
             * 사용자가 처음으로 Flicking을 할 때, 아래쪽에 만들어지는 Cell(한 칸)은 Null이다.
             */
            if( convertView == null ) {

                // Item Cell에 Layout을 적용시킬 Inflater 객체를 생성한다.
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

                // Item Cell에 Layout을 적용시킨다.
                // 실제 객체는 이곳에 있다.
                convertView = inflater.inflate(R.layout.item_list, parent, false);
            }

            TextView tvSubject = (TextView) convertView.findViewById(R.id.tvSubject);
            TextView tvAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
            TextView tvHitCount = (TextView) convertView.findViewById(R.id.tvHitCount);

            ArticleVO article = (ArticleVO) getItem(position);
            tvSubject.setText(article.getSubject());
            tvAuthor.setText(article.getAuthor());
            tvHitCount.setText(article.getHitCoutn() + "");

            return convertView;
        }

    }
}