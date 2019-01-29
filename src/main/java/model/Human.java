package model;

public class Human {
    private int age;
    private String name;
    private String sex;

    public Human(){
        this.age = 5;
        this.name = "デフォルト太郎";
        this.sex = "man";
    }

    public Human(int age, String name, String sex){
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public void setAge(int age){
        this.age = age;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSex(String sex){
        this.sex = sex;
    }
    public int getAge(){
        return this.age;
    }

}
