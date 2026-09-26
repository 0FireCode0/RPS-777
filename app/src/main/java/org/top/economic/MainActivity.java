package org.top.economic;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
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

import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import android.widget.PopupMenu;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private Fragment currentFragment;

    private ImageButton btn_burger_land;
    private View expandedMenuPanel;
    private ImageButton btnCloseMenu;
    private NavigationBarView menu;



    private View menuOverlay;

    private LinearLayout shellGameContainer;
    private LinearLayout shellMoneyContainer;
    private LinearLayout shellEducationContainer;
    private LinearLayout shellOtherContainer;

    private final HashMap<Integer, View> shellItemViews = new HashMap<>();


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

        menu                    = findViewById(R.id.menu);

        if (menu != null) {
            ViewCompat.setOnApplyWindowInsetsListener(menu, (v, insets) -> {
                v.setPadding(0, 0, 0, 0);
                return insets;
            });
        }
        StorePlayer.orientation         = getResources().getConfiguration().orientation;

        // логика кнопок для горизонтальной
        if ( StorePlayer.orientation == Configuration.ORIENTATION_LANDSCAPE )
        {
            btn_burger_land     = findViewById(R.id.btn_burger_land);

            menuOverlay             = findViewById(R.id.menu_overlay);
            expandedMenuPanel       = findViewById(R.id.expanded_menu_panel);
            btnCloseMenu            = findViewById(R.id.btn_close_menu);

            shellGameContainer      = findViewById(R.id.shell_game_container);
            shellMoneyContainer     = findViewById(R.id.shell_money_container);
            shellEducationContainer = findViewById(R.id.shell_education_container);
            shellOtherContainer     = findViewById(R.id.shell_other_container);
            buildShellMenu();


            if(btn_burger_land != null)
            {
                if ( StorePlayer.theamsDark ) {
                    ImageViewCompat.setImageTintList(btn_burger_land, ColorStateList.valueOf(ContextCompat.getColor(this, android.R.color.white)));
                }

                btn_burger_land.setOnClickListener(v -> {

                    menuOverlay.setVisibility(View.VISIBLE);
                    expandedMenuPanel.setVisibility(View.VISIBLE);

                    menuOverlay.setAlpha(0f);
                    expandedMenuPanel.setTranslationX(
                            -expandedMenuPanel.getWidth()
                    );

                    menuOverlay.animate()
                            .alpha(1f)
                            .setDuration(200)
                            .start();

                    expandedMenuPanel.animate()
                            .translationX(0f)
                            .setDuration(260)
                            .setInterpolator(
                                    new DecelerateInterpolator()
                            )
                            .start();
                });
            }

            if (btnCloseMenu != null) {
                btnCloseMenu.setOnClickListener(
                        v -> closeExpandedMenu()
                );

                menuOverlay.setOnClickListener(
                        v -> closeExpandedMenu()
                );
            }




            //Настройка монет, имени и имени животного в боковом меню
            if ( true ){
                TextView tv_buff = null;

                tv_buff = findViewById(R.id.name_animal);
                tv_buff.setText(StorePlayer.NameAnimal);

                tv_buff = findViewById(R.id.name);
                tv_buff.setText(StorePlayer.Name);

                tv_buff = findViewById(R.id.money_menu);
                tv_buff.setText( String.valueOf(StorePlayer.getMoney()) );
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
            updateShellMenuSelection(id);
            return true;
        });


    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


    private void buildShellMenu() {
        PopupMenu popupMenu = new PopupMenu(this, btn_burger_land);
        Menu shellMenu = popupMenu.getMenu();
        getMenuInflater().inflate(
                R.menu.shell_menu,
                shellMenu
        );
        for (int i = 0; i < shellMenu.size(); i++) {
            MenuItem item = shellMenu.getItem(i);
            View itemView = createShellItem(item);
            shellItemViews.put(
                    item.getItemId(),
                    itemView
            );
            int groupId = item.getGroupId();
            if (groupId == R.id.group_game) {
                shellGameContainer.addView(itemView);
            } else if (groupId == R.id.group_money) {
                shellMoneyContainer.addView(itemView);
            } else if (groupId == R.id.group_education) {
                shellEducationContainer.addView(itemView);
            } else if (groupId == R.id.group_other) {
                shellOtherContainer.addView(itemView);
            }
        }
        updateShellMenuSelection( menu.getSelectedItemId() );
    }
    private View createShellItem(MenuItem item) {

        LinearLayout row = new LinearLayout(this);

        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);

        int horizontalPadding = dp(9);
        int verticalPadding = dp(6);

        row.setPadding(
                horizontalPadding,
                verticalPadding,
                horizontalPadding,
                verticalPadding
        );

        row.setBackground(
                AppCompatResources.getDrawable(
                        this,
                        R.drawable.menu_item_background
                )
        );


        // Проверяем, является ли иконка маленьким цветным кружком
        boolean isMoneyCircle =
                item.getItemId() == R.id.nav_need_money
                        || item.getItemId() == R.id.nav_moneybox
                        || item.getItemId() == R.id.nav_wants;


        ImageView icon = new ImageView(this);
        LinearLayout.LayoutParams iconParams;
        if (isMoneyCircle) {
            //крыжки денег
            iconParams = new LinearLayout.LayoutParams(
                    dp(7),
                    dp(7)
            );
        } else {
            iconParams = new LinearLayout.LayoutParams(
                    dp(20),
                    dp(20)
            );
        }
        iconParams.rightMargin = dp(9);
        icon.setLayoutParams(iconParams);


        Drawable drawable = item.getIcon();
        if (drawable != null) {
            if (isMoneyCircle) {
                icon.setImageDrawable(drawable);
            } else {
                // Обычные иконки можно перекрашивать.
                drawable = DrawableCompat.wrap(
                        drawable.mutate()
                );
                icon.setImageDrawable(drawable);
                ImageViewCompat.setImageTintList(
                        icon,
                        AppCompatResources.getColorStateList(
                                this,
                                R.color.menu_panel
                        )
                );
            }
        }
        // Чтобы состояние selected родителя передавалось иконке
        icon.setDuplicateParentStateEnabled(true);


        // ТЕКСТ
        TextView text = new TextView(this);

        LinearLayout.LayoutParams textParams =
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f
                );

        text.setLayoutParams(textParams);

        text.setText(item.getTitle());
        text.setTextSize(14);

        text.setTypeface(
                text.getTypeface(),
                Typeface.BOLD
        );

        text.setTextColor(
                AppCompatResources.getColorStateList(
                        this,
                        R.color.menu_panel
                )
        );

        // Чтобы selected родителя влиял на цвет текста
        text.setDuplicateParentStateEnabled(true);


        row.addView(icon);
        row.addView(text);

        row.setOnClickListener(v -> {
            openShellItem(item.getItemId());
        });

        return row;
    }
    private int dp(int value) {

        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value,
                getResources().getDisplayMetrics()
        );
    }
    private void openShellItem(int id) {
        if (id == R.id.nav_home) {
            currentFragment = new MainFragment();
        } else if (id == R.id.nav_studio) {
            currentFragment = new StudioFragment();
        } else if (id == R.id.nav_shop) {
            currentFragment = new ShopFragment();
        } else if (id == R.id.nav_need_money) {
            // TODO: implement me
            //return;
        } else if (id == R.id.nav_moneybox) {
            // TODO: implement me
            //return;
        } else if (id == R.id.nav_wants) {
            // TODO: implement me
            //return;
        } else if (id == R.id.nav_wallet) {
            currentFragment = new WalletFragment();
        } else if (id == R.id.nav_money_school) {
            currentFragment = new MoneySchoolFragment();
        } else if (id == R.id.nav_achievements) {
            currentFragment = new AchievementsFragment();
        } else if (id == R.id.nav_report) {
            // TODO: implement me
            //return;
        } else if (id == R.id.nav_settings) {
            // TODO: implement me
            //return;
        } else if (id == R.id.nav_parental_control) {
            // TODO: implement me
            //return;
        } else if (id == R.id.nav_wardrobe) {
            // TODO: implement me
            //return;
        } else {

            //return;
        }
        loadFragment(currentFragment);
        // Синхронизируем основное меню
        if ( menu != null ) {
            if ( menu.getMenu().findItem(id) != null )
            {
                menu.setSelectedItemId(id);
            }
            else
            {
                int selectedId = menu.getSelectedItemId();
                android.view.MenuItem selectedItem = menu.getMenu().findItem(selectedId);
                if (selectedItem != null) {
                    selectedItem.setChecked(false);
                }
            }
        }
        updateShellMenuSelection(id);
        closeExpandedMenu();
    }
    private void closeExpandedMenu() {

        expandedMenuPanel.animate()
                .translationX(-expandedMenuPanel.getWidth())
                .setDuration(280)
                .setInterpolator(new DecelerateInterpolator(1.5f))
                .withEndAction(() -> {

                    expandedMenuPanel.setVisibility(View.GONE);
                    menuOverlay.setVisibility(View.GONE);

                })
                .start();

        menuOverlay.animate()
                .alpha(0f)
                .setDuration(220)
                .setInterpolator(new DecelerateInterpolator())
                .start();
    }
    private void updateShellMenuSelection(int selectedId) {

        for (Integer id : shellItemViews.keySet()) {

            View itemView = shellItemViews.get(id);

            if (itemView != null) {
                itemView.setSelected(id == selectedId);
            }
        }
    }
}
