package org.pff.beans;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.primefaces.PrimeFaces;
import org.pff.ParamsManager;
import org.pff.PointState;
import org.pff.Result;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.pff.Result.drawPointJS;

@ApplicationScoped
@ManagedBean(name="result")
public class ResultBean implements Serializable {
    private final String sessionID;

    private boolean curX1 = false;
    private boolean curX2 = false;
    private boolean curX3 = false;
    private boolean curX4 = false;
    private boolean curX5 = false;
    private boolean curX6 = false;
    private boolean curX7 = false;
    private double curY = 0;
    private double curR = 2;

    public boolean isCurX1() {
        return curX1;
    }

    public void setCurX1(boolean curX1) {
        this.curX1 = curX1;
    }

    public boolean isCurX2() {
        return curX2;
    }

    public void setCurX2(boolean curX2) {
        this.curX2 = curX2;
    }

    public boolean isCurX3() {
        return curX3;
    }

    public void setCurX3(boolean curX3) {
        this.curX3 = curX3;
    }

    public boolean isCurX4() {
        return curX4;
    }

    public void setCurX4(boolean curX4) {
        this.curX4 = curX4;
    }

    public boolean isCurX5() {
        return curX5;
    }

    public void setCurX5(boolean curX5) {
        this.curX5 = curX5;
    }

    public boolean isCurX6() {
        return curX6;
    }

    public void setCurX6(boolean curX6) {
        this.curX6 = curX6;
    }

    public boolean isCurX7() {
        return curX7;
    }

    public void setCurX7(boolean curX7) {
        this.curX7 = curX7;
    }

    public double getCurY() {
        return curY;
    }

    public void setCurY(double curY) {
        this.curY = curY;
    }

    public double getCurR() {
        return curR;
    }

    public void setCurR(double curZ) {
        this.curR = curZ;
    }

    ArrayList<Result> data;
    private Session ormSession;

    private float number = 2;

    public ResultBean() throws SQLException, ClassNotFoundException {
        data = new ArrayList<>();
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(true);



        sessionID = session.getId();
        try {
            Configuration configuration = new Configuration();
            configuration.configure("main/resources/hibernate.cfg.xml");
            configuration.addAnnotatedClass(org.pff.Result.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            ormSession = configuration.buildSessionFactory(serviceRegistry).openSession();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void checkClick() {
        try {
            Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

            String sx = requestParameterMap.get("pX").replace(',','.');
            String sy = requestParameterMap.get("pY").replace(',','.');
            String sr = requestParameterMap.get("pR").replace(',','.');
            //String sr2 = requestParameterMap.get("myForm:param-r").replace(',','.');

            float x= Float.parseFloat(sx);
            float y= Float.parseFloat(sy);
            float r= Float.parseFloat(sr);
            String col;
            if(ParamsManager.isValidR(r) && ParamsManager.isValidX(x) && ParamsManager.isValidY(y)) {
                if (ParamsManager.isInArea(x, y, r)) {
                    col = "'#00FF00'";
                    addPoint(x,y,r, PointState.IN);

                } else {
                    col = "'#FF0000'";
                    addPoint(x,y,r, PointState.OUT);
                }
            }
            else
            {
                addPoint(x,y,r, PointState.ODZ);
                col = "'#AFAFAF'";
            }
            PrimeFaces.current().executeScript(drawPointJS(x, y, col));
            //return addResult(x,y,r);



        } catch (Exception e) {
            e.printStackTrace();
            //return "check";
        }
        return;
    }
    public void callRedraw(){
        try {
            Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            data.clear();
            String sr = requestParameterMap.get("pR").replace(',', '.');



        }
        catch(Exception e){

        }
    }
    private void addPoint(double x, double y, double r, PointState match)
    {
        try{

            ormSession.getTransaction().begin();

            ormSession.save(new Result(x,y,r, match,sessionID));

            ormSession.getTransaction().commit();


            data.add(new Result(x,y,r, match,sessionID));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<Result> getAllResults(){
        System.out.println("Triggered");
        ArrayList<Result> temp = new ArrayList<>(data);
        Collections.reverse(temp);
        CriteriaBuilder builder = ormSession.getCriteriaBuilder();

        CriteriaQuery<Result> cquery = builder.createQuery(Result.class);

        Root<Result> root = cquery.from( Result.class );
        cquery.select( root );
        cquery.where(builder.equal( root.get("sessionID"), sessionID ));
        List<Result> persons = ormSession.createQuery( cquery ).getResultList();
        Collections.reverse(persons);
        return persons;
    }
    public void addResult(){
        try{
            Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

            String sx = requestParameterMap.get("myForm:param-x_input").replace(',','.');
            String sy = requestParameterMap.get("myForm:param-y").replace(',','.');
            String sr = requestParameterMap.get("myForm:param-r").replace(',','.');

            float x= Float.parseFloat(sx);
            float y= Float.parseFloat(sy);
            float r= Float.parseFloat(sr);
            addPoint(x,y,r,ParamsManager.getPointState(x, y, r));

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
