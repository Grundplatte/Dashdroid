package com.hyperion.dashdroid.books;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.books.recommendations.Bookshelf;
import com.hyperion.dashdroid.news.image.ImageLoader;

/**
 * Created by Valdrin on 6/8/2016.
 */
public class DetailBooks extends Activity {

    private Bookshelf bookshelf;
    private int position;
    private ImageLoader imageLoader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_fragment_detail);

        imageLoader = new ImageLoader(this);

        bookshelf = (Bookshelf)getIntent().getExtras().get("bookshelf");
        position = getIntent().getExtras().getInt("pos");

        TextView book_title = (TextView)findViewById(R.id.bd_title);
        book_title.setText(bookshelf.getBookItem(position).getTitle());
        TextView book_author = (TextView)findViewById(R.id.bd_author);
        book_author.setText(bookshelf.getBookItem(position).getAuthor());
        RatingBar book_rating = (RatingBar)findViewById(R.id.bd_rating);
        book_rating.setRating(bookshelf.getBookItem(position).getRating().floatValue());
        ImageView book_cover = (ImageView) findViewById(R.id.bd_cover);
        imageLoader.DisplayImage(bookshelf.getBookItem(position).getBookThumbnail(), book_cover);
        TextView book_pages = (TextView) findViewById(R.id.bd_pages);
        book_pages.setText(bookshelf.getBookItem(position).getPages() + "");
        TextView book_publishing = (TextView) findViewById(R.id.bd_publishing);
        book_publishing.setText(bookshelf.getBookItem(position).getPublishing());
        TextView book_publishedDate = (TextView) findViewById(R.id.bd_publishedDate);
        book_publishedDate.setText(bookshelf.getBookItem(position).getPublishedDate());
        TextView book_genre = (TextView) findViewById(R.id.bd_genre);
        book_genre.setText(bookshelf.getBookItem(position).getGenre());
        TextView book_language = (TextView) findViewById(R.id.bd_language);
        book_language.setText(bookshelf.getBookItem(position).getLanguage());
        TextView description = (TextView) findViewById(R.id.bd_description);
        description.setText(bookshelf.getBookItem(position).getDescription());
    }
}
