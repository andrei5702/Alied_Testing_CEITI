package Lesson_3;

public class Main_PF {
    public static void main(String[] args) {
        Main_PF_Tests tests = new Main_PF_Tests();
        tests.setUp();
        tests.loginWithValidCredentials();
        tests.addItemToCart();
        //tests.removeItemFromCart();
        tests.sortItemsByPrice();
        tests.tearDown();
    }


}
