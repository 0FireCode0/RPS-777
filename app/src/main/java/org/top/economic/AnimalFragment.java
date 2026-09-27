package org.top.economic;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AnimalFragment extends Fragment {

    private FrameLayout characterRoot;

    private FrameLayout tailBone;
    private FrameLayout bodyBone;
    private FrameLayout headBone;
    private FrameLayout armBone;


    private ImageView tail;
    private ImageView body;

    private ImageView head;
    private ImageView earLeft;
    private ImageView earRight;

    private ImageView arm;


    // Слоты для аксессуаров
    private FrameLayout headwearSlot;
    private FrameLayout headphonesSlot;
    private FrameLayout glassesSlot;

    private FrameLayout wristSlot;


    private ObjectAnimator breathingAnimator;
    private ObjectAnimator tailAnimator;
    private ObjectAnimator headAnimator;


    public AnimalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(
                R.layout.fragment_animal,
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

        findViews(view);

        setupAnimal();

        setupPivots();

        setupAccessories();

        startIdleAnimation();
    }


    private void findViews(View view) {

        characterRoot =
                view.findViewById(
                        R.id.character_root
                );


        tailBone =
                view.findViewById(
                        R.id.tail_bone
                );


        bodyBone =
                view.findViewById(
                        R.id.body_bone
                );


        headBone =
                view.findViewById(
                        R.id.head_bone
                );


        armBone =
                view.findViewById(
                        R.id.arm_bone
                );


        tail =
                view.findViewById(
                        R.id.animal_tail
                );


        body =
                view.findViewById(
                        R.id.animal_body
                );


        head =
                view.findViewById(
                        R.id.animal_head
                );


        earLeft =
                view.findViewById(
                        R.id.animal_ear_left
                );


        earRight =
                view.findViewById(
                        R.id.animal_ear_right
                );


        arm =
                view.findViewById(
                        R.id.animal_arm
                );


        // Слоты
        headwearSlot =
                view.findViewById(
                        R.id.headwear_slot
                );


        headphonesSlot =
                view.findViewById(
                        R.id.headphones_slot
                );


        glassesSlot =
                view.findViewById(
                        R.id.glasses_slot
                );


        wristSlot =
                view.findViewById(
                        R.id.wrist_slot
                );
    }


    private void setupAnimal() {

        AnimalType animalType =
                StorePlayer.getAnimalType();


        if (animalType == AnimalType.CAT) {

            tail.setImageResource(
                    R.drawable.cat_tail
            );

            body.setImageResource(
                    R.drawable.cat_body
            );

            head.setImageResource(
                    R.drawable.cat_head
            );

            earLeft.setImageResource(
                    R.drawable.cat_ear_left
            );

            earRight.setImageResource(
                    R.drawable.cat_ear_right
            );

            arm.setImageResource(
                    R.drawable.cat_arm
            );


        }
//        else if (animalType == AnimalType.DOG) {
//
//            tail.setImageResource(
//                    R.drawable.dog_tail
//            );
//
//            body.setImageResource(
//                    R.drawable.dog_body
//            );
//
//            head.setImageResource(
//                    R.drawable.dog_head
//            );
//
//            earLeft.setImageResource(
//                    R.drawable.dog_ear_left
//            );
//
//            earRight.setImageResource(
//                    R.drawable.dog_ear_right
//            );
//
//            arm.setImageResource(
//                    R.drawable.dog_arm
//            );
//        }

    }


    private void setupPivots() {

        tailBone.post(() -> {

            tailBone.setPivotX(
                    tailBone.getWidth() * 0.25f
            );

            tailBone.setPivotY(
                    tailBone.getHeight() * 0.60f
            );
        });


        headBone.post(() -> {

            headBone.setPivotX(
                    headBone.getWidth() * 0.50f
            );

            headBone.setPivotY(
                    headBone.getHeight() * 0.40f
            );
        });


        armBone.post(() -> {

            armBone.setPivotX(
                    armBone.getWidth() * 0.45f
            );

            armBone.setPivotY(
                    armBone.getHeight() * 0.58f
            );
        });


        bodyBone.post(() -> {

            bodyBone.setPivotX(
                    bodyBone.getWidth() * 0.50f
            );

            bodyBone.setPivotY(
                    bodyBone.getHeight() * 0.50f
            );
        });
    }


    private void setupAccessories() {

        // На всякий случай очищаем слоты.
        headwearSlot.removeAllViews();
        headphonesSlot.removeAllViews();
        glassesSlot.removeAllViews();
        wristSlot.removeAllViews();


        // ---------------------------------------------------------
        // ГОЛОВНОЙ УБОР
        // ---------------------------------------------------------

        String headwearId =
                StorePlayer.getHeadwearId();


        if ("hat_red".equals(headwearId)) {

            addAccessory(
                    headwearSlot,
                    R.drawable.accessory_hat_red
            );

            // Шапка закрывает уши
            earLeft.setVisibility(View.INVISIBLE);
            earRight.setVisibility(View.INVISIBLE);


        }
//        else if ("helmet_motorcycle".equals(headwearId)) {
//
//            addAccessory(
//                    headwearSlot,
//                    R.drawable.accessory_helmet_motorcycle
//            );
//
//            // Шлем полностью заменяет голову
//            head.setVisibility(View.INVISIBLE);
//
//            earLeft.setVisibility(View.INVISIBLE);
//            earRight.setVisibility(View.INVISIBLE);
//
//
//        }
        else {

            // Ничего на голове нет
            head.setVisibility(View.VISIBLE);

            earLeft.setVisibility(View.VISIBLE);
            earRight.setVisibility(View.VISIBLE);
        }


        // ---------------------------------------------------------
        // НАУШНИКИ
        // ---------------------------------------------------------

        String headphonesId =
                StorePlayer.getHeadphonesId();


//        if ("headphones_black".equals(headphonesId)) {
//
//            addAccessory(
//                    headphonesSlot,
//                    R.drawable.accessory_headphones_black
//            );
//        }


        // ---------------------------------------------------------
        // ОЧКИ
        // ---------------------------------------------------------

        String glassesId =
                StorePlayer.getGlassesId();


//        if ("glasses_round".equals(glassesId)) {
//
//            addAccessory(
//                    glassesSlot,
//                    R.drawable.accessory_glasses_round
//            );
//        }


        // ---------------------------------------------------------
        // ЗАПЯСТЬЕ
        // ---------------------------------------------------------

        String watchId =
                StorePlayer.getWatchId();


//        if ("watch_classic".equals(watchId)) {
//
//            addAccessory(
//                    wristSlot,
//                    R.drawable.accessory_watch_classic
//            );
//        }
    }
    private void addAccessory(
            FrameLayout slot,
            int drawableId) {

        ImageView accessory =
                new ImageView(requireContext());

        accessory.setImageResource(drawableId);

        accessory.setScaleType(
                ImageView.ScaleType.CENTER_INSIDE
        );

        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                );

        accessory.setLayoutParams(params);

        slot.addView(accessory);
    }



    // дыхание
    private void startIdleAnimation() {
        breathingAnimator =
                ObjectAnimator.ofPropertyValuesHolder(
                        bodyBone,

                        PropertyValuesHolder.ofFloat(
                                View.SCALE_Y,
                                1.0f,
                                1.05f
                        ),

                        PropertyValuesHolder.ofFloat(
                                View.SCALE_X,
                                1.0f,
                                0.99f
                        )
                );

        breathingAnimator.setDuration(1500);

        breathingAnimator.setRepeatMode(
                ValueAnimator.REVERSE
        );

        breathingAnimator.setRepeatCount(
                ValueAnimator.INFINITE
        );

        breathingAnimator.start();


        // ---------------------------------------------------------
        // ХВОСТ
        // ---------------------------------------------------------

        tailAnimator =
                ObjectAnimator.ofFloat(
                        tailBone,
                        View.ROTATION,
                        -4f,
                        4f,
                        -2f,
                        2f,
                        -4f
                );

        tailAnimator.setDuration(
                5000
        );

        tailAnimator.setInterpolator(
                new AccelerateDecelerateInterpolator()
        );

        tailAnimator.setRepeatCount(
                ValueAnimator.INFINITE
        );

        tailAnimator.start();


        // ---------------------------------------------------------
        // ЛЁГКОЕ ДВИЖЕНИЕ ГОЛОВЫ
        // ---------------------------------------------------------

        headAnimator =
                ObjectAnimator.ofFloat(
                        headBone,
                        View.TRANSLATION_Y,
                        0f,
                        -1.5f,
                        0f
                );

        headAnimator.setDuration(1800);

        headAnimator.setInterpolator(
                new AccelerateDecelerateInterpolator()
        );

        headAnimator.setRepeatCount(
                ValueAnimator.INFINITE
        );

        headAnimator.start();
    }



    @Override
    public void onDestroyView() {

        if (breathingAnimator != null) {
            breathingAnimator.cancel();
        }

        if (tailAnimator != null) {
            tailAnimator.cancel();
        }

        if (headAnimator != null) {
            headAnimator.cancel();
        }

        super.onDestroyView();
    }
}
