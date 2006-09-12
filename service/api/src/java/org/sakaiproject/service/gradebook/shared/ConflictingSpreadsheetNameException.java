/*******************************************************************************
 * Copyright (c) 2006 The Regents of the University of California
 *
 *  Licensed under the Educational Community License, Version 1.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.opensource.org/licenses/ecl1.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/

package org.sakaiproject.service.gradebook.shared;

/**
 * Author:Louis Majanja <louis@media.berkeley.edu>
 * Date: Aug 29, 2006
 * Time: 4:40:02 PM
 */
public class ConflictingSpreadsheetNameException extends GradebookException {

    public ConflictingSpreadsheetNameException(String message) {
        super(message);
    }
}