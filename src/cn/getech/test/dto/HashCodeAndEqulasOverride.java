package cn.getech.test.dto;

public class HashCodeAndEqulasOverride {

    private int filedOne;
    private String filedTwo;

    @Override
    public boolean equals(Object o) {
        System.out.println("equals被调用");
       return true;
    }

    @Override
    public int hashCode() {
        System.out.println("hashCode被调用");
        return 1;
    }

    public HashCodeAndEqulasOverride() {
    }

    public HashCodeAndEqulasOverride(int filedOne, String filedTwo) {
        this.filedOne = filedOne;
        this.filedTwo = filedTwo;
    }

    public int getFiledOne() {
        return filedOne;
    }

    public void setFiledOne(int filedOne) {
        this.filedOne = filedOne;
    }

    public String getFiledTwo() {
        return filedTwo;
    }

    public void setFiledTwo(String filedTwo) {
        this.filedTwo = filedTwo;
    }
}