class Main{
    public static void main(String[] args) {
        Thread hilo1 = new Thread(new test(1));
        Thread hilo2 = new Thread(new test(2));
        Thread hilo3 = new Thread(new test(3));
        hilo1.start();
        hilo2.start();
        hilo3.start();
        
    }
}