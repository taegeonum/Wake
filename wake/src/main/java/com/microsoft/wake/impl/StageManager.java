/**
 * Copyright (C) 2012 Microsoft Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microsoft.wake.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.microsoft.wake.Stage;


/**
 * A manager that manages all the stage
 */
public final class StageManager implements Stage {

  private static final Logger LOG = Logger.getLogger(StageManager.class.getName());
  private final UUID id = UUID.randomUUID();
  
  private static StageManager instance = new StageManager();
  private final Map<Stage, String> stageNames;
  private final List<Stage> stages;
  private final AtomicBoolean closed = new AtomicBoolean(false);
  
  StageManager() {

    stages = Collections.synchronizedList(new ArrayList<Stage>());
    stageNames = Collections.synchronizedMap(new HashMap<Stage, String>());
    
    LOG.log(Level.FINE, "StageManager {0} adds a shutdown hook", id);
    Runtime.getRuntime().addShutdownHook(new Thread(
      new Runnable() {
        @Override
        public void run() {
          try {
            LOG.log(Level.FINEST, "{0} Shutdown hook : closing stages", id);
            StageManager.instance().close();
            LOG.log(Level.FINEST, "{0} Shutdown hook : closed stages", id);
          } catch (Exception e) {
            LOG.log(Level.WARNING, "StageManager {0} close failure {1}", new Object[] {id, e.getMessage()});
          }
        }
      }
    ));
  }
  
  public static StageManager instance() {
    return instance;
  }
  
  @Deprecated
  public void register(Stage stage) {
    LOG.log(Level.FINE, "StageManager {0} adds stage {1}", new Object[] {id, stage.getClass().getName()});

    stages.add(stage);
    stageNames.put(stage, "unknown");
  }
  
  public void register(String name, Stage stage) {
    LOG.log(Level.FINE, "StageManager {0} adds stage {1} name {2}", new Object[] {id, stage.getClass().getName(), name});

    stages.add(stage);
    stageNames.put(stage, name);
  }
  
  @Override
  public void close() throws Exception {
    if (closed.compareAndSet(false, true)) {
      for (final Stage stage : stages) {
        final String name = stageNames.get(stage);
        LOG.log(Level.FINEST, "{0} Closing stage {1} name {2}", new Object[] {id, stage.getClass().getName(), name});
        stage.close();
      }
    }
  }
 
}
