/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.metron.integration;

import java.util.ArrayList;
import java.util.List;

public class ProcessorResult<T> {
    public static class Builder<T>{
        T result;
        List<byte[]> processErrors;
        List<byte[]> processInvalids;

        public Builder(){}

        public Builder withResult(T result){
            this.result = result;
            return this;
        }

        public Builder withProcessErrors(List<byte[]> processErrors){
            this.processErrors = processErrors;
            return this;
        }

        public Builder withProcessInvalids(List<byte[]> processInvalids){
            this.processInvalids = processInvalids;
            return this;
        }

        public ProcessorResult<T> build(){
            return new ProcessorResult<T>(result,processErrors,processInvalids);
        }

    }

    T result;
    List<byte[]> processErrors;
    List<byte[]> processInvalids;
    @SuppressWarnings("unchecked")
    public ProcessorResult(T result,List<byte[]> processErrors, List<byte[]> processInvalids){
        this.result = result;
        this.processErrors = processErrors == null ? new ArrayList() : processErrors;
        this.processInvalids = processInvalids == null ? new ArrayList() : processInvalids;
    }

    public T getResult(){
        return result;
    }

    public List<byte[]> getProcessErrors(){
        return processErrors;
    }

    public List<byte[]> getProcessInvalids(){
        return processInvalids;
    }

    public boolean failed(){
        return processErrors.size() > 0 || processInvalids.size() > 0;
    }

    public void getBadResults(StringBuffer buffer){
        if(buffer == null){
            return;
        }
        buffer.append(String.format("%d Errors", processErrors.size()));
        for (byte[] outputMessage : processErrors) {
            buffer.append(new String(outputMessage));
        }
        buffer.append("\n");
        buffer.append(String.format("%d Invalid Messages", processInvalids.size()));
        for (byte[] outputMessage : processInvalids) {
            buffer.append(new String(outputMessage));
        }
        buffer.append("\n");
    }
}
