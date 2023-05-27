package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class PlannerTest {

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void calculateDailyCaloriesDemand(ActivityLevel al) {
        //given
        Planner plan = new Planner();

        //when
        int caloriesDemand = plan.calculateDailyCaloriesDemand(TestConstants.TEST_USER, al);

        //then
        assertEquals(TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(al), caloriesDemand);
    }

    @Test
    void calculateDailyIntake() {
        //given
        Planner plan = new Planner();
        Comparator<DailyIntake> comp = new Comparator<DailyIntake>() {
            @Override
            public int compare(DailyIntake o1, DailyIntake o2) {
                if(o1.getProtein() == o2.getProtein()
                        && o1.getCalories() == o2.getCalories()
                        && o1.getFat() == o2.getFat()
                        && o1.getCarbohydrate() == o2.getCarbohydrate()){
                    return 0;
                }
                else return -1;
            }
        };
        //when
        DailyIntake actual = plan.calculateDailyIntake(TestConstants.TEST_USER);

        //then
        assertEquals(0, comp.compare(actual, TestConstants.TEST_USER_DAILY_INTAKE));



    }
}