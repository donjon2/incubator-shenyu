/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.plugin.mock.generator;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shenyu.plugin.mock.util.RandomUtil;
import org.apache.shenyu.spi.Join;

import java.util.List;

/**
 * Random english string generator.
 */
@Join
public class EnStringGenerator implements Generator<String> {
    
    private int min;
    
    private int max;
    
    @Override
    public String getName() {
        return "zh";
    }
    
    @Override
    public String generate() {
        return RandomStringUtils.random(RandomUtil.randomInt(min, max), 5, 129, true, false);
    }
    
    @Override
    public int getParamSize() {
        return 1;
    }
    
    @Override
    public void initParam(final List<String> params) {
        String[] range = params.get(0).split("-");
        min = Integer.parseInt(range[0]);
        max = Integer.parseInt(range[1]);
    }
    
    @Override
    public boolean match(final String rule) {
        return rule.matches("^en\\|\\d+-\\d+$");
    }
    
}
