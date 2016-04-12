package com.renrawnalon.computersim.computer;

/**
 * Created by renrawnalon on 16/04/12.
 */
public class MathHelper {
    static boolean willMultiplicationOverflow(Integer lhs, Integer rhs) {
        if (lhs == 0 || rhs == 0) {
            return false;
        } else if (lhs > 0 && rhs > 0) {
            return lhs > Integer.MAX_VALUE / rhs;
        } else if (rhs < 0 && lhs < 0) {
            return lhs < Integer.MAX_VALUE / rhs;
        } else {
            if (rhs > 0) {
                return lhs < Integer.MIN_VALUE / rhs;
            } else {
                return rhs < Integer.MIN_VALUE / lhs;
            }
        }
    }

    static boolean willAdditionOverflow(Integer lhs, Integer rhs) {
        if (lhs > 0 && rhs > 0) {
            return lhs > Integer.MAX_VALUE - rhs;
        } else if (lhs < 0 && rhs < 0) {
            return lhs < Integer.MIN_VALUE - rhs;
        }
        return false;
    }
}
