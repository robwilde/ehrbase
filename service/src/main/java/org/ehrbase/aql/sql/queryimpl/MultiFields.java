/*
 *  Copyright (c) 2020 Vitasystems GmbH and Christian Chevalley (Hannover Medical School).
 *
 *  This file is part of project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and  limitations under the License.
 *
 */

package org.ehrbase.aql.sql.queryimpl;

import org.ehrbase.aql.definition.I_VariableDefinition;
import org.jooq.Field;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiFields {

    private final List<QualifiedAqlField> fields = new ArrayList<>();
    private boolean useEntryTable = false;
    private String rootJsonKey;
    private final String templateId;
    private I_VariableDefinition variableDefinition; // the variable def for this list of qualified fields

    public MultiFields(I_VariableDefinition variableDefinition, List<QualifiedAqlField> fields, String templateId) {
        this.fields.addAll(fields);
        this.variableDefinition = variableDefinition;
        this.templateId = templateId;
    }

    public MultiFields(I_VariableDefinition variableDefinition, Field<?> field, String templateId) {
        this(variableDefinition, new QualifiedAqlField(field), templateId);
    }

    public MultiFields() {
        this(null, new QualifiedAqlField(null), null);
    }

    public MultiFields(I_VariableDefinition variableDefinition, QualifiedAqlField field, String templateId) {
        fields.add(field);
        this.variableDefinition = variableDefinition;
        this.templateId = templateId;
    }

    public void setUseEntryTable(boolean useEntryTable) {
        this.useEntryTable = useEntryTable;
    }

    public boolean isUseEntryTable() {
        return useEntryTable;
    }

    public int fieldsSize(){
        return fields.size();
    }

    private QualifiedAqlField getQualifiedField(int index){
        return fields.get(index);
    }

    public Iterator<QualifiedAqlField> iterator(){
        return fields.iterator();
    }

    public QualifiedAqlField getLastQualifiedField(){
        return fields.get(fieldsSize() - 1);
    }

    public QualifiedAqlField getQualifiedFieldOrLast(int index){
        if (index >= fieldsSize())
            return getLastQualifiedField();
        else
            return getQualifiedField(index);
    }

    public int size(){
        return fields.size();
    }

    public boolean isEmpty(){
        return fields.isEmpty();
    }

    public String getRootJsonKey() {
        return rootJsonKey;
    }

    public void setRootJsonKey(String rootJsonKey) {
        this.rootJsonKey = rootJsonKey;
    }

    public I_VariableDefinition getVariableDefinition() {
        return variableDefinition;
    }

    public String getTemplateId() {
        return templateId;
    }
}
