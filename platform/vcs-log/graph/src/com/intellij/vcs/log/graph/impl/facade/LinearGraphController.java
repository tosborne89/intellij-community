/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.vcs.log.graph.impl.facade;

import com.intellij.vcs.log.graph.actions.GraphAction;
import com.intellij.vcs.log.graph.actions.GraphAnswer;
import com.intellij.vcs.log.graph.api.LinearGraph;
import com.intellij.vcs.log.graph.collapsing.CollapsedGraph;
import com.intellij.vcs.log.graph.impl.print.elements.PrintElementWithGraphElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Set;

public interface LinearGraphController {

  @NotNull
  LinearGraph getCompiledGraph();

  @NotNull
  LinearGraphAnswer performLinearGraphAction(@NotNull LinearGraphAction action);

  interface LinearGraphAction extends GraphAction {
    @Nullable
    @Override
    PrintElementWithGraphElement getAffectedElement();
  }

  // Integer = nodeId
  class LinearGraphAnswer implements GraphAnswer<Integer> {
    @Nullable private final GraphChanges<Integer> myGraphChanges;
    @Nullable private final Cursor myCursorToSet;
    @Nullable private final Integer myCommitToJump;
    @Nullable private final Set<Integer> mySelectedNodeIds;

    public LinearGraphAnswer(@Nullable GraphChanges<Integer> graphChanges,
                             @Nullable Cursor cursorToSet,
                             @Nullable Integer commitToJump,
                             @Nullable Set<Integer> selectedNodeIds) {
      myGraphChanges = graphChanges;
      myCursorToSet = cursorToSet;
      myCommitToJump = commitToJump;
      mySelectedNodeIds = selectedNodeIds;
    }

    @Nullable
    public GraphChanges<Integer> getGraphChanges() {
      return myGraphChanges;
    }

    @Nullable
    @Override
    public Cursor getCursorToSet() {
      return myCursorToSet;
    }

    @Nullable
    @Override
    public Integer getCommitToJump() {
      return myCommitToJump;
    }

    @Nullable
    @Override
    public Runnable getGraphUpdater() {
      return null;
    }

    @Nullable
    public Set<Integer> getSelectedNodeIds() {
      return mySelectedNodeIds;
    }
  }

}
