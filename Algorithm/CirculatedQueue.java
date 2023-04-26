// 환형 큐 구현

import java.util.Scanner;


public class CircularQueue<T> {
    private T[] queue;
    private int front;
    private int rear;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.queue = (T[]) new Object[capacity];
        this.front = -1;
        this.rear = -1;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void enqueue(T value) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }

        if (isEmpty()) {
            front = 0;
        }

        rear = (rear + 1) % capacity;
        queue[rear] = value;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        T value = queue[front];
        queue[front] = null;
        front = (front + 1) % capacity;
        size--;

        if (isEmpty()) {
            front = -1;
            rear = -1;
        }

        return value;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        return queue[front];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the capacity of the circular queue:");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        CircularQueue<Integer> queue = new CircularQueue<>(capacity);

        while (true) {
            System.out.println("Choose an operation: enqueue, dequeue, peek, exit");
            String operation = scanner.nextLine().toLowerCase();

            switch (operation) {
                case "enqueue":
                    System.out.println("Enter an integer to enqueue:");
                    int numberToAdd = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        queue.enqueue(numberToAdd);
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "dequeue":
                    try {
                        int dequeuedNumber = queue.dequeue();
                        System.out.println("Dequeued: " + dequeuedNumber);
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "peek":
                    try {
                        int peekedNumber = queue.peek();
                        System.out.println("Front of the queue: " + peekedNumber);
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid operation. Try again.");
            }
        }
    }
}
