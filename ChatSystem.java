class ChatUser extends Thread {

    boolean running = true;
    boolean suspended = false;

    ChatUser(String name) {
        super(name);
    }

    public synchronized void suspendUser() {
        suspended = true;
        System.out.println(getName() + " Suspended");
    }

    public synchronized void resumeUser() {
        suspended = false;
        notify();
        System.out.println(getName() + " Resumed");
    }

    public void terminateUser() {
        running = false;
        interrupt();
        System.out.println(getName() + " Terminated");
    }

    public static void sendMessage(ChatUser user, String message, boolean important) {

        if (important) {
            user.setPriority(Thread.MAX_PRIORITY);
        }

        System.out.println(Thread.currentThread().getName()
                + " --> " + user.getName()
                + ": " + message);
    }

    public void run() {

        try {
            while (running) {

                synchronized (this) {

                    while (suspended) {
                        wait();
                    }
                }

                System.out.println(getName() + " is ready to chat");

                Thread.sleep(500);
            }

        } catch (InterruptedException e) {
            // Thread interrupted
        }
    }
}


public class ChatSystem {

    public static void main(String[] args) throws Exception {

        ChatUser alice = new ChatUser("Alice");
        ChatUser bob = new ChatUser("Bob");
        ChatUser charlie = new ChatUser("Charlie");

        alice.start();
        bob.start();
        charlie.start();

        Thread.sleep(1000);

        ChatUser.sendMessage(bob, "Hello Bob!", false);

        ChatUser.sendMessage(
                charlie,
                "Important Message!",
                true
        );

        System.out.println("Alice alive: " + alice.isAlive());
        System.out.println("Bob alive: " + bob.isAlive());

        charlie.suspendUser();

        Thread.sleep(1000);

        alice.resumeUser();
        bob.resumeUser();
        charlie.resumeUser();

        // Terminate the threads so join() can complete
        alice.terminateUser();
        bob.terminateUser();
        charlie.terminateUser();

        alice.join();
        bob.join();
        charlie.join();

        System.out.println("All Threads Finished");
    }
}