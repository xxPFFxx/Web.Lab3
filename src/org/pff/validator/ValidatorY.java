package org.pff.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ValidatorY")
public class ValidatorY implements javax.faces.validator.Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        System.out.println(value);
        Double y;
        try {
            y = new Double(value.toString());
            System.out.println(y);
            if (y.doubleValue() < -5.0d || y.doubleValue() > 5.0d) {
                throw new Exception();
            }
        }
        catch (IllegalArgumentException e) {
            FacesMessage msg =
                    new FacesMessage("Invalid value",
                            "Only digits can be used.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        catch (Exception e){
            FacesMessage msg =
                    new FacesMessage("Value out of bounds",
                            "Y must be in [-5;5]");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
}

