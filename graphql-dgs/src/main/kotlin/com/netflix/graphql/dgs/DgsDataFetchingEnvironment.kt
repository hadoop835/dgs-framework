/*
 * Copyright 2022 Netflix, Inc.
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

package com.netflix.graphql.dgs

import com.netflix.graphql.dgs.context.DgsContext
import com.netflix.graphql.dgs.exceptions.MultipleDataLoadersDefinedException
import com.netflix.graphql.dgs.exceptions.NoDataLoaderFoundException
import com.netflix.graphql.dgs.internal.utils.DataLoaderNameUtil
import graphql.schema.DataFetchingEnvironment
import org.dataloader.DataLoader

class DgsDataFetchingEnvironment(
    private val dfe: DataFetchingEnvironment,
) : DataFetchingEnvironment by dfe {
    fun getDfe(): DataFetchingEnvironment = this.dfe

    fun getDgsContext(): DgsContext = DgsContext.from(this)

    /**
     * Get the value of the current object to be queried.
     *
     * @throws IllegalStateException if called on the root query
     */
    fun <T> getSourceOrThrow(): T = getSource() ?: throw IllegalStateException("source is null")

    fun <K, V> getDataLoader(loaderClass: Class<*>): DataLoader<K, V> {
        val annotation = loaderClass.getAnnotation(DgsDataLoader::class.java)
        val loaderName =
            if (annotation != null) {
                DataLoaderNameUtil.getDataLoaderName(loaderClass, annotation)
            } else {
                val loaders = loaderClass.fields.filter { it.isAnnotationPresent(DgsDataLoader::class.java) }
                if (loaders.size > 1) throw MultipleDataLoadersDefinedException(loaderClass)
                val loaderField = loaders.firstOrNull() ?: throw NoDataLoaderFoundException(loaderClass)
                val theAnnotation = loaderField.getAnnotation(DgsDataLoader::class.java)
                theAnnotation.name
            }
        return getDataLoader(loaderName) ?: throw NoDataLoaderFoundException("DataLoader with name $loaderName not found")
    }
}
