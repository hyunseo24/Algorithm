/*
MAXHEAP 구현하기
*/

import java.util.Scanner;

public class MaxHeap {
    int[] elements;
    int n;
    
    public MaxHeap(int size) {
        this.elements = new int[size+1];
        this.n = 0;
    }
    
    public boolean isFull() {
        return n == elements.length - 1;
    }
    
    public boolean isEmpty() {
        return n == 0;
    }
    
    public void insert(int data) {
        if(this.isFull()) {
            throw new RuntimeException("Heap is full");
        }
        
        elements[++n] = data;
        int child = n;
        int parent = child / 2;
        
        while(parent > 0 && elements[parent] < elements[child]) {
            swap(parent, child);
            child = parent;
            parent = child / 2;
        }
    }
    
    public int remove() {
        if(this.isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        
        int removeElement = elements[1];
        elements[1] = elements[n--];
        heapify(1);
        
        return removeElement;
    }
    
    private void heapify(int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int greatest = i;
        
        if(left <= n && elements[left] > elements[greatest]) {
            greatest = left;
        }
        
        if(right <= n && elements[right] > elements[greatest]) {
            greatest = right;
        }
        
        if(greatest != i)
        {
            swap(i, greatest);
            heapify(greatest);
        }
    }
    
    private void swap(int i, int j) {
        int temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    public void preOrder(int i) {
        if (i > n) {
            return;
        }
        System.out.print(elements[i] + " ");
        preOrder(2 * i);
        preOrder(2 * i + 1);
    }

    public void inOrder(int i) {
        if (i > n) {
            return;
        }
        inOrder(2 * i);
        System.out.print(elements[i] + " ");
        inOrder(2 * i + 1);
    }

    public void postOrder(int i) {
        if (i > n) {
            return;
        }
        postOrder(2 * i);
        postOrder(2 * i + 1);
        System.out.print(elements[i] + " ");
    }

    public static void traverseHeap(MaxHeap heap) {
        System.out.print("Pre-Order : ");
        heap.preOrder(1);
        System.out.println();
        System.out.print("In-Order : ");
        heap.inOrder(1);
        System.out.println();
        System.out.print("Post-Order : ");
        heap.postOrder(1);
        System.out.println();
    }
    
    public static void removeElements(MaxHeap heap) {
        System.out.print("Output : ");
        while(!heap.isEmpty()) {
            int data = heap.remove();
            System.out.print(data);
            System.out.print(" ");
        }
        System.out.println(" ");
    }
    
    public static void main(String[] args) {
        int[] example = {23, 39, 7, 20, 14, 15, 46, 66, 75, 49, 32};
        
        MaxHeap heap = new MaxHeap(example.length);
        for(int element: example) {
            heap.insert(element);
        }

        traverseHeap(heap);
        removeElements(heap);
        
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        heap = new MaxHeap(n);
        for(int i=0;i<n;i++) {
            heap.insert( input.nextInt() );
        }
        input.close();

        traverseHeap(heap);
        removeElements(heap);
    }
}
