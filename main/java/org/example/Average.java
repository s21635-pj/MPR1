package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Average{

    private final File testFile;

    Average(){
        this.testFile = null;
    }

    Average(String filePath){
        this.testFile = new File("./src/test/resources/"+ filePath);
    }

    public double calculate(){
        try {
            if (testFile == null) {
                throw new IllegalArgumentException("no file specified");
            }
            if (!testFile.exists()) {
                throw new IllegalArgumentException("File not exist");
            }

            var filwReader = new BufferedReader(new FileReader(testFile));

            long sum_w = 0, sum_wx = 0;
            String line = null;
            while ((line = filwReader.readLine()) !=null){
                long w , x;
                String[] data = line.split(" ");
                if (data.length == 2){
                    try {
                        w = Long.parseLong(data[0]);
                        x = Long.parseLong(data[0]);
                    }catch (NumberFormatException e){
                        throw new IllegalArgumentException("Wrong file format");
                    }
                }
                else{
                    throw new IllegalArgumentException("Wrong file format");
                }

                sum_wx += x * w;
                sum_w += w;

            }

            BigDecimal finishResult = new BigDecimal((double)sum_wx / (double)sum_w).setScale(2, RoundingMode.HALF_UP);
            return finishResult.doubleValue();
        }catch (IOException e){
            throw new IllegalStateException(e);
        }
    }

    public String resultString(){
        var result = calculate();
        String stringResult = String.format("%.2f", result);
        return removeTrillingZeroes(stringResult);
    }

    String removeTrillingZeroes(String resultAverage){
        char lastChar = resultAverage.charAt(resultAverage.length()- 1);
        if (lastChar == '0' && resultAverage.contains(".")){
            return removeTrillingZeroes(resultAverage.substring(0,resultAverage.lastIndexOf('0')));
        }
        if (lastChar == '.'){
            return resultAverage.replace(".", "");
        }
        return resultAverage;
    }
}