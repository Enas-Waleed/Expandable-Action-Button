package com.vpaliy.vasya.fab_toolbar_example;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.bumptech.glide.Glide;
import com.popularmovies.vpaliy.bottomtoolbar.ExpandableButtonView;
import com.popularmovies.vpaliy.bottomtoolbar.ScrollListener;
import com.vpaliy.vasya.fab_toolbar_example.utils.SquareImage;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.expandableButton)
    protected ExpandableButtonView expandableButtonView;

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initImageList();
    }


    private void initImageList() {

        List<Integer> rawDrawableList = Arrays.asList(R.drawable.eleven, R.drawable.fifteen, R.drawable.five,
                R.drawable.four, R.drawable.fourteen, R.drawable.seven, R.drawable.seventeen,
                R.drawable.six, R.drawable.sixteen, R.drawable.ten, R.drawable.thirt, R.drawable.three, R.drawable.twelve,
                R.drawable.two);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new GalleryAdapter(this, rawDrawableList));
        recyclerView.addOnScrollListener(new ScrollListener(expandableButtonView));

        for (int index = 0; index < 5; index++) {
            ImageButton imageButton = new ImageButton(this);
            switch (index){
                case 1:
                    imageButton.setImageResource(R.drawable.ic_email_white_24dp);
                    break;
                case 2:
                    imageButton.setImageResource(R.drawable.ic_location_on_white_24dp);
                    break;
                case 3:
                    imageButton.setImageResource(R.drawable.ic_chat_white_24dp);
                    break;
                case 4:
                    imageButton.setImageResource(R.drawable.ic_content_copy_white_24dp);
                    break;
                default:
                    imageButton.setImageResource(R.drawable.ic_content_copy_white_24dp);

            }
            expandableButtonView.addToolbarItem(imageButton);
        }

    }


    /* Adapter for RecyclerView */
    public class GalleryAdapter extends
            RecyclerView.Adapter<GalleryAdapter.ImageViewHolder> {

        /*I should have used a simple array here, in order to optimize the code */
     
        private List<Integer> imageList;
        private LayoutInflater inflater;

        public GalleryAdapter(Context context, List<Integer> imageList) {
            this.inflater=LayoutInflater.from(context);
            this.imageList=imageList;
        }

        public class ImageViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.image)
            SquareImage image;

            public ImageViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }

            void onBindData() {
                Glide.with(itemView.getContext())
                        .load(imageList.get(getAdapterPosition()%imageList.size()))
                        .asBitmap()
                        .centerCrop()
                        .into(image);
            }
        }

        @Override
        public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View root=inflater.inflate(R.layout.image,parent,false);
            return new ImageViewHolder(root);
        }

        @Override
        public void onBindViewHolder(ImageViewHolder holder, int position) {
            holder.onBindData();
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }
    }

}
