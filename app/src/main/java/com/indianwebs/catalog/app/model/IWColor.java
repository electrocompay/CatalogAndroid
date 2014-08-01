package com.indianwebs.catalog.app.model;

/**
 * Created by abelmiranda on 6/30/14.
 */
public class IWColor {

    private String name;
    private String code;
    private String file;
    private String category;



    public static IWColor colorWithName(String name, String code, String file)
    {
        IWColor newColor = new IWColor();
        newColor.setName(name);
        newColor.setCode(code);
        newColor.setFile(file);
        return newColor;
    }

    public IWColor withCategory(String category)
    {
        this.setCategory(category);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



}
