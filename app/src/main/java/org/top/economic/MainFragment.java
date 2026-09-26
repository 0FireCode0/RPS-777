package org.top.economic;


import android.content.res.Configuration;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MainFragment extends Fragment {


    private FrameLayout homeRoot;
    private View animalContainer;
    private View speechBubble;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(
                R.layout.fragment_main,
                container,
                false
        );
    }


    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState) {

        super.onViewCreated(
                view,
                savedInstanceState
        );

        if ( StorePlayer.orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            homeRoot =
                    view.findViewById(
                            R.id.home_root
                    );


            animalContainer =
                    view.findViewById(
                            R.id.animal_container
                    );


            speechBubble =
                    view.findViewById(
                            R.id.speech_bubble
                    );


            updateHud(view);


            // Ждём, пока экран получит
            // реальные размеры.
            homeRoot.post(() -> {

                positionAnimal();

                positionSpeechBubble();
            });



        }
        setupClicks(view);
    }


    // =========================================================
    // ЖИВОТНОЕ
    // =========================================================

    private void positionAnimal() {

        int width =
                homeRoot.getWidth();


        int height =
                homeRoot.getHeight();


        if (width <= 0 || height <= 0) {
            return;
        }


        // Compose:
        //
        // val catH = h * 0.62f
        //

        float animalHeight =
                height * 0.62f;


        // Compose:
        //
        // x = w * 0.417f - catH / 2
        //

        float left =
                width * 0.417f
                        - animalHeight / 2f;


        // Compose:
        //
        // y = h * 0.29f
        //

        float top =
                height * 0.29f;


        FrameLayout.LayoutParams params =
                (FrameLayout.LayoutParams)
                        animalContainer.getLayoutParams();


        params.leftMargin =
                Math.round(left);


        params.topMargin =
                Math.round(top);


        animalContainer.setLayoutParams(
                params
        );


        /*
         * AnimalFragment внутри имеет
         * размер 300dp x 300dp.
         *
         * Поэтому масштабируем этот контейнер
         * до catH.
         */

        float baseSize =
                dp(240);


        float scale =
                animalHeight / baseSize;


        animalContainer.setPivotX(0f);
        animalContainer.setPivotY(0f);


        animalContainer.setScaleX(
                scale
        );


        animalContainer.setScaleY(
                scale
        );
    }


    // =========================================================
    // ОБЛАЧКО
    // =========================================================

    private void positionSpeechBubble() {

        int width =
                homeRoot.getWidth();


        int height =
                homeRoot.getHeight();


        if (width <= 0 || height <= 0) {
            return;
        }


        FrameLayout.LayoutParams params =
                (FrameLayout.LayoutParams)
                        speechBubble.getLayoutParams();


        params.leftMargin =
                Math.round(
                        width * 0.53f
                );


        params.topMargin =
                Math.round(
                        height * 0.20f
                );


        speechBubble.setLayoutParams(
                params
        );
    }


    // =========================================================
    // ДАННЫЕ HUD
    // =========================================================

    private void updateHud(View view) {

        TextView money =
                view.findViewById(
                        R.id.text_need_money
                );


        TextView piggy =
                view.findViewById(
                        R.id.text_piggy_money
                );


        TextView wants =
                view.findViewById(
                        R.id.text_wants_money
                );


        TextView coins =
                view.findViewById(
                        R.id.text_coins
                );


        if (money != null) {

            money.setText(
                    String.valueOf(
                            StorePlayer.getNeedMoney()
                    )
            );
        }


        if (piggy != null) {

            piggy.setText(
                    String.valueOf(
                            StorePlayer.getMoneybox()
                    )
            );
        }


        if (wants != null) {

            wants.setText(
                    String.valueOf(
                            StorePlayer.getWants()
                    )
            );
        }


        if (coins != null) {
            coins.setText( String.valueOf(StorePlayer.getMoney()) );
        }
    }


    // =========================================================
    // НАЖАТИЯ
    // =========================================================

    private void setupClicks(View view) {


        // -----------------------------------------------------
        // СНЯТЬ ВИДЕО
        // -----------------------------------------------------

        View videoButton =
                view.findViewById(
                        R.id.button_new_video
                );


        if (videoButton != null) {

            videoButton.setOnClickListener(
                    v -> openMainMenuItem(
                            R.id.nav_studio
                    )
            );
        }


        // -----------------------------------------------------
        // ХОТЕЛКИ
        // -----------------------------------------------------

        View wantsChip =
                view.findViewById(
                        R.id.chip_wants
                );


        if (wantsChip != null) {

            wantsChip.setOnClickListener(
                    v -> openMainMenuItem(
                            R.id.nav_shop
                    )
            );
        }


        // -----------------------------------------------------
        // РАСПРЕДЕЛЕНИЕ
        // -----------------------------------------------------

        View needChip =
                view.findViewById(
                        R.id.chip_need
                );


        if (needChip != null) {

            needChip.setOnClickListener(
                    v -> openMainMenuItem(
                            R.id.nav_wallet
                    )
            );
        }


        // -----------------------------------------------------
        // ЗАДАНИЕ ДНЯ
        // -----------------------------------------------------
        /*
        View dailyTask =
                view.findViewById(
                        R.id.daily_task
                );


        if (dailyTask != null) {

            dailyTask.setOnClickListener(
                    v -> openMainMenuItem(
                            R.id.nav_money_school
                    )
            );
        }
        */
    }


    // =========================================================
    // ПЕРЕКЛЮЧЕНИЕ ОСНОВНОГО МЕНЮ
    // =========================================================

    private void openMainMenuItem(int id) {

        View menu =
                requireActivity().findViewById(
                        R.id.menu
                );


        if (menu == null) {
            return;
        }


        if (menu instanceof
                com.google.android.material.navigation.NavigationBarView) {

            com.google.android.material.navigation.NavigationBarView navigationBar =
                    (com.google.android.material.navigation.NavigationBarView)
                            menu;


            if (navigationBar.getMenu().findItem(id)
                    != null) {

                navigationBar.setSelectedItemId(
                        id
                );
            }
        }
    }


    // =========================================================
    // DP
    // =========================================================

    private int dp(int value) {

        return (int)
                TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        value,
                        getResources().getDisplayMetrics()
                );
    }
}