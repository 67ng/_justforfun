package imooc.datastructure.AVL;

/**
 * Description:
 *
 * @date: 2018/11/29 17:13
 */

import imooc.datastructure.Set_Map.Set.BSTSet;
import imooc.datastructure.Set_Map.Set.FileOperation;
import imooc.datastructure.Set_Map.Set.LinkedListSet;

import java.util.ArrayList;

public class TestSetMain {

    private static double testSet(Set<String> set, String filename) {

        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words)
                set.add(word);
            System.out.println("Total different words: " + set.getSize());
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        String filename = "D:\\视频\\算法\\imooc算法\\算法1：玩转数据结构 从入门到进阶\\源码\\07-Set-and-Map\\02-LinkedListSet\\pride-and-prejudice.txt";

     /*   BSTSet<String> bstSet = new BSTSet<>();
        double time1 = testSet(bstSet, filename);
        System.out.println("BST Set: " + time1 + " s");

        System.out.println();

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        double time2 = testSet(linkedListSet, filename);
        System.out.println("Linked List Set: " + time2 + " s");
*/
        System.out.println();

        AVLSet<String> avlSet = new AVLSet<>();
        double time3 = testSet(avlSet, filename);
        System.out.println("AVL Set: " + time3 + " s");
    }
}

