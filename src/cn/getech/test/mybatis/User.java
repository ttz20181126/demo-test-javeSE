package cn.getech.test.mybatis;


import java.util.List;

public class User {
    private Integer id;
    private int age;
    private String username;

    private List<String> hobbyList;

    private List<Wife> wifeList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(List<String> hobbyList) {
        this.hobbyList = hobbyList;
    }

    public List<Wife> getWifeList() {
        return wifeList;
    }

    public void setWifeList(List<Wife> wifeList) {
        this.wifeList = wifeList;
    }
}