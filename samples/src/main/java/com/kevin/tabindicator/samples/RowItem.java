package com.kevin.tabindicator.samples;

/**
 * Created by dell on 2016/6/24.
 */
public class RowItem {
    private String key;
    private String value;

    public RowItem(String key,String value){
        super();
        this.key=key;
        this.value=value;
    }

    public String getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }
}
