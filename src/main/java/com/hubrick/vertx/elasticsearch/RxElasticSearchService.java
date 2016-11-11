/**
 * Copyright (C) 2016 Etaia AS (oss@hubrick.com)
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
package com.hubrick.vertx.elasticsearch;

import com.hubrick.vertx.elasticsearch.impl.DefaultRxElasticSearchService;
import com.hubrick.vertx.elasticsearch.model.DeleteByQueryOptions;
import com.hubrick.vertx.elasticsearch.model.DeleteOptions;
import com.hubrick.vertx.elasticsearch.model.GetOptions;
import com.hubrick.vertx.elasticsearch.model.IndexOptions;
import com.hubrick.vertx.elasticsearch.model.SearchOptions;
import com.hubrick.vertx.elasticsearch.model.SearchScrollOptions;
import com.hubrick.vertx.elasticsearch.model.SuggestOptions;
import com.hubrick.vertx.elasticsearch.model.UpdateOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import rx.Observable;

import java.util.Collections;
import java.util.List;

/**
 * ElasticSearch RX service
 *
 * @author Emir Dizdarevic
 * @since 1.0.0
 */
public interface RxElasticSearchService {

    static RxElasticSearchService createEventBusProxy(Vertx vertx, String address) {
        return new DefaultRxElasticSearchService(ElasticSearchService.createEventBusProxy(vertx, address));
    }

    default Observable<JsonObject> index(String index, String type, JsonObject source) {
        return index(index, type, source, new IndexOptions());
    }

    Observable<JsonObject> index(String index, String type, JsonObject source, IndexOptions options);

    Observable<JsonObject> update(String index, String type, String id, UpdateOptions options);

    default Observable<JsonObject> get(String index, String type, String id) {
        return get(index, type, id, new GetOptions());
    }

    Observable<JsonObject> get(String index, String type, String id, GetOptions options);

    default Observable<JsonObject> search(String index) {
        return search(index, new SearchOptions());
    }

    default Observable<JsonObject> search(String index, SearchOptions options) {
        return search(Collections.singletonList(index), options);
    }

    default Observable<JsonObject> search(List<String> indices) {
        return search(indices, new SearchOptions());
    }

    Observable<JsonObject> search(List<String> indices, SearchOptions options);

    default Observable<JsonObject> searchScroll(String scrollId) {
        return searchScroll(scrollId, new SearchScrollOptions());
    }

    Observable<JsonObject> searchScroll(String scrollId, SearchScrollOptions options);

    default Observable<JsonObject> delete(String index, String type, String id) {
        return delete(index, type, id, new DeleteOptions());
    }

    Observable<JsonObject> delete(String index, String type, String id, DeleteOptions options);

    default Observable<JsonObject> suggest(String index, SuggestOptions options) {
        return suggest(Collections.singletonList(index), options);
    }

    default Observable<JsonObject> suggest(String index) {
        return suggest(Collections.singletonList(index), new SuggestOptions());
    }

    default Observable<JsonObject> suggest(List<String> indices) {
        return suggest(indices, new SuggestOptions());
    }

    Observable<JsonObject> suggest(List<String> indices, SuggestOptions options);

    default Observable<JsonObject> deleteByQuery(String index, JsonObject query, DeleteByQueryOptions options) {
        return deleteByQuery(Collections.singletonList(index), query, options);
    }

    default Observable<JsonObject> deleteByQuery(String index, JsonObject query) {
        return deleteByQuery(Collections.singletonList(index), query, new DeleteByQueryOptions());
    }

    default Observable<JsonObject> deleteByQuery(List<String> indices, JsonObject query) {
        return deleteByQuery(indices, query, new DeleteByQueryOptions());
    }

    Observable<JsonObject> deleteByQuery(List<String> indices, JsonObject query, DeleteByQueryOptions options);
}
