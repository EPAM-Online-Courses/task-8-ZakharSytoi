package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietNotRecommended() {
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void isBMICorrect_AnyWeightZeroHeight_Exception(){
        //given
        double weight = 69.5;
        double height = 0.0;

        //then
        assertThrows(IllegalArgumentException.class, ()->{
            FitCalculator.isBMICorrect(weight, height);
        });

    }
    @ParameterizedTest(name = "{index} - Run with args: height = 1.72, weight = {0}")
    @ValueSource(doubles = {88.2, 87.2, 89.2})
    void isBMICorrect_SeveralWeightsAndHeights_True(double weight){
        //given
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }
    @ParameterizedTest(name = "{index} - Run with args: height = {0}, weight = {1}")
    @CsvSource(value = {"1.72 87.2", "1.74 88.2", "1.80 89.2"}, delimiter = ' ')
    void isBMICorrect_SeveralWeightsAndHeightsFromCsv_True(String height, String weight){
        //given

        //when
        boolean recommended = FitCalculator.isBMICorrect(Double.parseDouble(weight), Double.parseDouble(height));

        //then
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "{index} - Run with args: height = {0}, weight = {1}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void isBMICorrect_SeveralWeightsAndHeightsFromCsvFile_True(String height, String weight){
        //given

        //when
        boolean recommended = FitCalculator.isBMICorrect(Double.parseDouble(weight), Double.parseDouble(height));

        //then
        assertFalse(recommended);
    }


}