package com.example.doitmissioin_10_1.ui.moivelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.doitmissioin_10_1.Fragment1;
import com.example.doitmissioin_10_1.Fragment2;
import com.example.doitmissioin_10_1.Fragment3;
import com.example.doitmissioin_10_1.R;
import com.example.doitmissioin_10_1.databinding.FragmentMovieListBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MovieListFragment extends Fragment {

    ViewPager2 pager2;

    private FragmentMovieListBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentMovieListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //추가한거
        pager2 = binding.pager2;
        pager2.setOffscreenPageLimit(3);

        ArrayList<Fragment> fragments = new ArrayList<>();
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();

        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);

        FragmentStateAdapter adapter = new MyPagerAdapter(this, fragments);

        //페이지가 바뀔 때마다 아래쪽 글자 변경
        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updatePageInfo(position);
            }
        });

        pager2.setAdapter(adapter);
        //pager2.setSaveEnabled(false); // 상태 유지하는? 이 코드를 작성했더니 에러 해결. 에러 : fragment no longer exists for key f#0

        return root;
    }

    private void updatePageInfo(int position) {
        if (binding == null) return; // binding이 null인지 확인
        switch (position) {
            case 0:
                binding.textTitle.setText("라라랜드");
                binding.textInfo.setText("예매율 80% | 15세 관람가 | 평점 4.9");
                break;
            case 1:
                binding.textTitle.setText("시카고");
                binding.textInfo.setText("예매율 70% | 19세 관람가 | 평점 3.9");
                break;
            case 2:
                binding.textTitle.setText("위키드");
                binding.textInfo.setText("예매율 90% | 12세 관람가 | 평점 5.0");
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // 어댑터 클래스를 정의할 때 액티비티에서 정의하는 것이랑 프래그먼트에서 정의하는 차이를 확인하자.
    // extends, FragmentManager, 재정의하는 함수가 달라진다.
    class MyPagerAdapter extends FragmentStateAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        // 생성자: 프래그먼트 리스트를 전달받음
        public MyPagerAdapter(@NonNull MovieListFragment movieListFragment, ArrayList<Fragment> fragments) {
            super(movieListFragment);
            this.items = fragments;
        }

        @Override
        public int getItemCount() {
            return items.size(); // 프래그먼트의 총 개수 반환
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return items.get(position); // 특정 위치의 프래그먼트 반환
        }
    }
}