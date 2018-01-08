/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.cloud.tools.crepecake.cache;

import com.google.common.annotations.VisibleForTesting;
import java.nio.file.attribute.FileTime;
import java.util.List;
import javax.annotation.Nullable;

/** Metadata about a layer stored in the cache. This is part of the {@link CacheMetadata}. */
class LayerMetadata {

  /** The type of layer. */
  private final CachedLayerType type;

  /** The paths to the source files that the layer was constructed from. */
  private List<String> sourceFiles;

  /** The last time the layer was constructed, or {@code null} if unknown. */
  @Nullable private FileTime lastModifiedTime;

  /** {@code sourceFiles} must be empty if and only if {@code lastModifiedTime} is {@code null}. */
  LayerMetadata(
      CachedLayerType type, List<String> sourceFiles, @Nullable FileTime lastModifiedTime) {
    if ((sourceFiles.size() == 0) != (lastModifiedTime == null)) {
      throw new IllegalArgumentException(
          "lastModifiedTime ("
              + lastModifiedTime
              + ") must be null if and only if sourceFiles ("
              + sourceFiles
              + ")are not specified");
    }

    this.type = type;
    this.sourceFiles = sourceFiles;
    this.lastModifiedTime = lastModifiedTime;
  }

  CachedLayerType getType() {
    return type;
  }

  List<String> getSourceFiles() {
    return sourceFiles;
  }

  @Nullable
  public FileTime getLastModifiedTime() {
    return lastModifiedTime;
  }

  @VisibleForTesting
  void setSourceFiles(List<String> sourceFiles) {
    this.sourceFiles = sourceFiles;
  }
}
