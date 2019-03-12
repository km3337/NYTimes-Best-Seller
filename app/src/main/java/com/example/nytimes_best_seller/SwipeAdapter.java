package com.example.nytimes_best_seller;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;

public class SwipeAdapter extends FragmentPagerAdapter {
    public SwipeAdapter(FragmentManager fm){
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {
        PageFragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("book_number", i);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
