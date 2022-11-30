public class Dog {
    private int age;
    private int numberOfTricks;

    public Dog(int age, int numberOfTricks) {
        this.age = age;
        this.numberOfTricks = numberOfTricks;
    }

    public void setAge(int i){
        this.age = i;
    }

    public int getAge() {
        return age;
    }

    public int getNumberOfTricks() {
        return numberOfTricks;
    }

    public void setNumberOfTricks(int num) {
        numberOfTricks = num;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                ", numberOfTricks=" + numberOfTricks +
                '}';
    }
}