package com.gcd.w40k.tableunits;

public class HollowModelException extends RuntimeException {
    @Override
    public String getMessage() {
        return "This model is hollow. It's not usable unless you fill the gaps";
    }
}
