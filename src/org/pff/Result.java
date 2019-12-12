package org.pff;



import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="results")
public class Result implements Serializable {
    private static final long serialVersionUID = -5170875020617735653L;
    @Column(name="X")
    private double x;
    @Column(name="Y")
    private double y;
    @Column(name="R")
    private double r;
    @Id
    @GeneratedValue(generator="GENERATOR_COMMON")
    @GenericGenerator(name="GENERATOR_COMMON",strategy="increment")
    @Column(name="RESID")
    private int resultID;

    public int getResultID() {
        return resultID;
    }

    public void setResultID(int resultID) {
        this.resultID = resultID;
    }




    public Result() {
        this(0.0, 0.0, 2.0, PointState.IN, "Auto-Generated");
    }


    @Column(name="SESSIONID")
    public String sessionID;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    @Column(name="CHECKING")
    private int checking;

    public int getChecking() {
        return checking;
    }

    public void setChecking(int checking) {
        this.checking = checking;
    }

    @Transient
    private PointState match; //
    @Transient
    private String smatch;

    public Result(double x, double y, double r, PointState match, String SessionID) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.match = match;

        sessionID = SessionID;
        checking = PointState.toInt(match);
        smatch = genStringMatch(match);

    }
    // checking - int PointState.toInt(match);, changes match and smatch
    // match - change checking and smatch
    // smatch - doesn't change anything
    // old match - not affected

    public static String genStringMatch(PointState match){
        return (match == PointState.IN)?"Попал":((match == PointState.OUT)?"Не попал":"Не в одз");
    }
    public String getSmatch() {
        return smatch;
    }

    public void setSmatch(String smatch) {
        this.smatch = smatch;

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public PointState getMatch() {
        return match;
    }

    public void setMatch(PointState match) {
        this.match = match;
        smatch = genStringMatch(match);
    }
    public static String drawPointJS(float x, float y, String col){
        return "drawPoint("+Float.toString(x) + ", " + Float.toString(y) + ", "+col+");";
    }
    public String getJSColor(){
        return match == PointState.IN ? ("'#00FF00'"):((PointState.OUT == match)?"'#FF0000'":"'#AFAFAF'");
    }
    public static String getJSColor(PointState match){
        return match == PointState.IN ? ("'#00FF00'"):((PointState.OUT == match)?"'#FF0000'":"'#AFAFAF'");
    }

}
