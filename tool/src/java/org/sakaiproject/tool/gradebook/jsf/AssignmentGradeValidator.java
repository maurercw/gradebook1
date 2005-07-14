/**********************************************************************************
*
* $Id$
*
***********************************************************************************
*
* Copyright (c) 2003, 2004, 2005 The Regents of the University of Michigan, Trustees of Indiana University,
*                  Board of Trustees of the Leland Stanford, Jr., University, and The MIT Corporation
* 
* Licensed under the Educational Community License Version 1.0 (the "License");
* By obtaining, using and/or copying this Original Work, you agree that you have read,
* understand, and will comply with the terms and conditions of the Educational Community License.
* You may obtain a copy of the License at:
* 
*      http://cvs.sakaiproject.org/licenses/license_1_0.html
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
* INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
* AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
* DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*
**********************************************************************************/


package org.sakaiproject.tool.gradebook.jsf;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Validates assignment grades entered into the gradebook.  Since we display a
 * maximum of two decimal places in the UI, we use this validator to ensure that
 * the maximum precision entered into the gradebook is also two decimal places.
 * This should reduce rounding errors between actual scores and what is displayed
 * in the UI.
 *
 * @author <a href="mailto:jholtzman@berkeley.edu">Josh Holtzman </a>
 */
public class AssignmentGradeValidator implements Validator, Serializable {
	private static Log logger = LogFactory.getLog(AssignmentGradeValidator.class);

    /**
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.Object)
	 */
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value != null) {
			if (!(value instanceof Number)) {
				throw new IllegalArgumentException("The assignment grade must be a number");
			}
			double grade = ((Number)value).doubleValue();
            BigDecimal bd = new BigDecimal(grade);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP); // Two decimal places
            double roundedVal = bd.doubleValue();
            double diff = grade - roundedVal;
            if(diff != 0) {
                throw new ValidatorException(new FacesMessage(
                	FacesUtil.getLocalizedString(context, "org.sakaiproject.gradebook.tool.jsf.AssignmentGradeValidator.PRECISION")));
            }
		}
	}
}


