package org.example;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class AverageTest {
    @Test
    public void should_create_class(){
        var average = new Average();

       Assert assertThat(average).isNotNull;
    }
    @Test
    public void should_calculate_average(){
        var average = new Average("file1.txt");

        double average2 = Average.calculate();

        assertThat(average2).isToEqual;
    }


}