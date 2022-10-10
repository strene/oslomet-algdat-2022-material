import com.sun.source.tree.WhileLoopTree;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;

public class CircularBuffer {

    char[] buffer;
    int size;
    int head;
    int tail;
    int count;

    CircularBuffer(int size){

        this.buffer = new char[size];
        this.size = size;
        this.head = 0;
        this.tail = 0;
        this.count = 0;

    }

    void pushFront(char value){

        if (count + 1 > size){throw new IndexOutOfBoundsException();}
        head = head - 1;
        if (head < 0) head = size + head;
        buffer[head] = value;
        count = count + 1;

    }

    void pushBack(char value){

        if (count + 1 > size){throw new IndexOutOfBoundsException();}
        buffer[tail] = value;
        tail = (tail + 1)%size;
        count = count + 1;

    }

    char popFront(){

        if (count <= 0){throw new IndexOutOfBoundsException();}
        char out = buffer[head];
        buffer[head] = '*';
        head = (head + 1)%size;
        count = count - 1;
        return out;

    }

    char popBack(){

        if (count <= 0){throw new IndexOutOfBoundsException();}
        tail = tail - 1;
        if (tail < 0) tail = size + tail;
        char out = buffer[tail];
        buffer[tail] = '*';
        count = count - 1;
        return out;

    }

    int count(){
        return count;
    }

    void print(){
        System.out.println();
        System.out.println(buffer);
        for (int i = 0; i < size; i++){
            if (i == head) System.out.print("h");
            else if (i == tail) System.out.print("t");
            else System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args){

        // [* * * A B C D * * * *]
        //        h       t

        CircularBuffer buffer = new CircularBuffer(30);
        char[] values = ("OLKNAPUJSKENPØZEKA").toCharArray();

        for (int i = 0; i < values.length; ){
            for (int j = 0; j < 6; j++){
                if (values.length > i+j){
                    buffer.pushBack(values[i+j]);
                }
            }
            int c0 = buffer.count;
            while (buffer.count() > c0 - 3){
                System.out.print(buffer.popBack());
            }
            System.out.println();
            i += 6;
        }

        while (buffer.count > 0){
            System.out.println(buffer.popBack());
        }

        buffer.print();
        char[] c = "REMTIROGLA GO DATASTRUKTURER".toCharArray();
        for (int i =14; i < c.length; i++){
            buffer.pushBack(c[i]);
            buffer.print();
        }

        for (int i = 0; i < 13; i++){
            buffer.pushFront(c[i]);
            buffer.print();
        }

        while (buffer.count > 0) {
            if (buffer.count %2 == 0) buffer.popBack();
            else buffer.popFront();
            buffer.print();
        }

        for (int i = 0; i < c.length; i++){
            buffer.pushBack(c[i]);
        }
        buffer.print();
        for (int i = 0; i < c.length; i++){
            buffer.pushBack(buffer.popFront());
            buffer.print();
        }

    }


}
