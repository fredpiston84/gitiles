// Copyright 2012 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.gitiles;

import static com.google.common.base.Preconditions.checkNotNull;

import org.eclipse.jgit.storage.dfs.DfsRepository;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/** Gitiles access for testing. */
public class TestGitilesAccess implements GitilesAccess.Factory {
  private final DfsRepository repo;

  public TestGitilesAccess(DfsRepository repo) {
    this.repo = checkNotNull(repo);
  }

  @Override
  public GitilesAccess forRequest(final HttpServletRequest req) {
    return new GitilesAccess() {
      @Override
      public Map<String, RepositoryDescription> listRepositories(Set<String> branches) {
        // TODO(dborowitz): Implement this, using the DfsRepositoryDescriptions to
        // get the repository names.
        throw new UnsupportedOperationException();
      }

      @Override
      public Object getUserKey() {
        return "a user";
      }

      @Override
      public String getRepositoryName() {
        return repo.getDescription().getRepositoryName();
      }

      @Override
      public RepositoryDescription getRepositoryDescription() {
        RepositoryDescription d = new RepositoryDescription();
        d.name = getRepositoryName();
        d.description = "a test data set";
        d.cloneUrl = TestGitilesUrls.URLS.getBaseGitUrl(req) + "/" + d.name;
        return d;
      }
    };
  }
}
