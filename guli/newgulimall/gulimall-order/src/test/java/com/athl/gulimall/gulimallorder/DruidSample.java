package com.athl.gulimall.gulimallorder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DruidSample {
    public static void main(String[] args) {
       // Properties config = new Properties();
       //
       // String path = DruidSample.class.getResource("/application.properties").getPath();
       //try {
       //    path =new URLDecoder().decode(path,"UTF-8");
       //    config.load(new FileInputStream(path));
       //}catch (UnsupportedEncodingException e){
       //    e.printStackTrace();
       //} catch (FileNotFoundException e) {
       //    e.printStackTrace();
       //} catch (IOException e) {
       //    e.printStackTrace();
       //}
       // Scanner sc = new Scanner(System.in);

        ArrayList<BigDecimal> list = new ArrayList<>();
        List<BigDecimal> mapedList = list.stream().map(id -> id.subtract(BigDecimal.ONE)).collect(Collectors.toList());


    }
}
