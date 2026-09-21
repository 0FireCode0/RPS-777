package org.top.economic;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigationrail.NavigationRailView;

public class MainActivity extends AppCompatActivity {

    private int orientation;
    private Fragment currentFragment;

    private ImageButton btn_burger_land;
    private View expandedMenuPanel;
    private ImageButton btnCloseMenu;
    private NavigationBarView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                StorePlayer.theamsDark = true;
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                StorePlayer.theamsDark = false;
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                StorePlayer.theamsDark = false;
                break;
        }


        if (savedInstanceState == null) {
            currentFragment = new MainFragment();
            loadFragment(currentFragment);
        }
        menu                = findViewById(R.id.menu);
        if (menu != null) {
            ViewCompat.setOnApplyWindowInsetsListener(menu, (v, insets) -> {
                v.setPadding(0, 0, 0, 0);
                return insets;
            });
        }
        orientation         = getResources().getConfiguration().orientation;

        // логика кнопок для горизонтальной
        if ( orientation == Configuration.ORIENTATION_LANDSCAPE )
        {
            btn_burger_land     = findViewById(R.id.btn_burger_land);
            expandedMenuPanel   = findViewById(R.id.expanded_menu_panel);
            btnCloseMenu        = findViewById(R.id.btn_close_menu);


            if(btn_burger_land != null)
            {
                if ( StorePlayer.theamsDark ) {
                    ImageViewCompat.setImageTintList(btn_burger_land, ColorStateList.valueOf(ContextCompat.getColor(this, android.R.color.white)));
                }

                if (btn_burger_land != null) {
                    btn_burger_land.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            expandedMenuPanel.setVisibility(View.VISIBLE);
                            expandedMenuPanel.animate()
                                    .translationX(0f) // Выдвигаем на экран
                                    .setDuration(300)
                                    .setInterpolator(new DecelerateInterpolator())
                                    .start();
                        }
                    });
                }
            }

            if (btnCloseMenu != null) {
                btnCloseMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        expandedMenuPanel.animate()
                                .translationX(-expandedMenuPanel.getWidth()) // Убираем влево за экран
                                .setDuration(250)
                                .setInterpolator(new AccelerateInterpolator())
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        expandedMenuPanel.setVisibility(View.GONE);
                                    }
                                })
                                .start();
                    }
                });
            }
        }


        menu.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                currentFragment = new MainFragment();
            } else if (id == R.id.nav_studio) {
                currentFragment = new StudioFragment();
            } else if (id == R.id.nav_shop) {
                currentFragment = new ShopFragment();
            } else if (id == R.id.nav_wallet) {
                currentFragment = new WalletFragment();
            } else if (id == R.id.nav_money_school) {
                currentFragment = new MoneySchoolFragment();
            } else if (id == R.id.nav_achievements) {
                currentFragment = new AchievementsFragment();
            } else {
                return false;
            }

            loadFragment(currentFragment);
            return true;
        });

    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

}
