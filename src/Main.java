import model.AVL;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AVL<Integer, Integer> avl = new AVL<>();
        int t = sc.nextInt();
        int numAdd = 0;
        for(int i = 0; i < t; i++){
            numAdd = sc.nextInt();
            avl.insert(numAdd, numAdd);
        }

        int n = sc.nextInt();
        avl.insert(n, n);
        System.out.println(avl.inOrder());
        System.out.println(avl.preOrder());

    }
}