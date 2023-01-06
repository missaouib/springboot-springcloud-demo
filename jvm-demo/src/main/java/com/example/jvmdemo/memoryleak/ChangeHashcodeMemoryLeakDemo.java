package com.example.jvmdemo.memoryleak;

import java.util.HashSet;
import java.util.Objects;

/**
 * 改变hashCode
 */
public class ChangeHashcodeMemoryLeakDemo {

    public static void main(String[] args) {
        HashSet<Point> hs = new HashSet<>();
        Point cc = new Point();
        cc.setX(10);//hashCode=41
        hs.add(cc);
        cc.setX(20);//hashCode=51
        System.out.println("hs.remove = "+hs.remove(cc));//false
        hs.add(cc);
        System.out.println("hs.size = "+hs.size());//size = 2
    }

    static class Point {

        private int x;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        @Override
        public boolean equals(Object obj) {
            if (this==obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Point other = (Point) obj;
            if (x != other.x) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime*result + x;
            return result;
        }
    }
}
