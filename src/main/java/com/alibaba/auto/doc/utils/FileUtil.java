/*
 * Copyright 1999-2021 Alibaba Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.auto.doc.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author ：杨帆（舲扬）
 * @date ：Created in 2020/10/23 12:06 下午
 * @description：
 */
public class FileUtil {

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * Use nio write file
     *
     * @param contents string contents
     * @param filePath file path
     * @return boolean
     */
    public static boolean nioWriteFile(String contents, String filePath) {
        return nioWriteFile(filePath, contents, null);
    }

    /**
     * Use nio write file
     *
     * @param filePath   file path
     * @param contents   string contents
     * @param openOption open or create options
     * @return boolean
     */
    private static boolean nioWriteFile(String filePath, String contents, OpenOption openOption) {
        Path path = Paths.get(filePath);
        try {
            Files.createDirectories(path.getParent());
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            if (null == openOption) {
                Files.write(path, contents.getBytes(DEFAULT_CHARSET));
            } else {
                Files.write(path, contents.getBytes(DEFAULT_CHARSET), openOption);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
