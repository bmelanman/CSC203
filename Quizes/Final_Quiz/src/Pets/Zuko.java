package Pets;

public class Zuko extends Cat
{
    public Zuko() { System.out.println("C"); }
    public void speak(Cat c)
    {
        System.out.println("hsss");
    }

    public void speak(Pet p)
    {
        System.out.println("grrr");
    }

    public void speak(Zuko z)
    {
        System.out.println("hi me");
    }
}