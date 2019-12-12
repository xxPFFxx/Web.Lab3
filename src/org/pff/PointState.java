package org.pff;

public enum PointState {
    IN, OUT, ODZ;
    public static PointState ofInt(int i){
        switch (i){
            case 0: return IN;
            case 1: return OUT;
            default: return ODZ;
        }
    }
    public static int toInt(PointState i){
        switch (i){
            case IN: return 0;
            case OUT: return 1;
            default: return 2;
        }
    }
}
