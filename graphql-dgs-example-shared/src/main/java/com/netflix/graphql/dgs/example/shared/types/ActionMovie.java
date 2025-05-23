/*
 * Copyright 2025 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.graphql.dgs.example.shared.types;

public class ActionMovie implements Movie {
  private String title;

  private String director;

  private int nrOfExplosions;

  public ActionMovie() {
  }

  public ActionMovie(String title, String director, int nrOfExplosions) {
    this.title = title;
    this.director = director;
    this.nrOfExplosions = nrOfExplosions;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public int getNrOfExplosions() {
    return nrOfExplosions;
  }

  public void setNrOfExplosions(int nrOfExplosions) {
    this.nrOfExplosions = nrOfExplosions;
  }

  @Override
  public String toString() {
    return "ActionMovie{" + "title='" + title + "', " +"director='" + director + "', " +"nrOfExplosions='" + nrOfExplosions + "' " +"}";
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ActionMovie that = (ActionMovie) o;
      return java.util.Objects.equals(title, that.title) &&
              java.util.Objects.equals(director, that.director) &&
              nrOfExplosions == that.nrOfExplosions;
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(title, director, nrOfExplosions);
  }
}
