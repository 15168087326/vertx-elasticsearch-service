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

import com.hubrick.vertx.elasticsearch.model.IndexOptions;
import com.hubrick.vertx.elasticsearch.model.OpType;
import com.hubrick.vertx.elasticsearch.model.RefreshPolicy;
import io.vertx.core.json.JsonObject;
import org.elasticsearch.index.VersionType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link IndexOptions}
 */
public class IndexOptionsTest {

    @Test
    public void testIndexOptions() throws Exception {

        IndexOptions options1 = new IndexOptions()
                .setId("test_id");

        JsonObject json1 = options1.toJson();
        assertEquals(1, json1.fieldNames().size());

        IndexOptions options2 = new IndexOptions(json1);
        JsonObject json2 = options2.toJson();

        assertEquals(json1.encode(), json2.encode());

        options1.setId("test_id")
                .setRouting("routing")
                .setParent("parent")
                .setOpType(OpType.CREATE)
                .setRefresh(RefreshPolicy.IMMEDIATE)
                .setVersion(2L)
                .setVersionType(VersionType.EXTERNAL)
                .setTimeout("timeout");

        json1 = options1.toJson();
        assertEquals(8, json1.fieldNames().size());

        options2 = new IndexOptions(json1);
        json2 = options2.toJson();

        assertEquals(json1.encode(), json2.encode());

        options2 = new IndexOptions(options1);
        json2 = options2.toJson();

        assertEquals(json1.encode(), json2.encode());

    }

}
