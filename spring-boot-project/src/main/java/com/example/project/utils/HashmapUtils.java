package com.example.project.utils;

import java.util.*;

public class HashmapUtils {
    public static HashMap<Integer,Integer> trans(List<HashMap<String,Object>> list){
        HashMap<Integer,Integer> newhash = new HashMap<>();
        for(int i=0;i<list.size();i++){
            HashMap<String,Object> hashMap = list.get(i);
            Integer K = (Integer) hashMap.get("post_id");
            Long value = (Long) hashMap.get("count(1)");
            Integer V = Integer.parseInt(value.toString());
            newhash.put(K,V);
        }
        return newhash;
    }

    public static List<Map.Entry<Integer,Integer>> sort(HashMap<Integer,Integer> hashMap,int count){
        HashMap<Integer,Integer> newhash = new HashMap<>();
        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(hashMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }//5,4,3,2,1 ?
        });
        int count_ = count;
        if(list.size()<count)
            count_ = list.size();
//        for(Map.Entry<Integer,Integer> m:list.subList(0,count_)){
//            newhash.put(m.getKey(),m.getValue());
//        }
        return list.subList(0,count_);
    }

}
