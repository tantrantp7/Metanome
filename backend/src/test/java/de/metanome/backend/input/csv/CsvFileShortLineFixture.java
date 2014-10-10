/*
 * Copyright 2014 by the Metanome project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.metanome.backend.input.csv;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

import de.metanome.algorithm_integration.input.InputGenerationException;
import de.metanome.algorithm_integration.input.InputIterationException;

import java.io.StringReader;

/**
 * A fixture generating a csv file with 4 rows. Rows 2 and 4 have differing lengths (2 (short) and 4
 * (long)).
 *
 * @author Jakob Zwiener
 */
public class CsvFileShortLineFixture {

  public FileIterator getTestData() throws InputGenerationException, InputIterationException {
    return getTestData(false);
  }

  public FileIterator getTestData(boolean skipDifferingLines)
      throws InputIterationException, InputGenerationException {
    FileIterator iterator = new FileIterator("some_file");
    return iterator.setSeparator(',')
        .setQuoteChar('\'')
        .setEscapeChar('\\')
        .setSkipLines(0)
        .setStrictQuotes(false)
        .setIgnoreLeadingWhiteSpace(true)
        .setHasHeader(false)
        .setSkipDifferingLines(skipDifferingLines)
        .setReader(new StringReader(
        Joiner.on(',').join(getExpectedFirstParsableLine()) + "\nfour,five\n" + Joiner.on(',')
            .join(getExpectedSecondParsableLine()) + "\nnine,ten,eleven,twelve"));
  }

  public ImmutableList<String> getExpectedFirstParsableLine() {
    return ImmutableList.of("one", "two", "three");
  }

  public ImmutableList<String> getExpectedSecondParsableLine() {
    return ImmutableList.of("six", "seven", "eight");
  }

}
