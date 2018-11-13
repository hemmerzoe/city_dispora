package com.example.hemmerzoe.city_dispora.Data;

import android.content.Context;
import android.content.res.TypedArray;

import com.example.hemmerzoe.city_dispora.Model.CardViewImg;
import com.example.hemmerzoe.city_dispora.Model.Image;
import com.example.hemmerzoe.city_dispora.Model.People;
import com.example.hemmerzoe.city_dispora.R;
import com.example.hemmerzoe.city_dispora.utils.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataGenerator {


    private static Random r = new Random();

    public static int randInt(int max) {
        int min = 0;
//        return r.nextInt(1);
        return r.nextInt((max - min) + 0) + min;
    }

    public static List<Image> getImageDate(Context ctx) {
        List<Image> items = new ArrayList<>();
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.sample_images);
        String name_arr[] = ctx.getResources().getStringArray(R.array.people_names);
        String date_arr[] = ctx.getResources().getStringArray(R.array.general_date);
        for (int i = 0; i < drw_arr.length(); i++) {
            Image obj = new Image();
            obj.image = drw_arr.getResourceId(i, -1);
            obj.name = name_arr[i];
            obj.brief = date_arr[randInt(date_arr.length - 1)];
            obj.counter = r.nextBoolean() ? randInt(500) : null;
            obj.imageDrw = ctx.getResources().getDrawable(obj.image);
            items.add(obj);
        }
        Collections.shuffle(items);
        return items;
    }

    public static List<String> getStringsShort(Context ctx) {
        List<String> items = new ArrayList<>();
        String name_arr[] = ctx.getResources().getStringArray(R.array.strings_short);
        for (String s : name_arr) items.add(s);
        Collections.shuffle(items);
        return items;
    }

    public static List<Integer> getNatureImages(Context ctx) {
        List<Integer> items = new ArrayList<>();
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.sample_images);
        for (int i = 0; i < drw_arr.length(); i++) {
            items.add(drw_arr.getResourceId(i, -1));
        }
        Collections.shuffle(items);
        return items;
    }

    public static List<CardViewImg> getCardImageData(Context ctx, int count) {

        List<CardViewImg> items = new ArrayList<>();

        List<Integer> images = getNatureImages(ctx);
        List<String> titles = getStringsShort(ctx);
        List<String> subtitles = getStringsShort(ctx);

        for (int i = 0; i < count; i++) {
            CardViewImg obj = new CardViewImg();
            obj.image = images.get(getRandomIndex(images.size()));
            obj.title = titles.get(getRandomIndex(titles.size()));
            obj.subtitle = subtitles.get(getRandomIndex(subtitles.size()));
            items.add(obj);
        }
        return items;
    }

    public static List<People> getPeopleData(Context ctx) {
        List<People> items = new ArrayList<>();
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.sample_images);
        String name_arr[] = ctx.getResources().getStringArray(R.array.people_names);

        for (int i = 0; i < drw_arr.length(); i++) {
            People obj = new People();
            obj.image = drw_arr.getResourceId(i, -1);
            obj.name = name_arr[i];
            obj.email = Tools.getEmailFromName(obj.name);
            obj.imageDrw = ctx.getResources().getDrawable(obj.image);
            items.add(obj);
        }
        Collections.shuffle(items);
        return items;
    }

    private static int getRandomIndex(int max) {
        return r.nextInt(max - 1);
    }
}
