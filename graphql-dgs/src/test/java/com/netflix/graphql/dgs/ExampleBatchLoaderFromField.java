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

package com.netflix.graphql.dgs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.dataloader.BatchLoader;

@DgsComponent
public class ExampleBatchLoaderFromField {
    @DgsDataLoader(name = "exampleLoaderFromField")
    public BatchLoader<String, String> batchLoader = keys -> CompletableFuture.supplyAsync(() -> {
        List<String> values = new ArrayList<>();
        values.add("a");
        values.add("b");
        values.add("c");
        return values;
    });

    @DgsDataLoader(name = "privateExampleLoaderFromField")
    BatchLoader<String, String> privateBatchLoader = keys -> CompletableFuture.supplyAsync(() -> {
        List<String> values = new ArrayList<>();
        values.add("a");
        values.add("b");
        values.add("c");
        return values;
    });
}
