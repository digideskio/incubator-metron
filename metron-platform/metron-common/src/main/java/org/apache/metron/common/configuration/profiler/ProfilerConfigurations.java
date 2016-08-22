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
package org.apache.metron.common.configuration.profiler;

import org.apache.metron.common.configuration.ConfigurationType;
import org.apache.metron.common.configuration.Configurations;
import org.apache.metron.common.utils.JSONUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by nallen on 7/28/16.
 */
public class ProfilerConfigurations extends Configurations {

  public ProfilerConfig getProfilerConfig() {
    return (ProfilerConfig) configurations.get(getKey());
  }

  public void updateProfilerConfig(byte[] data) throws IOException {
    updateProfilerConfig(new ByteArrayInputStream(data));
  }

  public void updateProfilerConfig(InputStream io) throws IOException {
    ProfilerConfig config = JSONUtils.INSTANCE.load(io, ProfilerConfig.class);
    updateProfilerConfig(config);
  }

  public void updateProfilerConfig(ProfilerConfig config) {
    configurations.put(getKey(), config);
  }

  private String getKey() {
    return ConfigurationType.PROFILER.getName();
  }
}
