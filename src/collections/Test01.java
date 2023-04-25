package collections;

public class Test01 {
    public static void main(String[] args) {
        Person[] pers = new Person[1];
        pers[0] = new Person();

        Person[] pers2 = new Person[pers.length + 1];
        for (int i = 0; i < pers.length; i++) {
            pers2[i] = pers[i];
        }
        pers2[pers2.length - 1] = new Person();
    }
}

class Person {

}
